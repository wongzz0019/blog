# JMeter基本篇

![image-20220526202322255](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220526202322255.png)

## JMeter优缺点

![image-20220526203951819](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220526203951819.png)

## 安装和启动

![image-20220526220824385](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220526220824385.png)

![image-20220526220938277](C:\Users\Bosco\Desktop\GitHub\blog\image-20220526220938277.png)

## 项目简介

![image-20220526221031649](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220526221031649.png)

## 基本使用

![image-20220526223333686](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220526223333686.png)

## 获取所有学院信息的接口

![image-20220527120341789](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527120341789.png)

![image-20220527120958575](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527120958575.png)

![image-20220527121032700](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527121032700.png)





## JMeter线程组相关

![image-20220527120926701](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527120926701.png)

## 线程组并发执行

![image-20220527121835359](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527121835359.png)

## 线程组顺序执行

![image-20220527121923846](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527121923846.png)

## 两个特殊线程组

![image-20220527122540759](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527122540759.png)

![image-20220527122617409](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527122617409.png)

## 线程组常用属性

![image-20220527122927393](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527122927393.png)

## http请求默认值

封装http相同数据，方便使用

![image-20220527123251724](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527123251724.png)

![image-20220527123412834](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527123412834.png)

在http请求中就不用写相同数据了，方便了

## 信息头管理器

**新增修改实现时，提交的数据是JSON格式的，需要声明提交的数据内容类型，才能在JMeter中提交成功**

![image-20220527123647684](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527123647684.png)

![image-20220527123851986](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527123851986.png)

## 参数化

![image-20220527123944117](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527123944117.png)

## 参数化--用户定义的变量

**调用格式：${变量名}**

![image-20220528201357210](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528201357210.png)



![image-20220527124128374](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527124128374.png)

![image-20220527124400889](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527124400889.png)

![image-20220527124445147](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527124445147.png)

## 参数化--CSV数据文件设置

![image-20220527124532386](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527124532386.png)

编写csv文件，存数据

![image-20220527124736594](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527124736594.png)

添加csv数据文件设置

在HTTP请求中修改**消息体数据中的值为{变量名}**

![image-20220527125012810](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527125012810.png)

修改csv数据文件设置参数

![image-20220527125232994](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527125232994.png)

回到线程组，把**永远**勾选上

![image-20220527125327102](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527125327102.png)

可以启动执行了

## 参数化--用户参数

![image-20220527125456905](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527125456905.png)

![image-20220527130145257](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527130145257.png)



添加用户参数

![image-20220527130230910](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527130230910.png)

线程组中设置线程数，**有多少用户就多少线程数**

![image-20220527130326994](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527130326994.png)

在HTTP请求中修改**消息体数据中的值为{变量名}**

![image-20220527130356872](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527130356872.png)

## 参数化--函数

![image-20220527130440317](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220527130440317.png)

### _counter

![image-20220528125540969](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528125540969.png)

![image-20220528125605006](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528125605006.png)

### _Random

![image-20220528125742510](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528125742510.png)



### _time

![image-20220528130001566](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528130001566.png)



![image-20220528130020483](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528130020483.png)



## 直连数据库

![image-20220528130135505](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528130135505.png)

1. 下载数据库驱动jar包
2. JMeter中，在线程组下右键添加 - 配置元件 - JDBC Connection Configuration
3. 查看JDBC Connection Configuration页面配置信息，设置数据库配置项
   - **Variable Name for created pool：**创建的变量名（JDBC Request可调用）
   - **Database URL：**格式为jdbc:mysql:// 数据库IP地址:数据库端口/数据库名称
   - **JDBC Driver class：**下拉选项形式，这里选择com.mysql.jdbc.Driver
   - **Username：**需要连接数据库的用户名
   - **Password：**需要连接数据库的密码
4. 线程组上右键添加 - 取样器 - JDBC Request
5. JDBC Request页面，Query区域编写SQL脚本，Query Type：查用select、增删改用Update，在Query Type上方输入框填写第3步创建的变量名，Variable name 可以是查询结果保存的变量名
6. 添加查看结果树，运行后进行结果查看
7. 添加 **调试取样器**：线程组右键-添加-取样器-调试取样器
   1. 把提取的结构在百度上搜索：在HTTP请求中--填写好协议--服务器名或IP--端口号--请求方法--**路径：/s?word=${val_30} 百度s是搜索，word是关键词，val_30是结果中的变量名**--编码
8. https://www.cnblogs.com/mrgavin/p/12808447.html



## 断言

![image-20220528132339105](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528132339105.png)

