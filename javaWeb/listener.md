--------->8.2

#### listener

1.概念：

由java编写的，tomcat创建的web组件，主要对内置对象的状态（创建、销毁）的变化和属性的变化进行监听，做进一步处理

2.作用：

①对session状态（创建、销毁）的变化和属性的变化进行监听

②对application状态（创建、销毁）的变化和属性的化进行监听

3.语法

①包声明、包导入

②类实现

servletContextListener接口（对==application==做监听）

ServletContextAttributeListener接口（对application==属性==做监听）

③实现方法

contextInitialized(ServletContextEvent sce) ，监听到内置对象application，调用该方法

~~~
//拿到application
this.application = sce.getServletContext() ;
~~~

contextDestroyed(ServletContextEvent sce)

4.部署

不需要和用户产生关系，不需要mapping

~~~
<listener>
	<listener-class>cn.mldn.lxh.listener.ServletContextDemo</listener-class>
</listener>
~~~

5.  创建顺序     listener       config      application       filter

6.监听属性变化

①attributeAdded                                          监听到application（map），增加属性，拿到添加的属性

~~~
public void attributeAdded(ServletContextAttributeEvent scab)
	{
		System.out.println("** 增加属性："+scab.getName()+" --> "+scab.getValue()) ;
	}
~~~

②attributeReplaced                                  监听到application（map），拿到替换前的属性名、属性值

~~~
public void attributeReplaced(ServletContextAttributeEvent scab)
	{
		System.out.println("** 替换属性："+scab.getName()+" --> "+scab.getValue()) ;
	}
~~~

③attributeRemoved                                监听到application（map），拿到移除前的属性名、属性值

~~~
public void attributeRemoved(ServletContextAttributeEvent scab)
	{
		System.out.println("** 删除属性："+scab.getName()+" --> "+scab.getValue()) ;
	}
~~~

##### 7.对session做监听

①==创建时机==：用户第一次发请求访问服务器的动态组件，创建一次

​	关闭方式：

- 调用方法session.inValidate()

- 关闭浏览器，900s后

- 配置web.xml：

       ~~~
       session超时 
         需要在web.xml中进行配置 
         <session-config> 
        <session-timeout>1</session-timeout>  1分钟时间
         </session-config> 					
       ~~~

- 使用js

~~~
<script language="javaScript">
function window.onunload()
 {//浏览器是否关闭
  if(((event.clientX > document.body.clientWidth) && (event.clientY<0))
   || event.altKey
   || event.ctrlKey)
  {
  
  window.location.replace("removeline.jsp");  
  }
 }
</script>
~~~

~~~
removeline.jsp
<%
	session.invalidate() ;
%>
~~~

②实现接口

implements HttpSessionListener,

HttpSessionAttributeListener

③属性

~~~~
private HttpSession session ;
~~~~

④实现方法       

sessionCreated(HttpSessionEvent se)            ==可以获得session==        访问动态组件，==还没有验证==

~~~
this.session = se.getSession() ;
~~~

sessionDestroyed(HttpSessionEvent se)

⑥session属性变化监听

attributeAdded                ==可以获得session==        ==经过验证==，设置的session        

~~~~
public void attributeAdded(HttpSessionBindingEvent se)
	{
		System.out.println("** Session 增加属性:"+se.getName()+" --> "+se.getValue()) ;
	    System.out.println("** 获得Session "+se.getSession().getId()) ;
	}
~~~~

attributeReplaced                                                           监听到session，拿到替换前的属性名、属性值

~~~~
public void attributeReplaced(HttpSessionBindingEvent se)
	{
		System.out.println("** Session 替换属性:"+se.getName()+" --> "+se.getValue()) ;
	}
~~~~

attributeRemoved                                     					监听到session，拿到移除前的属性名、属性值 

~~~~
	public void attributeRemoved(HttpSessionBindingEvent se)
	{
		System.out.println("** Session 删除属性:"+se.getName()+" --> "+se.getValue()) ;
	}
~~~~

session  失效   ，el表达式到session找属性时，null.getxxxx，出错                                                              















​	





​	