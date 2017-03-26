<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../../common/path.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Exchanges</h1>
</div>
<div class="content">
    <h2 class="content-subhead">Exchange <fmt:message key="label.alipay.drawout"/> Search:</h2>

    <form class="pure-form" action="/admin/exchanges/query" method="post">
        <fieldset>
            <input name="pageNo" type="hidden" value="<c:out value="${requestParam.pageNo}" default="0" />"/>
            <input name="name" type="text" placeholder="<fmt:message key="input.placeholder.name"/> "
                   value="${requestParam.name}"/>
            <input name="payNo" type="text" class="pure-input-1-4"
                   placeholder="<fmt:message key="input.placeholder.alipay"/>,<fmt:message key="input.placeholder.qq"/>,<fmt:message key="input.placeholder.mobile"/>"
                   value="${requestParam.payNo}"/>
            <select id="productType" name="productType">
                <option value="" selected="selected">All</option>
                <option value="ALIPAY">ALIPAY</option>
                <option value="QB">QB</option>
                <option value="MOBILE">MOBILE</option>
            </select>
            <input name="afterDate" type="date" placeholder="afterDate"
                   value="<fmt:formatDate pattern="yyyy-MM-dd" value="${requestParam.afterDate}"/>"/>
            <input name="beforeDate" type="date" placeholder="beforeDate"
                   value="<fmt:formatDate pattern="yyyy-MM-dd" value="${requestParam.beforeDate}"/>"/>
            <label for="cb" class="pure-checkbox">
                <input id="cb" type="checkbox"
                       <c:if test="${requestParam.unPaid}">checked</c:if> name="unPaid"><fmt:message
                    key="label.alipay.unpaid"/>
            </label>
            <button type="submit" class="pure-button pure-button-primary">DoSearch</button>
            <c:if test="${requestParam !=null}">
                <a type="" class="pure-button pure-button-primary" href="/admin/exchanges/0">ShowAll</a>
            </c:if>
        </fieldset>
    </form>
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
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.createTime}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.processingTime}"/></td>
                <td>${entity.state.code}</td>
                </td>
            </tr>
        </c:forEach>
        </tbody> 
    </table>
    <jsp:include page="../../common/pagement.jsp"/>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        if (${requestParam!=null && requestParam.productType!=null}) {
            $("option[value='${requestParam.productType}']").attr("selected", true);
        }
    });
</script>
</body>
</html>
