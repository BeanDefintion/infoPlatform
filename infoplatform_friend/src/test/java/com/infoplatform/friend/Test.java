package com.infoplatform.friend;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.UUID;

/**
 * Test
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/23 16:15
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FriendApplication.class)
public class Test {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Long EXEC_RESULT = 1L;

    public boolean releaseLock(String key, String requestId) {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("/static/lua/redis.lua")));
        Object result = redisTemplate.execute(redisScript, new StringRedisSerializer(), new StringRedisSerializer(), Collections.singletonList(key), requestId);
        System.err.println("田帅是个大傻逼! " + result);
        if (EXEC_RESULT.equals(result)) {
            System.err.println(1111);
            return true;
        }
        System.err.println(2222);
        return false;

    }

    @org.junit.Test
    public void mmm() {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Thread a = new Thread(() -> {
                releaseLock(finalI + "", finalI + UUID.randomUUID().toString().substring(4));
            });
            a.start();
        }
    }

    public static void main(String[] args) {
        System.err.println(new ClassPathResource("/static/lua/redis.lua").exists());
    }

}
