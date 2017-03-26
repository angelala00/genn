<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Double Nine Products</h1>
</div>
<div class="content">
    <table class="pure-table">
        <thead>
        <tr>
            <th>#</th>
            <th>productID</th>
            <th>name</th>
            <th>price</th>
            <th>type</th>
            <th>info</th>
            <th>operates</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${list}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${entity.dnId}</td>
                <td>${entity.name}</td>
                <td>${entity.price}</td>
                <td>${entity.type}</td>
                <td>${entity.info}</td>
                <td><a href="del/${entity.id}">del</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="edit/${entity.id}">edit</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
