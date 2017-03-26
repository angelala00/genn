<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html;charset=utf-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="../../common/path.jsp" %>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>笑话</title>
</head>
<body>
<div style="padding-top:50px; padding-bottom:10px;  " align="center">${msg}</div>

<form method="post" class="pure-form" action="${path}/admin/xiaohua/subAdd" enctype="multipart/form-data">
    <input name="id" type="hidden" value="${entity.id}">
    <table align="center">
        <tr>
            <td>标题:</td>
            <td><input name="title" type="text"  value="${entity.title}"/></td>
        </tr>
        <tr>
            <td>内容:</td>
            <td><textarea name="content" >${entity.content}</textarea></td>
        </tr>
        <tr>
            <td>赞:</td>
            <td><input name="good" value="${entity.good}" type="text">
            </td>
        </tr>
        <tr>
            <td>图片:</td>
            <td><input name="pica" type="file"><input name="pic" type="hidden" value="${entity.pic}"></td>
        </tr>
		 <tr>
            <td>排序:</td>
            <td><input name="sort" value="${entity.sort}" type="text">
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input name="" class="pure-button pure-button-primary pure-input-1-1" type="submit" value="提交"/></td>
        </tr>
    </table>

</form>
</body>
</html>