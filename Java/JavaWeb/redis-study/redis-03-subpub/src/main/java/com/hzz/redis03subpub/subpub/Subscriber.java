package com.hzz.redis03subpub.subpub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

//订阅者
public class Subscriber extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("订阅者：订阅频道:" + channel + "，收到消息:" + message);
    }

    public static void main(String[] args) {
        System.out.println("启动订阅者");
        //创建Jedis
        Jedis jedis = new Jedis("120.78.166.92", 6379);
        jedis.auth("hzz2956195");
        //创建订阅者
        Subscriber subscriber = new Subscriber();
        //订阅信息
        jedis.subscribe(subscriber,"Huang");
    }
}