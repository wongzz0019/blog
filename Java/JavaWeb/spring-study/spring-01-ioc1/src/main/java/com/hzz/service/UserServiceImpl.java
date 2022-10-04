package com.hzz.service;

import com.hzz.mapper.UserDao;

/**
 * @author Bosco
 * @date 2021/11/28
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    //利用set进行动态实现值的注入！
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }


}
