package com.infoplatform.user;

import com.api.common.util.JwtUtil;
import com.api.common.util.RedisUtil;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.spring4all.swagger.EnableSwagger2Doc;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.stream.Stream;

/**
 * UserApplication
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/7 16:08
 **/
@EnableSwagger2Doc
@MapperScan(basePackages = "com.infoplatform.user.server.mapper")
@SpringCloudApplication
public class UserApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UserApplication.class, args);
//        Stream.of(context.getBeanDefinitionNames()).forEach((x) ->
//        System.err.println("实例化的JavaBean个数是: " + context.getBeanDefinitionCount() + " 名称是: " + x));
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

