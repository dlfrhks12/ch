<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="login/images/icons/favicon.ico" />
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
<link rel="stylesheet" type="text/css" href="path/to/font-awesome/css/font-awesome.min.css">
<title>사업자 회원가입</title>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<script>
// 사업자 회원 정규식 검증
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

//이름 확인
function checkIrum() {
	var pattern = /^[가-힣]{2,5}$/;
	return check($("#m_irum").val(), pattern, $("#m_irum_msg"), "이름은 한글 2~5자입니다");
}

//아이디 확인
function checkUsername() {
	var pattern = /^[A-Za-z][A-Za-z0-9]{7,10}$/;
	return check($("#m_username").val(), pattern, $("#m_username_msg"), "아이디는 영숫자 8~10자입니다");
}

//비밀번호 확인
function checkPassword() {
	var pattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
	return check($("#m_password").val(), pattern, $("#m_password_msg"), "비밀번호는 특수문자 포함 영숫자 8~10자입니다");
}

//비밀번호 일치 확인
function checkPassword2() {
	$("#m_password2_msg").text("");
	var pwd1 = $("#m_password").val();
	var pwd2 = $("#m_password2").val();
	if(pwd1!==pwd2) {
		$("#m_password2_msg").text("비밀번호가 일치하지 않습니다").css({"color":"red", "font-size":"0.75em"});
		return false;
	}
	$("#m_password2_msg").text("비밀번호가 일치합니다").css({"color":"green", "font-size":"0.75em"});
	return true;
}

//이메일 확인
function checkEmail() {
	var pattern = /^[A-Za-z][A-Za-z0-9]+@[A-Za-z\.]+$/;
	return check($("#m_email").val(), pattern, $("#m_email_msg"), "잘못된 이메일 형식입니다");
}

//사업자 번호 확인
function checkMnumber() {
	var pattern = /^[0-9]{10}$/;
	return check($("#m_num").val(), pattern, $("#m_num_msg"), "사업자번호는 숫자 10자리입니다")
}

//전화번호 확인
function checkTel() {
	var pattern = /^([0-9]{3})([0-9]{4})([0-9]{4})$/;
	return check($("#m_tel").val(), pattern, $("#m_tel_msg"), "전화번호는 숫자 10~11자리입니다")
}

// ajax로 아이디 사용 여부 확인
function ajaxCheckId() {
	$.ajax({
		url: "/cheatingday/main/m_id_check",
		method: "get",
		data: "mUsername=" + $("#m_username").val()
	})
	.done(()=>{$("#m_username_msg").text("사용 가능한 아이디입니다").css({"color":"green", "font-size":"0.75em"})})
	.fail(()=>{$("#m_username_msg").text("사용중인 아이디입니다").css({"color":"red", "font-size":"0.75em"})});
}

//이메일 사용 여부 확인
function ajaxCheckEmail() {
	$.ajax({
		url: "/cheatingday/main/m_email_check",
		method: "get",
		data: "mEmail=" + $("#m_email").val()
	})
	.done(()=>{$("#m_email_msg").text("사용 가능한 이메일입니다").css({"color":"green", "font-size":"0.75em"})})
	.fail((xhr)=>{
		console.log(xhr);
		$("#m_email_msg").text("사용중인 이메일입니다").css({"color":"red", "font-size":"0.75em"})}
	);
}

//사업자 등록번호 사용 여부 확인
function ajaxCheckMnumber() {
	$.ajax({
		url: "/cheatingday/main/m_num_check",
		method: "get",
		data: "mNum=" + $("#m_num").val()
	})
	.done(()=>{$("#m_num_msg").text("사용 가능합니다").css({"color":"green", "font-size":"0.75em"})})
	.fail(()=>{$("#m_num_msg").text("이미 가입 된 사업자 등록번호입니다").css({"color":"red", "font-size":"0.75em"})});
}


	
$(function() {
	$("#m_username").on("blur", function() {
		var result = checkUsername();
		if(result==true)
			ajaxCheckId();
	});
	$("#m_email").on("blur", function() {
		var result = checkEmail();
		if(result==true)
			ajaxCheckEmail();
	});
	$("#m_num").on("blur", function() {
		var result = checkMnumber();
		if(result==true)
			ajaxCheckMnumber();
	});
	
	$("#m_irum").on("blur", checkIrum);
	$("#m_password").on("blur", checkPassword);
	$("#m_password2").on("blur", checkPassword2);
	$("#m_tel").on("blur", checkTel);
	
	
	$("#join").on("click", function() {
	/* 
		formData값 확인하는 코드
		var formData = new FormData(document.getElementById("join_form"))
		for(var key of formData.keys())
			console.log(key);
		for(var value of formData.values())
			console.log(value);
	*/
	
		var r1 = checkUsername();
		var r2 = checkEmail();
		var r3 = checkMnumber();
		var r4 = checkPassword();
		var r5 = checkPassword2();
		var r6 = checkTel();
		var r7 = checkIrum();
		var result = r1 && r2 && r3 && r4 && r5 && r6 && r7;
		if(result===true) {
			$.when(
				$.ajax("/cheatingday/main/m_id_check?mUsername=" + $("#m_username").val()),
				$.ajax("/cheatingday/main/m_email_check?mEmail=" + $("#m_email").val()),
				$.ajax("/cheatingday/main/m_num_check?mNum=" + $("#m_num").val())
			).done(()=>{$("#join_form").submit(); })
		}
	})	
})
</script>
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="join_form" action="/cheatingday/join_manager" method="post">
					<span class="login100-form-title p-b-32">사장님 회원가입 </span> 
					<span class="login100-form-title p-b-32"></span> 
					<div>
						<label class="txt p-b-11" for="m_irum">이름</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input200" type="text" id="m_irum" name="mIrum">
							<span class="focus-input200"></span>
							<span id="m_irum_msg"></span>
						</div>
					</div>
					<div>
						<label class="txt p-b-11" for="m_username">아이디</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input200" type="text" id="m_username" name="mUsername">
							<span class="focus-input200"></span>
							<span id="m_username_msg"></span>
						</div>
					</div>
					<div>
						<label class="txt p-b-11" for="m_password">비밀번호</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input200" type="password" id="m_password" name="mPassword">
							<span class="focus-input200"></span>
							<span id="m_password_msg"></span>
						</div>
					</div>
					<div>
						<label class="txt p-b-11" for="m_password2">비밀번호 확인</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input200" type="password" id="m_password2" >
							<span class="focus-input200"></span>
							<span id="m_password2_msg"></span>
						</div>
					</div>
					<div>
						<label class="txt p-b-11" for="m_email">이메일</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input200" type="text" id="m_email" name="mEmail">
							<span class="focus-input200"></span>
							<span id="m_email_msg"></span>
						</div>
					</div>
					<div>
						<label class="txt p-b-11" for="m_tel">전화번호</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input200" type="text" id="m_tel" name="mTel">
							<span class="focus-input200"></span>
							<span id="m_tel_msg"></span>
						</div>
					</div>
					<div>
						<label class="txt p-b-11" for="m_num">사업자 등록번호</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input200" type="text" id="m_num" name="mNum">
							<span class="focus-input200"></span>
							<span id="m_num_msg"></span>
						</div>
					</div>
					<div>
						<input type="hidden" name="authorities" value="ROLE_MANAGER">
					</div>
					<div class="container-login100-form-btn" style="text-align: center;">
						<button type="button" class="btn btn-danger" id="join">가입</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>