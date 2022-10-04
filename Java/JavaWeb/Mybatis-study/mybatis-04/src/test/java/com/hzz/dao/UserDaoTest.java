package com.hzz.dao;

import com.hzz.pojo.User;
import com.hzz.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserDaoTest {

    static Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void getUserByIdTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        logger.info("测试，进入了test方法成功");

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);//获取接口的class对象
        User user = mapper.getUserById(1);//实现接口的类的返回值  UserMapper.xml
        System.out.println(user);
        sqlSession.close();
    }

    @Test   //分页1测试
    public void getUserByLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex",2);
        map.put("pageSize",2);

        List<User> userByLimit = mapper.getUserByLimit(map);
        for (User user : userByLimit) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test   //分页2测试
    public void getUserByRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //RowBounds实现
        RowBounds rowBounds = new RowBounds(1, 2); //从1开始，显示2个

        //通过java代码层面实现分页

        List<User> userList = sqlSession.selectList("com.hzz.dao.UserMapper.getUserByRowBounds",null,rowBounds);

        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }
}
