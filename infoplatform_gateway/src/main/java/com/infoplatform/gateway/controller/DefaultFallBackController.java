package com.infoplatform.gateway.controller;

import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认的熔断处理类
 *
 * @author 15293
 * @since 14:01 2019/8/30
 **/
@RestController
public class DefaultFallBackController {

    @RequestMapping("defaultFallBack")
    public BaseResponse defaultFallBack(HttpServletRequest request) {
        System.out.println("服务器正忙,请稍后重试....");
        return new BaseResponse(StatusCode.HYSTRIXERROR);
    }

}
