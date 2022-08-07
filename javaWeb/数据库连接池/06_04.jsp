<html>
<head>
<title>使用连接池连接数据库</title>
</head>
<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<jsp:useBean id="connManager" scope="application" class="db.test.DBConnManager" />
<body bgcolor="#CFF1E1">
<center><h2>
使用连接池连接数据库
</h2></center>
<%
    
   Connection con2 = connManager.getConnection("mysql");
    
   if(con2==null)
   {
%>
对不起，现在数据库忙，请稍后再试
<%
   }
    
   Statement stmt2 = con2.createStatement();
    
%>
 <center><h3>
使用con2连接访问Mysql型数据库hibernate_fistb
</h3></center>
<table border=1 align="center">
<tr>
<td>用户id</td>
<td>用户姓名</td>
<td>用户密码</td>
</tr>
</tr>
<%
   ResultSet rs=stmt2.executeQuery("select * from user");
   while(rs.next())
   {
%>
<tr>
<td><%=rs.getString("id")%></td>
<td><%=rs.getString("name")%></td>
<td><%=rs.getString("password")%></td>
</tr>
<%	
   }
   rs.close();
   stmt2.close();
   connManager.releaseConnection("mysql",con2);
%>
 
</table>
 
<%
    connManager.closeConns();
%>    
</body>
</html>
