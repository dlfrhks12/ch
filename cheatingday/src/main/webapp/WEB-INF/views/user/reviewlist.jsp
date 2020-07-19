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
<div>
${page }
	<div class="container">
	<div style="padding: 40px 0;">
		<h3>내가 쓴 리뷰내역</h3>
	</div>
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr class="table-danger">
					<th>상호명</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>주문내역</th>
					<th>작성일자</th>
					<th>별점</th>
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${page.rlist}" var="review">
				<tr>
					<td>${review.SName}</td>
					<td>${review.category}</td>
					<td data-toggle="modal" data-target="#exampleModalCenter">${review.RTitle}</td>
					<td>${review.menuname}</td>
					<td>${review.RWriteTimeStr}</td>
					<td>${review.RStarPoint} /
						<c:forEach begin="1" end="${review.RStarPoint }">
                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg">
                        </c:forEach>
                        <c:forEach begin="${review.RStarPoint+1 }" end="5">
                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg">
                        </c:forEach>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/cheatingday/user/reviewlist?pageno=${page.startPage-1}&uUsername=${username}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/cheatingday/user/reviewlist?pageno=${i}&uUsername=${username}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/cheatingday/user/reviewlist?pageno=${i}&uUsername=${username}">${i}</a></li>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/user/reviewlist?pageno=${page.endPage+1}&uUsername=${username}">다음</a></li>
			</c:if>
		</ul>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">구매내역 상세정보</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h4>page</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" style="background-color:red;">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>