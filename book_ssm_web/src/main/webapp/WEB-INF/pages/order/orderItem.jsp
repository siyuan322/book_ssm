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
    <h2 class="little_title">订单详情</h2>
    <table>
        <tr>
            <td>订单号</td>
            <td>图书编号</td>
            <td>名字</td>
            <td>数量</td>
            <td>金额</td>
            <td>总价格</td>
        </tr>
        <c:forEach items="${orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.order_id}</td>
                <td>${orderItem.id}</td>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>¥${orderItem.price}</td>
                <td>¥${orderItem.total_price}</td>
            </tr>
        </c:forEach>
    </table>


</div>

</body>

</html>