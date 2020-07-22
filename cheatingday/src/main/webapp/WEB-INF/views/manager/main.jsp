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
<style>
 section { margin-left: 320px; margin-top: 100px; margin-bottom: 100px;}
</style>
</head>

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
<body>
<sec:authentication property="principal.username" var="username"/>


<table>

<tr>
	<td>
	 <a href="/cheatingday/manager/information?mUsername=${username}">
	<img height="250px;" src="/cheatingday/manager/내정보 관리.png"></a></td>
	
	
	<td><a href="/cheatingday/manager/store_list?mUsername=${username}">
	<img height="250px;" src="/cheatingday/manager/매장관리.png"></a></td>
	
	<td><a style="cursor: pointer;" id="menuManage"><img height="250px;" src="/cheatingday/manager/메뉴관리.png"></a></td>
</tr>


<tr>
	<td><a href="/cheatingday/manager/order_list?mUsername=${username}">
	<img height="250px;" src="/cheatingday/manager/주문확인.png"></a></td>
  	<td><a style="cursor: pointer; " id="reviewManage" ><img height="250px;" src="/cheatingday/manager/리뷰관리.png"></a></td>
	<td><a href="/cheatingday/notice/list"><img height="250px;" src="/cheatingday/manager/공지사항.png"></a></td>
	<td><a href="/cheatingday/center/list"><img height="250px;" src="/cheatingday/manager/Q&A.png"></a></td>
</tr>
</table>

</id>
</body>
</html>