package db.test;

import java.sql.*;
import java.util.*;

/*连接池管理类,可以管理多个数据库连接池*/
public class DBConnManager {
	//连接池名列表
	private Vector poolnames = new Vector();
	//驱动程序名列表
	private Vector drivernames = new Vector();
	//数据库标识列表
	private Vector dbids = new Vector();
	//用户名列表
	private Vector usernames = new Vector();
	//密码列表
	private Vector passwds = new Vector();
	//最大连接数列表
	private Vector maxconns = new Vector();
	//连接池队列
	private Hashtable connPools = new Hashtable();
	
	public DBConnManager() {
		//添加mysql数据库的连接信息
		poolnames.addElement("mysql");
		drivernames.addElement("org.gjt.mm.mysql.Driver");
		dbids.addElement("jdbc:mysql://localhost/hibernate_fistb");
		usernames.addElement("root");
		passwds.addElement("mysql");
		maxconns.addElement("10");
		
		//创建连接池
		createPools();
	}
	
	/*将连接返回给由指定的连接池*/
	public void releaseConnection(String name, Connection con) {
		DBConnPool pool = (DBConnPool) connPools.get(name);
		if (pool != null)
			pool.releaseConnection(con);
	}
	
	/*得到一个指定连接池中的连接*/
	public Connection getConnection(String name) {
		DBConnPool pool = (DBConnPool) connPools.get(name);
		if (pool != null)
			return pool.getConnection();
		return null;
	}
	
	/*关闭所有连接*/
	public synchronized void closeConns() {
		Enumeration allPools = connPools.elements();
		while (allPools.hasMoreElements()) {
			DBConnPool pool = (DBConnPool) allPools.nextElement();
			pool.closeConn();
		}
	}
	
	/*创建连接池*/
	private void createPools() {
		for(int i = 0; i<poolnames.size();i++){
			String poolname = poolnames.elementAt(i).toString();
			String drivername = drivernames.elementAt(i).toString();
			String dbid = dbids.elementAt(i).toString();
			String username = usernames.elementAt(i).toString();
			String passwd = passwds.elementAt(i).toString();
			int maxconn=0;
			try {
				maxconn = Integer.parseInt(maxconns.elementAt(i).toString());
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
			} 
			DBConnPool pool = new DBConnPool(poolname, drivername, dbid, username, passwd, maxconn);
			connPools.put(poolname, pool);
		}
	}
}

