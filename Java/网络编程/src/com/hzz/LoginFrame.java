package com.hzz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * 用户登录程序
 * @author Bosco
 */

public class LoginFrame extends Frame implements ActionListener{

    public static void main(String[] args) {
        new LoginFrame();
    }

    // 全局变量——两个文本域和两按钮
    JTextField jTextFieldOne = new JTextField(8);
    JPasswordField jPasswordField = new JPasswordField(8);
    JButton jButtonOne = new JButton("提交");
    JButton jButtonTwo = new JButton("重置");

    public LoginFrame(){
        // 面板一
        JPanel jPanelOne = new JPanel();
        JLabel jLabelOne = new JLabel("用户名");
        jPanelOne.add(jLabelOne);
        jPanelOne.add(jTextFieldOne);

        // 面板二
        JPanel jPanelTwo = new JPanel();
        JLabel jLabelTwo = new JLabel("密码");
        jPanelTwo.add(jLabelTwo);
        jPanelTwo.add(jPasswordField);

        // 面板三
        JPanel jPanelThree = new JPanel();
        jPanelThree.add(jButtonOne);
        jPanelThree.add(jButtonTwo);


        // 注册监听
        this.addWindowListener(new MyWindowListener());
        jPasswordField.addKeyListener(new MyPasswordField());
        jTextFieldOne.addKeyListener(new MyKeyAdapter());
        jButtonOne.addActionListener(this);
        jButtonTwo.addActionListener(this);

        this.setLayout(new GridLayout(3,1));
        this.add(jPanelOne);
        this.add(jPanelTwo);
        this.add(jPanelThree);
        this.setTitle("用户登录窗口");
        this.setSize(350,150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String st1 = jTextFieldOne.getText();
        char[] cha = jPasswordField.getPassword();
        String st2 = new String(cha);
        if ("提交".equals(e.getActionCommand())) {
            if ("admin".equals(st1) && "123456".equals(st2)) {
                JDialog frame = new JDialog();
                frame.setTitle("弹出新窗口");
                frame.setBounds(this.getX(),this.getY(),350,150);
                JLabel jl = new JLabel();
                frame.getContentPane().add(jl);
                jl.setText("恭喜您，登录成功");
                jl.setVerticalAlignment(JLabel.CENTER);
                jl.setHorizontalAlignment(JLabel.CENTER);
                frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                frame.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null, "账号密码不正确！");
            }
        } else{
            jTextFieldOne.setText("");
            jPasswordField.setText("");
        }
    }

    static class MyPasswordField extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            String key="0123456789";
            if(!key.contains(e.getKeyChar() + "")){
                e.consume();
            }
        }
    }

    static class MyWindowListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == KeyEvent.VK_ENTER){
                jPasswordField.requestFocusInWindow();
            }
        }
    }

}
