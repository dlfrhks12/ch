<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<title>음식점 등록</title>
<script type="text/JavaScript"
	src="http://code.jquery.com/jquery-1.7.min.js"></script>

<script type="text/JavaScript"
	src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	function openDaumZipAddress() {

		new daum.Postcode({

			oncomplete : function(data) {

				

				jQuery("#zonecode").val(data.zonecode);

				jQuery("#address").val(data.address);

				jQuery("#address_etc").focus();

				console.log(data);

			}

		}).open();

	}

	//1.이미지 출력입니다
	function loadImage() {
		var file = $("#sajin")[0].files[0];
		var maxSize = 1024 * 1024;
		if (File.size > maxSize) {
			Swal.fire({
				icon : 'error',
				title : '크기오류',
				text : '사진크기는 1MB를 넘을 수 없습니다'
			});
			$("#sajin").val("");
			return false;
		}
		//하드디스크에 있는 이미지 파일을 로딩해 화면에 출력하는 코드
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#show_menusajin").attr("src", e.target.result);
		}
		reader.readAsDataURL(file);
		return true;
	}

	$(function() {
		$("#sajin").on("change", loadImage);
		$("#write").on("click", function() {
			$("#write_form").submit();
		});
	});
</script>
</head>
<body>

	<div id="wrap">
		<form id="write_form" action="/cheatingday/manager/store_insert"
			method="post" enctype="multipart/form-data">
			<img id="show_storesajin" height="240px"> <input type="hidden"
				name="_csrf" value="${_csrf.token}">
			<div class="form-group">
				<label for="storesajin">음식점 사진</label> <input id="sajin" type="file"
					name="sajin" class="form-control"
					accept=".jpg,.jpeg,.png,.gif,.bmp">
			</div>
			<div>
				<label for="sno__label">음식점 고유번호</label>
				<div class="form-group">
					<input type="text" class="form-control" id="sNum" name="sNum"
						placeholder="음식점 고유번호를 입력해주세요">
				</div>
			</div>
			<div>
				<label for="sname_label">상호명</label>
				<div class="form-group">
					<input type="text" class="form-control" id="sName" name="sName"
						placeholder="상호명을 입력해주세요">
				</div>

			</div>
			<div>
				<label for="stel_label">음식점 전화번호</label>
				<div class="form-group">
					<input id="sTel" type="text" class="form-control" name="sTel"
						placeholder="음식점 전화번호를  입력해주세요">
				</div>
			</div>
			<div>
				<label for="sAddress_label">음식점 주소</label>
				<div class="form-group">
					<input id="sAddress" type="text" class="form-control" name="sAddress">
				</div> 
				<!-- <div>
				<input
					id="zonecode" type="text" value="" style="width: 50px;" readonly />

				&nbsp; <input type="button" onClick="openDaumZipAddress();"
					value="주소 찾기" /> <br /> <input type="text" id="address" value=""
					style="width: 240px;" readonly /> <input type="text"
					id="address_etc" value="나머지주소입력" style="width: 200px;" />
					</div> -->
			</div>
			<div>
				<label for="sInfo_label">음식점 소개</label>
				<div class="form-group">
					<input id="sInfo" type="text" class="form-control" name="sInfo">
				</div>
			</div>
			<div>
				<label for="foodNo_label">음식 카테고리</label>
				<td><select id="foodNo" class="form-control" name="foodNo">
						<option value="1">분식</option>
						<option value="2">중식</option>
						<option value="3">치킨</option>
						<option value="4">한식</option>
						<option value="5">족발보쌈</option>
						<option value="6">피자양식</option>
						<option value="7">일식돈까스</option>
						<option value="8">카페디저트</option>
						<option value="9">프랜차이즈</option>
				</select></td>
			</div>
			<div>
				<label for="stel_label">사업자아이디</label>
				<div class="form-group">
					<input id="mUsername" type="text" class="form-control" name="mUsername"
						placeholder="사업자아이디를 입력하세요">
				</div>
			</div>
			<div class="form-group" style="text-align: center;">
				<button type="button" id="write" class="btn btn-info">음식점
					추가</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</div>

		</form>
	</div>

</body>
</html>