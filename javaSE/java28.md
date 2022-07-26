-------------->7.14

## 网络通信

#### 网络基本概念

1.TCP/IP（传输控制协议/网际协议）是Internet的主要协议，TCP/IP网络参考模型包括五个层次：应用层（应用程序上的通信）、传输层（保证端到端，传输协议主要是TCP、UDP协议）、网络层（点到点的连接）、链路层（物理连接访问控制、网卡；先听后发、边听边发、碰撞随机重发）、物理层（物理连接）。省略了表示层、会话层。

2.高层协议 http （--->TCP协议）等

3.==信道Socket==---->IP地址和端口号port（服务器的应用程序的逻辑标识，可以更改），（两个字节）其范围为0～65535，其中0～1023为系统所保留

##### 实现网络编程关键的三要素

###### IP地址

1.概念：==设备==在网络中的地址,是唯一的标识。==每台电脑都有==

IP (Internet Protocol) :全称”互联网协议地址”,是分配给上网设备的唯一标志。

常见的IP分类为: IPv4（32bit4个字节）和IPv6（16个字节，号称可以为地球每一-粒沙子编号）

- IP地址形式：

公网地址、和私有地址(局域网使用，例如wifi)。

192.168. 开头的就是常见的局域网地址，范围即为192.168.0.0--192.168.255.255, 专门为组织机构内部使用。

- IP常用命令:

ipconfig: 查看本机IP地址

ping IP地址:检查网络是否连通  ping网址或ip地址

- 特殊IP地址:

本机IP: 127.0.0.1或者localhost:称为回送地址也可称本地回环地址，只会寻找当前所在本机。

###### 端口

1.概念：==应用程序==在设备中唯一的标识。

2.端口类型

- 周知端口: 0~1023, 被预先定义的知名应用占用(如: HTTP占用80, FTP占用21)
- 注册端口: 1024~49151, 分配给用户进程或某些应用程序。( 如: Tomcat占用8080, MySQL占用3306)
- 动态端口: 49152到65535,之所以称为动态端口，是因为它-般不固定分配某种进程,而是动态分配。

==注意:==我们自己开发的程序选择注册端口,且一个设备中不能出现两个程序的端口号- -样,否则出错。

###### 协议

1.概念：==获得资源的方式==，数据在网络中传输的规则，常见的协议有UDP协议和TCP协议。

2.传输层的2个常见协议

TCP(Transmission Control Protocol) :传输控制协议

UDP(User Datagram Protocol): 用户数据报协议

3.TCP协议特点

- 使用TCP协议, 必须双方先建立连接，它是一种==面向连接==的可靠通信协议。
- 传输前，采用“三次握手”方式建立连接,所以是==可靠==的。
- 在连接中可进行大数据量的传输 。
- 连接、 发送数据都需要确认，且传输完毕后,还需释放已建立的连接，通信效率较低。

4.==TCP优点==：可靠、有序，以字节流方式发送数据（流协议）

5.TCP协议通信场景

对信息安全要求较高的场景,例如:文件下载、金融等数据通信。

==6.TCP三次握手确立连接==

![image-20220714193532389](E:/Program Files/Typora/img/image-20220714193532389.png)

<img src="E:/Program Files/Typora/img/image-20220714193601137.png" alt="image-20220714193601137" style="zoom: 80%;" />

==7.TCP四次挥手断开连接==

<img src="E:/Program Files/Typora/img/image-20220714193839889.png" alt="image-20220714193839889" style="zoom:80%;" />

<img src="E:/Program Files/Typora/img/image-20220714193948319.png" alt="image-20220714193948319" style="zoom:80%;" />

8.UDP协议:

- UDP是一 种无连接、不可靠传输的协议。
- 将数据源IP、目的地IP和端口封装成数据包，不需要建立连接
- 每个数据包的大小限制在64KB内
- 发送不管对方是否准备好，接收方收到也不确认，故是不可靠的
- 可以广 播发送，发送数据结束时无需释放资源，开销小,速度快。

==用户数据报协议UDP==：无序、不安全、不可靠，不需要建立连接（存储转发），传输效率高。

9.UDP协议通信场景

语音通话，视频会话等。

#### Java中的网络支持

java.net

##### 1.四大类：

InetAddress面向的是IP层网络层

URL面向的应用层

Sockets（TCP==传输控制协议==）和Datagram（UDP）面向的则是传输层

##### 2.使用InetAddress

没有构造函数，获取实例的方法：

public static InetAddress     getByName(String host)      通过域名拿到iP地址

~~~
  InetAddress address= null;
        try{
       
		//address= InetAddress.getByName(“112.80.248.76”);公网ip
            address= InetAddress.getByName(“www.baidu.com”);
        }catch(UnknownHostException e) {}
        System.out.println(address);
    }
~~~

public static InetAddress     getLocalHost()       获取本机的IP地址

