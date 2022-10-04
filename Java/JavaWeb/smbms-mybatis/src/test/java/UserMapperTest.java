//import com.hzz.mapper.user.UserMapper;
//import com.hzz.pojo.User;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
//import java.util.List;
//
///**
// * @author Bosco
// * @date 2021/12/1
// */
//public class UserMapperTest {
//    @Test//通过userCode获取User
//    public void getLoginUserTest(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User user = mapper.getLoginUser("admin");
//        System.out.println(user.toString());
//        sqlSession.close();
//    }
//
//    @Test//通过条件查询-userList
//    public void getUserListTest() {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = mapper.getUserList("孙", 3, 0, 4);
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
//        sqlSession.close();
//    }
//
//    @Test//增加用户信息
//    public void addTest() {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User user = new User();
//        user.setId(16);
//        user.setUserCode("chenzhen");
//        user.setUserName("陈真");
//        user.setUserPassword("0000000");
//        user.setUserRole(3);
//        mapper.add(user);
//        sqlSession.commit();//提交事务
//        sqlSession.close();
//    }
//
//    @Test//通过userId删除user
//    public void deleteUserByIdTest() {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        mapper.deleteUserById(17);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test//通过userId获取user
//    public void getUserByIdTest() {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User userById = mapper.getUserById(15);
//        System.out.println(userById.toString());
//        sqlSession.close();
//    }
//
//    @Test//修改用户信息
//    public void modifyTest() {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User user = new User();
//        user.setId(16);
//        user.setUserPassword("1122334");
//        user.setGender(2);
//        mapper.modify(user);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test//修改当前用户密码
//    public void updatePwdTest(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        mapper.updatePwd(16,"1472583");
//        sqlSession.commit();
//        sqlSession.close();
//
//    }
//
//
//}
