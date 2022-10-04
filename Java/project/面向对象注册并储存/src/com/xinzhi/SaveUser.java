package com.xinzhi;

import java.io.*;

/**
 * 注册信息写进文件
 * @author Bosco
 * @date 2021/6/10
 */
public class SaveUser {
    public void save(User user) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            // 创建文件
            File file = new File(Constants.BASE_PATH + Constants.USER_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            // 把管放到文件上
            fw = new FileWriter(file,true);
            pw = new PrintWriter(fw);
            // 写进文件
            pw.append(user.getUsername()).append("---").append(user.getPassword()).println();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
}