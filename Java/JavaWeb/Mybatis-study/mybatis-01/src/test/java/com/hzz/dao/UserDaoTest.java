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

    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1);

        System.out.println(userById);
        sqlSession.close();
    }

    //增删改需要提交事务
    @Test //插入
    public void addUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int addUser = mapper.addUser(new User(4, "小花", "1103"));
        if (addUser > 0) {
            System.out.println("插入成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test //删除
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(4);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test //更新
    public void updateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(new User(4, "小小", "555"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test //万能map 插入
    public void adduser2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //new一个map对象，并且.put赋值，
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userid",5);
        map.put("username","haha");
        //执行sql
        mapper.addUser2(map);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test //map 修改
    public void updateUser2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid",4);
        map.put("name","光头强");
        mapper.updateUser2(map);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test //万能map 查询
    public void getUserById2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userid",2);
        map.put("username","小洪");
        User userById2 = mapper.getUserById2(map);
        System.out.println(userById2);
        sqlSession.close();
    }

    @Test //模糊查询
    public void getUserLike(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userLike = mapper.getUserLike("%李%");
        //第二种：List<User> userLike = mapper.getUserLike("李");
        for (User user : userLike) {
            System.out.println(user);
        }

        sqlSession.close();
    }
}
