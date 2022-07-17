# 别名

```xml
<!--别名，如果添加了别名，我们也可以使用别名获取这个对象-->
<alias name="user" alias="abcdefg"/>
```

# Bean的配置

```xml
<!--
      id:bean的唯一标识符，也就是相当于我们学的对象名
      class：bean对象所对应的全限定名：包名 + 类型
      name：也是别名，而且name 可以同时取多个别名
      -->
<bean id="userT" class="com.hzz.pojo.UserT" name="userT2 u2,u3;u4">
    <property name="name" value="小智"/>
</bean>
```

# import

这个import。一般用于团队开发使用，它可以将多个配置文件，导入合并为一个。

假设，现在项目中有多个人开发，这三个人负责不同的类开发，不同的类需要注册在不同的bean中，我们可以利用import将所有人的beans.xml合并为一个总的！

- 张三
- 李四
- 王五
- applicationContext.xml（总）

```xml
<import resource="beans.xml"/>
<import resource="beans1.xml"/>
<import resource="beans2.xml"/>
```

**使用的时候，直接使用总的配置就可以了。**