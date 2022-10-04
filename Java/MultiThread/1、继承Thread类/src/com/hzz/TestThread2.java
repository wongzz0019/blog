package com.hzz;

//练习Thread,实现多线程同步下载图片。

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThread2 extends Thread{

    private String url;     //网络图片地址
    private String name;    //保存的文件名

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // 下载图片线程的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为"+name);
    }

    public static void main(String[] args) {
        TestThread2 p1 = new TestThread2("https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF","图片1.gif");
        TestThread2 p2 = new TestThread2("https://t7.baidu.com/it/u=3435942975,1552946865&fm=193&f=GIF","图片2.gif");
        TestThread2 p3 = new TestThread2("https://t7.baidu.com/it/u=2763645735,2016465681&fm=193&f=GIF","图片3.gif");

        p1.start();
        p2.start();
        p3.start();
    }
}

//下载器
class WebDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
