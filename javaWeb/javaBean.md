--------------->7.27

#### 标签

~~~
<jsp:useBean id="zs" scope="page" class="cn.zte.pxb.SimpleBean"/>
~~~

1.前提:   class文件已部署完成

2.过程：

~~~
if (page.getAttribute("zs")==null）{
	page.setAttribute("zs",new SimpleBean());  
	SimpleBean zs=(SimpleBean)page.getAttribute("zs");
}
~~~

WEB-INF下找到包对应的目录结构

如果没有，==查找顺序page、request、session、application==

①该页面的page内置对象，是否存在属性名为“zs”的属性值，

②如果没有，创建SimpleBean对象（属性空间、方法空间创建完，对象创建完成），调用无参的构造方法（装修队，为对象初始化），交给属性名zs，把属性名zs给交给page内置对象管理，

③取出属性名zs的属性值，交给一个局部变量zs

~~~
<%    局部变量zs
	zs.setName("bush") ;
	zs.setPassword("123123") ; 
%>
~~~

#### 表单自动收集

==同名收集==

~~~
<jsp:setProperty name="zs" property="*"/>
~~~

给地址所指对象的每个属性设值，值来自于request中的表单同名参数

~~~
${zs.name}
~~~

到scope中，通过page内置对象，找到属性名为zs的属性值，调用getname（）方法

##### 特殊情况

1.表单的参数类型与bean的类型不一致时

如果能转换，标签将类型自动转换

如果表单输入类型不对，无法自动收集，直接出异常

例如：int   age ，表单输入的年龄类型为String

~~~
request.getParamter("age");
~~~

2.表单属性个数与bean不一样

①表单      name、password                          tom、123（、null）

bean     name、password、age                tom、123、0

age保持初始化的原值------------->0

②表单      name、password                          " "、"  "（、null----->没输入域）

bean     name、password、age                null、null、0  

3.对于表单什么都没输入，是"  " ，该标签认为没有，将null设给对象的属性

4.

~~~
<jsp:setProperty name="ls" property="name" param="password"/>
~~~

到scope中取“ls”的属性值，给"ls"所指对象的name属性设值，值为表单中参数名为"password"的值

~~~
<jsp:setProperty name="ls" property="name"  value="password"/>
~~~

到scope中取“ls”的属性值，给"ls"所指对象的name属性设值，值为"password"

##### 错误

~~~
<jsp:setProperty name="ls" property="name"  value=20/>
~~~

提示：quote    symbol     exception

~~~
x <jsp:setProperty name="ls" property="name"  value="20"/>
~~~

##### JavaBean的作用域范围

● page      ● request      ●session       ●application

~~~~
	<%@page contentType="text/html;charset=gb2312"%>
	<jsp:useBean id="cb" scope="request" class="cn.mldn.lxh.CountBean"/>
	访问第
	<font color="red" size="15">
	<jsp:getProperty name="cb" property="coun"/>
	</font>
<%--<jsp:forward page="requestJBDemo02.jsp"/>--%>
	<a href="requestJBDemo02.jsp">requestJBDemo02.jsp</a>
~~~~

进行服务端跳转时，CountBean仍然在request中

进行客户端跳转时，request销毁，CountBean被回收

几毫秒内，GC将没有引用的堆内的对象回收

~~~
<jsp:useBean id="cb" scope="session" class="cn.mldn.lxh.CountBean"/>
~~~



<jsp>标签

缺点：不符合MVC、健壮性差

