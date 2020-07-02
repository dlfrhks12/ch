<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
</head>
<script>
function makePage() {
	
	var email = "${user.UEmail}";
	var tel = "${user.UTel}";
	
	var indexOfAt = email.indexOf("@");
	var email1 = email.substr(0, indexOfAt);
	var email2 = email.substr(indexOfAt+1);
	$("#email1").val(email1);
	$("#email2").val(email2).prop("disabled", true);
	
	var $options = $("#selectEmail option");
	
	$options.each(function(idx, option) {
		if($(option).text()==email2)
			$(option).prop("selected", true);
	});
	
	var length = tel.length;
	if(length==9) {
		$("#tel1").val(tel.substr(0,2));
		$("#tel2").val(tel.substr(2,3));
		$("#tel3").val(tel.substr(5,4));
	} else if(length==10) {
		$("#tel1").val(tel.substr(0,3));
		$("#tel2").val(tel.substr(3,3));
		$("#tel3").val(tel.substr(6,4));
	} else if(length==11) {
		$("#tel1").val(tel.substr(0,3));
		$("#tel2").val(tel.substr(3,4));
		$("#tel3").val(tel.substr(7,4));
	}
}
$(function() {
	makePage();
	$("#activateChangePwd").on("click", function() {
		$("#passwordArea").toggle();
	});
	$("#selectEmail").on("change", function() {
		var $choice = $("#selectEmail").val();
		if($choice=="직접입력") {
			$("#email2").val("").attr("placeholder", "직접 입력");
			$("#email2").prop("disabled", false);
		} else{
			$("#email2").val($choice);
			$("#email2").prop("disabled", true);
		}
	});
	
	$("#changeIrum").on("click", function() {
		var params = {
			_method: "put",
			UIrum: $("#irum").val(),
			UTel: "${user.UTel}",
			UEmail: "${user.UEmail}",
			_csrf: "${_csrf.token}"
		}
		console.log(params)
		$.ajax({
			url: "/cheatingday/user/update",
			method: "post",
			data: params,
		}).done(()=>{toastr.info("이름변경 성공","서버 메시지")})
		.fail(()=>{toastr.info("이름변경 실패", "서버 메시지")});
	});
	
	$("#changePwd").on("click", function() {
		var $password = $("#password").val();
		var $newPassword = $("#newPassword").val();
		var $newPassword2 = $("#newPassword2").val();
		if($newPassword!==$newPassword2)
			return false;
		var params = {
			_method: "put",
			_csrf: "${_csrf.token}",
			uPassword: $("#password").val(),
			newUPassword: $("#newPassword").val()
		};
		console.log(params)
		$.ajax({
			url: "/cheatingday/user/update",
			method: "post",
			data: params
		}).done(()=>{toastr.info("비밀번호 변경 성공","서버 메시지")})
		.fail(()=>{toastr.info("비밀번호 변경 실패", "서버 메시지")});
	});
	
	$("#update").on("click", function() { 
		var email = $("#email1").val() + "@" + $("#email2").val();
		
		var formData = new FormData();
		formData.append("irum", $("#irum").val());
		formData.append("email", email);
		formData.append("tel", $("#tel1").val()+$("tel2").val()+$("tel3").val());
		formData.append("_csrf", "${_csrf.token}");
		formData.append("_method", "put");
		if($("#password").val()!=="")
			formData.append("password", $("#password").val());
		if($("#newPassword").val()===$("#newPassword2"))
			formData.append("newPassword",$("#newPassword"));

		$.ajax({
			url: "/cheatingday/user/update",
			data: formData,
			method: "post",
			processData:false,
			contentType:false
		}).done(()=>{ toastr.info("변경 성공", "서버메시지"); })
		.fail(()=>{ toastr.info("변경 실패", "서버메시지"); })
	})
})
</script>
<body>
	<table class="table table-hover" id="user">
		<tr>
			<td class="first">이름</td>
			<td><input type="text" id="irum" value="${user.UIrum}">&nbsp;	<button type="button" class="btn btn-info" id="changeIrum">이름변경</button></td>
		</tr>
		<tr>
			<td class="first">아이디</td><td colspan="2"><span id="username">${user.UUsername }</span></td>
		</tr>
		<tr><td class="first">비밀번호</td>
			<td colspan="2">
				<button type="button" class="btn btn-info" id="activateChangePwd">비밀번호 수정</button>
				<div id="passwordArea">
					<span class="key">현재 비밀번호 : </span><input type="password" id="password" ><br>
					<span class="key">새 비밀번호 : </span><input type="password" id="newPassword"><br>
					<span class="key">새 비밀번호 확인 : </span><input type="password" id="newPassword2">
	  				<button type="button" class="btn btn-info" id="changePwd">변경</button>
				</div>
			</td></tr>
		<tr><td class="first">이메일</td>
			<td colspan="2">
				<input type="text" name="email1" id="email1">&nbsp;@&nbsp;<input type="text" name="email2" id="email2">&nbsp;&nbsp;
				<select id="selectEmail">
					<option selected="selected">직접 입력</option>
					<option>naver.com</option>
					<option>daum.net</option>
					<option>gmail.com</option>
				</select>
			</td></tr>
		<tr><td class="first">연락처</td>
			<td colspan="2">
				<input type="text" name="tel1" id="tel1" maxlength="3">&nbsp;
				<input type="text" name="tel2" id="tel2" maxlength="4">&nbsp;
				<input type="text" name="tel3" id="tel3" maxlength="4">
			</td></tr>
	</table>
	<button type="button" class="btn btn-success" id="update">변경하기</button>
</body>
</html>