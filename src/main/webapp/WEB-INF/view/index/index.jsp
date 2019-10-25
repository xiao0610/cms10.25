<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cms系统</title>
<body>
	<div>
		<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	</div>

	<div class="container">

		<br>
		<!-- 主体 -->
		<div class="row ">
			<!-- 左侧栏目 -->
			<div class="col-md-2">
				<ul class="list-group text-center">
					<li
						class="list-group-item cms-list-group-item-action ${null==article.channelId?"cms-list-group-item-active":"" }"><a
						href="/?hot=1" class="channel">热门 ${c.id}</a></li>
				</ul>
				<c:forEach items="${channel}" var="c">
					<ul class="list-group text-center">
						<li
							class="list-group-item cms-list-group-item-action ${c.id==article.channelId?"cms-list-group-item-active":"" }"><a
							href="?channelId=${c.id}" class="channel">${c.name}</a></li>
					</ul>
				</c:forEach>
			</div>

			<!-- 中间文章标题 -->
			<div class="col-md-8 split min_h_500">
				<div>
					<c:if test="${article.channelId!=null}">
						<!--显示栏目下分类  -->
						<ul class="nav">
							<li class="nav-item ${null==article.categoryId?"cms-list-group-item-active":"" }"><a
								class="nav-link" href="?channelId=${article.channelId}">全部</a></li>
							<c:forEach items="${category}" var="c">
								<li class="nav-item ${c.id==article.categoryId?"cms-list-group-item-active":"" }"><a
									class="nav-link"
									href="?channelId=${art.channelId}&categoryId=${c.id}">${c.name }</a>
								</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${article.channelId==null}">
						<!--轮播图  -->
						<div>
							<div class="bd-example">
								<div id="carouselExampleCaptions" class="carousel slide"
									data-ride="carousel">
									<ol class="carousel-indicators">
										<li data-target="#carouselExampleCaptions" data-slide-to="0"
											class="active"></li>
										<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
										<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
									</ol>
									<div class="carousel-inner">
										<c:forEach items="${slide}" var="s" varStatus="i">
											<div class="carousel-item ${i.count=='1'?"active":""}">
												<img src="/pic/${s.url}" class="d-block w-100" alt="...">
												<div class="carousel-caption d-none d-md-block">
													<h5>${s.title}</h5>
												</div>
											</div>
										</c:forEach>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleCaptions" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleCaptions" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
							</div>
						</div>
					</c:if>
				</div>

				<div>
					<hr>
					<c:forEach items="${list}" var="a">
						<div class="media">
							<img src="/pic/${a.picture}" class="mr-3" alt="..."
								style="width: 190px; height: 124px">
							<div class="media-body ">
								<h5 class="mt-0 " style="height: 100px;">
									<a href="/select?id=${a.id }" target="blank">${a.title}</a>
								</h5>
								<h5 class="mt-0 myposition">${a.user.username }
									&nbsp;
									<fmt:formatDate value="${a.created }"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</h5>
							</div>
						</div>
						<hr>
					</c:forEach>
					${pages}
				</div>
			</div>

			<!-- 右侧边栏 -->
			<div class="col-md-2 split min_h_500">
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong>24小时热文</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${art24}" var="a">
								<div class="media">
									<img src="/pic/${a.picture }" class="mr-3" alt="..."
										width="60px" height="60px">
									<div class="media-body">
										<h6>
											<a href="/select?id=${a.id}" target="blank">${a.title }</a>
										</h6>
										<h6 class="mt-0 myposition">${a.user.username }
											&nbsp;
											<fmt:formatDate value="${a.created }"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</h6>
									</div>
								</div>
								<br>
							</c:forEach>

						</div>
					</div>
				</div>
				<!--  最新文章 -->
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong> 最新文章</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${newart}" var="a">
								<div class="media">
									<img src="/pic/${a.picture }" class="mr-3" alt="..."
										width="60px" height="60px">
									<div class="media-body">
										<h6>
											<a href="/select?id=${a.id }" target="blank">${a.title }</a>
										</h6>
										<h6 class="mt-0 myposition">${a.user.username }
											&nbsp;
											<fmt:formatDate value="${a.created }"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</h6>
									</div>
								</div>
								<br>
							</c:forEach>

						</div>
					</div>
				</div>


			</div>
		</div>







	</div>
	</div>
	</div>
	<div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
	$(".page-link").click(function(){
		var data=$(this).attr("data")
		location=data
	})
</script>
</html>