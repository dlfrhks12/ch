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
<<<<<<< HEAD
#show_sajin {float: right; width: auto; height: auto; max-height: 130px; max-width: 130px;}
=======
#show_sajin {float: left; width: auto; height: auto; max-height: 130px; max-width: 130px; margin-bottom: 5px;}
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
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
</script>
</head>
<body>
	<div>
      <h4 style="padding:3px;">치팅데이 등록 음식점</h4>
   </div>
	<div id="ta">
		<div>
			<c:forEach items="${store.mainlist}" var="store">
<<<<<<< HEAD
				<div>
					<hr>
	                <img id="show_sajin"  src="${store.SSajin}">
					<ul onclick="location.href='/cheatingday/store_read?sNum=${store.SNum}'">
=======
			<hr>
				<div id="menulist">
	                <img id="show_sajin" src="${store.SSajin}">
					<ul onclick="location.href='/cheatingday/order/orderPage?sNum=${store.SNum}'">
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
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