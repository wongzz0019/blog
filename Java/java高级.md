# ArrayList

- 可以动态修改的数组，大小没有限制
- 实现了 List 接口

```java
// 初始化
ArrayList<String> sites =new ArrayList<String>();　 

//添加元素
sites.add("Google");

//访问元素
sites.get(0);

//修改元素
sites.set(0, "Wiki");

//删除元素
sites.remove(0); 

//计算大小
sites.size();

//迭代数组列表
for (int i = 0; i < sites.size(); i++) {
    System.out.println(sites.get(i));
}

for (String i : sites) {
    System.out.println(i);
}
```



# LinkedList

- 链表（Linked list）是一种线性表
- 链表可分为单向链表和双向链表。
- 与 ArrayList 相比，LinkedList 的增删效率高，而查改效率低

```java
LinkedList<E> list = new LinkedList<E>();   // 普通创建方法
LinkedList<E> list = new LinkedList(Collection<? extends E> c); // 使用集合创建链表

//在列表开头添加元素：
sites.addFirst("Wiki");

//在列表结尾添加元素：
sites.addLast("Wiki");

//在列表开头移除元素:
sites.removeFirst();

//在列表结尾移除元素：
sites.removeLast();

//获取列表开头的元素：
sites.getFirst();

//获取列表结尾的元素：
sites.getLast();
```



# HashSet

- 基于 HashMap 来实现的，不允许有重复元素的集合
- 无序的，允许有 null 值
- 线程不安全
- HashSet 实现了 Set 接口

```java
//实例化
HashSet<String> sites = new HashSet<String>();

//添加元素
sites.add("Taobao");

//判断元素是否存在
sites.contains("Taobao");

//删除元素
sites.remove("Taobao"); 

//删除集合中所有元素
sites.clear();  

//计算大小
sites.size();

//迭代 HashSet
for (String i : sites) {
    System.out.println(i);
}
```



# HashMap

- 散列表，键值对(key-value)映射
- 实现了 Map 接口，访问速度块
- 最多允许一个键为 null
- 线程不安全
- 是无序的，即不会记录插入的顺序

```java
//实例化
HashMap<Integer, String> Sites = new HashMap<Integer, String>();

//添加元素
Sites.put(1, "Google");

//访问元素
Sites.get(1);

//删除元素
Sites.remove(1);

//删除所有键值对
Sites.clear();

//计算大小
Sites.size();

//迭代 HashMap
for (Integer i : Sites.keySet()) {
    System.out.println("key: " + i + " value: " + Sites.get(i)); // 输出 key 和 value
}

// 返回所有 value 值
for(String value: Sites.values()) {
    // 输出每一个value
    System.out.print(value + ", ");
}
```



# Iterator（迭代器）

- Iterator（迭代器）不是集合，是用于访问集合的方法，可用于迭代 ArrayList 和 HashSet 等集合
- 迭代器 it 的两个基本操作是 next 、hasNext 和 remove。
- it.next() ：返回迭代器的下一个元素，并更新迭代器状态。
- it.hasNext() ：集合中是否还有元素。
- it.remove() ：将迭代器返回的元素删除。

```java
// 集合获取一个迭代器
// 引入 ArrayList 和 Iterator 类
import java.util.ArrayList;
import java.util.Iterator;

public class RunoobTest {
    public static void main(String[] args) {

        // 创建集合
        ArrayList<Integer> sites = new ArrayList<Integer>();
        sites.add(1);
        sites.add(2);
        sites.add(3);
        sites.add(5);

        // 获取迭代器
        Iterator<Integer> it = sites.iterator();

        // 输出集合中的第一个元素
        System.out.println(it.next());
        
         // 输出集合中的所有元素
        while(it.hasNext()) {
            System.out.println(it.next());
        }

          //删除元素
        while(it.hasNext()) {
            Integer i = it.next();
            if(i < 5) {  
                it.remove();  // 删除小于 10 的元素
            }
        }
    }
}
```



# Object 类

- 所有类的父类，**子类可以使用 Object 的所有方法**



