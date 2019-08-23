package com.infoplatform.friend.server.controller;


import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.api.common.util.CommonUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.infoplatform.friend.server.client.UserClient;
import com.infoplatform.friend.server.entity.PmFriend;
import com.infoplatform.friend.server.mapper.PmFriendMapper;
import com.infoplatform.friend.server.service.IPmFriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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

    @Autowired
    private UserClient userClient;

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "点击关注 {\"pmFriendId\": 2,\"pmFriendName\": \"斗罗大陆\"}", response = BaseResponse.class)
    @PostMapping("create")
    public BaseResponse create(HttpServletRequest request, @RequestBody PmFriend pmFriend) {
        Long userId = CommonUtil.gainUserIdByRequest(request);
        if (null != pmFriendService.selectByUserIdAndFriendId(userId, pmFriend.getPmFriendId())) {
            return new BaseResponse(StatusCode.FAIL, "您已经关注过该用户!");
        }
        PmFriend friend = pmFriendService.selectByUserIdAndFriendId(pmFriend.getPmFriendId(), userId);
        if (null != friend) {
            pmFriend.setIsLike(true);
            pmFriend.setUpdTime(LocalDateTime.now());
            friend.setIsLike(true);
            friendMapper.updateById(friend);
        }
        pmFriend.setCrtTime(LocalDateTime.now());
        pmFriend.setUpdTime(LocalDateTime.now());
        pmFriend.setPmUserId(userId);
        pmFriend.setPmUserName(CommonUtil.gainUserNameByRequest(request));
        friendMapper.insert(pmFriend);

        userClient.incFanscount(pmFriend.getPmFriendId(), 1);
        userClient.incFollowscount(userId, 1);
        return new BaseResponse(StatusCode.SUCCESS);
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("取消关注")
    @PostMapping("cancel")
    public BaseResponse cancle(HttpServletRequest request, @RequestBody PmFriend pmFriend) {
        Long userId = CommonUtil.gainUserIdByRequest(request);
        PmFriend friend = pmFriendService.selectByUserIdAndFriendId(pmFriend.getPmFriendId(), userId);
        if (null != friend) {
            friend.setIsLike(false);
            friendMapper.updateById(friend);
        }

        pmFriendService.deleteByUserIdAndFriendId(userId, pmFriend.getPmFriendId());
        userClient.incFollowscount(userId, -1);
        userClient.incFanscount(pmFriend.getPmFriendId(), -1);
        return new BaseResponse(StatusCode.SUCCESS);
    }

    @ApiOperation(value = "关注列表", response = PmFriend.class)
    @GetMapping("list/follow/{userId}")
    public BaseResponse listFollow(@PathVariable Long userId, int currentPage, int pageSize) {
        Page<PmFriend> page = new Page<>(currentPage, pageSize);
        return new BaseResponse(StatusCode.SUCCESS, pmFriendService.selectFollowListByUserId(page, userId));
    }


    @ApiOperation("粉丝列表")
    @GetMapping("list/fans/{userId}")
    public BaseResponse listFans(@PathVariable Long userId, int currentPage, int pageSize) {
        return new BaseResponse(StatusCode.SUCCESS, pmFriendService.selectFansByUserId(new Page<>(currentPage, pageSize), userId));
    }
}
