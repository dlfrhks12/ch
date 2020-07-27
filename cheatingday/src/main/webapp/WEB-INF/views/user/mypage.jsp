<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/JavaScript"
   src="http://code.jquery.com/jquery-1.7.min.js"></script>
<script type="text/JavaScript"
   src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet"
   href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
<title>내정보</title>
<link rel="stylesheet"
   href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
<script type="text/JavaScript"
   src="http://code.jquery.com/jquery-1.7.min.js"></script>
<script type="text/JavaScript"
   src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet"
   href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
#user td {
   height: 60px;
   line-height: 60px;
}

#user td input {
   height: 25px;
}

#tel1, #tel2, #tel3 {
   width: 125px;
}

.btn_two {
   text-align: right;
}
.btn {
   margin-right:10px;
}
.first {
   text-align: center;
}

.key {
   width: 10%;
   display: inline-block;
}
</style>
<script type="text/javascript">
function openDaumZipAddress() {
   new daum.Postcode({
      oncomplete:function(data) {
         jQuery("#zonecode").val(data.zonecode);
         jQuery("#address").val(data.address);
         jQuery("#address_etc").focus();
         console.log(data);
      }
   }).open();
} 

function makePage() {
   $("#passwordArea").hide();
   var email = "${user.UEmail}";
   var tel = "${user.UTel}";
   var address = "${user.UAddress}";
   
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

   var array = address.split(",");
   $("#zonecode").val(array[0]);
   $("#address").val(array[1]);
   $("#address_etc").val(array[2]);
   for(var i=0;i<array.length;i++) {
      console.log(array[i])
   }
}
$(function() {
   makePage();
   $("#activateChangePwd").on("click", function() {
      $("#passwordArea").toggle();
   });
   $("#selectEmail").on("change", function() {
      var $choice = $("#selectEmail").val();
      if($choice=="직접 입력") {
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
         type: "post",
         data: params,
      }).done(()=>{toastr.success("이름변경 성공","서버 메시지")})
      .fail(()=>{toastr.error("이름변경 실패", "서버 메시지")});
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
         UPassword: $("#password").val(),
         newUPassword: $("#newPassword").val(),
         UTel: "${user.UTel}",
         UEmail: "${user.UEmail}"
      };
      console.log(params);      
      console.log($newPassword);
      console.log($newPassword2);
      $.ajax({
         url: "/cheatingday/user/update",
         type: "post",
         data: params
      }).done(()=>{toastr.success("비밀번호 변경 성공","서버 메시지")})
      .fail(()=>{toastr.error("비밀번호 변경 실패", "서버 메시지")});
   });
   
   $("#update").on("click", function() { 
      var email = $("#email1").val() + "@" + $("#email2").val();
      var formData = new FormData();
      formData.append("UIrum", $("#irum").val());
      formData.append("UEmail", email);
      formData.append("UTel", $("#tel1").val()+$("#tel2").val()+$("#tel3").val());
      formData.append("UAddress", $("#zonecode").val());
      formData.append("UAddress", $("#address").val());
      formData.append("UAddress", $("#address_etc").val());
      formData.append("_csrf", "${_csrf.token}");
      formData.append("_method", "put");
      if($("#password").val()!=="")
         formData.append("password", $("#password").val());
      if($("#newPassword").val()===$("#newPassword2"))
         formData.append("newPassword",$("#newPassword"));
      for(var key of formData.keys())
         console.log(key);
      for(var value of formData.values())
         console.log(value);
      $.ajax({
         url: "/cheatingday/user/update",
         data: formData,
         type: "post",
         processData:false,
         contentType:false
      }).done(()=>{ toastr.success("변경 성공", "서버메시지"); })
      .fail(()=>{ toastr.error("변경 실패", "서버메시지"); })
   })
   
   $("#resign").on("click", function() {
      var params= {
         UUsername:"${user.UUsername}",
         _csrf:"${_csrf.token}",
         _method:"delete"
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
     }).then((result) => {
      if (result.value) {
      $.ajax({
         url:"/cheatingday/user/resign",
           type:"post",
           success: function() {
     swalWithBootstrapButtons.fire({
           title:"이용해 주셔서 감사합니다",
           text:"치팅데이 사랑해주셔서 감사합니다",
           icon:"success"
     }).then(()=>location.href="/cheatingday/")
     },error: function() {
           swalWithBootstrapButtons.fire("회원 탈퇴에 실패했습니다", "치팅데이 사랑해주셔서 감사합니다", "error");
     }
   })
        } else if (
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
})

</script>
<body>
<div class="container">
   <div style="padding: 40px 0;">
      <h3><i class="fas fa-user">&nbsp;내 정보</i></h3>
   </div>
   <table class="table table-hover" id="user">
      <tr>
         <td class="first table-danger">이름</td>
         <td><input type="text" id="irum" value="${user.UIrum}">&nbsp;
            <button type="button" class="btn btn-danger" id="changeIrum">이름변경</button>
         </td>
      </tr>
      <tr>
         <td class="first table-danger">아이디</td>
         <td colspan="2"><span id="username">${user.UUsername }</span></td>
      </tr>
      <tr>
         <td class="first table-danger">비밀번호</td>
         <td colspan="2">
            <button type="button" class="btn btn-danger" id="activateChangePwd">비밀번호 수정</button>
            <div id="passwordArea">
               <span class="key" style="width:120px;">현재 비밀번호:</span> 
               <input type="password" id="password"><br>
               <span class="key" style="width:120px;">새 비밀번호:</span> 
               <input type="password" id="newPassword"><br> 
               <span class="key" style="width:120px;">새 비밀번호 확인:</span>
               <input type="password" id="newPassword2" style="margin-right:20px;" >
               <button type="button" class="btn btn-danger" id="changePwd">변경</button>
            </div>
         </td>
      </tr>
      <tr>
         <td class="first table-danger">이메일</td>
         <td colspan="2"><input type="text" name="email1" id="email1">&nbsp;@&nbsp;
            <input type="text" name="email2" id="email2">&nbsp;&nbsp; <select
            id="selectEmail">
               <option selected="selected">직접 입력</option>
               <option>naver.com</option>
               <option>daum.net</option>
               <option>gmail.com</option>
         </select></td>
      </tr>
      <tr>
         <td class="first table-danger">연락처</td>
         <td colspan="2"><input type="text" name="tel1" id="tel1"
            maxlength="3">&nbsp; <input type="text" name="tel2"
            id="tel2" maxlength="4">&nbsp; <input type="text"
            name="tel3" id="tel3" maxlength="4"></td>
      </tr>
      <tr>
         <td class="first table-danger" id="addr">주소</td>
         <td colspan="2"><input id="zonecode" type="text"
            style="width: 50px;" name="uAddress" readonly />&nbsp;
            <button class="btn btn-danger" onClick="openDaumZipAddress();">주소찾기</button>
            <br> <input type="text" id="address" style="width: 240px;"
            name="uAddress" readonly /> <input type="text" id="address_etc"
            style="width: 200px;" name="uAddress" placeholder="상세주소입력 " /></td>
      </tr>
      
   </table>
   <div style="float: right;">
            <button type="button" class="btn btn-danger" id="update">변경하기</button>
            <button type="button" class="btn btn-danger" id="resign">회원탈퇴</button>
            </div>
   </div>
   
</body>
</html>