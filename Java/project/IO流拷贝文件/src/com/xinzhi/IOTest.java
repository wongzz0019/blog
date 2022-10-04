package com.xinzhi;

import jdk.internal.util.xml.impl.Input;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * @author Bosco
 * @date 2021/5/29
 */
public class IOTest {

    /**
     * mkdirs 创建文件夹
     * @throws Exception
     */
    @Test
    public void fileTest() throws Exception {
        File file = new File("F:/IO流测试/创建文件夹/aaa");
        System.out.println(file.mkdirs());
        //mkdir 和 mkdirs 的区别：mkdir只能创建一层目录，而mkdirs可以创建多层
        //不管什么情况直接用 mkdirs 就好了~
    }


    /**
     * 把一个文件拷贝到另一个地方
     */
    @Test
    public void copyFileTest() {

        // 流的定义 要在try外面定义
        InputStream is = null;
        OutputStream os = null;

        try { //核心代码

            //输入流
            is = new FileInputStream("F:\\IO流测试\\文件拷贝\\a\\000001.TIF");
            // 输出流
            os = new FileOutputStream("F:\\IO流测试\\文件拷贝\\b\\new01.TIF");
            // 桶。入流、出流
            byte[] buffer = new byte[1024*2];
            int len;

            // 输入流。len = 每次读取buffer个数据；当is.read(buffer)返回值为-1时，读取结束
            // .read()一定要穿数据桶（参数 byte） .read(buffer) 谨记！！！
            while ((len = is.read(buffer)) != -1) {

                // 输出流。写入数据，大小为buffer，从第0个开始 直到 len个结束。 一定要这样写
                os.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            /**
             * 无论如何，最终得关闭资源
             * 判断是否为空，不是就close关闭
             */
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拷贝完成！！！");
        }
    }


    /**
     * 字节流读文件内容 （完整版写法）
     */
    @Test
    public void readFileTest() {
        InputStream is = null;
        try {
            is = new FileInputStream("F:\\\\IO流测试\\\\读取文件\\\\aa.txt");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                String s = new String(buffer, 0,len);
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 字节流读文件内容 （简易版写法）
     * @throws Exception
     */
    @Test
    public void readFileTest2() throws Exception {
        InputStream is = new FileInputStream("F:\\IO流测试\\读取文件\\aa.txt");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            String s = new String(buffer, 0, len);
            System.out.println(s);
        }
        is.close();
    }


    /**
     * 字符流读文件内容  最好用于.txt文件
     * @throws Exception
     */
    @Test
    public void readFileTest3() throws Exception {
        Reader reader = new FileReader("F:\\IO流测试\\读取文件\\aa.txt");
        char[] buffer = new char[1024];
        int len;
        while ((len = reader.read(buffer)) != -1){
            String s = new String(buffer, 0, len);
            System.out.println(s);
        }
        reader.close();
    }


    /**
     * 处理流读文件内容。FileReader --- BufferedReader。一行一行读
     */
    @Test
    public void readFileTest4() throws Exception {
        Reader reader = new FileReader("F:\\IO流测试\\读取文件\\aa.txt");
        BufferedReader br = new BufferedReader(reader);

        String str;
        while ((str = br.readLine()) != null){
            System.out.println(str);
        }
        reader.close();
    }


    /**
     * 字节流写入文件  FileOutputStream
     */
    @Test
    public void writeFile() throws Exception {
        // 对一根管道到文件上
        OutputStream fo = new FileOutputStream("F:\\IO流测试\\写入文件\\hello.txt");
        String s = "hello world";
        // 写出去
        fo.write(s.getBytes());

        fo.flush();
        fo.close();

    }

    /**
     * 字符流写入文件  FileWrite
     */
    @Test
    public void writeFile2() throws Exception {
        // 对一根管道到文件上
        Writer w = new FileWriter("F:\\IO流测试\\写入文件\\字符流写入.txt");
        // 写出去
        w.write("这是用字符流的方式写入到txt文档里的！！");

        w.flush();
        w.close();
    }

    /**
     * 打印流和文件追加  追加(不会覆盖)：true   不追加(会覆盖)：false
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // 对一根管道到文件上
        FileWriter w = new FileWriter("F:\\IO流测试\\写入文件\\追加写入.txt", true);
        PrintWriter pw = new PrintWriter(w);

        String flag = null;
        while (!"exit".equals(flag)){
            flag = scanner.next();
            pw.println(flag);

            w.flush();
            pw.flush();
        }
        w.close();
        pw.close();
    }
}