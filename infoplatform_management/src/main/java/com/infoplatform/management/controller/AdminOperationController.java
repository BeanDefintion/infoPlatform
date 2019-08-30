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
        System.err.println("接口测试成功");
        return new BaseResponse(StatusCode.SUCCESS);
    }

    @GetMapping("test/wait")
    public String waittest() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "等待5s!";
    }
}
