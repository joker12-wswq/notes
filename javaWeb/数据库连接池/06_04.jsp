<html>
<head>
<title>ʹ�����ӳ��������ݿ�</title>
</head>
<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<jsp:useBean id="connManager" scope="application" class="db.test.DBConnManager" />
<body bgcolor="#CFF1E1">
<center><h2>
ʹ�����ӳ��������ݿ�
</h2></center>
<%
    
   Connection con2 = connManager.getConnection("mysql");
    
   if(con2==null)
   {
%>
�Բ����������ݿ�æ�����Ժ�����
<%
   }
    
   Statement stmt2 = con2.createStatement();
    
%>
 <center><h3>
ʹ��con2���ӷ���Mysql�����ݿ�hibernate_fistb
</h3></center>
<table border=1 align="center">
<tr>
<td>�û�id</td>
<td>�û�����</td>
<td>�û�����</td>
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
