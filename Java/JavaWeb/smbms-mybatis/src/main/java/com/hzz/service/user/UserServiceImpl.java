package com.hzz.service.user;

import com.hzz.mapper.user.UserMapper;
import com.hzz.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/16
 */

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public User login(String userCode) {
        return userMapper.getLoginUser(userCode);
    }

    public int add(User user) {
        return userMapper.add(user);
    }

    public List<User> getUserList(String userName, Integer userRole, Integer currentPageNo, Integer pageSize) {
        return userMapper.getUserList(userName, userRole, currentPageNo, pageSize);
    }

    public int deleteUserById(Integer delId) {
        return userMapper.deleteUserById(delId);
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public int modify(User user) {
        return userMapper.modify(user);
    }

    public int updatePwd(Integer id, String pwd) {
        return userMapper.updatePwd(id, pwd);
    }
}
