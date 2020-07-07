<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<col width="40%">
				<col width="10%">
				<col width="30%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>상호명</th>
					<th>적립일</th>
					<th>적립금액</th>
				</tr>
			</thead>
			<tbody id="list">
				<c:forEach items="${page.list}" var="board">
					<tr>
						<td>${board.bno}</td>
						<td><a href="/cheatingday/user/point?bno=${point.bno}">${point.title }</a></td>
						<td class='writer' data-toggle="modal" data-target="#myModal"
							data-writer="${board.writer }">${board.writer }</td>
						<td>${board.writeTimeStr}</td>
						<td>${board.readCnt }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align: center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/aboard/board/list?pageno=${page.startPage-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active"><a href="/aboard/board/list?pageno=${i}">${i}</a>
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
		<button type="button" id="write" class="btn btn-info"
			onclick="location.href='/aboard/board/write'">글쓰기</button>
	</div>
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<ul>
						<li id="read_by_id" data-dismiss='modal'>게시물 보기</li>
						<li id="find_joindate">가입일 보기</li>
						<sec:authorize access="isAuthenticated()">
							<li id="write_memo">메모 보내기</li>
						</sec:authorize>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>