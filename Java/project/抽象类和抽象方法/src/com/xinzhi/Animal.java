package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/28
 * abstract标明抽象类
 */
public abstract class Animal {

    /**
     * abstract标明是抽象方法，不需要大括号，
     * 父类为抽象类，子类必须要重写父类里的抽象方法
     */
    protected abstract void eat();

    protected void walk(){
        System.out.println("行走");
    }
}
