<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>store list</title>
<style>
section {margin-left:470px; margin-right:450px; margin-top:50px;}
#list {width:990px; margin: 0 auto;}
table th{text-align: center;}
#show_sajin {float: left; width: auto; height: auto; max-height: 120px; max-width: 120px; margin-bottom: 5px;}
#nosajin{padding: 15px 0px 0px 0px; height: 60px; line-height: 30px;}
#filter{float:right; margin-right: 30px;}
#menulist ul li {font-size: 15px; list-style:none; margin-left: 100px; padding: 5px;}
#menulist {margin-left: 70px; }
</style>
<script>
function loadImage() {	
	// 이하 하드디스크에 있는 이미지 파일을 로딩해 화면에 출력하는 코드
	var reader = new FileReader();
	reader.onload = function(e) {
		$("#show_sajin").attr("src", e.target.result);
	}
}
$(function(){
	loadImage();
	//분류 요소들을 꺼내와서 자바스크립스식으로 변환
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
	<select id="filter" name="filter">
			<option selected="selected">정렬</option>
			<option value="review">리뷰순 정렬</option>
			<option value="star">별점순 정렬</option>
	</select>
	<div>
      <h4 style="padding:3px; margin-left: 50px;">치팅데이 등록 음식점 </h4>
      
   </div>
	<div id="ta">
		<div>
			<c:forEach items="${store}" var="store">
				<div id="menulist">
	                <img id="show_sajin" src="${store.SSajin}">
					<ul onclick="location.href='/cheatingday/order/orderPage?sNum=${store.SNum}'">
						<li style="font-size: 21px;">${store.SName}</li>
						<li>별점 : ${store.SStarPoint} /
							<c:forEach begin="1" end="${store.SStarPoint }">
	                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg">
	                        </c:forEach>
	                        <c:forEach begin="${store.SStarPoint+1 }" end="5">
	                           <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg">
	                        </c:forEach>
                        </li>
	                  	<li>리뷰 : ${store.SReviewCnt} 개</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>