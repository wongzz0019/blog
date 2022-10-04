package com.hzz.mapper;

import com.hzz.pojo.User;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/6
 */
public interface UserMapper {

    public List<User> selectUser();

    public int addUser(User user);

    public int deleteUser(@Param("id") Integer id);
}
