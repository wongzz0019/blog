package com.hzz.demo04;

import com.hzz.Demo02.UserService;
import com.hzz.Demo02.UserServiceImpl;

/**
 * @author Bosco
 * @date 2021/12/3
 */
public class Client {
    public static void main(String[] args) {
        //真实角色
        UserServiceImpl userService = new UserServiceImpl();
        //代理角色，不存在
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //设置要代理的对象
        pih.setTarget(userService);
        //动态生成代理类。动态代理代理的是接口 UserService
        //注意：这里一定要强转接口类。因为实现类和代理类没有任何关系，唯一就是他俩都实现了同一接口
        UserService proxy = (UserService) pih.getProxy();
        proxy.delete();
    }
}
