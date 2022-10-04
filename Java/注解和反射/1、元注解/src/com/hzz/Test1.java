package com.hzz;

import java.util.ArrayList;

/**
 *什么是注解
 */
public class Test1 extends Object{
    //@Override 重写的注解
    @Override
    public String toString(){
        return super.toString();
    }

    //@Deprecated 不推荐程序员使用，但是可以使用，或者存在更好的方式
    @Deprecated
    public static void test(){
        System.out.println("Deprecated");
    }

    //@SuppressWarnings("参数")  抑制警告信息
    @SuppressWarnings("all")
    public void test1(){
        ArrayList list = new ArrayList();
    }

    public static void main(String[] args) {
        test();

    }
}
