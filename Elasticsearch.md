# ElasticSearch

## 入门-HTTP-索引-创建

创建索引就等同于创建数据库。

在 Postman 中，向 ES 服务器发 **PUT 请求** ： http://127.0.0.1:9200/shopping

![image-20220416182019472](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416182019472.png)

请求后，服务器返回响应：

```json
{
    "acknowledged": true,//响应结果
    "shards_acknowledged": true,//分片结果
    "index": "shopping"//索引名称
}
```

## 入门-HTTP-索引-查询 & 删除

### 查看所有索引

向 ES 服务器发 **GET 请求** ：http://127.0.0.1:9200/_cat/indices?v 

"_cat"：表示查看。	"indices"：表示索引

服务器响应结果：

```json
health status index    uuid                   pri rep docs.count docs.deleted store.size pri.store.size
yellow open   shopping J0WlEhh4R7aDrfIc3AkwWQ   1   1          0            0       208b           208b
```

### 查看单个索引

向 ES 服务器发 **GET 请求** ： http://127.0.0.1:9200/shopping

返回结果

```json
{
    "shopping": {//索引名
        "aliases": {},//别名
        "mappings": {},//映射
        "settings": {//设置
            "index": {//设置 - 索引
                "creation_date": "1617861426847",//设置 - 索引 - 创建时间
                "number_of_shards": "1",//设置 - 索引 - 主分片数量
                "number_of_replicas": "1",//设置 - 索引 - 主分片数量
                "uuid": "J0WlEhh4R7aDrfIc3AkwWQ",//设置 - 索引 - 主分片数量
                "version": {//设置 - 索引 - 主分片数量
                    "created": "7080099"
                },
                "provided_name": "shopping"//设置 - 索引 - 主分片数量
            }
        }
    }
}
```

### 删除索引

向 ES 服务器发 **DELETE 请求** ： http://127.0.0.1:9200/shopping

返回结果

```json
{
    "acknowledged": true
}
```



## 入门-HTTP-文档-创建（Put & Post）

### 创建文档

向创建好的索引中，创建文档添加数据。文档类比关系数据库中的表数据，添加的数据格式为 JSON 格式

向 ES 服务器发 **POST 请求** ： http://127.0.0.1:9200/shopping/_doc，请求体JSON内容如下：

```json
{
    "title":"小米手机",
    "category":"小米",
    "images":"http://www.gulixueyuan.com/xm.jpg",
    "price":4000.00
}
```

![image-20220416185924887](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416185924887.png)

### **创建自定义id的文档   可POST/PUT**   

**PUT/POST请求**：http://127.0.0.1:9200/shopping/_doc/3

![image-20220416190335882](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416190335882.png)

## 入门-HTTP-查询-主键查询 & 全查询

### **主键查询**

**GET请求**：http://127.0.0.1:9200/shopping/_doc/3

返回主键为3的信息

![image-20220416214540862](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416214540862.png)

### **全查询**

**GET请求**：http://127.0.0.1:9200/shopping/_search    "_search"

返回全部文档

![image-20220416214932036](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416214932036.png)

## 入门-HTTP-全量修改 & 局部修改 & 删除

### 全量修改

和新增文档一样，输入相同的 URL 地址请求，请求体将原有数据覆盖

**PUT（POST） 请求** ： http://127.0.0.1:9200/shopping/_doc/1001

```json
修改的请求体JSON内容

{
    "title":"小米手机",
    "category":"小米",
    "images":"http://www.gulixueyuan.com/xm.jpg",
    "price":199.00  --->>>修改的地方
}
```

修改成功后，服务器响应结果：

![image-20220416225325442](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416225325442.png)

### 局部修改

修改文档中某一天数据的局部信息，不会覆盖原有的

==请求连接中的文档位，要修改成"_update"==

**POST请求**：http://127.0.0.1:9200/shopping/_update/1001。

```json
##请求体的书写样式！！ 文档名 -- 需要修改的信息
{
    "doc":{
        "title":"华为手机",
        "category":"华为"
    }
}
```

响应：

![image-20220416230548441](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416230548441.png)

### 删除

删除一个文档（相当于关系数据库中数据表的一行）不会立即从磁盘上移除，它只是被标记成已删除（逻辑删除）。

**DELETE 请求** ： http://127.0.0.1:9200/shopping/_doc/3

结果

![image-20220416231024007](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416231024007.png)

验证

![image-20220416231144148](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416231144148.png)

## 入门-HTTP-条件查询 & 分页查询 & 查询排序

### 条件查询

#### URL带参查询

查找category为小米的文档，**GET请求**： http://127.0.0.1:9200/shopping/_search?q=category:小米 

"_search"：查询，" ? "：后接参数，" q "：query

![image-20220416232659790](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220416232659790.png)

#### 请求体带参

**GET请求**： http://127.0.0.1:9200/shopping/_search

请求体参数： query  查询 ---  match 匹配哪  ---   "category":"小米"   需要找的信息

```json
{
    "query":{
        "match":{
            "category":"小米"
        }
    }
}
```

返回结果同上

#### 带请求体方式的查找所有内容

**GET请求**： http://127.0.0.1:9200/shopping/_search  ，请求体JSON参数

```json
{
	"query":{
		"match_all":{}
	}
}
```

返回的结果和，没有请求体的**GET请求**： http://127.0.0.1:9200/shopping/_search，一样

#### 查询指定字段

**如果你想查询指定字段**，在 Postman 中，向 ES 服务器发 GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下

```json
{
	"query":{
		"match_all":{}   --->>查所有
	},
	"_source":["title"]  --->>字段“title”
}
```

返回结果如下：

```json
{
    "took": 5,
    "timed_out": false,
    "_shards": {
        "total": 1,
        "successful": 1,
        "skipped": 0,
        "failed": 0
    },
    "hits": {
        "total": {
            "value": 6,
            "relation": "eq"
        },
        "max_score": 1,
        "hits": [
            {
                "_index": "shopping",
                "_type": "_doc",
                "_id": "ANQqsHgBaKNfVnMbhZYU",
                "_score": 1,
                "_source": {
                    "title": "小米手机"
                }
            },
            {
                "_index": "shopping",
                "_type": "_doc",
                "_id": "A9R5sHgBaKNfVnMb25Ya",
                "_score": 1,
                "_source": {
                    "title": "小米手机"
                }
            },
            {
                "_index": "shopping",
                "_type": "_doc",
                "_id": "BNR5sHgBaKNfVnMb7pal",
                "_score": 1,
                "_source": {
                    "title": "小米手机"
                }
            },
            {
                "_index": "shopping",
                "_type": "_doc",
                "_id": "BtR6sHgBaKNfVnMbX5Y5",
                "_score": 1,
                "_source": {
                    "title": "华为手机"
                }
            },
            {
                "_index": "shopping",
                "_type": "_doc",
                "_id": "B9R6sHgBaKNfVnMbZpZ6",
                "_score": 1,
                "_source": {
                    "title": "华为手机"
                }
            },
            {
                "_index": "shopping",
                "_type": "_doc",
                "_id": "CdR7sHgBaKNfVnMbsJb9",
                "_score": 1,
                "_source": {
                    "title": "华为手机"
                }
            }
        ]
    }
}
```

