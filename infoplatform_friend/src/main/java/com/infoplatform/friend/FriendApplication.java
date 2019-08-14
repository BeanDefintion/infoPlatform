package com.infoplatform.friend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * FriendApplication
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/13 13:39
 **/
@ComponentScan(basePackages = {"com.**.**"})
@MapperScan(basePackages = "com.infoplatform.friend.**.mapper")
@SpringCloudApplication
public class FriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class, args);
    }
}
