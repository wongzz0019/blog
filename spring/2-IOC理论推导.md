# IOC理论推导

1. UserDao 接口

   ```java
   public interface UserDao {
       void getUser();
   }
   ```

2. UserDaoImpl 实现类

   ```java
   public class UserDaoImpl implements UserDao {
       public void getUser() {
           System.out.println("默认获取用户数据");
       }
   }
   ```

3. UserService 业务接口

   ```java
   public interface UserService {
       void getUser();
   }
   ```

4. UserServiceImpl 业务实现类

   ```java
   public class UserServiceImpl implements UserService {
   
       private UserDao userDao = new UserDaoImpl();
   
       public void getUser() {
           userDao.getUser();
       }
   }
   ```

5. 测试

   ```java
   public class MyTest {
       public static void main(String[] args) {
   
           //用户实际调用的是业务层，dao层他们不需要接触！
           UserService userService = new UserServiceImpl();
           userService.getUser();
       }
   }
   ```

在我们之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改原代码！如果程序代码量十分大，修改一次的成本代价十分昂贵！



我们使用一个Set接口实现，已经发生了革命性的变化！

```java
private UserDao userDao;

//利用set进行动态实现值的注入！
public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
}
```

- 之前，程序是主动创建对象！控制权在程序猿手上！
- 使用了set注入后，程序不再具有主动性，而是变成了被动的接收对象！



这种思想，从本质上解决了问题，不用再去管理对象的创建了。系统的耦合性大大降低~，可以更加专注的在业务的实现上！这是IOC的原型！

![image-20220717153743763](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-111)

**IOC本质**
**控制反转IoC（Inversion of Control），是一种设计思想，DI（依赖注入）是实现IoC的一种方法，**也有人认为DI只是IoC的另一种说法。没有IoC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：获得依赖对象的方式反转了。

采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。
**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection，DI）。**