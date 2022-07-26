## java的执行流程

#### 1.HelloWorld.java

~~~java
public class HelloWorld{
//java应用程序入口方法
	public static void main(String[] args) {
        String s = null;
        s = "Hello World!";
        //向控制台打印一条信息
		System.out.println("s="+s);
        //System.out.println("args="+args[0]);
        
        //源程序：1个，
        //字节码文件：4个，
        //创建的对象:3+1+1个，
        //用到的方法：3个，
        //局部变量（方法里）：2个    
	}
}
~~~

2.当前目录找不到，会到path找

<img src="E:/Program Files/Typora/img/image-20220524200506947.png" style="zoom:150%;" />

3.class字节码文件不一定是主类，主类供虚拟机调

4.静态 编译绑定        动态 运行绑定

#### 5.执行流程❤

javac    编译器通过文件名找到主类，通过主类找到其他类，静态，编译成class文件

java      解释器到当前目录找到.class文件，加载到虚内存的方法区的类代码区，找到该类绑定的主方法（由于	

​             static），执行和这个类绑定的主方法，public虚拟机可执行该主方法

main     在栈内，为主方法开辟空间，主方法栈帧-->在该栈内为局部变量args开辟空间，args存放String[]类型

​              的地址

String[] args     字符串数组对象，放在堆里，封装命令行参数

String     虚拟机通过classpath到标准类库-->找到了String，加载到方法区的类代码区

通过System类创建流对象，通过标准类库找到流对象-->标准字节输入out、输出in、出错流error

s = "Hello World!"        在方法区的常量池中开辟空间，Hello World!返回对象的地址交给s

toString    把s的值输出，该方法被重写了

<img src="E:/Program Files/Typora/img/image-20220525105310776-16534471952768.png" alt="image-20220525105310776" style="zoom:150%;" />

![image-20220525214329521](E:/Program Files/Typora/img/image-20220525214329521-16534862120491.png)

static: 告诉虚拟机如何找到主方法---->通过类找到主方法

​           静态的，根类相关，从类里找主方法

public : 让虚拟机找到主方法





## 类(java程序的基本组成)

1.一般类有两种访问权限：

<font color='red'><u>只有一个公有public作为主类，且主类名与java名相同；</u></font>

其他作为默认类（同一包下）。

2.权限修饰符

![](E:/Program Files/Typora/img/image-20220525094820275.png)

## Java Applet

1.工作原理（小应用程序）

有主类，无主方法-->虚拟机不能直接执行，浏览器执行

<img src="E:/Program Files/Typora/img/image-20220525112421026.png" alt="image-20220525112421026" style="zoom:150%;" />

2.不安全

.clas文件嵌入到HTML文件，浏览器执行。如果将黑客程序嵌入到HTML中，浏览器将自动执行该程序，搜集敏感信息，如银行账号、密码等，但浏览者不知道，从而泄露敏感信息。容易被黑客利用。

