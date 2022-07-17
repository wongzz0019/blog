# 什么是动态SQL

==**动态SQL就是指根据不同的条件生成不同的SQL语句**==

利用动态SQL这一特性可以彻底摆脱这种痛苦

```
if
choose (when, otherwise)
trim (where, set)
foreach
```



# 搭建环境

```sql
CREATE TABLE `blog`(
    `id` VARCHAR(50) NOT NULL COMMENT '博客id',
    `title` VARCHAR(100) NOT NULL COMMENT '博标题',
    `author` VARCHAR(30) NOT NULL COMMENT '博客作者',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `views` INT(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

创建一个基础工程

1. 导包(mysql，mybatis，junit)

2. 编写核心配置文件(mybatis-config.xml)

3. 编写工具类(MybatisUtils)

4. 编写实体类

   ```java
   @Data
   public class blog {
       private String  id;
       private String title;
       private String author;
       private Date createTime;
       private int views;
   }
   ```

5. 编写实体类对应Mapper接口 和 Mapper.xml文件  

6. 解决实体类属性和数据库字段不一致的方法，核心配置文件中设置！！！

   ```xml
   <!--开启驼峰命名转换-->
           <setting name="mapUnderscoreToCamelCase" value="true"/>
   ```

   



# if

```xml
<select id="queryBlogIF" parameterType="map" resultType="com.hzz.pojo.Blog">
    select * from blog where 1=1
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != null">
        and author = #{author}
    </if>
</select>
```

# choose (when, otherwise)

```xml
<select id="queryBlogChoose" parameterType="map" resultType="com.hzz.pojo.Blog">
    select * from blog
    <where>
        <choose>
            <when test="title != null">
                title=#{title}
            </when>
            <when test="author != null">
                and author = #{author}
            </when>
            <otherwise>
                and views = #{views}
            </otherwise>
        </choose>
    </where>
</select>
```



# trim (where, set)

```xml
<select id="queryBlogIF" parameterType="map" resultType="com.hzz.pojo.Blog">
    select * from blog 
    <where>
    	<if test="title != null">
        	title = #{title}
    	</if>
    	<if test="author != null">
        	and author = #{author}
    	</if>
    </where>
</select>
```

```xml
<update id="updateBlog" parameterType="map">
    update mybatis.blog
    <set>
        <if test="title != null">
            title = #{title},
        </if>
        <if test="author != null">
            author=#{author}
        </if>
        where id = #{id}
    </set>
</update>
```



==**所谓动态SQL，本质还是SQL语句，只是我们可以在SQL层面，去执行一个逻辑代码**==



# SQL片段

有时候，我们会将一些功能的部分抽取出来，方便复用！

1. 使用SQL--id标签抽取公共的部分

   ```xml
   <sql id="if-title-author">
       <if test="title != null">
           title = #{title}
       </if>
       <if test="author != null">
           and author = #{author}
       </if>
   </sql>
   ```

2. 在需要使用的地方使用include标签引用即可

   ```xml
   <select id="queryBlogIF" parameterType="map" resultType="com.hzz.pojo.Blog">
       select * from blog
       <where>
           <include refid="if-title-author"></include>
       </where>
   </select>
   ```

注意事项：

- 最好基于单表来定义SQL片段
- SQL片段中不要存在where标签



# Foreach

```sql
select * from  where 1=1 and (id=1 or id=2 or id=3)
```

![image-20211125141034671](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20211125141034671.png)

![image-20211125145009476](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20211125145009476.png)



```xml
<!--
    select * from blog where 1=1 and (id=1 or id=2 or id=3)
    我们现在传递一个万能map，这map中可以存在一个集合！
    -->
<select id="queryBlogForeach" parameterType="map" resultType="com.hzz.pojo.Blog">
    select * from mybatis.blog
    <where>
        <foreach collection="ids" item="id" open="and (" close=")" separator="or">
            id=#{id}
        </foreach>
    </where>
</select>
```

```java
@Test
public void queryBlogForeachTest(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

    HashMap map = new HashMap();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ids.add(1);
    ids.add(2);
    ids.add(3);
    map.put("ids",ids);

    List<Blog> blogs = mapper.queryBlogForeach(map);
    for (Blog blog : blogs) {
        System.out.println(blog);
    }
    sqlSession.close();
}
```

**==动态SQL就是在拼接SQL语句，我们只要保证SQL的正确性，按照SQL的格式，去排列组合就可以==**

建议：

- 在MySQL中写出完整的SQL语句，再对应地去修改成为我们的SQL实现通用即可！