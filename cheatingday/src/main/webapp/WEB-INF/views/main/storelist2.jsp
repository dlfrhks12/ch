<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<form name="form1" method="post" action="${path}/store/list_bystar">
		<select name="searchOption">
			<!-- 검색조건을 검색처리 후 결과화면에 보여주기 위해 c:out 출력태그 사용, 삼항연산자 -->
			<option value="sStarPoint" <c:out value="${map.searchOption == 'sStarPoint'?'selected':''}"/>>별점순</option>
			<option value="sReviewCnt" <c:out value="${map.searchOption == 'sReviewCnt'?'selected':''}"/>>리뷰많은순</option>
		</select>
		<input name="keyword" value="${map.keyword }>">
	</form>
</div>
<div id="page-wrapper">
	<div id="page" class="container">
		<div id="content">
			<header>${map.count}개의 가게가 검색되었습니다.</header>
			<div id="two-column">
				<div id="tbox1">
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
				</div>
				<div id="tbox2">
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
					<div class="box-style"> <a href="/cheatingday/manager/store_read?sNum=${store.SNum}"><img src="images/screenshot01.jpg" width="140" height="105" alt="" /></a>
						<h3>${store.sName}</h3>
						<a>${store.sStarPoint} | ${store.sReviewCnt}</a>
						<a>${store.SAddress}</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>