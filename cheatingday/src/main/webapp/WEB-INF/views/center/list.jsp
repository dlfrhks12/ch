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
<div style="width: 1200px; margin-left: 250px; margin-right: 450px;">

		<div style="padding: 40px 0;">
      <h3>Q & A 문의 게시판</h3>
 		</div>

		<table class="table table-hover">
			<colgroup>
				<col width="5%">
				<col width="10%">
				<col width="30%">
				<col width="10%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr class="table-danger">
					<th>번호</th>
					<th class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"> Q&A</a>
					<ul class="dropdown-menu">				
						<li><a href="/cheatingday/center/list">전체목록</a></li>		
		      		 	<li><a href="/cheatingday/center/list?qCano=1">문의목록</a></li>
		      		 	<li><a href="/cheatingday/center/list?qCano=2">신고목록</a></li>
		      		 	<li><a href="/cheatingday/center/list?qCano=3">기타</a></li>
					</ul>
					</th>
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
					<td>${qna.QWriteTimeStr}</td>
					<td>
					<c:choose>
						<c:when test="${qna.QIscomment==true}">
							<input type="text" disabled="disabled" value="답변완료">
						</c:when>
						<c:otherwise>
							<input type="text" disabled="disabled" value="답변대기중">
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center; padding-right: 800px;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/cheatingday/center/list?pageno=${page.startPage-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/cheatingday/center/list?pageno=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/cheatingday/center/list?pageno=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/center/list?pageno=${page.endPage+1}">다음</a></li>
			</c:if>
		</ul>
	</div>
	<sec:authorize access="hasRole('ROLE_MANAGER')">
		<div class="form-group" style="margin-left: 250px; margin-right: 450px;">
			<a class="btn btn-danger" href="/cheatingday/center/write">글쓰기</a>
		</div>
	</sec:authorize>
</body>
</html>