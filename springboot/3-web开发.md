自动装配

springboot到底帮我们配置了什么？我们能不能进行修改？能修改哪些东西？能不能扩展？

- xxxAutoConfiguration...向容器中自动配置组件
- xxxProperties：自动配置类，装配配置文件中自定义的一些内容



要解决的问题：

- 导入静态资源
- 首页
- jsp,模板引擎Thymeleaf
- 装配扩展springmvc
- 增删改查
- 拦截器
- 国际化



# 静态资源

1. 在springboot，可以使用以下方式处理静态资源
   - webjars	`localhost:8080/webjars/`
   - public ，static，/**（这是根目录下），resources    `localhost:8080/`
2. 优先级：resources > static(默认) > public



# 首页

**WebMvcAutoConfiguration** 下的 ，观察源码知：在静态资源目录下创建index.html即可

```java
private Resource getIndexHtml(String location) {
   return this.resourceLoader.getResource(location + "index.html");
}
```



# 模板引擎

- 注意：`template/**`下的任何页面都需要Controller来跳转才能访问，不能直接访问

1. 导入依赖

    ```XML
    <!--thymeleaf模版引擎在，都是基于3.x版本开发-->
    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring5</artifactId>
    </dependency>
    <dependency>
        <groupId>org.thymeleaf.extras</groupId>
        <artifactId>thymeleaf-extras-java8time</artifactId>
    </dependency>
    ```

2. 查看源码，在template下使用。将html放在我们的templates目录即可

    ```JAVA
    @ConfigurationProperties(prefix = "spring.thymeleaf")
    public class ThymeleafProperties {
    
        private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
        // 前缀
        public static final String DEFAULT_PREFIX = "classpath:/templates/";
        // 后缀
        public static final String DEFAULT_SUFFIX = ".html";
        ...
    }
    ```

3. 第一次使用，传msg，接受：`th:text="${msg}"`，（thymeleaf独有的语法）

    ```java
    @Controller
    public class ThymeleafController {
    
        @RequestMapping("/thymeleaf")
        public String thy(Model model){
            model.addAttribute("msg","<p>hello Thymeleaf</p>");
            model.addAttribute("users", Arrays.asList("张三","李四","王五"));
            // 后缀默认是 .html
            return "thymeleafTest";
        }
    }
    
    ```

    ```html
    <!DOCTYPE html>
    <!--thymeleaf约束-->
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>thymeleaf</title>
    </head>
    <body>
    <!--所有的html元素都可以被Th接管：th:xx元素名-->
    <!--test=默认是转义文本-->
    <h1 th:text="${msg}"></h1>
    <!--utest=默认是不转义文本-->
    <h1 th:utext="${msg}"></h1>
    <h1>
        <!--遍历往前写item-->
        遍历一 推荐这么使用：
        <h2 th:each="user:${users}" th:text="${user}"></h2><br>
        遍历二：
        <h2 th:each="user:${users}" >[[${user}]]</h2>
    </h1>
    </body>
    </html>
    ```

# SpringMVC扩展

`implements WebMvcConfigurer`，重写一些方法

```java
 /**
  *  扩展SpringMVC：实现WebMvcConfigurer接口,重写某些方法让我们实现一些新的配置
  *      注意：不能再使用@EnableWebMvc，这个方法会让扩展方法失效
  */
 @Configuration
 public class MyMVCConfig implements WebMvcConfigurer {
     /**
      * 视图跳转 访问/ssl，跳转到config.html
      */
     @Override
     public void addViewControllers(ViewControllerRegistry registry) {
         registry.addViewController(
                 "/ssl").setViewName("config");
     }
 }
```



# Springboot_web 简单案例

## 员工管理系统

## 1.准备工作

1. 导入静态依赖

   - 页面放在==templates==目录下
   - css、js、img等放在==static==目录下

2. 编写实体类

   - 部门实体

     ```java
     //部门表
     public class Department {
         private Integer id;
         private String departmentName;
     	//有参、无参、get、set、toString
     }
     ```

   - 员工实体

     ```java
     public class Employee {
         private Integer id;
         private String lastName;
         private String email;
         private Integer gender; //0:女  1:男
         private Department department;
         private Date birth;
     
         public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
             this.id = id;
             this.lastName = lastName;
             this.email = email;
             this.gender = gender;
             this.department = department;
             //让其在内部默认生成日期
             this.birth = new Date();
         }
         //无参、get、set、toString
     }
     ```

3. dao层  （这里模拟数据库）

   - 部门dao

     ```java
     @Repository
     public class DepartmentDao {
         //模拟数据库中的数据
         private static Map<Integer, Department> departments = null;
         static {
             departments = new HashMap<>(); //创建一个部门表
     
             departments.put(101,new Department(101,"教学部"));
             departments.put(102,new Department(102,"市场部"));
             departments.put(103,new Department(103,"教研部"));
             departments.put(104,new Department(104,"运营部"));
             departments.put(105,new Department(105,"后勤部"));
         }
     
         //获得所有部门信息
         public Collection<Department> getDepartments(){
             return departments.values();
         }
     
         //通过id得到部门
         public Department getDepartmentsById(Integer id){
             return departments.get(id);
         }
     }
     ```

   - 员工dao

     ```java
     @Repository
     public class EmployeeDao {
     
         //模拟数据库中的数据
         private static Map<Integer, Employee> employees = null;
     
         //员工有所属的部门
         @Autowired
         private DepartmentDao departmentDao;
         static {
             employees = new HashMap<>(); //创建一个员工表
     
             employees.put(1001,new Employee(1001,"AA","A212122@qq.com",0,new Department(101,"教学部")));
             employees.put(1002,new Employee(1002,"BB","B212122@qq.com",1,new Department(102,"市场部")));
             employees.put(1003,new Employee(1003,"CC","C212122@qq.com",0,new Department(103,"教研部")));
             employees.put(1004,new Employee(1004,"DD","D212122@qq.com",1,new Department(104,"运营部")));
             employees.put(1005,new Employee(1005,"EE","E212122@qq.com",0,new Department(105,"后勤部")));
         }
     
         //主键自增
         private static Integer initId = 1006;
         //增加一个员工
         public void save(Employee employee){
             if (employee.getId()==null){
                 employee.setId(initId++);
             }
     
             employee.setDepartment(departmentDao.getDepartmentsById(employee.getDepartment().getId()));
     
             employees.put(employee.getId(),employee);
         }
     
         //查询全部员工信息
         public Collection<Employee> getAll(){
             return  employees.values();
         }
     
         //通过id查询员工
         public Employee getEmployeeById(Integer id){
             return employees.get(id);
         }
     
         //通过id删除员工
         public void delete(Integer id){
             employees.remove(id);
         }
     }
     ```

## 2.首页实现

- 静态资源的<html>标签里加入`xmlns:th="http://www.thymeleaf.org`

