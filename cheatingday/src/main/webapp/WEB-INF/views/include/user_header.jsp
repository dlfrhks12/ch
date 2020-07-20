<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
   uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>

</style>
</head>
<body>
<sec:authentication property="principal.username" var="username"/>


<nav class="navbar navbar-expand-sm bg-danger navbar-dark">

<div id="wrap">
 	<ul class="navbar-nav">
   		<li class="nav-item active">
    		<a class="nav-link" href="/cheatingday/user/mypage?uUsername=${username}">내 정보</a>
    	</li>
    
		<li class="nav-item active">
			<a class="nav-link" href="/cheatingday/user/point?uUsername=${username}">포인트</a>
		</li>
 
    	<li class="nav-item active">
    		<a class="nav-link" href="/cheatingday/user/buylist?uUsername=${username}">구매내역</a>
    	</li>
    
    	<li class="nav-item active">
   			<a class="nav-link" href="/cheatingday/user/reviewlist?uUsername=${username}">내가 쓴 리뷰</a>
    	</li>
	</ul>
</div> 
  
</nav>
</body>
</html>