package com.cx.bank.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Code {

    public  String beMd5Code(String code) {

        byte[] secretBytes = null;

        try {

            secretBytes = MessageDigest.getInstance("md5").digest(code.getBytes());

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("没有这个md5算法！");

        }

        String md5code = new BigInteger(1, secretBytes).toString(16);

        for (int i = 0; i < 32 - md5code.length(); i++) {

            md5code = "0" + md5code;

        }

        return md5code;

    }

//	public static void main(String[] args) {
//		Md5Code md = new Md5Code();
//		String a =md.beMd5Code("1234");
//		System.out.println(a);
//		String b =md.beMd5Code("1234");
//		System.out.println(b);
//	}


}


