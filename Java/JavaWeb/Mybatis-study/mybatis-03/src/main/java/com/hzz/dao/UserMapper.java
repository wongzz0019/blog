package com.hzz.dao;

import com.hzz.pojo.User;

import java.util.List;

//一个接口的抽象方法
public interface UserMapper {

    //根据ID查询用户
    User getUserById(int id);


}
