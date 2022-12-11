package com.pky.config;

import com.pky.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//集成SpringSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // private LoginFailureHandler loginFailureHandler;

    //管理认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/history/**").hasAnyRole("child","teenager","adult")
                .antMatchers("/child/**").hasAnyRole("child","teenager","adult")
                .antMatchers("/teenager/**").hasAnyRole("teenager","adult")
                .antMatchers("/adult/**").hasRole("adult")
                .antMatchers("/v/**").hasAnyRole("child","teenager","adult");

        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login/toLogin")
                .defaultSuccessUrl("/login/jumpGetface");
                //.loginProcessingUrl("/login");

        http.csrf().disable();  //关闭csrf功能，防止登出失败
        http.logout().logoutSuccessUrl("/");

        http.rememberMe().rememberMeParameter("remember");  //记住密码

    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    /*
        加载了AuthenticationConfiguration, 配置了认证信息
        这个类是来配置认证相关的核心类, 这个类的主要作用是
        向spring容器中注入AuthenticationManagerBuilder, AuthenticationManagerBuilder其实是使用了建造者模式,
        他能建造AuthenticationManager,是身份认证的入口
    */

    //管理凭证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //从数据库中获取数据
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());


        //从内存中获取数据
        // auth.userDetailsService(userMapper);
        // auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        //         .withUser("pky").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
        //         .and()
        //         .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("child")
        //         .and()
        //         .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
