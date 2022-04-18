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
	"_source":["title"]  --->>指定字段“title”
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

### 分页查询

发 GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下：

```json
{
    "query":{
        "match":{}
    },
    "from":0,  --->当前页  （页码-1）*页内数据条数
    "size":2   --->一页多少条数据
}
```

返回结果

![image-20220417102526719](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417102526719.png)

### 查询排序

例如：按价格从低到高排序查询手机。 **"sort:{ }"** ，，**注意：要使用“match_all：{}**

GET请求：http://127.0.0.1:9200/shopping/_search  ，请求体JSON格式如下

```json
{
    "query":{
        "match_all":{}
    },
    "sort":{
        "price":{
            "order":"asc"  --降序desc
        }
    }
}
```

结果：

![image-20220417103245288](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417103245288.png)

## 入门-HTTP-多条件查询 & 范围查询

### 多条件查询(注意写法)

#### 且（must）

查询牌子是华为 并且 价格为1499的手机 。 ==must相当于数据库的&&==

GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下：

```json
{
    "query":{
        "bool":{
            "must":[{
                "match":{
                    "category":"华为"
                }
            },{
                "match":{
                    "price":1499
                }
            }]
        }
    }
}
```

结果：

![image-20220417105045642](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417105045642.png)

#### 或（should）

假设想找出小米和华为的牌子。  ==should相当于数据库的||==

在 Postman 中，向 ES 服务器发 GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下：

```json
{
    "query":{
        "bool":{
            "should":[{
                "match":{
                    "category":"华为"
                }
            },{
                "match":{
                    "category":"小米"
                }
            }]
        }
    }
}
```

结果：

![image-20220417105737895](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417105737895.png)

### 范围查询

假设想找出小米和华为的牌子，价格大于2000元的手机。

在 Postman 中，向 ES 服务器发 GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下：

```json
{
	"query":{
		"bool":{
			"should":[{
				"match":{
					"category":"小米"
				}
			},{
				"match":{
					"category":"华为"
				}
			}],
            "filter":{ 	--->过滤
            	"range":{
                	"price":{
                    	"gt":2000	--->"gt"是大于
                	}
	            }
    	    }
		}
	}
}
```

结果：

![image-20220417110501087](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417110501087.png)

## 入门-HTTP-全文检索 & 完全匹配 & 高亮查询

### 全文检索

这功能像搜索引擎那样，如品牌输入“华红”，返回结果带回品牌有“华为”和"红米"的。

在 Postman 中，向 ES 服务器发 GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下：

```json
{
    "query":{
        "match":{
            "category":"华红"
        }
    }
}
```

结果：

![image-20220417112111901](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417112111901.png)

### 完全匹配

GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下： =="match_phrase"==

```json
{
    "query":{
        "match_phrase":{
            "category":"红米"
        }
    }
}
```

结果

![image-20220417114151393](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417114151393.png)

### 高亮查询

 GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下： 

==highlight - 高亮  ；fields - 字段==

```json
{
    "query":{
        "match_phrase":{
            "category":"为"
        }
    },
    "highlight":{	//highlight高亮
        "fields":{	//fields字段
            "category":{}  //--->高亮这字段
        }
    }
}
```

结果

![image-20220417114843590](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417114843590.png)

## 入门-HTTP-聚合查询

==aggs	聚合操作==

类似与关系型数据库中的 group by，还有很多其他的聚合，例如取最大值max、平均值avg等等。

按price字段进行分组操作： GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下：

```json
{
    "aggs":{//聚合操作
        "price_group":{//分组名称，随便起
            "terms":{//分组	
                "field":"price"//分组字段
            }
        }
    }
}
```

结果

![image-20220417144429542](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417144429542.png)

这样结果会附带原有数据的。

通过JSON体设置=="size":0==，可以只返回分组结果，如下

```json
{
    "aggs":{
        "price_group":{
            "terms":{
                "field":"price"
            }
        }
    },
    "size":0 //不附带原数据显示
}
```

GET请求， http://127.0.0.1:9200/shopping/_search ，结果

![image-20220417144934166](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417144934166.png)



若想对所有手机价格求**平均值**。

 GET请求 ： http://127.0.0.1:9200/shopping/_search，附带JSON体如下：

```json
{
    "aggs":{
        "price_avg":{//名称，随意起名
            "avg":{//求平均
                "field":"price"
            }
        }
    },
    "size":0
}
```

结果

![image-20220417145319257](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417145319257.png)

## 入门-HTTP-映射关系

索引库等于数据库中的 database。

建索引库(index)中的映射了，类似于数据库(database)中的表结构(table)

创建数据库表需要设置字段名称，类型，长度，约束等；索引库也一样，需要知道这个类型下有哪些字段，每个字段有哪些约束信息，这就叫做映射(mapping)。

先创建一个索引：

![image-20220417150337745](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417150337745.png)

**创建映射**

==类型type：text能分词查询，keyword只能完全匹配==

==索引index：ture表示能索引查询。false不能索引查询，并且会报错==

```json
#PUT http://127.0.0.1:9200/user/_mapping

{
    "properties":{
        "name":{
            "type":"text",  //text类型，可分词查询
            "index":true    //true：可以索引查询
        },
        "sex":{
            "type":"keyword",   //keyword类型，不可分词，只能完全匹配
            "index":true
        },
        "tel":{
            "type":"keyword",
            "index":false   
        }
    }
}
```

返回结果如下：

```json
{
    "acknowledged": true
}
```

**查询映射**

```json
#GET http://127.0.0.1:9200/user/_mapping
```

返回结果如下：

```json
{
    "user": {
        "mappings": {
            "properties": {
                "name": {
                    "type": "text"
                },
                "sex": {
                    "type": "keyword"
                },
                "tel": {
                    "type": "keyword",
                    "index": false
                }
            }
        }
    }
}
```

增加数据	POST(PUT)请求： http://127.0.0.1:9200/user/_create/1001

```json
{
	"name":"小米",
	"sex":"男的",
	"tel":"1111"
}
```

返回结果如下：

```json
{
    "_index": "user",
    "_type": "_doc",
    "_id": "1001",
    "_version": 1,
    "result": "created",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 0,
    "_primary_term": 1
}
```

查找name含有”小“数据：GET http://127.0.0.1:9200/user/_search

```json
{
	"query":{
		"match":{
			"name":"小"
		}
	}
}
```

返回结果

![image-20220417151750690](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220417151750690.png)

查询电话

```json
# GET http://127.0.0.1:9200/user/_search
{
	"query":{
		"match":{
			"tel":"11"
		}
	}
}
```

结果

```json
{
    "error": {
          .......
    },
    "status": 400
}
```

**报错只因创建映射时"tel"的"index"为false。**

## 入门-JavaAPI-环境准备
