package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bosco
 * @date 2021/4/19
 */
public class ListChangeSetTest {

    // 利用Set的特性，去除list列表里的重复数据！！！

    @Test
    public void test1(){
        List<Integer> list = new ArrayList<>();
        list.add(39);
        list.add(39);
        list.add(1);
        list.add(3);
        list.add(6);
        list.add(0);
        list.add(3);

        // new后面是构造器，把构造器HashSet<>(list)里的数据list 放到了 set里面，利用Set的特性去重。
        Set<Integer> set = new HashSet<>(list);

        // 构造器ArrayList里的数据set 放到list里
        list = new ArrayList<>(set);

        for (Integer i : list){
            System.out.println(i);
        }
    }
}
