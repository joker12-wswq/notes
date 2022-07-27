package com.cx.bank.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cx.bank.manager.ManagerImpl;

/**
 * 登录
 */
public class login {

    JFrame frame =new JFrame("登录");
    Container c = frame.getContentPane();//创建视图，获取内容面板
    JLabel userLabel=new JLabel("用户名：");
    JTextField username= new JTextField();
    JLabel passwdLabel=new JLabel("密码：");
    JPasswordField password=new JPasswordField();

    JButton okbutton = new JButton("登录");
    //JButton cancelbttton = new JButton("取消");
    JButton registerbttton = new JButton("注册");



    public  login(){
        setFrame();
        listen();
    }


    public void setFrame() {
        frame.setBounds(600, 200, 300, 220);//设置窗体位置&大小
        c.setLayout(new BorderLayout());//设置视图的布局，边界布局
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
        buttonPanel.add(okbutton);
        //buttonPanel.add(cancelbttton);
        buttonPanel.add(registerbttton);
        c.add(buttonPanel, "South");
    }


    //监听设置
    public void listen(){
        //监听,并输出账号和密码

        //登录按钮
        okbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerImpl managerImpl;
                try {
                    managerImpl = ManagerImpl.getManagerImpl();//拿到业务对象
                    boolean flg = managerImpl.login(username.getText(),String.valueOf(password.getPassword()));
                    if (flg==true) {//用户名、密码正确

                        int f = managerImpl.getAdminFlag();
                        if (f == 0){//普通用户
                            frame.setVisible(false);//如果login函数判断账号密码正确，为了美观，将登录界面变为不可见
                            String logname = username.getText();//获取登录者用户名
                            new Menu(logname);
                        }else {//管理员
                            frame.setVisible(false);//如果login函数判断账号密码正确，为了美观，将登录界面变为不可见
                            new admin();
                        }

                    }else if ("".equals(username.getText())||"".equals(String.valueOf(password.getPassword()))) {
                        //如果账号或密码为空
                        JOptionPane.showMessageDialog(null,"用户名或密码不能为空!","提示",JOptionPane.WARNING_MESSAGE);
                    }
                    else if(flg==false) {
                        //对话框
                        JOptionPane.showMessageDialog(null, "账号或密码输入错了哦","提示",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        //注册按钮
        registerbttton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new register();
            }
        });


    }

    public static void main(String[] args) {

        new login();

    }


}
