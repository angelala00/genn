<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="uft-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="/resources/css/helppage.css">
    <title>Help Page</title>
</head>
<body>
<div id="page-wrap">
    <div class="info-col curCol" style="width: 320px;">
        <img src="http://css-tricks.com/examples/InfoGrid/images/aquaman.jpg"/>
        <dl>
            <c:forEach var="item" items="${helps}">
                <dt style="padding: 10px 5px 0px 10px; cursor: pointer; font-size: 20px;">${item.title}</dt>
                <dd style="position: relative; top: -1px; left: 0px;">&nbsp;&nbsp;&nbsp;&nbsp;${item.content}</dd>
            </c:forEach>
        </dl>
    </div>
</div>
<script src='/resources/js/jquery.min.js'></script>
<script src='/resources/js/infogrid.js'></script>
</body>
</html>