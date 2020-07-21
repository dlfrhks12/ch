<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상단바</title>
<style>
   #right {
   position: absolute;
   top: 10px;
   }
   #logo {
   width: 50px;
   height: 50px;
   }
   #mypage {
   position: absolute;
   right:100px;
   top:0px;
   }
   
</style>
<script>
$(function() {
	   $("#logout").on("click", function() {
	      var param = {
	         _csrf:"${_csrf.token}"
	      }
	      $.ajax({
	         url: "/cheatingday/logout",
	         method: "post",
	         data: param,
	         success: function() {
	            location.href = "/cheatingday/login";
	         }
	      })
	   });
	});
</script>
</head>
<body>
    <nav class="navbar navbar-light bg-light static-top">
        <div class="container">
           <div>
              <a href="/cheatingday"><img class="logo_image" src="/cheatingday/mainhome/images/logo.png" id="logo"/></a>
               <a class="navbar-brand" href="/cheatingday">Cheating Day</a>
           </div>
           <div class="form-group">
              <ul id="right">
                 <!-- 로그인 하지 않았을 때 보여줄 메뉴 -->
                 <sec:authorize access="isAnonymous()">
                     <a class="btn btn-danger" href="/cheatingday/login">로그인/회원가입</a>
                 </sec:authorize>         
                 
                 <!-- 일반회원(ROLE_USER)이 로그인 했을 때 보여줄 메뉴 -->
                 <sec:authorize access="hasRole('ROLE_USER')">
                    <a class="btn btn-danger" href="/cheatingday/user/mypage">내 정보</a>
                 </sec:authorize>
                 
                 <!-- 로그인 했을 때 모든 사용자에게 보여줄 메뉴 -->
                 <sec:authorize access="isAuthenticated()">
                    <a class="btn btn-danger" href="#" id="logout">로그아웃</a>
                 </sec:authorize>
                 
                 <!-- 사업자회원(ROLE_MANAGER)이 로그인 했을 때 보여줄 메뉴 -->
                 <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <a class="btn btn-danger" href="/cheatingday/manager/main">사장님페이지</a>
                 </sec:authorize>
                 
                 <!-- 관리자(ROLE_ADMIN)가 로그인했을 때 보여줄 메뉴 -->
                 <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-danger" href="/cheatingday/admin/main">관리자페이지</a>
                 </sec:authorize>
              </ul>
           </div>
        </div>
    </nav>
</body>
</html>