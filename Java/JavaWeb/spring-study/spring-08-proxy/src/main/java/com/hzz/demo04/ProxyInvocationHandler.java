package com.hzz.demo04;

import com.hzz.demo03.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//等会用这个类，自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到的代理类  target.getClass().getInterfaces()确定生成代理类的类型！
    public Object getProxy() {
        Object obj = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
        return obj;
    }

    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method(方法)   .getName()获取方法名
        log(method.getName());
        //动态代理的本质，就是使用反射机制实现！
        Object result = method.invoke(target, args); //method.invoke(target,args) invoke(调用)。执行target接口的方法。args参数
        return result;
    }

    //增加日志功能
    public void log(String msg){
        System.out.println("使用了"+msg+"方法");
    }

}