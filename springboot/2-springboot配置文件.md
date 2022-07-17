# 配置文件

SpringBoot使用一个全局的配置文件 ， 配置文件名称是固定的

- application.properties
  - 语法结构 ：key=value
- application.yml
  - 语法结构 ：key：空格 value

**配置文件的作用 ：**修改SpringBoot自动配置的默认值，因为SpringBoot在底层都给我们自动配置好了；

比如我们可以在配置文件中修改Tomcat 默认启动的端口号！测试一下！

```
server.port=8081
```



# yaml概述

以前的配置文件，大多数都是使用xml来配置；比如一个简单的端口配置，我们来对比下yaml和xml

传统xml配置：

```xml
<server>
    <port>8081<port>
</server>
```

yaml配置:

```yaml
server：
  prot: 8080
```



# yaml基础语法

说明：语法要求严格！

1. 空格不能省略
2. 以缩进来控制层级关系，只要是左边对齐的一列数据都是同一个层级的。
3. 属性和值的大小写都是十分敏感的。

**字面量：普通的值  [ 数字，布尔值，字符串  ]**

字面量直接写在后面就可以 ， 字符串默认不用加上双引号或者单引号；

```yaml
k: v
```

```yaml
# k: y
#对空格的要求十分高
#普通的key-value

#可以注入到我们的配置类中

name: hzz

#对象
student:
  name: hzz
  age: 3

#对象行内写法
student2: {name: hzz,age: 3}

#数组
pets:
  - cat
  - dog
  - pig
  -
pets2: [cat,dog,pig]
```

**修改SpringBoot的默认端口号**

配置文件中添加，端口号的参数，就可以切换端口；

```yaml
server:
  port: 8082
```



**yaml文件更强大的地方在于，他可以给我们的实体类直接注入匹配值！**



# yaml注入配置文件

1. 在springboot项目中的resources目录下新建一个文件 application.yml

2. 编写一个实体类 Dog

   ```java
   package com.kuang.springboot.pojo;
    
    
   @Component  //注册bean到容器中
   public class Dog {
       private String name;
       private Integer age;
       
       //有参无参构造、get、set方法、toString()方法  
   }
   ```

3. 我们在编写一个复杂一点的实体类：Person 类

   ```java
   @Component //注册bean到容器中
   public class Person {
       private String name;
       private Integer age;
       private Boolean happy;
       private Date birth;
       private Map<String,Object> maps;
       private List<Object> lists;
       private Dog dog;
       
       //有参无参构造、get、set方法、toString()方法  
   }
   ```

4. 使用yaml配置的方式进行注入，写的时候注意区别和优势，编写一个yaml配置！

   ```yaml
   person:
     name: hzz
     age: 3
     happy: true
     birth: 2021/12/28
     maps: {k1: v1,k2: v2}
     lists: [code,music,book]
     dog:
       name: 旺财
       age: 1
   ```

5. 已经把person这个对象的所有值都写好了，现在来注入到我们的类中！

   要使用==@ConfigurationProperties(prefix = "person")==将配置文件中配置的每一个属性的值，映射到这个组件中

   ```java
   /*
   @ConfigurationProperties作用：
   将配置文件中配置的每一个属性的值，映射到这个组件中；
   告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
   参数 prefix = “person” : 将配置文件中的person下面的所有属性一一对应
   */
   @Component //注册bean
   @ConfigurationProperties(prefix = "person")
   public class Person {
       private String name;
       private Integer age;
       private Boolean happy;
       private Date birth;
       private Map<String,Object> maps;
       private List<Object> lists;
       private Dog dog;
   }
   ```

6. IDEA 提示，springboot配置注解处理器没有找到，让我们看文档，我们可以查看文档，找到一个依赖！

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-configuration-processor</artifactId>
       <optional>true</optional>
   </dependency>
   ```

7. 确认以上配置都OK之后，我们去测试类中测试一下：

   ```java
   @SpringBootTest
   class Springboot02ConfigApplicationTests {
   
       @Autowired
       private Person person;//将person自动注入进来
       
       @Test
       void contextLoads() {
           System.out.println(person);//打印person信息
       }
   
   }
   ```

8. 结果：所有值全部注入成功！

   ![image-20211228234416160](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20211228234416160.png)



# 多配置文件

profile是Spring对不同环境提供不同配置功能的支持，可以通过激活不同的环境版本，实现快速切换环境；

我们在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml , 用来指定多个环境版本；

例如：

application-test.properties 代表测试环境配置

application-dev.properties 代表开发环境配置

但是Springboot并不会直接启动这些配置文件，它默认使用application.properties主配置文件；

我们需要通过一个配置来选择需要激活的环境：

```
#比如在配置文件中指定使用dev环境，我们可以通过设置不同的端口号进行测试；
#我们启动SpringBoot，就可以看到已经切换到dev下的配置了；
spring.profiles.active=dev
```



# yaml的多文档块

和properties配置文件中一样，但是使用yml去实现不需要创建多个配置文件，更加方便了 !

```yaml
spring:
  profiles:
    active: prod #指定使用哪个环境

---

server:
  port: 8082
spring:
  config:
    activate:
      on-profile: dev

---

server:
  port: 8083
spring:
  config:
    activate:
      on-profile: prod

---

server:
  port: 8084
spring:
  config:
    activate:
      on-profile: test
```

**注意：如果yml和properties同时都配置了端口，并且没有激活其他环境 ， 默认会使用properties配置文件的！**



# 配置文件加载位置

**外部加载配置文件的方式十分多，我们选择最常用的即可，在开发的资源文件中进行配置！**

官方外部配置文件说明参考文档

![image-20211229124307401](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20211229124307401.png)

springboot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文件：

```
优先级1：项目路径下的config文件夹配置文件
优先级2：项目路径下配置文件
优先级3：资源路径下的config文件夹配置文件
优先级4：资源路径下配置文件
```

优先级由高到底，高优先级的配置会覆盖低优先级的配置；

**SpringBoot会从这四个位置全部加载主配置文件；互补配置；**