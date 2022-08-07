<%--
  Created by IntelliJ IDEA.
  User: 菠菜
  Date: 2022/7/25
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>注册</title>
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
                    注册</h2>
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

                <a href="../../page/index/login.jsp"><span style="float: right">去登录</span></a>
            </div>

            <div class="col-lg-10">
            </div>

            <div class="col-lg-10">
                <button type="button" id="btn" class="btn btn-success col-lg-12">注册</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
