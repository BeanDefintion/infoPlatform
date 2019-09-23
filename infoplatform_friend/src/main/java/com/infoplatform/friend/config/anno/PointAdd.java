package com.infoplatform.friend.config.anno;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @author jack
 * @since 2019/9/23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PointAdd {

    /**
     * 增加的值
     *
     * @return
     */
    String value() default "";
}
