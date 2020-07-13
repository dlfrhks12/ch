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
var qna = undefined;
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
}
function printComment(qnacomment) {
	var $comments = $("#comments");
	$comments.empty();
	$.each(qnacomment, function(i, comment) {
		console.log(comment);
		var $comment = $("<div>").appendTo($comments);
		var $upper_div = $("<div>").appendTo($comment);
		var $center_div = $("<div>").appendTo($comment);
		var $lower_div = $("<div>").appendTo($comment);
		$("<span></span>").text("관리자").appendTo($upper_div);
		if(comment.ausername===loginId)
			$("<textarea>").attr("id","qccontent").attr("style","min-width:800px; min-height:200px;").val(comment.qcContent).appendTo($center_div); 
		else
			$("<textarea>").attr("id","qccontent").attr("disabled","disabled").attr("style","min-width:800px; min-height:200px;").val(comment.qcContent).appendTo($center_div); 
		$("<span>").text(comment.qcWriteTime).appendTo($lower_div);
		if(comment.ausername===loginId) {
			var btn = $("<button>").attr("class","delete_comment").attr("data-qcno",comment.qcNo).attr("data-ausername", comment.ausername)
				.text("삭제").appendTo($center_div).css("float","right");
			var btn2 = $("<button>").attr("class","update_comment").attr("data-qcno",comment.qcNo).attr("data-ausername", comment.ausername)
			.text("수정").appendTo($center_div).css("float","right");
		}
		$("<hr>").appendTo($comment);
	});

}
$(function() { 
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
		.done((result)=>{ printComment(result); $("#comment_textarea").val(""); $("#comment_textarea").prop("disabled", true); $("#comment_write").prop("disabled", true);})
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
		$.ajax({
			url: "/cheatingday/center/comment_delete",
			method: "post",
			data: params
		})
		.done((result)=>{ printComment(result); })
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
		.done((result)=>{alert("변경되었습니다"); console.log(result); })
		.fail((result)=>{console.log(params);});
	});
	
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
	<div id="wrap">
		<div>
			<div id="title_div">
				<div id="upper">
					<input type="text" id="qTitle" disabled="disabled"
						style="min-width: 600px;"> <input type="hidden"
						id="mUsername"> <span id="mIrum"></span>
				</div>
				<div id="lower">
					<input type="hidden" id="qCano">
					<ul id="lower_left">
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
					<button id="update" class="btn btn-info">변경</button>
					<button id="delete" class="btn btn-success">삭제</button>
				</div>
			</div>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div>
				<div class="form-group">
					<label for="comment_textarea">댓글을 입력하세요</label>
					<textarea class="form-control" rows="5" id="comment_textarea"
						placeholder="관리자만 댓글을 달 수 있습니다" disabled="disabled"></textarea>
				</div>
				<button type="button" class="btn btn-info" id="comment_write"
					disabled="disabled">댓글 작성</button>
			</div>
			</sec:authorize>
			<hr>
			<div id="comments"></div>
		</div>
	</div>
</body>
</html>