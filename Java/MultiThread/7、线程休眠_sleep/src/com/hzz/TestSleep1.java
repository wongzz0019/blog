package com.hzz;

/**
 * 模拟网络延时：放大问题的发生性
 */
public class TestSleep1 implements Runnable{

    //票数
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true){
            if (ticketNums <= 0){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"--拿到了第"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {
        TestSleep1 ticket = new TestSleep1();
        new Thread(ticket,"小明").start();
        new Thread(ticket,"老师").start();
        new Thread(ticket,"黄牛").start();
    }
}
