package com.pky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    //自定义的国际化组件，改变语言
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //拦截器，没用SpringSecurity时，同样可以使用拦截器，动态代理模式进行AOP横切，拦截请求
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(new LoginHandlerInterceptor())
    //             .addPathPatterns("/**")
    //             .excludePathPatterns("/index.html","/","/user/login","/css/*","/js/**","/img/**");
    // }
}
