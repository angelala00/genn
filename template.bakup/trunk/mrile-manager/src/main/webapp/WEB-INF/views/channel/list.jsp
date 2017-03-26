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
				<jsp:param value="4-103" name="p" />
			</jsp:include>
			<div class="main-content" id="main">
				<div class="breadcrumbs breadcrumbs-fixed">渠道管理</div>
				<div class="page-content content">
					<div class="searchdiv"></div>
					<div class="menubar">
						<a href="${WebUtil.projectname }/channel/edit/0">添加渠道</a>
					</div>
					<div>
						<c:set value="${items }" var="dataPage" scope="request"></c:set>
						<c:set value="${WebUtil.projectname }/channel/list?page=" var="fullUrl"
							scope="request"></c:set>
						<jsp:include page="../main_page.jsp"></jsp:include>
					</div>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Code</th>
								<th>CodeInt</th>
								<th>Used</th>
								<th>Name</th>
								<th>Description</th>
								<th>IsValid</th>
								<th>CreateTime</th>
								<th>UpdateTime</th>
								<th>type</th>
								<th>pic</th>
								<th>Operate</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items.getContent() }" var="item"
								varStatus="indexStatus">
								<tr>
									<td>${item.id }</td>
									<td>${item.code }</td>
									<td>${item.codeInt }</td>
									<td>${item.used }</td>
									<td>${item.name }</td>
									<td>${item.description }</td>
									<td>${item.isValid }</td>
									<td>${WebUtil.formatDate(item.createTime) }</td>
									<td>${WebUtil.formatDate(item.updateTime) }</td>
									<td>${item.type }</td>
									<td>${item.pic }</td>
									<td><a
										href="${WebUtil.projectname }/channel/edit/${item.id }">查看</a>
										<a onclick="removeOne(${item.id })"
										href="javascript:;">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div><jsp:include page="../main_bottem.jsp"></jsp:include>
	<script type="text/javascript">
		function removeOne(iid){
			$.get('${WebUtil.projectname }/channel/remove/'+iid,{},function(data){
				if (data.success) {
					alert("删除成功");
					window.location.href = '${WebUtil.projectname }/channel/list';
				} else {
					alert("删除失败,错误代码:" + data.code + ",错误消息:" + data.message
							+ ".");
				}
			});
		}
	</script>
</body>
</html>