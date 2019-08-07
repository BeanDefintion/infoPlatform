package com.api.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 全局通用状态码
 *
 * @author 15293
 * @since 2019/7/30 11:02
 **/
public enum StatusCode {

    /**
     * 成功
     **/
    SUCCESS(20000, "成功"),
    FAIL(20001, "失败"),
    LOGINERROR(20002, "用户名或密码错误"),
    ACCESSERROR(20003, "权限不足"),
    REMOTEERROR(20004, "远程调用失败"),
    REPERROR(20005, "重复操作"),
    INVALIDPARAMS(20000, "非法的参数"),
    usernotlogin(20000, "用户没有登陆");

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
