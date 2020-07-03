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
<title>메뉴읽기</title>

<script>
	//1. 메뉴사진 읽기
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
		$("#show_menusajin").attr("src", e.target.result);
	}
	reader.readAsDataURL(file);
	return true;
}

	//2. 메뉴 수정하기

//제이쿼리 문법 $(function(){ })	
$(function(){
//1. 메뉴사진 올리기
$("#menusajin").on("change",loadImage);

 //2. 메뉴(이미지포함) 수정하기-이름,가격,사진
$("#update").on("click",function(){
	
	var formData = new FormData();
	formData.append("menuno", $("#menuno").val());
	formData.append("menuname", $("#menuname").val());
	formData.append("menusal", $("#menusal").val());
	formData.append("_csrf", "${_csrf.token}");
	formData.append("_method","patch");

	if($("#sajin")[0].files[0]!=undefined)
		formData.append("sajin",$("#sajin")[0].files[0]);
	
	
	$.ajax({
		url: "/cheatingday/manager/menu_update",
		data: formData,
		method: "post",
		processData: false,
		contentType: false
	}).done(()=>{location.reload();})
	.fail(()=>{console.log("메뉴수정을 실패했습니다.")});
});

 
 //3.메뉴삭제
 	$("#delete").on("click",function(){
 		var params ={
 				menuno: ${menuRead.menuno},
 				_csrf: "${_csrf.token}",
 				_method: "delete"
 		}
 		
 		$.ajax({
 			url: "/cheatingday/manager/menu_delete",
 			method: "post",
 			data: params
 		}).done(()=> {location.href="/cheatingday/manager/menu_list"})
 		.fail(()=>{console.log("메뉴삭제를 실패했습니다.")})
 	});

}); 
 
</script>
</head>
<body>
 <div>
 
		<img id="show_menusajin" height="200px;" src="${menuRead.menusajin}">
	</div>
	<div>
		<input type="file" name="sajin" id="sajin">
	</div>
	
	<div>
	 메뉴번호 :<input type="text" name="menuno" id="menuno" value="${menuRead.menuno}">
	</div>
	
	<div>
	메뉴이름: <input type="text" name="menuname" id="menuname" value="${menuRead.menuname}">
	</div>
	
	<div>
	메뉴가격: <input type="text" name="menusal" id="menusal" value="${menuRead.menusal}">
	</div>
	
	<button type="button" class="btn btn-success" id="update">변경하기</button>
	<button type="button" class="btn btn-success" id="delete">삭제하기</button> 
</body>
</html>