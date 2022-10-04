package com.hzz.test;

import java.sql.*;

/**
 * @author Bosco
 * @date 2021/11/16
 */
public class TestJdbc2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        //useUnicode=true&characterEncoding=utf-8  解决中文乱码
        String url="jdbc:mysql://localhost:3306/jdbc_study?useUnicode=true&characterEncoding=utf-8";
        String username="root";
        String password="123456";

        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.连接数据库,代表数据库    DriverManager驱动管理  connection代表数据库，数据库所有操作她都能做
        Connection connection = DriverManager.getConnection(url, username, password);

        //3.编写SQL
        String sql = "insert into users(id, name, password, email, birthday) VALUES (?,?,?,?,?)";

        //4.预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,5);//给第一个占位符? 的赋值为1
        preparedStatement.setString(2,"黄宗钊");//给第二个占位符? 的赋值为黄宗钊
        preparedStatement.setString(3,"123456");//给第三个占位符? 的赋值为123456
        preparedStatement.setString(4,"hzz@qq.com");//给第四个占位符? 的赋值为hzz@qq.com
        preparedStatement.setDate(5,new Date(new java.util.Date().getTime()));//给第五个占位符? 的赋值为new Date(new java.util.Date().getTime()))

        //5.执行SQL
        int i = preparedStatement.executeUpdate();

        if (i>0){
            System.out.println("插入成功！！");
        }

        //6.关闭连接，释放资源（一定要做） 先开后关
        preparedStatement.close();
        connection.close();
    }
}