# 泛型

## 泛型方法

- 泛型方法声明格式 **<泛型标记符>**，放在方法返回类型之前

- ```java
  public static < E > void printArray( E[] inputArray ){
  }
  ```

- 多个类型参数，参数间用逗号隔开

- **java 中泛型标记符：**
  - **E** - Element (在集合中使用，因为集合中存放的是元素)
  - **T** - Type（Java 类）
  - **K** - Key（键）
  - **V** - Value（值）
  - **N** - Number（数值类型）
  - **？** - 表示不确定的 java 类型

## 泛型类

- 泛型类的声明：在类名后面添加了类型参数声明部分

  - ```java
    public class Box<T> {}
    ```

## 类型通配符

- 使用 **?** 代替具体的类型参数。例如 **List<?>** 在逻辑上是 **List<String>，List<Integer>**  等所有 **List<具体类型实参>** 的父类



# 序列化

- 一个对象可以被表示为一个字节序列，该字节序列包括该对象的数据、有关对象的类型的信息和存储在对象中数据的类型
- 在一个平台上序列化的对象可以在另一个不同的平台上反序列化该对象
- 实现序列化的对象所属的类需要满足：
  - 1.需要实现接口：Serializable
  - 2.当前类提供一个全局常量：serialVersionUID
  - 3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所属性



# 线程

- 线程的生命周期：新建 - 就绪 - 运行 - 阻塞 - 死亡

## 四种创建线程的方法

- 继承于Thread类

  ```markdown
  1. 创建一个继承于Thread类的子类
  2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
  3. 创建Thread类的子类的对象
  4. 通过此对象调用start()
  ```

  

- 实现Runnable接口

  ```markdown
  1. 创建一个实现了Runnable接口的类
  2. 实现类去实现Runnable中的抽象方法：run()
  3. 创建实现类的对象
  4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
  5. 通过Thread类的对象调用start()
  ```

  

- 实现Callable接口

  ```java
  /**
      * 1. 创建一个实现了Runnable接口的类
      * 2. 实现类去实现Runnable中的抽象方法：run()
      * 3. 创建实现类的对象
      * 4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
      * 5. 通过Thread类的对象调用start()
  */
  //1.创建一个实现Callable的实现类
  class NumThread implements Callable{
      //2.实现call方法，将此线程需要执行的操作声明在call()中
      @Override
      public Object call() throws Exception {
          int sum = 0;
          for (int i = 1; i <= 100; i++) {
              if(i % 2 == 0){
                  System.out.println(i);
                  sum += i;
              }
          }
          return sum;
      }
  }
  
  public class ThreadNew {
      public static void main(String[] args) {
          //3.创建Callable接口实现类的对象
          NumThread numThread = new NumThread();
          //4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
          FutureTask futureTask = new FutureTask(numThread);
          //5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
          new Thread(futureTask).start();
  
          try {
              //6.获取Callable中call方法的返回值
              //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
              Object sum = futureTask.get();
              System.out.println("总和为：" + sum);
          } catch (InterruptedException e) {
              e.printStackTrace();
          } catch (ExecutionException e) {
              e.printStackTrace();
          }
      }
  }
  ```

  

- 使用线程池

  ```java
  /**
  好处：
   * 1.提高响应速度（减少了创建新线程的时间）
   * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
   * 3.便于线程管理
   *      corePoolSize：核心池的大小
   *      maximumPoolSize：最大线程数
   *      keepAliveTime：线程没有任务时最多保持多长时间后会终止
   */
  
  class NumberThread implements Runnable{
      @Override
      public void run() {
          for(int i = 0;i <= 100;i++){
              if(i % 2 == 0){
                  System.out.println(Thread.currentThread().getName() + ": " + i);
              }
          }
      }
  }
  
  class NumberThread1 implements Runnable{
      @Override
      public void run() {
          for(int i = 0;i <= 100;i++){
              if(i % 2 != 0){
                  System.out.println(Thread.currentThread().getName() + ": " + i);
              }
          }
      }
  }
  
  public class ThreadPool {
      public static void main(String[] args) {
          //1. 提供指定线程数量的线程池
          ExecutorService service = Executors.newFixedThreadPool(10);
          ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
          //设置线程池的属性
          //        System.out.println(service.getClass());
          //        service1.setCorePoolSize(15);
          //        service1.setKeepAliveTime();
          //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
          service.execute(new NumberThread());//适合适用于Runnable
          service.execute(new NumberThread1());//适合适用于Runnable
          //        service.submit(Callable callable);//适合使用于Callable
          //3.关闭连接池
          service.shutdown();
      }
  }
  ```

