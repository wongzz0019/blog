package com.hzz;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 打印当前系统时间
 */
public class TestSleep3 {

    public static void main(String[] args) {
        localTime();
    }

    public static void localTime(){
        Date startTime = new Date(System.currentTimeMillis());//获取系统当前时间
        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis());//更新当前时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
