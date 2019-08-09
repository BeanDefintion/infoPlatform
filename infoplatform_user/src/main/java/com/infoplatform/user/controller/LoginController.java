package com.infoplatform.user.controller;

import com.api.common.constant.CommonConstant;
import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.api.common.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * LoginController
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/9 11:13
 **/
@RequestMapping("login")
@Api(tags = "用户微服务-登陆模块")
@RestController
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation(value = "登陆接口", notes = "token 认证签名 1小时过期 过期后需要用另一个参数refreshToken来进行更新 " +
            "refreshToken 24小时内有效 用于token过期后的续期")
//    @PostMapping("login")
    @GetMapping("login")
    public BaseResponse login(@RequestParam String userName, @RequestParam String password) {
        //账号密码校验
        if (StringUtils.equals(userName, "admin") && StringUtils.equals(password, "admin")) {
            //生成JWT
            String token = jwtUtil.createJWT("1", "tssb", "dog");
            //生成refreshToken
            String refreshToken = UUID.randomUUID().toString().replaceAll("-", "");
            //保存refreshToken至redis，使用hash结构保存使用中的token以及用户标识
            String refreshTokenKey = CommonConstant.REDISSTOREPREFIX + "REFRESHTOKEN:" + refreshToken;
            redisTemplate.opsForHash().put(refreshTokenKey, "token", token);
            redisTemplate.opsForHash().put(refreshTokenKey, "userName", userName);
            redisTemplate.expire(refreshTokenKey, 24 * 60 * 60, TimeUnit.SECONDS);
            //返回结果
            Map<String, Object> dataMap = new HashMap<>(15);
            dataMap.put("token", token);
            dataMap.put("refreshToken", refreshToken);
            return new BaseResponse(StatusCode.SUCCESS.getCode(), "成功", dataMap);
        }
        return new BaseResponse(StatusCode.FAIL);
    }

    @ApiOperation(value = "刷新token")
    @GetMapping("refresh")
//    @PostMapping("refresh")
    public BaseResponse refreshToken(@RequestParam String refreshToken, @RequestParam String token) {
        Map<String, Object> resultMap = new HashMap<>();
        String refreshTokenKey = CommonConstant.REDISSTOREPREFIX + "REFRESHTOKEN:" + refreshToken;
        String userName = (String) redisTemplate.opsForHash().get(refreshTokenKey, "userName");
        if (StringUtils.isBlank(userName)) {
            return new BaseResponse(StatusCode.FAIL, "refreshToken过期");
        }

        String newToken = jwtUtil.createJWT("1", "tssb", "dog");
        //替换当前token，并将旧token添加到黑名单
        String oldToken = (String) redisTemplate.opsForHash().get(refreshTokenKey, "token");
        redisTemplate.opsForHash().put(refreshTokenKey, "token", newToken);
        redisTemplate.opsForValue().set(refreshTokenKey, "", 24 * 60 * 60, TimeUnit.MILLISECONDS);
        resultMap.put("code", "10000");
        resultMap.put("data", newToken);
        return new BaseResponse(StatusCode.SUCCESS.getCode(), "成功", resultMap);
    }


}
