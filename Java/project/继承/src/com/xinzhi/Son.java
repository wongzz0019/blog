package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/22
 */
public class Son extends Father {

    // 子类的静态字段（属性）
    public static String NAME = "不知道父亲名字";

    /**
     * 子类的静态代码块
     * 类加载的时候就会调用
     * 类加载的时机：第一次主动使用就会加载这个类，把他加载到内存
     */
    static {
        System.out.println("子类的静态字段:" + NAME);
        System.out.println("子类的静态代码块");
    }

    // 子类的非静态属性
    private String hobby = "泡妞";

    // 子类的非静态代码块
    {
        System.out.println("子类的非静态属性：" + hobby);
        System.out.println("子类的非静态代码块");
    }

    /**
     * 构造子类必须先构造一个父类，而且必须首先构造父类，必须是在第一行
     */
    public Son(){
        super("打牌");
        System.out.println("子类的构造器");
    }

    public void swim(){
        // super超类，即父类，指向Fatehr
        super.smoke();
        System.out.println("我的爱好是" + getHobby());
    }

    // 注解，这是个重写的方法。 儿子继承了父亲的方法，但不想要就重写这方法
    @Override
    public void smoke(){
        System.out.println("我爱抽椰树！");
    }
}
