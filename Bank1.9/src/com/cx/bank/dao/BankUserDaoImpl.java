package com.cx.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.ConnectionMysql;
import com.cx.bank.util.Md5Code;

public class BankUserDaoImpl implements BankUserDao{

    ConnectionMysql cMysql = new ConnectionMysql();
    Md5Code md5Code = new Md5Code();

    public BankUserDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 注册时按用户名查找用户
     * @param name 用户输入的用户名
     * @return boolean
     */
    public boolean findByName(String name) {
        try {
            // TODO Auto-generated method stub
            //获取数据库连接
            Connection con = cMysql.JDBC();

            //sql语句
            String selectByName = "select * from t_user where user_name = ?";

            //获取SQL执行对象
            PreparedStatement ps = con.prepareStatement(selectByName);

            //设置?的值
            ps.setString(1, name);

            //执行sql
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {//存在用户名
                System.out.println("false用户名存在");
                return false;
            }

            //释放资源
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        System.out.println("true");
        return true;//表示注册的用户名不存在
    }


    /**
     * 添加用户完成注册方法
     * @param  user 用户输入的用户名、密码
     * @return void
     */
    public void insertUser(UserBean user) {
        // TODO Auto-generated method stub

        String name = user.getUserName();

        String psd = user.getPassWord();
        String mdpsd = md5Code.beMd5Code(psd);//md5加密

        user.setBalance(10);//预存10元


        try {
            //获取数据库连接
            Connection con = cMysql.JDBC();

            //sql语句
            String insertOne ="insert into t_user  (user_name,user_password) values (?,?)";

            //获取sql对象
            PreparedStatement pS = con.prepareStatement(insertOne);

            //设置?
            pS.setString(1, name);
            pS.setString(2, mdpsd);

            //执行sql,返回影响行数,没有参数
            int count = pS.executeUpdate();

            if (count >0) {
                System.out.println("添加成功~");
            }else {
                System.out.println("添加失败~");
            }

            //释放资源
//			rs.close();
            pS.close();
            con.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    /**
     * 登录时按用户名和密码查找用户
     * @param  user 用户输入的用户名、密码
     * @return boolean
     */
    public boolean findUser(UserBean user) {
        //获取用户输入的用户名密码
        String name = user.getUserName();
        String psd = user.getPassWord();

        try {

            //如果用户名存在
            if (findByName(name)==false){
                //获取数据库连接
                Connection con = cMysql.JDBC();

                //根据输入的用户名，从数据库中获取加密后的password
                String selectPsd = "select user_password from t_user where user_name = ?";

                //获取sql执行对象
                PreparedStatement ps = con.prepareStatement(selectPsd);
                //设置？的值
                ps.setString(1,name);
                //执行sql
                ResultSet rs = ps.executeQuery();
                //获取数据
                while (rs.next()){
                    String psd1 = md5Code.beMd5Code(psd);//对用户输入的密码进行MD5加密
                    String psd2 = rs.getString("user_password");
                    //两个加密码是否一致，用户名是否一致
                    if (psd2.equals(psd1)) {
                        System.out.println("用户名、密码正确");
                        return true;
                    }else {
                        System.out.println("密码错误");
                        return false;
                    }
                }

                //释放资源
                rs.close();
                ps.close();
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //用户不存在，返回false
        System.out.println("用户名不存在");
        return false;

    }


    /**
     * 存储,根据用户名存储余额
     * @param logname 登录用户名
     * @param moneyBean 余额
     */
    public void saveMoney(String logname, MoneyBean moneyBean) {

        double money = moneyBean.getMoney();

        try {
            //连接数据库
            Connection con = cMysql.JDBC();

            //定义sql语句
            String updateMoneyByName = "update t_user set balance = ?  where user_name = ?";

            //获取执行sql的对象
            PreparedStatement ps = con.prepareStatement(updateMoneyByName);

            //设置?
            ps.setDouble(1,money);
            ps.setString(2,logname);

            //执行sql
           int count = ps.executeUpdate();

           //处理结果
            if (count > 0){
                System.out.println("更新成功~");
            }else {
                System.out.println("更新失败~");
            }

            //释放资源
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 被转账人转账后金额变化
     * @param accountB 转入账户
     * @return void 无
     */
    @Override
    public double findMoneyByName(String accountB) {
        double money = 0;
        try {
            //连接数据库
            Connection con = cMysql.JDBC();

            //查询出该用户余额
            String sql ="select balance from t_user where user_name = ?";

            //获取sql执行对象
            PreparedStatement ps = con.prepareStatement(sql);

            //设置？的值
            ps.setString(1,accountB);

            //执行sql
            ResultSet rs = ps.executeQuery();

            //获取数据
            while (rs.next()){
               money = rs.getDouble("balance");
            }

            //释放资源
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return money;
    }


    /**
     * 将用户id、操作类型、操作金额存入t_log表中
     * @param logType 操作金额
     * @param logMount 操作类型
     * @param userId 用户id
     */
    @Override
    public void saveLog(String logType, double logMount, int userId) {
        try {
            //连接数据库
            Connection con = cMysql.JDBC();

            //定义sql语句
            String sql = "insert into t_log  (log_type,log_amount,userid) values (?,?,?)";

            //获取执行sql的对象
            PreparedStatement ps = con.prepareStatement(sql);

            //设置？
            ps.setString(1,logType);
            ps.setDouble(2,logMount);
            ps.setInt(3,userId);

            //执行sql
            int  count = ps.executeUpdate();

            if (count > 0){
                System.out.println("添加成功!");
            }else {
                System.out.println("添加失败~");
            }
            //释放资源
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据用户名查找id
     * @param logname 登录用户名
     * @return id
     */
    @Override
    public int findIdByName(String logname) {
        int id = 0;

        try {
            //连接数据库
            Connection con = cMysql.JDBC();

            //定义sql语句
            String sql = "select user_id from t_user where user_name = ?";

            //获取执行sql的对象
            PreparedStatement ps = con.prepareStatement(sql);

            //设置？
            ps.setString(1,logname);

            //执行sql
            ResultSet rs = ps.executeQuery();

            //获取资源
            while (rs.next()){
                id = rs.getInt("user_id");
            }

            //释放资源
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }


    /**
     * 根据用户id获取log信息
     * @param userId 用户id
     * @return data
     */
    @Override
    public Object[][] getLogMessage(int userId) {
        Object[][] data = new String[50][3];
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //连接数据库
            con = cMysql.JDBC();

            String sql = "select log_type,log_amount,user_name from t_log,t_user where t_log.userid = t_user.user_id and  userid = ?";

            ps = con.prepareStatement(sql);

            ps.setInt(1,userId);

             rs = ps.executeQuery();

            for (int i=0; rs.next(); i++){
                data[i][0] = rs.getString("user_name");
                data[i][1] = rs.getString("log_type");
//                data[i][2] = rs.getInt("log_amount")+"";
                data[i][2] = String.valueOf(rs.getInt("log_amount"));
            }

            //释放资源
            rs.close();
            ps.close();
            con.close();

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     * 根据用户id设置用户是否被冻结，数据库默认为1，可用
     * @param f 用户冻结标志
     * @return
     */
    @Override
    public boolean setUserFlag(int f,int id) {

        boolean flag = false;

        try {
            Connection con = cMysql.JDBC();

            String sql = "update t_user set user_flag = ?  where user_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,f);
            ps.setInt(2,id);

            int count = ps.executeUpdate();

            if (count > 0){
                System.out.println("冻结标志设置成功~");
                flag = true;
            }else {
                System.out.println("冻结标志设置失败~");
            }

            //释放资源
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;

    }


    /**
     * 获取用户是否冻结标志
     * @param id 用户id
     * @return
     */
    @Override
    public int getUserFlag(int id) {
        int f = 0;

        try {
            Connection con = cMysql.JDBC();

            String sql = "select  user_flag from t_user where user_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                f = rs.getInt("user_flag");
            }

            //释放资源
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }


    /**
     * 获取用户是否为管理员标志
     * @return
     */
    @Override
    public int getAdminFlag(int id) {
        int f = 0;

        try {
            Connection con = cMysql.JDBC();

            String sql = "select  admin_flag from t_user where user_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                f = rs.getInt("admin_flag");
            }

            //释放资源
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }


    public static void main(String[] args) {
        BankUserDaoImpl bDaoImpl = new BankUserDaoImpl();
//		bDaoImpl.findByName("b");

//        UserBean uBean = new UserBean();
//        uBean.setUserName("b");
//        uBean.setPassWord("92eb5ffee6ae2fec3ad71c777531578f");
//        bDaoImpl.insertUser(uBean);
//        bDaoImpl.findUser(uBean);

//        bDaoImpl.saveMoney("b",10);
//        double money =bDaoImpl.findMoneyByName("b");
//        System.out.println(money);
//        bDaoImpl.saveLog("存款",20,15);
        int id = bDaoImpl.findIdByName("b");
        System.out.println(id);
    }

}
