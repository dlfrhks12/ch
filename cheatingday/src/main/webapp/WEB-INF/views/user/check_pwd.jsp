<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
=======
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
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
<title>비밀번호 재확인</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
<<<<<<< HEAD
#wrap_id { 
	margin-right: auto; 
	margin-left: auto; 
	width: 600px;
	height: 300px;
}

.solid {
	border:2px solid black;;
}
=======
#check_pwd {
	position: absolute;
	left: 400px;
	top: 395px;
}

>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
</style>
<script>
$(function(){
	$("#check_pwd").on("click", function(){
			$("#check_pwd_frm").submit();
	})
	
	var msg = "${msg}";
	if(msg!="") {
		Swal.fire({
			  title: '또 틀리면 냥이가 문다?!',
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
					<span class="login100-form-title p-b-32">비밀번호 확인</span>
					<span class="txt1 p-b-11">비밀번호</span>
					<div class="wrap-input100 validate-input m-b-36">
						<input class="input100" type="password" id="uPassword" name="uPassword"> 
						<span class="focus-input100"></span>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br>
					<button type="button" class="btn btn-danger" id="check_pwd">확인</button>
				</form>
			</div>
		</div>
	</div>
<<<<<<< HEAD
	<div style="text-align: center;">
	<form id="check_pwd_frm" action="/cheatingday/user/check_pwd" method="post">
		비밀번호: <input type="password" id="uPassword" name="uPassword"><br>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br>
		<button type="button" class="btn btn-danger" id="check_pwd">로그인</button>
	</form>
	</div>
</div>
	
=======
<!-- <div class="container card" id="wrap_id"> -->
<!-- 	<div style="text-align: center; padding: 30px 0;"> -->
<!-- 		<h3>비밀번호 재확인</h3> -->
<!-- 	</div> -->
<!-- 	<div style="text-align: center;"> -->
<!-- 	<form id="check_pwd_frm" action="/cheatingday/user/check_pwd" method="post"> -->
<!-- 		비밀번호: <input type="password" id="uPassword" name="uPassword"><br> -->
<!-- 	</form> -->
<!-- 	</div> -->
<!-- </div> -->
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
</body>
</html>


