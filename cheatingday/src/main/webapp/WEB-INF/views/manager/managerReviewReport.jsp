<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
   $(function() {
      $("#report").on("click",function() {
         var radioVal = $('input[name="optradio"]:checked').val();
         params={
            _csrf:"${_csrf.token}",
            rbNo:${rbNo},
            reason:radioVal
         }
         $.ajax({
            url:"/dal/member/reqboard/report",
            method:"post",
            data:params,
            success: function() {
               console.log("===");
               Swal.fire({
                  icon:"success",
                  title:"의견 감사합니다!",
                  text:"신고가 접수되었습니다"
               }).then(()=>window.close())
            },error: function() {
               Swal.fire("실패!", "중복신고는 불가능합니다", "info");
            }
         })
      });
   });
</script>
<style>
   .btn {
      font-weight: bold;
   }
   h4 {
      font-weight: bold;
   }
</style>
</head>
<body>
<div id="report_wrap" style="margin: 15px;">
   <div id="report_title">
      <h2 style="font-weight: bold; margin-bottom: 20px;">게시물 신고하기</h2>   
   </div>
   <div id="report_body">
      <h4>신고 사유</h4>
      <div class="radio">
           <label><input value="회원을 향한 상습 비방" type="radio" name="optradio" checked>회원을 향한 상습 비방</label>
      </div>
      <div class="radio">
           <label><input value="음란하거나 성적인 게시물" type="radio" name="optradio">음란하거나 성적인 게시물</label>
      </div>
      <div class="radio">
           <label><input value="정치인 관련 게시물" type="radio" name="optradio">정치인 관련 게시물</label>
      </div>
      <div class="radio">
           <label><input value="홍보성/불법광고 게시물" type="radio" name="optradio">홍보성/불법광고 게시물</label>
      </div>
      <div class="radio">
           <label><input value="기타" type="radio" name="optradio">기타</label>
      </div>
   </div>
   <div id="report_content_wrap" style="margin-top: 20px;">
      <h4>상세 내용</h4>
      <div class="form-group">
           <textarea class="form-control" rows="5" id="comment" placeholder="내용을 입력해주세요 (선택)"></textarea>
      </div>
   </div>
   <button onclick="window.close()" class="btn btn-secondary" style="background-color: #8C8C8C; color: white;">취소</button>
   <button id="report" class="btn btn-warning">신고</button>
</div>

</body>
</html>