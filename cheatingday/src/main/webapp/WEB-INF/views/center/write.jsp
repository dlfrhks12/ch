<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
//ck에디터 업로더
$(function() {
	var ck = CKEDITOR.replace("qContent",{
		height:'500px',
		filebrowserUploadUrl:"http://localhost:8081/cheatingday/center/ckupload"
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
	<form action="/cheatingday/center/write" method="post" id="writeForm" enctype="multipart/form-data" style="width: 1000px; min-height: 800px; position:relative;  left : 20%;">
		<div class="form-group" >
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="qTitle">
		</div>
		<div>
 		<select id = "category" name="qCano">
						<c:forEach items = "${category}" var = "c">
							<option value = "${c.qcano }">${c.qcategory}</option>
						</c:forEach>
				</select>
 		</div>
		<div class="form-group">
			<textarea class="form-control" id="content" name="qContent" style="height: 800px;"></textarea>
		</div>
		<input type="hidden" name="_csrf" value="${_csrf.token}">
		<button type="button" class="btn btn-danger" id="write">작성</button>
	</form>
</body>
</html>
