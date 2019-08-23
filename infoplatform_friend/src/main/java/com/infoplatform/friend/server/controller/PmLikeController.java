package com.infoplatform.friend.server.controller;


import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.api.common.util.CommonUtil;
import com.infoplatform.friend.server.entity.PmLike;
import com.infoplatform.friend.server.mapper.PmLikeMapper;
import com.infoplatform.friend.server.service.IPmLikeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jack
 * @since 2019-08-21
 */
@RestController
@RequestMapping("pm-like")
public class PmLikeController {

    @Resource
    private PmLikeMapper likeMapper;

    @Autowired
    private IPmLikeService pmLikeService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("点赞")
    @PostMapping("create")
    public BaseResponse create(HttpServletRequest request, @RequestBody PmLike pmLike) {
        Long userId = CommonUtil.gainUserIdByRequest(request);
        if (null != pmLikeService.selectByUserIdAndLikeId(userId, pmLike.getLikeType().getCode(), pmLike.getLikeRelationId())) {
            return new BaseResponse(StatusCode.FAIL, "你已经点过赞啦!");
        }

        pmLike.setLikeUserName(CommonUtil.gainUserNameByRequest(request));
        likeMapper.insert(pmLike);
        return new BaseResponse(StatusCode.SUCCESS);
    }

    @ApiOperation("取消点赞")
    @DeleteMapping("cancel/{likeId}")
    public BaseResponse cancel(HttpServletRequest request, @PathVariable Long likeId) {
        PmLike pmLike = likeMapper.selectById(likeId);
        if (CommonUtil.gainUserIdByRequest(request).equals(pmLike.getLikeUserId())) {
            likeMapper.deleteById(likeId);
        }
        return new BaseResponse(StatusCode.SUCCESS);
    }
}
