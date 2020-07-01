<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/cheatingday/user/check_pwd" method="post">
		비밀번호:<input type="password" name="password"><br>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
		<button>로그인</button>
	</form>
</body>
</html>


