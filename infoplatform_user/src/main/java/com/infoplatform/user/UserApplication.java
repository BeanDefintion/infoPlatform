package com.infoplatform.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.Stream;

/**
 * UserApplication
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/7 16:08
 **/
@ComponentScan(basePackages = {"com.**.**"})
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UserApplication.class, args);
    }
}
