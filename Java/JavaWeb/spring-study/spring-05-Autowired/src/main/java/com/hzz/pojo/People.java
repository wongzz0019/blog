package com.hzz.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * @author Bosco
 * @date 2021/11/29
 */
public class People {
    @Autowired  //使用这注解不用写set方法
    @Qualifier(value = "cat11")
    //@Resource(name = "cat11")
    private Cat cat;

    //@Resource
    @Autowired //使用这注解不用写set方法
    private Dog dog;


    private String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
