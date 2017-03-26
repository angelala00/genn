<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script language="javascript" src="<%=request.getContextPath()%>/js/gap-mainframe.js"></script>
</head>
<body>
	<script language="javascript">
		function save_onClick() {
			document.form2.submit();
		}
	</script>
	<table class="table_operate" width="100%" background="/hrpsp/image/left_1.gif">
		<tr align="left">
			<td>
				<img width="3" height="22" src="/hrpsp/image/rig_1.gif" />
				<input name="a 2" type="button" class="save" style="width:50px;" onClick="javascript:save_onClick();" value="保存" />
				<input name="a 2" type="button" class="cal" style="width:50px;" onClick="javascript: history.go(-1);" value="取消" />
			</td>
			<td align="right">
				<img class="help" src="/hrpsp/image/help.gif" title="帮助" />
			</td>
		</tr>
	</table>
	<table width="100%" height="100%" border="0" align="center" class="table-content">
		<tr>
			<td valign="top">
				<div id="ccParent1">
					<table class="table_div_control">
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>