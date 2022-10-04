package com.hzz.dao;

import com.hzz.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//一个接口的抽象方法
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    //方法存在多个基本类型参数时，所有的参数前面必须加上@Param("id")注解
    //@Param("id") 必须对应 @Select(#{id})！！！
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);

    @Insert("insert into user(id,name,pwd) values (#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update user set name=#{name},pwd=#{password} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{uid}")
    int deleteUser(@Param("uid") int id);

}
