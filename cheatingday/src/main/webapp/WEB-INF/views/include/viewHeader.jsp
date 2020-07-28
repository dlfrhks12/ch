<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script>
$(function() {
	//메뉴관리 접근불가
	$("#menuManage").click(function() {
		var username = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}";
		$.ajax({
			url:"/cheatingday/manager/exists_snum",
			method:"get",
			data: 'mUsername='+username,
			success:function(result) {
				if(result==true) {
					location.href="/cheatingday/manager/menu_list?mUsername="+username;
				}else {
					Swal.fire("Info","등록된 매장이 없습니다! 매장 등록 후 이용해주세요","info");
				}
			}
		})
	})
	
	//매장리뷰관리 접근불가
	$("#reviewManage").click(function(){
		var username = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}";
		$.ajax({
			url:"/cheatingday/manager/exists_review",
			method:"get",
			data: 'mUsername='+username,
			success:function(result){
				if(result==true){
					location.href="/cheatingday/manager/review_list?mUsername="+username;
				}else{
					Swal.fire("Info","등록된 리뷰가 없습니다!","info");
				}
			}
						
		});
	});
});
</script>
</head>
<body>
<sec:authentication property="principal.username" var="username"/>


<nav class="navbar navbar-expand-sm bg-danger navbar-dark">

<div id="wrap">
 <ul class="navbar-nav">
 	<sec:authorize access="hasRole('ROLE_USER')">
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
    	<li class="nav-item active"><a class="nav-link" href="/cheatingday/notice/list">공지사항</a></li>
 	</sec:authorize>
 	
 	
 	
 <sec:authorize access="hasRole('ROLE_MANAGER')">
 
   <li class="nav-item active">
  	 <a class="nav-link" href="/cheatingday/manager/information?mUsername=${username}">내 정보 관리</a>
    </li>  
    <li class="nav-item active">
  	 <a class="nav-link" href="/cheatingday/manager/store_list?mUsername=${username}">내 매장 관리</a>
    </li> 
    <li class="nav-item active">
      <a id="menuManage" class="nav-link" href="/cheatingday/manager/order_list?mUsername=${username}">메뉴 관리</a>
    </li>
    
    <li class="nav-item active">
  	 <a class="nav-link" href="/cheatingday/manager/order_list?mUsername=${username}">주문 확인</a>
    </li>
	<li class="nav-item active">
  	 <a id="reviewManage" class="nav-link" href="#" >매장 리뷰 관리</a>
    </li>
 
  
    <li class="nav-item dropdown nav-item active">
      <a class=" nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">고객센터</a>
      <div class="dropdown-menu" >
        <a class="dropdown-item" href="/cheatingday/notice/list">공지 사항</a>
        <a class="dropdown-item" href="/cheatingday/center/list">Q & A</a>
      </div>
    </li>
  </sec:authorize>
  
  
  
  <sec:authorize access="hasRole('ROLE_ADMIN')">
  		<li class="nav-item active"><a class="nav-link" href="/cheatingday/admin/report_list">신고리뷰목록</a></li>
		<li class="dropdown nav-item active">
			<a class="nav-link" class="dropdown-toggle" data-toggle="dropdown" href="#"> 회원정보 관리 <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="/cheatingday/admin/user_list?job=user_list">일반 회원 목록</a></li>
				<li><a href="/cheatingday/admin/manager_list">사업자 목록</a></li>
			</ul>
		</li>
		<li class="nav-item active"><a class="nav-link" href="/cheatingday/notice/list">공지사항</a></li>
		<li class="nav-item active"><a class="nav-link" href="/cheatingday/center/list">Q&A</a></li>
  	</sec:authorize>
  </ul>
 </div>  
</nav>




</body>
</html>