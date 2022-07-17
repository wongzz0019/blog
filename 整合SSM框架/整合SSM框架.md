# 整合SSM框架

1. 数据库搭建

2. 导入相关依赖 和 资源过滤

3. 建立基本结构和配置框架

4. mybatis层

   - 数据库配置文件database.properties

   - IDEA关联数据库

   - 编写MyBatis的核心配置文件

     ```xml
     <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE configuration
             PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
             "http://mybatis.org/dtd/mybatis-3-config.dtd">
     <configuration>
         <!-- 配置数据源，交给spring去做   -->
     
         <!--别名-->
         <typeAliases>
             <package name="com.hzz.entity"/>
         </typeAliases>
     
     
         <mappers>
             <package name="com.hzz.mapper"/>
         </mappers>
     
     </configuration>
     ```

   - 编写数据库对应的实体类（entity）

   - 编写Mapper层的Mapper接口

   - 编写接口对应的Mapper.xml文件

   - 编写Service层的接口和实现类

5. spring层

   - Spring整合MyBatis层：spring-dao.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
             http://www.springframework.org/schema/beans/spring-beans.xsd
             http://www.springframework.org/schema/context
             https://www.springframework.org/schema/context/spring-context.xsd">
     
         <!-- 配置整合mybatis -->
         <!-- 1.关联数据库文件 -->
         <context:property-placeholder location="classpath:db.properties"/>
     
         <!-- 2.数据库连接池 -->
         <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
             <property name="driverClass" value="${jdbc.driver}"/>
             <property name="jdbcUrl" value="${jdbc.url}"/>
             <property name="user" value="${jdbc.username}"/>
             <property name="password" value="${jdbc.password}"/>
             <property name="maxPoolSize" value="30"/>
             <property name="minPoolSize" value="10"/>
             <property name="autoCommitOnClose" value="false"/>
             <property name="checkoutTimeout" value="10000"/>
             <property name="acquireRetryAttempts" value="2"/>
         </bean>
     
         <!-- 3.配置SqlSessionFactory对象 -->
         <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
             <!-- 注入数据库连接池 -->
             <property name="dataSource" ref="dataSource"/>
             <!--绑定mybatis的配置文件-->
             <property name="configLocation" value="classpath:mybatis-config.xml"/>
         </bean>
     
         <!-- 4.配置扫描Dao接口包，动态实现Dao接口注入到spring容器中 -->
         <!--解释 ：https://www.cnblogs.com/jpfss/p/7799806.html-->
         <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
             <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
             <property name="basePackage" value="com.hzz.mapper"/>
         </bean>
     </beans>
     ```

   - Spring整合Service层：spring-service.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
             http://www.springframework.org/schema/beans/spring-beans.xsd
             http://www.springframework.org/schema/context
             https://www.springframework.org/schema/context/spring-context.xsd">
     
         <!--1.指定要扫描的包，这个包下的注解就会生效-->
         <context:component-scan base-package="com.hzz.service"/>
         <!--2.配置声明式事务 ，用set方法注入数据源dataSource-->
         <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
             <!--注入数据源-->
             <property name="dataSource" ref="dataSource"/>
         </bean>
         <!--3.aop事务支持-->
         <aop:config>
             <aop:pointcut id="txPointCut" expression="execution(* com.hzz.dao.*.*(..))"/>
             <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
         </aop:config>
     </beans>
     ```

   - Spring整合MVC层：spring-servlet

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:mvc="http://www.springframework.org/schema/mvc"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
                                http://www.springframework.org/schema/beans/spring-beans.xsd
                                http://www.springframework.org/schema/mvc
                                https://www.springframework.org/schema/mvc/spring-mvc.xsd
                                http://www.springframework.org/schema/context
                                https://www.springframework.org/schema/context/spring-context.xsd">
     
         <!-- 配置SpringMVC -->
         <!--1.开启SpringMVC注解驱动 ，如果要使用jackson需要在这配置json乱码问题-->
         <mvc:annotation-driven/>
         <!-- 2.静态资源默认servlet配置-->
         <mvc:default-servlet-handler/>
         <!-- 3.自动扫描包 controller，扫描web相关的bean -->
         <context:component-scan base-package="com.hzz.controller"/>
         <!--4.试图解析器-->
         <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
             <property name="prefix" value="/WEB-INF/view/"/>
             <property name="suffix" value=".jsp"/>
         </bean>
     </beans>
     ```

   - Spring配置整合文件：applicationContext.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
             http://www.springframework.org/schema/beans/spring-beans.xsd">
     
         <import resource="spring-dao.xml"/>
         <import resource="spring-service.xml"/>
         <import resource="springmvc-servlet.xml"/>
     </beans>
     ```

     

6. 编写web.xml 

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
       <servlet>
           <servlet-name>springmvc</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:applicationContext.xml</param-value>
           </init-param>
           <load-on-startup>1</load-on-startup>
       </servlet>
       <servlet-mapping>
           <servlet-name>springmvc</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
       <filter>
           <filter-name>encodingFilter</filter-name>
           <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
           <init-param>
               <param-name>encoding</param-name>
               <param-value>utf-8</param-value>
           </init-param>
       </filter>
       <filter-mapping>
           <filter-name>encodingFilter</filter-name>
           <url-pattern>/*</url-pattern>
       </filter-mapping>
       <session-config>
           <session-timeout>15</session-timeout>
       </session-config>
   </web-app>
   ```

7. Controller层

   编写业务