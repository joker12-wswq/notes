---------->6.20

## static关键字

1. static修饰变量、方法和内部类
2. 类属性、类方法：static声明的属性和方法与类有关
3. ==static  不可用于<u>构造器</u>==（对象初始化）

- static{   }  静态代码块------>

  作用：给静态全局变量初始化

  即使没有静态全局变量，也比主方法早，调用一次

- {   }      创建几个对象，调用几次

不同：语法类似、调用次数

- static  变量    ---->具有静态全局变量的对象自动拥有静态全局变量的引用
- static  方法 ----->可以通过对象调，有得比较少，与静态变量类似

## 单例模式

1. 概念：一个类只有一个对象----->实现：内部创建对象，构造方法私有

2. 作用：数据共享

   ​			实现项目安全（配合线程同步）的重要手段

3. 缺点：降低项目并发性，增加耦合性

4. 实现：==----->线程不安全==

- 饿汉模式----->加载类时，就创建了对象

  三要素：构造方法私有、静态全局变量（存放对象地址）、静态方法（获取this）

  ==内存图形成环==

  存在问题：对象一直在堆内，不会释放 （可能一直没使用）

~~~java
     例:   7-3
/**单例--饥汉/饿汉模式*/
public class Singleton1 {
   public int count;
   private static Singleton1 instance=new Singleton1(10);
   
   private Singleton1(int n){//构造方法私有
   	this.count = n;
   	System.out.println("count = " + this.count);
   }
	
   public int getCount(){
   	return this.count;
   }
   
   public static Singleton1 getInstance(){
      //静态方法获取单例对象
	 return instance;//instance存放对象的地址
   }
}
~~~

- 懒汉/饱汉模式----->用的时候，再创建对象

  饿汉模式上的改进

## final

1. 应用：类、方法、变量之前

2. final 类：不可被继承，例如标准类库的类math、String、Integer

3. final 方法：能被继承，不能被覆盖

4. final 变量：

   final==静态==全局变量==只能显示初始化==不能赋值，必须初始化，勇

~~~java
	final static int a1=0;
	//TestFinal.a1=5;//final静态全局变量只能显示初始化不能赋值
~~~

​	 final==局部==变量（==形参==---->方法的参数）若无初始化只能赋值一次	

~~~java
	a4=5;
~~~

​	final==实例全局==变量不能赋值，只能用构造方法初始化

~~~java
	final int a2;  
	TestFinal tf = new TestFinal();
	//tf.a2=7;//final实例全局变量不能赋值
~~~

​	final==实例全局==变量 若没有显示初始化,只能通过构造方法初始化，不能重新赋值

~~~java
	final Aclass REF_VAR=new Aclass();
~~~

