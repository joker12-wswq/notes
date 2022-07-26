----------->7.12

##### 随机流

1.随机流：RandomAccessFile

随机流的父类为object，不区分输入输出流

2.随机流只能处理二进制文件

3.创建随机流对象，需要加入方式

~~~
//创建随机流对象，rw以读写方式
RandomAccessFile randf = new RandomAccessFile("temp.dat","rw");
~~~

4. 指定文件位置指针

seek（）方法   

~~~
//从0开始挪
randf.seek(8);
~~~

skipBytes( ) 方法

~~~
//从指针的位置
randf.skipBytes(8);//43
~~~

##### 字符流

1.字符流：BufferedReader和BufferedWriter；只能操作文本文件

2.使用字符流需要先创建字节流，但它不是套接流

~~~
FileInputStream fis = new FileInputStream("file1.txt");
InputStreamReader dis = new InputStreamReader(fis);
~~~

3.遇到字母时，自动扩充成2个字节存放 ；  

   遇到汉字时，两个字节合成一个字符

4.输入流   Reader ；输出流   Writer

~~~
BufferedReader reader = new BufferedReader(dis);
String s;
while( (s = reader.readLine()) != null )//读取一行字符  
    {   System.out.println("read: "+s);   }
~~~

## GUI-事件驱动

#### AWT包

抽象窗口工具包，java做界面，对象可见
![image-20220712201101552](https://user-images.githubusercontent.com/107198282/181004589-a0d48512-5dc5-4395-af88-e935e3723760.png)



##### 容器Container

1.框架（Frame类）

- Frame():创建一个不含标题的标准窗口
- Frame(String Title): 创建一个含有标题的窗口，这个标题是由参数title指定的。
- setSize()方法来设置窗口的大小
- setVisible()来显示窗口。

创建Frame窗口后，需要调用setSize()方法和调用setVisible()

2.面板Panel

面板：可以将许多组件组合起来的一种容器。

~~~
PanelTest p= new PanelTest();
Frame f=new Frame("正在测试面板！");//创建框架
f.add(p);
f.setSize(300,200);
f.setVisible(true);//设置可见
~~~

#### AWT组件

<img src="E:/Program Files/Typora/img/image-20220712201101552.png" alt="image-20220712201101552" style="zoom:50%;" />

##### 标签

- Label( ) : 新建一个空标签

- Label(String labeltext): 新建一个包含给定文本的标签

  ~~~
   Label lblName=new Label("名称 :");
  ~~~

- Label(String labeltext, int alignment) :新建一个包含给定对齐方式的标签

##### 文本域

- TextField() : 新建一个文本域 

- TextField(int columns) : 新建一个包含给定列数的文本域

  ~~~
  TextField txtName=new TextField(20);
  ~~~

-  TextField(String s) : 新建一个包含给定字符串的文本域 

- TextField(String s, int columns) : 新建一个包含给定字符串和列数的文本域 

##### 文本区

- TextArea( ) : 新建一个

  ~~~
  TextArea txtComment=new TextArea();
  ~~~

- TextAreaTextArea(int rows, int cols) : 新建一个包含给定行数和列数的

- TextAreaTextArea(String text, int rows, int cols) : 新建一个包含给定字符串、行数和列数的TextArea

##### 按钮

- Button() : 新建一个空的按钮
- Button(String text) : 新建一个包含给定字符串的按钮

~~~
Button btnOk=new Button("确定！");
Button btnCancel=new Button("取消！");
~~~

##### 复选框

- Checkbox()：创建一个空的复选框，且未被选中      
- Checkbox(String text)：创建一个用给定字符串作为标签的复选框，且未被选中
- Checkbox(String text,Boolean on)：创建一个标签由参数text指定的复选框，允许通过参数on设定复选框的初始状态。

~~~
 CheckboxGroup cg=new CheckboxGroup(); 
 Checkbox r1=new Checkbox("专科",cg,false);//加入到复选框组
~~~

创建单选按钮，需要先创建一个 CheckboxGroup 对象，然后将其加入到复选框组

##### 选择框

创建

~~~
Choice moviestars = new Choice( ); 
moviestars.addItem("安东尼奥.班德拉斯");
moviestars.addItem("莱昂纳多.迪卡普尼奥");
~~~

#### 布局管理器

##### 分类

FlowLayout（流式布局）

BorderLayout （边界布局）

GridLayout（网格布局）

GridBagLayout（网格包布局）

CardLayout （卡片布局）

##### FlowLayout

- FlowLayout( ):生成一个默认的流式布局

- FlowLayout(int alignment):可以设定每一行组件的对齐方式 

- FlowLayout(int alignment,int horz,int vert):可以设定组件间的水平和垂直距离

  ~~~~
  setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
  ~~~~

##### BorderLayout

![image-20220712203525276](https://user-images.githubusercontent.com/107198282/181004704-73bd42ec-c61d-4c76-9c31-c9d4dce45d02.png)

- BorderLayout( ):生成默认的边界布局
- BorderLayout(int horz,int vert): 可以设定组件间的水平和垂直距离

~~~
Panel p1 = new Panel();
p1.setLayout(new BorderLayout());
Button btn1 = new Button("east");
p1.add(btn1, BorderLayout.EAST);
add(p1);
~~~

##### GridLayout

![image-20220712203554617](https://user-images.githubusercontent.com/107198282/181004770-013885e2-e8e2-4d39-b866-5d670428b22f.png)

- GridLayout():生成一个单列的网格布局   

- GridLayout(int row,int col):生成一个设定行数和列

  ~~~
  	Panel p1 = new Panel();
  	p1.setLayout(new GridLayout(2,2));
      Button btn1 = new Button("Button1");
  	Button btn2 = new Button("Button2");
  	Button btn3 = new Button("Button3");
  	Button btn4 = new Button("Button4");
  	p1.add(btn1);
  	p1.add(btn2);
  	p1.add(btn3);
  	p1.add(btn4);
  	add(p1);
  ~~~

##### GridBagLayout

1.创建网格包对象，设置布局

~~~
	Panel p1 = new Panel();
   	GridBagLayout gridbag = new GridBagLayout();
    p1.setLayout(gridbag);
~~~

2.创建网格包布局管理器

~~~
//网格包布局管理器
GridBagConstraints ct = new GridBagConstraints();
~~~

3.

~~~~
ct.fill = GridBagConstraints.BOTH;//填充整个区域
~~~~

4.设置坐标，添加到面板

~~~
    ct.gridx = 0;//向右
	ct.gridy = 0;//向下
	ct.gridwidth = 1;//宽度
	//把btn1组件按照ct对象的布局信息设定到网格包布局中
	gridbag.setConstraints(btn1, ct);
	//把btn1组件按照网格包布局方式添加到p1面板上
	p1.add(btn1);
~~~

