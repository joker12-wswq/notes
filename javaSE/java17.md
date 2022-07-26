------------->6.23

## 异常处理

1.Error（错误）和 Exception（异常）都是 java.lang.Throwable 类的子类，在 [Java](http://c.biancheng.net/java/) 代码中只有继承了 Throwable 类的实例才能被 throw 或者 catch。

  分类：（父类throwable）异常事件分为错误、异常（程序运行、资源等问题）

  目的：使程序正常运行

2.运行：通过异常，找到问题，修改、解决问题，可通过编写代码解决

   编译：预案（处理异常的代码），屏蔽资源问题

3.常见异常

RuntimeException

- ArithmeticException：数学计算异常

  ~~~
  	c = 10/0;//算数异常
  	System.out.println(c);
  ~~~

- NullPointerException：空指针异常

  ~~~
  	Date d1 = null;//没引入util包
   	System.out.println(d.getTime());//空指针异常
  ~~~

- NegativeArraySizeException：负数组长度异常

  ~~~~
  	int[] arr = new int[-10];//负数组长度异常
  ~~~~

- ArrayIndexOutOfBoundsException：数组索引越界异常

  ~~~
  	//打印不存在的数组长度，或打印还没赋值的数组
  	int[] ar = new int[10];
  	System.out.println(ar);
  ~~~

- ClassNotFoundException：类文件未找到异常

  根据路径，class文件没有找到
  
- ClassCastException：造型异常----->instanceof解决

  上转型---->下转型

  小的放入大的里
  
  小的放入合适的里
  
  ~~~
  	myAnimal=new Dog();//上转型，失去自己子类特有、新增的属性和方法
  	((Dog)myAnimal).Bark();//下转型，拥有了自己子类特有、新增的属性和方法
  ~~~
  
  造型异常
  
  ~~~
  	Parent p = new Parent();
  	Child1 c1 = (Child1)p;
  ~~~

IOException

- FileNotFoundException：文件未找到异常

  ~~~
  	//创建文件输入流时，必须对其进行捕获或声明以便抛出
  	FileInputStream fin = new FileInputStream("c:/a.txt");
  ~~~

- EOFException：读写文件尾异常

- MalformedURLException：URL格式错误异常

- NumberformedException:   格式错误异常

- SocketException：Socket异常

4.处理异常原则  try  catch finally

throws  

- ==出现==在方法的方法名之后，可跟一个或多个异常类（不处理xxx异常）
- 出现异常，创建异常对象，通过throw抛给虚拟机运行池，首先当前方法找是否有处理异常的代码，没有则抛给调该方法的方法，处理异常
- 用于声明可能会出现的异常

throw     

- 出现在方法内，只能跟一个异常对象
- 用于拋出异常

try

- 一个try对应多个catch拦截异常
- 放一些有问题的代码，try块不要过大用以捕获可能发生的异常
- 可能出现异常的代码如若在try外，不能被cath拦截，出现异常，终止程序
- 如果有一行代码出现异常，该代码的后续代码不会执行

catch     

- catch 后的`( )`里放匹配的异常类，指明 catch 语句可以处理的异常类型
- ==顺序成正金字塔（由小到大），最后是Exception==
- 处理完后，继续执行后续代码

finally

- 无论是否出现异常，程序都会执行finally中的代码
- 当多个return时，返回值栈会做压栈处理，后进先出，只执行一个return，清空栈
- 正常流程return前，执行finally，finally中有return会把==异常流程的返回值被覆盖、正常流程的return覆盖或隐藏异常==，故不要加return

