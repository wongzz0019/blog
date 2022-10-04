package com.hzz;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;


public class ESTest_Index_Delete {
    public static void main(String[] args) throws Exception {

        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                //连接本地服务器
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        //删除索引
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        //发送请求，获取响应
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);

        //操作结果
        System.out.println("操作结果："+ response.isAcknowledged());

        //关闭客户端
        client.close();
    }
}
