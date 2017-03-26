<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../main_css.jsp"></jsp:include>
</head>
<body class="skin-2 navbar-fixed breadcrumbs-fixed">
	<jsp:include page="../main_top.jsp"></jsp:include>
	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<jsp:include page="../main_left.jsp">
				<jsp:param value="2-101" name="p" />
			</jsp:include>
			<div class="main-content" id="main">
				<div class="breadcrumbs breadcrumbs-fixed">用户管理</div>
				<div class="page-content content">
					<div class="searchdiv"></div>
					<div class="menubar"></div>
					<div>
						<c:set value="${items }" var="dataPage" scope="request"></c:set>
						<c:set value="${WebUtil.projectname }/user/list?page="
							var="fullUrl" scope="request"></c:set>
						<jsp:include page="../main_page.jsp"></jsp:include>
					</div>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>UserId</th>
								<th>ClientUserId</th>
								<th>LastIP</th>
								<th>Token</th>
								<th>NickName</th>
								<th>Phone</th>
								<th>CreateTime</th>
								<th>UpdateTime</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items.getContent() }" var="item"
								varStatus="indexStatus">
								<tr>
									<td>${item.id }</td>
									<td>${item.userId }</td>
									<td>${item.clientUserId }</td>
									<td>${item.ip }</td>
									<td>${item.token }</td>
									<td>${item.nickName }</td>
									<td>${item.mobilePhone }</td>
									<td>${WebUtil.formatDate(item.createTime) }</td>
									<td>${WebUtil.formatDate(item.updateTime) }</td>
									<td><a
										href="${WebUtil.projectname }/user/edit/${item.id }">查看</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div><jsp:include page="../main_bottem.jsp"></jsp:include>
	<script type="text/javascript">
		
	</script>
</body>
</html>