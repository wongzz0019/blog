package com.hzz;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","word");
        jsonObject.put("name","wong");
        String result = jsonObject.toJSONString();

//        jedis.watch(result); //监视
        //开启事务
        Transaction multi = jedis.multi();

        try {
            //命令入队
            multi.set("user3",result);
            multi.set("user4",result);
            // 代码抛出异常事务，执行失败！
            int i = 1/0;
            //执行事务
            multi.exec();
        } catch (Exception e){
            //如果有异常就 放弃事务
            multi.discard(); //放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user3"));
            System.out.println(jedis.get("user4"));
            jedis.close();  //关闭连接
        }
    }
}
