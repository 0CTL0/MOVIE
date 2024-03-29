<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/15
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册</title>
    <link href="../../../css/regist.css" rel="stylesheet" type="text/css"/>
</head>
<body>

    <div class="reg">
        <div class="header">
            <h1>
                <a href="/reg_log.do?page=登录">登录</a> <a href="/reg_log.do?page=注册">注册</a>
            </h1>
        </div>

    <h3 color="red">${msg}</h3>
    <!--前端H5校验-->
    <form action="/regist.do" method="post" >
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username" pattern="[a-zA-Z]{6,12}" class="input1"
                           required="required" placeholder="请输入6-12位的字母"></td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" name="password" pattern="[0-9]{6,}" class="input1"
                           required="required" placeholder="请输入至少6位数字"></td>
            </tr>
            <tr>
                <td>手机号：</td>
                <td><input type="text" name="phone" pattern="1[3578]\d{9}" class="input1"
                           required="required" placeholder="请输入手机号1[3578]\d{9}"></td>
            </tr>
            <tr>
                <td>邮&nbsp;&nbsp;&nbsp;&nbsp;箱:</td>
                <td><input type="email" name="email" placeholder="请输入邮箱" class="input1"
                           required="required"></td>
            </tr>
            <tr>
                <td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
                <td>
                    <input type="radio" name="sex" value="male" class="td1">男
                    <input type="radio" name="sex" value="female" class="td1">女
                </td>
            </tr>

            <!--
            <tr>
                <td>上传头像:enctype="multipart/form-data"</td>
                <td><input type="file" id="photo" name="upImg"></td>
            </tr>
            -->

            <tr>
                <td colspan="2">
                    <div class="btn-red">
                        <input type="submit" value="注册" id="reg-btn">
                    </div>
                </td>
            </tr>
        </table>
    </form>
    </div>

</body>
</html>
