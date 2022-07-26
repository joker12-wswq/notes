------------>7.8

#### 线程交互

##### 判断线程是否存活

~~~
//线程是否存活（没有被激活或死亡-->不存活）
boolean b=t.isAlive();
~~~

##### 终止线程

~~~
//终止线程，打破数据稳定性
 t.stop();
~~~

##### 中断线程

~~~
//中断线程，强行唤醒正在睡眠的线程，执行异常代码
t.interrupt();
~~~

##### 线程正常死亡

~~~
class ThreadDemo implements Runnable{
    private boolean flag = true;
    public void stopRunning(){
        this.flag = false;
    }

    public void run(){
        System.out.println("falg = " + this.flag);
        while(this.flag){
            try{
                Thread.currentThread().sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("线程被终止");
             return;
			}

            System.out.println("I love you");
        }
    }
}
~~~

~~~
ThreadDemo tt = new ThreadDemo();
Thread t = new Thread(tt);//启用tt对象的run（）方法
//定义方法，共用、修改标志flag,使线程正常死亡
tt.stopRunning();
~~~

#### 线程锁机制/线程同步

##### 线程安全问题

1.概述：多个线程访问同一个资源，可能出现业务安全问题

2.出现原因：

- 存在多线程并发
- 同时访问共享资源
- 存在修改共享资源

3.解决

解决线程安全问题，==先后依次==访问共享资源

加锁：把共享资源进行上锁，每次只能-个线程进入访问完毕以后解锁, 然后其他线程才能进来。

##### synchronized

每一个对象都有锁

检查锁，如果锁开启（离开语句块）了，进入语句块，然后关闭锁，执行完后，锁自动打开；如果锁关闭，在锁池等待

##### 块锁

1.块锁的对象可以为任意对象，生效区域为代码块

2.范围最小

3.锁对象的规范要求

- 规范上:建议使用==共享资源==作为锁对象。
- 对于实例方法建议使用==this==作为锁对象。
- 对于==静态方法==建议使用字节码(类名.class) 对象作为锁对象。

~~~
Object obj=new Object();//obj必须对于访问的线程来说是唯一的对象，对象类型没有要求
synchronized(obj){   操作共享资源的代码（核心代码）}
~~~

##### 方法锁

1.方法锁的对象为：调用该方法的对象，而且实例方法

2.当一个类的方法都有synchronized时，如果有一个方法上了锁，同一个对象的其他方法将不能被其他线程访问

3.格式

修饰符  synchronized   返回值类型   方法名称  (形参列表) {

操作共享资源的代码     }

4.同步方法底层原理

使用较多，锁对象藏起来了

- 同步方法其实底层也是有==隐式锁对象==的，只是锁的范围是整个方法代码。
- 如果方法是==实例方法==:同步方法默认用==this==作为的锁对象。但是代码要高度面向对象!
- 如果方法是==静态方法==:同步方法默认==用类名.class==作为的锁对象。---->反射对象

~~~
//对象锁池，this的锁，调用这个方法的对象
    private synchronized void sale(){
        if(this.tickets > 0){
             System.out.println(Thread.currentThread().getName() + " : tickets = " + this.tickets);
              try{
                Thread.currentThread().sleep(100);
            }
            catch(InterruptedException e){
                System.out.println(e);
           }
			  this.tickets--;
             System.out.println(Thread.currentThread().getName() + " : tickets after= " + this.tickets );
       }
    }
}
~~~

##### 静态/类锁

静态方法与类关联

锁谁：静态方法没有this，类的反射对象的锁

静态锁的==范围最大==

安全性与并发性相互作用的关系是相反的

##### lock锁

1.使用

~~~
private final Lock lock = new ReentrantLockO) ;//创建锁对象，唯一、不可替换
lock.lock(); //上锁
lock.unlock(); //解锁
~~~

2.lock锁

- 为了更清晰的表达如何加锁和释放锁, JDK5以后提供了-一个新的锁对象Lock,更加灵活、方便。
- Lock实现提供比使用synchronized方法和语句可以获得更泛的锁定操作。
- Lock是接口不能[接实例化,这里采用它的实现类ReentrantLock来构建Lock锁对象。

| 方法名称               | 说明                   |
| ---------------------- | ---------------------- |
| public ReentrantLock() | 获得Lock锁的实现类对象 |

3.Lock的API

| 方法名称       | 说明   |
| -------------- | ------ |
| void  lock()   | 获得锁 |
| void  unlock() | 释放锁 |

#### 生产者消费者问题

1.线程通信：

所谓线程通信就是线程间相互发送数据，线程通信通常通过共享一个数据的方式实现。

2.每一个对象除了有一个锁之外，还有一个等待队列

3.线程通信常见模型

- 生产者与消费者模型:生产者线程负责生产数据,消费者线程负责消费数据。
- 要求:生产者线程生产完数据后,唤醒消费者,然后等待自己;涌费者消费完该数据后，唤醒生产者,然后等待自己。.

##### wait()方法

==object类==的方法，开启锁池，将this对象从锁池放入等待池

##### notify()方法

==object类==的方法，将this对象从等待池放入锁池，==唤醒==正在等待的单个线程

##### notifyAll()方法

==唤醒==正在等待的多个线程

上述方法应该使用当前==同步锁对象（this）进行调用==，使用顺序是==先唤醒再等待==

##### 生产者消费者问题

生产者和消费者在同一时间段内共用同一个存储空间，如下图所示，生产者向空间里存放数据，而消费者取用数据，如果不加以协调可能会出现以下情况：

存储空间已满，而生产者占用着它，消费者等着生产者让出空间从而去除产品，生产者等着消费者消费产品，从而向空间中添加产品。互相等待，从而发生死锁。

##### 代码

~~~
public class Test1
{
	public static void main(String[] args)
	{
		Tree q=new Tree();
		//创建生产者、消费者两个线程对象
		Producer p=new Producer(q);
		Consumer c=new Consumer(q);
		p.start();
		c.start();
	}
}

class Producer extends Thread
{
	Tree q;
	Producer(Tree q)
	{
		this.q=q;
	}
	public void run()
	{
		for(int i=0;i<10;i++)
		{
			q.put(i);//调用树的放方法
			System.out.println("Producer put "+i);
		   
		}
	}
}
class Consumer extends Thread
{
	Tree q;
	//接收处理的对象
	Consumer(Tree q)
	{
		this.q=q;
	}
	public void run()
	{
		while(true)
		{
			System.out.println("Consumer get "+q.get());//调用树的取方法
		}
	}
}
/* 树类 **/
//锁池、等待池
class Tree
{
	private int hole;//树洞
	boolean bFull=false;
	//放情报
	public synchronized void put(int i)
	{
		if(!this.bFull)//如果为空
		{

			this.hole=i;//把情报放入树洞
			this.bFull=true;//设为非空
		/*从该对象的等待队列中释放消费者线程进入该对象锁池	
		使该线程将再次成为可运行的线程*/
		    this.notify();
		}
		try
		{//生产者线程进入this对象的等待队列开启锁池
		 //锁池-->等待池
			this.wait();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	//取情报
	public synchronized int get()
	{
		if(!this.bFull)//如果为空
		{ 
			try
			{
				this.wait();//消费者线程进入this对象的等待队列开启锁池
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		bFull=false;//设置为空
		/*从该对象的等待队列中释放生产者线程进入该对象锁池	
		使该线程将再次成为可运行的线程*/
		 
		int value=this.hole;
		this.notify();
		return value;
	}
}
~~~

