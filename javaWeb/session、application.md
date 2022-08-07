------------->7.26

1.==modle 1==  控制逻辑与视图逻辑混合在一起

自提交页面，显示与控制在同一个文件

~~~~
<%@page contentType="text/html;charset=gb2312"%>
<form action="login.jsp" method="post">
	用户名：<input type="text" name="uname"><br>
	密码：<input type="text" name="upass"><br>
	<input type="submit" value="登陆">
</form>

<%  ..........处理请求............%>
~~~~

2.缺点：

可维护性差（前后端代码在一个页面，修改页要在同一页面）

健壮性差，容易产生空指针异常

3.改进：

==model 2==      控制逻辑与视图逻辑分离

使用mvc设计模式，将视图层与控制层分离

#### session

浏览器关闭，用户下线后，经过900s（15min），session销毁

登录设置验证信息

~~~
if(name.equals("admin")&&password.equals("admin"))
		 {
			// 表示登陆成功
			// 通过flag属性判断用户是否已经登陆
			session.setAttribute("flag","ok") ;
			// 跳转到sucess.jsp
			//response.sendRedirect("sucess.jsp") ;
			}
~~~

使用功能时，session验证

~~~
if(session.getAttribute("flag")!=null)
~~~

计时

~~~
long l1= session.getCreationTime() ;
long l2 = session.getLastAccessedTime() ;
~~~

~~~
new Date(l1);
new Date(l2);
~~~

~~~
(l2-l1)/1000;
~~~

#### application

存放实现ServletContext接口的对象的变量

~~~~
ServletContext   application = new MyClass();
~~~~

✨取得虚目录的真实路径------->执行流程

~~~
this.getServletContext().getRealPath("/")
~~~

取得相对路径的绝对路径

~~~
application.getRealPath("base")
~~~

获取请求字符串，不包括IP地址、端口号

~~~
request.getRequestURI();
~~~

#### 安全性

问题 ： 404配置   500代码

1.在浏览器输入路径，访问web-INF下的jsp是无法直接访问的

2.解决：【JavaWeb 访问 WEB-INF安全目录下的jsp等等文件】https://mbd.baidu.com/ma/s/Srq3jd3M

~~~
<!--    配置WEB-INF下的jsp路径-->
  <servlet>
<!--    名称  任意、唯一（否则出现二异性，无法访问）-->
    <servlet-name>tom</servlet-name>
<!--    指定访问文件的位置-->
    <jsp-file>/WEB-INF/index.jsp</jsp-file>
  </servlet>
  <servlet-mapping>
  
    <servlet-name>tom</servlet-name>
<!--   映射名   -->
    <url-pattern>/t</url-pattern>
  </servlet-mapping>
~~~

3.xml文件缺点：编码比较麻烦

#### config

1.创建：tomcat启动，读取web.xml，config创建，早于application

2.config自动到web.xml中读取组件的初始化参数，主要是servlet标签，key values形式封装到config

3..作用：存放传递配置信息的初始化参数

4.使用config初始化参数，配置

#### javabean（MVC的M)

封装数据

##### 1.分类

数据bean（收集、传送数据的容器）       表单bean、结果bean

逻辑bean       业务bean（完成请求操作）、持久bean（封装存储介质）  通过接口关联，降低耦合												

##### 2.表单bean

收集存放表单数据的容器

要求：与表单参数的名称、个数、类型一致

1.包声明

2.类声明，文件名与主类名相同

3.属性私有，与表单参数的名称、个数、类型一致

4.每个属性设置getter、setter方法

5.显式书写无参构造方法

6.不实现接口，不继承类

##### 3.结果bean

封装外部的信息

要求：封装数据库记录时，与数据库的字段的名称、个数、类型一致

作用：封装数据库的结果信息

##### 4.优点

提高开发效率，实现自动化开发（自动创建、自动收集）

提高复用性（取款功能可以用于建行、工行）

提高可维护性、降低耦合（区别于jsp与各层在一起）

提高分布式开发（各功能在不同的服务器上运行）



