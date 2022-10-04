package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/29
 */

public class Node {

    /**
     * 具体存入的数据
     */
    private Integer data;

    /**
     * 指向下一个节点的引用
     */
    private Node next;

    public Node() {

    }

    public Node(Integer data,Node next) {
        this.data = data;
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                '}';
    }
}
