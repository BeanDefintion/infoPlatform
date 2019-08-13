package com.infoplatform.friend.controller;

import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * FriendController
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/13 13:45
 **/
@RestController
@RequestMapping("friend")
public class FriendController {

    @GetMapping("create")
    public BaseResponse create(HttpServletRequest request) {
        System.err.println(request.getHeader("authorization-userName"));
        return new BaseResponse(StatusCode.SUCCESS);
    }
}
