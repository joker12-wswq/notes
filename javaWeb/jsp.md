---------------->7.20

#### 注释

##### 作用

1.方便调试，寻找问题

2.便于维护

##### 分类

###### 静态注释

1.概念：对jsp静态代码的注释

2.处理：浏览器

~~~
静态注释  <!--     <input type="text" name="name"    -->
~~~

3.可以对==配置文件==的配置信息==注释==，==由服务器解析==

4.配置文件属于一种思想

###### 动态注释

1.概念：对jsp<u>动态代码</u>（el表达式、标签等）的注释

2.处理：服务器

~~~
动态注释     <%--      --%>
~~~

~~~
<%
		// 可以使用JAVA的单行注释
		   // System.out.println("abcd")  ;
		 	//多行注释
		   	/** 
			int a=3;
        System.out.println(a);
		  */
%>
~~~

3.<% %>内可以使用java注释

#### 变量

##### 全局变量

1.全局变量只初始化一次

2.<% !  %>    内表示全局变量

3.类似tomcat执行了main方法

访问jsp，tomcat将其变为java程序，调用jvm执行，创建对象，tomcat拿到this所指对象的方法，执行；刷新时，第二次直接拿到对象的地址，使用同一个对象

~~~
<%!
	int i = 0 ;
%>
<h1><%=++i%></h1>
~~~

~~~
public class demmo_jsp
{
	private int i=0;
 	public void  print(){    
 		this.i=this.i+1;
 		out.println (this.i);
 	}
}
~~~

##### 局部变量

1.使用时，每一次刷新都初始化

2.<%   %>    内表示局部变量

3.tomcat拿到this所指对象的方法，执行方法，调用完后，方法在栈内的空间释放

~~~
<%
	int i = 0 ;
%>
<h1><%=++i%></h1>
~~~

~~~
public class demmo_jsp
{ 
public  void print(){
 	int i=0;
  	i=i+1;
 	out.println (i);
	}
}
~~~

#### 表达式

##### 1.语法：

<%=     %>

##### 2.作用：

向浏览器输出数据

##### 3.表达式输出与指令输出的比较

 相同点：都是向浏览器输出数据

不同点：

|                 | 语法范畴 | 输出特点                                                 | 使用方式      |
| --------------- | -------- | -------------------------------------------------------- | ------------- |
| <%=     %>      | jsp      | 输出 使用response内置对象。<br/>符合MVC模式要求。        | 输出 比较简单 |
| out.println（） | Java     | 输出 使用out内置对象，<br/>不符合MVC模式要求（各司其职） | 输出 比较复杂 |

 结论：尽量不要在jsp页面中使用原生Java程序，否则：jsp程序将难以维护。

#### 表单

~~~
<body>
<!-- HTML与服务器交互的主要途径是表单 -->
输入表格的行数与列数，进行表格打印操作
<form action="printTable03.jsp" method="post">
	行数：<input type="text" name="rownum"><br>
	列数：<input type="text" name="colnum"><br>
	<input type="submit" value="打印">
</form>
~~~

将请求以<u>post的方式（默认为get请求）</u>交给printTable03.jsp，rownum参数存储行数，colnum参数存储列数

##### 请求

###### get

1.放在http协议上，参数可见

~~~~
......../XXX?rownum=5&colnum=8
~~~~

###### post

1.放在http协议内，参数不可见

2.只有表单能发

~~~
......../XXX
~~~

##### 交互过程

tomcat拿到请求后，

1.创建一个存放请求的容器==request（数据结构类似map）==内置对象，==存放请求参数==

2.处理请求的组件

①从request拿参数

~~~
String t_row = request.getParameter("rownum");
String t_col = request.getParameter("colnum");
~~~

try   catch异常，拦截不符合需求的参数，

②转换参数

~~~
int row = Integer.parseInt(t_row);
int col = Integer.parseInt(t_col);
~~~

③使用参数

3.tomcat     response将结果放到http协议上,回传给浏览器

4.请求结束,request、resonse销毁

#### 指令

~~~
<%@ page contentType="text/html;charset=gb2312"%>   //内容类型，当前页面，静态编码  
<%@ page import="java.util.Date"%>  
<%@ page import="java.io.*,java.sql.*"%> 
~~~

