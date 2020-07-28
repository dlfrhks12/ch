<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="login/images/icons/favicon.ico" />
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
<title>system</title>
<script src="/cheatingday/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="/cheatingday/login/vendor/animsition/js/animsition.min.js"></script>
<script src="/cheatingday/login/vendor/bootstrap/js/popper.js"></script>
<script src="/cheatingday/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/cheatingday/login/vendor/select2/select2.min.js"></script>
<script src="/cheatingday/login/vendor/daterangepicker/moment.min.js"></script>
<script src="/cheatingday/login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="/cheatingday/login/vendor/countdowntime/countdowntime.js"></script>
<style>
</style>
<script>
$(function() {
	var msg = "${msg}";
	if(msg!="") {
		$("#alert").text(msg);
		$("#msg").show();
	}
});
</script>
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<div class="txt1 p-b-11" id="msg" style="display: none; font-size:17px;; margin: 0 auto;">
					<span id="alert"></span>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>