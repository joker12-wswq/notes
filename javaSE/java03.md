## 面向对象设计

#### 结构化程序设计

例如C语言，把一个大问题分成不同的小问题，解决不同难度的小问题之和，将小于单独解决这一大问题的难度。从而达到1+1< 2的效果。

以功能为核心，在需求阶段获取所有需求，需求变更需要较大改动。

更关注方法函数，

与现实事物不一样，现实中以对象角度看待问题

#### 面向对象设计

以对象为核心，对象的属性和方法发生变化，但对象稳定，不会随需求变化发生大的改变，好维护

#### java三大特性

1. <font color='red'>多态</font>：父类的同样方法在子类有不同的体现
2. <font color='red'>封装</font>：外部使用<font color='orange'><u>隐藏的属性</u></font>(通过pravite，体现私有特点)，提供一个共有的方法
3. <font color='red'>继承</font>（单一继承，父类的私有属性不能被子类继承）

#### 对象

1. <font color='red'><u>状态</u></font>：对象的属性值（无状态，说明它的值不能改变）
2. 领域模型：把对象抽象出类、类和类的关系
3. 对象：把事物放在问题领域当中，抽象出属性和方法。
4. 类：将对象共同的属性和方法抽象出来

<font color='red'>创建对象后，在堆里为属性开辟空间</font>



#### 类与对象关系

类是对象的抽象，描述了每个对象的共同的行为特征；

对象是类的实例



## 类

1. 主方法不一定要再主类中
2. *字符串常量池---->堆内存或方法区内*
3. 方法调用结束，自动释放空间
4. <font color='red'><u>有几个类，编译就有几个class文件</u></font>

#### java类的声明

1. 使用class
2. 驼峰命名

#### 属性的声明

<img src="E:/Program Files/Typora/img/image-20220526104551577.png" alt="image-20220526104551577" style="zoom:150%;" />

#### 方法的声明

1. 修饰符  返回值类型  方法名称（形参列表）{  

  方法体代码    

  return 返回值;

  }



2. <font color='red'>堆里的代码区，放于共享代码块，存放方法的地址</font>

3. 方法要执行怎么办，如何进行？

  必须进行调用；调用格式：方法名（...）

<img src="E:/Program Files/Typora/img/image-20220526104701146.png" alt="image-20220526104701146" style="zoom:150%;" />

#### 构造器的声明

<img src="E:/Program Files/Typora/img/image-20220526111734284.png" alt="image-20220526111734284" style="zoom:150%;" />

<img src="E:/Program Files/Typora/img/image-20220526112032266.png" alt="image-20220526112032266" style="zoom:150%;" />

- 默认有一个无参构造器，如果写了一个有参构造器，需要再写一个无参构造器，无参构造器才能使用。
- <font color='red'><u>没有返回值概念</u></font>

#### 重载

![image-20220526110912228](E:/Program Files/Typora/img/image-20220526110912228.png)



#### return

![image-20220526111041472](E:/Program Files/Typora/img/image-20220526111041472.png)



#### <font color='red'>this</font>

<img src="E:/Program Files/Typora/img/image-20220526112708685.png" alt="image-20220526112708685" style="zoom: 100%;" />
