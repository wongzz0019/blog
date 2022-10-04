package com.hzz.Demo02;

/**
 * @author Bosco
 * @date 2021/11/30
 */
public class Clien {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        Proxy proxy = new Proxy();
        proxy.setUserService(userService);

        proxy.add();

    }
}
