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
<title>일반유저 아이디 찾기</title>
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
//일반회원 정규식 검증
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
	return check($("#u_irum").val(), pattern, $("#u_irum_msg"), "이름은 한글 2~5자입니다");
}

//이메일 확인
function checkEmail() {
	var pattern = /^[A-Za-z][A-Za-z0-9]+@[A-Za-z\.]+$/;
	return check($("#u_email").val(), pattern, $("#u_email_msg"), "잘못된 이메일 형식입니다");
}


$(function() {
	$("#u_irum").on("blur", checkIrum)
	$("#u_email").on("blur", checkEmail);
	
	$("#find_id").on("click", function() {
		if(checkIrum()==false)
			return;
		if(checkEmail()==false)
			return;
		$("#find_id_form").submit();
	})
})

</script>
</head>
<body>
   <div class="limiter">
      <div class="container-login100">
         <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
            <div class="alert alert-success alert-dismissible" id="msg" style="display:none;">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>
            <form class="login100-form validate-form flex-sb flex-w" id="find_id_form" action="/cheatingday/u_find_id" method="post">
               <span class="login100-form-title p-b-32">아이디 찾기</span>
               
               <div class="wrap-input100 validate-input m-b-36">
               	  <span class="txt1 p-b-11">아이디</span>
                  <input class="input100" type="text" id="u_username" name="uUsername" >
                  <span class="focus-input100" id="u_irum_msg"></span>
               </div>
               <div>
                   <span class="txt1 p-b-11">이메일</span>
                   <input class="input100" type="text" id="u_email" name="uEmail" >
                   <span class="focus-input100" id="u_email_msg"></span>
               </div>
               <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
               <button type="button" class="btn btn-danger" id="find_id">아이디 찾기</button>
            </form>
    		</div>
		</div>
	</div>
</body>
</html>