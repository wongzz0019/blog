# JSON 和 JavaScript 对象互转

## 要实现从JSON字符串转换为JavaScript 对象，使用 JSON.parse() 方法：

```javascript
var obj = JSON.parse('{"a": "Hello", "b": "World"}');
//结果是 {a: 'Hello', b: 'World'}
```



## 要实现从JavaScript 对象转换为JSON字符串，使用 JSON.stringify() 方法：

```javascript
var json = JSON.stringify({a: 'Hello', b: 'World'});
//结果是 '{"a": "Hello", "b": "World"}'
```



## 代码测试

1. 新建一个module ，springmvc-05-json ， 添加web的支持

2. 在web目录下新建一个 json-1.html ， 编写测试内容

   ```html
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>JSON_秦疆</title>
   </head>
   <body>
    
   <script type="text/javascript">
       //编写一个js的对象
       var user = {
           name:"秦疆",
           age:3,
           sex:"男"
       };
       //将js对象转换成json字符串
       var str = JSON.stringify(user);
       console.log(str);
       
       //将json字符串转换为js对象
       var user2 = JSON.parse(str);
       console.log(user2.age,user2.name,user2.sex);
    
   </script>
    
   </body>
   </html>
   ```

3. 在IDEA中使用浏览器打开，查看控制台输出！ 

   ![image-20211215111944177](C:\Users\Bosco\AppData\Roaming\Typora\typora-user-images\image-20211215111944177.png)





# Controller返回JSON数据

- Jackson应该是目前比较好的json解析工具了

- 当然工具不止这一个，比如还有阿里巴巴的 fastjson 等等。

- 我们这里使用Jackson，使用它需要导入它的jar包；

  ```xml
  <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
  <dependency>
     <groupId>com.fasterxml.jackson.core</groupId>
     <artifactId>jackson-databind</artifactId>
     <version>2.9.8</version>
  </dependency>
  ```

- 配置SpringMVC需要的配置

  - （web.xml）

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
    
       <!--1.注册servlet-->
       <servlet>
           <servlet-name>SpringMVC</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <!--通过初始化参数指定SpringMVC配置文件的位置，进行关联-->
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc-servlet.xml</param-value>
           </init-param>
           <!-- 启动顺序，数字越小，启动越早 -->
           <load-on-startup>1</load-on-startup>
       </servlet>
    
       <!--所有请求都会被springmvc拦截 -->
       <servlet-mapping>
           <servlet-name>SpringMVC</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
    
       <filter>
           <filter-name>encoding</filter-name>
           <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
           <init-param>
               <param-name>encoding</param-name>
               <param-value>utf-8</param-value>
           </init-param>
       </filter>
       <filter-mapping>
           <filter-name>encoding</filter-name>
           <url-pattern>/</url-pattern>
       </filter-mapping>
    
    </web-app>
    ```

  - springmvc-servlet.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/mvc
           https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    
       <!-- 自动扫描指定的包，下面所有注解类交给IOC容器管理 -->
       <context:component-scan base-package="com.kuang.controller"/>
    
       <!-- 视图解析器 -->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
             id="internalResourceViewResolver">
           <!-- 前缀 -->
           <property name="prefix" value="/WEB-INF/jsp/" />
           <!-- 后缀 -->
           <property name="suffix" value=".jsp" />
       </bean>
    
    </beans>
    ```

  - 我们随便编写一个User的实体类，然后我们去编写我们的测试Controller；

    ```java
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class User {
        private int id;
        private String name;
      private int age;
    }
    ```

  - 这里我们需要两个新东西，一个是@ResponseBody，一个是ObjectMapper对象，我们看下具体的用法

    编写一个Controller；

    ```java
    @Controller
    public class UserController {
    @RequestMapping("/j1")
     @ResponseBody//他就不会走视图解析器，会直接返回一个 字符串
     public String json1() throws JsonProcessingException {
         //jackson,ObjectMapper
         ObjectMapper mapper = new ObjectMapper();
    
         //创建一个对象
         User user = new User(1, "秦疆一号", 12);
         //System.out.println(user);
    
         String str = mapper.writeValueAsString(user);
         return str;
     }
    ```

  - 此时输入中文会产生乱码

    ```java
    //produces:指定响应体返回类型和编码
    @RequestMapping(value = "/json1",produces = "application/json;charset=utf-8")
    ```

  - 配置Tomcat ， 启动测试一下！http://localhost:8080/json1

    ![image-20220717164412138](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20220717164412138.png)

