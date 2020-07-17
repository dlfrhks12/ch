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
   var start =${store.startPage}-1;
   var end = ${store.endPage}+1;
   console.log(start);
   console.log(end);
   var foodNo = location.search.substr(8,1);
      console.log(foodNo);
   var filter = location.search.substr(8);
      console.log(filter);
   $("#filter").on("change", function(){
      console.log(this.value);
      if(this.value=="review")
         location.href="/cheatingday/store_list?foodNo="+foodNo+"&&job=review_list";
      if(this.value=="star")
         location.href="/cheatingday/store_list?foodNo="+foodNo+"&&job=star_list";
   })
   $("#before").on("click", function(){
         location.href="/cheatingday/store_list?foodNo="+filter+"&&pageno=start";
   })
   $("#after").on("click", function(){
         location.href="/cheatingday/store_list?foodNo="+filter+"&&pageno=end";
   })
	var filter = "${filter}";
		console.log(filter);
	var food = "${foodno}"
		console.log(food);
	var foodNo = location.search.substr(8,1);
		console.log(foodNo);
	$("#filter").on("change", function(){
		console.log(this.value);
		if(this.value=="review")
			location.href="/cheatingday/store_list?foodNo="+foodNo+"&&pageno=1&&job=review_list";
		if(this.value=="star")
			location.href="/cheatingday/store_list?foodNo="+foodNo+"&&pageno=1&&job=star_list";
	})
})
</script>
</head>
<body>
<div>
	<select id="filter" name="filter">
		<option value="review" <c:if test="${filter}">selected</c:if>>리뷰순 정렬</option>
		<option value="star" <c:if test="${filter}">selected</c:if>>별점순 정렬</option>
	</select>
	<table class="table table-hover">
		<colgroup>
			<col width="10%">
            <col width="10%">
            <col width="10%">
            <col width="10%">
            <col width="10%">
            <col width="10%">
            <col width="10%">
            <col width="10%">
        </colgroup>
        <thead>
        	<tr>
        		<th>매장명</th>
        		<th>매장사진</th>
        		<th>카테고리</th>
        		<th>리뷰수</th>
        		<th>별점</th>
            </tr>
         </thead>
         <tbody id="list">
            <c:forEach items="${store.mainlist}" var="store">
               <tr>
                  <td>${store.SName}</td>
                  <td>${store.SSajin}</td>
                  <td>${store.foodCategory}</td>
                  <td>${store.SReviewCnt}</td>
                  <td>${store.SStarPoint}</td>
               </tr>
            </c:forEach>
         </tbody>
	</table>
</div>
<div style="text-align:center; display: inline-block; padding-left: 600px; ">
	<ul class="pagination" class="pagination pagination-lg">
		<c:if test="${store.prev==true}">
			<li class="page-item" id="before"><a href="/cheatingday/store_list?pageno=${store.startPage-1}&&job=${filter}">이전</a></li>
		</c:if>
        <c:forEach begin="${store.startPage}" end="${store.endPage}" var="i">
        	<c:choose>
        		<c:when test="${store.pageno eq i }">
        			<li  class="page-item">
        				<a class="page-link" href="/cheatingday/store_list?pageno=${i}">${i}</a>
        			</li>
               </c:when>
               <c:otherwise>
               		<li class="page-item"><a class="page-link"  href="/cheatingday/store_list?pageno=${i}&&job=${filter}">${i}</a></li>
               </c:otherwise>
            </c:choose>
         </c:forEach>
         <c:if test="${store.next==true}">
            <li  class="page-item" id="after"><a class="page-link" href="/cheatingday/store_list?pageno=${store.endPage+1}&&job=${filter}">다음</a></li>
         </c:if>
     </ul>
</div>  
<div>
	<select id="filter" name="filter">
		<option selected="selected">정렬</option>
		<option value="review">리뷰순 정렬</option>
		<option value="star">별점순 정렬</option>
	</select>
	<table class="table table-hover">
		<colgroup>
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th>매장명</th>
				<th>매장사진</th>
				<th>카테고리</th>
				<th>리뷰수</th>
				<th>별점</th>
			</tr>
		</thead>
		<tbody id="list">
			<c:forEach items="${store.mainlist}" var="store">
				<tr>
					<td>${store.SName}</td>
					<td>${store.SSajin}</td>
					<td>${store.foodCategory}</td>
					<td>${store.SReviewCnt}</td>
					<td>${store.SStarPoint}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div style="text-align:center; display: inline-block; padding-left: 600px; ">
	<ul class="pagination" class="pagination pagination-lg">
		<c:if test="${store.prev==true}">
			<li class="page-item"><a class="page-link" href="/cheatingday/store_list?foodNo=${foodno}&&pageno=${store.startPage-1}&&job=${filter}">이전</a></li>
		</c:if>
		<c:forEach begin="${store.startPage}" end="${store.endPage}" var="i">
			<c:choose>
				<c:when test="${store.pageno eq i }">
					<li  class="page-item">
						<a class="page-link" href="/cheatingday/store_list?foodNo=${foodno}&&pageno=${i}">${i}</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"  href="/cheatingday/store_list?foodNo=${foodno}&&pageno=${i}&&job=${filter}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${store.next==true}">
			<li  class="page-item"><a class="page-link" href="/cheatingday/store_list?foodNo=${foodno}&&pageno=${store.endPage+1}&&job=${filter}">다음</a></li>
		</c:if>
      </ul>
   </div>   
</body>
</html>