### 响应断言

![image-20220528132655639](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528132655639.png)

![image-20220528132830163](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528132830163.png)

![image-20220528132907796](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528132907796.png)

### 大小断言

![image-20220528135134900](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528135134900.png)



![image-20220528135121303](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528135121303.png)

### 断言持续时间

![image-20220528135237286](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528135237286.png)

判断程序的响应时间



# JMeter进阶篇

![image-20220528135448885](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528135448885.png)

逻辑控制器必须当HTTP请求的父亲

## 逻辑控制器if

![image-20220528135631946](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528135631946.png)

第一步：创建好线程组，结果树，HTTP请求

HTTP请求填写好参数内容

![image-20220528203210569](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528203210569.png)

第二步：添加用户定义的变量

![image-20220528203258385](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528203258385.png)

第三步：添加 **if控制器**，注意父子关系

![image-20220528201309866](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528201309866.png)

第四步：填写内容

![image-20220528203458116](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528203458116.png)

最后：**条件成立才执行HTTP请求，不成立不执行**

![image-20220528203551179](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528203551179.png)

## 逻辑控制器forEach

![image-20220528203709288](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528203709288.png)

第一步：创建好线程组，结果树，HTTP请求

HTTP请求： **这里的${val}是ForEach控制器中的输出变量名**

![image-20220528204413685](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528204413685.png)

第二步：添加用户自定义的变量

![image-20220528204535208](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528204535208.png)

第三步：添加ForEach控制器，注意父子关系

![image-20220528204907080](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528204907080.png)



结果

![image-20220528204643873](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528204643873.png)



## 循环控制器

![image-20220528204936884](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528204936884.png)

第一步：操作同上，

![image-20220528205416035](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528205416035.png)

第二步：添加循环控制器，注意父子关系

![image-20220528205520343](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528205520343.png)

最后看结果

## 关联Xpath

## 关联正则表达式提取器

## 跨越线程组取值

## 高并发

HTTP请求参数：

![image-20220528211631421](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528211631421.png)

添加**同步定时器**

![image-20220528211833430](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528211833430.png)

添加 **聚合报告**

![image-20220528211942501](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528211942501.png)



## 高频率

![image-20220528212105465](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528212105465.png)

![image-20220528212125107](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528212125107.png)

添加 **常数吞吐量定时器**

![image-20220528212217708](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528212217708.png)

**每分钟访问次数=访问频率*60**

![image-20220528212314794](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528212314794.png)

![image-20220528212515554](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528212515554.png)

执行，看报告

![image-20220528212541374](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528212541374.png)



## 分布式

![image-20220528212616659](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220528212616659.png)





# ----------分割线---------

# Jmeter元件

![image-20220618161609964](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618161609964.png)

# Jmeter组件

![image-20220618161704466](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618161704466.png)

# 接口自动化脚本过程对应Jmeter哪个元件？？

![image-20220618161810241](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618161810241.png)

# 元件的执行顺序

![image-20220618162435062](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618162435062.png)

其它元件：如果其父节点不是取样器，则其作用域是该元件父节点下的所有后代节点等

![image-20220618162932023](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618162932023.png)

执行顺序：定时器1 - 请求1 - 定时器1 - 定时器2 - 请求2 - 定时器1 - 定时器3 - 请求3

# 线程组分类

![image-20220618182802970](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618182802970.png)

# 线程组参数详解

![image-20220618183555016](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618183555016.png)

- 线程数代表虚拟用户数，用户数越多，负载越大
- 循环次数代表运行时间，次数越多，运行时间越长

# 结果树有乱码

![image-20220618190752044](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618190752044.png)

# 参数化-csv数据文件设置

![image-20220618193651522](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618193651522.png)

![image-20220618195848805](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618195848805.png)

见测试文件里的**JMeter文件** 

# 参数化-函数

![image-20220618200720429](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618200720429.png)

# 参数化总结

![image-20220618200848806](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618200848806.png)

# 断言

![image-20220618202056468](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618202056468.png)

![image-20220618202218718](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618202218718.png)

# 响应断言

![image-20220618203044488](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618203044488.png)

# JSON断言

![image-20220618203550444](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618203550444.png)

![image-20220618204951423](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618204951423.png)

例子：：：

![image-20220618205243656](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618205243656.png)

# 断言持续时间(常用性能测)

![image-20220618205357295](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618205357295.png)

# 断言总结

![image-20220618205600413](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618205600413.png)

**JMeter中可以勾选忽略，忽略判断响应状态码**



# 关联-正则表达式提取器

