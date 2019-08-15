package com.infoplatform.user.server.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "TbUserPermit对象", description = "")
public class TbUserPermit implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "user_permit_id", type = IdType.AUTO)
    private Integer userPermitId;

    private Long userId;

    private Integer permitId;

    private LocalDateTime crtTime;

    private LocalDateTime updTime;


}
