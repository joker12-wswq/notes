---------------->7.15

#### UDP通信

适合单点发送

##### UDP协议的特点

- UDP是一种无连接、不可靠传输的协议。
- 将数据源IP、目的地IP和端口以及数据封装成数据包，大小限制在64KB内，直接发送出去即可。

![image-20220715170545731](https://user-images.githubusercontent.com/107198282/181005749-0d4d0f5b-f1d4-4f4d-98ab-50a571bafc38.png)

##### DatagramPacket:数据包对象( 韭菜盘子)

| 构造器                                                       | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public DatagramPacket(byte[] buf, int length, InetAddress address, int port)（发送端，除了创建一个空数据包对象接数据，还要声明地址和端口） | buf:要发送的内容，字节数组<br/>length:要发送内容的字节长度<br/>address:接收端的IP地址对象<br/>port:接收端的端口号<br/>创建接收端的数据包对象 |
| public DatagrarPacket(byte[] buf, int length)（接收端，只需要创建一个空数据包对象接数据） | buf:用来存储接收的内容<br/>length:能够接收内容的长度         |

##### DatagramSocket:发送端和接收端对象(人)

| 构造器                                                       | 说明                                               |
| ------------------------------------------------------------ | -------------------------------------------------- |
| public DatagramSocket()                                      | 创建发送端的Socket对象，==系统会随机分配个端口号== |
| public DatagramSocket(int port)                                          声明端口，发送端才能找到它 | 创建接收端的Socket对象并指定端口号                 |

##### DatagramSocket类成员方法

| 方法                                  | 说明       |
| ------------------------------------- | ---------- |
| public void send(DatagranPacket dp)   | 发送数据包 |
| public void receive(DatagramPacket p) | 接收数据包 |

##### 发送端与接收端通信

关闭资源    

~~~~
socket.close
~~~~

###### 发送端

Shanghai.java

 1.创建发送端对象，发送端自带的默认的端口号(人)

~~~
DatagramSocket mail_data=new DatagramSocket();
~~~

 2.创建一个数据包对象封装数据（韭菜盘子）

~~~~
//public DatogronPacket(byte bufll, int Length,InetAddress address, int port)

 byte buffer[]=out_message.getText().trim().getBytes();
 DatagramPacket data_pack=new DatagramPacket(buffer,buffer.length,address,888);
 
 in_message.append("数据报目标主机地址:"+data_pack.getAddress()+"\n");
 in_message.append("数据报目标端口是:"+data_pack.getPort()+"\n");
 in_message.append("数据报长度:"+data_pack.getLength()+"\n");
~~~~

3.发送数据出去

~~~
 mail_data.send(data_pack);
~~~

###### 接收端

Beijing.java

1.创建接收端对象，并注册端口(人)

~~~
DatagramSocket mail_data=null;
mail_data=new DatagramSocket(888);
~~~

2.创建一个数据包对象接收数据（韭菜盘子）

~~~
//byte[] data=new byte[1024*64];  64kb
byte[] data=new byte[8192];//8kb
DatagramPacket pack=null;
pack=new DatagramPacket(data,data.length);

int length=pack.getLength(); //获取收到的数据的实际长度.
InetAddress adress=pack.getAddress();//获取收到的数据包的始发地址.
int port=pack.getPort();//获取收到的数据包的始发端口.
//pack.getData()字节数组对象
~~~

3.等待接收数据

~~~~
mail_data.receive(pack);
~~~~

4.取出数据

~~~
// 4.取出数据即可
String rs = new String(data,0,pack.getlength());
System.out.printin("收到了: " + rs);
~~~

~~~
 String message=new String(pack.getData(),0,length);
 in_message.append("收到数据长度 "+length+"\n");
 in_message.append("收到数据来自 "+adress+"端口 "+port+"\n");
 in_message.append("收到数据是 "+message+"\n");
~~~

#### UDP通信-广播、组播

###### UDP的三种通信方式

- 单播: 单台主机与单台主机之间的通信。
- 广播: 当前主机与所在网络中的所有主机通信。
- 组播: 当前主机与选定的- -组主机的通信。

###### UDP如何实现广播

- 使用广播地址: 255.255.255.255

- 具体操作:

  ①发送端发送的数据包的目的地写的是广播地址、且指定端口。(255.255.255.255 ，9999)

  ②本机所在网段的其他主机的程序只要匹配端口成功即就可以收到消息了。(9999)

###### UDP如何实现组播

- 使用<u>组播/多播</u>地址: 224.0.0.0 ~ 239.255.255.255

- 具体操作:

  ①==发送端==的数据包的目的地是组播IP (例如: 224.0.1.1, 端口: 9999)

  ②==接收端==必须绑定该组播IP(224.0.1.1), 端口还要对应发送端的目的端口9999 ,这样即可接收该组播消息。

  ③DatagramSocket的子类==MulticastSocket==可以在接收端绑定组播IP.

BroadCast.java

接收端

// 1.创建一个接收端对象，注册端口

~~~
int port=5858;   
MulticastSocket socket=null; 
socket=new MulticastSocket(port);  
~~~

//2.把当前接收端加入到一个组播组中去绑定对应的组播消息的组播ip

~~~
InetAddress group=null;   
group=InetAddress.getByName("239.255.8.0");  //设置广播组的地址为239.255.8.0.
socket.joinGroup(group);            //加入广播组,加入group后,socket发送的数据报,
~~~



