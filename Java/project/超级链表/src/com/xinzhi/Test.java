package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/29
 */
public class Test {
    public static void main(String[] args) {
        SuperLink superLink = new SuperLink();
        superLink.add(2);
        superLink.add(3);
        superLink.add(4);
        superLink.add(5);
        superLink.add(6);
        System.out.println(superLink.get(4));
    }
}
