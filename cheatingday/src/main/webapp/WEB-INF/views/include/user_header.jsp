<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="user_header">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand"  href="/cheatingday/user"></a>
		</div>
		<ul class="nav navbar-nav" id="menu_parent">
			<sec:authorize access="hasRole('ROLE_USER')">
				<li><a href='/cheatingday/user/mypage'>내 정보</a></li>
				<li><a href='/cheatingday/user/point'>포인트</a></li>
				<li><a href='/cheatingday/user/buylist'>구매내역</a></li>
				<li><a href='/cheatingday/user/reviewlist'>내가 쓴 리뷰</a></li>
			</sec:authorize>
		</ul>
	</div>
</div>
</body>
</html>