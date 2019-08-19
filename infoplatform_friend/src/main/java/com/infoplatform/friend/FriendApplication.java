package com.infoplatform.friend;

import com.api.common.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * FriendApplication
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/13 13:39
 **/
@EnableFeignClients(basePackages = "com.infoplatform.friend.server.client")
@EnableSwagger2Doc
@MapperScan(basePackages = "com.infoplatform.friend.server.mapper")
@SpringCloudApplication
public class FriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class, args);
    }

    @Primary
    @Bean
    public RedisTemplate redisTemplate() {
        return new RedisUtil().redisTemplate();
    }

//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//    }
}
