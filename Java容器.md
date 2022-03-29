# Java容器



## Java 容器框架

![image-20220329181802528](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220329181802528.png)

![image-20220329181840595](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220329181840595.png)



### 分类

主要分为 `Collection` 和 `Map` 两种。

`Collection` ： `List`、`Set` 、`Queue`

`List`	：线性结构、可变长度、有序、可重复

``Set	``	：非线性、不可重复

``Queue``	：代表一种队列集合实现

``Map``	：键值对映射关系。key唯一、不重复；value可重复、可null



### Collection 和 Collections 区别

collection 是set、queue、list的父接口，集合的最顶层

collections是工具类



### ArrayList(集合)和Array(数组)的区别

**区别**

Array：大小固定不变。可存基本类型的值和对象，方法少

ArrayList ：大小可动态拓展。只能存对象，存基本数据类型要转成包装类，方法多



**转换**

数组转集合 ：Arrays.asList（数组）  ==注意：int要转为包装类才行==

```java
//方法一：int转包装类
Integer[] arr = {1,3,4,6,6};
Arrays.asList(arr);

//方法二：使用Arrays.stream方法
int[] arr1 = { 1, 2, 3 };
List list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
```

集合转数组 ：集合.toArray（）

```java
List list = new ArrayList();
list.add("a");
list.add("b");
 
list.toArray();
System.out.println(list.toString());
```



## List

线性结构、可变长度、有序、可重复

包含有 ：``ArrayList``、``LinkedList``、``Vector``、``Stack``

![image-20220329142853309](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220329142853309.png)

ArrayList 包含了两个重要的元素：`elementData`(存放数据的数组) 和 `size`(数组的实际大小)

#### ArrayList 和 LinkedList

- `ArrayList` 基于动态数组实现，容量限制，当元素数超过最大容量时，会自动扩容1.5倍；

  ![image-20220329152130774](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220329152130774.png)

- `LinkedList` 基于双向链表实现，不存在容量限制。

  ![image-20220329175217421](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220329175217421.png)

- 随机访问 ArrayList 比 LinkedList 快

- 增删操作 LinkedList 比 ArrayList 快

- 两者都不是线程安全



#### ArrayList 和 Vector

- Vector 线程安全、方法同步、扩容默认原来的2倍、能指定扩容大小
- ArrayList 线程不安全、方法不同步，但性能比Vector好，使用于频繁的查找工作，扩容1.5倍



### Vector 和 Stack

- 都是线程安全，方法使用synchronized同步
- Stack实际上继承Vector



## Set

#### HashSet 、TreeSet、LinkedHashSet 三者区别

1. HashSet
   - 无序
   - 集合元素能存放一个null
   - 数据结构是哈希表（基于 `HashMap` 实现）
2. TreeSet
   - 默认顺序排序的有序集合（小到大）
   - 数据结构是链表+哈希表
3. LinkedHashSet 
   - 按元素添加顺序排序的有序集合
   - 数据结构是红黑树

#### 三者共同点

- 数据都不可重复
- 非线程安全



### HashSet 的实现原理

1. HashSet底层由HashMap实现
2. HashSet的值存放于HashMap的key上
3. HashMap的value统一为PRESENT

### List怎样去重？

```java
//方法一：使用LinkedHashSet去重，保持添加到其中的数据的顺序
public static void main(String[] args) {
    int[] List =[1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8];
    LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(List);
    ArrayList<Integer> arrayList = new ArrayList<>(hashSet);
    System.out.println(arrayList);
}

//方法二：java8新特性 stream
public static void main(String[] args){
    int[] List =[1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8];
    List<Integer> listWithoutDuplicates = List.stream().distinct().collect(Collectors.toList());
    System.out.println(listWithoutDuplicates);
}

```



## Map

### HashMap

- 键可空，但只能有一个null；值可空，可有多个
- `HashMap` 非线程安全
- 数据结构：`HashMap` 使用一个 `Node<K,V>[]` 类型的数组 `table` 来储存元素

### TreeMap

- 有序的，按key的大小进行排序（从小到大）或自定义排序
- 非线性安全
- 基于红黑树实现

### LinkedHashMap

- Key 和 Value 都允许 null
- key唯一、value可重复
- 按元素插入顺序存储
- 非线性安全
- 数据结构：`LinkedHashMap` 通过维护一对 `LinkedHashMap.Entry<K,V>` 类型的头尾指针，以双链表形式，保存所有数据

### HashMap 和 Hashtable 区别

- HashMap 键值可为null，Hashtable不允许
- Hashtable 线程安全，HashMap 非线程安全
- HashMap是不同步的、效率高，HashTable是同步的、效率低
- 需要线程安全，可使用ConcurrentHashMap，效率比Hashtable高

### 如何决定使用 HashMap 还是 TreeMap

- 有排序要求，需要返回有序情况时使用TreeMap

### HashMap 的实现原理

........