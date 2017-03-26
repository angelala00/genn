<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Integrals</h1>
</div>
<div class="content">
    <h2 class="content-subhead">Integral Search:</h2>

    <form id="queryForm" class="pure-form" action="/admin/integrals/query" method="POST">
        <fieldset>
            <input name="pageNo" type="hidden" value="<c:out value="${requestParam.pageNo}" default="0" />"/>
            <input type="text" name="name" placeholder="<fmt:message key="input.placeholder.name"/>"
                   class="pure-input-1-4" value="${requestParam.name}">
            <select id="channel" name="channel">
                <option value="" selected="selected">All</option>
               <c:forEach var="channel" items="${list}" varStatus="status">
<option value="${channel.code}">${channel.name}</option>
               </c:forEach>
            </select>
            <input name="afterDate" type="date" placeholder="afterDate"
                   value="<fmt:formatDate pattern="yyyy-MM-dd" value="${requestParam.afterDate}"/>"/>
            <input name="beforeDate" type="date" placeholder="beforeDate"
                   value="<fmt:formatDate pattern="yyyy-MM-dd" value="${requestParam.beforeDate}"/>"/>
            <button type="submit" class="pure-button pure-button-primary pure-input-1-1">DoSearch</button>
            <c:if test="${requestParam !=null && (requestParam.name!=null)}">
                <a type="" class="pure-button pure-button-primary" href="/admin/integrals/0">ShowAll</a>
            </c:if>
        </fieldset>
    </form>
    <p><fmt:message key="description.integral"/><a class="pure-button pure-button-primary pure-button-xsmall"
                                                   href="/admin/integrals/stat"><fmt:message key="here"/></a></p>
    <table class="pure-table">
        <thead>
        <tr>
            <th>#</th>
            <th>userName</th>
            <th>channel</th>
            <th>score</th>
            <th>time</th>
            <sec:authorize access="hasRole('admin')">
                <th>orderId</th>
                <th>adId</th>
                <th>adName</th>
                <th>ip</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${page.content}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${entity.user.nickName}</td>
                <td>${entity.channel.code}</td>
                <td>${entity.score}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.createTime}"/></td>
                <sec:authorize access="hasRole('admin')">
                    <td>${entity.orderId}</td>
                    <td>${entity.adId}</td>
                    <td>${entity.adName}</td>
                    <td>${entity.ip}</td>
                </sec:authorize>
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
                    <li>
                        <a class="pure-button prev" <c:if
                                test="${requestParam !=null}"> onclick="pageNav(${page.number-1})" </c:if>
                           <c:if test="${requestParam ==null}">href="/admin/integrals/${page.number-1}" </c:if>><fmt:message
                                key="page.paginator.previous"/></a>
                    </li>
                </c:if>
                <c:if test="${!page.lastPage}">
                    <li>
                        <a class="pure-button next"  <c:if
                                test="${requestParam !=null}"> onclick="pageNav(${page.number+1})" </c:if>
                           <c:if test="${requestParam ==null}">href="/admin/integrals/${page.number+1}" </c:if>><fmt:message
                                key="page.paginator.next"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        if (${requestParam!=null && requestParam.channel!=null}) {
            $("option[value='${requestParam.channel}']").attr("selected", true);
        }
        $("#queryForm").change(function () {
            $("#queryForm input[name='pageNo']").val(0);
        });
    });
</script>
</body>
</html>
