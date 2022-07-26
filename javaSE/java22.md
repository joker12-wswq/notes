--------->7.6

## 反射

1.

 动态的过程，动态调用属性、方法，灵活、降低耦合、方便维护

   一个类==只有一个反射对象==，可以有多个对象

#### 2.问题：

- 破坏隐藏封装
- 降低效率

#### 3.对象反射

~~~
Class c=Class.forName(args[0]);
	//通过反射对象调用无参的构造方法创建该类的对象
	Object a=c.newInstance();
	Shapes s=(Shapes)a;
     s.output();
~~~

#### 4.获取反射对象的==三种方式==

- 通过加载类取得与该类对应的反射对象

~~~
//1.当前目录下找字符串及对应的class文件，没有则出错
//2.加载内存
//3.虚拟机为字节码文件创建反射对象，与class文件关联
//一个类只有一个反射对象
	Class c=Class.forName(args[0]);
	//通过反射对象调用无参的构造方法创建该类的对象
	Object a=c.newInstance();
~~~

- 通过类的class属性取得与该类对应的反射对象

~~~
 //Employee类被加载，系统会创建反射对象，但会延迟调用静态初始化块，
 //用反射对象创建对应的对象时调用，类class
    Class c2=Employee.class; 
 //Object o =c2.newInstance();//会调用Employee的静态初始化块
~~~

- 通过对象的getClass()方法

~~~
// 取得与该类对应的反射对象
    Employee e=new Employee();
	Class c3=e.getClass();
~~~

#### 5.方法反射

步骤

- 获取对象的反射对象

  ~~~
  Class c= cs.getClass();
  ~~~

- 取得方法名

  ~~~
  String methodName=args[0];
  ~~~

- 用==Method对象====封装==某个特定的==方法==

  反射对象获取类的方法，封装到Method对象

  ~~~
  //用Method对象封装某个特定的方法 
  //String类型的反射对象
  //c方法类信息
   Method m=c.getDeclaredMethod(methodName,String.class,String.class);   
  ~~~

- 调用对象的封装的方法

  封装方法的对象使用invoke调用，类对象、参数

  ~~~
  Object returnValue=m.invoke(cs,"admin","123");
  ~~~

## File类

#### 1.位置

File类在java.io包下，代表磁盘文件本身的对象

#### 2.文件过滤

###### 回调

通过在File中的list()方法中加入FileNameFilter参数，可以只将满足条件的文件列出来—回调

策略设计

==FileNameFilter==是一个接口，只有一个accept()方法需要实现—策略设计模式

实现接口

~~~
//类实现类FileNameFilter接口
class JavaFilter implements FilenameFilter
~~~

实现accept方法，过滤

~~~
public boolean accept(File dir,String name)
	{ 
		File f = new File(dir,name);
		if(f.isDirectory()) 
		 return true;
		  
	    else return name.endsWith(".java");
	}
~~~

递归实现

~~~
public boolean accept(File dir,String name)
	{ 
		File f = new File(dir,name);
		if(f.isDirectory()) 
		 //递归搜索
		{
		String[] javaFiles=f.list(new JavaFilter());
		for(int i=0;i<javaFiles.length;i++)
		{
			System.out.println(javaFiles[i]);
		}
		}
	    return name.endsWith(".java");
	}
}
~~~

创建文件对象

~~~
String dir=args[0];//输入路径
File currDir=new File(dir);//根据路径创建文件对象
~~~

回调

~~~
	//判断File对象是否为目录
	if (currDir.isDirectory())
		//表示是目录的都会调用accept方法，符合的放入数组
		//依次调用   回调
		{String[] javaFiles=currDir.list(new JavaFilter());
		//遍历输出
		  for(int i=0;i<javaFiles.length;i++)
		   {
			 System.out.println(javaFiles[i]);
		   } 
		}
~~~

#### 3.构造方法

- File(String path)：如果 path 是实际存在的路径，则该 File 对象表示的是目录；如果 path 是文件名，则该 File 对象表示的是文件。
- File(String path, String name)：path 是路径名，name 是文件名。
- File(File dir, String name)：dir 是路径对象，name 是文件名。