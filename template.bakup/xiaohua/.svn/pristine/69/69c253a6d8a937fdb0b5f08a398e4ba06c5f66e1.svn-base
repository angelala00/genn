<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Products</h1>
</div>
<p><a class="pure-button pure-button-primary" href="/admin/products/edit/0">Add New Prodcut To Sale</a></p>
<div class="content">
    <table class="pure-table">
        <thead>
        <tr>
            <th>#</th>
            <th>name</th>
            <th>code</th>
            <th>type</th>
            <th>price</th>
            <th>realPrice</th>
            <th>score</th>
            <th>info</th>
            <th>valid</th>
            <th>operates</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${list}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${entity.name}</td>
                <td>${entity.code}</td>
                <td>${entity.productType.name}[${entity.productType.code}]</td>
                <td>${entity.price}</td>
                <td>${entity.realPrice}</td>
                <td>${entity.score}</td>
                <td>${entity.info}</td>
                <td>
                    <c:if test="${entity.valid==1}"><fmt:message key="label.product.valid_true"/> </c:if><c:if
                        test="${entity.valid==0}"><fmt:message key="label.product.valid_false"/></c:if></td>
                <td>
                    <a href="/admin/products/edit/${entity.id}"><fmt:message key="opers.edit"/></a>
                    <c:if test="${entity.valid==1}">
                    <a href="/admin/products/unsale/${entity.id}"><fmt:message key="label.product.valid_false"/></a>
                </c:if>
                    <c:if test="${entity.valid==0}">
                        <a href="/admin/products/sale/${entity.id}"><fmt:message key="label.product.sale"/></a></c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
