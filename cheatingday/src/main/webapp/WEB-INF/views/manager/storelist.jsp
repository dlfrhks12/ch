<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<title>음식점 리스트</title>
<script>

//음식점 등록 버튼관리  - 사업자 1명 : 음식점 1개
function storeButton(){

	if(store.SName!==null){ //상호명이 존재하면
		//버튼을 숨김
		$("#write").hide(); 
	} else { //상호명이 없으면
		//버튼이 보임
		$("#write").show(); 
	}

});

$(function(){
	
	storeButton();
	
});


</script>

</head>
<body>
<div>
		<table class="table table-hover">
			<colgroup>
				<col width="40%">
				<col width="60%">
			</colgroup>
			<thead>
				<tr>
					<th>상호명</th>
					<th>음식점 주소</th>
				</tr>
			</thead>
			<tbody id="list">
			 <c:forEach items="${storeList}" var="store">
				<tr>
					<td><a href="/cheatingday/manager/store_read?sNum=${store.SNum}">${store.SName}</a></td>
					<td>${store.SAddress}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="form-group">
	<a href="/cheatingday/manager/store_insert">
		<button type="button" id="write" class="btn btn-info">음식점 추가</button></a>
	</div>
</body>
</html>