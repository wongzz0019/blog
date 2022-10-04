import com.hzz.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bosco
 * @date 2021/12/3
 */
public class Test {
    public static void main(String[] args) {
        //谨记“要导入applicationContext.xml文件”。获取Spring的上下文环境
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //注意：动态代理代理的是“接口”！！!
        UserService userServiceImplProxy = (UserService) context.getBean("userServiceImpl");
        userServiceImplProxy.add();
    }
}
