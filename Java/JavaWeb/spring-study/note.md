##常用依赖
```xml
<dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.13</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
```

##约束
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

        <!--指定要扫描的包，这个包下的注解就会生效-->
        <context:component-scan base-package=""/>

<!--当使用 context:component-scan 后，就可以将 context:annotation-config移除。-->

        <!--注解驱动支持-->
        <context:annotation-config/>
</beans>
```

##注解说明
- @Nullable 	字段标记了这个注解，说明这个字段可以为null;
- @Autowired：通过类型来自动装配。名字
    如果Autowired不能唯一自动装配上属性，则需要使用@Qualifier(value = “xxx”)去配置
- @Resource：通过名字自动装配。类型


- @Component：组件，放在类上，说明这个类被Spring管理了，就是bean！<bean>
- @Value(" ")：放在属性或set方法上，相当于<property name="name" value="黄黄"/>

###（xx.java）配置类 （相当于applicationContext.xml）
- @Configuration：放在类上，代表是一个配置类，和之前的bean.xml一样,相当于<beans>，通常和@Bean一起用
- @Bean：放在方法上，就是一个bean，方法名是bean的id，返回类型式是bean的class
- @ComponentScan("包名")：指定要扫描的包，这个包下的注解就会生效,相当于<context:component-scan>
- @Import(xxx.class)：把其它配置类整合一起。相当于把其它beans.xml导入到总的applicationContext.xml中


