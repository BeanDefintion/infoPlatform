package com.api.common.response;

import com.api.common.enums.StatusCode;
import lombok.Data;

/**
 * 全局通用返回参数
 *
 * @author 15293
 * @date 2019/7/30 11:26
 **/
@Data
public class BaseResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

}
