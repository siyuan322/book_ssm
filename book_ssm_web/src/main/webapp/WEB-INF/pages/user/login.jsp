<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>当当</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="${pageContext.request.contextPath}/img/logo.png">
</div>

<div class="login_banner">

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>密码登录</h1>
                    <a href="/user/register">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">${errMsg}</span>
                </div>
                <div class="form">
                    <form action="${pageContext.request.contextPath}/user/login" method="post">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>