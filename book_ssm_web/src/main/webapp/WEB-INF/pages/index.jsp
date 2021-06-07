<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>当当</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/boot.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
    <style>
        ul,
        li {
            padding: 0;
            margin: 0;
        }

        .book_cond {
            margin-left: 400px;
        }

        #index {
            width: 1200px;
            margin: auto;
            border-top: 1px solid #ff2832;
        }

        .page_one .xinxi {
            font-size: 16px;
        }

        .page_one .xinxi .left {
            width: 1200px;
            float: left;
        }

        .page_one .xinxi .left > ul li {
            width: 100%;
            height: 170px;
            border: 1px solid #eee;
            border-left: 0;
            margin-bottom: 15px;
            overflow: hidden;
        }

        .page_one .xinxi .left > ul li .img {
            width: 320px;
            height: 170px;
            float: left;
        }

        .page_one .xinxi .left > ul li .text1 {
            width: 600px;
            float: left;
            padding: 20px;
            color: #a3a3a3;
            font-size: 14px;
        }

        .page_one .xinxi .left > ul li .text1 p:nth-of-type(1) {
            color: #000;
            font-size: 18px;
            margin-bottom: 5px;
        }

        .page_one .xinxi .left > ul li .price {
            width: 215px;
            text-align: center;
            float: left;
            height: 95px;
            border-left: 1px solid #eee;
            margin-top: 35px;
            font-size: 16px;
        }

        .page_one .xinxi .left > ul li .price p {
            line-height: 1;
            margin-top: 15px;
        }

        .page_one .xinxi .left > ul li .price .price_num {
            line-height: 1;
            font-size: 30px;
            color: #ff4848;
        }

        .page_one .xinxi .left > ul li .price .price_num span:nth-of-type(3) {
            font-size: 16px;
        }

        .page_one .xinxi .left .page_num_inf {
            color: #878787;
            font-size: 19px;
            margin-bottom: 20px;
        }

        .page_one .xinxi .left .page_num_inf i {
            float: left;
            width: 4px;
            background-color: #878787;
            height: 20px;
            margin-top: 5px;
            margin-right: 10px;
        }

        .page_one .xinxi .left .page_num_inf span {
            color: #ff4848;
        }

        .form-control {
            width: 300px !important;
        }

        .btn {
            margin-bottom: -9px !important;
        }

        #submit_search {
            width: 80px;
            background-color: #ff2832;
            color: #fff;
            font: normal 16px "Microsoft Yahei";
        }

        #addCart {
            background-color: #ff2832;
            color: #fff;
            font: normal 16px "Microsoft Yahei";
        }

        .img img {
            margin-left: 50px;
        }

        /*分页样式*/

        .pageNum {
            width: 100%;
            overflow: hidden;
        }

        .pageNum ul li {
            width: 40px;
            height: 40px;
            float: left;
            border: 1px solid #eee;
            margin-right: 10px;
            text-align: center;
            line-height: 40px;
        }

        .pageNum ul li.curPage {
            background-color: #ffc900;
        }

        .pageNum ul li a {
            width: 100%;
            height: 100%;
            color: #000;
            font-size: 14px;
        }

        .pageNum ul .threeword {
            width: 75px;
        }

        .current {
            background-color: #ff2832;
        }

        .current a {
            color: #fff !important;
        }

        .tab_list {
            height: 39px;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        .tab_list a {
            display: block;
            float: left;
            height: 39px;
            line-height: 39px;
            padding: 0 20px;
            text-align: center;
            cursor: pointer;
            color: black;
            text-decoration: none;
        }

        .tab_current {
            width: 100px;
            height: 39px;
            color: #fff;
        }
    </style>

    <script>
        $(function () {
            // location.href = "${pageContext.request.contextPath}/book/findAll";
            $("button.addToCart").click(function () {
                let bookId = $(this).attr("bookId");
                location.href = "${pageContext.request.contextPath}/cart/add?id=" + bookId;
            })
        })
    </script>
</head>


<body>
<c:if test="${loginUser != null}">
    <c:if test="${\"user\".equals(loginUser.getRole())}">
        <jsp:include page="/WEB-INF/pages/common/userHeader.jsp"></jsp:include>
    </c:if>
</c:if>
<c:if test="${loginUser == null}">
    <div id="header">
        <img class="logo_img" alt="" src="${pageContext.request.contextPath}/img/logo.png">
        <div>
            <a href="${pageContext.request.contextPath}/user/login">登录</a>/
            <a href="${pageContext.request.contextPath}/user/register">注册</a>
        </div>
    </div>
</c:if>


<div id="index">
    <div id="book">
        <!-- 搜索框 -->
        <div class="book_cond">
            <form class="form-inline" action="${pageContext.request.contextPath}/book/findAll" method="get">
                <div class="form-group">
                    <input type="text" name="search" class="form-control" id="exampleInputName2">
                </div>
                <button type="submit" class="btn btn-default" id="submit_search">查 询</button>
            </form>
        </div>


        <!-- 图书展示 -->
        <%-- tab栏 --%>
        <div class="tab_list">
            <a href="${pageContext.request.contextPath}/book/findAll">全部</a>
            <a href="${pageContext.request.contextPath}/book/findAll?category=电子书">电子书</a>
            <a href="${pageContext.request.contextPath}/book/findAll?category=教育">教育</a>
            <a href="${pageContext.request.contextPath}/book/findAll?category=小说">小说</a>
            <a href="${pageContext.request.contextPath}/book/findAll?category=人文社科">人文社科</a>
            <a href="${pageContext.request.contextPath}/book/findAll?category=生活">生活</a>
        </div>
        <p class="current_category">${category}</p>
        <div class="page_one">
            <div class="xinxi clearfix">
                <div class="left">
                    <ul>
                        <c:forEach items="${pageInfo.list}" var="book">
                            <li>
                                <div class="img">
                                    <img src="${pageContext.request.contextPath}/${book.img_path}"
                                         height="150px" alt="">
                                </div>
                                <div class="text1">
                                    <p>${book.name}</p>
                                    <br/>
                                    <p>
                                        <strong>作者：</strong><span>${book.author}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <strong>销量：</strong>
                                        <span>${book.sales}</span>&nbsp;&nbsp;&nbsp;&nbsp; <strong>库存：</strong>
                                        <span>${book.stock}</span>
                                    </p>
                                    <p>
                                        <strong>简介：</strong><span>${book.brief}</span>
                                    </p>
                                </div>
                                <div class="price">
                                    <p class="price_num">
                                        <span>&yen;</span>
                                        <span>${book.price}</span>
                                    </p>
                                    <button type="button" class="btn addToCart" id="addCart" bookId="${book.id}">加入购物车
                                    </button>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="page_num_inf">
                        <i></i> 共
                        <span>${totalPage}</span>页<span>${bookNum}</span>条
                    </div>
                    <div class="pageNum">
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/book/findAll?category=${category}&page=1">首页</a>
                            </li>
                            <li class="threeword">
                                <c:if test="${currentPage <= 1}">
                                    <a href="${pageContext.request.contextPath}/book/findAll?category=${category}&page=1&search=${search}">上一页</a>
                                </c:if>
                                <c:if test="${currentPage > 1}">
                                    <a href="${pageContext.request.contextPath}/book/findAll?category=${category}&page=${currentPage-1}&search=${search}">上一页</a>
                                </c:if>
                            </li>

                            <c:forEach begin="1" end="${totalPage}" var="i" step="1">
                                <c:if test="${currentPage == i}">
                                    <li class="current">
                                        <a href="${pageContext.request.contextPath}/book/findAll?category=${category}&page=${i}&search=${search}">${i}</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage != i}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/book/findAll?category=${category}&page=${i}&search=${search}">${i}</a>
                                    </li>
                                </c:if>
                            </c:forEach>

                            <li class="threeword">
                                <c:if test="${currentPage >= totalPage}">
                                    <a href="${pageContext.request.contextPath}/book/findAll?category=${category}&page=${totalPage}&search=${search}">下一页</a>
                                </c:if>
                                <c:if test="${currentPage < totalPage}">
                                    <a href="${pageContext.request.contextPath}/book/findAll?category=${category}&page=${currentPage + 1}&search=${search}">下一页</a>
                                </c:if>
                            </li>

                            <li class="threeword">
                                <a href="${pageContext.request.contextPath}/book/findAll?category=${category}&page=${totalPage}&search=${search}">末页</a>

                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>

    </div>


</div>

</body>

</html>