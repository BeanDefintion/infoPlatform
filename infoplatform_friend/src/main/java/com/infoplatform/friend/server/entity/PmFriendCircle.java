package com.infoplatform.friend.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.infoplatform.friend.config.SpringContextHolder;
import com.infoplatform.friend.server.enums.RelationTypeEnum;
import com.infoplatform.friend.server.service.IPmCommentService;
import com.infoplatform.friend.server.service.IPmLikeService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jack
 * @since 2019-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PmFriendCircle对象", description = "")
public class PmFriendCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "friend_circle_id", type = IdType.AUTO)
    private Long friendCircleId;

    @ApiModelProperty(value = "位置的文本信息")
    private String positionText;

    @ApiModelProperty(value = "位置的具体经度")
    private String positionLng;

    @ApiModelProperty(value = "维度")
    private String positionLat;

    @ApiModelProperty(value = "添加的照片,逗号隔开")
    private String photos;

    @ApiModelProperty(value = "朋友圈内容(限制300字)")
    private String context;

    @ApiModelProperty(value = "点赞的数目")
    private Integer likeNum;

    @ApiModelProperty(value = "评论的数目")
    private Integer commentNum;

    @ApiModelProperty(value = "创建者Id")
    private Long creatorId;

    @ApiModelProperty(value = "创建者姓名")
    private String cretorName;

    private LocalDateTime crtTime;

    private LocalDateTime updTime;

    /**
     * 获得点赞玩家列表
     **/
    public List<PmLike> getLikeUserList() {
        IPmLikeService likeService = (IPmLikeService) SpringContextHolder.getBean(IPmLikeService.class);
        return likeService.selectByTypeAndId(RelationTypeEnum.FRIENDCIRCLE.getCode(), friendCircleId);
    }

    /**
     * 获得评论玩家列表
     **/
    public List<PmComment> getCommentUserList() {
        IPmCommentService commentService = (IPmCommentService) SpringContextHolder.getBean(IPmCommentService.class);
        return commentService.selectByTypeAndId(RelationTypeEnum.FRIENDCIRCLE.getCode(), friendCircleId);
    }
}
