<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${art.title}</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	</div>
	<div class="container">

		<dl>
			<dt>
				<h2 align="center">${art.title }</h2>
			</dt>
			<dd>${art.user.username }
				&nbsp;
				<fmt:formatDate value="${art.created }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</dd>
			<dd>${art.content}</dd>
		</dl>
	</div>
</body>
</html>