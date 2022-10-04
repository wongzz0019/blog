import com.hzz.dao.UserMapper;
import com.hzz.pojo.User;
import com.hzz.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import org.junit.Test;


/**
 * @author Bosco
 * @date 2021/11/25
 */
public class MyTest {

    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.queryUserById(1);
        System.out.println(user);

        //sqlSession.clearCache();//手动清理缓存

        System.out.println("================");
        User user1 = mapper.queryUserById(2);
        System.out.println(user1);


        sqlSession.close();
    }
}
