package com.hzz.dao;

import com.hzz.pojo.User;

import java.util.List;
import java.util.Map;

//一个接口的抽象方法
public interface UserMapper {
    //查询全部用户
    List<User> getUserList();

    //根据ID查询用户
    User getUserById(int id);

    //插入一个用户  这int是返回受影响的行数
    int addUser(User user);

    //修改一个用户
    int updateUser(User user);

    //删除一个用户
    int deleteUser(int id);

}
