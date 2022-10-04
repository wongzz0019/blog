package com.hzz.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Bosco
 * @date 2021/12/3
 */
public class BeforeLog implements MethodBeforeAdvice {

    //method:要执行的目标对象的方法
    //args:参数
    //target:目标对象

    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"的"+method.getName()+"方法被执行了");
    }
}
