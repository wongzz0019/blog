package com.hzz;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

//线程创建方式三：实现Callable接口
/**
Callable的好处
1.可以定义返回值
2.可以抛出异常
 * @author Bosco
 */
public class TestCallable implements Callable<Boolean> {

    private String url;     //网络图片地址
    private String name;    //保存的文件名

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public Boolean call() {
        WebDown webDown = new WebDown();
        webDown.down(url, name);
        System.out.println("下载了文件名为：" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, ExecutionException {
        TestCallable t1 = new TestCallable("https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF", "1.gif");
        TestCallable t2 = new TestCallable("https://t7.baidu.com/it/u=3435942975,1552946865&fm=193&f=GIF", "2.gif");
        TestCallable t3 = new TestCallable("https://t7.baidu.com/it/u=2763645735,2016465681&fm=193&f=GIF", "3.gif");

        //创建执行服务：
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        //获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        //关闭服务
        ser.shutdownNow();
    }
}

//下载器
class WebDown {
    public void down(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("下载器出现问题--");
        }
    }
}

