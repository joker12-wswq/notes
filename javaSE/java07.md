## 引用类型

（4个字节）引用类型变量的值是某个对象的==句柄(地址this)==，而不是对象本身·

```java
3-5  TestStudent.java

     //sl是用来存放Student类型对象的地址，而不是对象本身 ✨ 
     s1 = new Student("Lisa","Male",1,18);
       
     Student s2=null;
     s2 = s1; //把S1中存放的内容赋值给S2;java只有一种传递，就是我，值传递；✨  

```

栈里的局部变量要是没有赋值，那就是垃圾值

~~~java
例如：
int n;//没有赋值，没有使用的功能，需要赋值使用
~~~

## 变量

内存的一块空间

#### 分类

每个变量都有类型，根据有效范围，分为（<u>成员</u>）<u>全局</u>变量、局部变量。

==就近==：成员变量可与局部变量的==名字相同==，此时成员变量将被隐藏，即这个成员变量在此方法中暂时失效，==只取局部变量的值==。

~~~java
1 public class Man{
2      static int age=20;//👀如果没有static,则错误: 无法从静态上下文中引用非静态 变量 age
3      public static void main(String[] args){
4          int age=22;
5    //打印结果为：He's 22 years old this year .
6          System.out.println("He's "+age+" years old this year.");
7      }
8 } 
~~~

~~~java
 👀如果main方法不是静态的，则报错误：
           main 方法不是类 TestVar 中的static, 请将 main 方法定义为:
           public static void main(String[] args)
~~~

==main方法必须是静态的==，[参考](http://c.biancheng.net/view/972.html)

注意：

局部变量必须==初始化==，否则无法编译（<u>当==使用了==局部变量，但没有初始化时</u>）

全局变量未初始化时，系统会自动根据类型初始化

~~~java
Student s1 = new Student();//隐式初始化
~~~

#### 局部变量

1. 概念：定义在类中==方法、语句==（使用）内的变量,，不能加static。

2. 开辟空间的地区：方法==栈==帧

3. ==作用范围：只在当前代码体中有效==

   原因：每当程序调用方法时，系统都会为该方法建立一个方法栈，其所在方法中声明的变量就放在方法栈中，当方法结束系统会释放方法栈，其对应在该方法中声明的变量随着栈的销毁而结束，这就局部变量只能在方法中有效的原因

4. 开辟空间时间：使用方法、语句块时

5. this指针：无

#### 全局变量

作用范围：==整个类==中都有效

重名：静态全局变量与实例全局变量==不能重名==

##### 静态全局变量

~~~java
Static int i =233;//静态全局变量,用static修饰，程序执行完，释放空间
~~~

1. 调用：类、具有静态全局变量类的对象（共有）

2. ==堆内==存放区域：==每个对象的属性空间里==都有一个属性存放着对静态全局变量的引用
3. ==方法区==存放区域：数据区-->静态数据区
4. 概念：被static修饰的全局成员变量
5. 局限：耦合性过强
6. 次数：只开辟1次空间
7. 开辟时间：==编译==，加载类，做静态绑定
8. this指针：无

##### 实例全局变量

~~~java
Double  k;//实例全局变量，没有static修饰
~~~

1. 概念：没有被static修饰的全局成员变量

2. 开辟空间的地区：==堆==-->对象空间（与对象关联时）
3. 开辟时间：创建对象时
4. 开辟次数：与创建对象次数有关
5. this指针：有

##### 例如

~~~java
3-6  TestGlobalVar.java
~~~

内存图

1. 开辟空间顺序：.class--->main()

   ​						 方法区--->栈--->堆

   ​					堆 方法空间   4个字节   地址

2. 初始化：栈内不会自动初始化，堆内可以

3. 栈内方法调用完后，会自动释放空间

![image-20220601165129102](E:/Program Files/Typora/img/image-20220601165129102.png)

## 传值

1.一编译，对静态main方法和静态变量做绑定

2.贫血模型

概念：只有属性没有提供访问数据的方法的对象

缺点：没有安全性，不符合==隐藏和封装==👀（即private以及提供访问方法）

​		   如果有私有属性，外部无法访问

3.尽量不要在方法内创建对象，当方法释放了，对象地址消失，成为游离对象。--->    内存泄漏

通常在主方法，生存周期长的方法创建

==两个变量指向同一个地址==

this---> 在构造方法之前就有了

~~~java
Data d1 = new Data();
Data d2 = d1;
~~~

~~~java
例子  3-8
	 3-9	n.n  ---> n 代表对象的地址，调用n所指对象的n属性
~~~

重载    编译时进行

## 编码风格

■Package (包): 
	package banking;
	package zte.nc;
■Class (类)
	class Student
	class TestStudent
■Interface (接口)
	interface Person;

变量，驼峰命名

## 对象的地址

例如：

~~~java
public class AddessTest {
	public static void main(String[] args) {
		Object obj = new Object();
        System.out.println(obj);
        System.out.println(Integer.toHexString(obj.hashCode()));
	}
}
~~~

运行结果：

java.lang.Object@15db9742--->哈希地址
==<u>15db9742</u>-==-->16进制散列地址
