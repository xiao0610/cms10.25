<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${art.title}</title>
</head>
<body>
	<div class="container">
		<div class="container" align="right">
			<button class="btn btn-success" onclick="check(1)">同意</button>
			<button class="btn btn-warning" onclick="check(-1)">驳回</button>
			<button class="btn btn-info" onclick="back()">返回</button>
		</div>
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
<script type="text/javascript">
	//审核
	function check(status) {
		var id = '${art.id}';
		$.post("/admin/updateArticle", {
			id : id,
			status : status
		}, function(flag) {
			if (flag) {
				alert("操作成功");
			} else {
				alert("操作失败");
			}
		})
	}
	function back(){
		$("#content-wrapper").load("/admin/article");
	}
</script>
</html>