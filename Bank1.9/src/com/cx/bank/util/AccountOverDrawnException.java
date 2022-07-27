package com.cx.bank.util;

//取款超出余额是抛出异常
public class AccountOverDrawnException extends ArithmeticException{

    public AccountOverDrawnException() {
        // TODO Auto-generated constructor stub
    }

    public AccountOverDrawnException(String msg) {
        // TODO Auto-generated constructor stub
        super(msg);
    }


}
