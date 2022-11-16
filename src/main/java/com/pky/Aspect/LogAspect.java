package com.pky.Aspect;

import com.pky.Service.UserAOPAndListenerImpl;
import com.pky.dao.DetailsMapper;
import com.pky.dao.UserMapper;
import org.aspectj.lang.JoinPoint;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

//AOP面向切面，使得在页面转向index时切入方法
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    DetailsMapper detailsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAOPAndListenerImpl userAOPAndListener;

    @Pointcut("execution (* com.pky.Controller.RouterController.childView(..))")
    public void Pointcut(){

    }

    @Before("Pointcut()")
    public void doBefore(JoinPoint joinPoint){
        int id = userMapper.getUser(userAOPAndListener.findUser()).getID();
        detailsMapper.addStartTime(id, new java.sql.Timestamp(new Date().getTime()));
        log.info("访问时间：{}--访问接口：{}",new Date(),joinPoint.getSignature());
    }


}
