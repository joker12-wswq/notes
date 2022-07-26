------------->7.11

## 流

##### 1.流的概念

数据传输的管道。

##### 2.输入流与输出流

输入流：通过流对象将外部数据传输到（内存）

输出流：通过流对象将<u>内存数据</u>（变量、对象、数组等）传输到外部

##### 3.分类

![img](E:/Program Files/Typora/img/wps1.jpg)

根据外部，可分为：文件输入流、标准输入流、网络输入流，输出流也同理。

管道流：多线程之间通信的流

过滤流/套接流：需要套一个基类流存在

## 字节流

##### 1.基本类

InputStream和OutputStream，它们是抽象类。

##### 2.基本类常用方法

###### InputStream类的方法：

①read()：从流中读入数据

- int read()：从输入流中读一个字节，形成一个0～255之间的整数返回（是一个抽象方法）。

   ~~~
   while((c=fis.read())!=-1)//一次读取一个字节，然后转换成ascci码
   ~~~

- int read(byte b[])：读多个字节到数组中，填满整个数组。

  ~~~
   byte[] buf = new byte[512];
   int c = 0;
   //从Buffered流所指的文件读写，只适合文本文件
   while((c = fin.read(buf, 0, 512)) != -1)
  ~~~

-  int read(byte b[], int off, int len)：从输入流中读取长度为len的数据，写入数组b中从索引off开始的位置，并返回读取得字节数。      

对于这三个方法，若返回－1，表明流结束。

②close()：关闭流

###### OutputStream类的方法：

- write(int b)：将一个整数输出到流中（只输出低位字节，为抽象方法）

- write(byte b[])：将字节数组中的数据输出到流中

  ~~~~
  while((c = fin.read(buf, 0, 512)) != -1){
    //c并返回读取得字节数
    System.out.println("c = " + c);
    fout.write(buf, 0, c);
  //如果出现异常，无法正常写入，
  //需要使用flush，刷空输出流，并将缓冲区中的数据强制送出
  //fout.flush();
  }
  ~~~~

- write(byte b[], int off, int len)：将数组b中从off指定的位置开始，长度为len的数据输出到流中 

- flush()：刷空输出流，并将缓冲区中的数据强制送出 

- close()：关闭流

##### 3.文件流

###### FileInputStream类

用来打开一个输入文件，若要打开的文件==不存在==，则会产生例外==FileNotFoundException==，这是一个非运行时例外，==必须捕获或声明抛弃==；      

###### FileOutputStream类

用来打开一个输出文件，若要打开的文件==不存在==，则会==创建==一个==新的文件==，==否则==原文件的内容会被新写入的==内容==所==覆盖==。

~~~
 File inFile=new File("file1.txt");
 File outFile=new File("file2.txt");
 FileInputStream fis=new FileInputStream(inFile);//检查文件是否存在
 FileOutputStream fos=new  FileOutputStream(outFile);//不检查，不存在创建
~~~

- 在进行文件的读/写操作时，会产生非运行时例外IOException，必须捕获或声明抛弃。
- 提供了同步机制，使得某一时刻只有一个线程可以访问一个输入/输出流。

##### 4.过滤流/套接流

==线程安全==

使用前提：必须把它连接到某个输入/输出流上，通常在构造方法的参数中指定所要连接的流：

~~~
FilterInputStream(InputStream in);	
FilterOutputStream(OutputStream out);
~~~

##### 5.过滤流：缓冲流

缓冲流：BufferedInputStream和BufferedOutputStream

只能处理文本文件，一次读取多个字节

创建示例：

~~~
FileInputStream in = new FileInputStream(".//data1.txt");
BufferedInputStream fin = new BufferedInputStream(in);//套接流
FileOutputStream out = new FileOutputStream(".//data2.txt");
BufferedOutputStream fout = new BufferedOutputStream(out);
~~~

##### 6.过滤流：数据流

1.数据流类：DataInputStream和DataOutputStream

2.功能：

