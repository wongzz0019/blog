package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Bosco
 * @date 2021/4/19
 */
public class SetTest {

    @Test
    public void test1(){

        // TreeSet自动排序
        // Set<Integer> set = new TreeSet<>();

        // Set 无序，不能存重复值
        Set<Integer> set = new HashSet<>();

        set.add(1);
        set.add(5);
        set.add(5);
        set.add(3);
        set.add(4);
        set.add(4);

        for (Integer integer : set){
            System.out.println(integer);
        }



    }
}
