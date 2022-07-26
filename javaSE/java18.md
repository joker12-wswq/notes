---------->6.24

## 自定义异常

1.例如：实型相除除数为零，不显示异常

2.实现：

- 继承Exception或它的子类实现自己的异常类
- 设计两个构造器：无参、有参（传递详细出错的信息）

~~~
	public MyDivideException()
		{
		//super();
		}
	public MyDivideException(String msg)
		{
		super(msg);//通过父类构造方法给异常信息detailMessage设值
		}		
~~~

自己创建异常对象、抛出异常，自己try 、catch

~~~
		try{            
				//有参构造方法传递异常信息                                     
		   	 if(d==0.0) throw new MyDivideException("除数不能为零");  
	    	System.out.println(n+"/"+d+"="+n/d);                           
	 	}      
	       catch(MyDivideException e){
           //System.out.println(e.getMessage());
           //System.out.println(e);    toString   异常类型+toString
           //先调toString，然后at显示出错代码在哪个类哪个方法第几行
           //异常栈跟踪
	       e.printStackTrace(); 
	 }                    
~~~

3.异常数据结构  throwable---->线程安全

私有属性detailMessage，提供公有方法getMessage拿到detailMessage，不提供setMessage

4.异常栈    例8-10

异常是否会影响程序的执行效率？

出现异常的代码和处理异常的代码越近越好，最好出现在一个方法里

5.处理异常总原则

- 屏蔽编译异常，找到运行异常
- 在方法注释中写明不处理的异常
- 持久层、业务层等出现异常，直接throws将异常交给控制层处理

