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
<style>
.pagination{margin-top:20px;white-space: nowrap; text-align: center; height: 37px; line-height:37px;}
.pagination a{display:inline-block; border:1px solid #f7f7f7; height:35px; width:35px;  vertical-align:middle; text-align:center; text-decoration:none; font-size:15px; color:#898989;background:#f7f7f7;}
.pagination .on{color:#fff; font-weight:bold; background:#333;}
.pagination .prev,.pagination .next{height:35px; width:35px; border:1px solid #ebebeb;overflow:hidden;position:relative; font-size:0;}
.pagination .prev.end,.pagination .next.end{height:35px; width:35px; font-size:0;}
.pagination .prev{background:#ebebeb url("") no-repeat center center; margin-right:11px;}
.pagination .prev.end{background:#ebebeb url("") no-repeat center center; margin-right:4px; border-right:0;}
.pagination .next{background:#ebebeb url("") no-repeat center center; margin-left:12px;}
.pagination .next.end{background:#ebebeb url("") no-repeat center center; margin-left:4px; border-left:0;}
</style>
<script>
$(function(){
	//해당 댓글 삭제
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
	<span style="font-size: x-large; font-weight: bold; color: red;" >신고 리뷰 관리</span>
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
					<td><a href="/cheatingday/review/read?rNo=${report.RNo}" onclick="openWin()">${report.RTitle}</a></td>
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
				<li><a class="prev" href="/cheatingday/admin/report_list?pageno=${page.startPage-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/cheatingday/admin/report_list?pageno=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/cheatingday/admin/report_list?pageno=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a class="" href="/cheatingday/admin/report_list?pageno=${page.endPage+1}">다음</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>