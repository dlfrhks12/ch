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
	$(function(){
		var ck = CKEDITOR.replace("rContent",{
			filebrowserUploadUrl:"http://localhost:8081/cheatingday/review/ckupload"
		});
		var idx = 0;
		
	
		$("#write").on("click",function(){
			var params = {
				rTitle : $("#rTitle").val(),
				rStarPoint: $("#rStarPoint").val(),
				rContent:CKEDITOR.instances['rContent'].getData(),
				sName: $("#sName").val(),
				_csrf: "${_csrf.token}"
			}
			console.log(params);
			$.ajax({
				url:"/cheatingday/review/write",
				method:"post",
				data:params
			}).done((result)=>{location.href = "/cheatingday/review/list"}).fail((result)=>{console.log(result)});
		})
	})
</script>
</head>
<body>
	<form action = "/cheatingday/review/write" method = "post" id = "writeForm">
		<div class= "form-group" id = "title_div">
			<label for = "rTitle"></label>
			<input type = "text" class = "form-control" id = "rTitle" placeholder = "제목을 입력하세요 " name = "rTitle">
		</div>
		<div>
			<select id = "rStarPoint" name = "rStarPoint">
				<option value = "1">★</option>
				<option value = "2">★★</option>
				<option value = "3">★★★</option>
				<option value = "4">★★★★</option>
				<option value = "5">★★★★★</option>
			</select>
		</div>
		<div>
		<select id = "sName" name="sNum">
			<c:forEach items = "${sName}" var = "s">
				<option value = "${s.sNum }">${s.sName}</option>
			</c:forEach>
		</select>
		</div>
		<div class = "form-group">
			<textarea class = "form-control" id = "rContent" name = "rContent"></textarea>
		</div>
		<button type = "button" class = "btn btn-outline-danger" id = "write">WRITE</button>
	</form>
</body>
</html>