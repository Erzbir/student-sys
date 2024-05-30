package com.erzbir.backend.aop;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Erzbir
 * @Data: 2024/5/29 13:26
 */
@Aspect
@Component
public class StringResponseAspect {
    @Around("@annotation(com.erzbir.backend.annotation.StringResponse)")
    public Object handleMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return stringResponse(joinPoint);
    }

    @Around("@within(com.erzbir.backend.annotation.StringResponse)")
    public Object handleClass(ProceedingJoinPoint joinPoint) throws Throwable {
        return stringResponse(joinPoint);
    }


    public Object stringResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        return proceed != null ? JSONUtil.toJsonStr(proceed) : "";
    }
}
