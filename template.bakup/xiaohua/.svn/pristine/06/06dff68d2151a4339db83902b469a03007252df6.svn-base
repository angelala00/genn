<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Alipay Records</h1>
</div>
<div class="content">
    <h2 class="content-subhead">Alipay <fmt:message key="label.alipay.drawout"/> Search:</h2>

    <form id="queryForm" class="pure-form" action="/admin/withdraws/query" method="post">
        <fieldset>
            <input name="pageNo" type="hidden" value="<c:out value="${requestParam.pageNo}" default="0" />"/>
            <input name="name" type="text" placeholder="<fmt:message key="input.placeholder.name"/> " value="${requestParam.name}"/>
            <input name="mobile" type="text" placeholder="<fmt:message key="input.placeholder.mobile"/>" value="${requestParam.mobile}"/>
            <input name="payNo" type="text" placeholder="<fmt:message key="input.placeholder.alipay"/>" value="${requestParam.payNo}"/>
            <input name="afterDate" type="date" placeholder="afterDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${requestParam.afterDate}"/>" />
            <input name="beforeDate" type="date" placeholder="beforeDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${requestParam.beforeDate}"/>"/>
            <label for="cb" class="pure-checkbox">
                <input id="cb" type="checkbox" <c:if test="${requestParam.unPaid}">checked</c:if>  name="unPaid"><fmt:message key="label.alipay.unpaid"/>
            </label>
            <button type="submit" class="pure-button pure-button-primary">DoSearch</button>
            <c:if test="${requestParam !=null}">
                <a type="" class="pure-button pure-button-primary" href="/admin/withdraws/0">ShowAll</a>
            </c:if>
        </fieldset>
    </form>
    <a type="submit" href="/admin/withdraws/export" class="pure-button pure-button-primary">Export To Excel</a>
    <button  class="pure-button pure-button-primary" onclick="$('#batchOperForm').submit();"><fmt:message key='keywords.batch'/><fmt:message key='page.withdraw.opers.charged'/></button>
    <table class="pure-table">
        <thead>
        <tr>
            <th><input  type="checkbox" onclick="batchSelector(this)"/></th>
            <th>userName</th>
            <th>userPhoneNo.</th>
            <th><fmt:message key="isBlack"/></th>
            <th>alipayNo</th>
            <th>amountMoney</th>
            <th>createTime</th>
            <th>checkTime</th>
            <th>state</th>
            <th>merge</th>
            <th>operates</th>
        </tr>
        </thead>
        <tbody>
        <c:set scope="page" var="payAccountTmp" value=""/>
        <c:set scope="page" var="payAccountIndex" value="1"/>
        <form  id='batchOperForm' action="/admin/withdraws/batchCharged" method="post">
        <c:forEach var="entity" items="${page.content}" varStatus="status">
            <c:if test="${payAccountTmp!='' && payAccountTmp!=entity.alipayNo}">
                <c:set scope="page" var="payAccountIndex" value="${payAccountIndex+1}"/>
            </c:if>
            <tr
                    <c:if test="${payAccountTmp!='' && payAccountTmp!=entity.alipayNo}">class="splitTr"</c:if>
                    payAccountIndex="${payAccountIndex}">
                <c:set scope="page" var="payAccountTmp" value="${entity.alipayNo}"/>
                <td><input name="id" value="${entity.id}" type="checkbox"/></td>
                <td>${entity.user.nickName}</td>
                <td>${entity.user.mobilePhone}</td>
                <td><c:if test="${entity.user.isBlack}">
                    <fmt:message key="yes"/>
                </c:if>
                    <c:if test="${!entity.user.isBlack}">
                        <fmt:message key="no"/>
                    </c:if></td>
                <td>${entity.alipayNo}</td>
                <td>${entity.amountMoney}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.createTime}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.checkTime}"/></td>
                <td>${entity.state.code}</td>
                <td></td>
                <td>
                    <c:if test="${entity.state.code=='doing'||entity.state.code=='fail'}">
                    <a href="/admin/withdraws/charged/${entity.id}"><fmt:message key='page.withdraw.opers.charged'/></a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                        href="/admin/withdraws/rollback/${entity.id}"><fmt:message
                        key='page.withdraw.opers.rollback'/></a></td>
                </c:if>
                <c:if test="${entity.state.code!='doing'}"><fmt:message key="page.withdraw.opers.done"/> </c:if>
            </tr>
        </c:forEach>
        </form>
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
                           <c:if test="${requestParam ==null}">href="/admin/withdraws/${page.number-1}" </c:if>><fmt:message
                                key="page.paginator.previous"/></a>
                    </li>
                </c:if>
                <c:if test="${!page.lastPage}">
                    <li>
                        <a class="pure-button next"  <c:if
                                test="${requestParam !=null}"> onclick="pageNav(${page.number+1})" </c:if>
                           <c:if test="${requestParam ==null}">href="/admin/withdraws/${page.number+1}" </c:if>><fmt:message
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
        $("#queryForm input").each(function (index, ele) {
            $(ele).change(function () {
                $("#queryForm input[name='pageNo']").val(0);
            });
        });

        mergePayAccount(${payAccountIndex});
    });
</script>
</body>
</html>
