package com.infoplatform.friend.server.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.Setter;

/**
 * RelationTypeEnum
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/21 16:32
 **/
@Getter
public enum RelationTypeEnum {

    /**
     * 朋友圈类型
     **/
    FRIENDCIRCLE(1, "朋友圈");

    RelationTypeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }


    /**
     * 数据库中的值
     **/
    @EnumValue
    private final int code;
    /**
     * 描述
     **/
    private final String descp;
}
