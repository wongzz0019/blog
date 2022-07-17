# 简介

- Spring：春天------>给软件行业带来了春天！
- 2002，首次推出了Spring框架的雏形：interface21框架！
- Spring框架即以interface21框架为基础，经过重新设计，并不断丰富其内涵，于2004年3月24日发布了1.0正式版。
- Rod Johnson，Spring Framework创始人，著名作者。很难想象Rod Johnson的学历，真的让好多人大吃一惊，他是悉尼大学的博士，然而他的专业不是计算机，而是音乐学。
- Spring理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架！
- SSH：Struct2 + Spring + Hibernate!
- SSM：SpringMVC + Spring + Mybatis!

官网：https://spring.io/projects/spring-framework#overview
官方下载地址：https://repo.spring.io/release/org/springframework/spring/
GitHub：https://github.com/spring-projects/spring-framework

```markdown
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.13</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.12</version>
</dependency>

```



# 优点

- Spring是一个开源的免费的框架（容器）！
- Spring是一个轻量级的、非入侵式的框架！
- 控制反转（IOC），面向切面编程（AOP）！
- 支持事务的处理，对框架整合的支持！

**==总结一句话：Spring就是一个轻量级的控制反转（IOC）和面向切面编程（AOP）的框架！==**



# 组成

![image-20211128162147637](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20211128162147637.png)



# 拓展

现代化的Java开发！说白就是基于Spring的开发！

- Spring Boot

  - 一个快速开发的脚手架。
  - 基于SpringBoot可以快速的开发单个微服务。
  - 约定大于配置。

- Spring Cloud

  - SpringCloud是基于SpringBoot实现的。

  

**弊端：发展了太久之后，违背了原来的理念！配置十分繁琐，人称：“配置地狱！”**