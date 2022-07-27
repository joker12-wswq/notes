package com.cx.bank.model;
/**
 * 用户实体类
 * @author 菠菜
 *
 */
public class UserBean {
    private String userName;//用户名
    private String userPassWord;//密码
    private int userId;//用户编号
    private double balance;//用户余额
    private int userFlag;//使用标记
    private int adminFlag;//管理员标记

    public int getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(int adminFlag) {
        this.adminFlag = adminFlag;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return userPassWord;
    }
    public void setPassWord(String userPassword) {
        this.userPassWord = userPassword;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public int getUserFlag() {
        return userFlag;
    }
    public void setUserFlag(int userFlag) {
        this.userFlag = userFlag;
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", userPassWord='" + userPassWord + '\'' +
                ", userId=" + userId +
                ", balance=" + balance +
                ", userFlag=" + userFlag +
                ", adminFlag=" + adminFlag +
                '}';
    }
}
