<!doctype html>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ include  file="../common/path.jsp"%>
<html>
<head>
    <title><decorator:title/> - ${appinfo.name}</title>
    <link rel="shortcut icon" href="${path}/resources/img/e.gif">
    <link rel="stylesheet" href="${path}/resources/css/pure-min.css">
    <link rel="stylesheet" href="${path}/resources/css/baby-blue.css">
    <link rel="stylesheet" href="${path}/resources/css/main.css">
    <script src="${path}/resources/js/jquery-2.0.3.min.js"></script>
    <script src="${path}/resources/js/main.js"></script>
    <decorator:head/>
</head>

<body>

<div class="pure-g-r" id="layout">
    <a href="#menu" id="menuLink" class="pure-menu-link">
        <span></span>
    </a>

    <div class="pure-u" id="menu">
        <div class="pure-menu pure-menu-open">
            <a class="pure-menu-heading" href="${path}/admin">${appinfo.name}</a>
            <ul>
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('manager')">
                        <li class="">
                            <a href="${path}/admin/users?appId=xiaohua">用户</a>
                        </li>
                        <sec:authorize access="hasRole('admin')">
                            <li class="">
                                <a href="${path}/admin/region?appId=xiaohua">区域管理</a>
                            </li>
							   <li class="">
                                <a href="${path}/admin/xiaohua">笑话管理</a>
                            </li>
                            <li class="">
                                <a href="${path}/admin/users/blacklist?appId=xiaohua">黑名单</a>
                            </li>
                        </sec:authorize>
                        <li class="">
                            <a href="${path}/admin/users/top/0?appId=xiaohua">积分榜</a>
                        </li>
                        <li class=" ">
                            <a href="${path}/admin/integrals?appId=xiaohua">积分记录</a>
                        </li>
                        <li class=" ">
                            <a href="${path}/admin/exchanges?appId=xiaohua">兑换记录</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('admin')">
                        <li class=" ">
                            <a href="${path}/admin/channels?appId=xiaohua">渠道</a>
                        </li>
                        <li class=" ">
                            <a href="${path}/admin/products?appId=xiaohua">销售产品</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('payer')">
                        <li class=" ">
                            <a href="${path}/admin/withdraws?appId=xiaohua">支付宝提现记录</a>
                        </li>
                    </sec:authorize>
                    <%--    <li class=" ">
                            <a href="/admin/dnproducts">久久数字平台产品</a>
                        </li>--%>
                    <sec:authorize access="hasRole('manager')">
                        <li class="">
                            <a href="${path}/admin/stat">Stat</a>
                        </li>
                    </sec:authorize>
                  <%--  <sec:authorize access="hasRole('admin')">
                        <li class="">
                            <a href="/admin/statistics/four-dimensions">每月数据量</a>
                        </li>
                    </sec:authorize>--%>
                    <sec:authorize access="isAuthenticated()">
                        <li class="menu-item-divided">
                            <a><sec:authentication property="principal.username"/> </a>
                        </li>
						
                        <%--      <li class=" ">
                                  <a>${sessionScope.user.lastestLoginTime} </a>
                              </li>--%>
                        <li class="menu-item-divided">
                            <a href="${path}/admin/updatePassword">修改密码</a>
                        </li>
                        <li class="menu-item-divided">
                                <%--<a href="/user/login/out">Login Out</a>--%>
                            <a href="/j_spring_security_logout">Login Out</a>
                        </li>
                    </sec:authorize>
                </sec:authorize>
            </ul>
        </div>
    </div>
    <div class="pure-u-1" id="main">
        <sec:authorize access="isAuthenticated()">
            <decorator:body/>
        </sec:authorize>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $(".content > table tr").each(function (index, e) {
            if (index % 2 == 0) {
                $(e).addClass("pure-table-odd");
            }
        });
        var requestURI = '<%= request.getRequestURI()%>';
//        console.info(requestURI);
        var latestMatchNav;
        $("#menu ul >li > a:lt(8)").each(function (index, ele) {
//             console.info($(ele).attr("href"));
//             console.info(new RegExp($(ele).attr("href"),"g").test(requestURI));
            if (new RegExp($(ele).attr("href"), "g").test(requestURI)) {
                latestMatchNav = ele;
            }
        });
        $(latestMatchNav).parent().addClass("pure-menu-selected");
    });
</script>
</html>