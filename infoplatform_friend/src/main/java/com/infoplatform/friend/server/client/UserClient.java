package com.infoplatform.friend.server.client;

import com.api.common.response.BaseResponse;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import javafx.beans.DefaultProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * UserClient
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/16 17:25
 **/
@FeignClient("infoplatform-user")
@DefaultProperties
public interface UserClient {

    /***服务调用--增加关注数*/
    @RequestMapping(value = "/user/tb-user/incfans/{userId}/{num}", method = RequestMethod.PUT)
    BaseResponse incFanscount(@PathVariable("userId") Long userId, @PathVariable("num") int num);

    /***服务调用--增加粉丝数*/
    @RequestMapping(value = "/user/tb-user/incfollows/{userId}/{num}", method = RequestMethod.PUT)
    BaseResponse incFollowscount(@PathVariable("userId") Long userId, @PathVariable("num") int num);

    /****/
}
