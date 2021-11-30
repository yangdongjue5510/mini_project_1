package com.fastcampus.jblog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class DaoAdvice {
    @Before("BlogPointcut.daoPointcut()")
    public void beforeDaoLog(JoinPoint jp) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("[ 레파지토리 ] " + method +
                "() 매개변수 : " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "BlogPointcut.daoPointcut()", returning = "returnObj")
    public void afterDaoLog(JoinPoint jp, Object returnObj) {
        String method = jp.getSignature().getName();
        if(returnObj != null)
            System.out.println("[ 레파지토리 ] " + method + "() 리턴값 : " + returnObj);
    }
}
