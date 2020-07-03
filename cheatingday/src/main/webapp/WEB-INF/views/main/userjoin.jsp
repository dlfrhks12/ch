<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="login/images/icons/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="login/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css" href="login/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="login/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="login/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="login/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="login/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="login/css/util.css">
<link rel="stylesheet" type="text/css" href="login/css/main.css">
<title>일반회원 회원가입</title>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<script>

// 일반 회원가입 정규식 검증

//이름 확인
function checkUserIrum() {
	var pattern = /^[가-힣]{2,5}$/;
	return check($("#u_irum").val(), pattern, $("#u_irum_msg"), "이름은 한글 2~5자입니다");
}

//아이디 확인
function checkUserUsername() {
	var pattern = /^[A-Za-z][A-Za-z0-9]{8,10}$/;
	return check($("#u_username").val(), pattern, $("#u_username_msg"), "아이디는 영숫자 8~10자입니다");
}

//비밀번호 확인
function checkUserPassword() {
	var pattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
	return check($("#u_password").val(), pattern, $("#u_password_msg"), "비밀번호는 특수문자 포함 영숫자 8~10자입니다");
}

//비밀번호 일치 확인
function checkUserPassword2() {
	$("#u_password2_msg").text("");
	var pwd1 = $("#u_password").val();
	var pwd2 = $("#u_password2").val();
	if(pwd1!==pwd2) {
		$("#u_password2_msg").text("비밀번호가 일치하지 않습니다").css({"color":"red", "font-size":"0.75em"});
		return false;
	}
	return true;
}

//이메일 확인
function checkUserEmail() {
	var pattern = /^[A-Za-z][A-Za-z0-9]+@[A-Za-z\.]+$/;
	return check($("#u_email").val(), pattern, $("#u_email_msg"), "잘못된 이메일 형식입니다");
}


//전화번호 확인
function checkUserTel() {
	var pattern = /^([0-9]{3})([0-9]{4})([0-9]{4})$/;
	return check($("#u_tel").val(), pattern, $("#u_tel_msg"), "전화번호는 10~11자리 숫자입니다")
}

///////////////////////////////////////////////////////////////////////

//ajax로 아이디 사용 여부 확인
function ajaxCheckId() {
	$.ajax({
		url: "/cheatingday/u_id_check",
		method: "get",
		data: "u_username=" + $("#u_username").val(),
	}).done(()=>{$("#u_username_msg").text("사용가능합니다").css({"color":"green","font-size":"0.75em"})})
	.fail(()=>{$("u_username_msg").text("사용중인 아이디입니다").css({"color":"red","font-size":"0.75em"})});
}

//ajax로 이메일 사용 여부 확인
function ajaxCheckEmail() {
	$.ajax({
		url: "/cheatingday/main/u_email_check",
		method: "get",
		data: "u_email=" + $("#u_email").val(),
	}).done(()=>{$("#u_email_msg").text("사용가능합니다").css({"color":"green","font-size":"0.75em"})})
	.fail(()=>{$("#u_email_msg").text("사용중인 이메일입니다").css({"color":"red","font-size":"0.75em"})});
}

$(function() {
	$("#u_username").on("blur", function() {
		var result = checkUserUsername();
		if(result==true)
			ajaxCheckId();
	});
	$("#email").on("blur", function() {
		var result = checkUserEmail();
		if(result==true)
			ajaxCheckEmail();
	});
	$("#u_irum").on("blur", checkUserIrum);
	$("#u_password").on("blur", checkUserPassword);
	$("#u_password2").on("blur", checkUserPassword2);
	$("#u_tel").on("blur", checkUserTel);
	
	$("#join").on("click", function() {
		// ajax 통신에서 multipart 넘기기위한 자바스크립트 객체
		var formData = new FormData(document.getElementById("join_form"));
		for(var key of formData.keys())
			console.log(key);
		for(var value of formData.values())
			console.log(value);
		
		var r1 = checkUserUsername();
		var r2 = checkUserEmail();
		var r3 = checkUserPassword();
		var r4 = checkUserPassword2();
		var r5 = checkUserTel();
		var r6 = checkUserIrum();
		var result = r1 && r2 && r3 && r4 && r5 && r6;
		if(result===true) {
			$.when($.ajax("/cheatingday/main/u_id_check?u_username=" + $("#u_username").val()),
				$.ajax("/cheatingday/main/u_check_email?u_email=" + $("#u_email").val()))
			}.done(()=>{($("#join_form").submit();) })
		}
	})	
});
</script>
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="join_form" action="/cheatingday/join_user" method="post">
					<span class="login100-form-title p-b-32">일반회원가입 </span> 
					<span class="login100-form-title p-b-32"></span> 
					<div>
						<span class="txt p-b-11" id="u_irum">이름</span>
						<div class="wrap-input100 validate-input m-b-36" id="u_irum">
							<input class="input200" type="text" id="u_irum" >
							<span class="focus-input200"></span>
							<span id="u_irum_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="u_username">아이디</span>
						<div class="wrap-input100 validate-input m-b-36" id="u_username">
							<input class="input200" type="text" id="u_username" >
							<span class="focus-input200"></span>
							<span id="u_username_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="u_password">비밀번호</span>
						<div class="wrap-input100 validate-input m-b-36" id="u_password">
							<input class="input200" type="password" id="u_password" >
							<span class="focus-input200"></span>
							<span id="u_password_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="u_password2">비밀번호 확인</span>
						<div class="wrap-input100 validate-input m-b-36" id="u_password2">
							<input class="input200" type="password" id="u_password2" >
							<span class="focus-input200"></span>
							<span id="u_password2_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="u_email">이메일</span>
						<div class="wrap-input100 validate-input m-b-36" id="u_email">
							<input class="input200" type="text" id="u_email" >
							<span class="focus-input200"></span>
							<span id="u_email_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="u_tel">전화번호</span>
						<div class="wrap-input100 validate-input m-b-36" id="u_tel">
							<input class="input200" type="text" id="u_tel" >
							<span class="focus-input200"></span>
							<span id="u_tel_msg"></span>
						</div>
					</div>
					<div class="form-group" style="text-align: center;">
						<button type="button" class="btn btn-danger" id="join">가입</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>