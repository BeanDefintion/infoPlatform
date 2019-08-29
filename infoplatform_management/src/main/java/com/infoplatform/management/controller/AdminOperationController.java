package com.infoplatform.management.controller;

import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminOperationController
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/12 17:53
 **/
@RestController
@RequestMapping("admin")
public class AdminOperationController {

    @GetMapping("test")
    public BaseResponse test() {
        return new BaseResponse(StatusCode.INVALIDPARAMS);
    }

    @GetMapping("wait")
    public void wait1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
