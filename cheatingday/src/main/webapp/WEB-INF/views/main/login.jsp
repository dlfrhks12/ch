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
<script src="login/js/main.js"></script>
<script src="/aboard/script/valid.js"></script>
<script>
$(function() {
	var msg = "${msg}";
	if(msg!="") {
		$("#alert").text(msg);
		$("#msg").show();
	}
	
	$("#username").on("blur", checkUserUsername);
	$("#password").on("blur", checkUserPassword);
	$("#username").on("blur", checkManagerUsername);
	$("#password").on("blur", checkManagerPassword);

	$("#username").on("blur", checkUsername);
	$("#password").on("blur", checkPassword);
	
	$("#login").on("click", function() {
		if(checkUserUsername()==false || checkManagerUsername()==false)
			return;
		if(checkUserPassword()==false || checkManagerPassword()==false)
			return;
		$("#login_frm").submit();
	});
	
	$("#password").on("keypress", function(key) {
		if(key.keyCode!=13)
			return;
		if(checkUserUsername()==false || checkManagerUsername()==false) 
			return;
		if(checkUserPassword()==false || checkManagerPassword()==false)
			return;
		$("#login_form").submit();
		if(checkPassword()==false)
			return;
		$("#login_frm").submit();
	});
});
</script>
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="login_form" action="/cheatingday/main/login" method="post">
					<span class="login100-form-title p-b-32">로그인</span>

					<span class="txt1 p-b-11" id="username">아이디</span>
					<div class="wrap-input100 validate-input m-b-36" id="username" data-validate = "Username is required">
						<input class="input100" type="text" name="username" >
						<span class="focus-input100"></span>
						<span class="helper_text" id="username_msg"></span>
					</div>
					
					<span class="txt1 p-b-11" id="password">비밀번호</span>
					<div class="wrap-input100 validate-input m-b-12" id="password" data-validate = "Password is required">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" name="pass" >
						<span class="focus-input100"></span>
						<span class="helper_text" id="password_msg"></span>
					</div>
					
					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
							<label class="label-checkbox100" for="ckb1">아이디 저장</label>
						</div>

						<div>
							<a href="/main/find_id" class="txt3">아이디찾기</a>
							<a> / </a>
							<a href="/main/find_pwd" class="txt3">비밀번호찾기</a>
						</div>
					</div>
			</div>
		</div>
	</div>
</body>
</html>