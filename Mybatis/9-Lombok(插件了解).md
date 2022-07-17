# 使用步骤：

1. 在IDEA中安装Lombok插件

2. 在项目中导入lombok的jar包

   ```xml
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <version>1.16.18</version>
   </dependency>
   ```

3. 在实体类上加注解

   ```java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class User{
       private int id;
       private String name;
       private String pwd;
   }
   ```

说明：

```xml
@Data	无参构造、get、set、tostring、hashcode，equlas
@AllArgsConstructor
@NoArgsConstructor
@EqualsArgsHashCode
@ToString
@Getter
```

