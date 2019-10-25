<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入  css -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<!-- 后台页面样式模板 -->
<link rel="stylesheet" href="/resource/css/sb-admin.css">
<link rel="stylesheet" href="/resource/css/all.min.css">
<script type="text/javascript" src="/resource/js/cms.js"></script>
</head>
<body>
	<div class="container">
		<div class="form-group form-inline">
			<label for="title">文章标题:</label> <input id="title" type="text"
				class="form-control" name="title" value="${art.title}">
			&nbsp; <select name="status">
				<option value="0">待审</option>
				<option value="1">已审</option>
				<option value="-1">驳回</option>
			</select> &nbsp;
			<button class="btn btn-success btn-sm" type="button"
				onclick="query()">查询</button>
		</div>
		<table class="table table-bordered table-hover"
			style="text-align: center">
			<tr>
				<td>序号</td>
				<td>文章标题</td>
				<td>作者</td>
				<td>昵称</td>
				<td>发布时间</td>
				<td>文章状态</td>
				<td>点击量</td>
				<td>是否热门</td>
			</tr>
			<c:forEach items="${list}" var="arr" varStatus="index">
				<tr>
					<td>${index.index+1}</td>
					<td><a href="javascript:void(0)" data="/admin/article" onclick="see(${arr.id})">${arr.title}</a></td>
					<td>${arr.user.username}</td>
					<td>${arr.user.nickname}</td>
					<td><fmt:formatDate value="${arr.created}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${arr.status==0?'待审':arr.status==1?'已审':'驳回'}</td>
					<td>${arr.hits}</td>
					<td><c:if test="${arr.hot==0 }">
							<button class="btn btn-success" onclick="update(${arr.id},1)">否</button>
						</c:if> <c:if test="${arr.hot==1}">
							<button class="btn btn-danger" onclick="update(${arr.id},0)">是</button>
						</c:if></td>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>${pages}</div>
</body>
<script type="text/javascript">
	var a = '${art.status}'
	$("[name='status']").val(a)
	//查询
	function query() {
		var title = $("[name='title']").val()
		var status = $("[name='status']").val()
		$("#content-wrapper").load(
				"/admin/article?title=" + title + "&status=" + status);
	}
	
	function update(id,hot){
		//0:正常 1:停用
		var title = $("[name='title']").val()
		var status = $("[name='status']").val()
		var page=${page}
		$.post("/admin/updateArtlocked",{id:id,hot:hot},function(flag){
			$("#content-wrapper").load(
					"/admin/article?title=" + title + "&status=" + status+"&page="+page);
		})
	}
	function see(id){
		$("#content-wrapper").load("admin/seeart?id="+id);
	}
</script>
</html>