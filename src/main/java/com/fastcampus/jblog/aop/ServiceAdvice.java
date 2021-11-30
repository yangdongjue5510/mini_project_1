package com.fastcampus.jblog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class ServiceAdvice {
    @Before("BlogPointcut.servicePointcut()")
    public void beforeServiceLog(JoinPoint jp) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("[ 서비스 ] " + method +
                "() 매개변수 : " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "BlogPointcut.servicePointcut()", returning = "returnObj")
    public void afterServiceLog(JoinPoint jp, Object returnObj) {
        String method = jp.getSignature().getName();
        if(returnObj != null)
            System.out.println("[ 서비스 ] " + method + "() 리턴값 : " + returnObj);
    }
}
