# MySQL

## DDL

### 数据库操作

- 查看所有数据库：show database；
- 查看数据库中所有的表：show tables;
- 创建数据库：create database 数据库名；
- 使用哪个数据库：use 数据库名；
- 当前在哪个数据库：select database()；
- 删除数据库：drop database 数据库名；

### 表操作-新增

- 添加字段

  **ALTER TABLE** 表名 **ADD** 字段名 类型(长度) [COMMENT 注释] [约束];

  alter table emp add nickname varchar(20) comment '用户名'

### 表操作-修改

- 修改数据类型

  ALTER TABLE 表名 **MODIFY** 字段名 新数据类型(长度)；

  alter table emp modify username varchar(30) comment '用户名'

- 修改字段名和字段类型

  ALTER TABLE 表名 **CHANGE** 旧字段名 新字段名 类型(长度) [COMMENT 注释] [约束];

  alter table emp change nickname username varchar(30)

- 修改表名

  ALTER TABLE 表名 **RENAME TO** 新表名

  alter table emp rename to employee；

### 表操作-删除

- 删除字段

  ALTER TABLE 表名 **DROP** 字段名；

  alter table emp drop username；

- 删除表

  DROP TABLE 表名

- 删除表中所有数据

  **TRUNCATE** TABLE 表名；

  truncate table emp；



## DQL

**DQL语法：**

​	SELECT 

​					字段列表

​	FROM

​					表名列表

​	WHERE

​					条件列表

​	GROUP BY（分组查询）

​					分组字段列表

​	HAVING

​					分组后条件列表

​	ORDER BY（排序查询）

​					排序字段列表    ASC（默认，升序，小到大）/DESC（降序，大到小）

​	LIMIT

​					分页参数

- 聚合函数 ：count、max 、min 、avg、sum



## 连接查询

join 对比

![image-20220105154445303](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20220105154445303.png)



```sql
/*
连接查询
    如需要多张数据表的数据进行查询,则可通过连接运算符实现多个查询
内连接 inner join (交集)
    查询两个表中的结果集中的交集
外连接 outer join
    左外连接 left join
        (以左表作为基准,右边表来一一匹配,匹配不上的,返回左表的记录,右表以NULL填充)
    右外连接 right join
        (以右表作为基准,左边表来一一匹配,匹配不上的,返回右表的记录,左表以NULL填充)
        
等值连接和非等值连接
自连接
*/
 
-- 查询参加了考试的同学信息(学号,学生姓名,科目编号,分数)
SELECT * FROM student;
SELECT * FROM result;
 
/*思路:
(1):分析需求,确定查询的列来源于两个类,student  result,连接查询
(2):确定使用哪种连接查询?(内连接)
确定交叉点（两表中哪个数据相同）

-- join （连接的表） on （判断的条件） 连接查询
-- where 等值查询
*/
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
 
-- 右连接(也可实现) Right Join
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
RIGHT JOIN result r
ON r.studentno = s.studentno
 
-- 左连接 (查询了所有同学,不考试的也会查出来) Left Join
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
LEFT JOIN result r
ON r.studentno = s.studentno
 
-- 查一下缺考的同学(左连接应用场景)
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
LEFT JOIN result r
ON r.studentno = s.studentno
WHERE StudentResult IS NULL
 
-- 思考题:查询参加了考试的同学信息(学号,学生姓名,科目名,分数) 3表查询	
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON sub.subjectno = r.subjectno

-- 要查询哪些数据 select...
-- 从哪几个表中查 FROM 表  xxx  Join 连接的表 on 交叉条件
-- 假设存在一种多张表查询，慢慢来，先查询两张表然后再慢慢增加
```



## 视图 VIEW

==创建视图：create view 视图名 as 查询语句；==

CREATE VIEW view_bk AS 

SELECT  id bID, bankNo bkn, uName username FROM bank;

真实数据表中有4个字段，而视图创建了3个字段。

视图创建的字段，需要啥字段就拿数据表中的啥字段。
