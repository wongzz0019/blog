# Redis

## Redis入门

### Linux安装

1. 下载安装包

2. 解压Redis安装包。程序/opt   tar -zxvf 

3. 进入解压后的文件，看到redis配置文件

   ![image-20220328203339013](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328203339013.png)

4. 基本环境安装

   ```
   yum install gcc-c++
   make
   make install
   ```

5. redis默认安装路径`/usr/local/bin`

   ![image-20220328204651798](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328204651798.png)

   redis-benchmark：性能测试	

   redis-cli：客户端	

   redis-server：redis服务

   redis-sentinel：哨兵，一个单独进程	

   redis-check-aof：持久化的aof

   redis-check-rdb：持久化的rdb

6. 在默认路径创建文件夹rconfig，再把redis配置文件复制到rconfig文件夹

   ```
   mkdir rconfig
   cp /opt/redis-6.2.6/redis.conf rconfig/
   ```

   日后就是用这里这个配置文件进行启动

7. redis默认不是后台启动，修改配置文件，让它能后台启动

   ```
   vim redis.conf
   
   修改 daemonize no ---> yes
   ```

   ![image-20220328210126922](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328210126922.png)

8. 启动redis服务！

   `redis-server rconfig/redis.conf`(通过指定配置文件启动服务)

   ![image-20220328210917821](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328210917821.png)

9. 使用客户端连接redis服务 `redis-cli -p 6379`  , 测试成功

   ![image-20220328211205503](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328211205503.png)

10. 查看redis进程是否开启 `ps -ef|grep redis`

    ![image-20220328211547657](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328211547657.png)

11. 关闭redis服务 `shutdown` 并退出 `exit`

    ![image-20220328211701740](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328211701740.png)

12. 再次查看redis进程 `ps -ef|grep redis`



### 性能测试工具

**redis-benchmark** 是个压力测试工具！

图片来自菜鸟教程：

![image-20220328212511031](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328212511031.png)

简单测试下玩玩

```bash
#测试 100个并发连接 100000请求
redis-benchmark -h localhost -p 6379 -c 100 -n 100000
```

![image-20220328213812739](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220328213812739.png)



### 基础知识

redis默认有16个数据库，默认使用的是第0个

```bash
select 2	#select切换数据库
set name hzz	#设置k-v
dbsize	#查看数据库存储数 
get name	#获取v
keys *	#查看数据库所有的key
flushdb	#清空当前库
flushall	#清空所有库
```



## Redis五大数据类型

### Redis-Key

在redis中无论什么数据类型，在数据库中都是以key-value形式保存。

```bash
exists key：判断键是否存在

del key：删除键值对

move key db：将键值对移动到指定数据库

expire key seconds：设置键值对的过期时间

type key：查看value的数据类型

ttl key：查看离过期剩余时间

rename key newkey：修改 key名称。当newkey存在，key改名newkey，key值覆盖newkey值

renamenx key newkey：仅当 newkey 不存在时，将 key 改名为 newkey ，否则不成功； 当key不存在时，返回一个错误。
```



```bash
127.0.0.1:6379[2]> set age 10
OK
127.0.0.1:6379[2]> set name wong
OK
127.0.0.1:6379[2]> keys *
1) "age"
2) "name"
127.0.0.1:6379[2]> move age 3	#将键值对移动到指定数据库
(integer) 1
127.0.0.1:6379[2]> exists age	#判断键是否存在
(integer) 0	#不存在
127.0.0.1:6379[2]> exists name
(integer) 1	# 存在
127.0.0.1:6379[2]> del name	# 删除键值对
(integer) 1
127.0.0.1:6379[2]> set long 10
OK
127.0.0.1:6379[2]> expire long 10	#设置键值对的过期时间(秒)
(integer) 1	 #设置成功，开始计数
127.0.0.1:6379[2]> ttl long	#查看过期剩余时间
(integer) 4		#剩余的时间
127.0.0.1:6379[2]> ttl long
(integer) -2	#-2表示key过期，-1表示key未设置过期时间
127.0.0.1:6379[2]> get long	#过期的key 会被自动delete
(nil)
127.0.0.1:6379[2]> set max 11
OK
127.0.0.1:6379[2]> type max	#查看value的数据类型
string

127.0.0.1:6379> set key1 a
OK
127.0.0.1:6379> set key2 b
OK
127.0.0.1:6379> set key3 c
OK
127.0.0.1:6379> keys *
1) "key2"
2) "key3"
3) "key1"
127.0.0.1:6379> rename key1 key2	#rename修改key名称
OK
127.0.0.1:6379> get key2
"a"

#renamenx ：仅当 newkey 不存在时，将 key 改名为 newkey
127.0.0.1:6379> renamenx key2 key5	
(integer) 1
127.0.0.1:6379> keys *
1) "key5"
2) "key3"
```

**如果遇到不会的命令，官网查文档**



