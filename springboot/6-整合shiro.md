# Shiro

**三大对象：**

1. Subject  用户
2. SecurityManager  管理所有用户
3. Realm  连接数据



1. 导入依赖

   ```xml
   <dependencies>
       <!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-core -->
       <dependency>
           <groupId>org.apache.shiro</groupId>
           <artifactId>shiro-core</artifactId>
           <version>1.8.0</version>
       </dependency>
   
   
       <!-- configure logging -->
       <!-- https://mvnrepository.com/artifact/org.slf4j/jcl-over-slf4j -->
       <dependency>
           <groupId>org.slf4j</groupId>
           <artifactId>jcl-over-slf4j</artifactId>
           <version>2.0.0-alpha5</version>
       </dependency>
   
       <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
       <dependency>
           <groupId>org.slf4j</groupId>
           <artifactId>slf4j-log4j12</artifactId>
           <version>2.0.0-alpha5</version>
   
       </dependency>
   
       <dependency>
           <groupId>log4j</groupId>
           <artifactId>log4j</artifactId>
           <version>1.2.17</version>
       </dependency>
   
   ```

   

2. 配置文件

   - log4j.properties

     ```properties
     log4j.rootLogger=INFO, stdout
     
     log4j.appender.stdout=org.apache.log4j.ConsoleAppender
     log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
     log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m %n
     
     # General Apache libraries
     log4j.logger.org.apache=WARN
     
     # Spring
     log4j.logger.org.springframework=WARN
     
     # Default Shiro logging
     log4j.logger.org.apache.shiro=INFO
     
     # Disable verbose logging
     log4j.logger.org.apache.shiro.util.ThreadContext=WARN
     log4j.logger.org.apache.shiro.cache.ehcache.EhCache=WARN
     ```

   - shiro.ini

     ```ini
     [users]
     # user 'root' with password 'secret' and the 'admin' role
     root = secret, admin
     # user 'guest' with the password 'guest' and the 'guest' role
     guest = guest, guest
     # user 'presidentskroob' with password '12345' ("That's the same combination on
     # my luggage!!!" ;)), and role 'president'
     presidentskroob = 12345, president
     # user 'darkhelmet' with password 'ludicrousspeed' and roles 'darklord' and 'schwartz'
     darkhelmet = ludicrousspeed, darklord, schwartz
     # user 'lonestarr' with password 'vespa' and roles 'goodguy' and 'schwartz'
     lonestarr = vespa, goodguy, schwartz
     
     # -----------------------------------------------------------------------------
     # Roles with assigned permissions
     #
     # Each line conforms to the format defined in the
     # org.apache.shiro.realm.text.TextConfigurationRealm#setRoleDefinitions JavaDoc
     # -----------------------------------------------------------------------------
     [roles]
     # 'admin' role has all permissions, indicated by the wildcard '*'
     admin = *
     # The 'schwartz' role can do anything (*) with any lightsaber:
     schwartz = lightsaber:*
     # The 'goodguy' role is allowed to 'drive' (action) the winnebago (type) with
     # license plate 'eagle5' (instance specific id)
     goodguy = winnebago:drive:eagle5
     ```

     

