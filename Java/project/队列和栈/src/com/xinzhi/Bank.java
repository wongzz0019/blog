package com.xinzhi;

import java.util.Scanner;

/**
 * 银行取票机
 * @author Bosco
 * @date 2021/4/13
 */
public class Bank {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        Scanner scanner = new Scanner(System.in);
        int starNumber = 1;

        while (true) {

            // 加号
            for (int i = starNumber; i < starNumber + 5; i++) {
                queue.put(i);
            }

            // 消费号码
            while (queue.isNotEmpty()) {
                String name = scanner.next();
                System.out.println(name + "您的号码是" + queue.take());
            }

            starNumber += 5;
        }



    }

}
