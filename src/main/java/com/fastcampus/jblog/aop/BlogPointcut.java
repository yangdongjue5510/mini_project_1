package com.fastcampus.jblog.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BlogPointcut {
    @Pointcut("execution(* com.fastcampus.jblog.controller..*Controller.*(..))")
    public void controllerPointcut() {}

    @Pointcut("execution(* com.fastcampus.jblog.biz..*Impl.*(..))")
    public void servicePointcut() {}

    @Pointcut("execution(* com.fastcampus.jblog.biz..*DAO.*(..))")
    public void daoPointcut() {}
}
