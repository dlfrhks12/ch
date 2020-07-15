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
   #topmenu{
      margin-left: -40px;
      height: 40px;
      width: 105%;
   }
   #a, #b, #c, #d{
      list-style: none;
      color: white;
      background-color: red;
      float: left;
      width:25%;
      line-height: 70px;
      vertical-align: middle;
      text-align: center;
   }
   #topmenu li a{
      text-decoration: none;
      color: white;
      display: block;
      font-size: 12px;
      font-weight: bold;
   }
   #topmenu li a:hover{
      color: red;
      background-color: white;
   }
   #navLogo {
   width: 50px;
   height: 50px;
   }
</style>
</head>
<body>
<sec:authentication property="principal.username" var="username"/>
<nav>
<div id="topmenu">
		<div class="navbar-header">
			<a class="navbar-brand" href="/cheatingday/"><img class="logo_image" src="/cheatingday/mainhome/images/logo.png" id="navLogo"/></a>
		</div>
		<ul>
			<sec:authorize access="hasRole('ROLE_USER')">
				<li id="a"><a href="/cheatingday/user/mypage?uUsername=${username}">내 정보</a></li>
				<li id="b"><a href="/cheatingday/user/point?uUsername=${username}">포인트</a></li>
				<li id="c"><a href="/cheatingday/user/buylist?uUsername=${username}">구매내역</a></li>
				<li id="d"><a href="/cheatingday/user/reviewlist?uUsername=${username}">내가 쓴 리뷰</a></li>
			</sec:authorize>
		</ul>
</div>
</nav>
</body>
</html>