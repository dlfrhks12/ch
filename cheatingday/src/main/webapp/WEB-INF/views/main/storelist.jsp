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
</style>
<script>
function loadImage() {   
   // 이하 하드디스크에 있는 이미지 파일을 로딩해 화면에 출력하는 코드
   var reader = new FileReader();
   reader.onload = function(e) {
      $("#show_profile").attr("src", e.target.result);
   }
}
$(function(){
   loadImage();
   var filter = "${filter}";
      console.log(filter);
   var food = "${foodno}"
      console.log(food);
   $("#filter").on("change", function(){
      console.log(this.value);
      if(this.value=="review")
         location.href="/cheatingday/store_list?foodNo="+food+"&&pageno=1&&job=review_list";
      if(this.value=="star")
         location.href="/cheatingday/store_list?foodNo="+food+"&&pageno=1&&job=star_list";
   })
})
</script>
</head>
<body>
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
               <tr style = "cursor:pointer;" onclick="location.href='/cheatingday/store_read?sNum=${store.SNum}'">
                  <td>${store.SName}</td>
                  <td><img id="show_sajin" height="200px;" src="${store.SSajin}"></td>
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