package com.infoplatform.user;

import com.api.common.util.JwtUtil;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.stream.Stream;

/**
 * UserApplication
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/7 16:08
 **/
@MapperScan(basePackages = "com.infoplatform.user.server.mapper")
@ComponentScan(basePackages = {"com.**.**"})
@SpringCloudApplication
public class UserApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UserApplication.class, args);
        Stream.of(context.getBeanDefinitionNames()).forEach((x) -> System.err.println("实例化的JavaBean个数是: " + context.getBeanDefinitionCount() + " 名称是: " + x));
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource());
        return mybatisPlus;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}

