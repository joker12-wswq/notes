## tip

bean：封装数据

实体：封装业务逻辑

构造方法：<font color='red'>在对象创建完成(new)之后</font>，对对象进行<u>初始化</u>(构造器)

- [x] <font color='red'>代码缩进4个空格</font>✨

## 常用的包

1. java.lang---包含一- 些Java语言的核心类，如String、 Math、Integer、Exception、 System和Thread, 提供常用功能。<font color='gree'>自动导入，提供常用功能</font>
2. java.awt---包含了构成抽象窗口汇具集( abstract windowtoolkits)的多个类，这些类被用来构建和管理应用程序的图形用户界面(GUI)。
3. java.aplet---包含applet运行所需的些类。
4. java.net----包含执行与<font color='gree'>网络</font>相关的操作的类。
5. java.io---包含能提供多种<font color='gree'>输入/输出</font>功能的类。
6. java.util--包含一些<font color='gree'>实用工具类</font>，如定义系统特性、集合、口期日历相关的函数。

## 注释

作用：便于调试、维护程序

种类：单行、多行

说明性注释：放于类、接口、方法之前，使用多行注释（作用、参数、返回值、作者、修改记录等）

功能性注释：单行，放于属性、代码右侧（作用）或者代码上侧

~~~--
	// 单行注释----注释到行尾
	/* 单行或多行注释 */
	/** 可以用于文档化处理的单行或多行注释 */
~~~

java文档化工具：javadoc 注释可以用于生成API文档，不能收集私有、默认、作者、版本。

~~~java
	javadoc XXX.java
	javadoc -author -version -private XXXX.java //可以收集作者、版本、私有
~~~

## 分割符

一条语句是以分号<font color='red'>（;）</font>结尾的一行代码	

一个语句块是以一对花括号<font color='red'>（{}）</font>为边界的语句的集合

## 标识符

概念：给变量、类和方法的命名规范

规则：

1. 以字母、下划线“_”和”$”符开头
2. 首字母外，可以跟上字母、下划线“_”和”$”符或数字

<font color='red'>注意：</font>

1. 区别大小写
2. <font color='red'>*汉字*可以作为标识符</font>✨
3. <u>*关键字*</u>不能作为标识符
4. 首字符不能为数字
5. <u>*标准类库的类*</u>不能作为标识符
6. 不能有空格

例如：

![image-20220531085002885](https://user-images.githubusercontent.com/107198282/181000965-3988b127-c0a0-4f6c-b72c-d2e0b7bfa297.png)
