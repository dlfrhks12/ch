<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
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
 <ul class="navbar-nav">
 
     
    <li class="nav-item active">
  	 <a class="nav-link" href="#">주문 확인</a>
    </li>
    
	<li class="nav-item active">
  	<div id="reviewManage">
  	 <a class="nav-link" href="#" >
  		 매장 리뷰 관리</a></div> 
    </li>
 
    <li class="nav-item active">
  	 <a class="nav-link" href="/cheatingday/manager/store_list?mUsername=${username}">내 매장 관리</a>
    </li>
    
    <li class="nav-item active">
  	 <a class="nav-link" href="/cheatingday/manager/information?mUsername=${username}">내 정보 관리</a>
    </li>
    
    <li class="nav-item active">
      <div id="menuManage"><a class="nav-link" href="#">메뉴 관리</a></div>
    </li>
   
      <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
      	고객센터
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#">공지 사항</a>
        <a class="dropdown-item" href="#">문의 답변</a>
      </div>
    </li>
  
  </ul>
</nav>




</body>
</html>