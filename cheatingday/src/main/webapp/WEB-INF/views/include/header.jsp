<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/cheatingday/script/vaild.js"></script>
<style>
#header p {
	text-align: center;
	font-size: 2.0em;
	letter-spacing: 0.45em;
	bottom: 0px;
}

#right {
	position: relative;
	font-size: small;
	text-align: center;
	left: 600px;
	bottom: 30px;
}
</style>
<script>
	// 로그아웃 처리 - post로 요청
	$(function() {
		$("#logout").on("click", function() {
			var param = {
				_csrf : "${_csrf.token}"
			}
			$.ajax({
				url : "/cheatingday/main/logout",
				method : "post",
				data : param,
				success : function() {
					location.href = "/cheatingday";
				}
			})
		});
	});
	
</script>
</head>
<body>
	<div id="header" class="col s12">
		<div>
			<p><a href="/cheatingday">Cheating Day</a></p>
		</div>
		<div class="form-group" >
			<ul id="right" >
				<!-- 로그인 하지 않았을 때 보여줄 메뉴 -->
				<sec:authorize access="isAnonymous()">
					<a class="btn btn-danger" href="/cheatingday/main/login">로그인/회원가입</a>
				</sec:authorize>

				<!-- 로그인 했을 때 모든 사용자에게 보여줄 메뉴 -->
				<sec:authorize access="isAuthenticated()">
					<a class="btn btn-danger" href="#" id="logout">로그아웃</a>
				</sec:authorize>

				<!-- ROLE_USER(일반 회원) 권한으로 로그인했을 때 보여줄 메뉴 -->
				<sec:authorize access="hasRole('ROLE_USER')">
					<a class="btn btn-danger" href='/cheatingday/user'>마이페이지</a>
				</sec:authorize>

				<!-- ROLE_MANAGER(사업자) 권한으로 로그인했을 때 보여줄 메뉴 -->
				<sec:authorize access="hasRole('ROLE_MANAGER')">
					<a class="btn btn-danger" href='/cheatingday/manager'>사장님 페이지로</a>
				</sec:authorize>

				<!-- ROLE_ADMIN(관리자) 권한으로 로그인했을 때 보여줄 메뉴 -->
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">관리자 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<a class="btn btn-danger" href="/cheatingday/admin">관리자 페이지로 </a>
						</ul>
				</sec:authorize>
			</ul>
		</div>
	</div>
</body>
</html>