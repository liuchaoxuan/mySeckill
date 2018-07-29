<%--
  Created by IntelliJ IDEA.
  User: kyrie
  Date: 2018/7/23
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<html>
<head>
    <title>list</title>
    <%@include file="common/header.jsp"%>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel heading text-center">
            <h1>秒杀列表</h1>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>商品ID</th>
                <th>商品名</th>
                <th>库存数量</th>
                <th>开始时间</th>
                <th>结束时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item" >
                <tr>
                    <td>${item.seckillId}</td>
                    <td>${item.name}</td>
                    <td>${item.number}</td>
                    <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><a class="btn btn-info" href="/seckill/${item.seckillId}/detail" target="_blank">商品详情</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>
