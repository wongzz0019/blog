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
# setnx (set if not exist) # 不存在再设置 （在分布式锁中会常常使用！）
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

127.0.0.1:6379> setnx db redis	# 如果key不存在，则自动创建key
(integer) 1
127.0.0.1:6379> setnx db mysql	# 如果db存在，则设置失败！
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

### List（列表）

列表元素可重复

在redis里面，我们可以把list玩成 ，栈、队列、阻塞队列！
所有的list命令都是用 “ L” 开头的，Redis不区分大小命令

````bash
##########################################################################
lpush：左插入列表元素
rpush：右插入列表元素
lrange：获取列表元素
127.0.0.1:6379> LPUSH list one # 将一个值或者多个值，插入到列表头部 （左）
(integer) 1
127.0.0.1:6379> LPUSH list two
(integer) 2
127.0.0.1:6379> LPUSH list three
(integer) 3
127.0.0.1:6379> LRANGE list 0 -1 # 获取list中值！
1) "three"
2) "two"
3) "one"
127.0.0.1:6379> LRANGE list 0 1 # 获取区间[0,1]具体的值！
1) "three"
2) "two"
127.0.0.1:6379> Rpush list righr # 将一个值或者多个值，插入到列表尾部 （右）
(integer) 4
127.0.0.1:6379> LRANGE list 0 -1
1) "three"
2) "two"
3) "one"
4) "righr"
##########################################################################
lpop：左移除列表元素
rpop：右移除列表元素
127.0.0.1:6379> lrange list 0 -1	#获取list中的所有值
1) "three"
2) "two"
3) "one"
4) "righr"
127.0.0.1:6379> lpop list 	#移除左边第一个元素
"three"
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "one"
3) "righr"
127.0.0.1:6379> lpop list 2		#移除左边两个元素
1) "two"
2) "one"
127.0.0.1:6379> lrange list 0 -1
1) "righr"

127.0.0.1:6379> lrange list 0 -1	
1) "four"
2) "three"
3) "two"
4) "one"
5) "righr"
127.0.0.1:6379> rpop list	#移除右边第一个元素
"righr"
127.0.0.1:6379> lrange list 0 -1	#获取list中的所有值
1) "four"
2) "three"
3) "two"
4) "one"
127.0.0.1:6379> rpop list 2		#移除右边两个元素
1) "one"
2) "two"
127.0.0.1:6379> lrange list 0 -1	#获取list中的所有值
1) "four"
2) "three"
##########################################################################
lrem：移除指定的值！（一个或多个）
可用于：取关 uid 等
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "four"
3) "three"
4) "two"
5) "one"
127.0.0.1:6379> lrem list 1 one	  #移除list集合中指定个数的value，精确匹配
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "four"
3) "three"
4) "two"
127.0.0.1:6379> lrem list 2 two
(integer) 2
127.0.0.1:6379> lrange list 0 -1
1) "four"
2) "three"
##########################################################################
Lindex：通过下标获取list的值
127.0.0.1:6379> LRANGE list 0 -1
1) "two"
2) "one"
127.0.0.1:6379> lindex list 1 # 通过下标获得 list 中的某一个值！
"one"
127.0.0.1:6379> lindex list 0
"two"
##########################################################################
llen：查列表长度
127.0.0.1:6379> lrange list 0 -1
1) "four"
2) "three"
127.0.0.1:6379> llen list	#返回列表长度
(integer) 2
##########################################################################
ltrim：截取指定下标长度，并保留在list里
127.0.0.1:6379> lrange list 0 -1
1) "c"
2) "b"
3) "a"
4) "four"
5) "three"
127.0.0.1:6379> ltrim list 1 3	#通过下标截取指定的长度，这个list已经被改变了，只剩下截取的元素！
OK
127.0.0.1:6379> lrange list 0 -1	#只剩下截取的元素！
1) "b"
2) "a"
3) "four"
##########################################################################
rpoplpush：移除列表的最后一个元素，将他移动到另一个列表中，若另一列表不存在则创建再移动！
127.0.0.1:6379> lrange list 0 -1
1) "b"
2) "a"
3) "four"
127.0.0.1:6379> rpoplpush list newlist	# 移除list列表的最后一个元素，将他移动到newlsit列表中
"four"
127.0.0.1:6379> lrange list 0 -1	# 查看原来的列表
1) "b"
2) "a"
127.0.0.1:6379> lrange newlist 0 -1	# 查看目标列表中，确实存在改值！
1) "four"
##########################################################################
lset：将列表中指定下标的值替换为另外一个值，更新操作
127.0.0.1:6379> exists demo	#判断这个列表是否存在
(integer) 0
127.0.0.1:6379> lset demo 0 one	#如果不存在列表我们去更新就会报错
(error) ERR no such key
127.0.0.1:6379> lpush demo hello	#插入一个元素
(integer) 1
127.0.0.1:6379> lset demo 0 one	#下标存在元素，更新当前下标的值。成功
OK
127.0.0.1:6379> lrange demo 0 -1
1) "one"
127.0.0.1:6379> lset list 1 other #当列表下标不存在元素，则会报错！
(error) ERR index out of range
##########################################################################
linsert：将某个具体的value插入列表中指定某个元素的前面或者后面！
127.0.0.1:6379> lrange mylist 0 -1
1) "three"
2) "two"
3) "one"
127.0.0.1:6379> linsert mylist before two hi	#在元素 two 的前面插入 hi
(integer) 4
127.0.0.1:6379> lrange mylist 0 -1
1) "three"
2) "hi"
3) "two"
4) "one"
127.0.0.1:6379> linsert mylist after two world	#在元素 two 的前面插入 world
(integer) 5
127.0.0.1:6379> lrange mylist 0 -1
1) "three"
2) "hi"
3) "two"
4) "world"
5) "one"
````

**小结**

- 他实际上是一个链表，before Node after ， left，right 都可以插入值
- 如果key 不存在，创建新的链表
- 如果key存在，新增内容
- 如果移除了所有值，空链表，也代表不存在！
- 在两边插入或者改动值，效率最高！ 中间元素，相对来说效率会低一点~

消息排队！消息队列 （Lpush Rpop）， 栈（ Lpush Lpop）！



### Set（集合）

set中的值不能重复！ 命令都是==s==开头

