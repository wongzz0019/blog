package com.hzz.demo03;


public class Client {
    public static void main(String[] args) {
        //真实角色
        Host host = new Host();

        //代理角色：现在没有!
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理我们要调用的接口对象
        pih.setRent(host);//设置需要代理的对象
        //动态代理代理的是接口 Rent
        Rent proxy = (Rent) pih.getProxy();//这里的proxy是动态生成的，代理类
        proxy.rent();

    }
}
