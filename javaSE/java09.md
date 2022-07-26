-------->6.9

## 关系和布尔运算符

#### 逻辑运算符

（与）& ：见false为false

（或）|:见true为true

（非）！

（异或）^       true^false--->true  

（短路或） ||

(短路与)  &&

#### 区别

（短路或） ||、(或）|

~~~java
	 System.out.println("短路或运算");
     System.out.println(sc.Ma()||sc.Mb()||sc.Mc());//有true，后面不执行
     System.out.println("非短路或运算");
     System.out.println(sc.Ma()|sc.Mb()|sc.Mc());//有没有true，后面都执行
~~~

~~~java
	Student s=new Student();
	//s=null,name=null;
	if((s!=null)&(s.getName().equals("zs")))//后面都执行,容易产生空指针异常
	{
	}
~~~

~~~java
	//if((s!=null)&&("zs"==(s.getName()))//永远为false
	// if((s!=null)&&((s.getName()).equals("zs"))//可能空指针异常
 	//正确写法
	 //if((s!=null)&&("zs".equals(s.getName()))
	//}
~~~

#### 位运算

java向右循环移位

右移3位----->最快÷8（2^3）

~~~java
		/*位运算*/
		//只适用于整型
        int a1= 128;//2^7,
        int b1= 33; 
         //int c1=a1/((int)Math.pow(2,b1%32));
		int c1 = a1 >> b1;//右移33位，a/2^b%32，向右除、向左乘2的次方
                          //long  %64
~~~

#### 字符串连接符"+"

~~~java
		int a=10;
		double x = 9.987;
		double y = 1;
		double t = x+y;
		String s = "Price is:"+x;
		String st = "Total Price is:"+t;
		System.out.println(s);
		System.out.println(st);
		System.out.println(""+x+y);
		//从左向右，有+、字符串，x,y变成String类型,9.9871.0
		System.out.println(x+y+"");//x+y,算术运算，第二个+，连接符
~~~

## 流程控制

#### 分支语句

##### switch

~~~java
switch(n+1)//能自动转换成int的值，不是布尔值,char、byte、short、int，不能为long
~~~

#### 循环语句

##### 对比do while 和while的区别

do while     先执行，再判断

while         先判断，再执行

##### Break   And     Continue

~~~
	if(i%2==0)break;//跳出当前循环
	if(j%2==0)continue;//跳出本次循环
~~~

##### Break     Label

非正常出口

~~~java
outer://label标号,内外层循环上，符合标识符命名
      for(int i = 0;i<10;i++)
      {
        System.out.println("Outer loop:");
        inner:
          while(true)
          {//从键盘缓冲区读取一个字符的ascii码
            int k = System.in.read();
			//输入a,回车，读取到了'a','/r','/n'三个字符，输出对应字符的ascii码
			//输入b,回车，读取到了'b',
            System.out.println("Inner Loop:"+k);
            if(k=='b') break inner;//跳出标识的循环
            if(k=='q') break outer;//执行结束，缓冲区字符自动清空（释放）
          }
      }        
  }
~~~

##### continue  label

模式匹配

子串在父串中首次出现的位置，比较次数：父串长度--字串长度

跳出标识本次的循环

## 数组

概念：定义了多个连续的<u>内存空间</u>（堆内）

定义：两种方式

~~~
	类型[] 名称
	类型  名称[]
	int[] arr = new int[5];//声名的同时分配空间
	int[] arr2 = {1,2,3,4}//直接声名赋值
~~~

~~~
		int[] a;//声名
	    a = new int[5];//分配空间
	     a[0]=20;//赋值，元素名a[0]，表示内存空间
		 //变量名、元素名寻址方式不同
		 //直接     间接：首地址+下标
		 System.out.println(a[4]);//0
		System.out.println("数组长度："+a.length);//5
		System.out.println(a);//[I@15db9742，数组名，首元素地址
~~~



