**思路：搭建环境-->导入Mybatis-->编写代码-->测试！**

# 搭建环境

搭建数据库

```mys	
CREATE DATABASE `mybatis`;

USE `mybatis`;

CREATE TABLE `user`(
	`id` INT(20) NOT NULL PRIMARY KEY,
	`name` VARCHAR(30) DEFAULT NULL,
	`pwd` VARCHAR(30) DEFAULT NULL

)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`,`name`,`pwd`)VALUES
(1,'小明','123456'),
(2,'小洪','123456'),
(3,'小李','123456')
```

新建项目

1. 新建一个普通的maven项目

2. 删除src目录

3. 导入maven依赖

   ```xml
   <!-- 导入依赖   -->
       <dependencies>
           <!--mysql驱动-->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.47</version>
           </dependency>
           <!--mybatis-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.2</version>
           </dependency>
           <!--junit-->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
       </dependencies>
   ```

   

# 创建一个模块

- 编写mybatis的核心配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
  <!--configuration核心配置文件-->
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                  <property name="username" value="root"/>
                  <property name="password" value="123456"/>
              </dataSource>
          </environment>
      </environments>
      
      <!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！！-->
      <mappers>
          <mapper resource="com/hzz/dao/UserMapper.xml"/>
      </mappers>
  
  </configuration>
  ```

  

- 编写mybatis工具类

  ```java
  //SqlSessionFactory --> sqlSession
  //1.把资源加载进来
  public class MybatisUtils {
      private static SqlSessionFactory sqlSessionFactory;
      static {
          try {
              //使用mybatis第一步：获取SqlSessionFactory对象
              String resource = "mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  
      //既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
      // SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
      //2.创建能执行SQL的对象
      public static SqlSession getSqlSession(){
          SqlSession sqlSession = sqlSessionFactory.openSession();
          return sqlSession;
      }
  
  }
  ```

# 编写代码

- 实体类

  ```java
  //实体类
  
  public class User {
      private int id;
      private String name;
      private String pwd;
  
      public User() {
      }
  
      public User(int id, String name, String pwd) {
          this.id = id;
          this.name = name;
          this.pwd = pwd;
      }
  
      public int getId() {
          return id;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getPwd() {
          return pwd;
      }
  
      public void setPwd(String pwd) {
          this.pwd = pwd;
      }
  }
  
  ```

- Dao接口

  ```java
  public interface UserMapper {
      List<User> getUserList();
  
  }
  ```

  

- 接口实现类：由原来的UserDaoImpl转变为一个Mapper配置文件      全限定名:com.hzz.....

  ```java
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <!--namespace=绑定一个对应的Dao/Mapper接口-->
  <mapper namespace="com.hzz.dao.UserMapper">
  
  <!-- select查询语句   -->
      <!--id="对应的接口方法名" resultType="返回的结果类型 全限定名" resultType:返回单个,resultMap:返回多个" -->
      <select id="getUserList" resultType="com.hzz.pojo.User">
          select * from mybatis.user
      </select>
      
  </mapper>
  ```

# 测试

mybatis 核心配置文件（Mybatis-config.xml）中注册mappers

- junit测试

```java
public class UserDaoTest {

    @Test
    public void test(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一：执行SQL
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);//获取接口的class对象
        List<User> userList = mapper.getUserList();//调用class对象的方法  UserMapper.xml

        //方法二  旧方法不推荐
        //List<User> userList = sqlSession.selectList("com.hzz.dao.UserMapper.getUserList");

        for (User user : userList) {
            System.out.println(user);
        }

        //关闭sqlSession
        sqlSession.close();
    }
}
```

（注意！）可能会遇到的问题：

1. 配置文件没有注册
2. 绑定接口错误
3. 方法名不对
4. 返回类型不对
5. Maven导出资源问题

