package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/28
 */
public class Duck extends Animal{

    @Override
    protected void eat(){
        System.out.println("我是鸭子duck,我吃青菜！");
    }

    @Override
    protected void walk(){
        System.out.println("我是duck,是两脚行走的");
    }
}
