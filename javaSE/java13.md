----------->6.17

## spuer、this、代码块

例：6-10

压栈确认构造方法调用顺序

调用构造方法时，会将参数传给构造方法

堆内顺序：子类没有继承的父类属性/子类继承的父类属性---->方法---->父类初始化块----->父类构造方法--->子类新增的属性---->方法---->子类初始化块--->父类构造方法

## 封装类

| 简单数据类型 | 封装类    |
| ------------ | --------- |
| boolean      | Boolean   |
| byte         | Byte      |
| short        | Short     |
| int          | Integer   |
| long         | Long      |
| char         | Character |
| float        | Float     |
| double       | Double    |

在java.lang包中，无状态，是线程安全的

将基本数据类型变为引用类型

不提供无参的构造方法

认为创建---堆区

1.特点：

1. final类型（不能被继承）
2. JDK1.5允许基本类型与封装类混合运算
3. 有对应的基本数据类型，
4. 有唯一属性value，只能构造方法初始化一次



~~~
	Short s=new Short((short)1);//不造型找不到合适的构造器
	Integer i2 = new Integer("10");//Character除外
	Integer j2 = new Integer("10");//Character除外
	System.out.println(i2==j2);//false，不是自动装箱
~~~

2.创建封装类对象

有对应的基本数据类型，可通过字符串形式（“”）创建（Character除外）

例如：

~~~
	Integer i2 = new Integer("10");//Character除外
~~~

3.封装类转换成字符串：toString

==字符串转换为基本类型的数据==：parsexxxx

Character、Boolean除外

~~~
	double d5=Double.parseDouble("123.3");
	int n=Integer.parseInt("123");
~~~

==字符串转换为封装类对象==:valueOf(" ")

Character、Boolean除外

~~~
 	 Double  d3=Double.valueOf("12.3");
	 Integer k3=Integer.valueOf("12");
~~~

参与运算，自动拆箱  

~~~
	Integer i1 = new Integer(10);
	Integer j1 = new Integer(10);
	int n3=i1+i2;//20自动拆箱  
~~~

👀equals引用类型比较

~~~
	Integer i2 = new Integer("10");//Character除外
	int j=10;
	System.out.println(i2.equals(j));//true自动装箱
	System.out.println(2.equals(i2));//基本类型，
~~~

即可装箱、也可拆箱，以拆箱为先

~~~
	Integer i1 = new Integer(10);
	int j=10;
	System.out.println(i1==j);//true自动拆箱  
~~~

## toString

任何一个类都有toString

object类中toString，返回---->类型@16进制地址的前几位（整体是个字符串）

调用：打印一个对象时，==默认调用==toString

hashCode()返回对象真正地址的前几位并转换为十进制整型

~~~java
👀 person1=null;
	//没产生异常，调用了String.valueOf(person1)，
	//该方法return (obj == null) ? "null" : obj.toString();
	//如果是null返回null，不是则toString()
	System.out.println(person1);
	//产生NullPointerException异常
    System.out.println(person1.toString());
~~~

集合、异常、Data、String、Integer等==复写了toString==

## equals、==

File、Data、String、Integer   复写了equals

任何一个类都有equals，不复写相当于等值比较==，复写equals，一定要复写hashCode()==



