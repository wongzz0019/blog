import com.hzz.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bosco
 * @date 2021/11/28
 */
public class MyTest {
    public static void main(String[] args) {
        //获取Spring的上下文对象环境
        //new ClassPathXmlApplicationContext 用xml加载必须写这句话
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象现在都在spring中管理了，我么要使用 直接去里面取出来就可以
        Hello hello = (Hello)context.getBean("hello");
        System.out.println(hello.toString());
    }
}
