package com.infoplatform.friend.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
@ApiModel(value="PmFriend对象", description="")
public class PmFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pm_id", type = IdType.AUTO)
    private Long pmId;

    @ApiModelProperty(value = "用户id")
    private Long pmUserId;

    @ApiModelProperty(value = "用户姓名")
    private String pmUserName;

    @ApiModelProperty(value = "被关注者id")
    private Long pmFriendId;

    @ApiModelProperty(value = "被关注者姓名")
    private String pmFriendName;

    @ApiModelProperty(value = "是否互相关注")
    private Boolean isLike;

    private LocalDateTime crtTime;

    private LocalDateTime updTime;


}
