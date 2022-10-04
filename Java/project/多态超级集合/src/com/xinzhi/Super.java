package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/4/7
 */
public interface Super<T> {

    /**
     * 添加数据
     * @param data
     */
    void add(T data);

    void delete(int index);

    T get(int index);

    void update(int index,T newDate);

    void print();

    int size();

    //void say();
}
