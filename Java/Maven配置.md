# 1、Maven

Maven项目架构管理工具

核心思想：约定大约配置

## 1.1、配置环境变量

我的电脑-属性----》系统属性----》环境变量

在我们的系统环境变量中

配置如下配置：

- M2_HOME	maven目录下的bin目录
- MAVEN_HOME      maven的目录
- 在系统的path中配置     %MAVEN_HOME%\bin

![image-20211121111012378](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20211121111012378.png)

测试Maven是否安装成功，保证必须配置完毕！

## 1.2、阿里云镜像

- 镜像：mirrors

  - 作用：加速我们下载

- 国内用阿里云的镜像

  在settings.xml中配置

```xml
	 <mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>*,!jeecg,!jeecg-snapshots</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url> 
    </mirror>
```

## 1.3、本地仓库

建一个本地仓库：localRepository

maven目录中建一个文件夹maven-repo

在settings.xml中配置文件夹maven-repo的路径

```xml
  	<localRepository>F:\apache-maven-3.6.1\maven-repo</localRepository>
```

## 1.4、约定大于配置

我们之后可能会遇到我们写的配置文件，无法被导出或者生效的问题，解决方案（静态资源过滤）：

```java

<!-- 在build中配置resources,来防止我们资源导出失败的问题，使用静态资源过滤 -->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```

