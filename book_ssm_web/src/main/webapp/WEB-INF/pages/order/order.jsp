<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>

<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/pages/common/userHeader.jsp"></jsp:include>
<!-- 头部 -->

<div id="main">
    <h2 class="little_title">我的订单</h2>
    <table>
        <tr>
            <td>订单号</td>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <c:forEach items="${orderList}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.create_time}</td>
                <td>¥${order.price}</td>
                <c:if test="${order.status == 0}">
                    <td>未发货</td>
                </c:if>
                <c:if test="${order.status != 0}">
                    <td>已发货</td>
                </c:if>
                <td><a href="${pageContext.request.contextPath}/order/showOrderItem?orderId=${order.id}">查看详情</a>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>

</body>

</html>