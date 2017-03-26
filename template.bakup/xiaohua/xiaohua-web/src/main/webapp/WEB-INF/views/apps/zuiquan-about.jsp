<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="uft-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="/resources/css/helppage.css">
</head>
<body>
        <div id="page-wrap">
            <div class="info-col curCol" style="width: 320px; background-image: url('/resources/img/apps/zuiquan_640_1136.jpg')">
        <dl>
            <c:forEach var="item" items="${helps}">
                <dt style="padding: 10px 5px 0px 10px; cursor: pointer; font-size: 20px;">${item.title}</dt>
                <dd style="position: relative; top: -1px; left: 0px;">&nbsp;&nbsp;&nbsp;&nbsp;${item.content}</dd>
            </c:forEach>
        </dl>
    </div>
</div>
<script src='/resources/js/jquery.min.js'></script>
</body>
</html>