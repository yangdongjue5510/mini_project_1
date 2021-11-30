package com.fastcampus.jblog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class ControllerAdvice {
    @Before("BlogPointcut.controllerPointcut()")
    public void beforeControllerLog(JoinPoint jp) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("[ 컨트롤러 ] " + method +
                "() 매개변수 : " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "BlogPointcut.controllerPointcut()", returning = "returnObj")
    public void afterControllerLog(JoinPoint jp, Object returnObj) {
        String method = jp.getSignature().getName();
        if(returnObj != null)
            System.out.println("[ 컨트롤러 ] " + method + "() 리턴값 : " + returnObj);
    }
}
