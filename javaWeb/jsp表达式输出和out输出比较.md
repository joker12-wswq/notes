

      表达式
   语法：<%=     %>
   作用：向浏览器输出数据
   
   用jsp的表达式和Java输出的比较
   相同点：都是向浏览器输出数据
   不同点：
   1.语法范畴不同
     out.println（）属于Java语法范畴 <%   %>
     <%=     %>表达式输出 属于jsp语法范畴
 
   2. 输出特点
      out.println（）输出 使用out内置对象，
      不符合MVC模式要求。
      <%=     %>表达式输出 使用response内置对象。
      符合MVC模式要求。
   3.使用方式
     out.println（）输出 比较复杂
     <%=     %>表达式输出 比较简单
  结论：推荐使用<%=     %>表达式输出

    9*9  乘法表

    for (i=1;i<10;i++)
     for (j=1;j<10;j++)
       输出i*j

 <html>
 <head>
	<title>JSP</title>
 </head>
 <body>
<table border="1">
 <tr>
<td>1*1</td><td>1*2</td>......<td>1*9</td>
</tr>

<tr>
<td>2*1</td><td>2*2</td>......<td>2*9</td>
</tr>
.......
<tr>
<td>9*1</td><td>9*2</td>......<td>9*9</td>
</tr>  

</table>

  结论：尽量不要在jsp页面中使用原生Java程序，否则：jsp程序将难以维护。