3. helloworld

   如果Quickstart报错，是factory方法过时了，需要将这两行代码
   Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
   SecurityManager securityManager = factory.getInstance();

   替换为
   DefaultSecurityManager securityManager = new DefaultSecurityManager();
   IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
   securityManager.setRealm(iniRealm);

   ```java
   public class Quickstart {
   
       private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);
   
   
       public static void main(String[] args) {
   
           //死代码
           DefaultSecurityManager securityManager = new DefaultSecurityManager();
           IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
           securityManager.setRealm(iniRealm);
           SecurityUtils.setSecurityManager(securityManager);
   
   
           //核心代码
           //获取当前的用户对象 Subject
           Subject currentUser = SecurityUtils.getSubject();
   
           //通过当前的用户拿到Session
           Session session = currentUser.getSession();
           session.setAttribute("someKey", "aValue");
           String value = (String) session.getAttribute("someKey");
           if (value.equals("aValue")) {
               log.info("Subject+>session [" + value + "]");
           }
   
           // 判断当前的用户是否被认证！
           if (!currentUser.isAuthenticated()) {
   
               //Token：令牌
               UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
               token.setRememberMe(true);//记住我
   
               try {
                   currentUser.login(token);//执行登录操作
   
               } catch (UnknownAccountException uae) { //用户名不存在异常
                   log.info("There is no user with username of " + token.getPrincipal());
               } catch (IncorrectCredentialsException ice) { //密码错误异常
                   log.info("Password for account " + token.getPrincipal() + " was incorrect!");
               } catch (LockedAccountException lae) { //用户被锁定异常
                   log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                           "Please contact your administrator to unlock it.");
               }
               // ... catch more exceptions here (maybe custom ones specific to your application?
               catch (AuthenticationException ae) { //认证异常！！
                   //unexpected condition?  error?
               }
           }
   
           //当前用户认证
           //print their identifying principal (in this case, a username):
           log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
   
           //用户拥有什么角色
           if (currentUser.hasRole("schwartz")) {
               log.info("May the Schwartz be with you!");
           } else {
               log.info("Hello, mere mortal.");
           }
   
           //粗粒度
           //test a typed permission (not instance-level)
           if (currentUser.isPermitted("lightsaber:wield")) {
               log.info("You may use a lightsaber ring.  Use it wisely.");
           } else {
               log.info("Sorry, lightsaber rings are for schwartz masters only.");
           }
   
           //细粒度
           //a (very powerful) Instance Level permission:
           if (currentUser.isPermitted("winnebago:drive:eagle5")) {
               log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                       "Here are the keys - have fun!");
           } else {
               log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
           }
   
           //注销
           //all done - log out!
           currentUser.logout();
   
           //结束
           System.exit(0);
       }
   }
   ```

   



```java
//获取当前的用户对象 Subject
Subject currentUser = SecurityUtils.getSubject();
//通过当前的用户拿到Session
Session session = currentUser.getSession();
// 判断当前的用户是否被认证！
currentUser.isAuthenticated()
//当前用户认证
currentUser.getPrincipal()
//用户拥有什么角色
currentUser.hasRole("schwartz")
//获得当前用户的一些权限
currentUser.isPermitted("lightsaber:wield")
//注销
currentUser.logout();
```



## SpringBoot中整合shiro

1. 导入整合shiro-spring整合依赖

   ```xml
   <dependency>
       <groupId>org.apache.shiro</groupId>
       <artifactId>shiro-spring</artifactId>
       <version>1.8.0</version>
   </dependency>
   ```

2. 编写核心配置类ShiroConfig和UserRealm

   UserRealm类

   ```java
   /**
    * 自定义的UserRealm  extends AuthorizingRealm
    */
   public class UserRealm extends AuthorizingRealm {
   
       //授权
       @Override
       protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
           System.out.println("执行了=》授权doGetAuthorizationInfo");
           return null;
       }
   
       //认证
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
           System.out.println("执行了=》doGetAuthenticationInfo");
           return null;
       }
   }
   
   ```

   ShiroConfig类

   ```java
   @Configuration
   public class ShiroConfig {
   
       //ShiroFilterFactoryBean:3(编写顺序3)
       @Bean
       public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
           ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
           //设置安全管理
           bean.setSecurityManager(defaultWebSecurityManager);
           return bean;
       }
   
       //DefaultWeSecurityManager:2
       @Bean(name = "securityManager")
       public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
           DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
           //关联UserRealm
           securityManager.setRealm(userRealm);
           return securityManager;
       }
       //创建realm对象，需要自定义类:1
       @Bean
       public UserRealm userRealm(){
           return new UserRealm();
       }
   }
   ```



## Shiro实现登录拦截

1. 导入thymeleaf依赖，并创建index.html ,add.html,update.html

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-thymeleaf</artifactId>
   </dependency>
   ```

   index.html

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
   <h1>首页</h1>
   <p th:text="${msg}"></p>
   <hr>
   
   <a th:href="@{/user/add}">add</a> | <a th:href="@{/user/update}">update</a>
   
   </body>
   </html>
   ```

   add.html,

   ```html
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
   <h1>add</h1>
   </body>
   </html>
   ```

   update.html

   ```html
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
   <h1>update</h1>
   </body>
   </html>
   ```

2. 在MyController类中编写页面跳转

   ```java
   @Controller
   public class MyController {
   
       @RequestMapping({"/","/index"})
       public String toIndex(Model model){
           model.addAttribute("msg","hello shiro");
           return "index";
       }
   
       @RequestMapping("/user/add")
       public String add(){
           return "/user/add";
       }
   
       @RequestMapping("/user/update")
       public String update(){
           return "/user/update";
       }
   }
   ```

