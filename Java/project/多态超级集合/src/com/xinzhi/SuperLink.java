package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/4/7
 */
public class SuperLink<T> implements Super<T>{

    private Node head;
    private int size;
    /**
     * 添加数据
     * @param data
     */
    @Override
    public void add(T data){
        // 1.让这个node变成头
        Node newHead = new Node(data,null);

        // 2.让新的头指向旧的头
        newHead.setNext(head);

        // 3.让新的头变成旧的头
        head = newHead;
        size++;

    }
    @Override
    public void delete(int index) {
        if (index == 0) {
            Node node = getNode(index);
            head = node.getNext();
        } else {
            Node node = getNode(index - 1);
            node.setNext(node.getNext().getNext());
        }
        size--;
    }

    // 查找
    @Override
    public T get(int index){
        return (T)(getNode(index).getData());
    }

    @Override
    public void update(int index,T newDate){
        Node node = getNode(index);
        node.setData(newDate);
    }

    private Node getNode(int index){
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }
    @Override
    public void print(){
        Node node = head;
        while (node != null){
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

    @Override
    public int size() {
        return size;
    }


    private static class Node<T> {

        /**
         * 具体存入的数据
         */
        private T data;

        /**
         * 指向下一个节点的引用
         */
        private Node next;

        public Node() {

        }

        public Node(T data,Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
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

}
