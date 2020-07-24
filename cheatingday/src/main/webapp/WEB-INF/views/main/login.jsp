<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="login/images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="login/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="login/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="login/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="login/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css"
	href="login/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="login/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="login/css/util.css">
<link rel="stylesheet" type="text/css" href="login/css/main.css">
<link rel="stylesheet" type="text/css"
	href="path/to/font-awesome/css/font-awesome.min.css">
<title>로그인</title>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<style>
#login {
	position: absolute;
	left: 400px;
	top: 395px;
}

#join {
	position: absolute;
	top: 400px;
}

#account {
	font-size: 13px;
	color: #999999;
}

#joinbutton {
	font-size: 15px;
	color: red;
}

#txt {
	position: absolute;
	top: 335px;
	left: 330px;
}
</style>
<script>
	// 메세지 출력
	$(function() {
		// 로그인 버튼 누르면 로그인정보 submit & 로그인처리
		$("#login").on("click", function() {
			$("#login_form").submit();
		})
		
		var msg = "${msg}";
		if (msg != "") {
			swal.fire({
				icon : 'error',
				title : '비밀번호가 틀렸습니다',
				text : '다시 한번 확인해 주세요!',
				footer : '<a href="/cheatingday/find_pwd">비밀번호를 잊어버리셨나요?</a>'
			})
		}
		
	    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
	    var key = getCookie("key");
	    $("#username").val(key); 
	     
	    if($("#username").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	        $("#ckb1").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#ckb1").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#ckb1").is(":checked")){ // ID 저장하기 체크했을 때,
	            setCookie("key", $("#username").val(), 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("key");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("#userId").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#ckb1").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            setCookie("key", $("#username").val(), 7); // 7일 동안 쿠키 보관
	        }
	    });
	});
	
		 
	 
	function setCookie(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	}
	 
	function deleteCookie(cookieName){
	    var expireDate = new Date();
	    expireDate.setDate(expireDate.getDate() - 1);
	    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	 
	function getCookie(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	}
	
</script>
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<div class="alert alert-success alert-dismissible" id="msg"
					style="display: none;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
				<form class="login100-form validate-form flex-sb flex-w"
					id="login_form" action="/cheatingday/login" method="post">
					<span class="login100-form-title p-b-32">로그인</span> 
					<span class="txt1 p-b-11">아이디</span>
					<div class="wrap-input100 validate-input m-b-36"
						data-validate="Username is required">
						<input class="input100" type="text" id="username"
							name="a_username"> <span class="focus-input100"></span>
						<span class="helper_text" id="username_msg"></span>
					</div>
					<span class="txt1 p-b-11">비밀번호</span>
					<div class="wrap-input100 validate-input m-b-12" id="password"
						data-validate="Password is required">
						<span class="btn-show-pass"></span> <input class="input100"
							type="password" name="a_password"> <span
							class="focus-input100"></span> <span class="helper_text"
							id="password_msg"></span>
					</div>
					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember_me"> 
							<label class="label-checkbox100" for="ckb1">아이디 저장</label>
						</div>
					</div>
					<div class="container-login100-form-btn">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}">
						<button type="button" class="btn btn-danger" id="login">로그인</button>
					</div>
				</form>
				<div>
					<div id="join">
						<a id="account">계정이 없으신가요?&nbsp;&nbsp;</a> <a
							href="/cheatingday/join" class="txt3" id="joinbutton"> 회원가입하기</a>
					</div>
					<div id="txt">
						<a href="/cheatingday/find_id" class="txt3">아이디찾기</a> <a> / </a> <a
							href="/cheatingday/find_pwd" class="txt3">비밀번호찾기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>