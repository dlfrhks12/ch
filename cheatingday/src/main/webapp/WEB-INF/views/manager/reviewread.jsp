<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

$(function(){
	
	//신고하기 버튼을 누르면 신고수1개가 늘어나도록
	$("#report").on("click",function(){

		var param = {
				_method: "patch",
				_csrf: "${_csrf.token}",
				rNo: $("#rNo").val()
		};
		
		$.ajax({
			data: param,
			method: "post",
			url: "/cheatingday/manager/reviewRepoart"
		}).done((result)=>{$("#report_area").text(result)}).fail(()=>{console.log(param)});
		
	});
});

</script>
</head>
<body>
<div>
<input type="hidden" id="rNo" value="${reviewRead.RNo}">
리뷰번호 : ${reviewRead.RNo}
</div>
<div>
내용: ${reviewRead.RContent}
</div>
<div>
별점: 
<c:forEach begin="1" end="${reviewRead.RStarPoint }">
		<img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg">
 			</c:forEach>
          <c:forEach begin="${reviewRead.RStarPoint+1 }" end="5">
                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg">
                        </c:forEach>
</div>
<div>
작성시간: ${reviewRead.RWriteTimeStr}
</div>
<div>
주문번호: ${reviewRead.ONo}
</div>
<div>
제목: ${reviewRead.RTitle}
</div>
<div>
신고수: <span id="report_area">${reviewRead.RReport}</span>
</div>
<div>
아이디: ${reviewRead.UUsername}
</div>
<div>
<button type="button"  class="btn btn-danger" id="report">리뷰 신고하기
</button>
</div>
</body>
</html>