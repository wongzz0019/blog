package com.hzz.redis03subpub.subpub;

import redis.clients.jedis.Jedis;

//发送者
public class Publisher {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("120.78.166.92",6379);
        jedis.auth("hzz2956195");
        jedis.publish("Huang","hello,i am Publisher");
        jedis.publish("Huang","www");
    }
}
