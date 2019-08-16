package com.infoplatform.user.server.controller;


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
    @PostMapping(value = "/incfans/{num}")
    public void incFanscount(HttpServletRequest request, @PathVariable int num) {
        Long userId = Long.valueOf(request.getHeader("authorization-userId"));
        userService.incFansCount(userId, num);
    }
}
