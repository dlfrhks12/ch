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
$(function(){
	$("#delete").on("click", function() {
		var params = {
			rNo: $("#rNo").val(),
			_csrf: "${_csrf.token}",
			_method: "delete"
		}
		$.ajax({
			url: "/cheatingday/admin/report_delete",
			method: "post",
			data: params
		})
		.done((result)=>{ location.href="/cheatingday/admin/report_list" })
		.fail((result)=>{console.log(result)});
	});	
});
</script>
</head>
<body>
<div id="review">
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="20%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>가게명</th>
					<th>글쓴이</th>
					<th>리뷰제목</th>
					<th>날짜</th>
					<th>신고수</th>
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${page.alist}" var="report">
			<input type="hidden" id="rNo" value="${report.RNo}">
				<tr>
					<td>${report.SName}</td>
					<td>${report.UIrum}</td>
					<td><a href="/cheatingday/reviw/read?rNo=${report.RNo}" onclick="openWin()">${report.RTitle}</a></td>
					<td>${report.RWriteTimeStr}</td>
					<td>${report.RReport}</td>
					<td><button type="button" class="btn btn-danger" id="delete">리뷰삭제</button></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center;">
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
</body>
</html>