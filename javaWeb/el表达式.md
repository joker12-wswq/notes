----------->7.29

##### servlet

1.路径信息+servlet映射名

~~~
<url-pattern>/02_servlet/formServlet1</url-pattern>
~~~

demo.jsp在/02_servlet路径下，访问的Servlet为formServlet1

~~~
demo.jsp
<form action="formServlet1" method="post">
用户名：<input type="text" name="uname">
<input type="submit" value="提交">
</form>
~~~

缺点：每有一个表单需要访问servlet都需要配置

2.优化配置

~~~~
 <servlet-mapping>
	<servlet-name>form</servlet-name>
	<url-pattern>*.do</url-pattern>
  </servlet-mapping>
~~~~

~~~
<form action="formServlet1.do" method="post">
用户名：<input type="text" name="uname">
<input type="submit" value="提交">
</form>
~~~

3.获取内置对象

注入方式：config、response、request

依赖查找：out、session、application

~~~
// 取得一个session对象
HttpSession session = req.getSession() ;
~~~

~~~
// 取得application对象
ServletContext app=req.getSession().getServletContext();
~~~

~~~
<!-- 从web容器拿到out -->
PrintWriter out = resp.getWriter() ;
~~~

另外：

~~~
// 取得application对象-init方法，没有config
// ServletContext app = this.getServletContext() ;
// 取得application对象-init方法，有config
//ServletContext app = this.config.getServletContext() ;
~~~

4.servlet注解开发

①要求：至少jdk1.8，tomcat7.0以上

②注解@WebServlet（）      相当于<servlet>的<servlet-class>

```
@WebServlet(urlPatterns="/hello",loadOnStatrup=1)
相当于<servlet-mapping>中的<url-pattern>，<servlet>中的<load-on-startup>
```

tomcat一启动，扫描各组件

③对比web.xml中的配置

~~~
  <servlet>
	<servlet-name>form</servlet-name>
	<servlet-class>cn.cx.servlet.FormServlet</servlet-class>
    <load-on-startup>0</load-on-startup>
  </servlet>
  <servlet-mapping>
	<servlet-name>form</servlet-name>
	<url-pattern>/hello</url-pattern>
  </servlet-mapping>
~~~

##### el表达式

1.表达式语言，在jsp页面的视图端技术

2.作用：从内置对象中获取属性并处理，把处理结果给浏览器显示

3.语法：

~~~
${属性名}    从内置对象中找到该属性名，获取它的值
~~~

①如果属性名的值没有，则返回空串   “   ”而不是null，健壮性比较强

②没有；号和“ ”号

③jsp中支持el表达式的指令

~~~
 <%@ page isELIgnored="false"%>
~~~

4.查找数据的顺序

page-----request----session---application   

5.从指定内置对象拿

~~~
${内置对象Scope.属性名}
~~~

~~~
${sessionScope.name}
~~~

6.数据处理   

从内置对象找属性名，在做处理

~~~
<% 
 pageContext.setAttribute("a","30"); 
 request.setAttribute("b",5);
%> 
~~~

①自动类型转换

~~~
<h1>---a*b+c+6=${a*b+c+6}---</h1><br>  "30"*5+" "+6--->30*5+0+6-->156
<h1>---a>b=${a>b}---</h1><br>     "30" >  5 ---> 30 > 5
~~~

②运算

~~~~
<h1>----3+5*2=${3+5*2}-----</h1>   单纯做运算
~~~~

7.

~~~~
${sim.name}
~~~~

①如果scope中没有sim，后面不执行，结果为“   ”

②如果name没有，出现异常
