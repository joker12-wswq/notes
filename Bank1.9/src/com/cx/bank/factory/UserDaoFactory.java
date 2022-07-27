package com.cx.bank.factory;

import com.cx.bank.dao.BankUserDao;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/**
 * 工厂层，完成业务层和持久层的动态装配，消除业务层和持久层的耦合性
 * @author 菠菜
 */

public class UserDaoFactory {

    private static UserDaoFactory instance;

    private BankUserDao userDao;

    /**
     * 重写无参的构造方法
     * @throws Exception
     */
    private UserDaoFactory() throws Exception{

        Properties p = new Properties();//创建Properties对象，读取.properties文件

        //new File("xxx.xxx")  只是在内存中创建File文件映射对象,需要“xxx.xxx”文件存在，而并不会在硬盘中创建文件
        //硬盘文件，就是我们常说的文件
        //创建文件字节输入流对象，对文件数据以字节的形式进行读取操作
        //与根据File类对象的所代表的实际文件建立链接创建fileInputStream对象
        FileInputStream fis = new FileInputStream(new File("classInfo.properties"));

        p.load(fis);//读取、加载文件，从输入流中读取属性列表（键和元素对）。
        fis.close();//关闭流

        String className = p.getProperty("className");//通过key获取持久层对象的路径信息
        Class c = Class.forName(className);//通过String找到对应的类，创建反射对象
        Object o = c.newInstance();//创建该类（持久层）的对象

        userDao = (BankUserDao)o;//类实现了接口

    }


    /**
     * 创建工厂对象
     * @return instance
     * @throws Exception
     */
    public static UserDaoFactory getInstance() throws Exception {
        if (instance==null) {
            instance = new UserDaoFactory();
        }
        return instance;
    }


    /**
     * 创建userDao对象
     * @return userDao
     */
    public BankUserDao creatUserDao() {
        return userDao;
    }


}
