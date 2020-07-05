<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입점 신청</title>
<script>
$(function(){
	$("#button").on("click",function(){
		$("#store_insert_form").submit();
	});
	
});
</script>
</head>
<body>
	<div id="wrap">
		<form id="store_insert_form" action="/cheatingday/manager/store_apply" method="post">
			<input type="hidden" name="_csrf" value="${_csrf.token}">
		<div class="form-group">
			<label for="mnum">사업자번호</label>
			<input type="text" name="mnum" id="mnum" placeholder="사업자번호를 입력해주세요"> 
		</div>
		<div class="form-group">
			<label for="mirum">사업자이름</label>
			<input type="text" name="mirum" id="mirum" placeholder="성명을 입력해주세요"> 
		</div>
		<div class="form-group">
			<label for="mtel">사업자전화번호</label>
			<input type="text" name="mtel" id="mtel" placeholder="전화번호를 입력해주세요"> 
		</div>
		<div class="form-group">
			<label for="memail">이메일</label>
			<input type="text" name="memail" id="memail" placeholder="이메일을 입력해주세요"> 
		</div>
		
		<div class="form-group">
			<button id="button">입점신청하기</button>
		</div>
		</form>
	</div>
</body>
</html>