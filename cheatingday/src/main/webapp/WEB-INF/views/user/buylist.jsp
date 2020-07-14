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
<div>
		<table class="table table-hover">
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="20%">
			</colgroup>
			<thead>
				<tr>
					<th>상호명</th>
					<th>주문메뉴</th>
					<th>주문금액</th>
					<th>구매일시</th>
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${page.blist}" var="buylist">
				<tr>
					<td>${buylist.SName}</td>
					<td><a href="/cheatingday/buylist/read?sName=${buylist.SName}" onclick="openWin()">${buylist.SName}</a></td>
					<td>${buylist.OTotal}</td>
					<td>${buylist.OOrderTimeStr}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/cheatingday/buylist/list?pageno=${page.startPage-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/cheatingday/buylist/list?pageno=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/cheatingday/buylist/list?pageno=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/buylist/list?pageno=${page.endPage+1}">다음</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>