<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<title>음식점 정보</title>
<script>
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

	//2. 음식점 수정하기

//제이쿼리 문법 $(function(){ })	
$(function(){
//1. 음식점사진 올리기
$("#SSajin").on("change",loadImage);

 //2.음식점 수정하기
$("#update").on("click",function(){
	
	var formData = new FormData();
	formData.append("SNum", ${storeRead.SNum});
	formData.append("SInfo", $("#SInfo").val());
	formData.append("SName", $("#SName").val());
	formData.append("STel", $("#STel").val());
	formData.append("SAddress", $("#SAddress").val());
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
 		.fail(()=>{console.log("메뉴삭제를 실패했습니다.")})
 	});
 
 //4.메뉴전체삭제
 	$("#menu_delete").on("click"),function(){
	 	
 }

}); 
 

</script>
</head>
<body>


  <div>

	 <img id="show_storesajin" height="200px;" src="${storeRead.SSajin}">
	</div>
	<div>
		<input type="file" name="sajin" id="sajin">
	</div>
	
	<div>
	**음식점고유번호는 변경이 불가함<br>
	 음식점 고유번호 :<input type="text" name="SNum" id="SNum" value="${storeRead.SNum}">
	</div>
	
	<div>
	매장정보: <input type="text" name="SInfo" id="SInfo" value="${storeRead.SInfo}">
	</div>
	
	<div>
	상호명: <input type="text" name="SName" id="SName" value="${storeRead.SName}">
	</div>
	
	<div>
	음식점 전화번호: <input type="text" name="STel" id="STel" value="${storeRead.STel}">
	</div>
	
	<div>
	주소: <input type="text" name="SAddress" id="SAddress" value="${storeRead.SAddress}">
	</div>
	
	<!-- 음식점 카테고리번호에 해당하는 내용을 읽어와야해  -->
	<div>
	**카테고리도 변경이 불가함<br>
	카테고리 : <input type="text" name="foodcategory" id="foodcategory" value="${storeRead.foodCategory}">
	</div> 
	
	<button type="button" class="btn btn-success" id="update">변경하기</button>
	<button type="button" class="btn btn-success" id="menu_delete">메뉴 전체 삭제하기</button>
	<button type="button" class="btn btn-success" id="delete">매장 삭제하기</button>  
</body>
</body>
</html>