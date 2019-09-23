package com.infoplatform.friend.config.aop;

import com.infoplatform.friend.config.anno.PointAdd;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author jack
 * @since 2019/9/23
 */
@Slf4j
@Component
@Aspect
public class PointAddAspect {

    @Around(value = "@annotation(pointAdd)")
    public Object aroundMethod(ProceedingJoinPoint pjd, PointAdd pointAdd) {
        Object result = null;
        System.err.println(pointAdd.value());
        try {
            System.err.println("前置通知");
            result = pjd.proceed();
            System.err.println("后置通知");
        } catch (Throwable e) {
            System.err.println("异常通知");
        }
        System.err.println("返回通知");
        return result;
    }
}
