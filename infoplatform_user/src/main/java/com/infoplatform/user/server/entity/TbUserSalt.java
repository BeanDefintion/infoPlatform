package com.infoplatform.user.server.entity;

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
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbUserSalt对象", description="")
public class TbUserSalt implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_salt_id", type = IdType.AUTO)
    private Long userSaltId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户盐值")
    private String userSalt;

    private LocalDateTime crtTime;

    private LocalDateTime updateTime;


}
