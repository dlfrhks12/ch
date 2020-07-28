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
	
	$("#write").on("click", function() {
		var params = {
			title: $("#title").val(),
			cateno: $("#boardcate").val(),
			content: CKEDITOR.instances['content'].getData(),
			_csrf: "${_csrf.token}",
		}
		console.log(params);
		$.ajax({
			url: "/cheatingday/board/write",
			method: "post",
			data: params
		})
		.done((result)=>{location.href="/cheatingday/board/list"})
		.fail((result)=>{console.log(result)});
	});
	
});
</script>
<style>
	#writeForm{
		font-size: large;
		font-weight: bold;
		margin:0 auto;
		border: solid 1px gray;
		height:900px;
		border-radius: 35px;
		width:1200px;
	}
	#title_div{
		margin: 50px 100px 50px 200px;
		width:800px;
		border-color: navy;
		margin-bottom: 20px;
	}
	#boardcate{
		display: inline-block;
		text-align: center;
		margin: 30px 500px 20px 200px;
	}
	 #cke_content{
	 	width: 800px;
	 	margin: 0 auto;
	 	heigh: 800px;
	 	
	 }
	 #add{
	 	margin-left: 800px;
	 }
</style>
</head>
<body>
 	<form action = "/cheatingday/board/write" method = "post" id = "writeForm">
 		<div class = "form-group" id = "title_div">
 			<label for="title"></label>
 			<input type = "text" class = "form-control" id = "title" placeholder="제목을 입력하세요" name = "title" >
 		</div>
 		<div>
 		<select id = "boardcate" name="cateno">
			<c:forEach items = "${category}" var = "c">
				<option value = "${c.CATENO }">${c.CATEGORY}</option>
			</c:forEach>
		</select>
 		</div>
 		<div class = "form-group">
 			<textarea class = "form-control" id = "content" name = "content"></textarea>
 		</div>
 		<button type="button" class="btn btn-outline-danger" id="write">WRITE</button>
		<div id="attachment_div"></div>
 	</form>
</body>
</html>