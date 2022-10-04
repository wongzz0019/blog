package com.hzz.service.user;

import com.hzz.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/16
 */
public interface UserService {
    //通过userCode获取User
    public User login(String userCode);

    //增加用户信息
    public int add(User user);

    //通过条件查询-userList
    public List<User> getUserList(String userName,Integer userRole,Integer currentPageNo,Integer pageSize);

    //通过userId删除user
    public int deleteUserById(Integer delId);

    //通过userId获取user
    public User getUserById(Integer id);

    //修改用户信息
    public int modify(User user);

    //修改当前用户密码
    public int updatePwd(Integer id,String pwd);
}
