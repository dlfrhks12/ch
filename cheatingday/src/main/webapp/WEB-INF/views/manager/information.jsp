<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 정보관리</title>
<style>
	section {margin-left: 350px; margin-right: 350px; margin-top: 50px; margin-bottom: 80px; font-size: 17px;}
	#update {margin-left: 800px; margin-top: 30px;}
	#delete {position: absolute; left: 1250px; margin-top: 30px;}

</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
<script>
//사업자 정보 변경
function managerPage(){
	
	//비밀번호변경 토글 이용하기 위해 숨겨놓음.
	$("#passwordArea").hide();
	
	//이메일 설정
	var email = "${managerInfo.MEmail}";
	
	var indexOfAt = email.indexOf("@");
	var email1 = email.substr(0, indexOfAt);
	var email2 = email.substr(indexOfAt+1);
	$("#email1").val(email1);
	$("#email2").val(email2).prop("disabled", true);
	
	var $options = $("#selectEmail option");
	
	$options.each(function(idx, option){
		if($(option).text()==email2)
			$(option).prop("selected", true);
	});
	
	//전화번호 설정
	var tel = "${managerInfo.MTel}";
	
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

$(function(){
	managerPage();
	
	//비밀번호 토글 설정
	$("#activateChangePwd").on("click", function(){
		$("#passwordArea").toggle();
	});
	
	
	//이메일 바꾸는거 설정
	$("#selectEmail").on("change",function(){
		var $choice = $("#selectEmail").val();      ///////////// 여기는 왜 $choice를 주었지?
		if($choice=="직접입력"){
			$("#email2").val("").attr("placeholder","직접입력");
			$("#email2").prop("disabled",false);
		} else {
			$("#email2").val($choice);
			$("#email2").prop("disabled", true);
		} 
	});
	
	
	//이름만 변경
	$("#changeIrum").on("click",function(){
		var params = {
				_method: "patch",
				MUsername: $("#MUsername").val(),
				MIrum: $("#irum").val(),
				MTel: "${managerInfo.MTel}",
				MEmail: "${managerInfo.MEmail}",
				_csrf: "${_csrf.token}" 
		}
		$.ajax({
			url: "/cheatingday/manager/information_update",
			method: "post",
			data: params,
		}).done(()=>{toastr.success("사업자이름 변경 성공","서버 메시지")})
		.fail(()=>{toastr.error("사업자이름 변경 실패","서버 메시지")});
	});
	
	//비밀번호만 변경
	$("#changePwd").on("click",function(){
		var $password = $("#password").val();
		var $newPassword = $("#newPassword").val();
		var $newPassword2 = $("#newPassword2").val();
		if($newPassword!==$newPassword2)
			return false;
		var params ={
			_method: "patch",
			_csrf: "${_csrf.token}",
			MPassword: $("#password").val(),
			newMPassword: $("#newPassword").val(),
			MTel: "${managerInfo.MTel}",
			MEmail: "${managerInfo.MEmail}"
		};
		$.ajax({
			url: "/cheatingday/manager/information_update",
			method: "post",	
			data: params
		}).done(()=>{toastr.success("비밀번호 변경 성공","서버 메시지")})
		.fail(()=>{toastr.error("비밀번호 변경 실패", "서버 메시지")});
	});
	
	
	//사업자 정보 전체 변경
	$("#update").on("click",function(){
		var params = {
				_csrf: "${_csrf.token}",
				_method: "patch",
				MIrum: $("#irum").val(),
				MEmail: $("#email1").val() + "@" + $("#email2").val(),
				MTel: $("#tel1").val()+$("#tel2").val()+$("#tel3").val(),
				MUsername: $("#MUsername").val()
		};
		
		$.ajax({
			url: "/cheatingday/manager/information_update",
			method: "post",
			data: params
		}).done(()=>{toastr.success("변경 성공","서버 메시지")})
		.fail(()=>{toastr.error("변경 실패", "서버 메시지")});
	});
	
	 //사업자 탈퇴 - m_num,m_password,m_username,m_email,m_tel,m_irum,s_name
	$("#delete").on("click",function(){
		var params={
				_csrf: "${_csrf.token}",
				_method: "delete",
				MUsername : "${managerInfo.MUsername}"
				}
		
		const swalWithBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-info',
				cancelButton: 'btn btn-danger'
			},
			buttonsStyling: false
		})
		 swalWithBootstrapButtons.fire({
  		title: '정말 탈퇴하시겠습니까?',
  		text: "한 번 탈퇴하시면 계정복구는 불가능합니다",
		icon: 'warning',
 		showCancelButton: true,
  		confirmButtonText: '회원 탈퇴',
  		cancelButtonText: '탈퇴 취소',
  		reverseButtons: true
	  }).then((result)=> {
		  if(result.value){
			  $.ajax({
				  url: "/cheatingday/manager/out",
				  type: "post",
				  success: function(){
					  swalWithBootstrapButtons.fire({
						  title:"이용해 주셔서 감사합니다",
						  text:"치팅데이 사랑해주셔서 감사합니다",
						  icon:"success"
					  }).then(()=>location.href="/cheatingday/")
				  },error: function(){
					  swalWithBootstrapButtons.fire("회원 탈퇴에 실패했습니다", "치팅데이 사랑해주셔서 감사합니다", "error"); 
				  }
			  })
		  			}else if(
		  					result.dismiss === Swal.DismissReason.cancel
		  	  		) {
		  	   		swalWithBootstrapButtons.fire(
		  	        	'취소',
		  	        	'당신의 계정은 안전합니다 :)',
		  	        	'error'
		  	    	)
		  			}
	  })
	  });
		
		
	
});

</script>
</head>
<body>
	<div style="padding: 20px 0;">
      <h3><i class="fas fa-user">&nbsp; 나의 정보 보기</i></h3>
      <br>
	
<table class="table table-hover" id="user">
		<colgroup>
			<col width="10%">
			<col width="40%">
		</colgroup>
		<tr>
			<td class="first">이름</td>
			<td><input type="text" id="irum" value="${managerInfo.MIrum}">&nbsp;	
			<button type="button"  class="btn btn-danger" id="changeIrum">이름변경</button></td>
		</tr>
		<tr>
			<td class="first">아이디</td><td colspan="2"><span id="username">${managerInfo.MUsername}</span></td>
		</tr>
		<tr><td class="first">비밀번호</td>
			<td colspan="2">
				<button type="button"  class="btn btn-danger" id="activateChangePwd">비밀번호 수정</button>
				<div id="passwordArea">
					<span class="key">현재 비밀번호 : </span><input type="password" id="password" ><br>
					<span class="key">새 비밀번호 : </span><input type="password" id="newPassword"><br>
					<span class="key">새 비밀번호 확인 : </span><input type="password" id="newPassword2">
	  				<button type="button"  class="btn btn-danger" id="changePwd">변경</button>
				</div>
			</td></tr>
		<tr><td class="first">이메일</td>
			<td colspan="2">
				<input type="text" name="email1" id="email1">&nbsp;@&nbsp;
				<input type="text" name="email2" id="email2">&nbsp;&nbsp;
				<select id="selectEmail">
					<option selected="selected">직접입력</option>
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
	<div id="btn_update">
	<button type="button"  class="btn btn-danger" id="update" style="font-size: 17px;" >변경하기</button>
	<button type="button"  class="btn btn-danger" id="delete" style="font-size: 17px;" >탈퇴하기</button>
	</div> 
	</div>
</body>
</html>