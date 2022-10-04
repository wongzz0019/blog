package com.hzz.mapper;

import com.hzz.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Bosco
 * @date 2022/1/4
 */
@Repository
@Mapper
public interface UserMapper {
    User queryUserByName(String name);
}
