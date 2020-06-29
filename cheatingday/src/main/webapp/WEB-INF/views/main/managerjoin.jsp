<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/cheatingday/script/vaild.js"></script>
<script>

/*
// ajax로 아이디 사용 여부 확인
function ajaxCheckId() {
	$.ajax({
		url: "/cheatingday/main/id_check",
		method: "get",
		data: "m_username=" + $("#m_username").val(),
		success: function() {
			$("#m_username_msg").text("사용 가능한 아이디입니다").css({"color":"green", "font-size":"0.75em"});
		}, error: function() {
			$("#m_username_msg").text("사용중인 아이디입니다").css({"color":"red", "font-size":"0.75em"});
		}
	})
}

//이메일 사용 여부 확인
function ajaxCheckEmail() {
	$.ajax({
		url: "/cheatingday/main/email_check",
		method: "get",
		data: "m_email=" + $("#m_email").val(),
		success: function() {
			$("#m_email_msg").text("사용 가능한 이메일입니다").css({"color":"green", "font-size":"0.75em"});
		}, error: function() {
			$("#m_email_msg").text("사용중인 이메일입니다").css({"color":"red", "font-size":"0.75em"});
		}
	})
}

//사업자 등록번호 사용 여부 확인
function ajaxCheckMnumber() {
	$.ajax({
		url: "/cheatingday/main/managernum_check",
		method: "get",
		data: "m_num=" + $("#m_num").val(),
		success: funtion() {
			$("#m_num_msg").text("사용 가능합니다").css({"color":"green", "font-size":"0.75em"});
		}, error: function() {
			$("#m_num_msg").text("사용중인 사업자 등록번호입니다").css({"color":"red", "font-size":"0.75em"});
		}
	})
}



$function() {
	$("#m_username").on("blur", function() {
		var result = checkUsername();
		if(result==true)
			ajaxCheckId();
	})
	$("#m_email").on("blur", function() {
		var result = checkEmail();
		if(result==true)
			ajaxCheckEmail();
	})
	$("#m_num").on("blur", function() {
		var result = checkMnumber();
		if(result==true)
			ajaxCheckMnumber();
	})
	
	$("#m_irum").on("blur", checkIrum);
	$("#m_password").on("blur", checkPassword);
	$("#m_password2").on("blur", checkPassword2);
	$("#m_tel").on("blur", checkTel);
	
	$("#join").on("click", function() {
		var formDate = new FormData(document.getElementById("join_form"));
		for(var key of formData.keys())
			console.log(key);
		for(var value of formData.values())
			console.log(value);
		
		var r1 = checkUsername();
		var r2 = checkEmail();
		var r3 = checkMnumber();
		var r4 = checkPassword();
		var r5 = checkPassword2();
		var r6 = checkTel();
		var r7 = checkIrum();
		var result = r1 && r2 && r3 && r4 && r5 && r6 && r7;
		if(result===true) {
			$.when($.ajax("/cheatingday/main/id_check?m_username=" + $("#m_username").val())),
			$.ajax("/cheatingday/main/email_check?m_email=" + $("#m_email").val())
		}.done(()=>{$("#join_form").submit(); })
	})
}
*/
</script>
</head>
<body>
	<div id="wrap">
		<form id="join_form" action="/cheatingday/main/join_manager" method="post">
			<div>
				<label for="m_irum">이름</label>
				<span id="m_irum_msg"></span>
			</div>
			<div>
				<label for="m_username">아이디</label>
				<span id="m_username_msg"></span>
			</div>
			<div>
				<label for="m_password">비밀번호</label>
				<span id="m_password_msg"></span>
			</div>
			<div class="form-group">
				<input id="m_password" type="password" class="form-control" name="m_password">
			</div>
			<div>
				<label for="m_password2">비밀번호 확인</label>
				<span id="m_password2_msg"></span>
			</div>
			<div>
				<label for="m_email">이메일</label>
				<span id="m_email_msg"></span>
			</div>
			<div class="form-group">
				<input id="m_email" type="text" class="form-control" name="m_email">
			</div>
			<div>
				<label for="m_num">사업자번호</label>
				<span id="m_num_msg"></span>
			</div>
			<div>
				<label for="m_tel">전화번호</label>
				<span id="m_tel_msg"></span>
			</div>
			<div class="form-group">
				<input id="m_tel" type="text" class="form-control" name="m_tel">
			</div>
			<!-- 권한
		<div class="form-group">
			<select name="authorities">
				<option value="ROLE_USER">사용자</option>
				<option value="ROLE_MANAGER">매니저</option>
				<option value="ROLE_ADMIN">관리자</option>
			</select>
		</div> -->
			<div class="form-group" style="text-align: center;">
				<button type="button" id="join" class="btn btn-danger">가입</button>
			</div>
		</form>
	</div>
</body>
</html>