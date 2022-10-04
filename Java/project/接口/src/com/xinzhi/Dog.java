package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/28
 * implements 实现接口 ,和继承父类的写法一样.
 */
public class Dog implements Animal {
    @Override
    public void eat(){
        System.out.println("我是dog,我吃屎！");
    }

    @Override
    public void breathe() {
        System.out.println("我是Dog,我用鼻子呼吸！");
    }
}
