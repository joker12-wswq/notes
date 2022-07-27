
package com.cx.bank.model;

/**
 * @author 菠菜
 *
 */
public class MoneyBean {

    private double money;

    /**
     *
     */
    public MoneyBean() {
        // TODO Auto-generated constructor stub
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "MoneyBean [money=" + money + "]";
    }



}
