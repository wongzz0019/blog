# Bean的自动装配

- 自动装配是Spring满足bean依赖一种方式！
- Spring会在上下文中自动寻找，并自动给bean装配属性！

在Spring中有三种装配的方式：

1. 在xml中显式的配置；
2. 在java中显式配置；
3. **隐式的自动装配bean【重要】**



# 测试

环境搭建：一个人有两个宠物！



# ByName自动装配

```xml
<bean id="cat" class="com.hzz.pojo.Cat"/>
<bean id="dog" class="com.hzz.pojo.Dog"/>

<!--
        byName：会自动在容器上下文中查找，和自己对象set方法后面的值对应的bean id！
    -->
<bean id="people" class="com.hzz.pojo.People" autowire="byName">
    <property name="name" value="小明"/>
</bean>
```

# ByType自动装配

```xml
<bean id="cat" class="com.hzz.pojo.Cat"/>
<bean class="com.hzz.pojo.Dog"/> //使用了byType自动装配，某些类的 id可以不用写

<!--
    byType：会自动在容器上下文中查找，和自己对象属性类型相同的bean！
    -->
<bean id="people" class="com.hzz.pojo.People" autowire="byType">
    <property name="name" value="小明"/>
</bean>
```

小结：

- ByName的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致！
- ByType的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致！



# 使用注解实现自动装配

jdk1.5支持的注解，Spring2.5就支持注解了！

要使用注解须知：

1. 导入约束  context约束

   xmlns:context="http://www.springframework.org/schema/context"

   xsi:schemaLocation="http://www.springframework.org/schema/context
   	        https://www.springframework.org/schema/context/spring-context.xsd"

2. ==配置注解的支持==    <context:annotation-config/ >

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
   	        https://www.springframework.org/schema/beans/spring-beans.xsd
   	        http://www.springframework.org/schema/context
   	        https://www.springframework.org/schema/context/spring-context.xsd">
   		
   		<!--开启注解的支持    -->
           <context:annotation-config/>
   </beans>
   
   ```

**@Autowired注解**

直接在属性上使用即可！也可以在set方法上使用！

使用**@Autowired**我们就可以不用编写Set方法了，前提是你这个自动配置的属性在IOC（Spring）容器中存在，且符合名字ByName！

**科普：**

```xml
@Nullable 	字段标记了这个注解，说明这个字段可以为null;
```

```JAVA
public @interface Autowired {
    boolean required() default true;
    //默认为true，意思是不允许为空
}
```

测试代码

```java
public class People {
    //如果显式定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空
    @Autowired(required = false)
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;
}
```



如果**@Autowired**自动装配的环境比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用**@Qualifier(value = “xxx”)**去配置@Autowired的使用，指定一个唯一的bean对象注入！

```java
public class People {

    @Autowired
    @Qualifier(value = "cat22") //@Qualifier(value = "xx")选取相应<bean id="xx".../>的bean对象注入
    private Cat cat;
    
    @Autowired
    @Qualifier(value = "dog123")
    private Dog dog;
    private String name;
}
```

```xml
	<bean id="cat11" class="com.hzz.pojo.Cat"/>
    <bean id="cat22" class="com.hzz.pojo.Cat"/>
    <bean id="dog" class="com.hzz.pojo.Dog"/>
    <bean id="dog123" class="com.hzz.pojo.Dog"/>
    <bean id="dog44" class="com.hzz.pojo.Dog"/>
    <bean id="people" class="com.hzz.pojo.People"/>
```



**@Resource注解**

```java
public class People {
    @Resource(name = "cat22")
    private Cat cat;

    @Resource
    private Dog dog;
    private String name;
}
```

```xml
	<bean id="cat11" class="com.hzz.pojo.Cat"/>
    <bean id="cat22" class="com.hzz.pojo.Cat"/>
    <bean id="dog" class="com.hzz.pojo.Dog"/>
    <bean id="dog123" class="com.hzz.pojo.Dog"/>
    <bean id="dog44" class="com.hzz.pojo.Dog"/>
    <bean id="people" class="com.hzz.pojo.People"/>
```



小结：

@Resource和@Autowired的区别：

- 都是用来自动装配的，都可以放在属性字段上
- @ Autowired 通过byType的方式实现，而且必须要求这个对象存在！【常用】
- @ Resource 默认通过byName的方式实现，如果找不到名字，则通过byType实现！如果两个都找不到的情况下，就报错！【常用】
- 执行顺序不同：@Autowired 通过byType的方式实现。@Resource 默认通过byName的方式实现