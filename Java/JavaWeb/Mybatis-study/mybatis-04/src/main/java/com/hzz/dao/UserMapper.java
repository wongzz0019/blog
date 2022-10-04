package com.hzz.dao;

import com.hzz.pojo.User;

import java.util.List;
import java.util.Map;

//一个接口的抽象方法
public interface UserMapper {

    //根据ID查询用户
    User getUserById(int id);

    //分页
    List<User> getUserByLimit(Map<String,Integer> map);

    //分页2
    List<User> getUserByRowBounds();


}
