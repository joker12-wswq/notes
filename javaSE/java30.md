----------->7.18

## jdk新特性

#### default

jdk1.8后，接口中default、static方法可以使用，该方法可以在接口实现

所有，接口中的方法可以有实现方法

#### lambda

前提：实现函数式接口，使用匿名内部类

形式：（参数1，参数2，....）---->{   实现接口的方法体   }

###### Lambda概述

- Lambda表达式是JDK 8开始后的一-种新语法形式。
- ==作用: 简化匿名内部类的代码写法。==

Lambda表达式的简化格式

~~~
(匿名内部类被重写方法的形参列表) -> {
	被重写方法的方法体代码。
}
注: ->是语法形式，无实际含义
~~~

注意: Lambda表达式只能简化函数式接口的匿名内部类的写法形式

###### 什么是函数式接?

- 首先必须是接口、其次接口中有且仅有一个抽象方法的形式
- 通常我们会在接口上加上一个@FunctionalInterface注解，标记该接口必须是满足函数式接口。

###### Lambda表达式简化Comparator接口的匿名形式

~~~
Arrays.sort(ages, new Comparator<Integer>(){
	@Override
	public int compare(Integer 01, Integer o2) {
	return o2 - 01;
});
~~~

简化

~~~
Arrays.sort(ages, (Integer 01, Integer o2)-> {
	return o2 -01;
});
Arrays.sort(ages, ( 01,  o2)  ->  o2 -01);
~~~

###### Lambda表达式简化按钮监听器ActionListener的匿名内部类形式

~~~
/1给登录按钮绑定点法事件监听器
btn.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	System.out.printn("登录一下~-~");
});
~~~

简化

~~~~
btn.addActionL istener( (ActionEvente)-> {
	System.out.println("登录一 下~~~");
});
btn.addActionL istener( e-> System.out.println("登录一 下~~~"));
~~~~

###### 规则

Lambda表达式的省略写法(进一步在Lambda表达式的基础.上继续简化)

- 参数类型可以省略不写。

- 如果只有一一个参数,参数类型可以省略，同时()也可以省略。

- 如果Lambda表达式的方法体代码只有一行代码。可以省略大括号不写,同时要省略分号!

- 如果Lambda表达式的方法体代码只有一行代码。可以省略大括号不写。此时，如果这行代码是

  return语句，必须省略return不写，同时也必须省略";"不写

#### 方法与构造方法引用

1.通过关键字  ::    引用对象的构造方法

2.使用

#### Stream

对集合数据进行过滤、排序、比较、输出等操作

#### Comparator接口

自定义比较器规则，只支持引用类型

~~~
/**
参数一  被排序的数组必须是引用类型的元素
参数二匿名内部类对象，代表了一个比较器对象*/

Integer[] ages1 = {34， 12, 42，23)};

Arrays. sort(ages1, new ConparatorcInteger<>() {
	@0verride
	public int conpare(Integer 01, Integer 02){
	//指定比较规则。
	return o1 - o2;//默认升序
	//return o2 - o1;降序
	}
});

~~~

##### 自定义排序规则

●   设置Comparator接口对 应的比较器对象，来定制比较规则。

~~~
如果认为左边数据大于右边数据返回正整数

如果认为左边数据小于右边数据返回负整数

如果认为左边数据等于右边数据返回0
~~~

按这个规则会升序排序，否则是降序排序

##### 对象排序

对象

~~~
public class Student (
	private String nane; .
	private int age; 
	private double height;
	.......
}
~~~

建立数组

~~~
Student[] students = now Student[3];
students[0] = new student( name“吴磊"，age: 23，height 175.5);
students[1] = new student( name“谢高", age 18，helght 185.5);
students[2] = new Student( name“王亮"，age 20，height 195.5);
~~~

排序

~~~
Arrays. sort(students, new Comparator<Student>() (
	0override
	public int compare(Student o1, student 02) (
	//自己指定比较规则
	return o1.getAge() . o2.getAge(); //按照年龄升序排序!
);
~~~

~~~
return Double.compare(o1.getHeight(), o2.getHeight()); //比较浮点型可以这样写  升序
return Double.compare(o1.getHeight(), o2.getHeight()); //比较浮点型可以这样写  降序
~~~