## 线程的常用方法

```markdown
1. start():启动当前线程；调用当前线程的run()
2. run(): 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
3. currentThread():静态方法，返回执行当前代码的线程
4. getName():获取当前线程的名字
5. setName():设置当前线程的名字
6. yield():释放当前cpu的执行权
7. join():在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才结束阻塞状态。
8. stop():已过时。当执行此方法时，强制结束当前线程。
9. sleep(long millitime):让当前线程“睡眠”指定的millitime毫秒。在指定的millitime毫秒时间内，当前线程是阻塞状态。
10. isAlive():判断当前线程是否存活

线程的优先级：
1.
MAX_PRIORITY：10
MIN_PRIORITY：1
NORM_PRIORITY：5  -->默认优先级

2.如何获取和设置当前线程的优先级：
getPriority():获取线程的优先级
setPriority(int p):设置线程的优先级

说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲，高优先级的线程高概率的情况下被执行。并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行。
```

## 线程安全问题

```JAVA
//方式一： 同步代码块解决
synchronized(同步监视器){
    //需要被同步的代码
}

//方式二：同步方法
//在synchronized前面加上static, 如果是静态的同步方法, 所有的对象都共享; 相当于 Xxx.class的方式
private static synchronized void show (){ // 锁就是 当前类对象, Xxx.class
	...   
}
//只需要加上synchronized, 因为只创建一个window对象, 所以不需要加static, 相当于this
private synchronized void show() {  // 同步监视器(同步锁) 就是 this
	...
}

/**
解决线程安全问题的方式三：Lock锁
1. 面试题：synchronized 与 Lock的异同？
 *   相同：二者都可以解决线程安全问题
 *   不同：synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器
 *        Lock需要手动的启动同步（lock()），同时结束同步也需要手动的实现（unlock()）
 *
 * 2.优先使用顺序：
 * Lock -> 同步代码块（已经进入了方法体，分配了相应资源）-> 同步方法（在方法体之外）
*/
```

## 线程通信问题

```java
/**线程通信的例子：使用两个线程打印 1-100。线程1, 线程2 交替打印
 *
 * 涉及到的三个方法：
 * wait():一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器。
 * notify():一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级高的那个。
 * notifyAll():一旦执行此方法，就会唤醒所有被wait的线程。
 *
 * 说明：
 * 1.wait()，notify()，notifyAll()三个方法必须使用在同步代码块或同步方法中。
 * 2.wait()，notify()，notifyAll()三个方法的调用者必须是同步代码块或同步方法中的同步监视器。
 *    否则，会出现IllegalMonitorStateException异常
 * 3.wait()，notify()，notifyAll()三个方法是定义在java.lang.Object类中。
 *
 * 面试题：sleep() 和 wait()的异同？
 * 1.相同点：一旦执行方法，都可以使得当前的线程进入阻塞状态。
 * 2.不同点：1）两个方法声明的位置不同：Thread类中声明sleep() , Object类中声明wait()
 *          2）调用的要求不同：sleep()可以在任何需要的场景下调用。 wait()必须使用在同步代码块或同步方法中
 *          3）关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep()不会释放锁，wait()会释放锁。
*/
class Thread1 implements Runnable {

    private int number = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                this.notify(); // 唤醒被wait()的线程
                if (number <= 100) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": number" + number);
                    number++;
                    try {
                        // 调用wait()方法的线程,进入阻塞状态
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
```

