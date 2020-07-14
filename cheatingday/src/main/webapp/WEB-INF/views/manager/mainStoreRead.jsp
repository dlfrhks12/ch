<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div>
	 <img id="show_storesajin" height="200px;" src="${storeRead.SSajin}">
	</div>
	<div>
		<input type="file" name="sajin" id="sajin">
	</div>
	
	<div>
	<!--음식점 고유번호 :<input type="text" name="SNum" id="SNum" value="${storeRead.SNum}">-->
	 매장 고유번호: <span id="SNum">${storeRead.SNum}</span>
	</div>
	
	<div>
	매장정보: <input type="text" name="SInfo" id="SInfo" value="${storeRead.SInfo}">
	</div>
	
	<div>
	상호명: <input type="text" name="SName" id="SName" value="${storeRead.SName}">
	</div>
	
	<div>
	매장 전화번호: <input type="text" name="STel" id="STel" value="${storeRead.STel}">
	</div>
	
	<div>
	주소: <!--  <input type="text" name="SAddress" id="SAddress" value="${storeRead.SAddress}">-->
		 <input name="sAddress" id="zonecode" type="text"  style="width: 50px;" readonly />&nbsp;
		 <button type="button"  class="btn btn-danger" onClick="openDaumZipAddress();" >주소찾기</button> <br /> 
         <input name="sAddress" type="text" id="address"  style="width: 240px;" readonly /> 
         <input name="sAddress" type="text" id="address_etc"  style="width: 200px;" />
	</div>
	
	<!-- 음식점 카테고리번호에 해당하는 내용을 읽어와야해  -->
	<div>
	<!-- 카테고리 : <input type="text" name="foodcategory" id="foodcategory" value="${storeRead.foodCategory}">  -->
	카테고리:<span id="foodcategory">${storeRead.foodCategory}</span>
	</div> 
	
	<div>
	리뷰수:<span id="SReviewCnt">${storeRead.SReviewCnt}</span>
	</div>
	
	<div>
	별점평균:<span id="SStarPoint">${storeRead.SStarPoint}</span>
	</div>
	
</body>
</html>