## 关键字

###### 关键字列表（51个）：

![image-20220531085530596](E:/Program Files/Typora/img/image-20220531085530596.png)

###### 注意：

- 所有Java关键字都是<u>*小写的*</u>，TURE、FALSE、NULL等都不是Java关键字;
- goto和const虽然从未被使用，但也作为Java关键字保留;

###### 概念：

Java中<u>一些赋以特定的含义、并用做专门用途的单词</u>称为关键字(keyword)

###### <font color='red'>创建对象方式</font>：

反射、new、反序列化、克隆、方法

## 数据类型

#### 数据类型

###### 概念

变量开辟空间的一种方式

###### 分类

![image-20220531103801510](E:/Program Files/Typora/img/image-20220531103801510.png)

#### 基本数据类型

放一个确切的数值

| 类  型 | 占用存储空间                                    | 表数范围                              |
| ------ | ----------------------------------------------- | :------------------------------------ |
| byte   | <u>1字节</u> <font color='red'>(8位bits)</font> | -128 ~ 127                            |
| short  | 2字节                                           | -215 ~ 215-1 （-32768~32767）         |
| int    | 4字节                                           | -231 ~ 231-1 (-2147483648~2147483647) |
| long   | 8字节                                           | -263 ~ 263-1                          |

###### <font color='gree'>整型</font>

Java语言整型常量的三种表示形式:

- 十进制整数，如12, -314, 0。
- 八进制整数，要求以0开头，如012
- 十六进制数，要求0x或0X开头，如0x12

Java语言的整型常量默认为int型，如:int i =3;

声明long型常量可以后加‘l'或‘L’，如:long |= 3L;



###### <font color='gree'>浮点型</font>

Java浮点型常量默认为double型,如要声明一个常量为float型，则需在数字后面加f或F，

如： double  d = 3.14;		float  f = 3.14f;



###### <font color='gree'>字符型(char)</font>

可与int通用，2个字节

- 单个字符，单引号字符,如：'A'--->65,'a'-->97
- 转义字符
- Unicode值

| 字符                       | 整型 | 十六进制 | Unicode值（2字节） |
| -------------------------- | ---- | -------- | ------------------ |
| ‘A’                        | 65   | 41       | \U0041             |
| ‘a’                        | 97   | 61       | \u0061             |
| ‘0’                        | 48   |          |                    |
| 空格’ ‘                    | 32   | 20       | \u0020             |
| 回车‘\r’(本行的首位置)     | 13   | d        | /u000d             |
| 换行‘\n’(下一行的当前位置) | 10   | a        | /u000a             |

~~~java
3-3  CharTest.java
public class CharTest{
  public static void main(String args[]){
    char ch1 = 48;
    int ch2 = '张';
    char ch3 = '\"';
    char ch4 = '\u0041';
    int ch5= '\n';
    int ch51= '\r';
    char ch6=0x61;
    System.out.println(ch1);//0
    System.out.println(ch2);//24352
    System.out.println(ch3);//"
    System.out.println(ch4);//A   
	System.out.println(ch5);//10
	System.out.println(ch51);//13
    System.out.println(ch6);//a
  }
}
~~~



###### <font color='gree'>混合运算</font>

除了boolean类型，其他七种基本类型可以相互运算

```java
3-41  DataOper.java

		double db;
		float fa=5.1f;
		byte b1=3;
		byte b2=120;
		char c=48;
		short s=10;
	    long lo=5l;
	    int a=3;
		//只要表达式中，有一个数据时double，那么生成的结果就是double类型的，
		//必须用一个double类型的变量接收
		db=c+b2+s*a+5l+3.5;

		/**
		 1.Byte,char,short一旦参加运算，就会自动转化为整型
         a=b1+c+s;  
		 int b=b1+b2+c;
		 2.整型的精度比浮点型高，所以两者运算的时候，产生结果必须由浮点型变量接收
		 fa=b1+c+s+a+lo+1.5f;
		 3.整型类型之间运算，一旦其中有Long，结果就必须要用long类型变量接收
		 lo=b2+c+s+a+lo;
		 4.强转不会四舍五入
		 double d = 1.934;
		 int i=(int)d;//i=1
		 5.但是单目运算（j++;），复合赋值运算不会改变运算量的类型✨
		 m=k++; ✨
		 k+=1;✨//逆波兰式--->k=k+1;会改变

	    */
	
    	System.out.println("db="+db);
```





