------->6.27

## 系统属性

- Java中系统属性就是Java的环境变量
- System.getProperties()方法会返回系统属性值(Properties对象封装） 。
- Properties对象的getProperty()方法返回一个String来代表系统属性。

## 文件存储

1.properties文件

- 安全：只有自己能进入自己的文件
- 并发：不受锁限制
- 简单：不需要加载驱动等
- 可移植性好

缺点：对算法、内存要求较高

2.propertiesl类数据结构：java.util.properties中，hashtable的子类，key value

3.Properties类实现了从名字到值的映射

~~~
virtual_path=examples/
oracle_url=jdbc\:oracle\:thin\:@localhost\:1521\:O920
oracle_user=zs
file_path=c\:\\cctvfiles\\
oracle_name=O920
oracle_pwd=tiger
~~~

propertyNames()方法返回一个包含所有属性名的Enumeration对象

4.getProperty()方法返回一个代表该属性值的字符串

~~~
	//props对象中取得值
	this.oracle_url  = this.props.getProperty("oracle_url");
~~~

5.使用load()或store()方法能从文件==读入==属性集或将属性集==写入==文件

~~~
	File f=new File(".\\OracleSetup.properties");//当前目录下
	FileInputStream in = new FileInputStream(f);
	props.load(in);//读取数据
~~~

~~~
 	props.setProperty("oracle_user", "tom");//修改
	props.setProperty("password", "1111");
	try{
		FileOutputStream out = new 
		FileOutputStream(".\\Tom.properties");
		//文件字节输出流，有，删了重新建，没有，新建一个文件
	    props.store(out, ".\\Tom.properties");
		//存储  输出流地址  文件
		out.close();
		}
~~~

## 控制台输入/输出

1.类 System字段 

In “标准”输入流。 public static final InputStream in

Out  “标准”输出流。 public static final PrintStream out

err标准”错误输出流 public static final PrintStream err

- System.out可向标准输出设备输出 它是一个PrintStream对象
- System.in可从标准的输入设备输入它是一个InputStream对象
- System.err可向标准的错误设备输出它是一个PrintStream对象

## 向标准设备输出

1.方法：使用System.out.println/System.out.print两个常用的方法

2.区别：

- println()方法将参数打印出来，并加上”\n”字符
- print()方法，打印参数，但不加新行

3.相同：

- print和println方法对多数简单数据类型进行了重载(boolean, char, int, long, float, double)和char[], Object以及String
- print(Object)或println(Object)将会调用该对象的toString()方法，打印它的返回字符串

## math类

1.在java.lang包中，final，对外不提供构造方法，所有的属性、方法都是静态
