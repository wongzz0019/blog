# Java基础

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

