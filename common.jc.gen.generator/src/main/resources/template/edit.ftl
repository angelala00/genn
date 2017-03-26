<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
						<tr>
							<td>
								<img src='<%=request.getContextPath()%>/image/lb_new.gif' class="div_control_image">
								修改培训专家（提示：标有&nbsp;
								<span style="color: red;">*</span>
								&nbsp;为必填项）
							</td>
						</tr>
					</table>
				</div>
				<form name="form2" action="<%=request.getContextPath()%>/modifyTrain_Expert.action" method="post">
					<table class="table_noFrame" width="100%" border="1" bordercolor="#ffffff" cellspacing="#ffffff" cellpadding="0">
						<tr>
							<td width="12%" height="1" align="right" nowrap="nowrap">专家姓名</td>
							<td width="38%" align="left">
								<input type="text" class="text_field" value="${ train_ex.ex_name }"name="train_ex.ex_name" maxlength="225" />
							</td>
							<td width="12%" align="right" nowrap="nowrap">性别</td>
							<td width="38%" align="left">
								<c:if test="${ train_ex.ex_sex ==1}">男</c:if>
								<c:if test="${ train_ex.ex_sex ==0}">女</c:if>
							</td>
						</tr>
						<tr>
							<td align="right">学历</td>
							<td align="left"><select name="train_ex.fk_ex_edu_id">
									<c:forEach items="${ map.Educational }" var="p">
										<c:if test="${p.pk_edu_id==train_ex.fk_ex_edu_id }">
											<option value="${p.pk_edu_id }" selected="selected">${
												p.edu_name }</option>
										</c:if>
										<c:if test="${p.pk_edu_id!=train_ex.fk_ex_edu_id }">
											<option value="${p.pk_edu_id }">${ p.edu_name }</option>
										</c:if>
									</c:forEach>
							</select>
							</td>
							<td align="right" nowrap="nowrap">详细地址</td>
							<td align="left"><input type="text" class="text_field"
								name="train_ex.ex_address" value="${ train_ex.ex_address }"
								maxlength="500" />
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">联系电话</td>
							<td align="left"><input type="text" class="text_field"
								name="train_ex.ex_contact" maxlength="225"
								value="${ train_ex.ex_contact }" /> <input type="hidden"
								value="${ train_ex.pk_ex_id }" name="train_ex.pk_ex_id">
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>