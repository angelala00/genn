<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="main_css.jsp"></jsp:include>
</head>
<body class="skin-2 navbar-fixed breadcrumbs-fixed">
	<jsp:include page="main_top.jsp"></jsp:include>
	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<jsp:include page="main_left.jsp">
				<jsp:param value="1-" name="p" />
			</jsp:include>
			<div class="main-content" id="main">
				<div class="breadcrumbs breadcrumbs-fixed">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed');
						} catch (e) {
						}
					</script>
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home home-icon"></i> <a href="/main/">首页</a></li>
					</ul>
				</div>
				<div class="conteng">
					<span style="font-size: 32px;font-weight: normal;font-family: 'Open Sans','Helvetica Neue',Helvetica,Arial,sans-serif;">Welcome to mrile manager!</span>
				</div>
			</div>
		</div>
	</div><jsp:include page="main_bottem.jsp"></jsp:include>
</body>
</html>