3. 编写login.html用来实现Shiro的过滤

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
   <h1>登录</h1>
   <p th:text="${msg}" style="color: red"></p>
   <form th:action="@{/login}">
       <p>用户名：<input type="text" name="username"></p>
       <p>密码：<input type="text" name="password"></p>
       <p><input type="submit"></p>
   </form>
   </body>
   </html>
   ```

   

4. 在ShiroConfig类中的第一@Bean编写登录拦截过滤器

   ```java
   //添加shiro的内置过滤器
   /*
               anon:无需认证就可以访问
               authc：必须认证了才能访问
               user：必须拥有 记住我 功能才能用
               perms：拥有对某个资源的权限才能访问
               role ：拥有某个角色权限才能访问
           */
   LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
   
   //拦截
   //filterMap.put("/user/add","authc");
   //filterMap.put("/user/update","authc");
   filterMap.put("/user/*","authc");
   
   bean.setFilterChainDefinitionMap(filterMap);
   
   //设置登录的请求,没登陆的会跳转到/toLogin路径
   bean.setLoginUrl("/toLogin");
   
   return bean;
   ```

5. MyController中编写没登陆的跳转就跳转到登录页面

   ```java
   @RequestMapping("/toLogin")
       public String toLogin(){
           return "login";
       }
   ```



## Shiro实现用户认证

1. 在MyController中编写用户认证有没异常

   ```java
   @RequestMapping("/login")
   public String login(String username,String password,Model model){
       //获取当前的用户
       Subject subject = SecurityUtils.getSubject();
       //封装用户的登录数据
       UsernamePasswordToken token = new UsernamePasswordToken(username, password);
       try{
           //执行登录的方法，如果没有异常就说明ok了
           subject.login(token);
           return "index";
       }catch (UnknownAccountException e){
           model.addAttribute("msg","用户名错误");
           return "login";
       }catch (IncorrectCredentialsException e){
           model.addAttribute("msg","密码错误");
           return "login";
       }
   }
   ```

2. 修改login.html页面的form表单的跳转路径

   ```html
   <form th:action="@{/login}">
   ```

   

3. 在UserRealm类中的重写认证方法中编写

   ```java
   //认证
   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       System.out.println("执行了=》doGetAuthenticationInfo");
   
       //用户名，密码  数据库中取
       String name="root";
       String password="123456";
   
       //类的参数需要强转为UsernamePasswordToken(这是个MyController里的封装用户)
       UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
   
       if (!userToken.getUsername().equals(name)){
           return null; //抛出异常  UnknownAccountException
       }
   
       //密码认证，shiro做，不需要我们做
       return new SimpleAuthenticationInfo("",password,"");
   }
   ```



## Shiro整合Mybatis

1. ​	导入依赖

   ```xml
   <dependency>
       <groupId>org.mybatis.spring.boot</groupId>
       <artifactId>mybatis-spring-boot-starter</artifactId>
       <version>2.2.1</version>
   </dependency>
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
   </dependency>
   <dependency>
       <groupId>log4j</groupId>
       <artifactId>log4j</artifactId>
       <version>1.2.12</version>
   </dependency>
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>druid-spring-boot-starter</artifactId>
       <version>1.2.8</version>
   </dependency>
   ```

2. 编写application.yaml配置文件

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8
       driver-class-name: com.mysql.cj.jdbc.Driver
       username: root
       password: 123456
       type: com.alibaba.druid.pool.DruidDataSource
   
   mybatis:
     type-aliases-package: com.hzz.pojo
     mapper-locations: classpath:mapper/*.xml
   ```

3. 连接数据库编写实体类

   ```java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class User {
       private int id;
       private String name;
       private String pwd;
   }
   ```

4. 编写Dao层接口和mapper映射文件

   ```java
   @Repository
   @Mapper
   public interface UserMapper {
       User queryUserByName(String name);
   }
   
   ```

   ```xml
   <?xml version="1.0" encoding="utf-8" ?>
   <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
   <mapper namespace="com.hzz.mapper.UserMapper">
   
       <select id="queryUserByName" parameterType="string" resultType="user">
           select * from mybatis.user where name = #{name}
       </select>
   </mapper>
   ```

