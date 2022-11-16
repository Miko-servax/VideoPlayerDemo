package com.pky.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

//配置SpringSecurity登录失败对应的时间（记不清了，应该已弃用）
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    //在前端显示登录失败信息
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String ,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","用户名或密码错误");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
        response.getWriter().write(objectMapper.writeValueAsString(map));
        request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION",map.get("msg"));
        request.getRequestDispatcher("/login/toLogin").forward(request,response);
    }
}
