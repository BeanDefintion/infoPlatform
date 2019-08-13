package com.platform.eureka;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 由于默认是开启CSRF，所以需要将其关闭，不然会出现如下错误：
 * Root name 'timestamp' does not match expected ('instance') for type [simple type, class com.netflix.appinfo.InstanceInfo]
 *
 * @author 15293
 * @since 10:38 2019/8/13
 **/
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf
        http.csrf().disable();
        // 开启认证
        super.configure(http);
    }
}