```bash
##########################################################################
sadd：set集合中添加元素，元素位置是无序的
smembers：查看set集合的所有值
sisimember：判断某值是否存在set集合中
127.0.0.1:6379> sadd myset "hello"	#set集合中添加元素
(integer) 1
127.0.0.1:6379> sadd myset www http	#可以添加一次添加多个元素
(integer) 2
127.0.0.1:6379> smembers myset	#查看指定set的所有值
1) "hello"
2) "http"
3) "www"
127.0.0.1:6379> sismember myset http	#判断某一个值是不是在set集合中！
(integer) 1
127.0.0.1:6379> sismember myset a
(integer) 0
##########################################################################
scard：获取set集合元素个数
127.0.0.1:6379> scard myset	#获取set集合中的内容元素个数！
(integer) 3
##########################################################################
srem：删除指定元素
127.0.0.1:6379> srem myset aaa http	#移除set集合中的指定元素(一个或多个)
(integer) 2
127.0.0.1:6379> smembers myset	#查看set集合的所有值
1) "hello"
2) "www"
##########################################################################
set 无序不重复集合。
srandmember：随机抽选元素。可用于抽奖、抽随机！
127.0.0.1:6379> smembers myset #查看集合中所有元素
1) "like"
2) "one"
3) "hello"
4) "www"
5) "two"
127.0.0.1:6379> srandmember myset	#随机抽选一个元素
"hello"
127.0.0.1:6379> srandmember myset 2	#随机抽选两个元素
1) "like"
2) "one"
##########################################################################
spop: 随机删除set集合中的元素！
127.0.0.1:6379> smembers myset
1) "pp"
2) "cc"
3) "d"
4) "bbq"
5) "one"
127.0.0.1:6379> spop myset	#随机移除一个元素
"cc"
127.0.0.1:6379> spop myset 2	#随机移除两个元素
1) "bbq"
2) "pp"
127.0.0.1:6379> smembers myset
1) "d"
2) "one"
##########################################################################
smove：将一个指定的值，移动到另外一个set集合中！
127.0.0.1:6379> smembers myset
1) "d"
2) "one"
127.0.0.1:6379> smove myset myset2 one	# 将一个指定的值，移动另一个集合myset2中，当另一集合不存在，自动创建再移动
(integer) 1
127.0.0.1:6379> smembers myset
1) "d"
127.0.0.1:6379> smembers myset2
1) "one"
##########################################################################
数字集合类：
- 差集 sdiff
- 交集 sinter 可用于共同关注！(并集)
- 并集 sunion
127.0.0.1:6379[8]> sadd key1 a
(integer) 1
127.0.0.1:6379[8]> sadd key1 b
(integer) 1
127.0.0.1:6379[8]> sadd key1 c
(integer) 1
127.0.0.1:6379[8]> sadd key2 c
(integer) 1
127.0.0.1:6379[8]> sadd key2 d
(integer) 1
127.0.0.1:6379[8]> sadd key2 e
(integer) 1
127.0.0.1:6379> sdiff key1 key2 # 差集
1) "b"
2) "a"
127.0.0.1:6379> sinter key1 key2 # 交集 共同好友就可以这样实现
1) "c"
127.0.0.1:6379> sunion key1 key2 # 并集
1) "b"
2) "c"
3) "e"
4) "a"
5) "d"
微博，A用户将所有关注的人放在一个set集合中！将它的粉丝也放在一个集合中！
共同关注，共同爱好，（sinter 并集）
推荐好友（sdiff 差集）
```



### Hash（哈希）

相当于Map集合，key-map！这时候值是一个map（field-value）集合

命令以h开头

```bash
##########################################################################
127.0.0.1:6379> hset myhash field1 one	# set一个具体 key-vlaue
(integer) 1
127.0.0.1:6379> hget myhash field1	# 获取一个字段值
"one"
127.0.0.1:6379> hset myhash field2 two field3 three	# set多个 key-vlaue。（hmset命令也可以）
(integer) 2
127.0.0.1:6379> hgetall myhash	# 获取全部的数据，
1) "field1"
2) "one"
3) "field2"
4) "two"
5) "field3"
6) "three"
127.0.0.1:6379> hmget myhash field2 field3	# 获取多个字段值
1) "two"
2) "three"
127.0.0.1:6379> hdel myhash field1	# 删除hash指定key字段！对应的value值也就消失了！
(integer) 1
127.0.0.1:6379> hgetall myhash
1) "field2"
2) "two"
3) "field3"
4) "three"
##########################################################################
127.0.0.1:6379> hgetall myhash
1) "field2"
2) "two"
3) "field3"
4) "three"
127.0.0.1:6379> hlen myhash	# 获取hash表的字段数量！
(integer) 2
##########################################################################
127.0.0.1:6379> hexists myhash field2	#判断hash中指定字段是否存在！
(integer) 1	#存在
127.0.0.1:6379> hexists myhash field1
(integer) 0	#不存在
##########################################################################
# 只获得所有field
# 只获得所有value
127.0.0.1:6379> hkeys myhash	# 只获得所有field
1) "field2"
2) "field3"
127.0.0.1:6379> hvals myhash	# 只获得所有value
1) "two"
2) "three"
##########################################################################
incr decr
127.0.0.1:6379> hset myhash field 1
(integer) 1
127.0.0.1:6379> hget myhash field
"1"
127.0.0.1:6379> hincrby myhash field 5	#指定增量！
(integer) 6
127.0.0.1:6379> hget myhash field
"6"
127.0.0.1:6379> hincrby myhash field -3	#指定减量！
(integer) 3
127.0.0.1:6379> hget myhash field
"3"
127.0.0.1:6379> hsetnx myhash field3 hello	# 如果不存在则可以设置
(integer) 1
127.0.0.1:6379> hsetnx myhash field3 hi	# 如果存在则不能设置
(integer) 0
127.0.0.1:6379> hget myhash field3
"hello"

```

hash可用于变更的数据 user name age,尤其是是用户信息之类的，经常变动的信息！ hash 更适合于对象的
存储，String更加适合字符串存储！



### Zset（有序集合）

在set的基础上，增加了一个值，set k1 v1  。

zset k1 score1 v1

```bash
127.0.0.1:6379> zadd myset 1 one	# 添加一个值
(integer) 1
127.0.0.1:6379> zadd myset 2 two 3 three	 # 添加多个值
(integer) 2
127.0.0.1:6379> zrange myset 0 -1	#查询所有值
1) "one"
2) "two"
3) "three"
##########################################################################
#排序如何实现
127.0.0.1:6379> zadd salary 2000 zhangsan	# 添加三个用户
(integer) 1
127.0.0.1:6379> zadd salary 1000 lisi
(integer) 1
127.0.0.1:6379> zadd salary 4000 huangwu
(integer) 1
# ZRANGEBYSCORE key min max
127.0.0.1:6379> zrangebyscore salary -inf +inf	# 显示全部用户，并且从小到大排序！
1) "lisi"
2) "zhangsan"
3) "huangwu"
127.0.0.1:6379> zrevrange salary 0 -1 # 从大到进行排序！
1) "huangwu"
2) "zhangsan"
3) "lisi"
127.0.0.1:6379> zrangebyscore salary -inf +inf withscores	# 显示全部的用户，小到大排序并且附带成绩（scores）
1) "lisi"
2) "1000"
3) "zhangsan"
4) "2000"
5) "huangwu"
6) "4000"
127.0.0.1:6379> zrangebyscore salary -inf 3000 withscores	#显示工资小于3000员工的升
序排序！
1) "lisi"
2) "1000"
3) "zhangsan"
4) "2000"
##########################################################################
# zrem	移除有序集合中指定元素
# zcard 获取有序集合中的个数
127.0.0.1:6379> zrem salary lisi	 #移除有序集合中的指定元素
(integer) 1
127.0.0.1:6379> zrange salary 0 -1
1) "zhangsan"
2) "huangwu"
127.0.0.1:6379> zcard salary	# 获取有序集合中的个数
(integer) 2
##########################################################################
#zcount 获取指定区间的成员数量
127.0.0.1:6379> zcount salary 2000 4567	# 获取指定区间的成员数量！
(integer) 3
127.0.0.1:6379> zcount salary 0 500
(integer) 1
```

其与的一些API，有需要用到，查查看官方文档！
案例思路：set 排序 存储班级成绩表，工资表排序！
普通消息，1， 重要消息 2，带权重进行判断！
排行榜应用实现



## 三种特殊数据类型

### Geospatial 地理位置

朋友的定位，附近的人，打车距离计算？
Redis 的 Geo 在Redis3.2 版本就推出了！ 这个功能可以推算地理位置的信息，两地之间的距离，方圆
几里的人！
可以查询一些测试数据：http://www.jsons.cn/lngcodeinfo/0706D99C19A781A3/

<hr>

``geoadd ``  添加地理位置

规则：两级无法直接添加，我们一般会下载城市数据，直接通过java程序一次性导入！

有效的经度从-180度到180度。

