package com.api.common.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.ConfigFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName RedisConfig
 * @Descrription redis的配置类
 * @Author 15293
 * @DATE 2019/4/3 13:49
 * @Version 1.0
 **/
public class RedisUtil {

    /*** 密码**/
    private String password = ConfigFactory.load().getString("spring.redis.password");

    /*** ip**/
    private String hostName = ConfigFactory.load().getString("spring.redis.host");

    /*** 端口**/
    private int port = ConfigFactory.load().getInt("spring.redis.port");

    /***所用数据库**/
    private int database = ConfigFactory.load().getInt("spring.redis.database");

    /*** 连接最大数量**/
    private int maxActive = ConfigFactory.load().getInt("spring.redis.jedis.pool.max-active");

    /*** 最大空闲连接**/
    private int maxIdle = ConfigFactory.load().getInt("spring.redis.jedis.pool.max-idle");

    /*** 最小空闲连接**/
    private int minIdle = ConfigFactory.load().getInt("spring.redis.jedis.pool.min-idle");

    /*** 阻塞的最大等待时间**/
    private long maxWait = ConfigFactory.load().getLong("spring.redis.jedis.pool.max-wait");

    private RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(hostName);
        configuration.setPort(port);
        configuration.setPassword(RedisPassword.of(password));
        configuration.setDatabase(database);
        return configuration;
    }

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWait);
        return config;
    }

    private JedisConnectionFactory jedisConnectionFactory() {
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpb.poolConfig(jedisPoolConfig());
        return new JedisConnectionFactory(redisStandaloneConfiguration(), jpb.build());
    }

    public RedisTemplate redisTemplate() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate rt = new RedisTemplate();
        rt.setConnectionFactory(jedisConnectionFactory());
        RedisSerializer<?> rs = new StringRedisSerializer();
        rt.setKeySerializer(rs);
        rt.setValueSerializer(jackson2JsonRedisSerializer);
        rt.setHashKeySerializer(rs);
        rt.setHashValueSerializer(jackson2JsonRedisSerializer);
        rt.afterPropertiesSet();
        return rt;
    }


}
