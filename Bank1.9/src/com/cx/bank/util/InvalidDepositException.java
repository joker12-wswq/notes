package com.cx.bank.util;

//存款为负数抛出异常
public class InvalidDepositException extends NumberFormatException{

    public InvalidDepositException() {
        // TODO Auto-generated constructor stub
    }

    public InvalidDepositException(String msg) {
        // TODO Auto-generated constructor stub
        super(msg);
    }

}
