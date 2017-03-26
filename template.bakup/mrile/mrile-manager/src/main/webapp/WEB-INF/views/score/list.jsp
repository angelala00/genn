<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../main_css.jsp"></jsp:include>
</head>
<body class="skin-2 navbar-fixed breadcrumbs-fixed">
	<jsp:include page="../main_top.jsp"></jsp:include>
	<div class="main-container" id="main-container"><div class="main-container-inner">
		<jsp:include page="../main_left.jsp">
			<jsp:param value="3-102" name="p" />
		</jsp:include>
		<div class="main-content" id="main">
			<div class="breadcrumbs breadcrumbs-fixed">积分管理</div>
			<div class="page-content content">
				<div class="searchdiv">
				用户ID:<input type="text" id="userid" value="${userid }" >
				<button onclick="doSearch()">搜索</button>
				</div>
				<div class="menubar">
				</div>
				<div>
					<c:set value="${items }" var="dataPage" scope="request"></c:set>
<%-- 					${WebUtil.removePageParam(current_href) } --%>
					<c:set value="${WebUtil.projectname }/score/list?userid=${userid }&page=" var="fullUrl" scope="request"></c:set>
					<jsp:include page="../main_page.jsp"></jsp:include>
				</div>
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>AppId</th>
							<th>UserId</th>
							<th>Score</th>
							<th>type</th>
							<th>channel</th>
							<th>CreateTime</th>
							<th>Status</th>
							<th>OrderId</th>
							<th>AdId</th>
							<th>AdName</th>
							<th>Msg</th>
							<th>IP</th>
							<th>UDID</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${items.getContent() }" var="item" varStatus="indexStatus">
							<tr>
								<td>${item.id }</td>
								<td>${item.appId }</td>
								<td>${item.userId }</td>
								<td>${item.score }</td>
								<td>${item.type }</td>
								<td>${item.channel }</td>
								<td>${WebUtil.formatDate(item.createTime) }</td>
								<td>${item.status }</td>
								<td>${item.orderId }</td>
								<td>${item.adId }</td>
								<td>${item.adName }</td>
								<td>${item.msg }</td>
								<td>${item.ip }</td>
								<td>${item.udid }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
		</div><jsp:include page="../main_bottem.jsp"></jsp:include>
	<script type="text/javascript">
		function doSearch(){
			var userid = $('#userid').val();
			window.location.href = '${WebUtil.projectname }/score/list?userid=' + userid;
		}
	</script>
</body>
</html>