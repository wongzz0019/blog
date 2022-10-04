package com.hzz.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//等会用这个类，自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    //生成得到的代理类
    public Object getProxy(){
        Object obj = Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
        return obj;
    }

    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质，就是使用反射机制实现！
        seeHost();
        Object result = method.invoke(rent, args); //method.invoke(rent,args)执行什么方法?执行rent接口的方法。args参数
        money();
        return result;
    }

    public void seeHost(){
        System.out.println("看房");
    }

    public void money(){
        System.out.println("交钱");
    }
}
