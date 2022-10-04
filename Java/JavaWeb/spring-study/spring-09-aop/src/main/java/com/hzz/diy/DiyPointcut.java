package com.hzz.diy;

/**
 * @author Bosco
 * @date 2021/12/3
 */
public class DiyPointcut {
    public void before(){
        System.out.println("====方法执行前====");
    }

    public void after(){
        System.out.println("====方法执行后====");
    }
}
