package com.hzz.dao;

import com.hzz.pojo.User;
import com.hzz.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bosco
 * @date 2021/11/21
 */
public class UserDaoTest {

    @Test
    public void test(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一：执行SQL
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);//获取接口的class对象
        List<User> userList = mapper.getUserList();//实现接口的类的返回值  UserMapper.xml

        //方法二  旧方法不推荐
        //List<User> userList = sqlSession.selectList("com.hzz.dao.UserMapper.getUserList");

        for (User user : userList) {
            System.out.println(user);
        }

        //关闭sqlSession
        sqlSession.close();
    }

}
