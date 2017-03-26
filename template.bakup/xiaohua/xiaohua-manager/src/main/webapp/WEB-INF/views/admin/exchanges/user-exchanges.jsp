<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Exchanges</h1>
</div>
<div class="content">
    <table class="pure-table">
        <thead>
        <tr>
            <th>#</th>
            <th>userName</th>
            <th>product</th>
            <th>account</th>
            <th>money/score</th>
            <th>createTime</th>
            <th>processingTime</th>
            <th>state</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${page.content}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${entity.user.nickName}</td>
                <td>${entity.product.productType.name}</td>
                <td>${entity.consumerAccount}</td>
                <td>${entity.moneyExchange}/${entity.scoreConsumption}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.createTime}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.processingTime}" /></td>
                <td>${entity.state.code}</td>
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
                    <li><a class="pure-button prev" href="${page.number-1}"><fmt:message
                            key="page.paginator.previous"/> </a></li>
                </c:if>
                <c:if test="${!page.lastPage}">
                    <li><a class="pure-button next" href="${page.number+1}"><fmt:message
                            key="page.paginator.next"/></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
