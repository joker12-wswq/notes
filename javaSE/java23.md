---------->7.7

## 线程

#### 1.进程与线程

进程：程序执行的过程

线程：一个进程中多条执行线索，

目的：更好地进行多用户并发操作

不控制线程，容易造成数据混乱

#### 2.虚内存

class文件加载到方法区的类代码区，==jvm创建主线程==，通过字节码文件找到主方法，在栈内开辟主线程的主方法栈帧

#### 3.线程的生命周期

![image-20220707161422183](https://user-images.githubusercontent.com/107198282/181003951-2c10bcae-3d0c-4560-b54a-ab62e3acdd68.png)

~~~
 //一个线程类
 public class Mathine extends Thread;
~~~

###### 新建

新建态，占用堆空间

~~~
Mathine mathine1 = new Mathine();
Mathine mathine2 = new Mathine();
~~~

###### 就绪

线程处于就绪态，可以多个，分配（资源）栈空间、指令计数器pc

~~~
mathine2.start();
~~~

###### 运行

~~~
mathine1.run();
~~~

###### 阻塞

线程阻塞态（主动放弃、等待），资源没有释放，将栈空间地址、指令计数器地址交给寄存器
阻塞后是就绪

~~~
Thread.sleep(100);
yeild中断   运行-->就绪  时间片、让步
~~~

###### 死亡

死亡，释放资源

#### 4.线程的调度与优先级

###### 优先级

高优先级先调度，同优先级随机

###### 调度

线程随机调度，时间片不同，轮转

###### 设置线程名

~~~
 mathine1.setName("m1");
 mathine2.setName("m2");
~~~

###### 设置优先级

~~~
//设置线程优先级，主线程5级，最高10级
 mathine1.setPriority(Thread.MAX_PRIORITY);
 mathine2.setPriority(Thread.MIN_PRIORITY);
~~~

###### 设置后台线程

前台线程结束后台线程随即终止

~~~
MyThread mt=new MyThread();
//设定mt线程为后台线程
mt.setDaemon(true);
~~~

#### 5.例题

利用内部类实现多线程对同一个实例变量的访问

~~~
public class MultiThread3
{
	public static void main(String[] args)
	{
		MyThread mt=new MyThread();
		mt.getThread().start();
		mt.getThread().start();
		mt.getThread().start();
		mt.getThread().start();
		 
	}
}

class MyThread  
{
	int index=0;
	//私有内部类
	/***************************************/
	private class InnerThread extends Thread
	{
		public void run()
		{
			while(index<=10)
			{   
				System.out.println(Thread.currentThread().getName()+":"+index);
			    index=index+1;
			}
		}
	}
	/***************************************/
	Thread getThread()
	{
		return this.new InnerThread();
	}
	 
}
~~~

