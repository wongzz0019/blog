import com.hzz.pojo.User;
import com.hzz.pojo.UserT;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bosco
 * @date 2021/11/28
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) context.getBean("user");
        UserT userT = (UserT) context.getBean("userT");
        //user.show();
        userT.show();
    }
}
