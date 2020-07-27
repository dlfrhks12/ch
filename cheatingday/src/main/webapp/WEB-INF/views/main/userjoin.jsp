<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
<title>일반회원 회원가입</title>
<script src="login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="login/vendor/animsition/js/animsition.min.js"></script>
<script src="login/vendor/bootstrap/js/popper.js"></script>
<script src="login/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="login/vendor/select2/select2.min.js"></script>
<script src="login/vendor/daterangepicker/moment.min.js"></script>
<script src="login/vendor/daterangepicker/daterangepicker.js"></script>
<script src="login/vendor/countdowntime/countdowntime.js"></script>
<script type="text/JavaScript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
   function openDaumZipAddress() {
      new daum.Postcode({
         oncomplete:function(data) {
            $("#zonecode").val(data.zonecode);
            $("#address").val(data.address);
            $("#address_etc").focus();
         }
      }).open();
   }
      
   
// 일반회원 정규식 검증
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


//아이디 확인
function checkUsername() {
   var pattern = /^[A-Za-z][A-Za-z0-9]{7,10}$/;
   return check($("#u_username").val(), pattern, $("#u_username_msg"), "아이디는 영숫자 8~10자입니다");
}

//비밀번호 확인
function checkPassword() {
   var pattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
   return check($("#u_password").val(), pattern, $("#u_password_msg"), "비밀번호는 특수문자 포함 영숫자 8~10자입니다");
}

//비밀번호 일치 확인
function checkPassword2() {
   $("#u_password2_msg").text("");
   var pwd1 = $("#u_password").val();
   var pwd2 = $("#u_password2").val();
   if(pwd1!==pwd2) {
      $("#u_password2_msg").text("비밀번호가 일치하지 않습니다").css({"color":"red", "font-size":"0.75em"});
      return false;
   }
   $("#u_password2_msg").text("비밀번호가 일치합니다").css({"color":"green", "font-size":"0.75em"});
   return true;
}

//이메일 확인
function checkEmail() {
   var pattern = /^[A-Za-z][A-Za-z0-9]+@[A-Za-z\.]+$/;
   return check($("#u_email").val(), pattern, $("#u_email_msg"), "잘못된 이메일 형식입니다");
}


//전화번호 확인
function checkTel() {
   var pattern = /^([0-9]{3})([0-9]{4})([0-9]{4})$/;
   return check($("#u_tel").val(), pattern, $("#u_tel_msg"), "전화번호는 10~11자리 숫자입니다")
}



// ajax로 아이디 사용 여부 확인
function ajaxCheckId() {
   $.ajax({
      url: "/cheatingday/main/u_id_check",
      method: "get",
      data: "uUsername=" + $("#u_username").val()
   })
   .done(()=>{$("#u_username_msg").text("사용 가능한 아이디입니다").css({"color":"green", "font-size":"0.75em"})})
   .fail(()=>{$("#u_username_msg").text("사용중인 아이디입니다").css({"color":"red","font-size":"0.75em"})});
}

//이메일 사용 여부 확인
function ajaxCheckEmail() {
   $.ajax({
      url: "/cheatingday/main/u_email_check",
      method: "get",
      data: "uEmail=" + $("#u_email").val()
   })
   .done(()=>{$("#u_email_msg").text("사용가능합니다").css({"color":"green", "font-size":"0.75em"})})
   .fail(()=>{$("#u_email_msg").text("사용중인 이메일입니다").css({"color":"red","font-size":"0.75em"})});
}


$(function() {
   $("#u_username").on("blur", function() {
      var result = checkUsername();
      if(result==true)
         ajaxCheckId();
   });
   $("#u_email").on("blur", function() {
      var result = checkEmail();
      if(result==true)
         ajaxCheckEmail();
   });
   $("#u_irum").on("blur", checkIrum);
   $("#u_password").on("blur", checkPassword);
   $("#u_password2").on("blur", checkPassword2);
   $("#u_tel").on("blur", checkTel);

   $("#join").on("click", function() {
   
      var r1 = checkUsername();
      var r2 = checkIrum();
      var r3 = checkPassword();
      var r4 = checkPassword2();
      var r5 = checkEmail();
      var r6 = checkTel();
      var result = r1 && r2 && r3 && r4 && r5 && r6;
      if(result===true) {
         $.when(
            $.ajax("/cheatingday/main/u_id_check?uUsername=" + $("#u_username").val()),
            $.ajax("/cheatingday/main/u_email_check?uEmail=" + $("#u_email").val())
         ).done(()=>{$("#join_form").submit();})
      }
   })
})

