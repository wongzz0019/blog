package com.hzz.pojo;

/**
 * @author Bosco
 * @date 2021/11/28
 */
public class User {
    private String name;

    public User(String name){
        this.name = name;
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
