<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- <div> -->
<!-- 	<div class="maintop"> -->
<!-- 		<div>mrile manager!</div> -->
<!-- 		<div>每日一乐管理后台</div> -->
<!-- 	</div> -->
<!-- </div> -->
<div class="navbar navbar-default navbar-fixed-top" id="navbar">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed');
		} catch (e) {
		}
	</script>
	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="javascript:;" class="navbar-brand"> <small> <i class="icon-mrile-manager"></i>每日一乐管理后台
			</small>
			</a>
		</div>
		<div class="navbar-header pull-right">
			您好：<sec:authentication property="name"/>
		</div>
	</div>
</div>