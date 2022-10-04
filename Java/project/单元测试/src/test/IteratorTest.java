package test;

import org.junit.Test;

import java.util.*;

/**
 * 迭代器
 * @author Bosco
 * @date 2021/4/21
 */
public class IteratorTest {

    @Test
    public void arrayListTest(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(4);

        // 使用迭代器(iterator)删除集合里相同元素,并且使用迭代器遍历集合. hasNext:是否有下一个 next:下一个
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next == 4){
                iterator.remove();
            }
        }

        for (Integer i : list){
            System.out.println(i);
        }
    }

    @Test
    public void mapTest(){
        Map<Integer,User> map = new HashMap<>();
        map.put(1,new User("a",123));
        map.put(3,new User("b",123));
        map.put(2,new User("c",123));
        map.put(4,new User("d",123));
        map.put(5,new User("e",123));
        map.put(6,new User("f",123));

        Iterator<Map.Entry<Integer, User>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, User> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
    }


    @Test
    public void setTest(){
        Set<Integer> sets = new HashSet<>();
        sets.add(7);
        sets.add(9);
        sets.add(4);
        sets.add(0);

        Iterator<Integer> iterator = sets.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.println(next);
        }
    }
}
