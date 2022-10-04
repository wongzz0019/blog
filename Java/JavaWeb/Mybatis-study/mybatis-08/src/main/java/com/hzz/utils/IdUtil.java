package com.hzz.utils;

import org.junit.Test;

import java.util.UUID;

/**
 * @author Bosco
 * @date 2021/11/24
 */
public class IdUtil {
    public static String getId(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

    @Test
    public void test(){
        System.out.println(IdUtil.getId());
        System.out.println(IdUtil.getId());
        System.out.println(IdUtil.getId());
    }
}
