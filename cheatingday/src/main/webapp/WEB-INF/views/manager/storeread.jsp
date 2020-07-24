<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<title>음식점 정보</title>
<style>

section { margin-left: 600px; margin-bottom: 150px;}
#update {float:left; margin-top: 20px; font-size: 17px;}
#delete { margin-top: 20px; float: right; font-size: 17px;}
textarea {width:370px; height: 200px;   overflow:scroll;}
#all{
	width: 500px; 
}
#tx{
	margin-left: 65px;
	font-size: 17px;
}

</style>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
<script>
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

//1. 음식점사진 읽기
function loadImage(){
	var file = $("#sajin")[0].files[0];
	var maxSize = 1024*1024;
	//이거 사용하려면 sweetalert 링크걸어줘야해
	 if(file.size>maxSize){
		Swal.fire({
			icon: 'error',
			title: '사진크기 오류',
			text: '사진크기는 1MB를 넘을 수 없습니다'
		});
		$("#sajin").val("");
		return false;
	} 
	
	// 이하 하드디스크에 있는 이미지 파일을 로딩해 화면에 출력해요
	var reader = new FileReader();
	reader.onload = function(e){
		$("#show_storesajin").attr("src", e.target.result);
	}
	reader.readAsDataURL(file);
	return true;
}


//제이쿼리 문법 $(function(){ })	
$(function(){
//1. 음식점사진 올리기
$("#SSajin").on("change",loadImage);

 
 //3.음식점삭제
 	$("#delete").on("click",function(){
 		var params ={
 				sNum: ${storeRead.SNum},
 				_csrf: "${_csrf.token}",
 				_method: "delete"
 		}
 		
 		$.ajax({
 			url: "/cheatingday/manager/store_delete",
 			method: "post",
 			data: params
 		}).done(()=> {location.href="/cheatingday/manager/store_list"})
 		.fail(()=>{console.log("음식점 삭제를 실패했습니다.")})
 	});
 

 
 //5.주소API ,로 잘라 보이기
 	var beforejusoStr = "${storeRead.SAddress}";
 	var afterjusoStr = beforejusoStr.split(",");
 	
 	$("#zonecode").val(afterjusoStr[0]);
 	$("#address").val(afterjusoStr[1]);
 	$("#address_etc").val(afterjusoStr[2]);
 	
 //	for(var i=0; i<afterjusoStr.length; i++){
 //	console.log(afterjusoStr[i]);
 //	}
 
 	 //2.음식점 수정하기
 	$("#update").on("click",function(){
 		
 		var formData = new FormData();
 		formData.append("SNum", "${storeRead.SNum}");
 		formData.append("SInfo", $("#SInfo").val());
 		formData.append("SName", $("#SName").val());
 		formData.append("STel", $("#STel").val());
 		formData.append("SAddress", $("#zonecode").val());
 		formData.append("SAddress", $("#address").val());
 		formData.append("SAddress", $("#address_etc").val());
 		formData.append("_csrf", "${_csrf.token}");
 		formData.append("_method","patch");	
 		
 		 if($("#sajin")[0].files[0]!=undefined)
 			formData.append("sajin",$("#sajin")[0].files[0]);
 		 
 		 for(var key of formData.keys())
 		      console.log(key);
 		   for(var value of formData.values())
 		      console.log(value);	
 		
 		$.ajax({ 
 			url: "/cheatingday/manager/store_update",
 			data: formData,
 			method: "post",
 			processData: false,
 			contentType: false
 		}).done(()=>{location.reload();})
 		.fail(()=>{console.log("음식점 수정을 실패했습니다.")}); 
 	});
 
}); 
 

</script>
</head>
<body>
<div id="all">
<div class='card'>
  <div style="margin-left: 65px; margin-top: 30px; margin-bottom: 10px;">
	 <img id="show_storesajin"  height="300px;" width="370px;" src="${storeRead.SSajin}">
	</div>
<div id="tx">
	<div>
		<input type="file" name="sajin" id="sajin">
	</div><br>
	
	<div>
	<!--음식점 고유번호 :<input type="text" name="SNum" id="SNum" value="${storeRead.SNum}">-->
	 매장 고유번호 : <span id="SNum">${storeRead.SNum}번</span>
	</div><br>
	
	
	
	<div>
	상호명 : <input type="text" name="SName" id="SName" value="${storeRead.SName}">
	</div><br>
	
	<div>
	매장 전화번호 : <input type="text" name="STel" id="STel" value="${storeRead.STel}">
	</div><br>
	
	<div style="margin-bottom: 5px;">주소검색</div>
	<div>
	 <!--  <input type="text" name="SAddress" id="SAddress" value="${storeRead.SAddress}">-->
		 <input name="sAddress" id="zonecode" type="text"  style="width: 70px;" readonly />&nbsp;
		 <button type="button" style="height: 30px; margin-bottom: 5px;" class="btn btn-danger" onClick="openDaumZipAddress();" >주소찾기</button><br>
         <input name="sAddress" type="text" id="address"  style="width: 280px; margin-top: 10px;" readonly /><br> 
         <input name="sAddress" type="text" id="address_etc"  style="width: 280px; margin-top: 10px;" />
	</div><br>
	
	<!-- 음식점 카테고리번호에 해당하는 내용을 읽어와야해  -->
	<div>
	<!-- 카테고리 : <input type="text" name="foodcategory" id="foodcategory" value="${storeRead.foodCategory}">  -->
	카테고리 : &nbsp;<span id="foodcategory">${storeRead.foodCategory}</span>
	</div> <br>
	
	<div>
	리뷰수 : &nbsp;<span id="SReviewCnt">${storeRead.SReviewCnt}개</span>
	</div><br>
	
	<div>
	별점평균 :
	<c:forEach begin="1" end="${storeRead.SStarPoint}">
         <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg">
        </c:forEach>
         <c:forEach begin="${storeRead.SStarPoint+1}" end="5">
          <img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg">
    </c:forEach> / ${storeRead.SStarPoint}
	</div><br>
	
	<span id="info" >매장정보</span>
</div>
	<div style=" margin-bottom: 40px; margin-left: 65px; margin-top: 10px;">
	<textarea name="SInfo" id="SInfo" style="font-size: 17px; overflow: hidden;" >${storeRead.SInfo}</textarea>
	</div>
	</div> 
	<button type="button"   class="btn btn-danger" id="update">변경하기</button>
	<button type="button"  class="btn btn-danger" id="delete">삭제하기</button> 
</div>	
</body>
</html>