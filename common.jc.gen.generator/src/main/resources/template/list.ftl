<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script language="javascript" src="<%=request.getContextPath()%>/js/gap-mainframe.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/v1/refer.js"></script>
<script type="text/javascript">
//新连接
function openW(url){
	location.href="<%=path %>/"+url;
}
//删除操作前需确认
function ToDelUrl(url){
	if(confirm("此操作将不可逆转，你确定要删除？")){
	location.href="<%=path %>/"+url;
	}
}
</script>
</head>
<body>
	<table class="table_div_control">
		<tr>
			<td><img src="<%=request.getContextPath()%>/image/lb_detail.gif"
				class="div_control_image">&nbsp; ${beanName }列表</td>
			<td>
				<table align="right">
					<tr>
						<td>
							<input type="button" class="add" style="width: 50px;" onClick="javascript:window.location.href='<%=request.getContextPath()%>/${actionName }!toadd.action';"value="新增" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table class="table_div_content2">
		<tr>
			<td>
				<table cellspacing="0" cellpadding="0" border="0" align="center" width="99%" class="listCss">
					<tr>
						<td valign="top">
							<table cellspacing="1" cellpadding="1" border="0" width="100%">
								<tr valign="top">
									<#list tbinfo.columns as column>
									<th class="listCss" width="4%">${column.name_label}</th>
									</#list>
									<th class="listCss" width="4%">操作</th>
								</tr>
								<c:forEach items="${"${"}list ${"}"}" var="item" varStatus="v">
								<tr>
								<#list tbinfo.columns as column>
									<td class="listCss">${"${"}item.${column.name_java} ${"}"}</td>
								</#list>
									<td class="listCss">
										<input type="button" class="edit" style="width: 50px;" onClick="javascript:openW('${ActionName}!edit.action?${beanName}.${tbinfo.pk_java}=${"${"}item.${tbinfo.pk_java}${"}"}');" title="跳转到修改所选的某条记录" value="修改" />
										<input type="button" class="del" style="width: 50px;" onClick="javascript:ToDelUrl('${ActionName}!del.action?${beanName}.${tbinfo.pk_java}=${"${"}item.${tbinfo.pk_java}${"}"}');" title="删除所选记录" value="删除" />
									</td>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>