- 首页配置：所有页面的静态资源都需要使用thymeleaf接管。==url用@{}。@{/cssdashboard.css} : /根目录(resources-static)== 

## 3.页面国际化

1. 编写国际化配置文件

   - 在resources目录下创建文件i18n ---》再创建==login.properties、login_zh_CN.properties、login_en_US.properties==配置文件

     ![image-20220101124936389](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220101124936389.png)

     ![image-20220101125059106](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220101125059106.png)

     

2. 在核心配置文件==application.yaml==中配置绑定国际化文件的真实位置  （xxx.properties的写法是 spring.messages.basename=i18n.login）

   ```yaml
   spring:
     #绑定国际化配置文件的真实位置
     messages:
       basename: i18n.login
   ```

   

3. 在index.html页面中修改需要国际化显示的标签 messages用 ==#{ }==

   ![image-20220101125803112](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220101125803112.png)

   ![image-20220101130338041](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220101130338041.png)

   

4. 需要在项目中进行按钮中英切换，我们需要自定义一个组件`LocaleResolver`

   1. 在按钮切换处改写url

      ![image-20220101131857883](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220101131857883.png)

   2. 在config包下创建一个MyLocaleResolver的国际化解析请求类组件

      ```java
      public class MyLocaleResolver implements LocaleResolver {
      
          //解析请求
          @Override
          public Locale resolveLocale(HttpServletRequest request) {
              //获取请求中的语言参数
              String language = request.getParameter("l");
              //如果没有就是要默认的
              Locale locale = Locale.getDefault();
              //如果请求的链接携带了国际化的参数
              if (!StringUtils.isEmpty(language)){
                  //把参数分割为数组  zh_CN
                  String[] split = language.split("_");
                  //国家，地区
                  locale = new Locale(split[0],split[1]);
              }
              return locale;
          }
          @Override
          public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
          }
      }
      
      ```

   3. 记得将自己写的组件配置到spring容器  `@Bean`

      ![image-20220102150013835](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220102150013835.png)

   

## 4.登录和注销功能实现

### 4.1、登录功能

1. 在controller包中创建一个LoginController类

   ```java
   @Controller
   public class LoginController {
       @RequestMapping("/user/login")
       public String login(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model,
                           HttpSession session){
   
           //具体业务
           if (!StringUtils.isEmpty(username) && "123456".equals(password)){
               session.setAttribute("loginUser",username);
               return "redirect:/main.html";
           }else {
               model.addAttribute("msg","用户名或密码错误");
               return "index";
           }
       }
   }
   ```

2. 前端修改表单跳转路径

   ```html
   <form class="form-signin" th:action="@{/user/login}">
   ```

### 4.2、注销功能

1. 在LoginController类编写代码

   ```java
   @RequestMapping("/user/logout")
   public String logout(HttpSession session){
       session.invalidate();
       return "redirect:/index.html";
   }
   ```



## 5.拦截器

1. 在config包中创建LoginHandlerInterceptor 实现`HandlerInterceptor`接口

   ```java
   public class LoginHandlerInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
   
           //登录成功后，有用户的session
           Object loginUser = request.getSession().getAttribute("loginUser");
   
           if (loginUser == null){ //没有登录
               request.setAttribute("msg","没有权限，请先登录");
               request.getRequestDispatcher("/index.html").forward(request,response);
               return false;
           }else {
               return true;
           }
       }
   }
   ```

2. 在==MyMvcConfig类==中重写实现我们的拦截器类

   ```java
   //拦截器
       @Override
       public void addInterceptors(InterceptorRegistry registry) {
           registry.addInterceptor(new LoginHandlerInterceptor())
                   .addPathPatterns("/**").excludePathPatterns("/","/index.html","/user/login")
                   .excludePathPatterns("/css/*","/img/*","/js/*");
       }
   ```



## 6.展示员工列表

自己看代码

![image-20220102152337253](C:\Users\Bosco\Desktop\GitHub\blog\springboot\img\image-20220102152337253.png)

## 7.增加员工实现

自己看代码

## 8.删除及404处理

删除自己看代码

**404、500处理**：在==templates==目录下创建一个`error`目录，将页面放进去就行了