<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/car.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
    <script>
        $(function () {
            $(".decrement").click(function () {
                // 得到当前兄弟文本框的值
                let n = $(this).siblings(".itxt").val();
                console.log(n)
                if (n == 1) {
                    return false;
                } else {
                    let id = $(this).siblings(".hidder_id").text()
                    location.href = "${pageContext.request.contextPath}/buy/removeOneCartServlet?id=" + id
                }
            })
        })

    </script>
</head>

<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/pages/common/userHeader.jsp"></jsp:include>
<!-- 头部 -->

<div id="main">
    <h2 class="little_title">购物车</h2>
    <div class="w">
        <div class="cart-filter-bar">
            <em>全部商品</em>
        </div>
        <!-- 购物车主要核心区域 -->
        <div class="cart-warp">
            <!-- 头部全选模块 -->
            <div class="cart-thead">
                <div class="t-goods">书名</div>
                <div class="t-price">单价</div>
                <div class="t-num">数量</div>
                <div class="t-sum">小计</div>
                <div class="t-action">操作</div>
            </div>
            <!-- 商品详细模块 -->
            <div class="cart-item-list">
                <c:forEach items="${sessionScope.cart.items}" var="entry">
                    <div class="cart-item">
                        <div class="p-goods">
                            <div class="p-img">
                                <img src="${pageContext.request.contextPath}/${entry.value.img_path}" width="100px"
                                     alt="">
                            </div>
                            <div class="p-msg">${entry.value.name}</div>
                        </div>
                        <div class="p-price">￥${entry.value.price}</div>
                        <div class="p-num">
                            <div class="quantity-form">
                                <div hidden class="hidder_id">${entry.value.id}</div>
                                <a href="${pageContext.request.contextPath}/cart/sub?id=${entry.value.id}" class="
                                   decrement">-</a>
                                <input type="text" class="itxt" readonly="readonly" value="${entry.value.count}">
                                <a href="${pageContext.request.contextPath}/cart/add?id=${entry.value.id}"
                                   class="increment">+</a>
                            </div>
                        </div>
                        <div class="p-sum">￥${entry.value.totalPrice}</div>
                        <div class="p-action">
                            <a href="${pageContext.request.contextPath}/cart/delete?id=${entry.value.id}">删除</a>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- 结算模块 -->
            <c:if test="${not empty sessionScope.cart.items}">
                <div class="cart-floatbar">
                    <div class="operation">
                        <a href="${pageContext.request.contextPath}/cart/clear" class="clear-all">清空购物车</a>
                    </div>
                    <div class="toolbar-right">
                        <div class="amount-sum">已经选<em>${sessionScope.cart.totalCount}</em>件商品</div>
                        <div class="price-sum">总价： <em>￥${sessionScope.cart.totalPrice}</em></div>
                        <div class="btn-area">
                            <a href="${pageContext.request.contextPath}/order/create">
                                去结算
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

</div>


</body>

</html>