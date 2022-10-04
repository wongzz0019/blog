package com.hzz.pojo;

/**
 * @author Bosco
 * @date 2021/11/29
 */
public class UserT {
    private String name;

    public UserT(){
        System.out.println("UserT被创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void show(){
        System.out.println("name="+name);
    }

}
