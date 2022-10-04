package com.hzz.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Bosco
 * @date 2021/11/16
 */
public class TestJdbc3 {
    public static void main(String[] args) {
        //配置信息
        //useUnicode=true&characterEncoding=utf-8  解决中文乱码
        String url="jdbc:mysql://localhost:3306/jdbc_study?useUnicode=true&characterEncoding=utf-8";
        String username="root";
        String password="123456";


        Connection connection = null;

        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.连接数据库,代表数据库
            connection = DriverManager.getConnection(url, username, password);

            //3.通知数据库开启事务，false 开启!!!
            connection.setAutoCommit(false);

            String sql1 = "update account set money = money-100 where name = 'a'";
            connection.prepareStatement(sql1).executeUpdate();

            //制造错误
            int i = 1/0;

            String sql2 = "update account set money = money+50 where name = 'b'";
            connection.prepareStatement(sql2).executeUpdate();

            connection.commit();//以上两天SQL都执行成功了，就提交事务！
            System.out.println("success");
        }catch (Exception e){
            try{
                //如果出现异常，就通知数据库回滚事务
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
