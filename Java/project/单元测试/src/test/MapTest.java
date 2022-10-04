package test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Bosco
 * @date 2021/4/19
 */
public class MapTest {

    @Test
    public void test1(){
        // TreeMap 自动帮你排序。使用方法和HashMap一样
        // Map<String,User> map = new TreeMap<>();

        // Hash(哈希是不能排序的，hashmap、hashset)
        Map<String,User> map = new HashMap<>();
        map.put("banzhang",new User("黄宗钊",null));
        map.put("xuewei",new User("大黄",null));
        map.put("laodong",new User("小众",null));
        map.put("shenghuo",new User("哈哈",null));

        System.out.println(map.get("banzhang"));
        System.out.println("-----------------------");

        // map的循环，得用增强for循环。要记住！！！
        for(Map.Entry entry :map.entrySet() ){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        // 使用map自带的 KeySet() 方法来遍历hashmap的key
        Set<String> keys = map.keySet();
        for (String key : keys){
            System.out.println(key);
        }
    }
}
