-------------->6.22

## 内部类

1.概念：一个类中定义在一个类的内部或方法的内部

2.实例/全局<u>内部类（inner class）</u>：一个类中定义在一个类的内部

   方法/局部内部类：一个类中定义在方法的内部

3.组成---->内部类是一个类的组成

4.访问权限：外部类中的私有属性、方法可以被内部类访问

5.作用域：外部类的方法内或外部类内有效

6.对象创建：

外部类对象----->忽略内部类创建开辟空间（堆内）

内部类对象----->忽略外部类创建开辟空间（堆内），==自动拥有外部类对象的引用==，名称：外部类.this（一个空间存放）

- 外部类的外部：

~~~
	Outer1 a1 = new Outer1() ;//创建外部类对象
    Outer1.Inner oi=a1.new Inner();//创建内部类对象
~~~

~~~
 	this.size++;//内部类的size+1
    Outer1.this.size++;//外部类的size+1
~~~

- 外部类的实例方法内：

  ==方法内部类对象==，可以访问包含内部类的方法的局部变量、final局部变量

~~~
	Inner i = this.new Inner();//this指的是外部类
~~~

当==内部类私有==时，只在外部类的有效，创建对象，只能在外部类的实例方法内

方法不会执行类

7.字节码文件：外部类$内部类.class

8.特性：

- Inner class可以声明为抽象类 ，因此可以被其它的内部类继承。也可以声明为final的。
- 和外层类不同，Inner class可以声明为private或protected；
- Inner class 可以声明为static的，但此时就不能再使用外层封装类的非static的成员变量；
- 非static的内部类中的成员不能声明为static的，只有在顶层类或static的内部类中才可声明static成员

## 匿名内部类

1.匿名内部类只能创建一个对象

2.匿名内部类虽然没有类名，但系统会分配，有构造方法

~~~
//函数式接口
interface Haha {
    int test(int a,int b);
 }
~~~

~~~
	Haha lc = new Haha(){//创建实现了Haha接口的类的对象，变量类型为Haha
	public int test(int a,int b)
            {
                return (a+b);
			}
		};
~~~

匿名内部类

~~~
new Haha(){//创建实现了接口的类的对象
	public int test(int a,int b)
            {
                return (a+b);
			}
		};
~~~

3.Lambda表达式

~~~
	//接口的形参名    方法体
	//接口中有方法名  参数类型，表达式中省略了
	Haha lc1=(a,b)->(a+b); 
~~~

4.修饰符适用范围

|           | class | 属性 | 方法 | 构建器 | 自由块 | 内部类 |
| --------- | ----- | ---- | ---- | ------ | ------ | ------ |
| public    | Y     | Y    | Y    | Y      |        | Y      |
| protected |       | Y    | Y    | Y      |        | Y      |
| (Default) | Y     | Y    | Y    | Y      | Y      | Y      |
| private   |       | Y    | Y    | Y      |        | Y      |
| final     | Y     | Y    | Y    |        |        | Y      |
| abstract  | Y     |      | Y    |        |        | Y      |
| static    |       | Y    | Y    |        | Y      | Y      |



