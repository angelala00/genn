<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>每日一乐 - 看笑话放松心情</title>
<meta name="keywords" content="笑话,趣图,段子,搞笑,爆笑,内涵,内涵段子,搞笑笑话,搞笑趣图,搞笑段子,有趣段子,有意思的段子,有意思的笑话,笑话大全,每日一乐,每日乐,MRILE" />
<meta name="description" content="每日一乐(MRILE),分享搞笑笑话,段子,趣图等,每天看每日一乐,忘记烦恼忧愁,调节生活节奏,让你每天都有一个好心情。" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="common_css.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="common_header.jsp"></jsp:include>
	<div class="container">
		<div class="position">
			<a href="/">每日一乐</a> <span>&gt;</span> <strong>笑话</strong>
		</div>
		<div class="content">
			<div class="module articlelist">
				<ul>
					<c:forEach items="${items.getContent() }" var="xiaohua">
					<li>
						<div class='hd'>${xiaohua.title }</div> <pre>${xiaohua.content }</pre>
					</li>
					</c:forEach>
				</ul>
			</div>
			<c:set value="${items }" var="dataPage" scope="request"></c:set>
			<c:set value="/?page=" var="fullUrl" scope="request"></c:set>
			<jsp:include page="common_page.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="common_footer.jsp"></jsp:include>
</body>
</html>
