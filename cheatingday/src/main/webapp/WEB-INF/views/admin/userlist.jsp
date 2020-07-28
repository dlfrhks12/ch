<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(function() {
	// 유저목록 중 선택한 글을 블록	
	$("#block").on("click", function() {
		var ar = "";
		$(".username").each(function(idx) {
			if($(this).prop("checked")) {
				ar = ar + ($(this).val()) + "," ;
			}
		});
		// 11,22,33을 선택한 경우 -> "11,22,33,"
		
		var $form = $("<form>").attr("action","/cheatingday/admin/user_block").attr("method","post");
		$("<input>").attr("type","hidden").attr("name","uUsernames").val(ar).appendTo($form);
		$("<input>").attr("type","hidden").attr("name","_csrf").val("${_csrf.token}").appendTo($form);
		$form.appendTo($("body")).submit();
	})

	// 블록한 유저 중 선택한 글을 블록 해제
	$("#unblock").on("click", function() {
		var ar = [];
		$(".username").each(function(idx) {
			if($(this).prop("checked")) {
				ar.push($(this).val());
			}
		});
		// 자바스크립트 배열을 json으로 변환한 다음 보낸다
		// 11, 22, 33을 선택하면 [11,22,33]이 되고 json으로 변환한다
		
		var $form = $("<form>").attr("action","/cheatingday/admin/user_unblock").attr("method","post");
		$("<input>").attr("type","hidden").attr("name","uUsernames").val(JSON.stringify(ar)).appendTo($form);
		$("<input>").attr("type","hidden").attr("name","_csrf").val("${_csrf.token}").appendTo($form);
		console.log()  
		$form.appendTo($("body")).submit();
	})
})
</script>
</head>
<body>
	<div>
		<span style="font-size: x-large; font-weight: bold; color: red;" >${title}</span>
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="30%">
				<col width="10%">
				<col width="30%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
					<th>가입일</th>
					<th>전화번호</th>
					<th class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> 유저 관리</a>
						<ul class="dropdown-menu">
							<li><a href="/cheatingday/admin/user_list?job=user_list">유저
									목록</a></li>
							<li><a href="/cheatingday/admin/user_list?job=block_list">블록된
									유저</a></li>
						</ul></th>
				</tr>
			</thead>
			<tbody id="list">
				<c:forEach items="${list}" var="user">
					<tr>
						<td>${user.UUsername}</td>
						<td>${user.UIrum}</td>
						<td>${user.UEmail}</td>
						<td>${user.UJoinDateStr}</td>
						<td>${user.UTel}</td>
						<td><input type="checkbox" class="username"
							value="${user.UUsername}"></td>
					</tr>
				</c:forEach>
			</tbody>
		<tfoot>
			<tr>
				<c:choose>
					<c:when test="${title eq '유저 목록'}" >
						<td><button class="btn btn-danger" id="block">블록하기</button></td>
					</c:when>
					<c:otherwise>
						<td><button class="btn btn-info" id="unblock">블록해제</button></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</tfoot>		
		</table>
	</div>
</body>
</html>