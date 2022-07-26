## java的优点（高级编程语言）

![image-20220524091926063](https://user-images.githubusercontent.com/107198282/180995043-828b4d7b-47de-4e5d-801f-46276d111129.png)


1.简单

Java语言省略了C++语言中所有的难以理解、容易混淆的特性。

克服的问题：指针的使用

​                       二义性的问题

​                       内存问题

2.健壮

对各种非法输入能够很好地处理，使程序能继续运行，如强类型转换、异常处理等。

3.安全

线程、事物的锁机制等

4.结构中立跨品台。

java程序（XXX.java）在java平台上被编译为体系结构中立的字节码格式（Xxx.class），然后可以在实现这个java平台的任何系统运行。

5.多线程

支持多线程同时执行，并提供多线程同步机制，为多个用户服务。

6.面向对象

java完全面向对象，与现实契合。





#### java缺点

1.运行速度相对较慢、性能相对较低

2.线程不跨平台

3.代码不够简洁

4.占用内存空间较大



#### java特点

1.面向对象

##### 2.一次编写，到处运行

运行机制：编译器编译成class文件（虚拟机接受的文件），然后jvm将文件解释成系统能识别的机器指令
![image-20220524160821346-16533797030753](https://user-images.githubusercontent.com/107198282/180996915-e885b7ea-230e-4e04-9b94-e5b797d6dbd2.png)






## jdk

#### 1.全名：开发者工具包

#### 2.组成
![image-20220524095525151](https://user-images.githubusercontent.com/107198282/180997009-84956d62-525d-43bf-a395-4cc2d99e17aa.png)



#### 3.*path❤

编译需要找到bin目录，配置方便在任何盘符下都可以编译




#### 4.java_home
![image-20220524100152167](https://user-images.githubusercontent.com/107198282/180997924-71fe42aa-cf53-4fda-8298-29d5af617621.png)




#### 5.JDK8配置教程

[安装教程]: https://blog.csdn.net/weixin_44887352/article/details/106588830	"JDK8"

1.bin  --> javap  反编译     javac 编译器      java 解释器  

2.源码位于 src.zip内

#### 6.*classpath❤

编写程序需要标准类库，让虚拟机在指定位置找到jdk标准类库（能编译，不配不能执行）



## jre

获取class文件  -->加载到内存 -->验证 -->机器指令

![image-20220524160821346-16533797030753](https://user-images.githubusercontent.com/107198282/180997644-5c49d16c-83e5-4748-b73e-9c1782174c5f.png)







## 垃圾回收机制GC

1.一种后台线程，观察堆里的对象是否一直被使用

2.什么是“垃圾回收"机制？--->堆里  -->虚拟机控制

3.java如何改进内存泄漏？原因？  GC解决，不能避免。



## 虚内存组成部分

[仅供参考]: https://www.cnblogs.com/dw3306/p/14363472.html	"JVM的组成简介"

- 方法区---Method Area：类代码、共享代码段（代码区）

- java堆--java   heap:存放对象实例

- java栈--java  Stack:存放局部变量表，方法出口

- 程序计数器（指令计数器）--Program Counter Register:行号指示器。循环，跳转等基础指令

- 本地方法栈--Native Method Stack:JVM调用native方法

![image-20220524160619784-16533795847891](https://user-images.githubusercontent.com/107198282/180997553-c6911e0b-8aea-4edb-97d3-d26a63713967.png)




