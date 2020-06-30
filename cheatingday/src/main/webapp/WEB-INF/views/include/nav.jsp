<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#right {
	position: absolute;
	top: 10px;
	}
</style>
</head>
<body>
<<<<<<< HEAD
    <nav class="navbar navbar-light bg-light static-top">
        <div class="container">
        	<div>
            	<a class="navbar-brand" href="/cheatingday">Cheating Day</a>
        	</div>
        	<div class="form-group">
        		<ul id="right">
        			<!-- 로그인 하지 않았을 때 보여줄 메뉴 -->
        			<sec:authorize access="isAnonymous()">
			            <a class="btn btn-danger" href="login">로그인/회원가입</a>
        			</sec:authorize>			
        			
        			<!-- 로그인 했을 때 모든 사용자에게 보여줄 메뉴 -->
        			<sec:authorize access="isAuthenticated()">
        				<a class="btn btn-danger" href="#" id="logout">로그아웃</a>
        			</sec:authorize>
        			
        			<!-- 일반회원(ROLE_USER)이 로그인 했을 때 보여줄 메뉴 -->
        			<sec:authorize access="hasRole('ROLE_USER')">
        				<a class="btn btn-danger" href='/cheatingday/user'>마이페이지</a>
        			</sec:authorize>
        			
        			<!-- 사업자회원(ROLE_MANAGER)이 로그인 했을 때 보여줄 메뉴 -->
        			<sec:authorize access="hasRole('ROLE_MANAGER')">
        				<a class="btn btn-danger" href="/cheatingday/manager">사장님페이지</a>
        			</sec:authorize>
        			
        			<!-- 관리자(ROLE_ADMIN)가 로그인했을 때 보여줄 메뉴 -->
        			<sec:authorize access="hasRole('ROLE_ADMIN')">
        				<a class="btn btn-danger" href="/cheatingday/admin">관리자페이지</a>
        			</sec:authorize>
        		</ul>
        	</div>
        </div>
    </nav>
    
=======
<img src="/cheatingday/imgs/cht.jpg" width="100px" height="100px">

	<div class="navbar navbar-expand-sm bg-dark navbar-dark" style="width: 100%; margin: 0 auto;">
		<div class="container-fluid">
  			<form class="form-inline" action="/action_page.php" style="margin: 0 auto">
  				<input class="form-control mr-sm-2" type="text" placeholder="건물명,도로명,지번으로 검색하세요">
    			<button class="btn btn-danger" type="submit">검색</button>
    		</form>
		</div>
	</div>
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
</body>
</html>