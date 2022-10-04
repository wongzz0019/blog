package com.xinzhi;
import java.util.Scanner;

/**
 * 主函数
 * @author Bosco
 * @date 2021/6/10
 */
public class Register {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("’exit‘ - 退出注册！");
            System.out.println("请输入用户名：");
            String name = scanner.next();
            if ("exit".equals(name)){
                break;
            }
            System.out.println("请输入用户密码:");
            String password = scanner.next();
            if ("exit".equals(password)){
                break;
            }
            User user = new User(name, password);
            SaveUser saveUser = new SaveUser();
            saveUser.save(user);

        }
    }
}
