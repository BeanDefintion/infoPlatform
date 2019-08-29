package com.infoplatform.gateway.controller;

import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultFallBackController {

    @RequestMapping("defaultFallBack")
    public BaseResponse defaultFallBack() {
        System.out.println("降级操作...");
        return new BaseResponse(StatusCode.HYSTRIXERROR);
    }

}
