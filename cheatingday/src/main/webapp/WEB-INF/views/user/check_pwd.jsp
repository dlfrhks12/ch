<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.container { 
	padding-right: 15px; 
	padding-left: 450px; 
	margin-right: auto; 
	margin-left: auto; 
}
</style>
<script>
$(function(){
	$("#check_pwd").on("click", function(){
			$("#check_pwd_frm").submit();
	})
});
</script>
</head>
<body>
<div class="container">
	<div style="padding: 40px 0;">
		<h3>비밀번호 재확인</h3>
	</div>
	<form id="check_pwd_frm" action="/cheatingday/user/check_pwd" method="post">
		비밀번호:<input type="password" id="uPassword" name="uPassword"><br>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}"><br>
		<button type="button" class="btn btn-info" id="check_pwd">로그인</button>
	</form>
</div>
</body>
</html>


