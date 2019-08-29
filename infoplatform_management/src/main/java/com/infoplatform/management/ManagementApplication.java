package com.infoplatform.management;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ManagementApplication
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/12 15:01
 **/
@EnableSwagger2Doc
@SpringBootApplication
public class ManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }


}
