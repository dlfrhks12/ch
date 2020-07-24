<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<title>일반회원 비밀번호 변경</title>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<script>


/////////////////////////////////   공용       //////////////////////////////////////////////////


// 정규식 검증
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




/////////////////////////////////   일반회원 정규식 검증       //////////////////////////////////////////////////



//비밀번호 확인
function checkUserPassword() {
	var pattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
   	return check($("#uNewPassword").val(), pattern, $("#u_password_msg"), "비밀번호는 특수문자 포함 영숫자 8~10자입니다");
   }

//비밀번호 일치 확인
   function checkUserPassword2() {
   	$("#u_password2_msg").text("");
   	var pwd1 = $("#uNewPassword").val();
   	var pwd2 = $("#uNewPassword2").val();
   	if(pwd1!==pwd2) {
   		$("#u_password2_msg").text("비밀번호가 일치하지 않습니다").css({"color":"red", "font-size":"0.75em"});
   		return false;
   	}
   	$("#u_password2_msg").text("비밀번호가 일치합니다").css({"color":"green", "font-size":"0.75em"});
   	return true;
   }
   

 //비밀번호 확인
   function checkManagerPassword() {
   	var pattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
      	return check($("#mNewPassword").val(), pattern, $("#m_password_msg"), "비밀번호는 특수문자 포함 영숫자 8~10자입니다");
      }

   //비밀번호 일치 확인
      function checkManagerPassword2() {
      	$("#m_password2_msg").text("");
      	var pwd1 = $("#mNewPassword").val();
      	var pwd2 = $("#mNewPassword2").val();
      	if(pwd1!==pwd2) {
      		$("#m_password2_msg").text("비밀번호가 일치하지 않습니다").css({"color":"red", "font-size":"0.75em"});
      		return false;
      	} else {
   	   	$("#m_password2_msg").text("비밀번호가 일치합니다").css({"color":"green", "font-size":"0.75em"});
      		return true;
      	}
   }
      
      

$(function() {   
	var msg = "${msg}";
	if(msg!="") {
		alert(msg);
	}
	$("#uNewPassword").on("blur", checkUserPassword);
	$("#uNewPassword2").on("blur", checkUserPassword2);
	$("#mNewPassword").on("blur", checkManagerPassword);
	$("#mNewPassword2").on("blur", checkManagerPassword2);
	
	$("#uChangePwd").on("click", function() {
		var r1 = checkUserPassword();
		var r2 = checkUserPassword2();
		var result = r1 && r2
		if(result===true && $("#uNewPassword").val()!=="" && $("#uNewPassword2").val()!=="")  {
			$.ajax({
		        url: "/cheatingday/u_change_pwd",
		        type: "post",
		        data: $('#u_change_pwd_form').serialize(),
		     }).done(()=>{
<<<<<<< HEAD
		    	 alert("비밀번호가 변경되었습니다");})}
=======
		    	 alert("비밀번호가 변경되었습니다");
		    	 location.href = "/cheatingday"})}
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
		
<<<<<<< HEAD
		
		
=======
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
	})
		$("#mChangePwd").on("click", function() {
		var r1 = checkManagerPassword();
		var r2 = checkManagerPassword2();
		var result = r1 && r2
		if(result===true && $("#uNewPassword").val()!=="" && $("#uNewPassword2").val()!=="")  {
			$.ajax({
		        url: "/cheatingday/m_change_pwd",
		        type: "post",
		        data: $('#m_change_pwd_form').serialize(),
		     }).done(()=>{
		    	 alert("비밀번호가 변경되었습니다");})}
	})
})



/////////////////////////////////   사업자회원 정규식 검증       //////////////////////////////////////////////////

</script>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="u_change_pwd_form" method="post">
					<div>
						<label class="txt p-b-11" for="uPassword">현재 비밀번호</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input100" type="password" id="uPassword" name="uPassword">
							<span class="focus-input2100"></span> 
						</div>
					</div>
					<div>
						<label class="txt p-b-11" for="uNewPassword">새 비밀번호</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input100" type="password" id="uNewPassword" name="uNewPassword"> 
							<span class="focus-input100"></span> 
							<span id="u_password_msg"></span>
						</div>
					</div>		
					<div>
						<label class="txt p-b-11" for="uNewPassword2">새 비밀번호 확인</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input100" type="password" id="uNewPassword2">
							<span class="focus-input100"></span> 
							<span id="u_password2_msg"></span>
						</div>
					</div>
					<div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<button id="uChangePwd">비밀번호 변경</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_MANAGER')">
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="m_change_pwd_form" method="post">
					<div>
						<label class="txt p-b-11" for="mPassword">현재 비밀번호</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input100" type="password" id="mPassword" name="mPassword">
							<span class="focus-input2100"></span> 
						</div>
					</div>
					<div>
						<label class="txt p-b-11" for="mNewPassword">새 비밀번호</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input100" type="password" id="mNewPassword" name="mNewPassword" name="mNewPassword"> 
							<span class="focus-input100"></span> 
							<span id="m_password_msg"></span>
						</div>
					</div>		
					<div>
						<label class="txt p-b-11" for="mNewPassword2">새 비밀번호 확인</label>
						<div class="wrap-input100 validate-input m-b-36">
							<input class="input100" type="password" id="mNewPassword2">
							<span class="focus-input100"></span> 
							<span id="m_password2_msg"></span>
						</div>
					</div>
					<div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<button type="button" id="mChangePwd">비밀번호 변경</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</sec:authorize>
</body>
</html>