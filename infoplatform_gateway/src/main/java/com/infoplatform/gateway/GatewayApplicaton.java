package com.infoplatform.gateway;

import com.api.common.util.JwtUtil;
import com.api.common.util.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * GatewayApplicaton
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/8 10:45
 **/
//@EnableHystrix
@ComponentScan(basePackages = "com.**.**")
@SpringBootApplication
public class GatewayApplicaton {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplicaton.class, args);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Primary
    @Bean
    public RedisTemplate redisTemplate() {
        return new RedisUtil().redisTemplate();
    }

}
