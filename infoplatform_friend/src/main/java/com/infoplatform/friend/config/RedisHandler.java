package com.infoplatform.friend.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

/**
 * 练习Redis脚本的用法
 **/
@Component
public class RedisHandler {

    private final Logger logger = LoggerFactory.getLogger(RedisHandler.class);

    /**
     * Redis锁 前缀
     **/
    private static final String LOCK_PREFIX = "redis_lock";
    /**
     * 过期时间 毫秒
     **/
    private static final int LOCK_EXPIRE = 300;

    /**
     * 释放锁lua脚本
     */
    private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    /**
     * 释放锁成功返回值
     */
    private static final Long RELEASE_LOCK_SUCCESS_RESULT = 1L;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 最终加强分布式锁
     * ()
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key) {
        String lock = LOCK_PREFIX + key;
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }


    /**
     * lua释放锁
     **/
    public Object luaReleaseLock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end ";
        DefaultRedisScript<Integer> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Integer.class);

        return redisTemplate.execute(redisScript, Collections.singletonList(key), value);
    }

    /**
     * 释放锁
     *
     * @param key
     * @param uuid
     * @return
     */
    private boolean releaseLock(String key, String uuid) {
        logger.info("release lock:{key:{},uuid:{}}", key, uuid);
        return redisTemplate.execute(
                (RedisConnection connection) -> connection.eval(
                        RELEASE_LOCK_LUA_SCRIPT.getBytes(),
                        ReturnType.INTEGER,
                        1,
                        key.getBytes(),
                        uuid.getBytes())
        ).equals(RELEASE_LOCK_SUCCESS_RESULT);
    }

    private static final String lockScript = "if (redis.call('exists',KEYS[1]) == 0 or redis.call('get',KEYS[1]) == ARGV[2]) then return redis.call('setex',KEYS[1],ARGV[1],ARGV[2]) else return -1 end";
    public static final String unlockScript = "if (redis.call('exists',KEYS[1]) == 0 or redis.call('get',KEYS[1]) == ARGV[1]) then return redis.call('del',KEYS[1]) else return -1 end";

    public boolean tryLock(String key, String requestId, int seconds) {

        Object eval = redisTemplate.execute(RedisScript.of(lockScript), Lists.newArrayList(key), Lists.newArrayList(String.valueOf(seconds), requestId));
        logger.info(String.valueOf(eval));
        if (null != eval && StringUtils.equals(Constant.SET_SUCCESS, String.valueOf(eval))) {
            return true;
        }
        return false;
    }

    public boolean unLock(String key, String requestId) {
        Object eval = redisTemplate.execute(RedisScript.of(unlockScript), Lists.newArrayList(key), Lists.newArrayList(requestId));
        logger.info(String.valueOf(eval));
        if (null == eval) {
            return false;
        }
        int affectedRows = Integer.parseInt(String.valueOf(eval));
        if (1 == affectedRows || 0 == affectedRows) {
            return true;
        }
        return false;
    }

    class Constant {
        public static final String SET_SUCCESS = "OK";
        public static final String SET_IF_NOT_EXISTS = "NX";
        public static final String SET_WITH_EXPIRES = "EX";
    }

}