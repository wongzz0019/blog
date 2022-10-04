package com.hzz.mapper;

import com.hzz.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Mapper 这个注解表示了这是一个 mybatis 的mapper 类；Dao
 * @Repository 主动标识当前类要交给spring容器管理 ,只能在Dao层使用
 */
@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
