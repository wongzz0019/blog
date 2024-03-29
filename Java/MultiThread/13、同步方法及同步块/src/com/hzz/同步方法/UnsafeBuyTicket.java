package com.hzz.同步方法;

/**
 * 不安全买票 修改变成安全买票
 * 使用同步方法(synchronized)，安全买票
 * @author Bosco
 */
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();
        
        new Thread(station,"我").start();
        new Thread(station,"黄牛").start();
        new Thread(station,"路人").start();
    }
}

class BuyTicket implements Runnable{

    //票
    private int ticketNums = 10;
    boolean flag = true;
    @Override
    public void run() {
        //买票
        while (flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized 同步方法，锁的是this
     */
    private synchronized void buy() throws InterruptedException {
        //判断是否有票
        if (ticketNums<=0){
            flag = false;
            return;
        }
        //模拟延时
        Thread.sleep(100);
        //买票
        System.out.println(Thread.currentThread().getName()+"拿到"+ticketNums--);
    }
}
