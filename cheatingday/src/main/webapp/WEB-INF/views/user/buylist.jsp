<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.first {
	text-align: center;
}
.modal-dialog {
	position: absolute;
	width: 200px;
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
	$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})
});
</script> 
</head>
<body>

	 <div>
	<div>
	<div class="container">
	<div style="padding: 40px 0;">
		<h3><i class="fas fa-shopping-bag">&nbsp;구매내역</i></h3>
	</div>
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr class="table-danger">
					<th class="first">주문번호</th>
					<th class="first">상호명</th>
					<th class="first">주문메뉴</th>
					<th class="first">주문금액</th>
					<th class="first">구매일시</th>
					<th class="first">즐겨찾기</th>
				</tr>
			</thead>
			<tbody id="list" class="first">
				<c:forEach items="${page.blist}" var="buylist">
					<tr>
						<td>${buylist.orderNo}</td>
						<td><a href = "/cheatingday/review/write?orderNo=${buylist.orderNo}">${buylist.SName}</a></td>
						<td><a data-toggle="modal" data-target="#exampleModalCenter">${buylist.cartName}</a></td>
						<td>${buylist.cartPrice}</td>
						<td>${buylist.cartDayStr}</td>
						<c:if test="${buylist.favCheck eq false }">
							<td><button type="button" data-num="${buylist.SNum }"
									data-check="1" class="btn btn-info fav">
									<i class="fas fa-star"></i>
								</button></td>
						</c:if>
						<c:if test="${buylist.favCheck ne false }">
							<td><button type="button" data-num="${buylist.SNum }"
									data-check="2" class="btn btn-info fav">
									<i class="fas fa-star" style="color: yellow;"></i>
								</button></td>
								
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="text-align: center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a
					href="/cheatingday/user/buylist?pageno=${page.startPage-1}&uUsername=${username}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active"><a style="background-color: #E74D44; border: 1px solid;"
							href="/cheatingday/user/buylist?pageno=${i}&uUsername=${username}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li><a style="color: #E74D44" href="/cheatingday/user/buylist?pageno=${i}&uUsername=${username}">${i}</a></li>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a
					href="/cheatingday/user/buylist?pageno=${page.endPage+1}&uUsername=${username}">다음</a></li>
			</c:if>
		</ul>
	</div>
	</div>
	</div> 
	
	</div>
</body>
</html>