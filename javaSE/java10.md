------------>6.10

## 数组

~~~java
5-2
		String[] stringArray;
		stringArray = new String[5];//下标0-4
		//堆内创建5个连续的空间，存放String类型变量
		stringArray[1]="two";//将常量池中的two赋给该数组的第2个元素
		System.out.println("元素默认初始化值是："+stringArray[4]);
		System.out.println("数组对象的地址是："+stringArray);
		//System.out.println(stringArray[5]);数组下标越界
~~~

![案例5-2 内存图](E:/Program Files/Typora/img/案例5-2 内存图.JPG)

#### 初始化

##### 动态

将数组定义与赋值分开进行

~~~java
		int[] a;
		a = new int[10];
		for (int i = 0;i < a.length;i++)
		{
			a[i] = i+1;
			System.out.println("a[" + i +"]=" +a[i] );
		}	
~~~

##### 静态

5-3    3种方式

~~~java
	//1--->自动给每个元素赋值
	int[]a = {1,3,5,7,9,11};
	//2✨
	int[]num;
	num=new int[]{1, 2, 3, 4, 5};
	//3
	int[] b = new int[3];
~~~

错误

~~~java
	只能初始化，不能赋值	
	int[] a;
	a= {1,3,5,7,9,11};
~~~

==空数组对象不能使用==

~~~java
	int[]k=new int[0];//没有元素，不能引用
	System.out.println(k[0]);
~~~

==负数组长度异常==

~~~java
	int[] m =new int[-10];
~~~

增强for   ==foreach==

for(元素类型  元素名：遍历的数组或集合){   

```java
System.out.println(元素名);//输出   
```

 }

~~~java
	for (int i:b)//b所指对象中，依次取出
		{
			System.out.println(i);//输出元素名
		}
~~~

数组对象的传递，地址

#### 应用

##### 冒泡排序

​	从第一个元素又开始，相邻的元素，两两相比较，如果前面的比相邻的大就交换，直到都比完后，将最大的数放在最后，完成一轮，第二轮也是同样的比较方法，不过每次都会将排好的数，不进行比较。

冒泡排序-------->==从小到大==

冒泡次数（比较轮数）：length-1

交换次数：length-1-i

![img](E:/Program Files/Typora/img/aHR0cHM6Ly9pbWFnZXMyMDE3LmNuYmxvZ3MuY29tL2Jsb2cvODQ5NTg5LzIwMTcxMC84NDk1ODktMjAxNzEwMTUyMjMyMzg0NDktMjE0NjE2OTE5Ny5naWY.gif)

代码

~~~java
 		//冒泡排序算法
        int[] numbers=new int[]{10,2000,50,600,95};
		//需进行length-1次冒泡
        for(int i=0;i<numbers.length-1;i++)//比较轮数
        {   
            
            /**for(int j = i+1; j < numbers.length; j++)
            {
                if(numbers[i]>numbers[j])
                {
                    int temp = numbers[j];
					numbers[j] = numbers[i];
					numbers[i] = temp;
                }
            }
            */
            for(int j=0;j<numbers.length-1-i;j++)//交换次数
            {
                if(numbers[j]>numbers[j+1])//如果前面的比相邻的大
                {//交换元素
                    int temp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=temp;
                }
            }
        }

        System.out.println("从小到大排序后的结果是:");
		 for(int e:numbers){
            System.out.print(e+"\t");//不加"\t"，被排序的数会连在一起
      		}
~~~

##### 选择排序

1. ###### 工作原理

   ​		第一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，然后再从剩余的未排序元素中寻找到最小（大）元素，然后放到已排序的序列的末尾。以此类推，直到全部待排序的数据元素的个数为零。

   ==后面元素比前面小，就交换。==

2. ###### 代码（小-->大）

~~~java
	public static void main(String[] args) {
		//模拟数据
		int[] array = {12,88,45,666,8888};

		System.out.println("原数组：");
		for (int i : array) {
			System.out.print(i+" ");
		}

		System.out.println();//间隔

		selectSort(array);//使用选择排序方法

		System.out.println("排序后：");
		for (int i : array) {
			System.out.print(i+" ");//打印输出
		}
	}


	/**
	*选择排序，由小到大
	*由大到小，则
	if(arr[j]>arr[max]){
					max = j;
				}
			}
			if(max!=i){
				swap(arr, i, max);
			}
	*/
	public static void selectSort(int[] arr){
		for(int i = 0; i < arr.length-1; i++){
			int min = i;
			for(int j = i+1; j <arr.length ;j++){
				if(arr[j]<arr[min]){
					min = j;
				}
			}
			if(min!=i){
				swap(arr, i, min);
			}
		}
	}

	
	/**
	*完成数组两元素间交换
	*/
	public static void swap(int[] arr,int a,int b){
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
~~~

##### 插入排序

1. ###### 概念及其介绍

    插入排序（InsertionSort）,一般也被称为直接插入排序。对于少量元素的排序，他是一个有效的算法。

2. ###### 思路

    它的基本思路是将一个记录插入到已经排序好的有序表中，从而得到一个新的、记录增加1的有序表。在实现过程中使用双层循环，外层循环对除了第一个元素之外的所有元素，内层循环对当前元素前面有序表进行待插入位置查找，进行移动。

3. ###### 适用说明

   插入排序的平均时间复杂度是O(n^2)，空间复杂度为常数阶O(1)，具体时间复杂度和数组的有序性也是有关联的。插入排序中，当待排序数组是有序时，是最优的情况，只需当前跟前一个数比较一下就可以了，这是一共需要比较n-1次，时间复杂度为O(n)。最坏的情况是待排序数组是逆序的，此时需要比次数最多，最坏的情况是O(n^2)。

4. ###### 效果图

   ![img](E:/Program Files/Typora/img/20180606142602783.gif)

5. ###### 代码

~~~java
package somedemo;

import java.util.Arrays;
/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] numbers = {5,3,2,6,4};
        System.out.println("排序前的结果为：" + Arrays.toString(numbers));
        for (int i = 1; i < numbers.length; i++) { //控制循环轮数
            int temp = numbers[i]; //定义待交换元素
            int j; //定义待插入的位置
            for (j = i; j > 0 && temp < numbers[j - 1]; j --) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = temp;
            System.out.println("第" + i + "轮的排序结果为：" + 			Arrays.toString(numbers));
        }
        System.out.println("排序后的结果为：" + Arrays.toString(numbers));
    }
}
~~~

​     Arrays.sort( )   从小到大，调优的快速排序

​    
