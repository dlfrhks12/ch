<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.modal-dialog {
	position: absolute;
	width: 500px;
	top: 35%;
	left: 40%;
}
</style>
<script>
$(function() {
	$(".fav").click(function() {
		var snum = $(this).data("num");
		var params ={
				SNum:snum,
				_method: "put",
				_csrf: "${_csrf.token}",
			};
		$.ajax({
			url: "/cheatingday/user/favorite",
			type: "post",
			data: params,
			success: function(result){
				if(result==1) {
					Swal.fire({
						title:"success",
						text:"즐겨찾기 되었습니다",
						icon:"success"
					}).then(()=>location.reload())
				}
				else if(result==2) {
					Swal.fire({
						title:"info",
						text:"즐겨찾기가 해제되었습니다",
						icon:"info"
					}).then(()=>location.reload())
				}
			}
		})
	})
});
</script>
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
					<th>즐겨찾기</th>
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${page.blist}" var="buylist">
				<tr>
					<td>${buylist.SName}</td>
					<td><a class='writer' data-toggle="modal" data-target="#myModal" data-writer="${buylist.SName}" onclick="openWin()">${buylist.SName}</a></td>
					<td>${buylist.OTotal}</td>
					<td>${buylist.OOrderTimeStr}</td>
					<c:if test="${buylist.favCheck eq false }">
						<td><button type="button" data-num="${buylist.SNum }" data-check="1" class="btn btn-info fav" ><i class="fas fa-star"></i></button></td>
					</c:if>
					<c:if test="${buylist.favCheck ne false }">
						<td><button type="button" data-num="${buylist.SNum }" data-check="2" class="btn btn-info fav" ><i class="fas fa-star" style="color:yellow;"></i></button></td>
					</c:if>
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
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
				<table>
					<tr>
						<th id="sName" data-dismiss='modal'>상호명</th>
						<th id="jumunMenu">주문메뉴</th>
						<th id="pay">금액</th>
						<th id="amount">수량</th>
					</tr>
					<c:forEach items="${page.blist}" var="buylist">
					<tr>
						<td>${buylist.SName}</td>
						<td>${buylist.OTotal}</td>
					</tr>
				</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>