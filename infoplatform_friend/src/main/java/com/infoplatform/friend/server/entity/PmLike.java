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
@ApiModel(value = "PmLike对象", description = "")
public class PmLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "like_id", type = IdType.AUTO)
    private Long likeId;

    @ApiModelProperty(value = "点赞用户id")
    private Long likeUserId;

    @ApiModelProperty(value = "点赞用户名称")
    private String likeUserName;

    @ApiModelProperty(value = "点赞类型 枚举")
    private RelationTypeEnum likeType;

    @ApiModelProperty(value = "关联的Id")
    private Long likeRelationId;

    private LocalDateTime crtTime;

    private LocalDateTime updTime;


}
