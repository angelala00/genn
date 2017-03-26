<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html;charset=utf-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="../../common/path.jsp" %>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>渠道区域得分限制</title>
</head>
<body>
<div style="padding-top:50px; padding-bottom:10px;  " align="center">${msg}</div>
<c:set var="subUrl" value="${path}/admin/region/subUpdate"/>
<c:if test="${empty  entity.id}">
    <c:set var="subUrl" value="subAdd"/>
</c:if>
<form method="post" class="pure-form" action="${subUrl}">
    <input name="id" type="hidden" value="${entity.id}">
    <table align="center">
        <tr>
            <td>省名称:</td>
            <td><input name="regionName" type="text" readonly value="${entity.regionName}"/></td>
        </tr>
        <tr>
            <td>城市名称:</td>
            <td><input name="cityName" type="text" readonly value="${entity.cityName}"/></td>
        </tr>
        <tr>
            <td>channel:</td>
            <td><c:if test="${empty list}">
                <input name="channel" type="text" readonly value="${entity.channel}"/>
            </c:if>
                <c:if test="${!empty list}">
                    <section name="channel">
                        <c:forEach var="channel" items="${list}">
                            <option value="${channel.code}">${channel.name}</option>
                        </c:forEach>
                    </section>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>每日得分上限:</td>
            <td><input name="score" type="text" value="${entity.score}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input name="" class="pure-button pure-button-primary pure-input-1-1" type="submit" value="提交"/></td>
        </tr>
    </table>

</form>
</body>
</html>