package com.hzz.pojo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//这里这个注解的意思，就是说明这个类被Spring接管了，注册到了容器中
/*<bean id="user" class="com.hzz.pojo.User">
    <property name="" value="">
  </bean>
 */

@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("黄黄")//属性注入值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
