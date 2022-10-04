import com.hzz.pojo.Student;
import com.hzz.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bosco
 * @date 2021/11/29
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student)context.getBean("student");
        System.out.println(student.toString());
            /*
     Student{
     name='小明'
     address=Address{address='广东'}
     books=[红楼梦, 西游记, 三国演义, 水浒传]
     hobbys=[唱歌, 敲代码, 看定影, 拍照]
     card={身份证=123456798, 银行卡=987654321}
     games=[LOL, CS, DOTA]
     wife='null'
     info={password=123456, url=哈哈, driver=2213, username=root}
     }
    */
    }

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = context.getBean("user2", User.class); //类名.class 可以直接转为对应类
        User user2 = context.getBean("user2", User.class);

        System.out.println(user==user2);
    }
}