</script>
</head>
<body>
   <div class="limiter">
      <div class="container-login100">
         <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
            <form class="login100-form validate-form flex-sb flex-w" id="join_form" action="/cheatingday/join_user" method="post">
               <span class="login100-form-title p-b-32">일반회원가입</span> 
               <span class="login100-form-title p-b-32"></span>
               <div>
                  <label class="txt p-b-11" for="u_irum">이름</label>
                  <div class="wrap-input100 validate-input m-b-36">
                     <input class="input200" type="text" id="u_irum" name="uIrum">
                     <span class="focus-input200"></span> 
                     <span id="u_irum_msg"></span>
                  </div>
               </div>
               <div>
                  <label class="txt p-b-11" for="u_username">아이디</label>
                  <div class="wrap-input100 validate-input m-b-36">
                     <input class="input200" type="text" id="u_username" name="uUsername"> 
                     <span class="focus-input200"></span> 
                     <span id="u_username_msg"></span>
                  </div>
               </div>
               <div>
                  <label class="txt p-b-11" for="u_password">비밀번호</label>
                  <div class="wrap-input100 validate-input m-b-36">
                     <input class="input200" type="password" id="u_password" name="uPassword"> 
                     <span class="focus-input200"></span> 
                     <span id="u_password_msg"></span>
                  </div>
               </div>
               <div>
                  <label class="txt p-b-11" for="u_password2">비밀번호 확인</label>
                  <div class="wrap-input100 validate-input m-b-36">
                     <input class="input200" type="password" id="u_password2">
                     <span class="focus-input200"></span> 
                     <span id="u_password2_msg"></span>
                  </div>
               </div>
               <div>
                  <label class="txt p-b-11" for="u_email">이메일</label>
                  <div class="wrap-input100 validate-input m-b-36">
                     <input class="input200" type="text" id="u_email" name="uEmail">
                     <span class="focus-input200"></span> 
                     <span id="u_email_msg"></span>
                  </div>
               </div>
               <div>
                  <label class="txt p-b-11" for="u_tel">전화번호</label>
                  <div class="wrap-input100 validate-input m-b-36">
                     <input class="input200" type="text" id="u_tel" name="uTel">
                     <span class="focus-input200"></span> 
                     <span id="u_tel_msg"></span>
                  </div>
               </div>
               <div>
	               <label class="txt p-b-11" for="u_address">주소</label>
	               <div style="height: 70px;">
			           <button type="button" class="btn btn-outline-danger" onClick="openDaumZipAddress();" id="sea" style="float: right; margin-right: 240px;" >검색</button>
			           <div class="wrap-input300 validate-input m-b-50">
		                 <input class="input300" id="zonecode" type="text" name="uAddress" placeholder="우편번호" />
		              </div>
	               </div>
	                  <div class="wrap-input100 validate-input m-b-36">
	                     <input class="input200" type="text" id="address" name="uAddress" placeholder="주소를 검색해주세요" readonly/>
	                  </div>
	               <div class="wrap-input100 validate-input m-b-36">
	                     <input class="input200" type="text" id="address_etc" name="uAddress" placeholder="상세주소 입력"/>
	                     <span class="focus-input200"></span>
	               </div>
               </div>
                   <div>
                  <input type="hidden" name="authorities" value="ROLE_USER">
               </div>
               <div class="container-login100-form-btn" style="text-align: center;">
                  <button type="button" class="btn btn-danger" id="join">가입</button>
                   </div>
            </form>   
         </div>
      </div>
   </div>
</body>
</html>