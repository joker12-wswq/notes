
package com.cx.bank.manager;

import com.cx.bank.dao.BankUserDao;
import com.cx.bank.factory.UserDaoFactory;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;

import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

/**
 *@author 菠菜
 *@version 1.0
 * 单例模式   业务层  同步
 */
public class ManagerImpl implements ManagerInterface{
    private static ManagerImpl instanceImpl;//私有的静态变量，存放对象的地址
    private BankUserDao useDao =null;//接口类型的变量

    private int id = 0;

    MoneyBean moneyBean = new MoneyBean();

    //BankDaoImpl bankDaoImpl = new BankDaoImpl();



    /**
     * 将无参的构造方法私有，创建工厂对象
     * @throws Exception
     */
    private ManagerImpl() throws Exception {
        UserDaoFactory userDaoFactory = UserDaoFactory.getInstance();
        useDao = userDaoFactory.creatUserDao();
    }


    /**
     * 获取this的静态方法
     * 使用synchronized检查锁，让同一时刻只有一个线程能使用该方法，实现同步
     * @throws Exception
     */
    public static synchronized ManagerImpl getManagerImpl() throws Exception{
        if (instanceImpl ==null) {
            instanceImpl = new ManagerImpl();
        }
        return instanceImpl;
    }



    /**
     * 实现注册方法
     * @param name 注册用户名
     * @param psd  注册密码
     * @return boolean
     */
    public boolean register(String name,String psd) {
        UserBean user = new UserBean();//创建user对象

        //将用户名和密码设置到user对象里
        user.setUserName(name);
        user.setPassWord(psd);
        boolean flag = useDao.findByName(name);//查找用户名是否存在
        if (flag) {//不存在
            useDao.insertUser(user);
        }
        return flag;

    }


    /**
     * 实现登录方法
     * @param name 用户名
     * @param psd 密码
     * @return boolean
     */
    @Override
    public boolean login(String name, String psd) {
        // TODO Auto-generated method stub
        UserBean user = new UserBean();
        user.setUserName(name);
        user.setPassWord(psd);
        boolean flag;

        flag = useDao.findUser(user);
        //用户名、密码正确，则将该用户的余额从数据库中查找出来，并设置给moneyBean封装
        if (flag){
            double money = useDao.findMoneyByName(user.getUserName());//余额
            moneyBean.setMoney(money);
            id = useDao.findIdByName(user.getUserName());//获取用户id
        }
        return flag;
    }


    /**
     * 查询余额方法
     */
    public double inquiry() {
        double balance = moneyBean.getMoney();
        System.out.println("您当前的余额为："+balance);
        useDao.saveLog("查询",balance,id);//存储log日志
        return balance;
    }


    /**
     * 实现存款方法
     * @param saveMoney 存款金额
     */
    public void deposit(double saveMoney) throws InvalidDepositException{

        double balance = moneyBean.getMoney()+saveMoney;//存款后的余额
        useDao.saveLog("存款",saveMoney,id);//存储log日志
        moneyBean.setMoney(balance);
    }


    /**
     * 实现取款方法
     * @param takeMoney 取款金额
     * @throws AccountOverDrawnException 取款金额超出余额异常
     */
    public void withdrawals(double takeMoney) throws AccountOverDrawnException{


        double balance = moneyBean.getMoney();//余额

        double afterWithDrawals = balance - takeMoney;//取款后的余额
        useDao.saveLog("取款",takeMoney,id);//存储log日志
        moneyBean.setMoney(afterWithDrawals);

    }


    /**
     * 实现转账方法
     * @param account 本人账户
     * @param toAccount 转入账户
     * @param toMoney 转账金额
     * @return void
     */
    @Override
    public String transfer(String account,String toAccount,double toMoney) {
        // TODO Auto-generated method stub
        boolean flag;
        String msg;
        double balance = moneyBean.getMoney();//本人账户余额
        double toBalance = useDao.findMoneyByName(toAccount);//被转入账户余额
        useDao.saveLog("转账",toMoney,id);//存储log日志

        flag = useDao.findByName(toAccount);
        if (flag) {
            msg = "该账户不存在";
        }else if (toAccount.equals(account)) {
            msg = "请不要自己转给自己!";
        }
        else {



//            useDao.transferMoney(toAccount, toMoney);
            double toAfterTransfer = toBalance + toMoney;//被转账账户转账后的余额

            //被转账账户转账后的余额，转为MoneyBean类型
            MoneyBean moneyBean1 = new MoneyBean();
            moneyBean1.setMoney(toAfterTransfer);

            useDao.saveMoney(toAccount,moneyBean1);//将被转入账户改变后的余额作更新

            double afterTransfer = balance - toMoney;//本人转账后的余额
            moneyBean.setMoney(afterTransfer);
            msg = "转账成功，";


        }
        return msg;
    }

    /**
     * 实现退出系统方法
     * @param logname 退出账号
     */
    public void exitSystem(String logname) {

        useDao.saveMoney(logname, moneyBean);
        useDao.saveLog("退出系统",0,id);//存储log日志
//		System.out.println("退出成功!");
        System.exit(1);
    }

    /**
     * 封装log信息
     * @return data
     */
    @Override
    public Object[][] getLog() {
        Object[][] data = useDao.getLogMessage(this.id);
        return data;
    }


    /**
     * 获取用户是否冻结标志
     * @return  useDao.getUserFlag(id)
     */
    @Override
    public int getUserFlag() {
        return useDao.getUserFlag(id);
    }

    /**
     * 获取用户是否为管理员标志
     * @return useDao.getAdminFlag(id)
     */
    @Override
    public int getAdminFlag() {
        return useDao.getAdminFlag(id);
    }


    /**
     * 冻结账户
     * @param name 需要冻结的用户名
     */
    @Override
    public boolean frozenAccount(String name) {
        int id = useDao.findIdByName(name);
        return useDao.setUserFlag(0,id);
    }

    /**
     * 解冻账号
     * @param name 需要解冻的用户名
     */
    @Override
    public boolean thawAccount(String name) {
        int id = useDao.findIdByName(name);
        return useDao.setUserFlag(1,id);
    }

}
