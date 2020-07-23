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
		var filter = "${filter}";
		console.log(filter);
		var rNo = "${RNo}";
		$("#filter").on("change",function(){
			console.log(this.value);
			if(this.value=="review")
				location.href = "/cheatingday/review/list?rNo="+rNo+"&&pageno=1&&rno_list";
			if(this.value=="star")
				location.href = "/cheatingday/review/list?rNo="+rNo+"&&pageno=1&&star_list";
		})
	})
</script>
</head>
<body>

 <div>
	<select id = "filter" name = "filter">
		<option selected="selected">정렬</option>
		<option value = "roo_list">최신순</option>
		<option value = "star_list">별점순</option>
	</select>
	<table class = "table table-hover">
		<colgroup>
			<col width="19%">
			<col width="19%">
			<col width="19%">
			<col width="19%">
			<col width="19%">
			<col width="19%">
		</colgroup>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>별점</th>
				<th>주문번호</th>
				<th>매장 이름</th>
				<th>작성시간</th>
			</tr>
      <a class="btn btn-outline-danger" href="/cheatingday/review/write">리뷰쓰기</a>
   </div>  
		</thead>
		<tbody id = "list">
			<c:forEach items = "${review.reviewlist }" var = "review">
				<tr>
					<td id = "rNo">${review.RNo}</td>
					<td id = "rTitle"><a href = "/cheatingday/review/read?rNo=${review.RNo}">${review.RTitle}</a></td>
					<td id = "uUsername">${review.UUsername}</td>
					<td id = "rStarPoint">${review.RStarPoint}</td>
					<td id = "oNo">${review.ONo }</td>
					<td id = "sName">${review.SName }</td>
					<td id = "sName">${review.RWriteTimeStr }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
 <div style="text-align:center; display: inline-block; padding-left: 600px; ">
      <ul class="pagination" class="pagination pagination-lg">
         <c:if test="${review.prev==true}">
            <li class="page-item"><a class="page-link" href="/cheatingday/review/list?rNo=${rNo}&&pageno=${review.startPage-1}&&job=${filter}">이전</a></li>
         </c:if>
         <c:forEach begin="${review.startPage}" end="${review.endPage}" var="i">
            <c:choose>
               <c:when test="${review.pageno eq i }">
                  <li  class="page-item">
                     <a class="page-link" href="/cheatingday/review/list?rNo=${rNo}&&pageno=${i}">${i}</a>
                  </li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"  href="/cheatingday/review/list?rNo=${rNo}&&pageno=${i}&&job=${filter}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${store.next==true}">
			<li  class="page-item"><a class="page-link" href="/cheatingday/review/list?rNo=${rNo}&&pageno=${review.endPage+1}&&job=${filter}">다음</a></li>
		</c:if>
      </ul>
      </div>   
</body>
</html>