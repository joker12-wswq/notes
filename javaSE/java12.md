--------------->6.15

## 应用

#### 覆盖\重写

- 子类重写了继承父类的方法，调用时，使用的是重写的方法？

  调用的是重写的方法，不再对父类作静态绑定

- 重写的父类被子类继承的方法能否访问父类的私有属性？不能的话，如何来访问？

  不再对父类作静态绑定,故不能访问父类的私有属性

  ~~~java
  	Person t = new Teacher();
  	//对象是Teacher类型，赋值变量类型是Person
  ~~~

  ~~~java
  	使用super用于实例方法
  	//return super.showName();
  	//super找父类定义的，传当前对象的地址，不能用于静态方法（没有this指针）
  ~~~

  注意：super不是父类对象的地址，因为没有父类对象

###### 造型注意

要求：有上转型、造型前需要进行判断（instanceof）、是对变量内容造型

方法上转型：父类定义的方法，看不到子类新增的

对象上转型：Person t = new Teacher();

~~~~java
 if(myDog instanceof Dog){//判断myDog所指对象的类型是否为Dog类型
    	 ((Dog)myDog).Bark();//变量内容造型
    	 ((Dog)myDog).Dog();
              //myDog.Dog();//变量myDog类型没变化
		      //myDog.Bark();
	}
~~~~

~~~java
	myDog.bite(yourDog);//传了this、yourDog两个参数
~~~

#### 访问控制

private--->Detfault--->proteced--->public

| **修饰符**    | **同一个类中** | **同一个包中** | **子类中** | **全局** |
| ------------- | -------------- | -------------- | ---------- | -------- |
| **private**   | Yes            |                |            |          |
| **default**   | Yes            | Yes            |            |          |
| **protected** | Yes            | Yes            | Yes        |          |
| **public**    | Yes            | Yes            | Yes        | Yes      |

------------->6.16

## super关键字

==一定出现在继承中==

表示和父类动态绑定的方法和属性

1.指定调用的构造方法

创建子类对象，调用父类的哪个构造方法？

​	没有super,默认调用无参数的构造方法

​	有super(参数)--->在子类构造方法中的==<u>第一行语句</u>==（必须）      例：6-3

~~~java

super();//显示指定调用父类的默认构造方法
	super(200);//显示指定调用父类的有参构造方法
~~~

压栈：创建子类对象时，先对需要调用的构造方法进行压栈，由下往上压，先进后出

栈

​			1.主方法栈帧

​			2.构造方法栈帧

​			3.xxxx

调完后，出栈的顺序：3-->2-->1

2.

- 调用父类中定义的成员方法

  ~~~java
  	//方法覆盖
  	public String showName()
  	{
  		System.out.println("Teacher123");
  	//return name+"老师";//失去动态绑定，找不到name
  	  return super.showName()+"老师";
  	}
  ~~~

- 访问父类中定义的属性

## this

代表：创建对象的地址

出现：new的时，在构造方法之前出现

带this：对象的属性和方法自动都带this指针

不带this：主方法、静态方法、

应用：

构造器中指对this所指对象的初始化

方法中指调用该方法对象的引用

~~~java
	account.createAccount().getAccountId());//方法链
~~~

出现构造方法的==<u>第一条语句</u>==--->在调用构造方法之前，==先==调用本类中重载的（构造）方法

~~~java
	//调用构造器Person()		
	this();
	//调用构造器Person(String theName)
	this(theName);
~~~

注意：创建对象不一定调一个构造方法

​			this、super不能同时出现在构造方法中

## 语句块

不属于某一个代码，独立出现

作用：初始化对象的属性

调用：类似顺序执行

出现在构造方法之前时，调用比构造方法早，调用时会把对象的地址传过来；

受位置的影响,如果初始化块出现在属性之前，先执行初始化块，在为属性开辟空间

语法：{  }，一般置于类的末尾

~~~java
	例 6-9
	//初始化块
	{
		this.name = "Tony Blair";
		this.age = 50;
	 
		this.sex = "Female";
		System.out.println("初始化块执行后：name="+this.name
			+" ,age="+this.age+" ,sex="+this.sex);
      System.out.println(t3);
	}	
~~~



