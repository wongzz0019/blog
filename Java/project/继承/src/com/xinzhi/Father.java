package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/22
 */
public class Father {

    // 父类的静态字段（属性）
    public static String NAME = "老王";

    /**
     * 父类的静态代码块
     * 类加载的时候就会调用
     * 类加载的时机：第一次主动使用就会加载这个类，把他加载到内存
     */
    static {
        System.out.println("父类的静态字段:" + NAME);
        System.out.println("父类的静态代码块");
    }

    // 父类的非静态属性
    private String hobby = "泡妞";

    // 父类的非静态代码块
    {
        System.out.println("父类的非静态属性：" + hobby);
        System.out.println("父类的非静态代码块");
    }


    // 有参构造
    public Father(String hobby) {
        this.hobby = hobby;
        System.out.println("父类的构造方法");
    }

    public void smoke(){
        System.out.println("我爱抽中华！");
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * 在Father类里重写equals方法
     * instanceof
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Father father;
        // instanceof作用: 判断 左边obj 是不是 右边Father(class类) 的一个实例，返回boolean类型
        // 某个实例对象 instanceof 某个类名
        // 左边的实例对象不能是基础数据类型
        if (obj instanceof Father){
            father = (Father) obj;
            if (this.getHobby().equals(father.getHobby())){
                return true;
            }
        }
        return false;
    }
}
