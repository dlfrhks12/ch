// 사업자 회원가입 정규식 검증


//이름 확인
function checkManagerIrum() {
	var pattern = /^[가-힣]{2,5}$/;
	return check($("#m_irum").val(), pattern, $("#m_irum_msg"), "이름은 한글 2~5자입니다");
}

//아이디 확인
function checkManagerUsername() {
	var pattern = /^[A-Za-z][A-Za-z0-9]{8,10}$/;
	return check($("#m_username").val(), pattern, $("#m_username_msg"), "아이디는 영숫자 8~10자입니다");
}

//비밀번호 확인
function checkManagerPassword() {
	var pattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
	return check($("#m_password").val(), pattern, $("#m_password_msg"), "비밀번호는 특수문자 포함 영숫자 8~10자입니다");
}

//비밀번호 일치 확인
function checkManagerPassword2() {
	$("m_#password2_msg").text("");
	var pwd1 = $("#m_password").val();
	var pwd2 = $("#m_password2").val();
	if(pwd1!==pwd2) {
		$("#m_password2_msg").text("비밀번호가 일치하지 않습니다").css({"color":"red", "font-size":"0.75em"});
		return false;
	}
	return true;
}

//이메일 확인
function checkManagerEmail() {
	var pattern = /^[A-Za-z][A-Za-z0-9]+@[A-Za-z\.]+$/;
	return check($("#m_email").val(), pattern, $("#m_email_msg"), "잘못된 이메일 형식입니다");
}

//사업자 번호 확인
function checkManagerMnumber() {
	var pattern = /^([0-9]{3})([0-9]{2})([0-9]{5})$/;
	return check($("#m_num").val(), pattern, $("#m_num_msg"), "사업자번호는 10자리 숫자입니다")
}

//전화번호 확인
function checkManagerTel() {
	var pattern = /^([0-9]{3})([0-9]{4})([0-9]{4})$/;
	return check($("#m_tel").val(), pattern, $("#m_tel_msg"), "전화번호는 10~11자리 숫자입니다")
}