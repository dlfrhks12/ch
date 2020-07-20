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
	${map.count}개의 게시물이 있습니다.
	<div>
	<select id="filter" name="filter">
		<option value="review" <c:if test="${filter}">selected</c:if>>리뷰순 정렬</option>
		<option value="star" <c:if test="${filter}">selected</c:if>>별점순 정렬</option>
	</select>
	<table class="table table-hover">
        <tr>
        	<th>매장명</th>
        	<th>매장사진</th>
        	<th>카테고리</th>
        	<th>리뷰수</th>
        	<th>별점</th>
        </tr>
        <c:forEach var="store" items="${map.list}">
               <tr>
                  <td><a href="/cheatingday/order/orderPage?sNum=${store.SNum}">${store.SName}</a></td>
                  <td>${store.SSajin}</td>
                  <td>${store.foodCategory}</td>
                  <td>${store.SReviewCnt}</td>
                  <td>${store.SStarPoint}</td>
               </tr>
            </c:forEach>
	</table>
</div>
<div style="text-align:center; display: inline-block; padding-left: 600px; ">
	<ul class="pagination" class="pagination pagination-lg">
		<c:if test="${store.prev==true}">
			<li class="page-item" id="before"><a href="/cheatingday/search_list?pageno=${store.startPage-1}&&job=${filter}">이전</a></li>
		</c:if>
        <c:forEach begin="${store.startPage}" end="${store.endPage}" var="i">
        	<c:choose>
        		<c:when test="${store.pageno eq i }">
        			<li  class="page-item">
        				<a class="page-link" href="/cheatingday/search_list?pageno=${i}">${i}</a>
        			</li>
               </c:when>
               <c:otherwise>
               		<li class="page-item"><a class="page-link"  href="/cheatingday/search_list?pageno=${i}&&job=${filter}">${i}</a></li>
               </c:otherwise>
            </c:choose>
         </c:forEach>
         <c:if test="${store.next==true}">
            <li  class="page-item" id="after"><a class="page-link" href="/cheatingday/search_list?pageno=${store.endPage+1}&&job=${filter}">다음</a></li>
         </c:if>
     </ul>
</div>  
</body>
</html>