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
				<jsp:param value="5-104" name="p" />
			</jsp:include>
			<div class="main-content" id="main">
				<div class="breadcrumbs breadcrumbs-fixed">公告管理</div>
				<div class="page-content content">
					<div class="searchdiv"></div>
					<div class="menubar">
						<a href="${WebUtil.projectname }/announcement/edit/0">添加公告</a>
					</div>
					<div>
						<c:set value="${items }" var="dataPage" scope="request"></c:set>
						<c:set value="${WebUtil.projectname }/announcement/list?page=" var="fullUrl"
							scope="request"></c:set>
						<jsp:include page="../main_page.jsp"></jsp:include>
					</div>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Title</th>
								<th>Content</th>
								<th>CreateTime</th>
								<th>Sort</th>
								<th>Recommend</th>
								<th>Operate</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items.getContent() }" var="item"
								varStatus="indexStatus">
								<tr>
									<td>${item.id }</td>
									<td>${item.title }</td>
									<td>${item.content }</td>
									<td>${WebUtil.formatDate(item.createTime) }</td>
									<td>${item.sort }</td>
									<td>${item.recommend }</td>
									<td><a
										href="${WebUtil.projectname }/announcement/edit/${item.id }">查看</a>
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
			$.get('${WebUtil.projectname }/announcement/remove/'+iid,{},function(data){
				if (data.success) {
					alert("删除成功");
					window.location.href = '${WebUtil.projectname }/announcement/list';
				} else {
					alert("删除失败,错误代码:" + data.code + ",错误消息:" + data.message
							+ ".");
				}
			});
		}
	</script>
</body>
</html>