## 数据类型

[参考]: https://blog.csdn.net/qq_37577735/article/details/88958406

#### 引用数据类型

![image-20220527154304334](E:/Program Files/Typora/img/image-20220527154304334.png)

#### 基本数据类型

![image-20220527154230491](E:/Program Files/Typora/img/image-20220527154230491.png)

## 类

#### private

外部改变类的<font color='red'>私有属性</font>，需要调用<font color='red'>公有的get、set方法</font>，改变默认，在<font color='red'>同一个包下</font>才可以直接调用属性

~~~java
 class Student
  {
	//定义属性
	String studentId;
	String name;
	String sex;
	int grade;
	private int age;
	
	public Student()
	{}
	
	//定义属性“studentId”的设置方法
	public void setStudentId(String student_Id)
	{
		this.studentId = student_Id;
	}
	//定义属性“studentId”的获取方法
	public String getStudentId()
	{
		return this.studentId;
	}
	
	//定义属性“name”的设置方法
	public void setName(String student_name)
	{
		this.name = student_name;
	}
	//定义属性“name”的获取方法
	public String getName()
	{
		return this.name;
	}
	
	//定义属性“Sex”的设置方法
	public void setSex(String student_sex)
	{
		this.sex = student_sex;
	}
	//定义属性“Sex”的获取方法
	public String getSex()
	{
		return this.sex;
	}
	
	//定义属性“grade”的设置方法
	public void setGrade(int student_grade)
	{
		this.grade = student_grade;
	}
	//定义属性“grade”的获取方法
	public int getGrade()
	{
		return this.grade;
	}
	
	//定义属性“age”的设置方法
    public void setAge(int student_age)
	{
		this.age = student_age;
	}
	//定义属性“age”的获取方法
	public int getAge()
	{
		return this.age;
	}
}
    public class StudentTest {
      public static void main(String[] args){
	      Student s=null;
	      s = new Student();
          
	      s.setAge(18);
          //age  私有属性
          //s.age=18;
		  s.name="tom";//name  默认属性
		 
	     System.out.println("学生姓名： " + s.name);
       }
}
~~~

![image-20220530144841051](E:/Program Files/Typora/img/image-20220530144841051.png)

#### 信息的隐藏

1. 隐藏: 指的是对象的一种保护机制,使得它的属性或方法<font color='red'>不被外部的程序直接访问</font>

2. 解决的问题：

   使用者对类内部定义的数据(对象的成员变量)的直接操作会<font color='red'>导致数据的错误、混乱或安全性问题。</font>

#### 信息的封装

1. 封装：指的是将对象的状态信息(属性)和行为(方法) 捆绑为一个逻辑单元的机制。
2. 方式：Java中通过将数据封装、声明为私有的(private),再提供一一个或多个公开的( public)方法实现对该属性的操作
3. 通过方法访问属性，避免数据混乱

## java源文件的基本结构

#### 源文件基本语法

1. 包声明     如： package   xxxxx（公司名.项目名.组名）

   将字节码文件文件放在包对应的目录结构下，下才能被访问，有包声明，但没在对应目录结构下，外部类无法访问

   <font color='brown'>便于管理</font>

   ~~~java
   //打包编译=打包+编译
   javac -d . Student.java
   //-d空格.空格java文件名，前提:java文件中有包声明  
   javac -d . *.java    //将有包声明的文件进行打包编译，没有的直接编译
   java 包名.字节码文件名  //在包内找到字节码文件执行    
      
   ~~~

2. 包导入（1+）   如： import  xxxxxxx

    <font color='red'><u>告诉虚拟机字节码文件的位置</u></font>

   .*   扫描类库中的所有文件，代码简单，效率低

   .     直接定位

3. 一般类有两种访问权限：

   <font color='red'><u>只有一个公有public作为主类，且主类名与java名相同；</u></font>

   其他作为默认类（同一包下）。
