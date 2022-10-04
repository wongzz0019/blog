import com.hzz.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bosco
 * @date 2021/11/28
 */
public class MyTest {
    public static void main(String[] args) {
        //获取ApplicationContext；拿到Spring的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //容器在手，天下我有，需要什么，就直接get什么！
        UserServiceImpl mySqlImpl = (UserServiceImpl) context.getBean("userServiceImpl");

        mySqlImpl.getUser();
    }
}
