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
<style>
.first {
	text-align: center;
}	
#point {
	width:80px; 
	background-color: #E74D44; 
	border-radius: 8px; 
	color: white;
	margin: 0 auto;
	text-align: center;
}
</style>
<body>
<div>
	<div class="container">
	<div style="padding: 40px 0;">
		<h3 ><i class="fas fa-coins">&nbsp;포인트 적립내역</i></h3>
	</div>
		<table class="table table-hover">
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="20%">
			</colgroup>
				<tr class="table-danger">
					<th class="first">지점</th>
					<th class="first">적립일자</th>
					<th class="first">적립금액</th>
				</tr>
			<c:forEach items="${page.plist}" var="point">
				<tr>
					<td class="first">${point.SName}</td>
					<td class="first">${point.OOrderTimeStr}</td>
					<td class="first">${point.accumulationSal}</td>
				</tr>
			</c:forEach>
				<tr>
					<td style="text-align: center;"><i class="fas fa-plus-circle">포인트 총합</i></td>
					<td> </td>
					<td><div id="point" style="">${count}</div></td>
				</tr>
		</table>
		<hr>
		<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li class="page-item"><a href="/cheatingday/user/point?pageno=${page.startPage-1}&uUsername=${username}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a style="background-color: #E74D44; border: 1px solid;" href="/cheatingday/user/point?pageno=${i}&uUsername=${username}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a style="color: #E74D44" href="/cheatingday/user/point?pageno=${i}&uUsername=${username}">${i}</a></li>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/user/point?pageno=${page.endPage+1}&uUsername=${username}">다음</a></li>
			</c:if>
		</ul>
	</div>
		</div>
	</div>
	
</body>
</html>