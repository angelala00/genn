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
    <h2 class="content-subhead">User Search:</h2>
    <form class="pure-form" action="/admin/users/blacklist" method="post">
        <fieldset>
            <select id="queryField">
                <option value="name" selected="selected">UserName</option>
                <option value="mac">Mac</option>
                <option value="token">Token</option>
                <option value="openUdid">OpenUdid</option>
                <option value="ip">ip</option>
            </select>
            <input id="queryFieldValue" type="text" name="name" placeholder="query key words" value="${requestParam.queryFieldValue}">
            <button type="submit" class="pure-button pure-button-primary">DoSearch</button>
            <c:if test="${requestParam !=null && requestParam.queryField!=null && requestParam.queryFieldValue!=null}">
                <a type="" class="pure-button pure-button-primary" href="/admin/users/blacklist">ShowAll</a>
            </c:if>
        </fieldset>
    </form>
    <table class="pure-table pure-table-horizontal">
        <thead>
        <tr>
            <th>#</th>
             <th>userName/userId/mac/token/openUdid</th>
            <th>mobilePhone</th>
            <th>surplusScore</th>
            <th>createTime</th>
            <th>lastLoginIp</th>
            <th>operators</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${page.content}" varStatus="status">
            <tr data-id="${entity.userId}">
                <td>${status.index+1}</td>
                <td><span class="userName">
                    <a target="_blank" href="/admin/users/stat/${entity.userId}">${entity.nickName}</a>
                </span><br/>${entity.userId}<br/>${entity.mac}<br/>${entity.token}<br/>${entity.openUdid}</td>
                <td>${entity.mobilePhone}</td>
                <td>${entity.surplus}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.createTime}" /></td>
                <td>${entity.ip}</td>
                <td class="operators">
                    <c:if test="${!entity.isBlack}"><a href="/admin/users/black/${entity.id}"><fmt:message key="user.black"/></a></c:if>
                    <c:if test="${entity.isBlack}"><a href="/admin/users/white/${entity.id}"><fmt:message key="user.white"/></a></c:if>
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
                    <li><a class="pure-button prev" href="/admin/users/blacklist/${page.number-1}"><fmt:message
                            key="page.paginator.previous"/> </a></li>
                </c:if>
                <c:if test="${!page.lastPage}">
                    <li><a class="pure-button next" href="/admin/users/blacklist/${page.number+1}"><fmt:message
                            key="page.paginator.next"/></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        //console.info("query field ${requestParam.queryField}");
        $("#queryField").change(function(){
            var queryField = $("#queryField option:selected").attr("value");
            $("#queryFieldValue").attr("name",queryField);
        });
        if (${requestParam.queryField!=null}) {
            console.info($("#queryField option[value=${requestParam.queryField}]"));
            $("#queryField option[value=${requestParam.queryField}]").attr("selected","true");
        }
    });
</script>
</body>
</html>
