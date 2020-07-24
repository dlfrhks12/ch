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
<title>아이디 찾기</title>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<style type="text/css">
button {
	width: 380px; 
	height: 60px;
	font-size: 1.3em;
	text-align: center;
	margin-top: -10px;
	}
	
.input100 {
	border: 0.5px solid #FF7373;
	color: #CC3D3D;
	}
.input100:hover {
	background-color: #CC3D3D;
	color: white;
}
	
	

</style>
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<div class="login100-form validate-form flex-sb flex-w">
					<span class="login100-form-title p-b-32" style="text-align: center;">아이디 찾기</span><br>
					<div class="wrap-input100 validate-input m-b-36">
						<input class="input100" type="button" value="일반회원" onclick="location.href='/cheatingday/u_find_id'"> 
					</div>
					<div class="wrap-input100 validate-input m-b-36">
						<input class="input100" type="button" value="사업자회원" onclick="location.href='/cheatingday/m_find_id'"> 
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>