【注意：使用json记得处理乱码问题】



## 代码优化

**乱码统一解决**

上一种方法比较麻烦，如果项目中有许多请求则每一个都要添加，可以通过Spring配置统一指定，这样就不用每次都去处理了！

我们可以在springmvc的配置文件上添加一段消息StringHttpMessageConverter转换配置！

```xml
<mvc:annotation-driven>
   <mvc:message-converters register-defaults="true">
       <bean class="org.springframework.http.converter.StringHttpMessageConverter">
           <constructor-arg value="UTF-8"/>
       </bean>
       <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
           <property name="objectMapper">
               <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                   <property name="failOnEmptyBeans" value="false"/>
               </bean>
           </property>
       </bean>
   </mvc:message-converters>
</mvc:annotation-driven>
```

**返回json字符串统一解决**

- @ResponseBody解决（每个方法都得加，不建议使用）

  ```java
  @Controller
  public class UserController {
     //produces:指定响应体返回类型和编码
     @RequestMapping(value = "/json1")
     @ResponseBody
     public String json1() throws JsonProcessingException {
         //创建一个jackson的对象映射器，用来解析数据
         ObjectMapper mapper = new ObjectMapper();
         //创建一个对象
         User user = new User(1, "秦疆一号", 12);
         //将我们的对象解析成为json格式
         String str = mapper.writeValueAsString(user);
         //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
         return str;
    }
  }
  ```

- **@RestController**（直接加到类上即可）

```java
@RestController
public class UserController {
   @RequestMapping(value = "/j1")
   public String json1() throws JsonProcessingException {
       //创建一个jackson的对象映射器，用来解析数据
       ObjectMapper mapper = new ObjectMapper();
       //创建一个对象
       User user = new User(1, "秦疆一号", 12);
       //将我们的对象解析成为json格式
       String str = mapper.writeValueAsString(user);
       return str;
  }

}
```



## 测试集合输出

增加一个新的方法

```java
@RequestMapping("/j2")
public String json2() throws JsonProcessingException {

    //创建一个jackson的对象映射器，用来解析数据
    ObjectMapper mapper = new ObjectMapper();
    //创建一个对象
   	User user1 = new User(1, "秦疆1号", 12);
    User user2 = new User(2, "秦疆2号", 12);
    User user3 = new User(3, "秦疆3号", 12);
    User user4 = new User(4, "秦疆4号", 12);
    User user5 = new User(5, "秦疆5号", 12);
    List<User> list = new ArrayList<User>();
    list.add(user1);
    list.add(user2);
    list.add(user3);
    list.add(user4);
    list.add(user5);
    //将我们的对象解析成为json格式
    String str = mapper.writeValueAsString(list);
    return str;
}
```

运行结果 : 十分完美，没有任何问题！

![image-20220717164546756](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20220717164546756.png)



## 输出时间对象

- 增加一个新的方法

  ```java
  @RequestMapping("/j3")
  public String json3() throws JsonProcessingException {
  
     ObjectMapper mapper = new ObjectMapper();
  
     //创建时间一个对象，java.util.Date
     Date date = new Date();
     //将我们的对象解析成为json格式
     String str = mapper.writeValueAsString(date);
     return str;
  }
  ```

- 运行结果 :

  ![image-20220717164752070](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20220717164752070.png)

- 默认日期格式会变成一个数字，是1970年1月1日到当前日期的毫秒数！

- Jackson 默认是会把时间转成timestamps形式

**解决方案：取消timestamps形式 ， 自定义时间格式**

