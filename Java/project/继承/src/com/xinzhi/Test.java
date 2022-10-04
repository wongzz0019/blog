package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/22
 */
public class Test {
    public static void main(String[] args) {
        Son son = new Son();

        Father father1 = new Father("抽烟");
        Father father2 = new Father("抽烟");
        System.out.println(father1.equals(father2));

        // String里面已经重写了equals方法
        String str1 = new String("a");
        String str2 = new String("a");
        System.out.println(str1.equals(str2));
    }
}
