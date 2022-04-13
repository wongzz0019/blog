# spring

## IOC是什么

- IOC即控制反转，对象由spring创建、管理、装配

## 依赖注入的方法

- 构造方法注入
- set方法注入

## @Autowired和@Resource区别

- @Autowired通过byType的方式实现
- @Resource默认byName方式实习的，如果找不到名，则通过byType实现
- 当**@Autowired**自动装配的环境比较复杂，可使用@Qualifier(values="xxx")指定bean
- @Value 用于注入基本数据类型和String类型

## AOP是什么

- AOP是面向切面编程的设计思想，是在不修改源代码的情况下给程序动态统一添加额外功能的一种技术
- 通常用在事务，日志等

## AOP相关术语

- **切面（aspect）：是一个类**
- **通知（advice）：类中的一个方法**
- **切入点（pointcut）：类方法执行的地点**
- **目标（target）：被通知对象**
- 代理（Proxy）：向目标对象应用通知之后创建的对象。
- 连接点（JointPoint）：与切入点匹配的执行点。

## Spring框架中都用到了哪些设计模式？

- 工厂模式：BeanFactory
- 单例模式
- 代理模式

## spring常见注解

- @Bean：注解在方法上，返回值为一个bean
- @Autowired：通过byType实现装配
- @Resource：默认byName，再byType
- @Qualifier：指定一个bean，配合@Autowired用
- @Value：属性值注入
- @Repository：用在持久层
- @Service：用在业务层
- @Controller：用在控制层
- @RestController：放在控制层的全局位置，返回json类型
- @RequestMapper：映射web请求
- @GetMapper：请求
- @PostMapper：响应
- @Component：组件
- @Configuration：配置类
- @ResponseBody：标明是form提交的数据
- @PathVariable：接收路径参数 