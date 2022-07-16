# 继承

通过  **extends**  关键字继承，子类继承父类可重写父类的方法

```java
// 父类
class father {
    public void eat(){
        System.out.println("我喜欢吃饭")
    }
}
 
class son extends father {
    public void eat(){
        System.out.println("我喜欢面")
    }
}
```

### 1.super 与 this 关键字

- super：指向父类，实现对父类成员的访问
- this：指向自己的引用

## 2.final 关键字

- final 关键字声明类，不能被继承
- final 关键字修饰方法，该方法不能被子类重写



# 重写与重载

- 重写(Override)：重写父类的方法，返回值类型、方法名、参数个数及类型都要和父类方法相同
- 重载(Overload)：同个类中有多个名称相同的方法，但方法有不同的参数列表



# 抽象类

- 抽象类除了不能实例化对象之外，类的其它功能依然存在
- 由于**抽象类不能实例化对象**，所以抽象类**必须被继承**，才能被使用
- 使用 **abstract** 来定义抽象类

```java
public abstract class Employee{
	private String name;
    public Employee(String name){
        System.out.println("hhh");
        this.name = name;
    }
    ....
}
```

## 抽象方法

- 抽象方法**只包含**一个方法名，而没有方法体
- 使用 **abstract** 来定义
- 如果一个类包含抽象方法，那么该类必须是抽象类。
- 任何子类**必须重写**父类的抽象方法，或者声明自身为抽象类。

```java
public abstract class Employee
{
   private String name;
   
   // 抽象方法
   public abstract double computePay();
   
   //其余代码
}
```



# 封装

- 将抽象性函式接口的实现细节部分包装、隐藏起来

- 优点

  1. 良好的封装能够减少耦合。

  2. 类内部的结构可以自由修改。

  3. 可以对成员变量进行更精确的控制。

  4. 隐藏信息，实现细节。

```java
public class Person{
    private String name;

    public String getName(){
      return name;
    }

    public void setAge(int age){
      this.age = age;
    }
}
```



# 接口

- 接口是隐式抽象的，当声明一个接口的时候，不必使用**abstract**关键字。
- 接口中每一个方法也是隐式抽象的，声明时同样不需要**abstract**关键字。
- 接口中的方法都是公有的。

```java
// 定义接口
interface Animal {
   public void eat();
   public void travel();
}

// 实现接口
public class MammalInt implements Animal{
 
   public void eat(){
      System.out.println("Mammal eats");
   }
 
   public void travel(){
      System.out.println("Mammal travels");
   } 
 
   public static void main(String args[]){
      MammalInt m = new MammalInt();
      m.eat();
      m.travel();
   }
}
```



# 枚举

- 枚举类使用 **enum** 关键字来定义，各个常量使用逗号 **,** 来分割

```java
// 定义一个颜色的枚举类
enum Color
{
    RED, GREEN, BLUE;
}
 

public class Test
{
    // 执行输出结果
    public static void main(String[] args)
    {
        Color c1 = Color.RED;
        System.out.println(c1); // 输出结果为：RED
    }
}
```

### 内部类中使用枚举

```java
public class Test
{
    enum Color
    {
        RED, GREEN, BLUE;
    }
 
    // 执行输出结果
    public static void main(String[] args)
    {
        Color c1 = Color.RED;
        System.out.println(c1);// 输出结果为：RED
    }
}
```

### 迭代枚举元素

```java
enum Color
{
    RED, GREEN, BLUE;
}
public class MyClass {
  public static void main(String[] args) {
    for (Color myVar : Color.values()) {
      System.out.println(myVar);
    }
  }
}

/**
	输出结果为
	RED
	GREEN
	BLUE
*/
```

