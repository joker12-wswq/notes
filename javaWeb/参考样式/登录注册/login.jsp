<%--
  Created by IntelliJ IDEA.
  User: 菠菜
  Date: 2022/7/25
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>登录</title>
  <script src="../../js/jquery-3.1.1.min.js"></script>
  <script src="../../js/bootstrap.min.js"></script>
  <link href="../../css/bootstrap.min.css" rel="stylesheet">
  <link href="../../css/style.css" rel="stylesheet">
</head>

<body id="body">
<form id="from" action="#" method="post">
  <div class="mycenter">
    <div class="mysign">
      <div class="col-lg-11 text-center text-info">
        <h2>
          请登录</h2>
      </div>
      <div class="col-lg-10">
        <input type="text" class="form-control" name="username" placeholder="请输入账户名" required
               autofocus />
      </div>
      <div class="col-lg-10">
      </div>
      <div class="col-lg-10">
        <input type="password" class="form-control" name="password" placeholder="请输入密码" required
               autofocus />
      </div>
      <div class="col-lg-10">
      </div>
      <div class="col-lg-10 mycheckbox checkbox">

        <input type="checkbox" class="col-lg-1">记住密码</input>

        <a href="../../page/index/register.jsp"><span style="float: right">没有账号？去注册</span></a>
      </div>
      <div class="col-lg-10">
      </div>
      <div class="col-lg-10">
        <button type="button" id="btn" class="btn btn-success col-lg-12">登录</button>
      </div>
    </div>
  </div>
</form>
</body>
</html>
