1. 导入相关jar包

   - junit
   - mybatis
   - mysql数据库
   - spring相关的
   - aop织入
   - mybatis-spring【新知识点】

   ```xml
   <dependencies>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.12</version>
       </dependency>
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.47</version>
       </dependency>
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.3.13</version>
       </dependency>
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.2</version>
       </dependency>
       <!--Spring操作数据库的话,还需要一个spring-jdbc -->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-jdbc</artifactId>
           <version>5.3.12</version>
       </dependency>
       <dependency>
           <groupId>org.aspectj</groupId>
           <artifactId>aspectjweaver</artifactId>
           <version>1.9.6</version>
       </dependency>
       <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis-spring</artifactId>
           <version>2.0.6</version>
       </dependency>
       <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <version>1.18.20</version>
       </dependency>
   </dependencies>
   
   ```

   

2. 编写配置文件

3. 测试



# 回忆mybatis

1. 编写实体类

   ```java
   @Data
   public class User {
       private int id;
       private String name;
       private String pwd;
   }
   ```

   

2. 编写核心配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
   
      <typeAliases>
          <package name="com.kuang.pojo"/>
      </typeAliases>
   
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;useUnicode=true&amp;characterEncoding=utf8"/>
                  <property name="username" value="root"/>
                  <property name="password" value="123456"/>
              </dataSource>
          </environment>
      </environments>
   
      <mappers>
          <package name="com.kuang.dao"/>
      </mappers>
   </configuration>
   ```

   

3. 编写接口

   ```java
   public interface UserMapper {
       public List<User> selectUser();
   }
   ```

   

4. 编写Mapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   
   <mapper namespace="com.kuang.mapper.UserMapper">
   
       <!--sql-->
       <select id="selectUser" resultType="user">
           select * from mybatis.user
       </select>
   </mapper>
   ```

   

5. 测试

   ```java
   @Test
   public void selectUser() throws IOException {
   
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
   
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
      List<User> userList = mapper.selectUser();
      for (User user: userList){
          System.out.println(user);
     }
   
      sqlSession.close();
   }
   ```



# 整合 Mybatis-Spring

**什么是MyBatis-Spring？**

MyBatis-Spring 会帮助你将 MyBatis 代码无缝地整合到 Spring 中。

文档链接：http://mybatis.org/spring/zh/index.html

如果使用 Maven 作为构建工具，仅需要在 pom.xml 中加入以下代码即可：

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.4</version>
</dependency>
```



## **整合实现一：**

1. 引入Spring配置文件spring-dao.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
   
   </beans>
   ```

2. 在Spring配置文件中 配置数据源替换mybaits的数据源

   ```xml
   <!--DataSource:使用Spring的数据源替换Mybatis的配置文件    c3p0 dbcp druid
           我们这里使用Spring提供的JDBC : org.springframework.jdbc.datasource-->
   <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
       <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
       <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
       <property name="username" value="root"/>
       <property name="password" value="123456"/>
   </bean>
   ```

3. 配置SqlSessionFactory，和绑定MyBatis配置文件，注册Mapper.xml文件

   ```xml
   <!--sqlSessionFactory -->
   <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
       <property name="dataSource" ref="datasource"/>
       <!--configuration  绑定Mybatis配置文件 -->
       <property name="configLocation" value="classpath:mybatis-config.xml"/>
       <!--mapperLocations  这是之前mybatis-config.xml中的注册每一个Mapper.xml-->
       <property name="mapperLocations" value="classpath:com/hzz/mapper/*.xml"/>
   </bean>
   ```

4. 注册sqlSessionTemplate(就是我们之前使用的SqlSession)，使用构造器注入sqlSessionFactory

   ```xml
   <!--SqlSessionTemplate:就是我们MybatisUtils使用的SqlSession-->
   <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
       <!--只能使用构造器注入sqlSessionFactory，因为它没有set方法-->
       <constructor-arg ref="sqlSessionFactory"/>
   </bean>
   
   ```

5. 需要UserMapper接口的UserMapperImpl 实现类，私有化sqlSessionTemplate

   ```java
   public class UserMapperImpl implements UserMapper{
   
       private SqlSessionTemplate sqlSession;
   
       public void setSqlSession(SqlSessionTemplate sqlSession) {
           this.sqlSession = sqlSession;
       }
   
       public List<User> selectUser() {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           return mapper.selectUser();
       }
   }
   
   ```

6. 将自己写的实现类，注入到Spring配置文件中   ==注意：UserMapperImpl实现类中有属性sqlSession，利用set方法给他赋值==

   ```xml
   <bean id="userMapperImpl" class="com.hzz.mapper.UserMapperImpl">
       <property name="sqlSession" ref="sqlSession"/>
   </bean>
   ```

7. 测试

   ```java
   public class MyTest {
       @Test
       public void test() throws IOException {
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           UserMapper userMapperImpl = (UserMapper) context.getBean("userMapperImpl1");
           for (User user : userMapperImpl.selectUser()) {
               System.out.println(user);
           }
       }
   }
   ```



## **整合实现二**：

用实现类继承SqlSessionDaoSupport，利用getSqlSession()方法获取sqlSession

![image-20211205161826908](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20211205161826908.png)

1. 新建一个实现类UserMapperImpl2

   ```java
   public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
   
   
       public List<User> selectUser() {
           SqlSession sqlSession = getSqlSession();
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           return mapper.selectUser();
       }
   }
   ```

2. 注入到Spring配置文件中。==注意：实现类的父类需要一个sqlSessionFactory参数，利用set方法注入==

   ```xml
   <bean id="userMapperImpl2" class="com.hzz.mapper.UserMapperImpl2">
           <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
       </bean>
   ```

3. 测试

   ```java
   @Test
   public void test () throws IOException {
       ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
   
       UserMapper userMapper = context.getBean("userMapperImpl2", UserMapper.class);
       for (User user : userMapper.selectUser()) {
           System.out.println(user);
       }
   }
   ```

