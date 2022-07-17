# 什么是AOP

AOP（Aspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
![image-20211203192751995](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20211203192751995.png)

# AOP在Spring中的作用

**==提供声明式事务；允许用户自定义切面==**

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志，安全，缓存，事务等等…
- 切面（ASPECT）：横切关注点被模块化的特殊对象。**即，它是一个类。**
- 通知（Advice）：切面必须要完成的工作。**即，它是类中的一个方法。**
- 目标（Target）：被通知对象。
- 代理（Proxy）：向目标对象应用通知之后创建的对象。
- 切入点（PointCut）：切面通知执行的“地点”的定义。**即，类方法执行的地点**
- 连接点（JointPoint）：与切入点匹配的执行点。

![image-20211203194138409](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20211203194138409.png)



SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice：

![image-20211203194215936](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20211203194215936.png)

即AOP在不改变原有代码的情况下，去增加新的功能。



# 使用Spring实现AOP

**【重点】使用AOP织入，需要导入一个依赖包！**

```XML
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.6</version>
</dependency>
```

方式一：使用Spring的API接口【主要SpringAPI接口实现】

1. 接口

   ```java
   public interface UserService {
       void add();
       void delete();
       void update();
       void select();
   }
   ```

2. 真实角色

   ```java
   public class UserServiceImpl implements UserService{
       public void add() {
           System.out.println("增加一个用户");
       }
   
       public void delete() {
           System.out.println("删除一个用户");
       }
   
       public void update() {
           System.out.println("更新一个用户");
       }
   
       public void select() {
           System.out.println("查询一个用户");
       }
   }
   ```

3. 日志功能（前置通知）

   ```java
   public class BeforeLog implements MethodBeforeAdvice {
   
       //method:要执行的目标对象的方法
       //args:参数
       //target:目标对象
       public void before(Method method, Object[] args, Object target) throws Throwable {
           System.out.println(target.getClass().getName()+"的"+method.getName()+"方法被执行了");
       }
   }
   ```

   后置通知

   ```java
   public class AfterLog implements AfterReturningAdvice {
   
       //returnValue：返回值
       public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
           System.out.println(target.getClass().getName()+"的"+method.getName()+"被执行了!"+"返回结果为："+returnValue);
       }
   }
   ```

4. applicationContext.xml 核心配置文件（注意：导入aop约束）

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/aop
           https://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <!--注册bean-->
       <bean id="userServiceImpl" class="com.hzz.service.UserServiceImpl"/>
       <bean id="beforeLog" class="com.hzz.log.BeforeLog"/>
       <bean id="afterLog" class="com.hzz.log.AfterLog"/>
   
       <!--方式一：使用原生Spring API接口-->
       <!--配置aop:需要导入aop的约束-->
       <aop:config>
           <!--切入点(pointcut)。id:切入点的昵称,随便写。 expression:表达式。 execution(要执行的位置！*(修饰符)*(返回类型)*(类名)*(方法名)*(参数))-->
           <aop:pointcut id="pointcut" expression="execution(* com.hzz.service.UserServiceImpl.*(..))"/>
   
           <!--advisor:环绕通知，执行环绕增加。 advice-ref:引用哪个方法。 pointcut-ref:引用哪个切入点 -->
           <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>
           <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
       </aop:config>
       
   </beans>
   ```

5. 测试（注意：要记得导入beans.xml文件，动态代理代理的是“接口”）

   ```java
   public class Test {
       public static void main(String[] args) {
           //谨记“要导入applicationContext.xml文件”。获取Spring的上下文环境
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           //注意：动态代理代理的是“接口”！！!
           UserService userServiceImplProxy = (UserService) context.getBean("userServiceImpl");
           userServiceImplProxy.add();
       }
   }
   ```



方法二：自定义类实现AOP【主要是切面定义】

1. 接口

   ```java
   public interface UserService {
       void add();
       void delete();
       void update();
       void select();
   }
   ```

2. 真实角色

   ```java
   public class UserServiceImpl implements UserService{
       public void add() {System.out.println("增加一个用户");}
       public void delete() {System.out.println("删除一个用户");}
       public void update() {System.out.println("更新一个用户");}
       public void select() {System.out.println("查询一个用户");}
   }
   ```

3. 自定义类

   ```java
   public class DiyPointcut {
       public void before(){System.out.println("====方法执行前====");}
       public void after(){System.out.println("====方法执行后====");}
   }
   ```

4. spring配置文件beans.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/aop
           https://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <!--方式二：自定义类。也要导入aop约束-->
       <bean id="diyPointcut" class="com.hzz.diy.DiyPointcut"/>
   
       <aop:config>
           <!--自定义切面，ref:要引用的自定义类-->
           <aop:aspect ref="diyPointcut">
               <!--切入点-->
               <aop:pointcut id="pointcut2" expression="execution(* com.hzz.service.UserServiceImpl.*(..))"/>
               <!--通知-->
               <aop:before method="before" pointcut-ref="pointcut2"/>
               <aop:after method="after" pointcut-ref="pointcut2"/>
           </aop:aspect>
       </aop:config>
       
   </beans>
   ```

5. 测试

   ```java
   public class Test {
       public static void main(String[] args) {
           //谨记“要导入applicationContext.xml文件”。获取Spring的上下文环境
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           //注意：动态代理代理的是“接口”！！!
           UserService userServiceImplProxy = (UserService) context.getBean("userServiceImpl");
           userServiceImplProxy.add();
       }
   }
   ```



方式三：使用注解实现AOP

1. 新建一个使用注解的类

   ```java
   //方式三:使用注解方式实现AOP
   //切面-方法-切入点
   
   @Aspect //标注这个类是一个切面; 切面
   public class AnnotationPointCut {
   
       //@通知(execution(切入点))
       @Before("execution(* com.hzz.service.UserServiceImpl.*(..))")
       public void Before(){
           System.out.println("====方法执行前====");
       }
   
       @After("execution(* com.hzz.service.UserServiceImpl.*(..))")
       public void after(){
           System.out.println("====方法执行后====");
       }
   
       //在环绕增强中,我们可以给定一个参数,代表我们要处理的切入点
       @Around("execution(* com.hzz.service.UserServiceImpl.*(..))")
       public void around(ProceedingJoinPoint jp) throws Throwable {
           System.out.println("环绕前");
   
           //获得签名
           Signature signature = jp.getSignature();
           System.out.println("signature:"+signature);
   
           Object proceed = jp.proceed();//执行方法。这代表的是切入点里的方法
           
           System.out.println(proceed);
           System.out.println("环绕后");
       }
   }
   ```

2. 在Spring配置文件中，注册bean，并增加支持注解的配置。

   ```xml
   <!--方式三:使用注解实现-->
   <bean id="annotation" class="com.hzz.diy.AnnotationPointCut"/>
   <!--开启注解支持!-->
   <aop:aspectj-autoproxy/>
   ```

3. 测试