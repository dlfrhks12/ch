<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<script>
	   
//일반회원 정규식 검증
function check(value, pattern, area, fail_msg) {
	area.text("");
	if(value.length==0) { 
		area.text("필수입력입니다").css({"color":"red", "font-size":"0.75em"});
		return false;
	} else if(pattern.test(value)==false) { 
		area.text(fail_msg).css({"color":"red", "font-size":"0.75em"});
		return false;
	}
	return true
}

//비밀번호 확인
function checkPassword() {
	var pattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
	return check($("#aNewPassword").val(), pattern, $("#a_password_msg"), "비밀번호는 특수문자 포함 영숫자 8~10자입니다");
}

//비밀번호 일치 확인
function checkPassword2() {
	$("#a_password2_msg").text("");
	var pwd1 = $("#aNewPassword").val();
	var pwd2 = $("#aNewPassword2").val();
	if(pwd1!==pwd2) {
		$("#a_password2_msg").text("비밀번호가 일치하지 않습니다").css({"color":"red", "font-size":"0.75em"});
		return false;
	}
	return true;
}
	   
	   
$("#change_pwd").on("click", function() {
	var r1 = checkPassword();
	var r2 = checkPassword2();
	var result = r1 && r2;
	if(result===true) {
		$("#change_pwd_form").submit();
	}
})

</script>
</head>
<body>
	<form id="change_pwd_form" action="/cheatingday/u_change_pwd" method="post">
		<div>
		<label class="txt p-b-11" for="aNewPassword">새 비밀번호</label>
			<div class="wrap-input100 validate-input m-b-36">
				<input class="input200" type="password" id="aNewPassword" name="aNewPassword"> 
				<span class="focus-input200"></span> 
				<span id="a_password_msg"></span>
			</div>
		</div>
		<div>
			<label class="txt p-b-11" for="aNewPassword2">새 비밀번호 확인</label>
			<div class="wrap-input100 validate-input m-b-36">
				<input class="input200" type="password" id="aNewPassword2">
				<span class="focus-input200"></span> 
				<span id="a_password2_msg"></span>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<button id="changePwd">비밀번호 변경</button>
	</form>
</body>
</html>