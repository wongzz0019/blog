package com.hzz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Bosco
 * @date 2021/9/4
 */
public class Test3 {
    //注解可以显示赋值，如果没有默认值，我们就必须给注解赋值
    @MyAnnotation2(age = 10,name = "哇哈哈")
    public void test(){}

    @MyAnnotation3("王小明") //参数名是value的，直接写赋值
    public void test2(){

    }
}


@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    //注解的参数：参数类型+参数名（）;
    String name() default ""; // default 默认值
    int age();
    int id() default -1; //如果默认值为-1，代表不存在

    String[] schools() default {"北京大学","清华大学"};
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String  value(); //只有一个参数成员，一般用参数名为value
}
