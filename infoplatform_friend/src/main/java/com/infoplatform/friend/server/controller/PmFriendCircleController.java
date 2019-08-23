package com.infoplatform.friend.server.controller;


import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.api.common.util.CommonUtil;
import com.infoplatform.friend.server.entity.PmFriendCircle;
import com.infoplatform.friend.server.mapper.PmFriendCircleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jack
 * @since 2019-08-20
 */
@Api(tags = "朋友微服务(/friend/)-朋友圈")
@RestController
@RequestMapping("pm-friend-circle")
public class PmFriendCircleController {

    @Resource
    private PmFriendCircleMapper friendCircleMapper;

    @ApiOperation("发布朋友圈")
    @PostMapping("create")
    public BaseResponse create(HttpServletRequest request, @RequestBody PmFriendCircle pmFriendCircle) {
        pmFriendCircle.setCrtTime(LocalDateTime.now());
        pmFriendCircle.setUpdTime(LocalDateTime.now());
        pmFriendCircle.setCreatorId(CommonUtil.gainUserIdByRequest(request));
        pmFriendCircle.setCretorName(CommonUtil.gainUserNameByRequest(request));
        return new BaseResponse(StatusCode.SUCCESS);
    }

}
