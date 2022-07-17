# 整合Mybatis

## 1、导入mybatis依赖

```xml
<!-- 导入mybaits依赖-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.1</version>
</dependency>
```

## 2、在application.properties(yaml)中添加数据量的连接信息

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=123456
```



## 3、编写实体类

```java
public class User {
    private int id;
    private String name;
    private String pwd;
    有参无参、get、set
}
```

## 4、编写Dao层

### 4.1、编写mapper接口并写上==注解@Mapper、@Repository==

```java
/**
 * @Mapper 这个注解表示了这是一个 mybatis 的mapper 类；Dao
 * @Repository 主动标识当前类要交给spring容器管理 ,只能在Dao层使用
 */
@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
```

### 4.2、在src\main\resources\mybatis\mapper路径下加入UserMapper.xml配置文件"

```xml
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.hzz.mapper.UserMapper">

    <select id="queryUserList" resultType="user" useCache="true">
        select * from user
    </select>

    <select id="queryUserById" resultType="user">
        select * from user where id = #{id}
    </select>

    <insert id="addUser" parameterType="user">
        insert into user (id,name,pwd) values (#{id},#{name},#{pwd})
    </insert>

    <update id="updateUser" parameterType="user">
        update user set name=#{name},pwd=#{pwd} where id = #{id}
    </update>

    <delete id="deleteUser" parameterType="int">
        delete from user where id = #{id}
    </delete>
</mapper>
```

## ==5、在application.properties中添加mybatis的信息==

```properties
##spring集成Mybatis环境
#别名
mybatis.type-aliases-package=com.hzz.pojo
#绑定mybatis的xml文件  classpath代表resources目录了
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
```



## 6、编写service层

和之前学的mybatis一样

创建一个service接口类

创建一个实现接口的类 ，自动装配(调用)Dao层，重写servic接口的方法



## 7、编写controller

```java
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList = userMapper.queryUserList();
        for (User user : userList){
            System.out.println(user);
        }
        return userList;
    }
    
	.....
        
}
```

