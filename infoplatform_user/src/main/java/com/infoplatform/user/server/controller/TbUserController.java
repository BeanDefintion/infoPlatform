package com.infoplatform.user.server.controller;


import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.infoplatform.user.server.service.ITbUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author jack
 * @since 2019-08-14
 */
@RestController
@RequestMapping("tb-user")
public class TbUserController {

    @Autowired
    private ITbUserService userService;

    @ApiOperation("增加粉丝")
    @ApiImplicitParam(name = "num", value = "增加的粉丝数", paramType = "path", required = true)
    @PutMapping(value = "/incfans/{num}")
    public BaseResponse incFanscount(HttpServletRequest request, @PathVariable int num) {
        Long userId = Long.valueOf(request.getHeader("authorization-userId"));
        userService.incFansCount(userId, num);
        return new BaseResponse(StatusCode.SUCCESS);
    }

    @ApiOperation("增加关注")
    @ApiImplicitParam(name = "num", value = "增加的粉丝数", paramType = "path", required = true)
    @PutMapping(value = "/incfollows/{num}")
    public BaseResponse incFollowcount(HttpServletRequest request, @PathVariable int num) {
        Long userId = Long.valueOf(request.getHeader("authorization-userId"));
        userService.incFollowCount(userId, num);
        return new BaseResponse(StatusCode.SUCCESS);
    }
}
