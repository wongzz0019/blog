package com.hzz;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;


public class EStest_Doc_Query {
    public static void main(String[] args) throws Exception {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        //创建请求体
        SearchRequest request = new SearchRequest();
        request.indices("user");

        //1、全量查询 - matchAllQuery()
        //构造查询条件 request.source(new SearchSourceBuilder()...)
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        //2、条件查询
//        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("sex","女")));

//        //3、分页查询
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.matchAllQuery());
//        //当前页 (（当前页-1）*每页显示数据条数 )
//        builder.from(2);
//        //每页多少条数据
//        builder.size(2);
//        request.source(builder);

//        //4 、查询排序
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.sort("age", SortOrder.DESC);
//        request.source(builder);

//        //5、过滤字段查询
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        //排除字段
//        String[] excludes = {"age"};
//        //包含字段
//        String[] includes = {};
//        builder.fetchSource(includes,excludes);
//        request.source(builder);

//        //6、组合查询
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //must 必须 ; mustNot 必须不是 ;
////        boolQueryBuilder.must(QueryBuilders.matchQuery("sex","男"));
////        boolQueryBuilder.must(QueryBuilders.matchQuery("age",20));
////        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex","男"));
//        //should 可以为
//        boolQueryBuilder.should(QueryBuilders.matchQuery("age",20));
//        boolQueryBuilder.should(QueryBuilders.matchQuery("age",8));
//
//        //条件整体
//        builder.query(boolQueryBuilder);
//        //传给请求体
//        request.source(builder);

//        //7、范围查询
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
//        rangeQuery.gte(20);
//        rangeQuery.lte(31);
//        //条件整体
//        builder.query(rangeQuery);
//        //传给请求体
//        request.source(builder);

////        8、模糊查询
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("name","huangw").fuzziness(Fuzziness.AUTO);
//
//        //条件整体
//        builder.query(fuzzyQuery);
//        //传给请求体
//        request.source(builder);

////        9、高亮查询
//        //查询条件，查询name为zhangsan
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(
//                QueryBuilders.termQuery("name","zhangsan"));
//        //构建高亮
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        //设置高亮参数
//        highlightBuilder.preTags("<font color='red'>");
//        highlightBuilder.postTags("</font>");
//        //高亮字段
//        highlightBuilder.field("name");
//        sourceBuilder.highlighter(highlightBuilder);
//        //传给请求体
//        request.source(sourceBuilder);

//        //10、最大值查询
//        //相当于创建查询
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        // 选用最大值查询方式
//        sourceBuilder.aggregation(AggregationBuilders.max("maxAge").field("age"));
//        //传给请求体
//        request.source(sourceBuilder);


        //11、分组查询
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // aggregation聚合查询 -- AggregationBuilders.terms分组查询
        sourceBuilder.aggregation(AggregationBuilders.terms("age_groupBy").field("age"));
        //传给请求体
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits(); //hits包含了所有数据
        System.out.println(hits.getTotalHits()); //查询条数
        System.out.println(response.getTook()); //查询用时

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        client.close();
    }
}