![image-20220618210709936](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618210709936.png)

![image-20220618210821967](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618210821967.png)

![image-20220618211650289](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618211650289.png)

![image-20220618211658439](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618211658439.png)

![image-20220618211721592](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618211721592.png)

# 关联-XPath提取器

![image-20220618220047489](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618220047489.png)

![image-20220618220537108](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618220537108.png)

![image-20220618220551021](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618220551021.png)

![image-20220618220559356](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618220559356.png)

![image-20220618220511802](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618220511802.png)

# 关联-json提取器

![image-20220618220721641](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618220721641.png)

**练习**

![image-20220618220730908](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618220730908.png)

![image-20220618221216042](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618221216042.png)

![image-20220618221222817](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618221222817.png)

![image-20220618221235394](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618221235394.png)

# 关联-JMeter属性

![image-20220618221506971](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618221506971.png)

![image-20220618221551138](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618221551138.png)

![image-20220618221721535](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618221721535.png)



**步骤：**

![image-20220618222500428](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618222500428.png)

![image-20220618222611634](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618222611634.png)

![image-20220618222543549](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618222543549.png)

![image-20220618222555651](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618222555651.png)

![image-20220618222628938](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618222628938.png)

**要先运行线程组1，再运行线程组2**

![image-20220618222650506](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618222650506.png)



# 关联总结

![image-20220618222858921](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618222858921.png)



# JMeter脚本录制

![image-20220618223143408](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618223143408.png)

![image-20220618223215303](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618223215303.png)





**步骤：：：**

![image-20220618223429204](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618223429204.png)

​	![image-20220618223502113](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618223502113.png)

**其它同上面截图操作一致**



![image-20220618223800277](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618223800277.png)



# 脚本录制过滤设置

![image-20220618224253742](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618224253742.png)



# ==直连数据库使用场景==

![image-20220618224826352](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618224826352.png)

# ==直连数据库操作案例1==

![image-20220618225011512](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618225011512.png)



![image-20220618225040109](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618225040109.png)



![image-20220618225107128](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618225107128.png)

![image-20220618225418899](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618225418899.png)

![image-20220618225519493](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618225519493.png)

![image-20220618225536524](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618225536524.png)

![image-20220618225602208](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618225602208.png)



# ==直连数据库案例2==

![image-20220618225817312](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618225817312.png)

![image-20220618230045008](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618230045008.png)

![image-20220618230206260](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618230206260.png)

![image-20220618230227008](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618230227008.png)

![image-20220618230455875](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618230455875.png)

# ==直连数据库总结==

![image-20220618230736267](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618230736267.png)



# if控制器

![image-20220618231039657](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618231039657.png)

![image-20220618231114261](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618231114261.png)

**方法一：用JS写法：：：**

![image-20220618233501119](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618233501119.png)

![image-20220618233508891](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618233508891.png)

![image-20220618233518931](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618233518931.png)

![image-20220618233538275](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618233538275.png)



**方法二，用函数写法：：：**

![image-20220618233716646](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618233716646.png)



![image-20220618233642575](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618233642575.png)

![image-20220618233734034](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618233734034.png)



![image-20220618233757370](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618233757370.png)



# 循环控制器

![image-20220618234106160](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618234106160.png)

![image-20220618234240013](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220618234240013.png)

# foreach控制器

![image-20220619000612261](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619000612261.png)

![image-20220619000653022](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619000653022.png)



![image-20220619001652081](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619001652081.png)

![image-20220619001702984](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619001702984.png)

![image-20220619001712475](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619001712475.png)

![image-20220619001720757](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619001720757.png)

![image-20220619001730091](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619001730091.png)



# 定时器-同步定时器

![image-20220619001957052](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619001957052.png)

![image-20220619002020445](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619002020445.png)

**步骤：：：**

![image-20220619003207173](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619003207173.png)

![image-20220619003223421](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619003223421.png)

![image-20220619003230341](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619003230341.png)

![image-20220619003236829](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619003236829.png)





![image-20220619003138398](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619003138398.png)



# 定时器-常数吞吐量定时器

![image-20220619003528285](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619003528285.png)

![image-20220619003716827](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619003716827.png)

![image-20220619004404140](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619004404140.png)

![image-20220619004414512](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619004414512.png)

![image-20220619004420560](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619004420560.png)

![image-20220619004437204](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619004437204.png)



![image-20220619004454623](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619004454623.png)



# 定时器-固定定时器

![image-20220619004948210](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619004948210.png)

![image-20220619005012447](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220619005012447.png)

