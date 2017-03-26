<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="main_css.jsp"></jsp:include>
</head>
<body>
	<table class="table_div_control">
		<tr>
						<th class="listCss">id</th>
			<th class="listCss">name</th>
			<th class="listCss">description</th>
			<th class="listCss">parentid</th>
			<th class="listCss">create_time</th>
			<th class="listCss">update_time</th>
			<th class="listCss">sort</th>
		</tr>
		<c:forEach items="${items.getContent() }" var="item" varStatus="indexStatus">
		<tr>
			<td>${item.id }</td>
			<td>${item.name }</td>
			<td>${item.description }</td>
			<td>${item.parentid }</td>
			<td>${item.createTime }</td>
			<td>${item.updateTime }</td>
			<td>${item.sort }</td>
			<td><a href="${WebUtil.projectname }/category/edit/${item.id }">查看</a>
				<a onclick="removeOne(${item.id })" href="javascript:;">删除</a></td>
		</tr>
		</c:forEach>
	</table>
	<jsp:include page="main_bottem.jsp"></jsp:include>
	<script type="text/javascript">
	</script>
</body>
</html>