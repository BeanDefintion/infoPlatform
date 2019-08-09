package com.infoplatform.gateway;

import com.api.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * GatewayApplicaton
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/8 10:45
 **/
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
}
