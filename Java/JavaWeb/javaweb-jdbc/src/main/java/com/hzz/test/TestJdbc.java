package com.hzz.test;

import org.junit.Test;

import java.sql.*;

/**
 * @author Bosco
 * @date 2021/11/16
 */
public class TestJdbc {
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

        //3.向数据库发送SQL的对象Statement(普通) ：CRUD
        Statement statement = connection.createStatement();

        //4.编写SQL
        //String sql = "select * from users";
        String sql = "delete from users where id =4";

        //受影响的行数，增删改都是用executeUpdate即可
        //int i = statement.executeUpdate(sql);

        //5.执行查询SQL：返回一个ResultSet结果集
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            System.out.println("id="+rs.getInt("id"));
            System.out.println("name="+rs.getString("name"));
            System.out.println("password="+rs.getString("password"));
            System.out.println("email="+rs.getString("email"));
            System.out.println("birthday="+rs.getDate("birthday"));
        }

        //6.关闭连接，释放资源（一定要做） 先开后关
        rs.close();
        statement.close();
        connection.close();
        
    }
}
