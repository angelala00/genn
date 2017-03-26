<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Channels</h1>
</div>
<div class="content">
    <p><fmt:message key="label.channel.integral_statistic"/><fmt:message key="click"/><a class="pure-button pure-button-primary pure-button-xsmall" target="_blank" href="/admin/integrals/stat"><fmt:message key="here"/></a></p>
    <table class="pure-table">
        <thead>
        <tr>
            <th>#</th>
            <th>name</th>
            <th>code</th>
            <th>maxScoreTimes</th>
            <th>maxScoreDaily</th>
            <th>twiceInternal</th>
            <th>order</th>
            <th>type</th>
            <th>isValid(/channels)</th>
            <th>isValid2(/channelss)</th>
            <th><fmt:message key="label.channel.dailyTimes"/> </th>
            <th>operates</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${list}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${entity.name}</td>
                <td>${entity.code}</td>
                <td>${entity.maxScoreTimes}</td>
                <td>${entity.maxScoreDaily}</td>
                <td>${entity.twiceInternal}</td>
                <td>${entity.order}</td>
                <td>${entity.type}</td>
                <td>${entity.valid}
                    <c:if test="${!entity.valid}">
                        <a href="/admin/channels/open/${entity.id}"><fmt:message key="open"/></a>
                    </c:if>
                    <c:if test="${entity.valid}">
                        <a href="/admin/channels/close/${entity.id}"><fmt:message key="close"/></a>
                    </c:if></td>
                <td>${entity.valid2}  <c:if test="${!entity.valid2}">
                    <a href="/admin/channels/open2/${entity.id}"><fmt:message key="open"/></a>
                </c:if>
                    <c:if test="${entity.valid2}">
                        <a href="/admin/channels/close2/${entity.id}"><fmt:message key="close"/></a>
                    </c:if>
                </td>
                <td>${entity.dailyTimes}</td>
                <td>
                    <a href="/admin/channels/edit/${entity.id}"><fmt:message key="opers.edit"/></a>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
