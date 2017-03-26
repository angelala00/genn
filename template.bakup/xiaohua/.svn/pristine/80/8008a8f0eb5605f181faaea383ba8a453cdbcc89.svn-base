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

<div class="content">
    <h2 class="content-subhead">Search:</h2>
    <form class="pure-form" action="${path}/admin/xiaohua/" method="get">
		<input name="title" type="text" value="${param.title}">
        <button type="submit" class="pure-button pure-button-primary">DoSearch</button>
  </form>
  <a href="${path}/admin/xiaohua/add">添加</a>
    <table class="pure-table pure-table-horizontal">
        <thead>
        <tr>
            <th>#</th>
            <th>标题</th>
            <th>排序</th>
            <th>类型</th>
        	<th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${page.content}" varStatus="status">
            <td>${status.index+1}</td>
            <td>${entity.title}</td>
            <td>${entity.sort}</td>
            <td>${entity.type}</td>
                <td class="operators">
         <a href="${path}/admin/xiaohua/update/${entity.id}">修改</a>
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
					href="${path}/admin/xiaohua/?page=${page.number-1}&title=${param.title}"><fmt:message
							key="page.paginator.previous" /> </a></li>
			</c:if>
			<c:if test="${!page.lastPage}">
				<li><a class="pure-button next"
					href="${path}/admin/xiaohua/?page=${page.number+1}&title=${param.title}"><fmt:message
							key="page.paginator.next" /></a></li>
			</c:if>
		</ul>
	</div>
</div>
</div>
</body>
</html>
