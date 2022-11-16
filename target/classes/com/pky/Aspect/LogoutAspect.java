package com.pky.Aspect;

import org.aspectj.lang.annotation.Pointcut;

//废弃，无法找到SpringSecurity登出时的方法
public class LogoutAspect {

    @Pointcut("execution (* com.pky.config.SecurityConfig.configure(..))")
    public void Pointcut(){

    }

}
