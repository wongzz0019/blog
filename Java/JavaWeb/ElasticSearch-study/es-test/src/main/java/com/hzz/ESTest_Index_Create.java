package com.hzz;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexAction;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


public class ESTest_Index_Create {
    public static void main(String[] args) throws Exception {

        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                //连接本地服务器
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        //创建索引 - 请求对象
        CreateIndexRequest request = new CreateIndexRequest("user");
        //发送请求 - 获得响应  第二个参数为默认请求方式
        CreateIndexResponse createIndexResponse = client.indices().create(request,RequestOptions.DEFAULT);

        //响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作:"+acknowledged);

        //关闭客户端
        client.close();
    }
}
