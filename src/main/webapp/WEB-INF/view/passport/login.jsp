<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>欢迎登录</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<br>
	<div class="container">
		<div class="row passport_bg">

			<div class="col-md-6">
				<br>
				<div class="card">
					<div class="card-body">
						<span style="color: red">${error }</span>
						<h5 class="card-title" align="center">欢迎登录</h5>
						<form action="/passport/login" id="form1" method="post">

							<div class="form-group">
								<label for="username">用户名:</label> <input id="username"
									class="form-control" type="text" name="username"
									value="${user.username}">
							</div>
							<div class="form-group">
								<label for="password">密码:</label> <input id="password"
									class="form-control" type="password" name="password">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-success">登录</button>
								<button type="reset" class="btn btn-warning">重置</button>
							</div>

						</form>

					</div>
				</div>

			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
						<img src="/resource/images/guest.jpg" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/resource/images/guest1.jpg" alt="..."
							class="rounded-circle img-thumbnail" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br /> <br />
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
</body>
<script type="text/javascript">
	$("#form1").validate({
		rules : {
			username : {
				required : true,
				rangelength : [ 2, 5 ],
			},
			password : {
				required : true,
				rangelength : [ 6, 10 ],
			}
		},
		messages : {
			username : {
				required : "用户名不能为空",
				rangelength : "用户名长度必须在2-5之间",
			},
			password : {
				required : "密码不能为空",
				rangelength : "密码长度必须在6-10之间 ",
			}
		}
	})
</script>
</html>