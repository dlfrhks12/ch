<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	section {margin-left: 300px; margin-right: 300px;}
</style>
</head>
<body>
<sec:authentication property="principal.username" var="username"/>
<div>

<div style="padding: 40px 0;">
      <h3><i class="fas fa-pencil-alt">&nbsp;매장 리뷰</i></h3>
	<br>

		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="20%">
			</colgroup>
			<thead>
				<tr class="table-danger">
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
					<td><a href="/cheatingday/review/read?rNo=${manager.RNo}">${manager.RTitle}</a></td>
					<td>${manager.RWriteTimeStr}</td>
					<td>${manager.RStarPoint} /
						<c:forEach begin="1" end="${manager.RStarPoint }">
                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg">
                        </c:forEach>
                        <c:forEach begin="${manager.RStarPoint+1 }" end="5">
                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg">
                        </c:forEach>
					</td>
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
							<a style= "background-color:#E74D44; border: 1px solid;" href="/cheatingday/manager/review_list?pageno=${i}&mUsername=${username}">${i}</a>
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