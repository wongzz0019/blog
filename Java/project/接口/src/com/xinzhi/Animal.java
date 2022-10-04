package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/28
 * 接口必须都是抽象方法
 * interface. 一个Animal接口
 * 定义了动物有哪些方法
 */
public interface Animal {

    /**
     * 规定了动物吃的方法
     * 接口的方法不用写权限修饰符和大括号
     * 默认是public
     */
    void eat();

    /**
     * 规定了动物的行走方法
     * 接口的方法不用写权限修饰符和大括号
     * 默认是public
     */
    void breathe();
}
