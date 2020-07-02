<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
//1. 이미지 출력입니다
function loadImage(){
	var file = $("#sajin")[0].files[0];
	var maxSize = 1024*1024;
	if(File.size>maxSize){
		Swal.fire({
			icon: 'error',
			title: '크기오류',
			text: '사진크기는 1MB를 넘을 수 없습니다'
		});
		$("#sajin").val("");
		return false;
	}
//하드디스크에 있는 이미지 파일을 로딩해 화면에 출력하는 코드
	var reader = new FileReader();
	reader.onload = function(e){
		$("#show_menusajin").attr("src",e.target.result);
	}
	reader.readAsDataURL(file);
	return true;
}

$(function(){
	$("#sajin").on("change", loadImage);
	
	$("#write").on("click",function(){
		
		$("#write_form").submit();
		
	});
});
</script>
</head>
<body>
<!--user- 회원가입 같이 만들음 > 사진도 추가해줘야해서 -->

<div id="wrap">
		<form id="write_form" action="/cheatingday/manager/menu_write" method="post" enctype="multipart/form-data">
			<img id="show_menusajin" height="240px">
			<input type="hidden" name="_csrf" value="${_csrf.token }">
			<div class="form-group">
				<label for="menusajin">메뉴 사진</label>
				<input id="sajin" type="file" name="sajin" class="form-control"  accept=".jpg,.jpeg,.png,.gif,.bmp">
			</div>
			<div>
				<label for="menuno_label">메뉴번호</label>
				<div class="form-group">
					<input type="text" class="form-control" id="menuno" name="menuno">
				</div>
			</div>
			<div>
				<label for="menuname_label">메뉴이름</label>
				<div class="form-group">
					<input type="text" class="form-control" id="menuname" name="menuname">
				</div>
			</div>
			<div>
				<label for="menusal_label">메뉴가격</label>
				<div class="form-group">
					<input id="menusal" type="text" class="form-control" name="menusal">
				</div>
			</div>
			<div>
				<label for="sNum_label">음식점고유번호</label>
				<div class="form-group">
					<input id="sNum" type="text" class="form-control" name="sNum">
				</div>
			</div>
			<div class="form-group" style="text-align: center;">
				<button type="button" id="write" class="btn btn-info">메뉴추가</button>&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</form>
	</div>

</body>
</html>