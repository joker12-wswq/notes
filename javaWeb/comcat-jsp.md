---------------->7.19

## Web项目流程

<img src="E:/Program Files/Typora/img/image-20220719084735899.png" alt="image-20220719084735899" style="zoom: 67%;" />

java项目，只能是桌面应用。不适用网络级开发，代码量大

javaweb项目，网络应用，用户可以通过网络，==tomcat在webapps下通过项目名找到目录名，执行对应的文件==

#### 各层作用

浏览器：发出请求，显示结果信息

控制层：对请求分析，并根据分析结果进行分发

业务层：对请求进行处理

持久层：对<u>存储介质</u>（文件、数据库、云等）的操作进行封装------>不是必须的，例如计算器

​			   通过r==equest==回传给业务层

视图层：对结果信息进行处理（渲染），将结果显示到浏览器通过http协议

​				jsp视图组件，动态代码只能由服务器执行，浏览器执行不了

​				通过==response==回传给浏览器

服务端comcat：通过ip、端口找到，web中间件，接收请求，处理请求，返回结果信息。

​							==web项目以目录形式存放==

处理过程：将请求发给控制层

## tomcat

1.一个web容器，只能执行符合J2EE的web服务器

2.其他web容器：weblogic、webSphere等

3.==不处理html中的动态代码==

#### 3.tomcat安装

#### 4.目录

bin目录-------->启动、关闭tomcat

webapps目录---->存放web项目

lib目录--------->servlet-api.jar(j2ee标准类库的类)

cof目录------>配置文件

work目录---->jsp编译后产生的class文件

#### 5.web项目结构

webapps

​		testweb

​       		index.jsp

​       		WEB-INF

​               		classses      web组件的字节码文件

​              		 web.xml 

​					   lib               存放 jar包    

web组件只有封装在web项目中，才能被访问

tomcat默认项目名是Root

#### 6.修改显示（虚目录）

1.将项目放置到自己想要的目录下

2.conf/server.xml

~~~
    <!-- 配置项虚目录-->
	<!-- 网址为：http://localhost:8080/test/-->
	<Context  path="/test" docBase="E:\testweb" />
~~~

tomcat   2 字节的 UTF-8 序列的字节 2 无效。

解决：将xml文件头部的UTF-8改成UTF8

~~~
改为： <?xml version="1.0" encoding="UTF8"?>
~~~

## jsp

1.组成：动态+静态执行代码

2.动态代码：<%            %>、标签          ==服务器执行==，将执行结果嵌入到静态代码

   静态代码：css、div、等                       浏览器执行

#### 3.执行过程

启动服务器tomcat，tomcat在webapps下通过项目名找到目录名                                                                                                            

浏览器发请求

tomcat找到jsp页面，将jsp变成java程序，调用jvm编译成class文件，调用虚拟机执行动态代码

通过response对象发给浏览器

4.一个jsp页面修改后，浏览器不会显示修改后的信息

comcat不再编译该jsp

解决：删除work中的loclhost文件缓存，重启tomcat

5.静态页面          服务器上

   tomcat下载静态页面，发送给浏览器解析

  