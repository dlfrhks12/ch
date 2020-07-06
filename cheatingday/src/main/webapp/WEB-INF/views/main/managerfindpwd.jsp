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
<title>사업자회원 비밀번호 찾기</title>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<script>
$(function() {
	   var msg = "${msg}";
	   if(msg!="") {
	      $("#alert").text(msg);
	      $("#msg").show();
	   }
	   $("#find_pwd").on("click", function() {
	      $("#find_pwd_form").submit();
	   });
	});
</script>
</head>
<body>
 <div class="limiter">
      <div class="container-login100">
         <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
            <div class="alert alert-success alert-dismissible" id="msg" style="display:none;">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>
            <form class="login100-form validate-form flex-sb flex-w" id="find_pwd_form" action="/cheatingday/m_find_pwd" method="post">
               <span class="login100-form-title p-b-32">사업자회원 비밀번호 찾기</span><br>
               
               <div>
					<label class="txt p-b-11" for="m_username">아이디</label>
					<div class="wrap-input100 validate-input m-b-36">
						<input class="input200" type="text" id="m_username" name="mUsername">
						<span class="focus-input200"></span>
					</div>
			   </div>
               <div>
					<label class="txt p-b-11" for="m_email">이메일</label>
					<div class="wrap-input100 validate-input m-b-36">
						<input class="input200" type="text" id="m_email" name="mEmail">
						<span class="focus-input200"></span>
					</div>
			   </div>
               <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
               <button type="button" class="btn btn-danger" id="find_pwd">비밀번호 찾기</button>
            </form>
    		</div>
		</div>
	</div>
</body>
</html>