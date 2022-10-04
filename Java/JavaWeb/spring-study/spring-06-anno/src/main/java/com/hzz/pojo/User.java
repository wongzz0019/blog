package com.hzz.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//相当于<bean id="user" class="com.hzz.pojo.User"/>
//@Component 组件

@Component
@Scope("prototype")
public class User {

    public String name;

    //相当于<property name="name" value="黄黄"/>
    @Value("黄哥")
    public void setName(String name) {
        this.name = name;
    }
}
