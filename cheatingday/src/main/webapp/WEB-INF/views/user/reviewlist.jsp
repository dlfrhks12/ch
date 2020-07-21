<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div>
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
					<th>글번호</th>
					<th>아이디</th>
					<!-- <th>카테고리</th> -->
					<th>상호명</th>
					<!-- <th>주문내역</th> -->
					<th>제목</th>
					<th>작성일자</th>
					<th>별점</th>
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${page.rlist}" var="review">
				<tr>
					<td>${review.RNo}</td>
					<td>${review.UUsername}</td>
					<%-- <td>${review.카테고리}</td> --%>
					<td>${review.SName}</td>
					<td><a href="/cheatingday/buylist/read?sName=${review.RContent}" onclick="openWin()">${review.RContent}</a></td>
					<%-- <td>${review.주문내역}</td> --%>
					<td>${review.RWriteTimeStr}</td>
					<td>${review.RStarPoint}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/cheatingday/reviewlist/list?pageno=${page.startPage-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/cheatingday/reviewlist/list?pageno=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/cheatingday/reviewlist/list?pageno=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/reviewlist/list?pageno=${page.endPage+1}">다음</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>