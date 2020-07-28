<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="login/images/icons/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="/cheatingday/login/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/css/util.css">
<link rel="stylesheet" type="text/css" href="/cheatingday/login/css/main.css">
<link rel="stylesheet" type="text/css" href="path/to/font-awesome/css/font-awesome.min.css">
<title>비밀번호 재확인</title>
<script src="/cheatingday/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="/cheatingday/login/vendor/animsition/js/animsition.min.js"></script>
<script src="/cheatingday/login/vendor/bootstrap/js/popper.js"></script>
<script src="/cheatingday/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/cheatingday/login/vendor/select2/select2.min.js"></script>
<script src="/cheatingday/login/vendor/daterangepicker/moment.min.js"></script>
<script src="/cheatingday/login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="/cheatingday/login/vendor/countdowntime/countdowntime.js"></script>
<script>
$(function(){
	$("#check_pwd").on("click", function(){
			$("#check_pwd_frm").submit();
	});
	
   
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
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="check_pwd_frm" action="/cheatingday/user/check_pwd" method="post">
					<span class="login100-form-title p-b-32" style="text-align: center;">비밀번호 확인</span>
					<span class="txt1 p-b-11" style="margin-left: 45px;">고객님의 소중한 개인정보를 위해서 본인확인을 진행합니다</span>
					<div class="wrap-input100 validate-input m-b-36" style="width: 80%;">
						<input class="input100" type="password" id="uPassword" name="uPassword" style="400px;"> 
						<span class="focus-input100"></span>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<button type="button" class="btn btn-danger" id="check_pwd" style="float: right; width: 60px; height: 56px;">확인</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

