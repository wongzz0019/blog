package com.hzz;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


public class ESTest_Client {
    public static void main(String[] args) throws Exception {

        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                //连接本地服务器
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        //关闭客户端
        client.close();
    }
}
