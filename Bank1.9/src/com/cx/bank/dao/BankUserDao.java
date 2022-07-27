package com.cx.bank.dao;

import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;



/**
 * 持久层接口
 * @author 菠菜
 *
 */
public interface BankUserDao {
    boolean findByName(String name);//根据用户名查找是否存在该用户
    void insertUser(UserBean user);//插入数据到数据库中
    boolean findUser(UserBean user);//按用户名和密码查找用户
    double findMoneyByName(String accountB);//根据用户名查找转入账户的余额
    void saveMoney(String logname,MoneyBean moneyBean) ;//存储money
    void saveLog(String logType,double logMount,int userId);//将用户id、操作类型、操作金额存入t_log表中
    int findIdByName(String logname);//根据用户名查找id
    Object [][] getLogMessage(int userId);//获取log日志信息
    boolean  setUserFlag(int f,int id);//设置用户是否冻结标志
    int getUserFlag(int id);//获取用户是否冻结标志
    int getAdminFlag(int id);//获取用户是否为管理员标志
}
