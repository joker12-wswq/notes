
package com.cx.bank.manager;


import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

/**
 * @author 菠菜
 * @version 1.2
 * 业务层接口
 */

public interface ManagerInterface {
    boolean register(String name,String psd);//注册
    boolean login(String name,String psd);//登录
    void deposit(double saveMoney) throws InvalidDepositException;//存款
    void withdrawals(double takeMoney) throws AccountOverDrawnException;//取款
    double inquiry();//查询余额
    String transfer(String account,String toAccount,double toMoney);//转账
    void exitSystem(String logname);//退出系统
    Object[][] getLog();//根据获取log日志
    int getUserFlag();//获取用户是否冻结标志
    int getAdminFlag();//获取用户是否为管理员标志
    boolean frozenAccount(String name);//冻结账户
    boolean thawAccount(String name);//解冻账号
}
