package com.infoplatform.friend.friend.controller;


import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.infoplatform.friend.friend.entity.PmFriend;
import com.infoplatform.friend.friend.mapper.PmFriendMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jack
 * @since 2019-08-13
 */
@RestController
@RequestMapping("pm-friend")
@MapperScan(basePackages = "com.infoplatform.friend.friend.mapper")
@ComponentScan(basePackages = "com.**.**")
public class PmFriendController {

    @Resource
    private PmFriendMapper friendMapper;

    @PostMapping("create")
    public BaseResponse create(@RequestBody PmFriend pmFriend) {
        friendMapper.insert(pmFriend);
        return new BaseResponse(StatusCode.SUCCESS);
    }

}