有效的纬度从-85.05112878度到85.05112878度。

当坐标位置超出上述指定范围时，该命令将会返回一个错误。

```bash
#geoadd key 经度 纬度 值
127.0.0.1:6379> geoadd china:city 121.37 31.23 shanghai	#添加地理位置
(integer) 1
127.0.0.1:6379> geoadd china:city 106.50 29.53 chongqing 114.05 22.52 shenzhen 116.38 39.90 beijing		#添加多个地理位置
(integer) 3
```



``geopos``  获取指定地区的坐标值（经纬度）

```bash
#geopos key member
127.0.0.1:6379> geopos china:city shenzhen	#获取单个地区坐标值
1) 1) "114.04999762773513794"
   2) "22.5200000879503861"
127.0.0.1:6379> geopos china:city beijing shanghai	#获取多个地区坐标值
1) 1) "116.38000041246414185"
   2) "39.90000009167092543"
2) 1) "121.36999815702438354"
   2) "31.22999903975783553"
```



``geodist``  获取两位置间的直线距离，没有指定单位参数，默认用m(米)单位

- m 表示单位为米。
- km 表示单位为千米。
- mi 表示单位为英里。
- ft 表示单位为英尺。

```bash
127.0.0.1:6379> geodist china:city beijing shenzhen	#两地间的直线距离，默认是m
"1945573.9752"
127.0.0.1:6379> geodist china:city beijing shenzhen km	#北京到深圳直线距离，km
"1945.5740"
```



``georadius``  以给定的经纬度为中心，找出某一半径内的元素

```bash
#georadius key 经度 维度 多少距离内 单位 --->显示以给定的经纬度中心距离多少的地区名
#georadius key 经度 维度 多少距离内 单位 withdist --->显示地区名和距离
127.0.0.1:6379> georadius china:city 110 30 1000 km	#以110，30 这个经纬度为中心，寻找方圆1000km内的城市
1) "chongqing"
2) "shenzhen"
127.0.0.1:6379> GEORADIUS china:city 110 30 500 km withdist #显示地区名和距离
1) 1) "chongqing"
   2) "341.9374"
```



``geogradiusbymember``	找出指定元素周围的其他元素！

```bash
127.0.0.1:6379> georadiusbymember china:city chongqing 1000 km	#指定元素的周围多少米的其它元素。当没有就返回本身
1) "chongqing"
```

### Hyperloglog 统计基数

- 什么是基数

A {1,3,5,7,8,7}
基数（不重复的元素） = 5，可以接受误差！

- 简介

  - Redis Hyperloglog 基数统计的算法！

  - 优点：占用的内存是固定。如果要从内存角度来比较的话 Hyperloglog 首选！
  - 网页的UV （独立访问。一个人访问一个网站多次，但是还是算作一个人！）、独立IP数、搜索记录数等需去重。可以使用
  - 传统的方式，set 保存用户的id，然后就可以统计 set 中的元素数量作为标准判断 !这个方式如果保存大量的用户id，就会比较麻烦！我们的目的是为了计数，而不是保存用户id；
  - 0.81% 错误率！ 统计UV任务，可以忽略不计的！

```bash
127.0.0.1:6379> pfadd mykey a b c d e f g h j	 # 创建第一组元素 mykey
(integer) 1
127.0.0.1:6379> pfcount mykey	# 统计 mykey 元素的基数数量
(integer) 9
127.0.0.1:6379> pfadd mykey2 c d y u i o a n	# 创建第二组元素 mykey2
(integer) 1
127.0.0.1:6379> pfcount mykey2	# 统计 mykey2 元素的基数数量
(integer) 8
127.0.0.1:6379> pfmerge key mykey mykey2	# 合并两组 mykey mykey2 => key 并集
OK
127.0.0.1:6379> pfcount key		# 看并集的数量！(基数统计去除重复元素)
(integer) 14
```

如果允许容错，那么一定可以使用 Hyperloglog ！
如果不允许容错，就使用 set 或者自己的数据类型即可!



### Bitmap

位存储

统计用户信息，活跃，不活跃！ 登录 、 未登录！ 打卡，365打卡！ 两个状态的，都可以使用
Bitmaps！
Bitmap 位图，数据结构！ 都是操作二进制位来进行记录，就只有0 和 1 两个状态！

365 天 = 365 bit

1字节 = 8bit

46 个字节左右！

