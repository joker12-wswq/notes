------------>7.28

###### 1.概念

java编写的、线程安全的<u>web组件</u>（由tomcat调用和创建）

###### 2.作用

①CGI，能从web容器中拿数据（内置对象）---->注入（调用方法，获取数据）方式

②（通过request拿到请求参数）创建表单bean，收集表单参数

③分析请求，分发请求（控制层）

④根据业务层返回的转向信息，转到（request.getRequestDispatche）视图组件

3.tomcat读取web.xml中servlet的配置信息，调用doGet( )、doPost( )方法，将response、request注入

###### 4.语法

包声明、包导入

继承HttpServlet

复写doGet( )、doPost( )方法

###### 5.编译

WEB-INF  classes 下，打包编译

- jdk10前，无法直接编译servlet，因为jdk中没有对应的包，包在servlet-api.jar

  需要将servlet-api.jar放在jdk/jre/lib/ext包下，

- jdk10后，配置环境变量classpath，例如：“set classpath= =%Tomcat%\common\lib\servlet. jar”

###### 6.配置web.xml

~~~~
 <servlet>
	<servlet-name>simple</servlet-name>
   servlet  class文件所在的位置
	<servlet-class>cn.zte.pxb.servlet.SimpleServlet</servlet-class>
    配置后，tomact启动，自动根据servlet-class创建servlet对象
	没有配置则第一次请求访问时，创建servlet对象,只创建一次
	0-9优先级从高到低
	<load-on-startup>0</load-on-startup>
  </servlet>
  <servlet-mapping>
   通过/demo映射名找到servlet对象，注入response、request地址，对象不变，调用doGet、doPost
	<servlet-name>simple</servlet-name>
	映射名任意
	<url-pattern>/demo</url-pattern>
  </servlet-mapping>
~~~~

###### 7.✨生命周期

创建--->初始化--->等待用户请求------->处理请求--->处理完请求后等待----->（修改web.xml配置或关闭tomcat）销毁

初始化

~~~
// 初始化
	public void init(ServletConfig config) throws ServletException
	{
	//注入config
		System.out.println("** Servlet 初始化 ...") ;
	}
~~~

doGet、doPost

~~~
// 表示处理get请求
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException
	{
		System.out.println("** Servlet doGet处理 ...") ;
	}
~~~

销毁

~~~~
// 销毁
	public void destroy()
	{
		//释放资源
		System.out.println("** Servlet 销毁 ...") ;
	}
~~~~

8.

只执行初始化操作：如果servlet中没有复写doGet( )、doPost( )方法，不需要配置  </servlet-mapping>

此时，无法接收用户请求，如果没有<load-on-startup>，无法创建servlet对象

如果servlet有有参的init方法和无参的init方法，执行有参的init方法

9.

✨线程安全的类举例    integer、String、异常类

属性私有，get方法，构造方法修改、final



10.servelt 动态连接数据库

不修改代码，通过初始化信息

在web.xml中的servlet标签中，配置<init-param>

==缺点==：配置错误，无法启动服务器

~~~
	<init-param>
		<param-name>usename</param-name>
		<param-value>scott</param-value>
	</init-param>
	<init-param>
		<param-name>password</param-name>
		<param-value>tiger</param-value>
	</init-param>
	<init-param>
		<param-name>DBDRIVER</param-name>
		<param-value>oracle.jdbc.driver.OracleDriver</param-value>
	</init-param>
~~~

设置私有属性

~~~
 private String usename=null;
 private String password=null;
~~~

使用init方法，注入config，初始化连接参数

~~~~
// 初始化，注入config
	// 要取得初始化参数，必须使用以下初始化方法
	public void init(ServletConfig config) throws ServletException
	{    
		// config对象中有取得初始化参数的方法：getInitParameter("参数名称")
        this.usename= config.getInitParameter("usename") ;
		//传递给实例全局变量
		this.password = config.getInitParameter("password") ;
		String dd = config.getInitParameter("DBDRIVER") ;

	}
~~~~

将初始化参数传给持久层

