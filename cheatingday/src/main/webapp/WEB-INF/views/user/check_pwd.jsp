<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function(){
	$("#check_pwd").on("click", function(){
			$("#check_pwd_frm").submit();
	})
});
</script>
</head>
<body>
	<form id="check_pwd_frm" action="/cheatingday/user/check_pwd" method="post">
		비밀번호:<input type="password" id="uPassword" name="uPassword"><br>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}"><br>
		<button type="button" class="btn btn-info" id="check_pwd">로그인</button>
	</form>
</body>
</html>


