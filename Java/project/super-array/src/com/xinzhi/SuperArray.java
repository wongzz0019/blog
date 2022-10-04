package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/16
 */
public class SuperArray {
    private int[] array;
    private int currentIndex = -1;

    // 构造方法。 length是指数组长度
    public SuperArray(int length) {
        array = new int[length];
    }

    // this指的是SuperArray,()指的是上面的构造方法
    public SuperArray() {
        this(10);
    }

    // 数组排序
    public void sort(){
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 -i ; j++) {
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    // 往数组添加数据。
    public void add(int data){
        currentIndex++;
        // 游标加完后越界，就数组扩容
        if (currentIndex >= array.length){
            int[] temp = new int[array.length+1];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
        array[currentIndex] = data;
    }

    // 这里的return作用：结束当前的方法，因为返回值为void
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
    public void update(int index,Integer newDate){
        array[index] = newDate;
    }

    public void print(){
        System.out.println("-----------结果----------");
        for (int i = 0; i <= currentIndex; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // 把数组转为字符串
    public String toSrt(){
        String res = "";
        for (int i = 0; i <= currentIndex ; i++) {
            if (i == currentIndex){
                res += array[i];
            } else {
                res = res + array[i] + ",";
            }
        }
        return res;
    }

    // 获取数组
    public int[] get(){
        return array;
    }

    public  int get(int index){
        return array[index];
    }
}
