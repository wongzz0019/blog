package com.hzz.mapper;

import com.hzz.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/5
 */
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {


    public List<User> selectUser() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
