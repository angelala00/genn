<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="common/path.jsp"%>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<div class="content">
    <h2 id="striped-table" class="">Admin Login<a href="#striped-table" class="content-link"></a></h2>
    <p>
        some description here
    </p>
    <div class="pure-g grid-example">
        <div class="pure-u-1-5">
        </div>
        <div class="pure-u-3-5">
            <div class="l-box">
                <style scoped="scoped">
                    .pure-form{margin-top: 0px;margin-bottom: 20px;}
                    .pure-form>div{margin-top: 20px;}
                </style>
                <form class="pure-form" action="${path}/<c:url value='j_spring_security_check' />" method="post">
                    <div class="pure-control-group">
                        <input type="text" class="pure-input-1 l-centered" name="username" placeholder="Username">
                    </div>
                    <div class="pure-control-group">
                        <input type="password" class="pure-input-1 l-centered" name="password" placeholder="Password">
                    </div>
                    <div class="pure-control-group">
                        <button type="submit" class="pure-button pure-input-1 pure-button-primary l-centered">login in</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="pure-u-1-5">
        </div>
    </div>
    <c:if test="${not empty msg}">
        <p>
            Your login attempt was not successful, try again.<br/> Caused :
            <button  class="button-error pure-button">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</button>
        </p>
    </c:if>
</div>
</body>
</html>
