<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재확인</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
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
<div class="container card" id="wrap_id">
	<div style="text-align: center; padding: 30px 0;">
		<h3>비밀번호 재확인</h3>
	</div>
	<div style="text-align: center;">
	<form id="check_pwd_frm" action="/cheatingday/user/check_pwd" method="post">
		비밀번호: <input type="password" id="uPassword" name="uPassword"><br>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br>
		<button type="button" class="btn btn-danger" id="check_pwd">로그인</button>
	</form>
	</div>
</div>
	
</body>
</html>


