package com.hzz.dao;

import com.hzz.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/11/25
 */
public interface UserMapper {

    //根据id查询用户
    User queryUserById(@Param("id") int id);
}
