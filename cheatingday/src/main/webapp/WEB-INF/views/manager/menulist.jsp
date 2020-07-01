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
<meta charset="UTF-8">
<title>메뉴리스트</title>
<sec:authorize access="isAuthenticated()">
	<script>
		var isLogin = true;
		var loginId =  "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}"
	</script>
</sec:authorize>
<sec:authorize access="isAnonymous()">
	<script>
		var isLogin = true;
		var loginId = undefined;
	</script>
</sec:authorize>
<script>
 	//1. 메뉴사진 읽기
	function loadImage(){
		var file = $("#menusajin")[0].files[0];
		var maxSize = 1024*1024;
		if(file.size>maxSize){
			Swal.fire({
				icon: 'error',
				title: '사진크기 오류',
				text: '사진크기는 1MB를 넘을 수 없습니다'
			});
			$("#menusajin").val("");
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
	
	
	
	//3. 메뉴 삭제하기- 첨부파일 삭제처럼 하나씩    <!--이렇게하는게 맞는건가?-->
	function deleteMenu(menus){
		var $ul = $("#menus");
		$ul.empty();
		$.each(menus, function(i, menu){
			var $li = $("<li>").appendTo($ul);
		
			var str = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			str+="<span class='delete_menuDelete' data-menuno='";
			str+=menu.menuno;
			str+="' >X</span>";
			
			$li.append(str);
			$(".delete_menuDelete").css("cursor","pointer").attr("title", "메뉴삭제하기");
			
			}
		};
	}
	          
$(function(){
	
	//1. 메뉴사진 올리기
	$("#menusajin").on("change",loadImage);
	
	//2. 메뉴(이미지포함) 수정하기-이름,가격,사진
	$("#update").on("click",function(){
		var formData = new FormData();
		formData.append("menuname", $("#menuname").val());
		formData.append("menusal", $("menusal").val());
		formData.append("menusajin",$("menusajin").val());
		formData.append("_method","patch");
		
		$.ajax({
			url: "/cheatingday/manager/menu_update",
			data: formData,
			method: "post",
			processData: false,
			contentType: false
		}).done(()=>{location.reload();})
		.fail(()=>{console.log(result)});
	});
	
	//3. 메뉴삭제 - 댓글삭제처럼 하나씩
	$("#delete").on("click",function(){
		var params ={
				menuno: menu.menuno,
				_method: "delete"
		}
		$.ajax({
			url: "/cheatingday/manager/menu_delete",
			method: "post",
			data: params
		}).done(()=>{location.reload();})
		.fail(()=>{console.log(result)});
		
	});
});
 
</script>
</head>
<body>
	<div>
		<table class="table table-hover">
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="30%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>메뉴번호</th>
					<th>메뉴이름</th>
					<th>메뉴가격</th>
					<th>메뉴사진</th>
					<th>음식점고유번호</th><!-- 음식점 고유번호는 jsp에 없어도 될거같은데?  -->
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${menuList}" var="menu">
				<tr>
					<td>
						<input type="text" name="menuno" id="menuno" value="${menu.menuno}"></td>
					<td><input type="text" name="menuname" id="menuname"value="${menu.menuname}"></td>
					<td>
						<input type="text" name="menusal" id="menusal" value="${menu.menusal}"></td>
				<!-- 메뉴사진 출력  -->
					 <td><img id="show_menusajin" height="200px" src="${menu.menusajin}">
					 	<input type="file" name="menusajin" id="menusajin"></td>
					<td>${menu.SNum}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="form-group">
	<a href="/cheatingday/menu_write">
		<button type="button" id="write" class="btn btn-info">메뉴추가</button></a>
	<a href="/cheatingday/menu_update">
		<button type="button" id="update" class="btn btn-info">메뉴수정하기</button></a>
	<a href="/cheatingday/menu_delete">
		<button type="button" id="delete" class="btn btn-info">메뉴삭제하기</button></a>
	</div>
	
	<div id="menus"></div>
</body>
</html>