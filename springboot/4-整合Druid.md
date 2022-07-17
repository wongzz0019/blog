# 整合Druid（德鲁伊）数据源

## 1、添加druid依赖

```xml
<!-- druid数据源-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.8</version>
</dependency>
```

## 2、在application.yaml编写数据源参数

```yaml
spring:
  datasource:
    username: root
    password: 123456
    # 假如时区报错，就增加一个时区配置就ok，serverTimezone=UTC
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    #type指定数据源druid
    type: com.alibaba.druid.pool.DruidDataSource

    #SpringBoot默认是不注入这些的，需要自己绑定
    #druid数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

    #配置监控统计拦截的filters，stat：监控统计、log4j：日志记录、wall：防御sql注入
    #如果允许报错，java.lang.ClassNotFoundException: org.apache.Log4j.Properity
    #则导入log4j 依赖就行
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

## 3、新建一个DruidConfig类

```java
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        //new DruidDataSource()会从prefix = "spring.datasource"中获取数据
        return new DruidDataSource();
    }

    //后台监控 : 相当于以前的web.xml, ServletRegistrationBean
    //因为springboot 内置了servlet容器，所有没有web.xml,替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登录，配置账号密码
        HashMap<String, String> initParameters = new HashMap<>(2);
        //增加配置
        initParameters.put("loginUsername","admin");//登录的key是固定的loginUsername
        initParameters.put("loginPassword","123456");//loginPassword
        //允许谁可以访问
        initParameters.put("allow","");
        //设置初始化配置参数
        bean.setInitParameters(initParameters);
        return bean;
    }

    //filter过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求?
        HashMap<String, String> map = new HashMap<>();
        //这些东西不统计
        map.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(map);
        return bean;
    }
}
```

完事~