<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<sec:authentication property="principal.username" var="username"/>
<div>
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="30%">
				<col width="30%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>리뷰번호</th>
					<th>아이디</th>
					<th>제목</th>
					<th>작성시간</th>
					<th>별점</th>
				</tr>
			</thead> 
			<tbody id="list">
			<c:forEach items="${page.MList}" var="manager">
				<tr>
					<td>${manager.RNo}</td>
					<td>${manager.UUsername}</td>
					<td><a href="/cheatingday/manager/review_read?rNo=${manager.RNo}">${manager.RTitle}</a></td>
					<td>${manager.RWriteTimeStr}</td>
					<td>${manager.RStarPoint}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/cheatingday/manager/review_list?pageno=${page.startPage-1}&mUsername=${username}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/cheatingday/manager/review_list?pageno=${i}&mUsername=${username}">${i}</a>
						</li>
					</c:when>     
					<c:otherwise>
						<li><a href="/cheatingday/manager/review_list?pageno=${i}&mUsername=${username}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/manager/review_list?pageno=${page.endPage+1}&mUsername=${username}">다음</a></li>
			</c:if>
		</ul>
	</div> 
	
</body>
</html>