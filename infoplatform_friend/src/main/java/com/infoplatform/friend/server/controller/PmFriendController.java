package com.infoplatform.friend.server.controller;


import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.infoplatform.friend.server.entity.PmFriend;
import com.infoplatform.friend.server.mapper.PmFriendMapper;
import com.infoplatform.friend.server.service.IPmFriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jack
 * @since 2019-08-13
 */
@Api(tags = "朋友微服务(/friend/)-好友")
@RestController
@RequestMapping("pm-friend")
public class PmFriendController {

    @Resource
    private PmFriendMapper friendMapper;

    @Autowired
    private IPmFriendService pmFriendService;

    @ApiOperation("点击关注")
    @PostMapping("create")
    public BaseResponse create(HttpServletRequest request, @RequestBody PmFriend pmFriend) {
        Long userId = Long.valueOf(request.getHeader("authorization-userId"));
        PmFriend friend = pmFriendService.selectByUserIdAndFriendId(userId, pmFriend.getPmFriendId());
        friendMapper.insert(pmFriend);
        return new BaseResponse(StatusCode.SUCCESS);
    }

}
