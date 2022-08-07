----------->8.1

##### el表达式深度导航

1.数据库的表之间存在一对多，多对多的关系

2.举例：

①NameBean    CompanyBean     EmployeeBean第三方

EmployeeBean（一）     NameBean 员工（多）        

EmployeeBean（一）     CompanyBean 公司（多）

②对象的创建

~~~
对象EmployeeBean，有NameBean、CompanyBean 类型的变量
~~~

③el获取

~~~
${xxx.xxx.xxx}  对象通过getXX，获取对象的地址，该对象在通过get方法拿到属性的值
~~~

##### el表达式对集合操作

~~~
${xxx[2]}  拿到集合第二个元素
~~~

#### filter

1.过滤servlet，拦截器/过滤器

2.作用：请求拦截、结果拦截（java编写的、线程安全的web组件）

3.基本语法：

①包声明、包导入

②实现Filter（javax.servlet.Filter）接口                                                                                                             

③实现方法==init==（FilterConfig filterConfig）、==doFilter==（ServletRequest req,ServletResponse resp,FilterChain chain）、==destroy==()

~~~
public void doFilter(ServletRequest req,ServletResponse resp,FilterChain chain)
throws IOException,ServletException{   
		HttpServletRequest request= (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		System.out.println("** 过滤器 doFilter请求拦截 (chain之前)...") ;
		chain.doFilter(request,response);
		System.out.println("** 过滤器 doFilter结果拦截 (chain之后)...") ;
	}
~~~

- tomcat启动，扫描所有webapp下的项目的we.xml文件， 创建两个config对象，ServletConfig和FilterConfig

- tomcat将请求交给拦截器，请求被拦截，注入Request、Response、实现filter接口的对象 ，自动调用doFilter方法

- 参数造型

  ~~~
  	HttpServletRequest request= (HttpServletRequest)req;
  	HttpServletResponse response = (HttpServletResponse)resp;
  ~~~

-   chain.doFilter(request,response);

  ~~~
  如果有下一个拦截器，激活下一个拦截器，继续拦截，所以一个项目可能有多个拦截器
  没有就下传，继续执行下面流程
  ~~~

  ~~~
  该doFilter不是本方法doFilter，所以不是自己调自己
  ~~~

==如果没有 chain.doFilter(request,response)，请求将无法下传==，==没有结果传给浏览器==

4.部署web-INF classes下 ，打包编译

5.配置

与servlet相比

①没有load on start up，tomcat一起到，自动创建拦截器对象

②<url-pattern>/*</url-pattern>，标识拦截或过滤条件，此处表示所有请求都拦截

*.jsp  对访问jsp的请求做拦截

~~~
 <filter>
	<filter-name>first</filter-name>
	<filter-class>cn.zte.cx.filter.FirstFilter</filter-class>
  </filter>
  <filter-mapping>
	<filter-name>first</filter-name>
	<url-pattern>/*</url-pattern>
  </filter-mapping>
~~~

6.应用

==编码拦截==

1.配置加入初始化参数,初始化时，init方法获得初始化参数

this.encoding = filterConfig.getinitparam

filter  创建私有属性 encoding

~~~~
filter标签内
   <init-param>
      <init-param-name>encoding</init-param-name>
      <init-param-value>gbk</init-param-value>
    <init-param>   
~~~~

2.doFilter方法,动态编码request.setCharachterEncoding(this.encoding)

3.注解方式

~~~
@WebFilter(filterName = "encodingFilter",urlPatterns = "/*",initParams = {@WebInitParam(name="encoding",value = "gbk")})
~~~

==请求验证拦截==

1.对jsp拦截，*.jsp

2.从request拿到请求字符串：项目名/XXX.jsp

~~~
//获取请求相对路径,返回去除host部分路径
//此语法的request为httprequest类型的
String URI = request.getRequestURI();
~~~

3.

~~~
//返回子字符串"/"，在字符串中第一次出现的索引
URI.indexOf（"/",1） ——————> 数字
~~~

~~~
////截取在字符串中，从该索引开始，直到字符串结尾的子字符串
String a = URI.substring(数字)
~~~

4.比较

~~~
//当session存在时，返回该session，否则不会新建session，返回null
Httpsession session = request.getSession(false);
~~~

~~~
if（!"/index.jsp".equals(a)）{
	//有验证信息，session为null
    if(session==null  ||  session.getA   “name" ==null)
    跳转到index.jsp
    return
}
下传。。。。。
~~~

