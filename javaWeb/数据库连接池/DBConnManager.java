package db.test;

import java.sql.*;
import java.util.*;

/*���ӳع�����,���Թ��������ݿ����ӳ�*/
public class DBConnManager {
	//���ӳ����б�
	private Vector poolnames = new Vector();
	//�����������б�
	private Vector drivernames = new Vector();
	//���ݿ��ʶ�б�
	private Vector dbids = new Vector();
	//�û����б�
	private Vector usernames = new Vector();
	//�����б�
	private Vector passwds = new Vector();
	//����������б�
	private Vector maxconns = new Vector();
	//���ӳض���
	private Hashtable connPools = new Hashtable();
	
	public DBConnManager() {
		//���mysql���ݿ��������Ϣ
		poolnames.addElement("mysql");
		drivernames.addElement("org.gjt.mm.mysql.Driver");
		dbids.addElement("jdbc:mysql://localhost/hibernate_fistb");
		usernames.addElement("root");
		passwds.addElement("mysql");
		maxconns.addElement("10");
		
		//�������ӳ�
		createPools();
	}
	
	/*�����ӷ��ظ���ָ�������ӳ�*/
	public void releaseConnection(String name, Connection con) {
		DBConnPool pool = (DBConnPool) connPools.get(name);
		if (pool != null)
			pool.releaseConnection(con);
	}
	
	/*�õ�һ��ָ�����ӳ��е�����*/
	public Connection getConnection(String name) {
		DBConnPool pool = (DBConnPool) connPools.get(name);
		if (pool != null)
			return pool.getConnection();
		return null;
	}
	
	/*�ر���������*/
	public synchronized void closeConns() {
		Enumeration allPools = connPools.elements();
		while (allPools.hasMoreElements()) {
			DBConnPool pool = (DBConnPool) allPools.nextElement();
			pool.closeConn();
		}
	}
	
	/*�������ӳ�*/
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

