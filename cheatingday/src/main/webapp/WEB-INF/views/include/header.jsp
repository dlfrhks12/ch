<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#header {
	border: 1px solid red;
}

#header p {
	text-align: center;
	font-size: 3.0em;
	letter-spacing: 0.45em
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
			<p><a href="/cheatingday">Cheating Day!</a></p>
		</div>
		<div class="form-group">
			<ul id="right">
				<!-- 로그인 하지 않았을 때 보여줄 메뉴 -->
				<sec:authorize access="isAnonymous()">
					<a class="btn btn-danger" href="/cheatingday/main/login">로그인/회원가입</a>
				</sec:authorize>

				<!-- 로그인 했을 때 모든 사용자에게 보여줄 메뉴 -->
				<sec:authorize access="isAuthenticated()">
					<li><a href="#" id="logout">로그아웃</a></li>
				</sec:authorize>

				<!-- ROLE_USER(일반 회원) 권한으로 로그인했을 때 보여줄 메뉴 -->
				<sec:authorize access="hasRole('ROLE_USER')">
					<li><a href='/cheatingday/user'>마이페이지</a></li>
				</sec:authorize>

				<!-- ROLE_MANAGER(사업자) 권한으로 로그인했을 때 보여줄 메뉴 -->
				<sec:authorize access="hasRole('ROLE_MANAGER')">
					<li><a href='/cheatingday/manager'>사장님 페이지로</a></li>
				</sec:authorize>

				<!-- ROLE_ADMIN(관리자) 권한으로 로그인했을 때 보여줄 메뉴 -->
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">관리자 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/cheatingday/admin">관리자 페이지로 </a></li>
						</ul>
				</sec:authorize>
			</ul>
		</div>
	</div>
</body>
</html>