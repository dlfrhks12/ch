<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>	    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/cheatingday/ckeditor/ckeditor.js"></script>
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
<script>
	var board = undefined;
	//보드 출력
	function allBoard(){
		$("#title").val(board.title);
		$("#writer").val(board.username);
		$("#bno").val(board.bno);
		$("#write_time").val(board.writeTimeStr);
		$("#good_cnt").val(board.goodCnt);
		$("#bad_cnt").val(board.badCnt);
		//로그인 했고 아이디가 같을때
		if(isLogin===true && board.username===loginId)
			ck = CKEDITOR.replace("content", {
				//컨텍스트 패스 붙여주기
				filebrowserUploadUrl: 'http://localhost:8081/cheatingday/board/ckupload'
			});
		$("#content").val(board.content).css("height","600px").css("overflow","scroll");
		//버튼 영역 감추기
		$("#btn_area").hide();
		//로그인 했고, 아이디 같을때
		if(isLogin===true && board.username===loginId) {
			$("#title").prop("disabled", false);
			$("#btn_area").show();
			$("#comment_textarea").prop("disabled", false);
			$("#comment_write").prop("disabled", false);
		//로그인 했고 아이디 다를때
		} else if(isLogin===true && board.writer!==loginId) {
			$("#good_btn").prop("disabled", false);
			$("#bad_btn").prop("disabled", false);
			$("#comment_textarea").prop("disabled", false);
			$("#comment_write").prop("disabled", false);
		}
	}
	
	//첨부파일
	function printAttachment(attachments){
		var $ul = $("#attachment");
		$ul.empty();
		$.each(attachments, function(i,attachment){
			var $li = $("<li>").appendTo($ul);
			if(attachment.isImage==true)
				$("<i class= 'fa fa-file-image-o'></i>").appendTo($li);
			else
				$("<i class='fa fa-paperclip'></i>").appendTo($li);
			//첨부파일에 대한 링크에 아이콘 뒤에 추가할거야
			$("<a href='/cheatingday/attachment/view?fno=" + attachment.fno +"'>" + attachment.originalFileName + "</a>").appendTo($li);
			//첨부파일 삭제 할건데 로그인 되있어야 하고 글쓴 사람이 똑같아야해
			if(isLogin===true&&board.username){
				var str = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				str+="<span class='delete_attachment' data-fno='";
				str+=attachment.fno;
				str+="' data-bno='";
				str+=attachment.bno;
				str+="' >X</span>";
				console.log(str);
				$li.append(str);
				$(".delete_attachment").css("cursor","pointer").attr("title", "삭제하기");
			}
		})
	}
	//댓글 
	function princComment(comments){
		var $comments = $("#comments");
		$comments.empty();
		$.each(comments,function (i,comment){
			var $comment = $("<div>").appendTo($comments);
			var $upper_div = $("<div>").appendTo($comment);
			var $center_div = $("<div>").appendTo($comment);
			var $lower_div = $("<div>").appendTO($comment);
			$("<span></span>").text(comment.writer).appendTo($upper_div);
			$("<span>").text(comment.writeTime).appendTo($lower_div);
			$("<div>").html(comment.content).appendTo($center_div);
			
			if(comment.writer===loginId){
				var btn = $("<button>").attr("class","delete_comment").attr("data-writer",comment.writer)
					.text("삭제").appendTo($center_div).css("float","right");
			}
			$("<hr>").appendTo($comment);
		})
	}
	$(function(){
		//댓글 작성
		$("#comment_write").on("click",function(){
			if(isLogin===false)
				return;
			var params = {
					//writer,content,csrf
				bno:board.bno,
				content:$("#comment_textarea").val(),
				_csrf: "${_csrf.token}"
			}
			$.ajax({
				url:"/cheatingday/comment/write",
				method:"post",
				data: params
			}).done((result)=>{printComment(result)}).fail((result)=>console.log(result));
		})
		//댓글 삭제
		$("#comments").on("click","delet_comment",function(){
			var params={
				bno:board.bno,
				writer: $(this).data("writer"),
				_method:"delete",
				_csrf:"${_csrf.token}"
			}
			$.ajax({
				url:"/cheatingday/comment/delete",
				method:"post",
				data: params
			}).done((result)=>{printComment(result)}).fail((result)=>{console.log(result)});
		})
		//보드 업데이트
		$("#update").on("click",function(){
			var params={
				bno:board.bno,
				title:$("#title").val(),
				content: CKEDITOR.instances['content'].getData(),
				_csrf: "${_csrf.token}",
				_method:"patch"
			}
			$.ajax({
				url: "/cheatingday/board/update",
				method:"post",
				date: params
			}).done((result)=>{location.reload()}).fail((result)=>{console.log(result)})
		})
		//보드 삭제
		$("#delete").on("click",function(){
			var params = {
				bno: board.bno,
				_csrf: "${_csrf.token}",
				_method:"delete"
			}
			$.ajax({
				url:"/cheatingday/board/delete",
				method:"post",
				data:params
			}).done((result)=>{location.href = "/cheatingday/board"}).fail((result)=>{console.log(result)})
		})
		//글 추천
		$("#lower_right").on("click","#good_btn",function(){
			if(isLogin===false)
				return;
			if(isLogin===true && board.username===loginId)
				return;
			var param = {
				_method: "patch",
				_csrf: "${_csrf.token}",
				bno: board.bno,
				isGood: 'true'
			};
			$.ajax({
				url: "/cheatingday/board/good_or_bad",
				method: "post",
				data: param,
				success:function(result) {
					console.log("result:" + result);
					$("#good_cnt").text(result);
				}, error:function() {
					console.log("==========================");
				}
		})
		//글 비추
		$("#lower_right").on("click","#bad_btn",function(){
			if(isLogin===false)
				return;
			if(isLogin===true&&board.username===loginId)
				return;
			var param = {
				_method:"patch",
				_csrf:"${_csrf.token}",
				bno:board.bno,
				isGood:'false'
			}
			$.ajax({
				url:"/cheatingday/board/good_or_bad",
				method:"post",
				data:param
			}).done((result)=>{$("#bad_cnt").text(result)}).fail((xhr)=>{console.log(xhr)});
		})
		
	})
</script>
</head>
<body>
	<div>
		<div id = "title_div">
			<div id = "upper">
				<input type="text" id="title" disabled="disabled">
				<span id = "writer"></span>
			</div>
			<div id = "lower">
				<ul id="lower_left">
					글번호<span id="bno"></span>
					<span id="write_time"></span>
				</ul>
				<ul id="lower_right">
					
						<button type="button" class="btn btn-primary" id="good_btn" disabled="disabled">
							추천 <span class="badge" id="good_cnt"></span>
						</button>
					
						<button type="button" class="btn btn-success" disabled="disabled">
							조회 <span class="badge" id="read_cnt"></span>
						</button>   
  					<button type="button" class="btn btn-danger" id="bad_btn" disabled="disabled">
  						비추 <span class="badge" id="bad_cnt"></span>
  						</button>       	
				</ul>
			</div>
			<div>
				<ul id="attachment">
				</ul>
			</div>
			<div id="content_div">
			<div class="form-group">
				<div class="form-control" id="content"></div>
			</div>
			<div id="btn_area">
				<button id="update" class="btn btn-info">변경</button>
				<button id="delete" class="btn btn-success">삭제</button>
			</div>
		</div>
		</div>
		<div>
			<div class="form-group">
				<label for="comment_textarea">댓글을 입력하세요</label>
				<textarea class="form-control" rows="5"	id="comment_textarea" placeholder="욕설이나 모욕적인 댓글은 삭제될 수 있습니다" disabled="disabled"></textarea>
			</div>
			<button type="button" class="btn btn-info" 
				id="comment_write" disabled="disabled">댓글 작성</button>
		</div>
		<hr>
		<div id="comments">
		</div>
	</div>
	</div>
</body>
</html>