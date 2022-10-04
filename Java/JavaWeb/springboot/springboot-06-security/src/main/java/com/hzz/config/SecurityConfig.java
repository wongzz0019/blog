package com.hzz.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Bosco
 * @date 2022/1/2
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能有只有对应有权限的人才能访问

        http.authorizeRequests()
                .antMatchers("/").permitAll()//首页允许所有人访问
                .antMatchers("/level1/**").hasRole("vip1")//vip1才能访问/level/**
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认会到登录页面，需要开启登录的页面
        //login,定制登录页。loginPage("/toLogin"),
        //自定义接收前端参数usernameParameter("user").passwordParameter("pwd")；表单提交的路径:loginProcessingUrl("/login")
        http.formLogin().loginPage("/toLogin").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");

        //注销
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能 cookie;自定义接收前端参数
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证 Authentication
    //在Spring Security 5.0+ 新增了很多加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //这些数据正常应该从数据库中读取的auth.jdbcAuthentication()
        //这里是从内存读取;  密码编码：PasswordEncoder
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                //withUser()登录的用户名， roles()角色，对应授权中的角色
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                //and() 连接 ，接着写下一个
                .and()
                .withUser("xiao").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("hzz").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
