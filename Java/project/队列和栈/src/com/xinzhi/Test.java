package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/4/12
 */
public class Test {
    public static void main(String[] args){
        Stack<Integer> stack = new Stack();
        stack.put(1);
        stack.put(2);
        stack.put(3);
        stack.put(4);

        System.out.println("弹出了" + stack.pop());
        System.out.println("弹出了" + stack.pop());
        System.out.println("弹出了" + stack.pop());
        System.out.println("弹出了" + stack.pop());
        System.out.println("弹出了" + stack.pop());


        Queue<Integer> queue = new Queue<>();
        queue.put(5);
        queue.put(6);
        queue.put(7);
        queue.put(8);
        queue.put(9);
        System.out.println(queue.take() + "出队了");
        System.out.println(queue.take() + "出队了");
        System.out.println(queue.take() + "出队了");
        System.out.println(queue.take() + "出队了");
        System.out.println(queue.take() + "出队了");
        System.out.println(queue.take() + "出队了");
    }
}
