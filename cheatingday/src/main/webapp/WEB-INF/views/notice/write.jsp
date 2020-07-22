<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="/cheatingday/ckeditor/ckeditor.js"></script>
<script>
$(function() {
	//ck에디터 업로더
	var ck = CKEDITOR.replace("content",{
		height:'500px',
		filebrowserUploadUrl:"http://localhost:8081/cheatingday/notice/ckupload"
	})
	// form을 넘기기전에 값 확인
	$("#write").on("click", function() {
		console.log($("#writeForm").serialize());
		$("#writeForm").submit();
	});
});
</script>
</head>
<body>
	<form action="/cheatingday/notice/write" method="post" id="writeForm" enctype="multipart/form-data" style="width: 1000px; min-height: 800px; position:relative;  left : 20%;">
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="nTitle">
		</div>
		<div class="form-group">
			<textarea class="form-control" id="content" name="content" style="height: 800px;"></textarea>
		</div>
		<input type="hidden" name="_csrf" value="${_csrf.token}">
		<button type="button" class="btn btn-danger" id="write">작성</button>
	</form>
</body>
</html>
