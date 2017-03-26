<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:action name="select" id="select"></s:action>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script language="javascript" src="<%=request.getContextPath()%>/js/gap-mainframe.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
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
			<td valign="top"><div id="ccParent1">
					<table class="table_div_control">
						<tr>
							<td>
								<img src='<%=request.getContextPath()%>/image/lb_new.gif' class="div_control_image">
								新增${BeanName}（提示：标有&nbsp;
								<span style="color: red;">*</span>
								&nbsp;为必填项）
							</td>
						</tr>
					</table>
				</div>
				<form name="form2" action="<%=request.getContextPath()%>/${actionName}!add.action" method="post">
					<table class="table_noFrame" width="100%" border="1" bordercolor="#ffffff" cellspacing="#ffffff" cellpadding="0">
					
						<#list tbinfo.columns as column>
						<#if column.name == tbinfo.pk>
						<tr style="display:none">
							<td width="12%" height="1" align="right" nowrap="nowrap">${column.name_label}</td>
							<td width="38%" align="left">
								<input type="hidden" class="text_field" name="${beanName}.${column.name_java}" maxlength="225"  value="${"${"}${beanName}.${column.name_java}${"}"}"/>
							</td>
						</tr>
						<#else>
						<tr>
							<td width="12%" height="1" align="right" nowrap="nowrap">${column.name_label}</td>
							<td width="38%" align="left">
								<input type="text" class="text_field" name="${beanName}.${column.name_java}" maxlength="225"  value="${"${"}${beanName}.${column.name_java}${"}"}"/>
							</td>
						</tr>
						</#if>  
						</#list>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>