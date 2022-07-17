# IOC创建对象的方式

1. 使用无参构造创建对象，默认！

2. 假设我们要使用有参构造创建对象。

   1. 下标赋值

      ```xml
      <!-- 第二种：通过类型创建，不建议使用!!!-->
      <bean id="user" class="com.hzz.pojo.User">
          <constructor-arg index="0" value="小明"/>
      </bean>
      ```

   2. 类型

      ```xml
      <!-- 第二种：通过类型创建，不建议使用!!!-->
      <bean id="user" class="com.hzz.pojo.User">
          <constructor-arg type="java.lang.String" value="小明"/>
      </bean>
      ```

   3. 参数名

      ```xml
      <!--第三种：直接通过参数名来设置  -->
      <bean id="user" class="com.hzz.pojo.User">
          <constructor-arg name="name" value="小明"/>
      </bean>
      ```

   **总结：在配置文件加载的时候，容器中管理的对象就已经初始化了！**