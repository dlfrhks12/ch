<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
// 정규식 검증 후 오류가 있을 경우 오류메세지 출력

// 이름 확인
function checkIrum() {
	var pattern = /^[가-힣]{2,5}$/;
	return check($("#m_irum").val(), pattern, $("#m_irum_msg"), "이름은 한글 2~5자입니다");
}

// 아이디 확인
function checkUsername() {
	var pattern = ^[A-Za-z][A-Za-z0-9]{8,10}$;
	return check($("#m_username").val(), pattern, $("#m_username_msg"), "아이디는 영숫자 8~10자입니다");
}

// 비밀번호 확인
function checkPassword() {
	var pattern = ^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$;
	return check($("#m_password").val(), pattern, $("#m_password_msg"), "비밀번호는 특수문자 포함 영숫자 8~10자입니다");
}

// 비밀번호 일치 확인
function checkPassword2() {
	$("m_#password2_msg").text("");
	var pwd1 = $("#m_password").val();
	var pwd2 = $("#m_password2").val();
	if(pwd1!==pwd2) {
		$("#m_password2_msg").text("비밀번호가 일치하지 않습니다").css({"color":"red", "font-size":"0.75em"});
		return false;
	}
	return true;
}

// 이메일 확인
function checkEmail() {
	var pattern = /^[A-Za-z][A-Za-z0-9]+@[A-Za-z\.]+$/;
	return check($("#m_email").val(), pattern, $("#m_email_msg"), "잘못된 이메일 형식입니다");
}

// 사업자 번호 확인
function checkMnumber() {
	var pattern = /^([0-9]{3})([0-9]{2})([0-9]{5})$/"$1-$2-$3";
	return check($("#m_num").val(), pattern, $("#m_num_msg"), "사업자번호는 10자리 숫자입니다")
}

// 전화번호 확인
function checkTel() {
	var pattern = /^([0-9]{3})([0-9]{4})([0-9]{4})$/"$1-$2-$3";
	return check($("#m_tel").val(), pattern, $("#m_tel_msg"), "전화번호는 10~11자리 숫자입니다")
}

// 아이디 사용 여부 확인
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

// 이메일 사용 여부 확인
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

// 사업자 등록번호 사용 여부 확인
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
</script>
</head>
<body>
	<div id="wrap">
		<form action="join_form" action="/cheatingday/main/join_manager">
		<div class="form-group">
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
		</div>
		-->
		<div class="form-group" style="text-align:center;">
			<button type="button" id="join" class="btn btn-into">가입</button>
		</div>
		</form>
	</div>
</body>
</html>