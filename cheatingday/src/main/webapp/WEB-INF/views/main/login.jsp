<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/cheatingday/script/valid.js"></script>
<script>
$(function() {
	var msg = "${msg}";
	if(msg!="") {
		$("#alert")
		$("#msg").show();
	}
})
</script>
</head>
<body>
	<div class="alert alert-success alert=dismissible" id="msg" style="display:none;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<span id="alert"></span>
	</div>
	<form action="login_form" action="/cheatingday/main/login" method="post">
		<div class="form-group">
			<label for="username"></label>
			<input id="username" type="text" name="username" class="form-control">
			<span class="helper-text" id="usernaem_msg"></span>
		</div>
		<div class="form-group">
			<label for="password"></label>
			<input id="password" type="password" name="password" class="form-control">
			<span class="helper-text" id="password_msg"></span>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<button type="button" class="btn btn-danger" id="login">로그인</button>
	</form>
</body>
</html>


