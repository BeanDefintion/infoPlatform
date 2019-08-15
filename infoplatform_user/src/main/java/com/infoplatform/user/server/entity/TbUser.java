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
 * 用户
 * </p>
 *
 * @author jack
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbUser对象", description="用户")
public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "注册类型 0是web 1是小程序")
    private Integer registerType;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "微信标志")
    private String openid;

    @ApiModelProperty(value = "登陆名称")
    private String loginName;

    @ApiModelProperty(value = "性别 0未知 1男 2女")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "E-Mail")
    private String email;

    @ApiModelProperty(value = "最后登陆日期")
    private LocalDateTime lastLoginDate;

    @ApiModelProperty(value = "粉丝数")
    private Integer fansCount;

    @ApiModelProperty(value = "关注数")
    private Integer followCount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime crtTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updTime;


}