- 处理二进制

  ~~~
  FileOutputStream fos = new FileOutputStream("a.txt");
  DataOutputStream dos = new DataOutputStream (fos);
  try{
  	 dos.writeBoolean(true);
       dos.writeByte((byte)123);//内存方式，写二进制文件，不可见，查看乱码 
  ~~~

  ~~~
  FileInputStream  fis = new FileInputStream("a.txt");
  DataInputStream dis = new DataInputStream(fis);
  try{
      System.out.println("\t "+dis.readBoolean());
      System.out.println("\t "+dis.readByte());
  ~~~

- 处理文本文件

  可以一次读一行

- 对网络的数据进行编码

  DataInputStream类的readUTF()方法     

  ​	从流 中读取用 UTF-8 格式编码的 Unicode 字符格式的字符串；然后以 String 形式返回此字符串DataOutputStream类的writeUTF（）方法     

  ​	以与机器无关方式使用 UTF-8 编码将一个字符串写入基础输出流。 

##### 7.对象流（套接流）

###### ①一些概念

对象的持续性：对象的串行化与反串行化

对象流：传输对象的状态，存放对象的状态文件---->二进制文件

对象的状态：对象的实例全局变量的值

序列化/串行化：通过对象输出流，把对象的状态传送到二进制文件，保存对象的状态

反序列化/反串行化：从保存对象的状态的二进制文件或网络当中，通过对象输入流，再生对象的过程

###### ②串行化

步骤：

1.加入串行化协议，实现Serializable接口

~~~
public class Student implements Serializable
{
  ......
}
~~~

2.创建对象

~~~
Student t = new Student(123, "张三", 23, "研发部");
~~~

3.给对象的属性设值，让对象具有状态，一般使用无参构造方法，set

4.创建对象输出流，创建文件字节输出流

~~~
FileOutputStream fout = new FileOutputStream("student.data");
ObjectOutputStream out = new ObjectOutputStream(fout);
~~~

5.套接到对象输出流

###### ③反串行化

硬拷贝  没有调用构造方法

步骤：

1.创建文件字节输入流管道

~~~
FileInputStream fin = new FileInputStream("student.data");
~~~

2.创建对象输入流

~~~
 ObjectInputStream in = new ObjectInputStream(fin);
~~~

3.对象输入流所指的文件中读取对象的状态

~~~
Student t = (Student)in.readObject();
System.out.println("Student : " + t);
in.close();
fin.close();
~~~

##### 8.标准流

①创建时间: 执行主方法

②创建者:jvm创建，system类管理

③包:java.lang.system

④类 System字段

-  In “标准”输入流。  public static final InputStream inOut  
- “标准”输出流。        public static final PrintStream outerr
- 标准”错误输出流      public static final PrintStream err

##### 9.管道流

###### 管道流:

PipedInputStream、PipedOutputStream，多线程之间通信的流

###### 连接方式:

1）在构造方法中进行连接

~~~
byte aByteData1 = 123, aByteData2 = 111;
PipedInputStream pis = new PipedInputStream();
PipedOutputStream pos = new  PipedOutputStream(pis);
~~~

2）通过各自的connect()方法连接在类

PipedInputStream中，

~~~'
connect(PipedOutputStream pos)；
~~~

在类PipedOutputStream中，

~~~
connect(PipedInputStream pis)；
~~~

##### 10.顺序输入流

功能：将多个不同的输入流统一为一个输入流的功能

示例：

~~~
FileInputStream f1,f2;
String s;
//创建文件输入流
f1 = new FileInputStream(“file1.txt”);
f2 = new FileInputStream(“file2.txt”);
//将多个流放入SequenceInputStream流中
SequenceInputStream fs = new SequenceInputStream(f1, f2);
//将SequenceInputStream流套接到数据流
DataInputStream ds = new DataInputStream(fs);
//通过数据流每次读取一行
while( (s = ds.readLine()) != null )
System.out.println(s);
~~~