![image.png](https://i.loli.net/2020/12/10/oOzwbPAxHYp5v3W.png?ynotemdtimestamp=1648619775467)

使用bitmap 来记录 周一到周日的打卡！

周一：1

周二：0

周三：0

周四：1

.......

```bash
#模拟上班打卡
127.0.0.1:6379> setbit sign 1 1	#设置每日 有没打卡（0没，1有）
(integer) 0
127.0.0.1:6379> setbit sign 2 0
(integer) 0
127.0.0.1:6379> SETBIT sign 3 0
(integer) 0
127.0.0.1:6379> SETBIT sign 4 1
(integer) 0
127.0.0.1:6379> SETBIT sign 5 1
(integer) 0
127.0.0.1:6379> SETBIT sign 6 0
(integer) 0
```

查看某一天是否有打卡！

```bash
127.0.0.1:6379> GETBIT sign 4
(integer) 1
```

统计操作，统计 打卡的天数！

```bash
127.0.0.1:6379> BITCOUNT sign # 统计这周的打卡记录，就可以看到是否有全勤！
(integer) 3
```



## 事务

Redis 事务本质：一组命令的集合！ 一个事务中的所有命令都会被序列化，在事务执行过程的中，会按
照顺序执行！

一次性、顺序性、排他性！执行一些列的命令！

```
------ 队列 set set set 执行------
```

==**Redis事务没有隔离级别的概念！**==
所有的命令在事务中，并没有直接被执行！只有发起执行命令的时候才会执行！Exec
==**Redis单条命令式保存原子性的，但是事务不保证原子性！**==

Redis事务命令主要包括 WATCH, EXEC, DISCARD, MULTI。

redis的事务：

- 开启事务（multi）
- 命令入队（......）
- 执行事务（exec）

> 正常执行事务！

```bash
127.0.0.1:6379> MULTI	#开启事务
OK
127.0.0.1:6379(TX)> set key1 v1	# 命令入队
QUEUED
127.0.0.1:6379(TX)> set name wong	# 命令入队
QUEUED
127.0.0.1:6379(TX)> set age 18	# 命令入队
QUEUED
127.0.0.1:6379(TX)> get name	# 命令入队
QUEUED
127.0.0.1:6379(TX)> set k2 v2	# 命令入队
QUEUED
127.0.0.1:6379(TX)> EXEC	# 执行事务
1) OK
2) OK
3) OK
4) "wong"
5) OK
127.0.0.1:6379> get k2 #获取值
"v2"
```

> 放弃事务！(DISCARD )

```bash
127.0.0.1:6379> multi	# 开启事务
OK
#命令入队
127.0.0.1:6379(TX)> set k1 v1
QUEUED
127.0.0.1:6379(TX)> set k2 v2
QUEUED
127.0.0.1:6379(TX)> set k3 v3
QUEUED
127.0.0.1:6379(TX)> DISCARD	# 取消事务，命令入队不成功
OK
127.0.0.1:6379> get k3	# 事务队列中命令都不会被执行！(因为取消了事务)
(nil)

```

> 编译型异常（代码有问题！ 命令有错！） ，事务中所有的命令都不会被执行！

```bash
127.0.0.1:6379> multi	#开启事务
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> getset k3 #错误的命令
(error) ERR wrong number of arguments for 'getset' command
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> set k5 v5
QUEUED
127.0.0.1:6379> exec # 执行事务报错！
(error) EXECABORT Transaction discarded because of previous errors.
127.0.0.1:6379> get k5 # 所有的命令都不会被执行！
(nil)
127.0.0.1:6379> get k1
(nil)
```

> 运行时异常（1/0）， 如果事务队列中存在语法性，那么执行命令的时候，其他命令是可以正常执行
> 的，错误命令抛出异常！

```bash
127.0.0.1:6379> set k1 "v1"	#设置一个key
OK
127.0.0.1:6379> multi	#开启事务
OK
127.0.0.1:6379> incr k1 # 执行事务的时候会失败！ incr只对梳子有效
QUEUED	#这暂时报成功
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> get k3
QUEUED
127.0.0.1:6379> exec	#执行事务
1) (error) ERR value is not an integer or out of range #虽然第一条命令报错了
2) OK	#但是其它命令依旧正常执行成功
3) OK
4) "v3"
127.0.0.1:6379> get k2
"v2"
127.0.0.1:6379> get k3
"v3"
```

### **==监控！ Watch（面试常问）==**

watch要在事务开启前使用

**悲观锁：**

- 很悲观，认为什么时候都会出问题，无论做什么都会加锁！

**乐观锁：** 可用于秒杀系统等...

- 很乐观，认为什么时候都不会出问题，所以不会上锁！ - - 更新数据的时候去判断一下，在此期间是否
  有人修改过这个数据，
- 获取version
- 更新的时候比较 version

> Redis测监视测试

**正常执行成功**

```bash
27.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> watch money # 监视 money 对象
OK
127.0.0.1:6379> multi # 事务正常结束，数据期间没有发生变动，这个时候就正常执行成功！
OK
127.0.0.1:6379> DECRBY money 20	#自减20
QUEUED
127.0.0.1:6379> INCRBY out 20	#自增20
QUEUED
127.0.0.1:6379> exec	#正常执行成功
1) (integer) 80	#money
2) (integer) 20	#out
```

**测试多线程修改值 , 使用watch 可以当做redis的乐观锁操作！**

```bash
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> watch money	# 监视 money
OK
127.0.0.1:6379> decrby money 10
(integer) 90
127.0.0.1:6379> incrby money 10
(integer) 100
127.0.0.1:6379> MULTI	#开启事务
OK
127.0.0.1:6379(TX)> decrby money 10
QUEUED
127.0.0.1:6379(TX)> incrby out 10
QUEUED
127.0.0.1:6379(TX)> exec  #执行之前，另外一个线程修改了我们的值，就会导致事务执行失败，值修改不成功
(nil)

```

另一线程修改money值

```bash
#另一线程
127.0.0.1:6379> INCRBY money 100
(integer) 200
```

如果修改失败，获取最新的值就好 unwatch  - watch

```bash
127.0.0.1:6379> UNWATCH #1.发现事务执行失败，先解除监控
OK
127.0.0.1:6379> watch money	#重新监控，获取最新值
OK
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379(TX)> decrby money 5
QUEUED
127.0.0.1:6379(TX)> INCRBY out 5
QUEUED
127.0.0.1:6379(TX)> EXEC #对比监控的值是否发送了变化，如果没有那可以执行成功，如果变了就执行失败
1) (integer) 195
2) (integer) 5

```



## Jedis

什么是Jedis？是 Redis 官方推荐的 java连接开发工具！ 使用Java 操作Redis 中间件！

如果你要使用java操作redis，那么一定要对Jedis 十分的熟悉！

#### 测试

1. 导入对应的依赖

```java
<!--导入jedis的包-->
<dependencies>
<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
    <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>4.2.0</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.78</version>
        </dependency>
</dependencies>
```

1. 、编码测试：

- 连接数据库
- 操作命令
- 断开连接！

##### windows下：

```java
package com.hzz;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        // 1、 new Jedis 对象即可
        Jedis jedis = new Jedis("127.0.0.1",6379);
        // jedis 所有的命令就是我们之前学习的所有指令！所以之前的指令学习很重要！

        System.out.println(jedis.ping());
    }
}

```

控制台输入 PONG   即成功！

##### 远程连接阿里云

- 修改配置文件

  - 注释掉 ``#bind 127.0.0.1 -::1``
  - 设置保护模式为no，`protected-mode no`
  - 设置密码

- 阿里云

  - 安全组添加redis-6379的端口

- Xshell 启动redis服务 

  - redis-server 配置文件

- 用代码连接服务器

  - ```java
    public class TestPing {
        public static void main(String[] args) {
            // 1、 new Jedis 对象即可
            Jedis jedis = new Jedis("服务器公IP",6379);
            // jedis 所有的命令就是我们之前学习的所有指令！所以之前的指令学习很重要！
    
            jedis.auth("redis密码");
            System.out.println(jedis.ping());
        }
    }
    ```

- 如果失败

  - Xshell连接服务器，看是否开启6379端口
  - 关闭防火墙



#### 常用的API

- String
- List
- Set
- Hash
- Zset

--- 所有的api命令，和之前一样，一个都没有变化！

#### 事务

--- 事务正常的情况

```java
public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","word");
        jsonObject.put("name","wong");
  		String result = jsonObject.toJSONString();

		//jedis.watch(result); //监视
        //开启事务
        Transaction multi = jedis.multi();

        try {
            //命令入队
            multi.set("user1",result);
            multi.set("user2",result);
            //执行事务
            multi.exec();
        } catch (Exception e){
            //如果有异常就 放弃事务
            multi.discard(); //放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();  //关闭连接
        }
    }
}
```

输出 ：

![image-20220330230256713](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220330230256713.png)



--- 事务异常的情况

```java
public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","word");
        jsonObject.put("name","wong");
        
        //开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();

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
```

输出：

![image-20220330230728628](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220330230728628.png)



## SpringBoot整合Redis

SpringBoot 操作数据：spring-data jpa jdbc mongodb redis！

SpringData 也是和 SpringBoot 齐名的项目！

说明： 在 SpringBoot2.x 之后，原来使用的jedis 被替换为了 lettuce

jedis : 采用的直连，多个线程操作的话，是不安全的，如果想要避免不安全的，使用 jedis pool 连接
池！ 更像 BIO 模式

lettuce : 采用netty，实例可以再多个线程中进行共享，不存在线程不安全的情况！可以减少线程数据
了，更像 NIO 模式

### 源码分析：

```java
@Bean
@ConditionalOnMissingBean(name = "redisTemplate") // 我们可以自己定义一个redisTemplate来替换这个默认的！
    public RedisTemplate<Object, Object>redisTemplate(RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {
        // 默认的 RedisTemplate 没有过多的设置，redis 对象都是需要序列化！
        // 两个泛型都是 Object, Object 的类型，我们后使用需要强制转换 <String, Object>
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
}
@Bean
@ConditionalOnMissingBean // 由于 String 是redis中最常使用的类型，所以说单独提出来了一个bean！
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
```

### 整合测试

1. 导入依赖

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>
   ```

2. 配置连接

   ```yaml
   #SpringBoot 所有的配置类，都有一个自动配置类  RedisAutoConfiguration
   #自动配置类都会绑定一个 properties 配置文件  RedisProperties
   
   #配置redis  这样远程连接
   spring:
     redis:
       database: 11
       host: 公网ip
       port: 6379
       password: 密码
       
   ```

3. 测试！

   ```java
   @SpringBootTest
   class Redis02SpringbootApplicationTests {
       @Autowired
       private RedisTemplate redisTemplate;	//自动装配进来才能用
       @Test
       void contextLoads() {
           // redisTemplate 操作不同的数据类型，api和我们的指令是一样的
           // opsForValue 操作字符串 类似String
           // opsForList 操作List 类似List
           // opsForSet
           // opsForHash
           // opsForZSet
           // opsForGeo
           // opsForHyperLogLog
           // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务和基本的 CRUD
           // 获取redis的连接对象
           // RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
           // connection.flushDb();
           // connection.flushAll();
           
           redisTemplate.opsForValue().set("key","hello,wordl");
           //redisTemplate.opsForValue().set("key","你好"); 中文时需要配置序列化，否则中文乱码
           System.out.println(redisTemplate.opsForValue().get("key"));
       }
   }
   ```

   ![image-20220331103407602](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220331103407602.png)

![image-20220331104307703](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220331104307703.png)

### 自定义RedisTemplate

redis序列化有多种，下面是json序列化

```java
@Configuration
public class RedisConfig {

    // 自己定义了一个 RedisTemplate
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplates(RedisConnectionFactory factory) {
        // 我们为了自己开发方便，一般直接使用 <String,Object>
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        // Json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
```

```java
@Test
public void test() {
    // 真实开发一般都是使用json来传递对象
    User user = new User("小明", 11);
    redisTemplate.opsForValue().set("user",user);
    System.out.println(redisTemplate.opsForValue().get("user"));
}
```

### RedisUtil工具类

正常开发都是用工具类操作的，工具类可以通过网上查找

```java
@Component
public class RedisUtil {
    @Autowired
    @Qualifier("redisTemplates")
    private RedisTemplate<String, Object> redisTemplate;

    // =============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */

    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */

    public void delCache(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }
    // =============================String============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key    键
     * @param inData 要增加几(大于0)
     * @return
     */
    public long incr(String key, long inData) {
        if (inData < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, inData);
    }

    /**
     * 递增
     *
     * @param key    键
     * @param inData 要减少几(大于0)
     * @return
     */
    public long decr(String key, long inData) {
        if (inData < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, -inData);
    }

    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);

    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */

    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {

        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */

    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */

    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */

    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return 358
     */

    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */

    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return 391
     */

    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return 405
     */

    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return 420
     */

    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */

    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 453
     */

    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 472
     */

    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 490
     */

    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */

    public long lRemove(String key, long count, Object value) {

        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}

```

测试

```java
@Test
public void utilTest(){
    User user = new User("小小", 18);
    redisUtil.set("user2",user);
    System.out.println(redisUtil.get("user2"));
}
```



## Redis.conf配置文件

### 单位

配置文件 unit单位 对大小写不敏感！

![image-20220331123148164](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220331123148164.png)

### 包含

![image-20220331123322627](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220331123322627.png)

就是好比我们学习Spring、Improt， include

### 网络 NETWORK

```bash
bind 127.0.0.1 	# 绑定的ip
protected-mode yes	 # 是否开启保护模式
port 6379	 # 端口设置
```

### 通用 GENERAL

```BASH
daemonize yes 	# 以守护进程的方式运行，默认是 no，我们需要自己开启为yes！
pidfile /var/run/redis_6379.pid	 # 如果以后台的方式运行，我们就需要指定一个 pid 文件！
# 日志
# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably) 生产环境
# warning (only very important / critical messages are logged)
loglevel notice
logfile ""	 # 日志的文件位置名
databases 16	 # 数据库的数量，默认是 16 个数据库
always-show-logo yes	 # 是否总是显示LOGO
```

### 快照 snapshotting

持久化， 在规定的时间内，执行了多少次操作，则会持久化到文件 .rdb. aof
redis 是内存数据库，如果没有持久化，那么数据断电及失！

```BASH
# 如果900s内，如果至少有一个1 key进行了修改，我们及进行持久化操作
save 900 1
# 如果300s内，如果至少10 key进行了修改，我们及进行持久化操作
save 300 10
# 如果60s内，如果至少10000 key进行了修改，我们及进行持久化操作
save 60 10000

# 我们之后学习持久化，会自己定义这个测试！
stop-writes-on-bgsave-error yes	 # 持久化如果出错，是否还需要继续工作！
rdbcompression yes 	# 是否压缩 rdb 文件，需要消耗一些cpu资源！
rdbchecksum yes 	# 保存rdb文件的时候，进行错误的检查校验！
dir ./	 # rdb 文件保存的目录！
```

### REPLICATION 复制，我们后面讲解主从复制的，时候再进行讲解

### SECURITY 安全

```bash
127.0.0.1:6379> ping
PONG
127.0.0.1:6379> config get requirepass 	# 获取redis的密码
1) "requirepass"
2) ""
127.0.0.1:6379> config set requirepass "123456" 	# 设置redis的密码
OK
127.0.0.1:6379> config get requirepass 	# 发现所有的命令都没有权限了
(error) NOAUTH Authentication required.
127.0.0.1:6379> ping
(error) NOAUTH Authentication required.
127.0.0.1:6379> auth 123456	 # 使用密码进行登录！
OK
127.0.0.1:6379> config get requirepass
1) "requirepass"
2) "123456"
```

### 限制 CLIENTS

```bash
maxclients 10000 	# 设置能连接上redis的最大客户端的数量
maxmemory <bytes> 	# redis 配置最大的内存容量
maxmemory-policy noeviction 	# 内存到达上限之后的处理策略
1、volatile-lru：只对设置了过期时间的key进行LRU（默认值）
2、allkeys-lru ： 删除lru算法的key
3、volatile-random：随机删除即将过期key
4、allkeys-random：随机删除
5、volatile-ttl ： 删除即将过期的
6、noeviction ： 永不过期，返回错误
```

### APPEND ONLY 模式 aof配置

```bash
appendonly no	 # 默认是不开启aof模式的，默认是使用rdb方式持久化的，在大部分所有的情况下，
rdb完全够用！
appendfilename "appendonly.aof" 	# 持久化的文件的名字
# appendfsync always 	# 每次修改都会 fsync。消耗性能
appendfsync everysec 	# 每秒执行一次 fsync，可能会丢失这1s的数据！
# appendfsync no	 # 不执行 sync，这个时候操作系统自己同步数据，速度最快！
```



## ==Redis持久化==

面试和工作，持久化都是重点！
Redis 是**内存数据库**，如果不将内存中的数据库状态保存到磁盘，那么一旦服务器进程退出，服务器中
的数据库状态也会消失。所以 Redis 提供了持久化功能！

### RDB（Redis DataBase） （快照）

将某时刻所有数据写入硬盘中。**保存的文件以.rdp结尾**的文件。恢复时是将快照文件直接读到内存里。

redis默认的就是RDB

![image.png](https://i.loli.net/2020/12/10/8bZ21fKTvtBWjdl.png)



如果大规模恢复数据，对于数据恢复的完整性不是非常敏感，那RDB方式要比AOF方式更加的高效。

**过程**：RDB创建（fork）一个子进程来进行持久化，先写入临时文件，待持久化结束，用临时文件代替上次持久化好的文件。

**生成快照方式** ：

- 客户端方式：BGSAVE 和 SAVE 指令 。
  - bgsave：开启一个新进程。 
  - save：接收到save命令的redis服务器都会**阻塞**其他命令
- 服务器配置自动触发

**优点**：

1. 适合大规模的数据恢复！

**缺点：**

1. 需要一定的时间间隔进程操作！如果redis意外宕机了，这个最后一次修改数据就没有的了！
2. fork进程的时候，会占用一定的内容空间！！



### AOF（Append Only File）

只追加日志文件。就是将所有redis 写命令 记录到日志文件中，恢复的时候就把这个文件全部在执行一遍！

![image.png](https://i.loli.net/2020/12/11/R9bNlX8x27iBQtM.png)

以日志的形式来记录每个写操作，将Redis执行过的所有指令记录下来（读操作不记录），只许追加文件
但不可以改写文件，redis启动之初会读取该文件重新构建数据，换言之，redis重启的话就根据日志文件
的内容将写指令从前到后执行一次以完成数据的恢复工作

aof保存的是 appendonly.aof 文件

**append**

![image-20220331150311561](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220331150311561.png)

默认是不开启的。开启后需重启，redis 就可以生效了！

如果aof文件有错误，需用命令==redis-check-aof --fix appendonly.aof==修复

**优点**：

1. 每一次修改都同步，文件的完整会更加好！
2. 每秒同步一次，可能会丢失一秒的数据
3. 从不同步效率最高的！

**缺点**：

1. 相对于数据文件来说，aof远远大于 rdb，修复的速度也比 rdb慢！
2. Aof 运行效率也要比rdb慢，所以我们redis默认的配置就是rdb持久化



### RDB 和 AOF ,我应该用哪一个？

- 如果你非常关心你的数据,但仍然可以承受数分钟以内的数据丢失， 那么你可以只使用 RDB 持久。
- AOF 将 Redis 执行的每一条命令追加到磁盘中，处理巨大的写入会降低 Redis 的性能，不知道你是否可以接受。



### 持久化总结

- 两种持久化方案既可以同时使用，又可以单独使用，在某种情况下可以都不使用，具体用哪种取决于用户数据和应用决定
- 无论使用aof或rdp，将数据持久化到硬盘都是用必要的，除了持久化外，用户还应该对持久化的文件进行备注（最好备份多个不同地方）



## Redis发布订阅

Redis 发布订阅(pub/sub)是一种消息通信模式：发送者(pub)发送消息，订阅者(sub)接收消息。微信、
微博、关注系统！

Redis 客户端可以订阅任意数量的频道。

订阅/发布消息图：

第一个：消息发送者， 第二个：频道 第三个：消息订阅者！

![image.png](https://i.loli.net/2020/12/10/DB7uzngTAlRO4by.png)

下图展示了频道 channel1 ， 以及订阅这个频道的三个客户端 —— client2 、 client5 和 client1 之间的
关系：![image.png](https://i.loli.net/2020/12/10/1hJRPkSUxTDuWsp.png)

当有新消息通过 PUBLISH 命令发送给频道 channel1 时， 这个消息就会被发送给订阅它的三个客户
端：![image.png](https://i.loli.net/2020/12/10/uzlj9fb23KLaerw.png)

#### 命令

这些命令被广泛用于构建即时通信应用，比如网络聊天室(chatroom)和实时广播、实时提醒等。

![image.png](https://i.loli.net/2020/12/11/HWzekTxsLGJKQSt.png)

#### 测试

**订阅端:**

````bash
#SUBSCRIBE
127.0.0.1:6379> SUBSCRIBE kuangshenshuo  # 订阅一个频道 kuangshenshuo 
1) "subscribe" 
2) "kuangshenshuo"
3) (integer) 1
# 等待读取推送的信息 
1) "message"  # 消息 
2) "kuangshenshuo"  # 那个频道的消息
3) "hello,kuangshen"  # 消息的具体内容

1) "message"
2) "kuangshenshuo"
3) "hello,redis"
````

**发送端:**

```bash
#PUBLISH
127.0.0.1:6379> PUBLISH kuangshenshuo "hello,kuangshen"   # 发布者发布消息到频道！
(integer) 1
127.0.0.1:6379> PUBLISH kuangshenshuo "hello,redis"   # 发布者发布消息到频道！
(integer) 1 
127.0.0.1:6379> 
```

#### **使用场景：**

1. 实时消息系统！
2. 事实聊天！（频道当做聊天室，将信息回显给所有人即可！）
3. 订阅，关注系统都是可以的！ 稍微复杂的场景我们就会使用 消息中间件MQ（）

#### java

**JedisPushSub类**
通过继承`JedisPubSub类`重写回调方法。实现java中Redis的发布订阅。`onMessage()`:发布者发布消息时，会执行订阅者的回调方法onMessage()接收发布的消息，在此方法实现消息接收后进行自定义的业务逻辑处理。

```java
//订阅者
public class Subscriber extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("订阅者：订阅频道:" + channel + "，收到消息:" + message);
    }

    public static void main(String[] args) {
        System.out.println("启动订阅者");
        //创建Jedis
        Jedis jedis = new Jedis("公网ip", 6379);
        jedis.auth("密码");
        //创建订阅者
        Subscriber subscriber = new Subscriber();
        //订阅信息
        jedis.subscribe(subscriber,"Huang");
    }
}
```

```java
//发送者
public class Publisher {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("公网ip",6379);
        jedis.auth("密码");
        jedis.publish("Huang","hello,i am Publisher");
        jedis.publish("Huang","www");
    }
}
```

## 主从复制

### 概念

 主从复制，是指将一台Redis服务器的数据，复制到其他的Redis服务器。前者称为主节点（Master/Leader）,后者称为从节点（Slave/Follower）， ==数据的复制是单向的！只能由主节点复制到从节点==（主节点以写为主、从节点以读为主）。

==默认情况下，每台Redis服务器都是主节点==，主节点只能有一个，从节点可多个。

当主服务器宕机后，需要手动把一台从服务器切为主服务器。

**主从复制的作用主要包括：**

1. 数据冗余：主从复制实现了数据的热备份，是持久化之外的一种数据冗余的方式。
2. 故障恢复：当主节点故障时，从节点可以暂时替代主节点提供服务，是一种服务冗余的方式
3. 负载均衡：在主从复制的基础上，配合读写分离，由主节点进行写操作，从节点进行读操作，分担服务器的负载；尤其是在多读少写的场景下，通过多个从节点分担负载，提高并发量。
4. 高可用基石：主从复制还是哨兵和集群能够实施的基础。

主从复制，读写分离！80%的情况下都是在进行读操作！减缓服务器的压力！架构中经常使用！ 一主 二从！

只要在公司中，主从复制就是必须要使用的，因为在真实的项目中不可能单机使用Redis！

### 环境配置

只配置从库，不用配置主库

```bash
120.78.166.92:6379> INFO replication	#查看当前库信息
# Replication
role:master	#角色
connected_slaves:0	#没有从机
master_failover_state:no-failover
master_replid:7a78b6811c33929d7cd234a5ae6cc465b469d10f
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
```

复制3个配置文件redisxxx-config，然后修改对应的信息

1. port端口
2. pidfile 名字
3. logfile文件名字
4. dbfilename dump.rdb 名字

修改完毕之后，启动我们的3个redis服务器，可以通过进程信息查看！

![image.png](https://i.loli.net/2020/12/11/7Qi6C4oDYBI1uHZ.png?ynotemdtimestamp=1649986735683)

### 一主二从

默认情况下，每台Redis服务器都是主节点；我们一般情况下只用配置从就好了！

认老大！ 一主 （79）二从（80，81）

```bash
127.0.0.1:6380> SLAVEOF 127.0.0.1 6379   #  SLAVEOF host 6379  找谁当自己的老大！
OK
127.0.0.1:6380> info replication 
# Replication
role:slave  # 当前角色是从机
master_host:127.0.0.1   # 可以的看到主机的信息 
master_port:6379 
master_link_status:up 
master_last_io_seconds_ago:3 
master_sync_in_progress:0 
slave_repl_offset:14 
slave_priority:100 
slave_read_only:1 
connected_slaves:0 master_replid:a81be8dd257636b2d3e7a9f595e69d73ff03774e
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:14
second_repl_offset:-1 
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1 
repl_backlog_histlen:14

