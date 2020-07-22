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
<title>비밀번호 재확인</title>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<style>
#check_pwd {
	position: absolute;
	left: 400px;
	top: 395px;
}

#wrap_id { 
	margin-right: auto; 
	margin-left: auto; 
	width: 600px;
	height: 300px;
}

.solid {
	border:2px solid black;;
}
</style>
<script>
$(function(){
	$("#check_pwd").on("click", function(){
			$("#check_pwd_frm").submit();
	})
	
	var msg = "${msg}";
	if(msg!="") {
		Swal.fire({
			  title: '다시 한번 비밀번호를 생각해주세요!',
			  width: 500,
			  padding: '4em',
			  background: '#fff url(https://sweetalert2.github.io/images/trees.png)',
			  backdrop: `
			    rgba(0,0,123,0.4)
			    url("https://sweetalert2.github.io/images/nyan-cat.gif")
			    left top
			    no-repeat
			  `
			})
	}
});
</script>
</head>
<body>
<div class="container card" id="wrap_id">
	<div style="text-align: center; padding: 30px 0;">
		<h3>비밀번호 재확인</h3>
	</div>
	<div style="text-align: center;">
	<form id="check_pwd_frm" action="/cheatingday/user/check_pwd" method="post">
		비밀번호: <input type="password" id="uPassword" name="uPassword"><br>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}"><br>
		<button type="button" class="btn btn-danger" id="check_pwd">로그인</button>
	</form>
	</div>
</div>
	
</body>
</html>


