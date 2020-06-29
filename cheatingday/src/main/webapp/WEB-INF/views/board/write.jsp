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
<script>
$(function() {
	var ck = CKEDITOR.replace("content", {
		filebrowserUploadUrl: 'http://localhost:8081/cheatingday/board/ckupload'
	});
	
	var idx = 0;
	$("#add").on("click", function() {
		var $input = $("<input>").attr("type","file").attr("class", "form-control-file").attr("name", "attachments[" + idx + "]");
		idx++;
		$input.appendTo($("#attachment_div"))
	});
	
	// form을 넘기기전에 값 확인
	$("#write").on("click", function() {
		console.log($("#writeForm").serialize());
		$("#writeForm").submit();
	});
});
</script>
</head>
<body>
 	<form action = "/cheatingday/board/write" method = "post" id = "writeForm">
 		<div class = "form-group">
 			<label for="title">제목:</label>
 			<input type = "text" class = "form-control" id = "title" name = "title" >
 		</div>
 		<div>
 		<select id = "boardcate">
						<c:forEach items = "${category}" var = "c">
							<option value = "${c.CATENO }">${c.CATEGORY}</option>
						</c:forEach>
				</select>
 		</div>
 		<div class = "form-group">
 			<input type = "text" class = "form-control" id = "content" name = "content" placeholder="내용">
 		</div>
 		<button type="button" class="btn btn-success" id="write">작성</button>
		<button type="button" id="add">첨부파일 추가</button>
		<div id="attachment_div"></div>
 	</form>
</body>
</html>