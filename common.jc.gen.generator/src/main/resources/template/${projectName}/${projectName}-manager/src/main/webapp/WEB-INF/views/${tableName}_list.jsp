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
			<#list tbinfo.columns as column>
			<th class="listCss">${column.name_java}</th>
			</#list>
		</tr>
		<c:forEach items="${'$'}{items.getContent() }" var="item" varStatus="indexStatus">
		<tr>
			<#list tbinfo.columns as column>
			<td>${'$'}{item.${column.nameJavaVar} }</td>
			</#list>
			<td><a href="${'$'}{WebUtil.projectname }/${tbinfo.tbname }/edit/${'$'}{item.id }">查看</a>
				<a onclick="removeOne(${'$'}{item.id })" href="javascript:;">删除</a></td>
		</tr>
		</c:forEach>
	</table>
	<jsp:include page="main_bottem.jsp"></jsp:include>
	<script type="text/javascript">
	</script>
</body>
</html>