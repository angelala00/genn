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
					<div class="menubar">添加渠道</div>
					<table class="table table-striped table-bordered table-hover">
						<tbody>
							<tr>
								<td>Code</td>
								<td><input id="id" type="hidden" value="${item.id }">
									<input id="code" type="text" value="${item.code }">
								</td>
							</tr>
							<tr>
								<td>CodeInt</td>
								<td><input id="codeInt" type="text"
									value="${item.codeInt }"></td>
							</tr>
							<tr>
								<td>名称</td>
								<td><input id="name" type="text" value="${item.name }"></td>
							</tr>
							<tr>
								<td>描述</td>
								<td><input id="description" type="text"
									value="${item.description }"></td>
							</tr>
							<tr>
								<td>是否可用</td>
								<td><input id=isValid type="text"
									value="${item.isValid }"></td>
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
			var code = $('#code').val();
			var codeInt = $('#codeInt').val();
			var name = $('#name').val();
			var description = $('#description').val();
			var isValid = $('#isValid').val();
			$.ajax({
				  url: '${WebUtil.projectname }/channel/save',
				  data: {
		 				id : id,
		 				code : code,
		 				codeInt : codeInt,
		 				name : name,
		 				description : description,
		 				isValid : isValid
				  },
				  success: function(data){
						if (data.success) {
							alert("保存成功");
							window.location.href = '${WebUtil.projectname }/channel/list';
						} else {
							alert("保存失败,错误代码:" + data.code + ",错误消息:" + data.message
									+ ".");
						}
				  },
				  error: function(jqXHR,textStatus,errorThrown){
					  alert("服务器异常,状态码:"+jqXHR.status+","+textStatus);
				  }
				});
		}
	</script>
</body>
</html>