5. 编写service层接口是Impl实现类

   ```java
   public interface UserService {
       User queryUserByName(String name);
   }
   ```

   ```java
   @Service
   public class UserServiceImpl implements UserService {
   
       @Autowired
       UserMapper userMapper;
   
       @Override
       public User queryUserByName(String name) {
           return userMapper.queryUserByName(name);
       }
   }
   ```

6. 在UserRealm类中自动装配userService，把认证里的改为真正数据库

   ```java
   @Autowired
   UserService userService;
   ```

   ```java
   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       System.out.println("执行了=》doGetAuthenticationInfo");
   
       //类的参数需要强转为UsernamePasswordToken(这是个MyController里的封装用户)
       UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
       //连接真实的数据库
       User user = userService.queryUserByName(userToken.getUsername());
   
       if (user == null){ //没有这个人
           return null; //抛出异常 UnknownAccountException
       }
   
       //密码认证，shiro做，不需要我们做
       return new SimpleAuthenticationInfo("",user.getPwd(),"");
   }
   ```



## Shiro请求授权实现

1. 在ShiroConfig类中的过滤方法添加授权 ，授权写在拦截前面

   ```java
   //授权，有user:add这权限才能访问。正常的情况下，没有授权会跳转到为授权页面
   filterMap.put("/user/add","perms[user:add]");
   ```

2. MyController中编写跳转未授权页面

   ```java
   @RequestMapping("/noauth")
   @ResponseBody
   public String unauthorized(){
       return "未授权无法访问";
   }
   ```

3. 在ShiroConfig类的过滤方法//设置未授权页面的请求

   ```java
   //设置未授权页面的请求
   bean.setUnauthorizedUrl("/noauth");
   ```

4. 在user表中添加一个perms权限属性

   ![image-20220104154819899](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220104154819899.png)

   给User实体类添加一个权限的属性

   ```java
   private String perms;
   ```

5. ShiroConfig中添加授权

   ```java
           //授权，有user:add这权限才能访问。正常的情况下，没有授权会跳转到为授权页面
           filterMap.put("/user/add","perms[user:add]");
           filterMap.put("/user/update","perms[user:update]");
   ```

6. 编写UserRealm中的授权 `doGetAuthorizationInfo`

   ```java
   //授权
   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       System.out.println("执行了=》授权doGetAuthorizationInfo");
       //SimpleAuthorizationInfo new一个简单的授权
       SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
       //拿到当前登录的这个对象
       Subject subject = SecurityUtils.getSubject();
       //这是个user，从下面认证获取的
       User currentUser = (User) subject.getPrincipal();
       //设置当前用户的权限
       info.addStringPermission(currentUser.getPerms());
   
       return info;
   }
   ```

7. 完事



## Shiro整合Thymeleaf

1. 导入整合依赖

   ```xml
   <!-- https://mvnrepository.com/artifact/com.github.theborakompanioni/thymeleaf-extras-shiro -->
   <dependency>
       <groupId>com.github.theborakompanioni</groupId>
       <artifactId>thymeleaf-extras-shiro</artifactId>
       <version>2.1.0</version>
   </dependency>
   ```

2. 编写ShiroConfig配置类

   ```java
   //整合ShiroDialect :用来整合shiro thymeleaf
   @Bean
   public ShiroDialect getShiroDialect(){
       return new ShiroDialect();
   }
   ```

3. 在index.html页面导入命名空间，并修改对应标签

   命名空间

   ```html
   xmlns:shiro="http://www.thymeleaf.org/thymeleaf-extras-shiro"
   ```

   修改标签

   ```html
   <h1>首页</h1>
   <!--shiro:notAuthenticated 没有认证就显示登录，就是成功登录了就不会显示登录-->
   <div shiro:notAuthenticated>
       <a th:href="@{/toLogin}">登录</a>
   </div>
   <p th:text="${msg}"></p>
   <hr>
   
   <!--//如果有user:add的权限就显示<a>标签add-->
   <div shiro:hasPermission="user:add">
       <a th:href="@{/user/add}">add</a>
   </div>
   
   <!--如果有user:update权限 就显示update-->
   <div shiro:hasPermission="user:update">
       <a th:href="@{/user/update}">update</a>
   </div>
   ```

4. 测试

   ![image-20220104212109263](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220104212109263.png)