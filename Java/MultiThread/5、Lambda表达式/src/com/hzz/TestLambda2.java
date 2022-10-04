package com.hzz;

/**
 * @author Bosco
 * @date 2021/8/27
 */
public class TestLambda2 {

    public static void main(String[] args) {
        ILove love = null;

        //1.lambda表达式简化
         love = (int a,int b)->{
            System.out.println("i love you--"+a);
        };

        //简化1.参数类型.   用这个！！！
        love = (a,b)-> {
            System.out.println("i love you--"+a);
        };


        //总结：
            //lambda表达式只能有一行代码的情况下才能简化成一行，如果有多行，那就用代码块包裹。
            //前提是接口为函数接口 --- 只有仅有一个接口方法
            //多个参数也可以去掉参数类型，要去掉都去掉
        love.love(5,20);
    }
}

interface ILove{
    void love(int a,int b);
}
