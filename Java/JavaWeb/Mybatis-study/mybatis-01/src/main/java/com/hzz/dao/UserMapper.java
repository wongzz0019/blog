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
    //万能map查询单个用户
    User getUserById2(Map<String,Object> map);
    //模糊查询用户
    List<User> getUserLike(String value);

    //插入一个用户  这int是返回受影响的行数
    int addUser(User user);
    //万能Map 插入用户
    int addUser2(Map<String,Object> map);

    //修改一个用户
    int updateUser(User user);
    //万能map修改用户
    int updateUser2(Map<String,Object> map);

    //删除一个用户
    int deleteUser(int id);

}
