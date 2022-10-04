package com.hzz.pojo;

/**
 * @author Bosco
 * @date 2021/11/28
 */
public class Hello
{
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "hello{" +
                "str='" + str + '\'' +
                '}';
    }
}
