package com.hzz.dao;

import com.hzz.pojo.User;
import com.hzz.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

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
        User user = mapper.getUserById(1);//实现接口的类的返回值  UserMapper.xml

        //方法二  旧方法不推荐
        //List<User> userList = sqlSession.selectList("com.hzz.dao.UserMapper.getUserList");
        System.out.println(user);

        //关闭sqlSession
        sqlSession.close();
    }

    //  select * from mybatis.user where id = #{id}
    //类型处理器
        //select id,name,pwd from mybatis.user where id = #{id}
}
