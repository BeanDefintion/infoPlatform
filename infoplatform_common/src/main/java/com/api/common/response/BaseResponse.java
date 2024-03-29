package com.api.common.response;

import com.api.common.enums.StatusCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局通用返回参数
 *
 * @author 15293
 * @date 2019/7/30 11:26
 **/
@Data
public class BaseResponse<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public BaseResponse() {

    }

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

    public BaseResponse(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    public BaseResponse(StatusCode statusCode, String msg) {
        this.code = statusCode.getCode();
        this.msg = msg;
    }
}
