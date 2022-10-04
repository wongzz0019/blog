package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/4/7
 */

public class SuperArray<T> implements Super<T>{
    private Object[] array;
    private int currentIndex = -1;

    // 构造方法。 length是指数组长度
    public SuperArray(int size) {
        array = new Object[size];
    }

    // this指的是SuperArray,()指的是上面的构造方法
    public SuperArray() {
        this(10);
    }

    // 数组排序
//    public void sort(){
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = 0; j < array.length - 1 -i ; j++) {
//                if (array[j] > array[j+1]){
//                    int temp = array[j];
//                    array[j] = array[j+1];
//                    array[j+1] = temp;
//                }
//            }
//        }
//    }

    // 往数组添加数据。
    @Override
    public void add(T data){
        currentIndex++;
        // 游标加完后越界，就数组扩容
        if (currentIndex >= array.length){
            Object[] temp = new Object[array.length+1];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
        array[currentIndex] = data;
    }

    // 这里的return作用：结束当前的方法，因为返回值为void
    @Override
    public void delete(int index){
        if (index < 0 || index > currentIndex){
            System.out.println("您输入的下标不合法！");
            return;
        }
        for (int i = index; i < currentIndex; i++) {
            array[i] =  array[i+1];
        }
        currentIndex--;
    }

    // 更新
    @Override
    public void update(int index,T newDate){
        array[index] = newDate;
    }

    @Override
    public void print(){
        System.out.println("-----------结果----------");
        for (int i = 0; i <= currentIndex; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }



    @Override
    public T get(int index){
        return (T)array[index];
    }
}

