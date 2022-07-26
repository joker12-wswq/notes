## 运算符

#### 1.分类

分为单目、双目、三目运算符（目：运算量）

#### 2.具体

![image-20220606170602474](E:/Program Files/Typora/img/image-20220606170602474.png)

#### 3.算术运算符

相除    /      取商

~~~java
	7/5 -->取商，结果为1
	整数/0-->运行，算数异常
	0/0.0=NaN  无穷大
	17. 0/0 = Infinity  正无穷
	-17.0/0 = -Infinity  负无穷
~~~

1取余    %--->有周期性地需要哪些数

~~~java
	17%4=1
	17%18=17
    0%17=0
    0%0--->java.lang.ArithmeticException算数异常
    17.6 % 4-->整数取余，小数不变
        
    System.out.println("17.6 mod 4="+17.6 % 4);//1.6000000000000014
	System.out.println("17.6 mod 0 ="+ 17.6 % 0);//NaN
	System.out.println("-17.6 mod 0="+ -17.6 % 0);//NaN
	System.out.println("0.0 mod 0="+0.0 % 0);//NaN
~~~

---------->            2022.6.7

#### 4.关系运算符

==    判断两个值是否相等，按内容比较，真正对内存操作（地址比较）

#### 5.==、equals区别

相同：都进行比较运算

1. ==    

   按变量的内容比较

   ~~~java
     	 Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        System.out.println("n1 == n2 :" + (n1 == n2));
   	 //false，比较n1与n2的内容,他们的内容为地址
   ~~~

   比较类型：基本类型、引用类型

2. equals  

   对象比较

   ~~~java
   	System.out.println("n1.equals(n2) :" + n1.equals(n2));
   	//true，所指对象的类型、属性值相同，返回true
        System.out.println("n2.equals(n1) :" + n2.equals(n1));//true
   ~~~

   比较类型：引用类型

   null，出现空指针异常（表单.equals）--->常量.equals变量

==final== 只能通过构造方法初始化

#### 6.==Integer特点==

~~~java
4-3     Equivalence.java
    
     Integer n1 = new Integer(47);
     Integer n2 = new Integer(47);
     System.out.println("n1 == n2 :" + (n1 == n2));//false
~~~

~~~java
		//integer自动装箱特点👀👀👀👀👀👀
		n1=300;n2=300;//自动装箱,new Integer()
	    System.out.println("***n1 == n2 :" + (n1 == n2));//false
	  	System.out.println("______________");
	 
	    n1=100;n2=100;//自动装箱,-128~127中，在常量池创建
	    System.out.println("####n1 == n2 :" + (n1 == n2));//true
~~~

#### 7.==String特点==

1.创建对象

~~~java
	   String str1;
	   str1=new String("Hello");
	   //由于new String() 在堆里创建一个对象，由于""，在常量池里创建一个
	   String str2 = new String("Hello");
	   //由于new String() 在堆里创建一个对象，常量池已经存在Hello
	   System.out.println(str1 == str2);//false
~~~

2.

~~~java
	System.out.println(str1 == "Hello");//false，堆内地址与常量池比较
~~~

![案例4-3 Equivalence 内存图](E:/Program Files/Typora/img/案例4-3 Equivalence 内存图.JPG)

#### 8.初始化与赋值

赋值，可以多次

初始化，1次

~~~
	   String str1;
	   str1=new String("Hello");//赋值，将该对象的地址赋给str1
	   
	   String str1=new String("Hello");
	   //String("Hello")初始化，用一个对象类型的变量来接对象的地址
~~~

File、Data、String、Integer   复写了equals
