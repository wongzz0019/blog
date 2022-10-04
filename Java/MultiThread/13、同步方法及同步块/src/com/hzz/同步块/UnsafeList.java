package com.hzz.同步块;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全的集合 修改为 安全的集合
 * 用同步块 synchronized(对象){}
 * @author Bosco
 * @date 2021/8/31
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        //向集合添加数据
        //使用同步块，令集合安全
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出集合的长度，添加的数量和输出的长度一致 即安全
        System.out.println(list.size());
    }
}
