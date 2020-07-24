<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
section {margin-left:470px; margin-right:450px; margin-top:50px;}
#list {width:990px; margin: 0 auto;}
table th{text-align: center;}
#show_sajin {float: left; width: auto; height: auto; max-height: 150px; max-width: 150px; margin-top: 30px;}
#nosajin{padding: 15px 0px 0px 0px; height: 60px; line-height: 30px;}
#filter{float:right; margin-right: 30px;}
#menulist ul li {font-size: 15px; list-style:none; margin-left: 30px; padding: 5px;}
#menulist {margin-left: 80px; }
</style>
</head>
<body>
	<div>
      <h3 style="padding:3px;">치팅데이 등록 음식점</h3>
   </div>
	<div id="ta">
		<div>
			<c:forEach items="${store.mainlist}" var="store">
			<hr>
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