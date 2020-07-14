<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function moveurl(url) { 
   var cate = "${store}";
	if(cate==[])
		alert("해당카테고리의 매장이 존재하지 않습니다");
	else
		location.href = url;
		console.log(cate);
} 
</script>
</head>
<body>
<div>
	<form name=move  method=post>
		<select name=url onchange="moveurl(this.value);">
			<option selected="selected">정렬</option>
			<option value="/cheatingday/store_list?job=review_list&&foodNo=${store[0].foodNo}">리뷰순 정렬</option>
			<option value="/cheatingday/store_list?job=star_list&&foodNo=${store[0].foodNo}">별점순 정렬</option>
		</select>
	</form>
		<br>	
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>매장명</th>
					<th>매장사진</th>
					<th>카테고리</th>
					<th>리뷰수</th>
					<th>별점</th>
				</tr>
			</thead>
			<tbody id="list">
				<c:forEach items="${store}" var="store">
					<tr>
						<td>${store.SName}</td>
						<td>${store.SSajin}</td>
						<td>${store.foodCategory}</td>
						<td>${store.SReviewCnt}</td>
						<td>${store.SStarPoint}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>