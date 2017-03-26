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
				<div class="breadcrumbs breadcrumbs-fixed">公告管理</div>
				<div class="page-content content">
					<div class="menubar">添加公告</div>
					<table class="table table-striped table-bordered table-hover">
						<tbody>
							<tr>
								<td>标题</td>
								<td><input id="id" type="hidden" value="${item.id }">
									<input id="title" type="text" value="${item.title }">
								</td>
							</tr>
							<tr>
								<td>内容</td>
								<td><input id="content" type="text"
									value="${item.content }"></td>
							</tr>
							<tr>
								<td>排序字段</td>
								<td><input id="sort" type="text" value="${item.sort }"></td>
							</tr>
							<tr>
								<td>是否推荐</td>
								<td><input id="recommend" type="text"
									value="${item.recommend }"></td>
							</tr>
						</tbody>
					</table>
					<div></div>
					<input type="button" onclick="dosave()" value="保存" />
				</div>
			</div>
		</div>
	</div><jsp:include page="../main_bottem.jsp"></jsp:include>
	<script type="text/javascript">
		function dosave() {
			var id = $('#id').val();
			var title = $('#title').val();
			var content = $('#content').val();
			var sort = $('#sort').val();
			var recommend = $('#recommend').val();
			$.ajax({
				  url: '${WebUtil.projectname }/announcement/save',
				  data: {
		 				id : id,
		 				title : title,
		 				content : content,
		 				sort : sort,
		 				recommend : recommend
				  },
				  success: function(data){
						if (data.success) {
							alert("保存成功");
							window.location.href = '${WebUtil.projectname }/announcement/list';
						} else {
							alert("保存失败,错误代码:" + data.code + ",错误消息:" + data.message
									+ ".");
						}
				  },
				  error: function(jqXHR,textStatus,errorThrown){
					  alert("服务器异常,状态码:"+jqXHR.status+","+textStatus);
				  }
				});
// 			$.get('${WebUtil.projectname }/announcement/save', {
// 				id : id,
// 				title : title,
// 				content : content,
// 				sort : sort,
// 				recommend : recommend
// 			}, function(data,status) {
// 				if (data.success) {
// 					alert("保存成功");
// 					window.location.href = '${WebUtil.projectname }/announcement/list';
// 				} else {
// 					alert("保存失败,错误代码:" + data.code + ",错误消息:" + data.message
// 							+ ".");
// 				}
// 			});
		}
	</script>
</body>
</html>