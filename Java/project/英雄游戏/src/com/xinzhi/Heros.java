package com.xinzhi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bosco
 * @date 2021/4/27
 */
public class Heros {
    public static final List<Character> HEROS = new ArrayList<>();

    static {
        HEROS.add(new Character("后裔",1000,600,300));
        HEROS.add(new Character("钟馗",1400,400,500));
        HEROS.add(new Character("李元芳",1100,600,350));
        HEROS.add(new Character("牛魔",1800,100,400));
    }
}