# 在主机中查看！
127.0.0.1:6379> info replication
# Replication 
role:master connected_slaves:1  # 多了从机的配置
slave0:ip=127.0.0.1,port=6380,state=online,offset=42,lag=1    # 多了从机的配置
master_replid:a81be8dd257636b2d3e7a9f595e69d73ff03774e
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:42
second_repl_offset:-1 
repl_backlog_active:1 
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1 
repl_backlog_histlen:42 
```

如果两个都配置完了，就是有两个从机

![image.png](https://i.loli.net/2020/12/11/NaXd9sivbnVCc8M.png?ynotemdtimestamp=1649986735683)

真实的从主配置应该在配置文件中配置，这样的话是永久的，现在这里用的是命令，暂时的！

> 注意

主机可以写，从机不能写只能读！主机中的所有信息和数据，都会自动从机保存！
主机写：

![image.png](https://i.loli.net/2020/12/11/gxmYE634XbqQteL.png)

从机只能读取内容！

![image.png](https://i.loli.net/2020/12/11/T68wEHfrcv2MC5x.png)

测试：主机断开连接，从机依旧连接到主机的，但是没有写操作，这个候，主机如果回来了，从机依旧可以直接获取到主机写的信息！

> 如果没有了主机，可手动指定

主机断开了连接，可用` SLAVEOF no one` 让自己变成主机！其他的节点就可以手动连接到最新的这个主节点（手动）！如果这时旧主机修复了，那就重新连接！



## 哨兵模式

（自动选举主机的模式）

> 概述

能后台监控主机是否故障，如果故障了根据投票数自动将从库转换为主库。

哨兵模式是一种特殊的模式，哨兵是一个独立的进程，作为进程，它会独立运行。

==其原理是哨兵通过发送命令，等待Redis服务器响应，从而监控运行的多个Redis实例。==

![img](https://upload-images.jianshu.io/upload_images/11320039-57a77ca2757d0924.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)

这里的哨兵有两个作用

- 通过发送命令，让Redis服务器返回监控其运行状态，包括主服务器和从服务器。
- 当哨兵监测到master宕机，会自动将slave切换成master，然后通过发布订阅模式通知其他的从服
  务器，修改配置文件，让它们切换主机。

然而一个哨兵进程对Redis服务器进行监控，可能会出现问题，为此，我们可以使用多个哨兵进行监控。
各个哨兵之间还会进行监控，这样就形成了多哨兵模式。

![image.png](https://i.loli.net/2020/12/11/RLzoX5jQ4WbqkNM.png?ynotemdtimestamp=1649990117848)

假设主服务器宕机，哨兵1先检测到这个结果，系统并不会马上进行failover过程，仅仅是哨兵1主观的认
为主服务器不可用，这个现象成为 **主观下线** 。当后面的哨兵也检测到主服务器不可用，并且数量达到一
定值时，那么哨兵之间就会进行一次投票，投票的结果由一个哨兵发起，进行failover[故障转移]操作。
切换成功后，就会通过发布订阅模式，让各个哨兵把自己监控的从服务器实现切换主机，这个过程称为
**客观下线**。

### 搭建哨兵架构

我们目前的状态是 一主二从！

1、创建哨兵配置文件 sentinel.conf，并编写

```bash
# sentinel monitor 被监控的名称(自己取)  host  port  1

