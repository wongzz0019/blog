# 基础语法

```java
public class Hello{
    public static void main(String[] args){
        System.out.println("Hello word");
    }
}
```



# 命名规则

- 类名：单词首字母大写 - 驼峰式
- 变量名：首字母小写 - 驼峰式
- 常量名：全部大写，多个单词用“_”分隔
- 方法名：首字母小写 - 驼峰式（同变量名）



# 修饰符

## 1.访问修饰符

- public：公共的
- private：私有的
- protected：同一包内的类和所有子类可见
- default：同一包内可见

## 2.非访问修饰符

- static ：静态，用来修饰类方法和类变量
- final ：用来修饰类、方法和变量，修饰的变量为常量
- abstract ：用来创建抽象类和抽象方法
- synchronized 和 volatile ：用于线程



## 变量

- 类变量（静态变量）：static，定义类里，不用new可以直接用
- 实例变量
- 局部变量



# 数据类型

## 1.基本类型（四类八种）

-  整数型：byte、short、int、long
- 浮点型：float、double
- 字符型：char
- 布尔型：boolean

## 2.引用类型

- 类
- 接口
- 数组



# 运算符

## 1.算术运算符

- +、-、*、/、%（取余）、++、--

## 2.关系运算符

- ==、!=、>、<、>=、<=

## 3.逻辑运算符

- &&（逻辑与，两真为真）、||（逻辑或，一真为真）、！（逻辑非）

## 4.赋值运算符

- +=、-=、*=、/=、(%)=

## 5.条件运算符

- **?:**       a>b ? 1 : 2

## 6.instanceof 运算符

- 操作对象实例，检查该对象是否是一个特定类型

- ```java
  String name = "James";
  boolean result = name instanceof String; // 由于 name 是 String 类型，所以返回真
  ```



# 循环结构

## 1.while循环

```java
while( 布尔表达式 ) {
  //循环内容
}
```

## 2.do…while 循环

```java
//先运行do再判断
do {
       //代码语句
}while(布尔表达式);
```

## 3.for循环

```java
for(int i = 0; i < 5; i++){
    System.out.println(i);
}
```

## 4.增强 for 循环

 ```java
 int [] numbers = {10, 20, 30, 40, 50};
 for(int x : numbers ){
     System.out.println( x );
 }
 ```

## 5.break 关键字

- 跳出最里层的循环，并且继续执行该循环下面的语句

## 6.continue 关键字

- 结束本次循环，进行下一次循环



# 条件判断语句

## 1.if

```java
if(布尔表达式)
{
   //如果布尔表达式为true将执行的语句
}
```

## 2.if...else

```java
if(布尔表达式){
   //如果布尔表达式的值为true
}else{
   //如果布尔表达式的值为false
}
```

## 3.if...else if...else

```java
if(布尔表达式 1){
   //如果布尔表达式 1的值为true执行代码
}else if(布尔表达式 2){
   //如果布尔表达式 2的值为true执行代码
}else if(布尔表达式 3){
   //如果布尔表达式 3的值为true执行代码
}else {
   //如果以上布尔表达式都不为true执行代码
}
```

## 4.switch case 语句

```java
switch(expression){
    case value :
       //语句
       break; //可选
    case value :
       //语句
       break; //可选
    //你可以有任意数量的case语句
    default : //可选
       //语句
}
```



# 包装类

- 包装类  ---  基本数据类型
- Boolean  --  boolean
- Byte   --   byte 
- Short  --  short 
- Integer  --  int
- Long  --  long
- Character  --  char
- Float   --   float
- Double  --  double



# String 类

 **Java 中字符串属于对象**

```java
String s1 = "Runoob";              // String 直接创建
String s2 = s1;                    // 相同引用
String s3 = new String("Runoob");   // String 对象创建

// 字符串长度
String site = "www.baidu.com";
System.out.println( "长度" + site.length());

// 连接字符串
string1.concat(string2);
str = "hello" + "hi";

// 判断字符串是否为空
str.isEmpty();
....
```



## 数组

```java
// 声明数组变量
int[] arrayInt;
String[] arrayStr;

// 创建数组
// 第一种方式
int[] mylist1 = new int[3];
mylist1[0] = 1;
mylist1[1] = 2;
mylist1[2] = 3;
// 第二种方式
int[] mylist2 = {1,2,3,4}
```

## Arrays 类（工具类）

**java.util.Arrays 类能方便地操作数组**

- 对数组排序：通过 sort 方法,按升序
- 比较数组：通过 equals 方法比较数组中元素值是否相等



# 方法

```java
//静态方法
public static int max(int num1, int num2) {
  return num1 > num2 ? num1 : num2;
}
```

## 方法重载

**重载的方法必须拥有不同的参数列表**

```java
public static double max(double num1, double num2) {
  if (num1 > num2)
    return num1;
  else
    return num2;
}
```



# 异常处理

## 1.捕获异常

```java
try
{
   // 程序代码
}catch(ExceptionName e1)
{
   //Catch 块
}
```

## 2.多重捕获块

```java
try{
   // 程序代码
}catch(异常类型1 异常的变量名1){
  // 程序代码
}catch(异常类型2 异常的变量名2){
  // 程序代码
}catch(异常类型3 异常的变量名3){
  // 程序代码
}
```

## 3.throws/throw 关键字

```java
// throws 用在方法上
public void deposit(double amount) throws Exception
  {
    // 程序代码
  }

// throw 用在方法里面
public void deposit(double amount){
    // Method implementation
    throw new RemoteException();
  }
```

## 4.finally关键字

**无论是否发生异常，finally 代码块中的代码总会被执行。**

**在 finally 代码块中，可以运行清理类型等收尾善后性质的语句**

```java
try{
  // 程序代码
}catch(异常类型1 异常的变量名1){
  // 程序代码
}finally{
  // 程序代码
}
```





## 面向对象特征



## ==和equals区别

- 最大区别是一个是方法一个是运算符
- == 基本类型比较值，引用类型比较地址
- equals 默认是引用类型比较，但String、Integer重写equals变成值比较

## 重写和重载区别

- 重载：同个类中有多个名称相同的方法，但方法参数个数或参数类型不同
- 重写：父子类中，重写父类的方法，返回值类型、方法名、参数个数及类型都要和父类方法相同。









# 算法

## 冒泡

```java
public static void sort(int[] arr){
    int temp;
    for(int i = 0; i < arr.length; i++){
        for(int j = 0; j < arr.length-i-1; j++){
            if(arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
}
```

## 插入排序

