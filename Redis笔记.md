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

