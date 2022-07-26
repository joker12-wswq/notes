--------------->7.4

## String类

1.final类型，java.lang.String，代表一组不可改变的Unicode字符序列

2.字符串一旦创建不能改变，只有构造方法或创建子类对象才能设置

~~~
private final char value[];
~~~

3.创建方式

~~~
1.String x=new String("xx");
1）在堆区创建一个字符串对象。
2）在方法区字符串常量池创建一个字符串对象。

2.String x = "xx";
在方法区字符串常量池创建一个字符串对象，可被共享。
~~~

4.常用方法

创造新的字符串：------>堆

- kconcat：字符串的附加

  ~~~
  	String s1 = new String("HeIIo");//共2个
  	s1 = s1.concat(" 张三!");//HeIIoWorld张三! 
  ~~~

- replace、 replaceAll：字符串的替换

  ~~~
  	String s1 = new String("HeIIo");//共2个
  	String s2 = "World";//共3个
  	s1 += s2;//HeIIoWorld
  	s1 = s1.replaceAll("II", "aa");//HeaaoWorld张三! 
  ~~~

- substring：求子串

  ~~~
  	String a = "abcdceadt.java";
  	String s5=a.substring(1,9);//bcdceadt，从1-8
  ~~~

- toLowerCase：转换为小写

- toUpperCase：转换为大写

- trim：把字符串首尾空格删除

- split:把原来的字符串分割为几个字串

  ~~~
  	String s7="11:23:15";
  	//用“：”分割字符串
  	String[] ret=s7.split(":");
  	for(int i=0;i<ret.length;i++)
  	{
  		System.out.print(ret[i]+" ");}//11 23 15
  	}
  ~~~

~~~
	//从第二个字符起"a"首次出现的位置
		int n1=a.indexOf("a",2);//6
		 System.out.println(n1);
		 //从索引位置2处开始反向搜索
		 int n=a.lastIndexOf("a",2);//0
		System.out.println(n);
		//测试此字符串是否以指定的后缀结束
		System.out.println(a.endsWith(".java")); //true      
~~~

~~~
System.out.println(s1 + " ls index: " + s1.charAt(2));//a  
//该字符串的索引2是哪个字符
~~~

## StringBuffer类

1.代表一组可改变的Unicode字符序列

2.自带缓冲，16字节，超过自动扩容

3.StringBuffer类和String类比较 

 相同点:  

- 都用来处理字符串。  
- 都提供了length(),charAt(),subString()方法，且用法相同。  

不同点：  

- StringBuffer类对象可变，改变其缓冲区的方法不会创建新对象。如果空间不够可自动扩容。

- StringBuffer类没有==<u>覆盖</u>（没有重写，相当于等值比较==）equals()方法。   

- StringBuffer类不支持“+”连接运算，StringBuffer 类的 ==append() 方法==用于向原有 StringBuffer 对象中==追加字符串==   

- StringBuffer类覆盖toString()方法，但和String类覆盖 toString()方法实现方式不同。

  ~~~
  //先新建一个String对象，从该StringBuffer对象获取字符序列，最后由toString返回String对   象的地址
  	StringBuffer sb1 = new StringBuffer("Hello");
  	System.out.println("sb1 = " + sb1);
  ~~~

4.StringBuffer类和StringBuilder类比较

相同点：两者都是具有字符串缓存

不同点：

StringBuffer类是线程安全的

StringBuilder类是非程安全的



