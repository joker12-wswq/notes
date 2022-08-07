#### idea创建web项目

1.新建一个java项目

2.右击项目，选择第二个Add Framework Support，添加web支持

3.java EE 勾选Web Applicationn ，ok

4.配置tomcat

- 点击工具栏 Add Configuration,出现Run/Configurations窗口
- 点击+号，选择Tomcat Server下的Local，注意不是TomEE Server下的Local

【Intellij IDEA创建web项目 [超详细]】https://mbd.baidu.com/ma/s/zWuLj0Rp





#### 用Maven创建web项目

1.创建一个空项目

2.配置maven环境

file-->setting    搜索maven     配置例子

![image-20220720095230287](E:/Program Files/Typora/img/image-20220720095230287.png)

3.创建web项目

- 不使用骨架

①创建一个maven   moudel

​    file -->new  --->moudel，选择第二个maven，next，给moudel命名，parent选择none

②pom.xml，加入

![image-20220721100045897](E:/Program Files/Typora/img/image-20220721100045897.png)

③加入webapp

file --->project   structre---->facets  如果没有web点+，添加

然后两处点+

![image-20220720095936335](E:/Program Files/Typora/img/image-20220720095936335.png)

![image-20220720095953379](E:/Program Files/Typora/img/image-20220720095953379.png)

③将WEB-INF拖入到webapp，右击webapp，新建jsp

- 使用骨架

①创建一个maven   moudel

​    file -->new  --->moudel，选择第二个maven，



#### 修改路径名

edit configrations,选中tomcat，选择Deploment，向下滑动，修改该名称

![image-20220720145943756](E:/Program Files/Typora/img/image-20220720145943756.png)



#### 报错问题

[(26条消息) IDEA日常填坑：Cannot resolve plugin org.apache.maven.plugins:maven-war-plugin_^Being^的博客-CSDN博客](https://blog.csdn.net/weixin_43802738/article/details/104957732?ops_request_misc=%7B%22request%5Fid%22%3A%22165840170516780357292486%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=165840170516780357292486&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~baidu_landing_v2~default-1-104957732-null-null.142^v33^pc_search_v2,185^v2^control&utm_term=Cannot resolve plugin org.apache.maven.plugins%3Amaven-war-plugin%3A3.2.2&spm=1018.2226.3001.4187)



#### idea  创建maven父子工程项目

https://blog.51cto.com/u_15482433/4921414



#### idea创建servlet

【IDEA Maven创建第一个servlet】https://mbd.baidu.com/ma/s/WRuzp4BY



#### IDEA中使用Tomcat - Tomcat Maven插件

1. pom.xml 添加Tomcat插件

  ~~~~
  <build>
  	<plugins>
  	<l-Tomcat插件-->
  	<plugin>
  		<groupld>org. apache .tomcat.maven</groupld>
  		<artfactld>tomcat7-maven-plugin</arifatld>
  		<version>2.2</version>
  	</plugin>
  </plugins>
  </build>
  ~~~~

  2.使用Maven Helper插件快速启动项目,选中项目,右键--> Run Maven --> tomcat7:run

  

  