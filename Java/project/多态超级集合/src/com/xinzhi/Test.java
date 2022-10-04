package com.xinzhi;

import javax.jws.soap.SOAPBinding;

/**
 * @author Bosco
 * @date 2021/4/7
 */
public class Test {
    public static void main(String[] args) {
        Super<User> s = new SuperLink<>();
        s.add(new User("zhangsan","123"));
        s.add(new User("LISI","1234"));
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i));
            s.get(i).say();
        }
    }
}
