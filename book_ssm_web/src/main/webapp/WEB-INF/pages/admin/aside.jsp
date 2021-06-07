<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${loginUser.username}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index">
                <a href="${pageContext.request.contextPath}/pages/admin/main.jsp">
                    <i class="fa fa-dashboard"></i>
                    <span>首页</span></a>
            </li>

            <c:if test="${\"admin\".equals(loginUser.getRole())}">
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-cogs"></i>
                        <span>系统管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
				    </span>
                    </a>

                    <ul class="treeview-menu">

                        <li id="system-setting">
                            <a href="${pageContext.request.contextPath}/admin/sales/findAll">
                                <i class="fa fa-circle-o"></i> 用户管理
                            </a>
                        </li>

                        <li id="system-setting">
                            <a href="${pageContext.request.contextPath}/admin/data/category/show">
                                <i class="fa fa-circle-o"></i> 销售数据
                            </a>
                        </li>

                        <li id="system-setting">
                            <a href="${pageContext.request.contextPath}/sysLog/findAll">
                                <i class="fa fa-circle-o"></i> 访问日志
                            </a>
                        </li>
                    </ul>

                </li>
            </c:if>

            <c:if test="${\"sales\".equals(loginUser.getRole())}">
                <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                    <span>基础数据</span> <span class="pull-right-container"> <i
                            class="fa fa-angle-left pull-right"></i>
				</span>
                </a>
                    <ul class="treeview-menu">

                        <li id="system-setting"><a
                                href="${pageContext.request.contextPath}/admin/book/findAll">
                            <i class="fa fa-circle-o"></i> 书籍管理
                        </a></li>
                        <li id="system-setting"><a
                                href="${pageContext.request.contextPath}/admin/data/sales/show"> <i
                                class="fa fa-circle-o"></i> 数据统计
                        </a></li>

                    </ul>
                </li>
            </c:if>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>