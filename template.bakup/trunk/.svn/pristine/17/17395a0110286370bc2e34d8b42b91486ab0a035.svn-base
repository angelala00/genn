<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="text-center mt10 vm">
<c:if test="${dataPage.getTotalPages() > 1 }">

<c:set var="switchnum" value="20"></c:set>
<%-- <c:set var="" value=""></c:set> --%>
<%-- <c:set var="" value=""></c:set> --%>
<%-- <c:set var="" value=""></c:set> --%>

	<ul class="pagination">
		<li <c:if test="${!dataPage.hasPreviousPage() }"> class="disabled"</c:if>><a <c:if test="${dataPage.hasPreviousPage() }"> href="${fullUrl }${dataPage.number - 1 }"</c:if>><i class="icon-arrow-left"></i> 上一页</a></li>
		
		<c:set var="begintemp" value="0"></c:set>
		<c:set var="endtemp" value="${dataPage.totalPages - 1 }"></c:set>
		
		<c:if test="${dataPage.totalPages >= switchnum}">
			<c:if test="${dataPage.number >= dataPage.totalPages - 3}">
				<c:set var="begintemp" value="${dataPage.totalPages - 7 }"></c:set>
			</c:if>
			<c:if test="${dataPage.number < dataPage.totalPages - 3}">
				<c:if test="${dataPage.number <= 4}">
				<c:set var="begintemp" value="0"></c:set>
				</c:if>
				<c:if test="${dataPage.number > 4}">
				<c:set var="begintemp" value="${dataPage.number - 3 }"></c:set>
				</c:if>
			</c:if>
		</c:if>
		
		
		<c:if test="${dataPage.totalPages >= switchnum}">
			<c:if test="${dataPage.number <= 3}">
				<c:set var="endtemp" value="6"></c:set>
			</c:if>
			<c:if test="${dataPage.number > 3}">
				<c:if test="${dataPage.number < dataPage.totalPages - 6}">
				<c:set var="endtemp" value="${dataPage.number + 3 }"></c:set>
				</c:if>
				<c:if test="${dataPage.number >= dataPage.totalPages - 6}">
				<c:set var="endtemp" value="${dataPage.totalPages - 1 }"></c:set>
				</c:if>
			</c:if>
		</c:if>
		
		
		<c:if test="${dataPage.totalPages >= switchnum}">
			<c:if test="${dataPage.number > 4}">
				<li><a href="${fullUrl }0">1</a></li>
				<li><span class="shenglue">...</span></li>
			</c:if>
		</c:if>
		<c:forEach begin="${begintemp }" end="${endtemp }" var="pagenum">
			<c:if test="${pagenum == dataPage.number }">
				<li class="active"><a href="javascript:;">${pagenum + 1 } <span class="sr-only">(current)</span></a></li>
			</c:if>
			<c:if test="${pagenum != dataPage.number }">
				<li><a href="${fullUrl }${pagenum }">${pagenum + 1 }</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${dataPage.totalPages >= switchnum}">
			<c:if test="${dataPage.number < dataPage.totalPages - 6}">
				<li><span class="shenglue">...</span></li>
				<li><a href="${fullUrl }${dataPage.totalPages - 2 }">${dataPage.totalPages - 1 }</a></li>
				<li><a href="${fullUrl }${dataPage.totalPages - 1 }">${dataPage.totalPages - 0 }</a></li>
			</c:if>
		</c:if>
		
		<li <c:if test="${!dataPage.hasNextPage() }"> class="disabled"</c:if>><a <c:if test="${dataPage.hasNextPage() }"> href="${fullUrl }${dataPage.number + 1 }"</c:if>>下一页 <i class="icon-arrow-right"></i></a></li>
	</ul>
	<span class="f12 c666 ml10">共${dataPage.totalPages }页 
	去第 <input id="gotopageinput" value="" type="text" style="width: 35px;padding-bottom: 0px;" /> 页 <button onclick="gotopage()">确定</button>
	<script type="text/javascript">
	function gotopage(){
		window.location.href='${fullUrl }' + ($('#gotopageinput').val() - 1);
	}
	</script>
	</span>
</c:if>
</div>
