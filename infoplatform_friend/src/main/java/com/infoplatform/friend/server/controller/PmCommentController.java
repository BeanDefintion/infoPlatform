package com.infoplatform.friend.server.controller;


import com.api.common.enums.StatusCode;
import com.api.common.response.BaseResponse;
import com.api.common.util.CommonUtil;
import com.infoplatform.friend.server.entity.PmComment;
import com.infoplatform.friend.server.entity.PmFriendCircle;
import com.infoplatform.friend.server.mapper.PmCommentMapper;
import com.infoplatform.friend.server.mapper.PmFriendCircleMapper;
import io.swagger.annotations.ApiOperation;
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
 * @since 2019-08-21
 */
@RestController
@RequestMapping("pm-comment")
public class PmCommentController {

    @Resource
    private PmCommentMapper commentMapper;

    @Resource
    private PmFriendCircleMapper friendCircleMapper;


    @ApiOperation("发表评论/回复评论")
    @PostMapping("create")
    public BaseResponse create(HttpServletRequest request, @RequestBody PmComment pmComment) {
        pmComment.setCommentUserId(CommonUtil.gainUserIdByRequest(request));
        pmComment.setCommetUserName(CommonUtil.gainUserNameByRequest(request));
        pmComment.setCrtTime(LocalDateTime.now());
        pmComment.setUpdTime(LocalDateTime.now());
        commentMapper.insert(pmComment);
        switch (pmComment.getCommentType().getCode()) {
            case 1:
                PmFriendCircle friendCircle = friendCircleMapper.selectById(pmComment.getCommentRelationId());
                friendCircle.setCommentNum(friendCircle.getCommentNum() + 1);
                friendCircleMapper.updateById(friendCircle);
                break;
            default:
                break;
        }

        return new BaseResponse(StatusCode.SUCCESS);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("delete/{commentId}")
    public BaseResponse deleteComment(HttpServletRequest request, @PathVariable Long commentId) {
        commentMapper.deleteById(commentId);
        return new BaseResponse(StatusCode.SUCCESS);
    }


}
