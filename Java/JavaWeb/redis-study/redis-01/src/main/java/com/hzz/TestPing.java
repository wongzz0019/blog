package com.hzz;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        // 1、 new Jedis 对象即可
        Jedis jedis = new Jedis("120.78.166.92",6379);
        // jedis 所有的命令就是我们之前学习的所有指令！所以之前的指令学习很重要！

        jedis.auth("hzz2956195");
        System.out.println(jedis.ping());
    }
}
