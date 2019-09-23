package com.infoplatform.friend;

import com.api.common.util.RedisUtil;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * FriendApplication
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/13 13:39
 **/
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "com.infoplatform.friend.server.client")
@EnableSwagger2Doc
@MapperScan(basePackages = "com.infoplatform.friend.server.mapper")
@ComponentScan(basePackages = {"com.**.**", "com.infoplatform.friend.config.aop"})
@SpringCloudApplication
public class FriendApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(FriendApplication.class, args);
        Stream.of(context.getBeanDefinitionNames()).forEach((x) -> System.err.println("实例化的JavaBean个数是: " + context.getBeanDefinitionCount() + " 名称是: " + x));
    }

    @Primary
    @Bean
    public RedisTemplate redisTemplate() {
        return new RedisUtil().redisTemplate();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 你的最大单页限制数量
        paginationInterceptor.setLimit(100);
        return paginationInterceptor;
    }
}
