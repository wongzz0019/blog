package com.xinzhi;


/**
 * @author Bosco
 * @date 2021/3/29
 */
public class SuperLink {

    private Node head;
    /**
     * 添加数据
     * @param data
     */
    public void add(Integer data){
        // 1.让这个node变成头
        Node newHead = new Node(data,null);

        // 2.让新的头指向旧的头
        newHead.setNext(head);

        // 3.让新的头变成旧的头
        head = newHead;

    }

    public void delete(int index) {
        if (index == 0) {
            Node node = getNode(index);
            head = node.getNext();
        } else {
            Node node = getNode(index - 1);
            node.setNext(node.getNext().getNext());
        }
    }

    public Integer get(int index){
        return getNode(index).getData();
    }

    public void update(int index,Integer newDate){
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

    public void print(){
        Node node = head;
        while (node != null){
            System.out.println(node.getData());
            node = node.getNext();
        }
    }
}

