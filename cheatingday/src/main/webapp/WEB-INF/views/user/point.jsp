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
			</colgroup>
			<thead>
				<tr>
					<th>지점</th>
					<th>적립일자</th>
					<th>적립금액</th>
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${page.plist}" var="point">
				<tr>
					<td><a href="/cheatingday/point/read?sName=${point.SName}" onclick="openWin()">${point.SName}</a></td>
					<td>${point.OOrderTimeStr}</td>
					<td>${point.accumulationSal}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/cheatingday/point/list?pageno=${page.startPage-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/cheatingday/point/list?pageno=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/cheatingday/point/list?pageno=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/point/list?pageno=${page.endPage+1}">다음</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>