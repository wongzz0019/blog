package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Bosco
 * @date 2021/4/19
 */
public class ListTest {

    @Test
    public void arrayListTest(){
        // List 有序，可以存重复值
        // LinkedList 本质上是链表(双向链表) 。接口为List 。方法和ArrayList一样
        List<User> linked = new LinkedList<>();

        // ArrayList 本质上是数组 . 接口为List
        List<User> list = new ArrayList<>();
        List<User> list2 = new ArrayList<>();

        list2.add(new User("小chen",1));
        list2.add(new User("小zhu",3));

        list.add(new User("小红",222));
        list.add(new User("小huang",123));
        list.add(new User("小chen",1));
        list.add(new User("小zhu",3));
        list.add(new User("小bai",5));
        // 按下标添加元素
        list.add(2,new User("alibab",555));

        // 按下标删除
        list.remove(4);
        // 按对象删除 .要重写equals方法
        list.remove(new User("小红",2222));
        // 按列表集合删除
        list.removeAll(list2);

        // contains 判断是否含有. 也得重写equals方法
        System.out.println(list.contains(new User("alibab",555)));
        // 修改
        list.set(1,new User("FAFA",520));


        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 增强for循环
        for (User user : list) {
            System.out.println(user.getUsername());
        }


    }
}
