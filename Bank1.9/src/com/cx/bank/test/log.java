package com.cx.bank.test;

import com.cx.bank.manager.ManagerImpl;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用户操作日志
 */
public class log   {
    ManagerImpl managerImpl;

    {
        try {
            managerImpl = ManagerImpl.getManagerImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public log() {
        JFrame jFrame  = new JFrame("操作明细");
        jFrame.setSize(600,800);//大小
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);//居中
        jFrame.setResizable(false);
        //创建面板
        JPanel panel = new JPanel();
        //表格
        //表格
        Object[] columnNames = {"用户名","操作类型","操作金额"};//表头
//        Object[][] data = new Demo05().table();//JDBC获取表内容
        Object[][] data = managerImpl.getLog();//JDBC获取表内容
        JTable table = new JTable(data, columnNames);//表头和内容放入表
//        JTable table = new JTable();//表头和内容放入表
        table.setRowHeight(40);//设置行高
        table.setPreferredScrollableViewportSize(new Dimension(550,650));//整张表的大小
        JScrollPane scrollPane = new JScrollPane(table);//给表整一个滑轮
        panel.add(scrollPane);//把表放入面板
        //组件
        JButton button = new JButton("[返回]");
        //添加
        panel.add(button);
        //事件
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new UI12().showUI(Person.name, Person.myid);
                jFrame.dispose();
            }
        });
        //显示
        jFrame.setContentPane(panel);
        jFrame.setVisible(true);
    }


//    public static void main(String[] args) {
//        new log();
//    }
}
