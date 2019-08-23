package com.infoplatform.friend.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.infoplatform.friend.server.enums.RelationTypeEnum;
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
@ApiModel(value="PmComment对象", description="")
public class PmComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    @ApiModelProperty(value = "评论用户id")
    private Long commentUserId;

    @ApiModelProperty(value = "评论用户名称")
    private String commetUserName;

    @ApiModelProperty(value = "评论的类型")
    private RelationTypeEnum commentType;

    @ApiModelProperty(value = "评论关联id")
    private Long commentRelationId;

    @ApiModelProperty(value = "回复的评论id")
    private Long commentReplyId;

    @ApiModelProperty(value = "评论内容(有字数限制)")
    private String commentContent;

    private LocalDateTime crtTime;

    private LocalDateTime updTime;


}
