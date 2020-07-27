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
  #all{
   		width: 1200px;
   		display: inline-block;
   		margin-left: 450px;
   		margin-top: 120px;
   }
</style>
</head>
<body>

 <div id = "all">
	<table class = "table table-hover">
		<colgroup>
			<col width="13%">
			<col width="19%">
			<col width="19%">
			<col width="19%">
			<col width="19%">
			<col width="30%">
		</colgroup>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>별점</th>
				<th>매장 이름</th>
				<th>작성시간</th>
			</tr>

		</thead>
		<tbody id = "list">
			<c:forEach items = "${page.reviewlist}" var = "review">
				<tr>
					<td id = "rNo">${review.RNo}</td>
					<td id = "rTitle"><a href = "/cheatingday/review/read?rNo=${review.RNo}">${review.RTitle}</a></td>
					<td id = "uUsername">${review.UUsername}</td>
					<td>
                  <c:forEach begin="1" end="${review.RStarPoint }">
                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg">
                        </c:forEach>
                        <c:forEach begin="${review.RStarPoint+1 }" end="5">
                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg">
                        </c:forEach>
               </td>
					<td id = "sName">${review.SName }</td>
					<td id = "rWriteTimeStr">${review.RWriteTimeStr }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
 <div style="text-align:center; display: inline-block; padding-left: 600px; ">
      <ul class="pagination" class="pagination pagination-lg">
         <c:if test="${page.prev==true}">
            <li class="page-item"><a class="page-link" href="/cheatingday/review/list?rNo=${rNo}&&pageno=${page.startPage-1}&&job=${filter}">이전</a></li>
         </c:if>
         <c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
            <c:choose>
               <c:when test="${page.pageno eq i }">
                  <li  class="page-item">
                     <a class="page-link" href="/cheatingday/review/list?rNo=${rNo}&&pageno=${i}">${i}</a>
                  </li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"  href="/cheatingday/review/list?rNo=${rNo}&&pageno=${i}&&job=${filter}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${page.next==true}">
			<li  class="page-item"><a class="page-link" href="/cheatingday/review/list?rNo=${rNo}&&pageno=${page.endPage+1}&&job=${filter}">다음</a></li>
		</c:if>
      </ul>
      </div>   
</body>
</html>