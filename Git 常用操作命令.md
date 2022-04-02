# Git 常用操作命令

1. 查看git配置信息

   git config --list

2. 账号配置（修改也是这样）

   git config --global user.name ”用户名“

   git config --global user.password “密码”

   git config --global user.email “邮箱”

3. 初始化 仓库（需在文件夹内）会出现.git文件

   git init

4. 提交至暂存区/缓冲区

   git add *

   git add README

5. 提交暂存区到本地仓库

   git commit -m '提交信息'

6. 查看状态

   git status -s

7. 查看历史操作记录 ，嫌看得眼花缭乱，可加`--pretty=oneline`参数

   git log 

8. 查看每一次命令

   git reflog

9. 版本穿梭前进回退

   git reset --hard commit_id          HEAD指向当前版本，HEAD^上一个版本，HEAD^^上上版本，HEAD~100往上100个版本，

10. 分支管理

   git branch	查看分支

   git branch -r	查看远程分支

   git branch aa	创建分支aa

   git checkout aa	切换到aa分支

   git checkout -b cc	在aa分支分出一个cc分支

   git merge aa	合并分支（需回到主分支再执行）

   git branch -d aa	删除aa分支	

11. 忽略文件

    在主目录下创建“.gitgnore”文件，此文件有如下规则：

    1. 忽略文件中的空行或以井号（#）开始的行将会被忽略。

    2. 可以使用Linux通配符。例如：星号（*）代表任意多个字符，问号（？）代表一个字符，方括号（[abc]）代表可选字符范围，大括号（{string1,string2,...}）代表可选的字符串等。

    3. 如果名称的最前面有一个感叹号（!），表示例外规则，将不被忽略。

    4. 如果名称的最前面是一个路径分隔符（/），表示要忽略的文件在此目录下，而子目录中的文件不忽略。

    5. 如果名称的最后面是一个路径分隔符（/），表示要忽略的是此目录下该名称的子目录，而非文件（默认文件或目录都忽略）。

       ```
       #为注释
       *.txt        #忽略所有 .txt结尾的文件,这样的话上传就不会被选中！
       !lib.txt     #但lib.txt除外
       /temp        #仅忽略项目根目录下的TODO文件,不包括其它目录temp
       build/       #忽略build/目录下的所有文件
       doc/*.txt    #会忽略 doc/notes.txt 但不包括 doc/server/arch.txt	
       ```



**#本地仓库**

git init   初始化

git add .	提交到暂存区

git status -s	查看状态

git log/reflog	查看历史操作/每一次的命令

git reset commit_id	版本回退或前进

git commit -m '提交信息'	提交到本地仓库

**#生成密钥**

ssh-keygen -t rsa -C "邮箱"

cat ~/.ssh/id_rsa.pub	查看密钥

ssh -T git@github.com	验证是否成功

ssh -T git@gitee.com	

**#推送已有项目**

git remote add 别名 项目链接

git push -u 别名 master

**#克隆已有仓库**

git clone 项目链接