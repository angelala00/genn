<!doctype html>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ include file="../common/path.jsp"%>

<html>
<head>
    <meta charset="uft-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><decorator:title/> - ${appinfo.name}</title>
    <link rel="shortcut icon" href="${path}/resources/img/m.gif">
    <link rel="stylesheet" href="${path}/resources/css/pure-min.css">
    <link rel="stylesheet" href="${path}/resources/css/main.css">
    <link rel="stylesheet" href="${path}/resources/css/baby-blue.css">
    <decorator:head/>
</head>
<body>
<div class="header">

    <h1>${appinfo.name}</h1>
    <blockquote>
        ${appinfo.name}
    </blockquote>
</div>
<decorator:body/>
</body>
</html>