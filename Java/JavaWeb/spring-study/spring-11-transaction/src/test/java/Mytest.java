import com.hzz.mapper.UserMapper;
import com.hzz.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bosco
 * @date 2021/12/6
 */
public class Mytest {

    @Test
    public void selectUserTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        userMapper.addUser(new User(7,"waa","1521"));
        for (User user : userMapper.selectUser()) {
            System.out.println(user);
        }
    }

}