~~~
InetAddress myIP = null;
        try
       {     //DESKTOP-KUIT3HD/192.168.1.146
             myIP = InetAddress.getLocalHost();
       }
       catch(UnknownHostException e){}
       System.out.println(myIP);
    }
~~~

判断是否连通

~~~
Systen. out . printLn(ip3. isReachable( timeout: 5000));//5s内
~~~

##### 3.使用URL

-  URL是<u>统一资源定位符（</u>Uniform Resource Locator）的简称，代表==网络的资源==
- 通过URL、URLConnection==客户端与网络资源的连接==，就可以访问Internet
- 一个URL包括两部分内容：
- ==协议名称和资源名称==，中间用冒号隔开：  Protocol:resourceName 如：http://www.ztenc.com.cn

~~~
URL url1,url2,url3;
try{
        url1 = new URL(“file:/D:/image/example.gif”);//文件协议
        url2 = new URL(“http://www.ztenc.com.cn”);//网络协议
        url3 = new URL(url2, “test.gif”);
}catch(MalformedURLException e)
{
       DisplayErrorMessage();
} 
~~~

通过URL读取www信息

URL中最常用的两个方法是：

public InputStream getInputStream() 

public OutputStream getOutputStream()

~~~
//上传
URL gis = new URL("http://gis.pku.edu.cn/test.htm");
       URLConnection uc = gis.openConnection();
       BufferedReader in = new BufferedReader( 
               new InputStreamReader( uc.getInputStream() ) );
       String line;
       while( (line = in.readLine()) != null )
        {
             System.out.println(line);
         }
         in.close();
//下载
URL url = new  URL("http://www.ztenc.com.cn/~lyw/cgi-bin/test.cgi"); 
URLConnection uc = url.openConnection(); 
uc.setDoOutput(true);
PrintStream out = new  PrintStream(uc.getOutputStream());
BufferedReader in = new BufferedReader( 
               new InputStreamReader( uc.getInputStream() ) );
~~~

##### 4.URL和InetAddress的联合使用

利用类URL的getHost()方法得到主机名 

~~~
URL  url = getCodeBase();
String host = url.getHost();
Try{
    InetAddress address = InetAddress.getByName(host);
}
~~~

利用类InetAddress的getByName()得到该主机的IP地址

~~~
 	//uDP协议过程
 	DatagramSocket socket = new DatagramSocket();
    DatagramPacket packet = new DatagramPacket(buf, length, address, port);
    socket.send(packet);
~~~

##### 5.Socket通信

<img src="E:/Program Files/Typora/img/image-20220714142039668.png" alt="image-20220714142039668" style="zoom:50%;" />

单点连接，服务端与客户端通信

服务端与客户端同时希望拿到数据，都没有放数据，==死锁==

拿到socket信息

~~~
connection.getRemoteSocketAddress()
~~~

①服务端Server

创建信道端口，等待客户端Client发出请求

~~~
 ServerSocket server=null;
 Socket you=null;
 server=new ServerSocket(4331);//注册服务器端口
 you=server.accept();//等待接收客户端socket连接请求
~~~

创建信道的数据输入（读）、输出流（写）

~~~
 DataOutputStream out=null;
 DataInputStream  in=null;
 in=new DataInputStream(you.getInputStream());
 out=new DataOutputStream(you.getOutputStream());
~~~

进行读写设置

~~~
String s=null;
s=in.readUTF();// 通过使用in读取客户放入"线路"里的信息.堵塞状态,
                             //除非读取到信息.
out.writeUTF("你好:我是服务器");//通过 out向"线路"写入信息.
             out.writeUTF("你说的数是:"+s);
             System.out.println("服务器收到:"+s);
             Thread.sleep(500);
~~~

下线设置

~~~
catch(IOException e)
         {  System.out.println("用户下线"+e);
     //java.net.SocketException: Connection reset
		 }
~~~

拿的数据是客户端在信道放的数据

②客户端Client

如果没有再指定端口，将会发生==连接被拒==

在指定信道端口，发出请求

~~~
Socket mysocket;
mysocket=new Socket("localhost",4331);
~~~

创建信道的数据输入（读）、输出流（写）

~~~
DataInputStream in=null;
DataOutputStream out=null;
in=new DataInputStream(mysocket.getInputStream());
out=new DataOutputStream(mysocket.getOutputStream());
~~~

进行读写设置

~~~
String s=null;
out.writeUTF("你好 ");//通过 out向"线路"写入信息.
          while(true)
            {  
             s=in.readUTF();//通过使用in读取服务器放入"线路"里的信息.堵塞状态,
                             //除非读取到信息.
               out.writeUTF(":"+Math.random());
               System.out.println("客户收到:"+s);
               Thread.sleep(500);
            }
         }
~~~

~~~
IOException e)
         {  System.out.println("无法连接"+e);
         //java.net.ConnectException: Connection refused: connect
		 }
~~~

拿的数据是服务端在信道放的数据