sentinel montitor myredis 120.78.166.92  6379 1 

#后面的这个数字1，代表哨兵系统里的哨兵数量。当主机挂了slave投票数要超过<后面数字的一半>才进行故障转移,选取新主机
```

2、启动哨兵！

```bash
redis-sentinel kconfig/sentinel.conf
```

```bash
[root@kuangshen bin]# redis-sentinel kconfig/sentinel.conf
26607:X 31 Mar 2020 21:13:10.027 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
26607:X 31 Mar 2020 21:13:10.027 # Redis version=5.0.8, bits=64,
commit=00000000, modified=0, pid=26607, just started
26607:X 31 Mar 2020 21:13:10.027 # Configuration loaded

                _._
            _.-``__ ''-._
        _.-`` `. `_. ''-._       Redis 5.0.8 (00000000/0) 64 bit
    .-`` .-```. ```\/ _.,_ ''-._
    ( ' , .-` | `, )` _.-'|         Running in sentinel mode
    |`-._`-...-` __...-.``-._|'        Port: 26379
    | `-._ `._ / _.-' |             PID: 26607
    `-._ `-._ `-./ _.-' _.-'
    |`-._`-._ `-.__.-' _.-'_.-'|
    | `-._`-._ _.-'_.-' |            http://redis.io
    `-._ `-._`-.__.-'_.-' _.-'
    |`-._`-._ `-.__.-' _.-'_.-'|
    | `-._`-._ _.-'_.-' |
    `-._ `-._`-.__.-'_.-' _.-'

26607:X 31 Mar 2020 21:13:10.029 # WARNING: The TCP backlog setting of 511
cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value
of 128.
26607:X 31 Mar 2020 21:13:10.031 # Sentinel ID is
4c780da7e22d2aebe3bc20c333746f202ce72996
26607:X 31 Mar 2020 21:13:10.031 # +monitor master myredis 127.0.0.1 6379 quorum
1
26607:X 31 Mar 2020 21:13:10.031 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @
myredis 127.0.0.1 6379
26607:X 31 Mar 2020 21:13:10.033 * +slave slave 127.0.0.1:6381 127.0.0.1 6381 @
myredis 127.0.0.1 6379
```

如果Master 节点断开了，就会从从机中随机选择一个服务器！ （这里面有一个投票算法！）

如果旧主机回来了，只能做新主机的从机，这就是哨兵模式的规则！

哨兵日志：

![image.png](https://i.loli.net/2020/12/11/N64mMouFDGfrsQT.png?ynotemdtimestamp=1649990117848)

### 优点

1. 哨兵集群，基于主从复制模式，所有的主从配置优点，它全有
2. 主从可以切换，故障可以转移，系统的可用性就会更好
3. 哨兵模式就是主从模式的升级，手动到自动，更加健壮！

### 缺点

1. Redis 不好啊在线扩容的，集群容量一旦到达上限，在线扩容就十分麻烦！
2. 实现哨兵模式的配置其实是很麻烦的，里面有很多选择！

> 哨兵模式的全部配置！

```bash
# Example sentinel.conf
# 哨兵sentinel实例运行的端口 默认26379。
port 26379
# 哨兵sentinel的工作目录
dir /tmp
# 哨兵sentinel监控的redis主节点的 ip port
# master-name 可以自己命名的主节点名字 只能由字母A-z、数字0-9 、这三个字符".-_"组成。
# quorum 配置多少个sentinel哨兵统一认为master主节点失联 那么这时客观上认为主节点失联了
# sentinel monitor <master-name> <ip> <redis-port> <quorum>
sentinel monitor mymaster 127.0.0.1 6379 2
# 当在Redis实例中开启了requirepass foobared 授权密码 这样所有连接Redis实例的客户端都要提供
密码
# 设置哨兵sentinel 连接主从的密码 注意必须为主从设置一样的验证密码
# sentinel auth-pass <master-name> <password>
sentinel auth-pass mymaster MySUPER--secret-0123passw0rd
# 指定多少毫秒之后 主节点没有应答哨兵sentinel 此时 哨兵主观上认为主节点下线 默认30秒
# sentinel down-after-milliseconds <master-name> <milliseconds>
sentinel down-after-milliseconds mymaster 30000
# 这个配置项指定了在发生failover主备切换时最多可以有多少个slave同时对新的master进行 同步，
这个数字越小，完成failover所需的时间就越长，
但是如果这个数字越大，就意味着越 多的slave因为replication而不可用。
可以通过将这个值设为 1 来保证每次只有一个slave 处于不能处理命令请求的状态。
# sentinel parallel-syncs <master-name> <numslaves>
sentinel parallel-syncs mymaster 1
# 故障转移的超时时间 failover-timeout 可以用在以下这些方面：
#1. 同一个sentinel对同一个master两次failover之间的间隔时间。
#2. 当一个slave从一个错误的master那里同步数据开始计算时间。直到slave被纠正为向正确的master那
里同步数据时。
#3.当想要取消一个正在进行的failover所需要的时间。
#4.当进行failover时，配置所有slaves指向新的master所需的最大时间。不过，即使过了这个超时，
slaves依然会被正确配置为指向master，但是就不按parallel-syncs所配置的规则来了
# 默认三分钟
# sentinel failover-timeout <master-name> <milliseconds>
sentinel failover-timeout mymaster 180000
# SCRIPTS EXECUTION
#配置当某一事件发生时所需要执行的脚本，可以通过脚本来通知管理员，例如当系统运行不正常时发邮件通知
相关人员。
#对于脚本的运行结果有以下规则：
#若脚本执行后返回1，那么该脚本稍后将会被再次执行，重复次数目前默认为10
#若脚本执行后返回2，或者比2更高的一个返回值，脚本将不会重复执行。
#如果脚本在执行过程中由于收到系统中断信号被终止了，则同返回值为1时的行为相同。
#一个脚本的最大执行时间为60s，如果超过这个时间，脚本将会被一个SIGKILL信号终止，之后重新执行。
#通知型脚本:当sentinel有任何警告级别的事件发生时（比如说redis实例的主观失效和客观失效等等），
将会去调用这个脚本，这时这个脚本应该通过邮件，SMS等方式去通知系统管理员关于系统不正常运行的信
息。调用该脚本时，将传给脚本两个参数，一个是事件的类型，一个是事件的描述。如果sentinel.conf配
置文件中配置了这个脚本路径，那么必须保证这个脚本存在于这个路径，并且是可执行的，否则sentinel无
法正常启动成功。
#通知脚本
# shell编程
# sentinel notification-script <master-name> <script-path>
sentinel notification-script mymaster /var/redis/notify.sh
# 客户端重新配置主节点参数脚本
# 当一个master由于failover而发生改变时，这个脚本将会被调用，通知相关的客户端关于master地址已
经发生改变的信息。
# 以下参数将会在调用脚本时传给脚本:
# <master-name> <role> <state> <from-ip> <from-port> <to-ip> <to-port>
# 目前<state>总是“failover”,
# <role>是“leader”或者“observer”中的一个。
# 参数 from-ip, from-port, to-ip, to-port是用来和旧的master和新的master(即旧的slave)通
信的
# 这个脚本应该是通用的，能被多次调用，不是针对性的。
# sentinel client-reconfig-script <master-name> <script-path>
sentinel client-reconfig-script mymaster /var/redis/reconfig.sh # 一般都是由运维来配
置！
```



## 缓存穿透、击穿、雪崩

Redis缓存的使用，极大的提升了应用程序的性能和效率，特别是数据查询方面。但同时，它也带来了一
些问题。其中，最要害的问题就是数据的一致性问题。

另外的一些典型问题就是，缓存穿透、缓存雪崩和缓存击穿。

![image.png](https://i.loli.net/2020/12/11/aG9wODIJn7AEzej.png)



### 缓存穿透（查不到）

> 概念

用户要查询一个数据，发现redis内存数据库没有，也就是缓存没有命中，于是向持久层数据库查询。发现也没有，于是本次查询失败。当用户很多的时候，缓存都没有命中（秒杀！），于是都去请求了持久层数据库。这会给持久层数据库造成很大的压力，这时候就相当于出现了缓存穿透。==很多用户查询数据，但这些数据没有在redis内存数据库中(缓存没有命中)，于是都向持久层数据库查询，给持久层数据库造成大压力，这就出现缓存穿透。==

> 解决方案

#### 布隆过滤器

布隆过滤器是一种数据结构，对所有可能查询的参数以hash形式存储，在控制层先进行校验，不符合则
丢弃，从而避免了对底层存储系统的查询压力

![image.png](https://i.loli.net/2020/12/11/nhHiCqjOGVJlWUp.png?ynotemdtimestamp=1650000112400)

#### 缓存空对象

当存储层不命中后，即使返回的空对象也将其缓存起来，同时会设置一个过期时间，之后再访问这个数
据将会从缓存中获取，保护了后端数据源；

![image.png](https://i.loli.net/2020/12/11/pKjOfIo76eZ2wc9.png?ynotemdtimestamp=1650000112400)

但是这种方法会存在两个问题：

1. 如果空值能够被缓存起来，这就意味着缓存需要更多的空间存储更多的键，因为这当中可能会有很多
   的空值的键；
2. 即使对空值设置了过期时间，还是会存在缓存层和存储层的数据会有一段时间窗口的不一致，这对于
   需要保持一致性的业务会有影响。



### 缓存击穿（量太大，缓存过期！）

> 概念

缓存击穿，是指一个key非常热点，大并发集中对这一个点进行访问，当这个key在失效的瞬间，持续的大并发就穿破缓存，直接请求数据库，就像在一个桶上凿开了一个洞。

当某个key在过期的瞬间，有大量的请求并发访问，这类数据一般是热点数据，由于缓存过期，会同时访问数据库来查询最新数据，并且回写缓存，会导使数据库瞬间压力过大。

> 解决方案

#### 设置热点数据永不过期

从缓存层面来看，没有设置过期时间，所以不会出现热点 key 过期后产生的问题。

#### **加互斥锁**

分布式锁setnx：使用分布式锁，保证对于每个key同时只有一个线程去查询后端服务，其他线程没有获得分布
式锁的权限，因此只需要等待即可。这种方式将高并发的压力转移到了分布式锁，因此对分布式锁的考
验很大。

![image.png](https://i.loli.net/2020/12/11/x34TrX7OavufWtc.png?ynotemdtimestamp=1650000112400)

### 缓存雪崩

> 概念

缓存雪崩，是指在某一个时间段，缓存集中过期失效。Redis 宕机！

**举个简单的例子**：如果所有首页的Key失效时间都是12小时，中午12点刷新的，我零点有个秒杀活动大量用户涌入，假设当时每秒 6000 个请求，本来缓存在可以扛住每秒 5000 个请求，但是缓存当时所有的Key都失效了。此时 1 秒 6000 个请求全部落数据库，数据库必然扛不住，它会报一下警，真实情况可能DBA都没反应过来就直接挂了。此时，如果没用什么特别的方案来处理这个故障，DBA 很着急，重启数据库，但是数据库立马又被新的流量给打死了。

> 解决方案

#### redis高可用

这个思想的含义是，既然redis有可能挂掉，那我多增设几台redis，这样一台挂掉之后其他的还可以继续
工作，其实就是搭建的集群。（异地多活！）

#### 限流降级

这个解决方案的思想是，在缓存失效后，通过加锁或者队列来控制读数据库写缓存的线程数量。比如对
某个key只允许一个线程查询数据和写缓存，其他线程等待。

#### 数据预热

数据加热的含义就是在正式部署之前，我先把可能的数据先预先访问一遍，这样部分可能大量访问的数
据就会加载到缓存中。在即将发生大并发访问前手动触发加载缓存不同的key，设置不同的过期时间，让
缓存失效的时间点尽量均匀。

