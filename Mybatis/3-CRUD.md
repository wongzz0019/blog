# 1、namespace

namespace中的包名要和Dao/Mapper接口的包名一致

- id：就是对应的namespace中的方法名
- resultType：SQL语句执行的返回值！
- parameterType：参数类型！

# 2、select

选择，查询语句

1. 编写接口

   ```java
   //根据ID查询用户
       User getUserById(int id);
   ```

   

2. 编写对应的mapper中的sql语句

   ```java
   <!--id="绑定的接口方法名"  parameterType="参数类型  全限定名"  resultType="返回类型"-->
   <select id="getUserById" parameterType="int" resultType="com.hzz.pojo.User">
       select * from mybatis.user where id = #{id}
   </select>
   ```

   

3. 测试

```java
@Test
public void getUserById(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    User userById = mapper.getUserById(1);

    System.out.println(userById);
    sqlSession.close();
}
```

# 3、insert 

1. 编写接口

   ```java
   //插入一个用户  这int是返回受影响的行数
   int addUser(User user);
   ```

2. 编写对应的mapper中的sql语句

   ```java
   <insert id="addUser" parameterType="com.hzz.pojo.User">
       insert into mybatis.user (id,name,pwd) values (#{id},#{name},#{pwd})
   </insert>
   ```

3. 测试

   ```java
   //增删改需要提交事务
   @Test
   public void addUser(){
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       int addUser = mapper.addUser(new User(4, "小花", "1103"));
       if (addUser > 0) {
           System.out.println("插入成功");
       }
       sqlSession.commit();
       sqlSession.close();
   }
   ```

   

# 4、update

1. 编写接口

   ```java
   //修改一个用户
   int updateUser(User user);
   ```

2. 编写对应的mapper中的sql语句

   ```java
   <update id="updateUser" parameterType="com.hzz.pojo.User" >
       update mybatis.user set name=#{name},pwd=#{pwd} where id=#{id}
   </update>
   ```

3. 测试

   ```java
   //增删改需要提交事务
   @Test
   public void updateUser(){
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       mapper.updateUser(new User(4, "小小", "555"));
       sqlSession.commit();
       sqlSession.close();
   }
   ```

# 5、delete

1. 编写接口

   ```java
   //删除一个用户
   int deleteUser(int id);
   ```

2. 编写对应的mapper中的sql语句

   ```java
   <delete id="deleteUser" parameterType="int">
       delete from mybatis.user where id=#{id}
   </delete>
   ```

3. 测试

   ```java
   //增删改需要提交事务
   @Test
   public void deleteUser(){
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       mapper.deleteUser(4);
       sqlSession.commit(); //提交事务
       sqlSession.close();
   }
   ```

**注意点：**

- 增删改需要提交事务！

# 6、万能Map

假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑使用Map！

```java
//万能map修改用户
int updateUser2(Map<String,Object> map);
```

```xml
<update id="updateUser2" parameterType="map">
    update user set id=#{userid} where name=#{name}
</update>
```

```java
@Test //map 修改
public void updateUser2(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userid",4);
    map.put("name","光头强");
    mapper.updateUser2(map);

    sqlSession.commit();
    sqlSession.close();
}
```

Map传递参数，直接在sql中取出key即可！【parameterType="map"】

对象传递参数，直接在sql中取对象的属性即可！【parameterType="Object"】

只有一个基本类型参数的情况下，可以直接在sql中取到！

多个参数用Map，**或者注解!**

**多个参数用注解@param，这里parameterType="对应的Bean(实体)"**



# 7、模糊查询

like -- %  

1. java代码执行的时候，，传递通配符% %

   ```java
   List<User> userLike = mapper.getUserLike("%李%");
   ```

   

2. 在sql拼接中使用通配符

   ```java
   select * from mybatis.user where name like "%"#{value}"%"
   ```

   like concat('%' ,${value}, '%' ) 组合，可以防止sql注入

   ```xml
   select * from user where name like concat('%',${value},'%')
   ```

   