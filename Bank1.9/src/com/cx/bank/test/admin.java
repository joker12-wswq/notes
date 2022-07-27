package com.cx.bank.test;

import com.cx.bank.manager.ManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class admin {

    JFrame frame =new JFrame("管理员");  //设置窗体标题

    ManagerImpl managerImpl;
    {
        try {
            managerImpl = ManagerImpl.getManagerImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public admin(){

//        this.setSize(400, 300);         //设置窗体大小
        frame.setLocation(400, 200);     //设置位置

//        Container container = this.getContentPane();
//        container.setLayout(new BorderLayout());

        JButton jButton1 = new JButton("冻结账户");
        JButton jButton2 = new JButton("解冻账户");
        JButton jButton3 = new JButton("返回登录");


        //     背景

        //1.把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
        ImageIcon bg=new ImageIcon("src/img/641-1.jpeg");
        JLabel label=new JLabel(bg);
        label.setSize(bg.getIconWidth(),bg.getIconHeight());
        frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //2.把窗口面板设为内容面板并设为透明、流动布局。
        JPanel pan=(JPanel)frame.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new BorderLayout());
        //3.之后把组件和面板添加到窗口面板就可以；
//        JButton btn=new JButton("测试按钮");
//        pan.add(btn);

        //        标题——上方
        JPanel titlePanel =new JPanel();//创建一个放置标题的面板
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("欢迎您，管理员"));
        pan.add(titlePanel, "North");//加入视图中

        //        按钮底部
        JPanel buttonPanel =new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton3);
        pan.add(buttonPanel, "South");



        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String frozenName = JOptionPane.showInputDialog(null,"请输入需要冻结的用户名:","消息",JOptionPane.PLAIN_MESSAGE);
                boolean f = managerImpl.frozenAccount(frozenName);
                if (f){
                    JOptionPane.showMessageDialog(null, "冻结成功，"+frozenName+"账户已被冻结","消息",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "冻结失败~","消息",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String frozenName = JOptionPane.showInputDialog(null,"请输入需要解结的用户名:","消息",JOptionPane.PLAIN_MESSAGE);
                boolean f = managerImpl.thawAccount(frozenName);
                if (f){
                    JOptionPane.showMessageDialog(null, "冻结成功，"+frozenName+"账户已被解结","消息",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "解冻失败~","消息",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new login();
            }
        });

        frame.setSize(bg.getIconWidth(),bg.getIconHeight());


        frame.setDefaultCloseOperation(3);  //设置可关闭

        frame.setVisible(true);  //设置可见

        frame.setResizable(false);   //设置不可拉伸大小
    }



    public static void main(String[] args) {
        new admin();
    }
}
