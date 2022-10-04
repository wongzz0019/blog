package com.hzz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


public class EStest_Doc_Insert_Batch {
    public static void main(String[] args) throws Exception {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        //批量插入数据 - 创建请求体 BulkRequest
        BulkRequest request = new BulkRequest();
        //IndexRequest()
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON,"name","zhangsan","age",20,"sex","男"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON,"name","lishi","age",40,"sex","男"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name","huangwu1","age",21,"sex","女"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON,"name","huangwu2","age",8,"sex","男"));
        request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON,"name","huangwu3","age",20,"sex","女"));
        request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON,"name","huangwu4","age",20,"sex","男"));

        //获取响应
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems().length);

        client.close();
    }
}
