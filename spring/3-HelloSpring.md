# HelloSpring

1. 新建一个maven项目，编写实体类

   ```java
   public class Hello
   {
       private String str;
   
       public String getStr() {
           return str;
       }
   
       public void setStr(String str) {
           this.str = str;
       }
   
       @Override
       public String toString() {
           return "hello{" +
                   "str='" + str + '\'' +
                   '}';
       }
   }
   ```

2. 编写配置文件beans.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <!--使用Spring来创建对象，在Spring这些都称为Bean
           类型 变量名 = new 类型();
           Hello hello = new Hello();
   
           id = 变量名
           class = new的对象
           property 相当于给对象中的属性设置一个值！
       -->
       <bean id="hello" class="com.hzz.pojo.Hello">
           <property name="str" value="Spring"/>
       </bean>
   </beans>
   ```

3. 测试

   ```java
   public class MyTest {
       public static void main(String[] args) {
           //解析beans.xml文件，获取Spring的上下文对象
           ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
           //getBean：参数为Spring配置文件中bean的id
           Hello hello = (Hello)context.getBean("hello");
           System.out.println(hello.toString());
       }
   }
   ```

   

**思考问题？**

- Hello对象是谁创建的？
  Hello对象是由Spring创建的。
- Hello对象的属性是怎么设置的？
  Hello对象的属性是由Spring容器设置的。

这个过程就叫控制反转：

控制：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用Spring后，对象是由Spring来创建的。

反转：程序本身不创建对象，而变成被动的接收对象。

依赖注入：就是利用set方法来进行注入的。

IOC是一种编程思想，由主动的编程变成被动的接收。

可以通过new ClassPathXmlApplicationContext去浏览一下底层源码。

**OK，到了现在，我们彻底不用在程序中去改动了，要实现不同的操作，只需要在xml配置文件中进行修改，**

**==所谓的IOC，一句话搞定：对象由Spring来创建，管理，装配！==**