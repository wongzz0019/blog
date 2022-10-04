package com.hzz;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;


public class ESTest_Index_Search {
    public static void main(String[] args) throws Exception {

        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                //连接本地服务器
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        //查询索引 - 请求对象
        GetIndexRequest request = new GetIndexRequest("user");
        //发送请求 - 获取响应
        GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);

        //响应状态
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());

        //关闭客户端
        client.close();
    }
}
