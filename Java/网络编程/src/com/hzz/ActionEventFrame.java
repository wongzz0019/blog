package com.hzz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 改变窗口颜色
 * @author Bosco
 * @date 2021/9/10
 */
public class ActionEventFrame extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new ActionEventFrame();
    }

    // 全局变量——面板二
    JPanel jPanelTwo = new JPanel();
    public ActionEventFrame(){
        // 面板一
        JPanel jPanelOne = new JPanel();
        JButton jButtonRed = new JButton("红色");
        JButton jButtonGreed = new JButton("绿色");
        JButton jButtonBlue = new JButton("蓝色");
        jPanelOne.add(jButtonRed);
        jPanelOne.add(jButtonGreed);
        jPanelOne.add(jButtonBlue);

        // 注册监听
        jButtonRed.addActionListener(this);
        jButtonGreed.addActionListener(this);
        jButtonBlue.addActionListener(this);

        this.add(jPanelOne,BorderLayout.NORTH);
        this.add(jPanelTwo);
        this.setTitle("ActionEvent事件监听器");
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton) e.getSource();
        if ("红色".equals(jb.getText())){
            jPanelTwo.setBackground(Color.red);
        }
        if ("绿色".equals(jb.getText())){
            jPanelTwo.setBackground(Color.green);
        }
        if ("蓝色".equals(jb.getText())){
            jPanelTwo.setBackground(Color.blue);
        }
    }
}
