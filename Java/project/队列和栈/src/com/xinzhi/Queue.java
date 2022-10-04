package com.xinzhi;

/**
 * 队列  先进先出（FIFO）
 * @author Bosco
 * @date 2021/4/13
 */
public class Queue<T> {

    /**
     * 维护一个超级数组
     */
    private SuperArray<T> superArray = new SuperArray<>();

    /**
     * 入队的方法
     * @param date
     */
    public void put(T date){
        superArray.add(date);
    }

    /**
     * 出队的方法
     * @return
     */
    public T take(){
        if (superArray.size() > 0) {
            T temp = superArray.get(0);
            superArray.delete(0);
            return temp;
        }
        return null;
    }

    public boolean isNotEmpty() {
        return superArray.size() > 0;
    }
}
