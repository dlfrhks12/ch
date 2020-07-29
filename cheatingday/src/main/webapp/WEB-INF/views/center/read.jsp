<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
   uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="/cheatingday/ckeditor/ckeditor.js"></script>
<!-- 로그인여부 확인 및 아이디꺼내오기 -->
<sec:authorize access="isAuthenticated()">
   <script>
      var isLogin = true;
      var loginId = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}"
   </script>
</sec:authorize>
<sec:authorize access="isAnonymous()">
   <script>
      var isLogin = false;
      var loginId = undefined;
   </script>
</sec:authorize>
<style>
   #lower_left li{
      display: inline-block;
      padding : 0 5px;
   }
   #lower_left li:nth-of-type(2n){  /* #lower_left 하위의 li 중 짝수번째인 것에 적용 */
      border-left: 1px solid gray;
      border-right: 1px solid gray;
   }
   #lower_right li {
      display: inline-block;
      padding : 0 5px;
      font-size: 0.9em;
   }
   #lower_left {
      float: left;
      margin-left: -40px;
   }
   #lower_right {
      float: right;
   }
   #lower {
      overflow: hidden;
 	}
</style>
<script>
//공백상태
var qna = undefined;
//읽어올 요소출력
function printQna() {
   $("#qTitle").val(qna.qtitle);
   $("#mUsername").text(qna.musername);
   $("#mIrum").text(qna.mirum);
   $("#qNo").text(qna.qno);
   $("#qCano").text(qna.qcano);
   $("#qCategory").text(qna.qcategory);
   $("#qWriteTime").text(qna.qwriteTimeStr);
   if(isLogin===true && qna.musername===loginId)
      var ck = CKEDITOR.replace("qContent", {
         height:'500px',
         // c:url은 웹 애플리케이션 컨텍스트 패스를 붙여주는 역할
         filebrowserUploadUrl: 'http://localhost:8081/cheatingday/center/ckupload'
      });
   $("#qContent").html(qna.qcontent).css("height","600px").css("overflow","scroll");
   // 초기화 - 버튼영역 감추기
   $("#btn_area").hide();
   console.log(qna);
   console.log(qna.musername);
   console.log(loginId);
   console.log(isLogin);
   if(isLogin==true && qna.musername==loginId){
      $("#qTitle").prop("disabled", false);
      $("#btn_area").show();
   }
   else if(isLogin==true && qna.mUsername!==loginId && qna.comments==""){
      $("#comment_textarea").prop("disabled", false);
      $("#comment_write").prop("disabled", false);
   }
   else if(qna.comments!="")
	  $("#comment_area").hide();
}
//댓글출력
function printComment(qnacomment) {
   var $comments = $("#comments");
   $comments.empty();
   $.each(qnacomment, function(i, comment) {
      console.log(comment);
      var $comment = $("<div>").appendTo($comments);
      var $upper_div = $("<div>").appendTo($comment);
      var $center_div = $("<div>").appendTo($comment);
      var $lower_div = $("<div>").appendTo($comment);
      $("<span></span><br>").text("관리자").appendTo($upper_div);
      if(comment.ausername===loginId)
         $("<textarea>").attr("id","qccontent").attr("style","min-width:800px; min-height:200px;").val(comment.qcContent).appendTo($center_div); 
      else
         $("<textarea>").attr("id","qccontent").attr("disabled","disabled").attr("style","min-width:1000px; min-height:200px;").val(comment.qcContent).appendTo($center_div); 
      $("<span>").text(comment.qcWriteTime).appendTo($upper_div);
      if(comment.ausername===loginId) {
         var btn = $("<button>").attr("class","delete_comment btn btn-danger").attr("data-qcno",comment.qcNo).attr("data-ausername", comment.ausername)
            .text("삭제").appendTo($center_div).css("float","right");
         var btn2 = $("<button>").attr("class","update_comment btn btn-danger").attr("data-qcno",comment.qcNo).attr("data-ausername", comment.ausername)
         .text("수정").appendTo($center_div).css("float","right");
      }
      $("<hr>").appendTo($comment);
   });

}
$(function() { 
   //위 요소 출력
   var qNo = location.search.substr(5);
   console.log(qNo);
   $.ajax({
      url:"/cheatingday/center/read",
      data: "qNo=" + qNo,
      method:"post"
   }).done((result)=>{ 
      qna = result;
      console.log(qna);
      printQna();
      printComment(qna.comments);
      console.log(qna.comments.length);
   });
   // 1. 댓글 달기
   $("#comment_write").on("click", function() {
      if(isLogin===false)
         return;
      if($("#comment_textarea").val()=="")
         return false;
      var params = {
         // writer, content, csrf
         qNo : qna.qno, 
         qcContent : $("#comment_textarea").val(),
         _csrf: "${_csrf.token}"
      }
      console.log(params);
      $.ajax({
         url: "/cheatingday/center/comment_write",
         method: "post",
         data: params
      }) 
      .done((result)=>{ printComment(result); $("#comment_textarea").val(""); $("#comment_area").hide(); })
      .fail((result)=>{console.log(result)});
   })
   
   // 댓글 삭제
   $("#comments").on("click", ".delete_comment", function() {
      // data-ano 속성의 값을 꺼낼 때 
      // data("ano") -> 넣은 값의 타입 그대로
      // attr("data-ano") -> 문자열
      var params = {
         qcNo: $(this).data("qcno"),
         qNo: qna.qno,
         aUsername: $(this).data("ausername"),
         _method: "delete",
         _csrf: "${_csrf.token}"
      }
      console.log(params);
      $.ajax({
         url: "/cheatingday/center/comment_delete",
         method: "post",
         data: params
      })
      .done((result)=>{ printComment(result); $("#comment_area").show();  $("#comment_textarea").prop("disabled", false); $("#comment_write").prop("disabled", false); })
      .fail(()=>{console.log(params)});
   });
   //댓글 업데이트
      $("#comments").on("click", ".update_comment", function() {
      var params = {
         qNo: qna.qno,
         qcNo: $(this).data("qcno"),
         aUsername: $(this).data("ausername"),
         qcContent: $("#qccontent").val(),
         _csrf: "${_csrf.token}",
         _method: "patch"
      }
      $.ajax({
         url: "/cheatingday/center/comment_update",
         method: "post",
         data: params
      }) 
      .done((result)=>{alert("변경되었습니다"); })
      .fail((result)=>{console.log(params);});
   });
   //글 업데이트
   $("#update").on("click", function() {
      var params = {
         qNo: qna.qno,
         qTitle: $("#qTitle").val(),
         qCano: qna.qcano,
         qContent: CKEDITOR.instances['qContent'].getData(),
         _csrf: "${_csrf.token}",
         _method: "patch"
      }
      $.ajax({
         url: "/cheatingday/center/update",
         method: "post",
         data: params
      })
      .done((result)=>{ location.reload(); })
      .fail((result)=>{console.log(result)});
   });
   
   // 글 삭제
   $("#delete").on("click", function() {
      var params = {
         qNo: qna.qno,
         _csrf: "${_csrf.token}",
         _method: "delete"
      }
      $.ajax({
         url: "/cheatingday/center/delete",
         method: "post",
         data: params
      })
      .done((result)=>{ location.href="/cheatingday/center/list" })
      .fail((result)=>{console.log(result)});
   });   
});
</script>
</head>
<body>
   <hr> 
   <div id="wrap" style="width: 1000px; min-height: 800px; margin-left: 400px; margin-right: 400px;" >
      <div>
         <div id="title_div">
            <div id="upper">
               <input type="text" id="qTitle" disabled="disabled"
                  style="min-width: 600px;"> <input type="hidden"
                  id="mUsername">
            </div>
            <div id="lower">
               <input type="hidden" id="qCano">
               <ul id="lower_left">
                  <li><span id="mIrum"></span></li>
                  <li><span id="qWriteTime"></span></li>
               </ul>
               <span id="qCategory"></span>
               
            </div>
         </div>
         <div id="content_div">
            <div class="form-group">
               <div class="form-control" id="qContent"></div>
            </div>
            <div id="btn_area">
               <button id="update" class="btn btn-danger">변경</button>
               <button id="delete" class="btn btn-danger">삭제</button>
            </div>
         </div>
         <sec:authorize access="hasRole('ROLE_ADMIN')">
         <div id="comment_area">
            <div class="form-group">
               <label for="comment_textarea">댓글을 입력하세요</label>
               <textarea class="form-control" rows="5" id="comment_textarea"
                  placeholder="관리자만 댓글을 달 수 있습니다" disabled="disabled"></textarea>
            </div>
            <button type="button" class="btn btn-danger" id="comment_write"
               disabled="disabled">댓글 작성</button>
         </div>
         </sec:authorize>
         <hr>
         <div id="comments"></div>
      </div>
      <button class="btn btn-danger" onclick="location.href='/cheatingday/center/list';">목록이동</button>
   </div>
</body>
</html>