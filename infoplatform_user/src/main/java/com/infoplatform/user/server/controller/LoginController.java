package com.infoplatform.user.server.controller;

import com.api.common.constant.CommonConstant;
import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.api.common.util.CommonUtil;
import com.api.common.util.JwtUtil;
import com.infoplatform.user.server.entity.TbUser;
import com.infoplatform.user.server.entity.TbUserSalt;
import com.infoplatform.user.server.mapper.TbUserMapper;
import com.infoplatform.user.server.mapper.TbUserSaltMapper;
import com.infoplatform.user.server.service.ITbUserSaltService;
import com.infoplatform.user.server.service.ITbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Autowired
    private ITbUserService userService;

    @Autowired
    private ITbUserSaltService userSaltService;

    @Resource
    private TbUserMapper userMapper;

    @Resource
    private TbUserSaltMapper userSaltMapper;

    @ApiOperation(value = "登陆接口", notes = "token 认证签名 1小时过期 过期后需要用另一个参数refreshToken来进行更新 " +
            "refreshToken 24小时内有效 用于token过期后的续期")
    @GetMapping("token/gain/web")
    public BaseResponse tokenGain(@RequestParam String userName, @RequestParam String password) {
        //账号密码校验
        TbUser user = userService.findByLoginName(userName);
        if (user == null) return new BaseResponse(StatusCode.FAIL, "用户名或密码错误!");

        TbUserSalt userSalt = userSaltService.selectByUserId(user.getUserId());
        if (!user.getPassword().equals(CommonUtil.sha1Encoder(password + userSalt.getUserSalt())))
            return new BaseResponse(StatusCode.FAIL, "用户名或密码错误!");

        // 生成JWT
        String token = jwtUtil.createJWT(String.valueOf(user.getUserId()), userName, "", "");
        // 生成refreshToken
        String refreshToken = UUID.randomUUID().toString().replaceAll("-", "");
        // 保存refreshToken至redis，使用hash结构保存使用中的token以及用户标识
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

    @ApiOperation(value = "刷新token 前端应在获取到token的1小时内 例如50分钟 ")
    @PostMapping("token/refresh")
    public BaseResponse refreshToken(@RequestParam String refreshToken) {
        Map<String, Object> resultMap = new HashMap<>();
        String refreshTokenKey = CommonConstant.REDISSTOREPREFIX + "REFRESHTOKEN:" + refreshToken;
        String userName = (String) redisTemplate.opsForHash().get(refreshTokenKey, "userName");
        if (StringUtils.isBlank(userName)) {
            return new BaseResponse(StatusCode.FAIL, "refreshToken过期");
        }

        String newToken = jwtUtil.createJWT("1", "tssb", "dog", "");
        //替换当前token，并将旧token添加到黑名单
        String oldToken = (String) redisTemplate.opsForHash().get(refreshTokenKey, "token");
        redisTemplate.opsForHash().put(refreshTokenKey, "token", newToken);
        redisTemplate.opsForHash().put(refreshTokenKey, "userName", userName);
        redisTemplate.expire(refreshTokenKey, 24 * 60 * 60, TimeUnit.SECONDS);
        resultMap.put("code", "10000");
        resultMap.put("data", newToken);
        return new BaseResponse(StatusCode.SUCCESS.getCode(), "成功", resultMap);
    }

    @ApiOperation("注册,生成一个用户")
    @PostMapping("register")
    public BaseResponse register(@RequestParam String userName, @RequestParam String passWord) {
        if (null != userService.findByLoginName(userName)) {
            return new BaseResponse(StatusCode.FAIL, "该用户已存在!");
        }

        TbUser user = new TbUser();
        user.setRegisterType(0);
        user.setLoginName(userName);
        user.setCrtTime(LocalDateTime.now());
        user.setCrtTime(LocalDateTime.now());
        userMapper.insert(user);

        TbUserSalt userSalt = new TbUserSalt();
        userSalt.setUserId(user.getUserId());
        String userSaltString = UUID.randomUUID().toString().substring(0, 5);
        userSalt.setUserSalt(userSaltString);
        userSaltMapper.insert(userSalt);

        user.setPassword(CommonUtil.sha1Encoder(passWord + userSaltString));
        userMapper.updateById(user);

        return new BaseResponse(StatusCode.SUCCESS);
    }
}
