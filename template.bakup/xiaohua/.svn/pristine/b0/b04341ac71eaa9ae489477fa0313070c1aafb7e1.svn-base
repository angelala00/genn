<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<div class="pure-g">
	<div class="pure-u-1">
		<p>currentPages
			:${page.number+1},totalPages:${page.totalPages},totalAmount:${page.totalElements}</p>
	</div>
	<div class="pure-u-1">
		<ul class="pure-paginator">
			<c:if test="${!page.firstPage}">
				<li><a class="pure-button prev"
					href="${path}/admin/exchanges/${page.number-1}"><fmt:message
							key="page.paginator.previous" /> </a></li>
			</c:if>
			<c:if test="${!page.lastPage}">
				<li><a class="pure-button next"
					href="${path}/admin/exchanges/${page.number+1}"><fmt:message
							key="page.paginator.next" /></a></li>
			</c:if>
		</ul>
	</div>
</div>