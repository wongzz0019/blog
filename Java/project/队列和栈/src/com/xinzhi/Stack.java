package com.xinzhi;

/**
 * 栈  先进后出（FILO）
 * @author Bosco
 * @date 2021/4/12
 */
public class Stack<T> {

    /**
     * 维护一个超级数组
     */
    private SuperArray<T> superArray = new SuperArray<>();

    /**
     * 入（压）栈的方法
     * @param date
     */
    public void put(T date){
        superArray.add(date);
    }

    /**
     * 弹栈的方法
     * @return
     */
    public T pop(){

        if (superArray.size() > 0) {
            T temp = superArray.get(superArray.size() - 1);
            superArray.delete(superArray.size() - 1);
            return temp;
        }
        return null;
    }
}
