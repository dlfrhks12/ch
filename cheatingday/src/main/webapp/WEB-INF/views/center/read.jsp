<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/aboard/ckeditor/ckeditor.js"></script>
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
var qna = undefined;
function printQna() {
	$("#qTitle").val(qna.qtitle);
	$("#mUsername").text(qna.musername);
	$("#mIrum").text(qna.mirum);
	$("#qCano").text(qna.qcano);
	$("#qCategory").text(qna.qcategory);
	$("#qWriteTime").text(qna.qwriteTimeStr);
	//if(isLogin===true && qna.musername===loginId)
		var ck = CKEDITOR.replace("qContent", {
			// c:url은 웹 애플리케이션 컨텍스트 패스를 붙여주는 역할
			
			filebrowserUploadUrl: 'http://localhost:8081/cheatingday/center/ckupload'
		});
	$("#qContent").html(qna.qcontent).css("height","600px").css("overflow","scroll");
	// 초기화 - 버튼영역 감추기
	$("#btn_area").hide();

		$("#title").prop("disabled", false);
		$("#btn_area").show();
		$("#comment_textarea").prop("disabled", false);
		$("#comment_write").prop("disabled", false);
		
	} 
function printComment(qnacomment) {
	var $comments = $("#comments");
	$comments.empty();
	$.each(qnacomment, function(i, comment) {
		var $comment = $("<div>").appendTo($comments);
		var $upper_div = $("<div>").appendTo($comment);
		var $center_div = $("<div>").appendTo($comment);
		var $lower_div = $("<div>").appendTo($comment);
		$("<span></span>").text(comment.ausername).appendTo($upper_div);
		$("<span>").text(comment.qcwriteTime).appendTo($lower_div);
		$("<div>").html(comment.qccontent).appendTo($center_div);  
		
		if(comment.ausername===loginId) {
			var btn = $("<button>").attr("class","delete_comment").attr("data-cno",comment.qcno).attr("data-writer", comment.ausername)
				.text("삭제").appendTo($center_div).css("float","right");
		}
		$("<hr>").appendTo($comment);
	});
}
$(function() {
	// 자바객체 -> json -> 자바스크립트 객체
	var qno = location.search.substr(5);
	$.ajax({
		url:"/cheatingday/center/read",
		data: "qNo=" + qno,
		method:"post"
	}).done((result)=>{
		qna = result;
		printQna();
		printComment(qna.comments);
	});
	
	// 1. 댓글 달기
	$("#comment_write").on("click", function() {
		if(isLogin===false)
			return;
		var params = {
			// writer, content, csrf
			bno : board.bno, 
			content : $("#comment_textarea").val(),
			_csrf: "${_csrf.token}"
		}
		$.ajax({
			url: "/aboard/comment/write",
			method: "post",
			data: params
		})
		.done((result)=>{ printComment(result); })
		.fail((result)=>{console.log(result)});
	})
	
	// 댓글 삭제
	$("#comments").on("click", ".delete_comment", function() {
		// data-ano 속성의 값을 꺼낼 때 
		// data("ano") -> 넣은 값의 타입 그대로
		// attr("data-ano") -> 문자열
		var params = {
			cno: $(this).data("cno"),
			bno: board.bno,
			writer: $(this).data("writer"),
			_method: "delete",
			_csrf: "${_csrf.token}"
		}
		$.ajax({
			url: "/aboard/comment/delete",
			method: "post",
			data: params
		})
		.done((result)=>{ printComment(result); })
		.fail((result)=>{console.log(result)});
	});
	
	$("#update").on("click", function() {
		var params = {
			qNo: qna.qno,
			qTitle: $("#qTitle").val(),
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
	
	$("#delete").on("click", function() {
		var params = {
			bno: board.bno,
			_csrf: "${_csrf.token}",
			_method: "delete"
		}
		$.ajax({
			url: "/aboard/board/delete",
			method: "post",
			data: params
		})
		.done((result)=>{ location.href="/aboard" })
		.fail((result)=>{console.log(result)});
	});	
});
</script>
</head>
<body>
<div id="wrap">
	<div>
		<div id="title_div">
			<div id="upper">
				<input type="text" id="qTitle" disabled="disabled" style="min-width: 600px;">
				<input type="hidden" id="mUsername">
				<span id="mIrum"></span>
			</div>
			<div id="lower">
				<input type="hidden" id="qCano">
				<ul id="lower_left">
					<li><span id="qWriteTime"></span></li>
					<li><span id="qCategory"></span></li>

				</ul>
			</div>
		</div>
		<div id="content_div">
			<div class="form-group">
				<div class="form-control" id="qContent"></div>
			</div>
			<div id="btn_area">
				<button id="update" class="btn btn-info">변경</button>
				<button id="delete" class="btn btn-success">삭제</button>
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