package com.cx.bank.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.*;

import com.cx.bank.manager.ManagerImpl;

import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;
/**
 * 用户主页
 * @author
 * Menu是一个 菜单类，也是最为底层的一个类
 * 提供各个功能的按钮
 * 此类未使用布局， 所以使用坐标固定了各个标签和按钮的位置
 */

public class Menu extends JFrame implements ActionListener{


    JButton jb1, jb2,jb4,jb5,jb7,jb8;  //创建按钮
    JLabel jlb1, jlb2, jlb3;   //标签

    String logname;//存储登录者的用户名
    String welcome = "欢迎您:";

    public Menu(String logname) {
        this();//先执行无参的构造方法
        this.logname = logname;
//    	this.welcome = welcome + logname;
    }

    public Menu()
    {
        jb1 = new JButton("查询");
        jb2 = new JButton("存款");

        jb4 = new JButton("转账");
        jb5 = new JButton("取款");

        jb7 = new JButton("操作明细");
        jb8 = new JButton("退出");



        jlb1 = new JLabel("快乐作死银行");
        jlb1.setFont(new   java.awt.Font("Dialog",   1,   23)); //设置字体类型， 是否加粗，字号
        jlb2 = new JLabel(welcome);
        jlb2.setFont(new   java.awt.Font("Dialog",   1,   20));
        jlb3 = new JLabel("请您选择服务");
        jlb3.setFont(new   java.awt.Font("Dialog",   1,   22));

        jb1.addActionListener(this);   //事件监听
        jb2.addActionListener(this);

        jb4.addActionListener(this);
        jb5.addActionListener(this);

        jb7.addActionListener(this);
        jb8.addActionListener(this);


        this.setTitle("银行管理管理系统");  //设置窗体标题
        this.setSize(450, 500);         //设置窗体大小
        this.setLocation(400, 200);     //设置位置
        this.setLayout(null);           //设置布局，不采用布局

        //设置按钮的位置和大小
        jb1.setBounds( 0,50,90,60);
        jb2.setBounds( 0,150,90,60);

        jb4.setBounds( 354,50,90,60);
        jb5.setBounds( 354,150,90,60);


        jb7.setBounds(354,350,90,60);
        jb8.setBounds(0,350,90,60);

        //设置标签的位置和大小
        jlb1.setBounds(150,120,150,50);
        jlb2.setBounds(190,160,150,50);
        jlb3.setBounds(150,250,150,50);

        this.add(jb1);   //加入窗体
        this.add(jb2);

        this.add(jb4);
        this.add(jb5);

        this.add(jb7);
        this.add(jb8);

        this.add(jlb1);
        this.add(jlb2);
        this.add(jlb3);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  //设置可关闭

        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }

    public boolean isDouble(String s) {
        Pattern pattern = Pattern.compile("[+-]?\\d+(.\\d+)?");
        return pattern.matcher(s).matches();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        try {
            ManagerImpl managerImpl = ManagerImpl.getManagerImpl();
            int f = managerImpl.getUserFlag();//是否冻结标志

            if (e.getActionCommand()=="查询")
            {
                //String order = e.getActionCommand();
                double money = managerImpl.inquiry();
                JOptionPane.showMessageDialog(null, "您当前余额为:"+money,"消息",JOptionPane.INFORMATION_MESSAGE);
            }

            else if (e.getActionCommand()=="存款")
            {
                if (f == 1){//没有冻结
                    String saveMoney = JOptionPane.showInputDialog(null,"请输入存款金额:","消息",JOptionPane.PLAIN_MESSAGE);
                    if (isDouble(saveMoney)) {
                        double money = Double.parseDouble(saveMoney);
                        if (money <0) {
                            String msg = "存款余额不能为负数！";
                            throw new InvalidDepositException(msg);
                        }
                        managerImpl.deposit(money);
                        JOptionPane.showMessageDialog(null, "存款成功，您当前余额为:"+managerImpl.inquiry(),"消息",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "您输入的不是数字!","消息",JOptionPane.WARNING_MESSAGE);
                    }
                }else {//被冻结
                    JOptionPane.showMessageDialog(null, "您的账户已被冻结，无法使用该功能!","消息",JOptionPane.WARNING_MESSAGE);
                }
            }



            else if (e.getActionCommand()=="取款")
            {
                if (f == 1){
                    String takeMoney = JOptionPane.showInputDialog(null,"请输入取款金额:","消息",JOptionPane.PLAIN_MESSAGE);

                    if (isDouble(takeMoney)) {
                        double money = Double.parseDouble(takeMoney);
                        if (money > managerImpl.inquiry()) {
                            String msg = "取款超出余额，余额不足!";
                            throw new InvalidDepositException(msg);
                        }
                        managerImpl.withdrawals(money);
                        JOptionPane.showMessageDialog(null, "取款成功，您当前余额为:"+managerImpl.inquiry(),"消息",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "您输入的不是数字!","消息",JOptionPane.WARNING_MESSAGE);
                    }
                }else {//被冻结
                    JOptionPane.showMessageDialog(null, "您的账户已被冻结，无法使用该功能!","消息",JOptionPane.WARNING_MESSAGE);
                }

            }


            else if (e.getActionCommand()=="转账")
            {
                if (f == 1){
                    String account = JOptionPane.showInputDialog(null,"请输入您的账户名称:","消息",JOptionPane.PLAIN_MESSAGE);
                    String toAccount = JOptionPane.showInputDialog(null,"请输入需要转入的账户名称:","消息",JOptionPane.PLAIN_MESSAGE);
                    String toMoney = JOptionPane.showInputDialog(null,"请输入转账金额:","消息",JOptionPane.PLAIN_MESSAGE);
                    if (isDouble(toMoney)) {
                        double money = Double.parseDouble(toMoney);
                        double balance = managerImpl.inquiry();
                        if (balance < money) {
                            JOptionPane.showMessageDialog(null,"账户余额低于转账金额！","消息",JOptionPane.WARNING_MESSAGE);
                        }

                        String msg = managerImpl.transfer(account, toAccount, money);
                        if (msg == "该账户不存在"||msg == "请不要自己转给自己!") {
                            JOptionPane.showMessageDialog(null,msg,"消息",JOptionPane.WARNING_MESSAGE);
                        }else {
                            JOptionPane.showMessageDialog(null, msg+"您当前余额为:"+managerImpl.inquiry(),"消息",JOptionPane.INFORMATION_MESSAGE);
                        }

                    }else {
                        JOptionPane.showMessageDialog(null, "您输入的金额不是数字!","消息",JOptionPane.WARNING_MESSAGE);
                    }
                }else {//被冻结
                    JOptionPane.showMessageDialog(null, "您的账户已被冻结，无法使用该功能!","消息",JOptionPane.WARNING_MESSAGE);
                }
            }


            else if (e.getActionCommand()=="退出")
            {
                managerImpl.exitSystem(logname);
            }

            else if (e.getActionCommand()=="操作明细")
            {
                new log();
            }
        } catch (InvalidDepositException e2) {
            // TODO: handle exception
            System.out.println(e2.getMessage());
            JOptionPane.showMessageDialog(null, e2.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }catch (AccountOverDrawnException e2) {
            // TODO: handle exception
            System.out.println(e2.getMessage());
            JOptionPane.showMessageDialog(null, e2.getMessage(),"消息",JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}

