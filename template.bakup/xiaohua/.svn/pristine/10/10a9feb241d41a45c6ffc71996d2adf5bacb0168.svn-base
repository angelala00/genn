<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Users</h1>
</div>
<div class="content">
    <table class="pure-table pure-table-horizontal">
        <thead>
        <tr>
            <th>#</th>
             <th>userName</th>
            <th>mobilePhone</th>
            <th><fmt:message key="isBlack"/> </th>
            <th>surplusScore</th>
            <th>totallyScore</th>
            <th>operators</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${page.content}" varStatus="status">
            <tr data-id="${entity.userId}">
                <td>${status.index+1}</td>
                <td><span class="userName">
                    <a href="/admin/users/stat/${entity.userId}">${entity.nickName}</a>
                </span><br/>${entity.userId}<br/>${entity.mac}<br/>${entity.token}<br/>${entity.openUdid}</td>
                <td>${entity.mobilePhone}</td>
                <td><c:if test="${entity.isBlack}">
                     <fmt:message key="yes"/>
                </c:if>
                    <c:if test="${!entity.isBlack}">
                        <fmt:message key="no"/>
                    </c:if></td>
                <td>${entity.surplus}</td>
                <td>${entity.totalPoints}</td>
                <td class="operators">
                    <a target="_blank" href="/admin/users/stat/${entity.userId}"><fmt:message key="detail"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pure-g">
        <div class="pure-u-1">
            <p>currentPages
                :${page.number+1},totalPages:${page.totalPages},totalAmount:${page.totalElements}</p>
        </div>
        <div class="pure-u-1">
            <ul class="pure-paginator">
                <c:if test="${!page.firstPage}">
                    <li><a class="pure-button prev" href="/admin/users/${page.number-1}"><fmt:message
                            key="page.paginator.previous"/> </a></li>
                </c:if>
                <c:if test="${!page.lastPage}">
                    <li><a class="pure-button next" href="/admin/users/${page.number+1}"><fmt:message
                            key="page.paginator.next"/></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>