```java
@RequestMapping("/j3")
public String json4() throws JsonProcessingException {

   ObjectMapper mapper = new ObjectMapper();

   //不使用时间戳的方式
   mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
   //自定义日期格式对象
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   //指定日期格式
   mapper.setDateFormat(sdf);

   Date date = new Date();
   String str = mapper.writeValueAsString(date);

   return str;
}
```

运行结果 : 成功的输出了时间！

![image-20220717164714343](C:\Users\Bosco\Desktop\GitHub\blog\spring\img\image-20220717164714343.png)

**抽取为工具类**

**如果要经常使用的话，这样是比较麻烦的，我们可以将这些代码封装到一个工具类中；我们去编写下**

```java
public class JsonUtils {
    public static String getJson(Object object) {
        return getJson(object,"yyyy-MM-dd HH:mm:ss");
    }
    public static String getJson(Object object, String dateFormat) {
        ObjectMapper mapper = new ObjectMapper();

        //java自定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        //sdf.format(date)

        // 使用ObjectMapper 来格式化输出
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        mapper.setDateFormat(sdf);

        try {
            //ObjectMapper,时间解析后的默认格式为：TImestamp.时间戳
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
```

我们使用工具类，代码就更加简洁了！

```java
@RequestMapping("/j3")
    public String json3(){

        Date date = new Date();

        return JsonUtils.getJson(date,"yyyy-MM-dd HH:mm:ss");
    }
}
```

大功告成！完美！

### 8.7、FastJson

- fastjson.jar是阿里开发的一款专门用于Java开发的包，
- 实现json对象与JavaBean对象的转换，
- 实现JavaBean对象与json字符串的转换，
- 实现json对象与json字符串的转换。
- 实现json的转换方法很多，最后的实现结果都是一样的。

fastjson 的 pom依赖！

```xml
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>fastjson</artifactId>
   <version>1.2.60</version>
</dependency>
12345
```

**fastjson 三个主要的类：**

1. JSONObject 代表 json 对象
   - JSONObject实现了Map接口, 猜想 JSONObject底层操作是由Map实现的。
   - JSONObject对应json对象，通过各种形式的get()方法可以获取json对象中的数据，也可利用诸如size()，isEmpty()等方法获取"键：值"对的个数和判断是否为空。其本质是通过实现Map接口并调用接口中的方法完成的。
2. JSONArray 代表 json 对象数组
   - 内部是有List接口中的方法来完成操作的。
3. JSON代表 JSONObject和JSONArray的转化
   - JSON类源码分析与使用
   - 仔细观察这些方法，主要是实现json对象，json对象数组，javabean对象，json字符串之间的相互转化。

**代码测试，我们新建一个FastJsonDemo 类**

```java
@RequestMapping("/j4")
    //@ResponseBody//他就不会走视图解析器，会直接返回一个 字符串
    public String json4(){

        User user1 = new User(1, "秦疆1号", 12);
        User user2 = new User(2, "秦疆2号", 12);
        User user3 = new User(3, "秦疆3号", 12);
        User user4 = new User(4, "秦疆4号", 12);
        User user5 = new User(5, "秦疆5号", 12);

        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);

        System.out.println("*******Java对象 转 JSON字符串*******");
        String str1 = JSON.toJSONString(list);
        System.out.println("JSON.toJSONString(list)==>"+str1);
        String str2 = JSON.toJSONString(user1);
        System.out.println("JSON.toJSONString(user1)==>"+str2);

        System.out.println("\n****** JSON字符串 转 Java对象*******");
        User jp_user1=JSON.parseObject(str2,User.class);
        System.out.println("JSON.parseObject(str2,User.class)==>"+jp_user1);

        System.out.println("\n****** Java对象 转 JSON对象 ******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println("(JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("name"));

        System.out.println("\n****** JSON对象 转 Java对象 ******");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);

        return JSON.toJSONString(list);
    }

```

这种工具类，我们只需要掌握使用就好了，在使用的时候在根据具体的业务去找对应的实现。和以前的commons-io那种工具包一样，拿来用就好了！