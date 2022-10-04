package com.hzz.config;

import com.hzz.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


//这个也会被Spring容器托管，注册到容器中，它本身就是一个@Component。
// @Configuration代表是一个配置类，和我们之前的bean.xml一样
@Configuration //相当于: <beans >
@ComponentScan("com.hzz.pojo") //相当于: <context:component-scan>
@Import(HzConfig2.class)
public class HzConfig {

    //<bean id="getUser" class="com.hzz.pojo.User">
    // @Bean:注册一个bean，就相当于我们之前写的一个bean标签
    // 这个方法的名字，就相当于bean标签中id属性
    // 这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();// 就是返回要注入到bean的对象！
    }
}
