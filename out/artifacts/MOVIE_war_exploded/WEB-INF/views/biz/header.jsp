<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/11
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--导航栏-->
<div class="bg">
    <div class="header">
        <div class="web-logo">
            <img src="../../../img/logo.jpg" alt="">
        </div>
        <div class="category-list">
            <ul>
                <li><a href="/home.do">首页</a></li>
                <li><a href="/dzCategory.do?movieType=动作">动作片</a></li>
                <li><a href="/aqCategory.do?movieType=爱情">爱情片</a></li>
                <li><a href="/dmCategory.do?movieType=动漫">动漫片</a></li>
                <li><a href="/jqCategory.do?movieType=剧情">剧情片</a></li>
                <li><a href="/llCategory.do?movieType=伦理">伦理片</a></li>
                <li><a href="/khCategory.do?movieType=科幻">科幻片</a></li>
                <li><a href="/jlCategory.do?movieType=纪录">纪录片</a></li>
                <li><a href="/jpCategory.do?movieType=街拍">街拍</a></li>
            </ul>
        </div>

        <div class="search" id="search">
            <form action="/search.do" method="post" >
                <input type="text" name="movieName" class="input-movieName" placeholder="请输入影片名">
                <input type="submit" value="提交" class=".submitName" >
            </form>
        </div>


        <div class="log-reg">
            <ul>
                <li><a href="/reg_log.do"><img src="../../../img/login.png" alt=""></a></li>
                <li><a href="/reg_log.do?page=登录">
                    <c:choose>
                    <c:when test="${empty sessionScope.userName}">
                        注册登录
                    </c:when>
                    <c:otherwise>
                        ${sessionScope.userName}
                    </c:otherwise>
                </c:choose>
                </a></li>
            </ul>



        </div>
        <div class="clear"></div>
    </div>
</div>