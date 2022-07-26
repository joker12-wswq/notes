-------------------->7.5

## collections   [ ]

1.集合与数组

都是存储数据的容器

集合     

长度可变、==存放对象的地址==、类型可以不同、无序且不可重复、覆写了toString、接口引用、数据结构不同

数组     

长度固定、存放基本和引用数据、类型必须相同、有序且可重复、没有覆写toString、适合查找的线性顺序表

2.ApI层次结构

![image-20220705163605629](https://user-images.githubusercontent.com/107198282/181003288-78407672-ce81-47f1-9ca6-1370471434f7.png)

3.常用方法

- boolean add(object  element);向集合中添加元素

  ~~~
  	//创建集合，空集合，空数组没意义、可以定义
  	 Collection c=new ArrayList();
  	 //添加元素 
        c.add(1);//Jdk5.0之后，自动装箱
        c.add(new Integer(100));
  	  Object o=new Object();
  	  c.add(o);
  ~~~

- int size();获取集合中元素的个数

  ~~~
   System.out.println(c.size());//3
  ~~~

- void clear();清空集合

  ~~~
  c.clear();
  ~~~

- boolean isEmpty();判断集合中是否有元素

  ~~~
  	//判断集合中是否为空
  	System.out.println(c.isEmpty());//false
  ~~~

- Object[] toArray();将集合转换成数组

  ~~~
  	  Object[] objs=c.toArray();
  	  for(int i=0;i<objs.length;i++)
        System.out.println(objs[i]);
  ~~~

- boolean contains(Object o);判断集合中是否包含某个元素

  ~~~
  	//判断集合中是否包含某个元素
  	System.out.println(c.contains(o));//true
  ~~~

- boolean remove(Object o);删除集合中某个元素

- Iterator iterator();获取集合所依赖的迭代器对象

  ~~~
  	List  list = new ArrayList();
  	Iterator  elements = list.iterator();
  	while( elements.hasNext() ){
  		System.out.println( elements.next() );
  	
  ~~~

==泛型集合==可以使用for进行==遍历==

~~~
	//适用于数组、泛型集合，泛型集合可以不用迭代器
	for(Book bk:al)
		{System.out.println(bk);}
	}	
~~~

## list
![image-20220706085528231](https://user-images.githubusercontent.com/107198282/181003767-c760a6cb-8c76-4e82-9863-8fa9cf273bea.png)



有序可重复

~~~
    //集合使用equals比较
    List list = new ArrayList(); //list有序可重复
    System.out.println(list.size());//0
	 list.add(5);
	 list.add("one");
     list.add("second");
     list.add("3rd");
     list.add(new Integer(4));
     list.add(new Float(5.0F));
     list.add("second");
     list.add(new Integer(4));
     list.remove("3rd");
     System.out.println(list.size());//7
	 //[5, one, second, 4, 5.0, second, 4]
	 //有序：添加的顺序
~~~

## set

#### iterator与enumeration

enumeration线程安全

#### HashSet

无序不可重复，HashSet的数据结构为哈希表，与hashmap类似，只是value是系统给的常量

~~~
	 Set set = new HashSet();
	//遍历集合每一个元素，父接口的迭代
	//方法iterator()创建一个实现Iterator接口的对象，将set元素放入Iterator集合
     Iterator itr = set.iterator();
      while(itr.hasNext()){
       System.out.println(itr.next());
      }
~~~

#### TreeSet

无序、不可重复，自动排序,TreeSet的数据结构是一个二叉树，存入的数据最好是可比较的，实现compare

~~~
	SortedSet ss=new TreeSet();//TreeSet实现了SortedSet接口，接口类型变量稳定，降低耦合性
		ss.add(5);
		ss.add(5);
		ss.add(3);
		ss.add(1);
		ss.add(8);
		System.out.println(ss);
		Iterator it=ss.iterator();
		while (it.hasNext())
		{
			System.out.println(it.next());
		}
~~~

## map  { }

1.Map中的key是==无序不可重复==的，和HashSet相同

2.==HashMap==的默认初始化容量16
 默认负载因子0.75-------->结点个数/桶个数



3.==hashmap==的key要重写equals、hashcode

~~~
		HashTest a = new HashTest();
		HashTest b = new HashTest();
		a.setI(1);
		b.setI(11);//b
		Map<HashTest,String> map=new HashMap<HashTest,String>();
		map.put(a,"tom");
        map.put(b,"bush");
		//调用key的hashcode找到下标，碰撞，调用key的equals（=8次时，红黑二叉树；<5，链		表；剩余缓冲区，都可），
		//b.equals(a)-->false-->挂在节点下面（jdk1.8后），key的equals、hashcode
        map.put(a,"lisa");//key相同，新value替换旧value
        
		String str=map.get(b);//先找hashcode，equals
		System.out.println(map);//类型@key=value
~~~

![案例9-16 HashTest 内存图](https://user-images.githubusercontent.com/107198282/181003518-11ec3510-197c-4bb0-b94f-ac15d01a8e02.JPG)


- 存储键值对    put

~~~
  Map<String,String> persons=new HashMap<String,String>();
 persons.put("1000","jack");
 persons.put("1001","jack");
 persons.put("1002","king");
 persons.put("1003","sun");
 persons.put("1002","cook");
~~~

- 取得键值对（entry）的个数           size

~~~
System.out.println(persons.size());//4
~~~

- 判断集合中是否包含相应的key       containsKey

~~~
System.out.println(persons.containsKey("1003"));//true
~~~

- 通过key取得value       get

~~~
System.out.println(persons.get("1002"));//cook
~~~

- 通过key删除键值对     remove

~~~
persons.remove("1002");
~~~

- 清空Map

~~~
persons.clear();
~~~

- 判断该集合是否为空

~~~
System.out.println(persons.isEmpty());//true
~~~

- ==遍历map集合==

~~~
 	  Set set2=map.entrySet();
      Iterator itr2 = set2.iterator();
      while(itr2.hasNext()){
      Map.Entry entry=( Map.Entry)itr2.next();
      System.out.println(entry.getKey()+":"+entry.getValue());
      }
~~~

5.层次结构

![image-20220705164347363](https://user-images.githubusercontent.com/107198282/181003611-0762984f-96c2-4a59-b997-5490509db9f2.png)

