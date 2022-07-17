我们现在要完全不使用Spring的xml配置了，全权交给Java来做！
JavaConfig是Spring的一个子项目，在Spring4之后，它成为了一个核心功能！

![image-20211130161047946](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20211130161047946.png)



# 实体类

```java
//这里这个注解的意思，就是说明这个类被Spring接管了，注册到了容器中
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("黄黄")//属性注入值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

```

# 配置类（代替配置文件applicationContext.xml）

```java	
// @Configuration代表是一个配置类，和我们之前的bean.xml一样
@Configuration
@ComponentScan("com.hzz.pojo")
@Import(HzConfig2.class)
public class HzConfig {

    // 注册一个bean，就相当于我们之前写的一个bean标签
    // 这个方法的名字，就相当于bean标签中id属性
    // 这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();// 就是返回要注入到bean的对象！
    }
}

```

# 测试类

```java
public class MyTest {
    public static void main(String[] args) {
        //如果完全使用了配置类方式去做，我们就只能通过 AnnotationConfig 上下文来获取容器，通过配置类的class对象加载！
        ApplicationContext context = new AnnotationConfigApplicationContext(HzConfig.class);
        User user = (User) context.getBean("getUser"); //配置类的方法名
        System.out.println( user.getName());
    }
}
```

这种纯Java的配置方式，在SpringBoot中随处可见！