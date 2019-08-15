package com.infoplatform.user.server.entity;

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
@ApiModel(value="TbRolePermit对象", description="")
public class TbRolePermit implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer rolePermitId;

    private Integer roleId;

    private Integer permitId;

    private LocalDateTime crtTime;

    private LocalDateTime updTime;


}
