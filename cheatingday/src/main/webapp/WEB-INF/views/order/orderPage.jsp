<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div, span {
		display: inline-block;
	}
	span {
		width: 175px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
////////////////////////////////////////////////////예지///////////////////////////////////////////
//메뉴사진 읽기
function loadImage(){
	
	var file = $("#sajin")[0].files[0];
	
	//이미지파일 화면에 출력
	var reader = new FileReader();
	reader.onload = function(e){
		$("#show_menusajin").attr("src", e.target.result);
	}
	reader.readAsDataURL(file);
	return true;
	
}


//다음 지도API
function openDaumZipAddress() {
	new daum.Postcode({
		oncomplete:function(data) {
			jQuery("#zonecode").val(data.zonecode);
			jQuery("#address").val(data.address);
			jQuery("#address_etc").focus();
			console.log(data);
		}
		
	}).open();
}

$(function(){
	//5.주소API ,로 잘라 보이기
 	var beforejusoStr = "${storeRead.SAddress}";
 	var afterjusoStr = beforejusoStr.split(",");
 	
 	$("#zonecode").val(afterjusoStr[0]);
 	$("#address").val(afterjusoStr[1]);
 	$("#address_etc").val(afterjusoStr[2]);
 	
///////////////////////////////////////////////////////////예지///////////////////////////////////////////////////

///////////////////////////////////////////////////일관///////////////////////////////

	// 3. 장바구니에 추가
	$(".cart").on("click", function() {
		var params = {
			menuno: $(this).attr("data-menuno"),
			_csrf: "${_csrf.token}"
		}
		$.ajax({ 
			url: "/cheatingday/cart/add",
			method: "post",
			data: params,
		}).done(()=>{
			var choice = confirm("상품을 장바구니에 담았습니다. 장바구니로 이동하시겠습니까?");
			if(choice==true)
				location.href = "/cheatingday/cart/cartview"
		}).fail((xhr)=>{
			alert(xhr.responseText);
		})
	});
 	
})

</script>
</head>
<body>
<div id="storeInfo">
	 <div>
	 <img id="show_storesajin" height="200px;" src="${storeRead.SSajin}">
	</div>
	
	
	<div>
	매장정보: <span id="SInfo">${storeRead.SInfo}</span>
	</div>
	
	<div>
	상호명: <span id="SName">${storeRead.SName}</span>
	</div>
	
	<div>
	매장 전화번호: <span id="STel">${storeRead.STel}</span>
	</div>
	
	<div>
         주소: <span>${storeRead.SAddress}</span>
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
</div>

<div id="menuInfo">
	<%-- <c:forEach items="${menuRead}"  var="menu">
		<div id="menusajin">
			<img id="show_menusajin" height="200px;" src="${menu.menusajin}">
		</div>
		<div id="menuname">
			${menu.menuname}
		</div>
		<div id="menusal">
			${menu.menusal}
		</div>	
	</c:forEach> --%>
	
	<a href="/cheatingday/cart/cartview">장바구니로</a><br>
	<c:forEach items="${cartlist}" var="product" varStatus="status">
		<div style="width: 176px; margin-right: 25px;">
			<img src="${product.menusajin}" width="175px">
			<div>
				<span>${product.menusal}원</span>
				<!--  <span style="font-size: 0.75em;">1개당 ${product.menusal/product.mcount}원</span>-->
				<span style="font-size: 0.75em;">${product.menuname}</span>
			</div>
			<div>
				<button class="cart" data-menuno="${product.menuno}">장바구니 담기</button>
			</div>
		</div>
		<%-- <c:if test="${status.MCount ==5}">
			<hr>
		</c:if --%>
	</c:forEach>
	
	

</div>
</body>
</html>