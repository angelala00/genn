<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html;charset=utf-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="../../common/path.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Users</h1>
</div>
<div class="content">
    <h2 class="content-subhead">User Search:</h2>
    <form class="pure-form" action="${path}/admin/region/" method="get">
		<input name="cityName" type="text" value="${param.cityName}">
        <button type="submit" class="pure-button pure-button-primary">DoSearch</button>
  </form>
  <%--<a href="${path}/admin/region/add">添加</a>--%>
    <table class="pure-table pure-table-horizontal">
        <thead>
        <tr>
            <th>#</th>
            <th>省名称</th>
            <th>城市名称</th>
            <th>控制的渠道</th>
            <th>当前分数限制（0表示不限制）</th>
        	<th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${page.content}" varStatus="status">
            <td>${status.index+1}</td>
            <td>${entity.regionName}</td>
            <td>${entity.cityName}</td>
            <td>${entity.channel}</td>
            <td>${entity.score}</td>
                <td class="operators">
         <a href="${path}/admin/region/update/${entity.id}">修改</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<div class="pure-g">
	<div class="pure-u-1">
		<p>currentPages
			:${page.number+1},totalPages:${page.totalPages},totalAmount:${page.totalElements}</p>
	</div>
	<div class="pure-u-1">
		<ul class="pure-paginator">
			<c:if test="${!page.firstPage}">
				<li><a class="pure-button prev"
					href="${path}/admin/region/?page=${page.number-1}&cityName=${param.cityName}"><fmt:message
							key="page.paginator.previous" /> </a></li>
			</c:if>
			<c:if test="${!page.lastPage}">
				<li><a class="pure-button next"
					href="${path}/admin/region/?page=${page.number+1}&cityName=${param.cityName}"><fmt:message
							key="page.paginator.next" /></a></li>
			</c:if>
		</ul>
	</div>
</div>
</div>
</body>
</html>
