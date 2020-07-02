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
<link rel="stylesheet" type="text/css" href="path/to/font-awesome/css/font-awesome.min.css">
<title>Insert title here</title>
<script src="/cheatingday/script/valid.js"></script>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<script>


// ajax로 아이디 사용 여부 확인
function ajaxCheckId() {
	$.ajax({
		url: "/cheatingday/main/id_check",
		method: "get",
		data: "m_username=" + $("#m_username").val()
	})
	.done(()=>{$("#m_username_msg").text("사용 가능한 아이디입니다").css({"color":"green", "font-size":"0.75em"})})
	.fail(()=>{$("#m_username_msg").text("사용중인 아이디입니다").css({"color":"red", "font-size":"0.75em"})});
}

//이메일 사용 여부 확인
function ajaxCheckEmail() {
	$.ajax({
		url: "/cheatingday/main/email_check",
		method: "get",
		data: "m_email=" + $("#m_email").val()
	})
	.done(()=>{$("#m_email_msg").text("사용 가능한 아이디입니다").css({"color":"green", "font-size":"0.75em"})})
	.fail(()=>{$("#m_email_msg").text("사용중인 아이디입니다").css({"color":"red", "font-size":"0.75em"})});
}

//사업자 등록번호 사용 여부 확인
function ajaxCheckMnumber() {
	$.ajax({
		url: "/cheatingday/main/managernum_check",
		method: "get",
		data: "m_num=" + $("#m_num").val()
	})
	.done(()=>{$("#m_num_msg").text("사용 가능한 아이디입니다").css({"color":"green", "font-size":"0.75em"})})
	.fail(()=>{$("#m_num_msg").text("사용중인 아이디입니다").css({"color":"red", "font-size":"0.75em"})});
}



$(function() {
	$("#m_username").on("blur", function() {
		var result = checkManagerUsername();
		if(result==true)
			ajaxCheckId();
	});
	$("#m_email").on("blur", function() {
		var result = checkManagerEmail();
		if(result==true)
			ajaxCheckEmail();
	});
	$("#m_num").on("blur", function() {
		var result = checkManagernumber();
		if(result==true)
			ajaxCheckMnumber();
	});
	
	$("#m_irum").on("blur", checkManagerIrum);
	$("#m_password").on("blur", checkManagerPassword);
	$("#m_password2").on("blur", checkManagerPassword2);
	$("#m_tel").on("blur", checkManagerTel);
	
	$("#join").on("click", function() {
		var formDate = new FormData(document.getElementById("join_form"));
		for(var key of formData.keys())
			console.log(key);
		for(var value of formData.values())
			console.log(value);
		
		var r1 = checkManagerUsername();
		var r2 = checkManagerEmail();
		var r3 = checkManagernumber();
		var r4 = checkManagerPassword();
		var r5 = checkManagerPassword2();
		var r6 = checkManagerTel();
		var r7 = checkManagerIrum();
		var result = r1 && r2 && r3 && r4 && r5 && r6 && r7;
		if(result===true) {
			$.when($.ajax("/cheatingday/main/id_check?m_username=" + $("#m_username").val()),
				$.ajax("/cheatingday/main/email_check?m_email=" + $("#m_email").val())
			}.done(()=>{$("#join_form").submit(); })
		}
	})	
});
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
						<span class="txt p-b-11" id="m_irum">이름</span>
						<div class="wrap-input100 validate-input m-b-36" id="m_irum">
							<input class="input200" type="text" id="m_irum" >
							<span class="focus-input200"></span>
							<span id="m_irum_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="m_username">아이디</span>
						<div class="wrap-input100 validate-input m-b-36" id="m_username">
							<input class="input200" type="text" id="m_username" >
							<span class="focus-input200"></span>
							<span id="m_username_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="m_password">비밀번호</span>
						<div class="wrap-input100 validate-input m-b-36" id="m_password">
							<input class="input200" type="text" id="m_password" >
							<span class="focus-input200"></span>
							<span id="m_password_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="m_password2">비밀번호 확인</span>
						<div class="wrap-input100 validate-input m-b-36" id="m_password2">
							<input class="input200" type="text" id="m_password2" >
							<span class="focus-input200"></span>
							<span id="m_password2_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="m_email">이메일</span>
						<div class="wrap-input100 validate-input m-b-36" id="m_email">
							<input class="input200" type="text" id="m_email" >
							<span class="focus-input200"></span>
							<span id="m_email_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="m_num">사업자 등록번호</span>
						<div class="wrap-input100 validate-input m-b-36" id="m_num">
							<input class="input200" type="text" id=""m_num" >
							<span class="focus-input200"></span>
							<span id=""m_num_msg"></span>
						</div>
					</div>
					<div>
						<span class="txt p-b-11" id="m_tel">전화번호</span>
						<div class="wrap-input100 validate-input m-b-36" id="m_tel">
							<input class="input200" type="text" id="m_tel" >
							<span class="focus-input200"></span>
							<span id="m_tel_msg"></span>
						</div>
					</div>
					<div class="container-login100-form-btn" style="text-align: center;">
						<button type="button" class="btn btn-danger" id="join">가입</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
</body>
</html>