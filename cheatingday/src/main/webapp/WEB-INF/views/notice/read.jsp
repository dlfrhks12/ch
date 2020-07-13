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
$(function() {
	var notice = ${notice};
	console.log(notice);
	// 자바객체 -> json -> 자바스크립트 객체
	$("#nTitle").val(notice.ntitle);
	$("#aUsername").val(notice.ausername);
	$("#aIrum").text(notice.airum);
	$("#nNo").text(notice.nno);
	$("#write_time").text(notice.nwriteTimeStr);
	$("#read_cnt").text(notice.nreadCnt);
	if(isLogin===true && notice.ausername===loginId)
		var ck = CKEDITOR.replace("content",{
			height:'500px',
			filebrowserUploadUrl:"http://localhost:8081/cheatingday/notice/ckupload"
		})
	$("#content").html(notice.content);
	// 초기화 - 버튼영역 감추기
	$("#btn_area").hide();
	if(isLogin===true && notice.ausername===loginId) {
		$("#nTitle").prop("disabled", false);
		$("#btn_area").show();
		$("#content").prop("readonly", false);
	} 
	$("#delete").on("click", function(){
		var params = {
			nNo : notice.nno,
			_method: "delete",
			_csrf: "${_csrf.token}"
		}
		$.ajax({
			url: "/cheatingday/notice/delete",
			method: "post",
			data: params
		})
		.done((result)=>{ location.href=result })
		.fail((result)=>{console.log(result)});

	})
	$("#update").on("click", function(){
		var params = {
			nNo : notice.nno,
			nTitle : $("#nTitle").val(),
			content : CKEDITOR.instances['content'].getData(),
			_csrf: "${_csrf.token}",
			_method: "patch"
		}
		console.log(params);
		$.ajax({
			url: "/cheatingday/notice/update",
			method: "post",
			data: params
		})
		.done((result)=>{ location.reload();})
		.fail((result)=>{console.log(result)});
	})
});
</script>
</head>
<body>
<div id="wrap">
	<div>
		<div id="title_div">
			<div id="upper">
				<input type="text" id="nTitle" disabled="disabled" style="min-width: 600px;">
				<input type="hidden" id="aUsername">
				<span style="text-align: " id="aIrum"></span>
			</div>
			<div id="lower">
				<ul id="lower_left">
					<li><span id="write_time"></span></li>
				</ul>
				<ul id="lower_right">
  						<li>
						<button type="button" class="btn btn-success" disabled="disabled">
							조회수 <span class="badge" id="read_cnt"></span>
						</button></li>        	
				</ul>
			</div>
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
		<hr>
</div>
</body>
</html>