### String（字符串）

```bash
############################################################
127.0.0.1:6379> set key1 v1		#设置值
OK
127.0.0.1:6379> get key1	#获取值
"v1"
127.0.0.1:6379> exists key1		#判断key是否存在
(integer) 1
127.0.0.1:6379> append key1 "hello"		#追加字符串，如果追加的key不存在，想相当于set key value
(integer) 7
127.0.0.1:6379> get key1
"v1hello"
127.0.0.1:6379> strlen key1		#获取字符串长度
(integer) 7
127.0.0.1:6379> append key2 "www"  #key2不存在，就set key
(integer) 3
127.0.0.1:6379> keys *
1) "key2"
2) "key1
############################################################
# i++/i-- incr，decr
27.0.0.1:6379> set views 0	#初始浏览量为0
OK
127.0.0.1:6379> get views
"0"
127.0.0.1:6379> incr views	#自增1 浏览量变为1
(integer) 1
127.0.0.1:6379> get views
"1"
127.0.0.1:6379> incr views	#自增1 浏览量变为2
(integer) 2
127.0.0.1:6379> decr views	#自减1 浏览量1
(integer) 1
127.0.0.1:6379> decr views	#自减1 浏览量0
(integer) 0
127.0.0.1:6379> decr views	#自减1 浏览量-1
(integer) -1
127.0.0.1:6379> decr views	#自减1 浏览量-2
(integer) -2
127.0.0.1:6379> get views
"-2"

# 步长 i+=/i-= incrby decrby
127.0.0.1:6379> incrby views 10	 #incrby 可以设置步长，指定增量！
(integer) 8
127.0.0.1:6379> incrby views 10
(integer) 18
127.0.0.1:6379> decrby views 5	#decrby 可以设置步长，指定减量！
(integer) 13
############################################################
# 截取字符串范围 getrange
127.0.0.1:6379> set key1 "hello,world!"
OK
127.0.0.1:6379> getrange key1 0 4	#截取字符串[0,4]
"hello"
127.0.0.1:6379> getrange key1 0 -1	#截取全部的字符串和get key一样
"hello,world!"
127.0.0.1:6379> getrange key1 1 -1
"ello,world!"

# 替换！setrange
127.0.0.1:6379> set key2 abcdefg
OK
127.0.0.1:6379> get key2
"abcdefg"
127.0.0.1:6379> setrange key2 2 xxx # 指定位置开始 替换字符串
(integer) 7
127.0.0.1:6379> get key2
"abxxxfg"
############################################################
# setex (set with expire) # 设置过期时间
# setnx (set if not exist) # 不存在在设置 （在分布式锁中会常常使用！）
127.0.0.1:6379> setex key3 30 hi	#设置key3的值为 hi,30秒后过期
OK
127.0.0.1:6379> ttl key3
(integer) 26
127.0.0.1:6379> get key3
"hi"
127.0.0.1:6379> ttl key3
(integer) 7
127.0.0.1:6379> ttl key3
(integer) 4
127.0.0.1:6379> ttl key3	#过期，值失效
(integer) -2
127.0.0.1:6379> get kye3
(nil)

127.0.0.1:6379> setnx db redis	# 如果db不存在，创建db
(integer) 1
127.0.0.1:6379> setnx db mysql	# 如果db存在，创建失败！
(integer) 0
127.0.0.1:6379> get db
"redis"
############################################################
# mset,mget
127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3 # 同时设置多个值
OK
127.0.0.1:6379> keys *
1) "k1"
2) "k2"
3) "k3"
127.0.0.1:6379> mget k1 k2 k3 # 同时获取多个值
1) "v1"
2) "v2"
3) "v3"
127.0.0.1:6379> msetnx k1 v1 k4 v4 # msetnx 是一个原子性的操作，要么一起成功，要么一起失败！
(integer) 0
127.0.0.1:6379> get k4
(nil)

# 对象
set user:1 {name:zhangsan,age:3} # 设置一个 user:1 对象 值为 json字符来保存一个对象！
# 这里的key是一个巧妙的设计： user:{id}:{filed} , 如此设计在Redis中是完全OK了！
127.0.0.1:6379> mset user:1:name zhangsan user:1:age 2 （mset key value key value）
OK
127.0.0.1:6379> mget user:1:name user:1:age （mget key key）
1) "zhangsan"
2) "2"
############################################################
# getset 先get后set
127.0.0.1:6379> getset db redis # 如果不存在值，则返回 nil
(nil)
127.0.0.1:6379> set db redis
OK
127.0.0.1:6379> getset db mongodb # 如果存在值，获取原来的值，并设置新的值
"redis"
127.0.0.1:6379> get db
"mongodb"

```

String类似的使用场景：value除了是我们的字符串还可以是我们的数字！
计数器
统计多单位的数量
粉丝数
对象缓存存储...

