import com.hzz.config.HzConfig;
import com.hzz.pojo.User;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Bosco
 * @date 2021/11/30
 */
public class MyTest {
    public static void main(String[] args) {
        //如果完全使用了配置类方式去做，我们就只能通过 AnnotationConfig 上下文来获取容器，通过配置类的class对象加载！
        ApplicationContext context = new AnnotationConfigApplicationContext(HzConfig.class);
        User user = (User) context.getBean("getUser"); //配置类的方法名
        System.out.println( user.getName());

    }

}
