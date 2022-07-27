package com.cx.bank.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cx.bank.manager.ManagerImpl;

/***
 * 注册
 */
public class register {

    JFrame frame =new JFrame("注册");
    Container c = frame.getContentPane();//创建视图
    JLabel userLabel=new JLabel("用户名：");
    JTextField username= new JTextField();

    JLabel passwdLabel=new JLabel("密码：");
    JPasswordField password=new JPasswordField();
    //JButton okbutton = new JButton("登录");
    JButton back = new JButton("返回登录");
    JButton ok = new JButton("注册");


    public register() {
        // TODO Auto-generated constructor stub
        setFrame();
        addListener();
    }


    public void setFrame() {
        frame.setBounds(600, 200, 300, 220);//设置窗体位置&大小
        c.setLayout(new BorderLayout());//设置视图的布局
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置❌功能
        Init();//初始化，把控件放在布局里
        frame.setVisible(true);//设置窗体可见
        frame.setResizable(false);//设置窗口固定
    }

    public void Init(){
//        标题——上方
        JPanel titlePanel =new JPanel();//创建一个放置标题的面板
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("欢迎"));
        c.add(titlePanel, "North");//加入视图中
//        输入框——中间
        JPanel inputPanel =new JPanel();
        inputPanel.setLayout(null);
        userLabel.setBounds(50, 20, 50, 20);//标签位置
        passwdLabel.setBounds(50, 60, 50, 20);
        inputPanel.add(userLabel);
        inputPanel.add(passwdLabel);
        username.setBounds(110, 20, 120, 20);
        password.setBounds(110, 60, 120, 20);
        inputPanel.add(username);
        inputPanel.add(password);
        c.add(inputPanel, "Center");
//        按钮底部
        JPanel buttonPanel =new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        //buttonPanel.add(okbutton);
        buttonPanel.add(back);
        buttonPanel.add(ok);
        c.add(buttonPanel, "South");
    }

    //监听
    private void addListener() {
        //注册监听
        ok.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {//账号密码书写不规范或没输入就会提示·

                                     ManagerImpl managerImpl;
                                     try {
                                         managerImpl = ManagerImpl.getManagerImpl();
                                         boolean reg = managerImpl.register(username.getText(), String.valueOf(password.getPassword()));
                                         if (reg == false) {
                                             //System.out.println("该用户已存在，请重新注册");
                                             JOptionPane.showMessageDialog(null,"该用户已存在，请重新注册!","提示",JOptionPane.WARNING_MESSAGE);
                                         }else if ("".equals(username.getText())||"".equals(String.valueOf(password.getPassword()))) {
                                             //如果账号或密码为空
                                             JOptionPane.showMessageDialog(null,"用户名或密码不能为空!","提示",JOptionPane.WARNING_MESSAGE);
                                         }else {
                                             //System.out.println("请登录!");
                                             JOptionPane.showMessageDialog(null,"注册成，请登录!","提示",JOptionPane.INFORMATION_MESSAGE);
                                         }
                                     }
                                     catch (Exception e1) {
                                         // TODO Auto-generated catch block
                                         e1.printStackTrace();
                                     }//拿到业务对象


                                 }
                             }

        );
        //返回监听
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new login();
            }
        });
    }

}
