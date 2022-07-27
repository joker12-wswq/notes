package com.cx.bank.util;

import java.io.FileInputStream;
import java.sql.Connection;

import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class ConnectionMysql {

    public ConnectionMysql() {
        // TODO Auto-generated constructor stub
    }

    public Connection JDBC() throws Exception {
        //配置文件对象
        Properties prop = new Properties();
        //当前目录下
        prop.load(new FileInputStream("../Bank1.9/src/lib/druid.properties"));

        //获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接

        Connection conn = dataSource.getConnection();

        System.out.println(conn);

        System.out.println(System.getProperty("user.dir"));

        return conn;

    }

	public static void main(String[] args) throws Exception {
		ConnectionMysql con = new ConnectionMysql();
		con.JDBC();
	}


}
