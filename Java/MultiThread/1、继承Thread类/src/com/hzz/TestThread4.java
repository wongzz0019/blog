package com.hzz;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Bosco
 * @date 2021/8/24
 */
public class TestThread4 implements Runnable{

    private String name;
    private String url;
    public TestThread4(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader2 webDownloader2 = new WebDownloader2();
        webDownloader2.down(url,name);
        System.out.println("下载了"+name);

    }

    public static void main(String[] args) {
        TestThread4 t1 = new TestThread4("https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF","1.gif");
        TestThread4 t2 = new TestThread4("https://t7.baidu.com/it/u=3435942975,1552946865&fm=193&f=GIF","2.gif");
        TestThread4 t3 = new TestThread4("https://t7.baidu.com/it/u=2763645735,2016465681&fm=193&f=GIF","3.gif");

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
}

//下载器
class WebDownloader2{
    public void down(String url,String name) {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("下载器出现问题--");
        }
    }
}
