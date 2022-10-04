package com.hzz.reflection;

/**
 * @author Bosco
 * @date 2021/9/4
 *
 * 获得反射对象
 *
 */
public class Tets01 {
    public static void main(String[] args) throws ClassNotFoundException {
        //通过反射获取类的class对象
        Class c1 = Class.forName("com.hzz.reflection.User");
        System.out.println(c1);

        Class c2 = Class.forName("com.hzz.reflection.User");
        Class c3 = Class.forName("com.hzz.reflection.User");
        Class c4 = Class.forName("com.hzz.reflection.User");

        //一个类在内存中只有一个Class对象
        //一个类被加载后，类的整个结构都会被封装在Class对象中
        //如果hashCode一样，即同一个类
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        System.out.println(c4.hashCode());
    }
}

//实体类：pojp , entity
class User{
    String name;
    int age;

    public User() {
    }

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
