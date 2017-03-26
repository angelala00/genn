<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script language="javascript" src="<%=request.getContextPath()%>/js/gap-mainframe.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/v1/refer.js"></script>
<script type="text/javascript">
//新连接
function openW(url){
}
//删除操作前需确认
function ToDelUrl(url){
	if(confirm("此操作将不可逆转，你确定要删除？")){
	}
}
</script>
</head>
<body>
	<table class="table_div_control">
	</table>
</body>
</html>