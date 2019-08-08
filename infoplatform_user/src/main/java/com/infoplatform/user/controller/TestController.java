package com.infoplatform.user.controller;

import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/8 14:08
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @Value("${user.name}")
    private String name;

    @GetMapping(value = "hello_world")
    public BaseResponse gainWorld() {
        System.err.println("测试配置会不会自动更新:" + name);
        return new BaseResponse(StatusCode.SUCCESS, "Hello World!");
    }
}
