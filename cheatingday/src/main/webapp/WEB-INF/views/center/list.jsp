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
				<col width="10%">
				<col width="20%">
				<col width="10%">
				<col width="30%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>답변여부</th>
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${page.qlist}" var="qna">
				<tr>
					<td>${qna.QNo}</td>
					<td>${qna.QCategory}</td>
				<td><a href="/cheatingday/center/read?qNo=${qna.QNo}" onclick="openWin()">${qna.QTitle}</a></td>
					<td>${qna.MIrum}</td>
					<td>${qna.NWriteTimeStr}</td>
					<td>
						<c:when test="${qna.QIscomment==1}">
							<input type="text" disabled="disabled" value="답변완료">
						</c:when>
						<c:otherwise>
							<input type="text" disabled="disabled" value="답변대기중">
						</c:otherwise>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/aboard/board/list?pageno=${page.startPage-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/aboard/board/list?pageno=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/aboard/board/list?pageno=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/aboard/board/list?pageno=${page.endPage+1}">다음</a></li>
			</c:if>
		</ul>
	</div>
	<div class="form-group">
		<a class="btn btn-info" href="/cheatingday/notice/write">글쓰기</a>
	</div>
</body>
</html>