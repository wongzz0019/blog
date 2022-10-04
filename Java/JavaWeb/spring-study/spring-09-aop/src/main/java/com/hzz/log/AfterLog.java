package com.hzz.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author Bosco
 * @date 2021/12/3
 */
public class AfterLog implements AfterReturningAdvice {

    //returnValue：返回值
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"的"+method.getName()+"被执行了!"+"返回结果为："+returnValue);
    }
}
