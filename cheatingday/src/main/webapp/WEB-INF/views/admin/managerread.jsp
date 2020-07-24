<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(function(){
	//관리자 승인해주기
	$("#enabled").on("click", function() {
		var params = {
			mNum : $("#mNum").val(),
			_csrf: "${_csrf.token}"
		}
		console.log(params);
		$.ajax({
			url: "/cheatingday/admin/manager_unblock",
			method: "post",
			data: params
		})
		.done((result)=>{ alert("승인하였습니다"); location.href="/cheatingday/admin/manager_list"; })
		.fail((result)=>{console.log(result)});
	});
})

</script>
</head>
<body>
<div>
	<h1>사업자 정보</h1>
	<table class="table table-hover" id="manager">
		<tr>
			<td class="first">사업자등록번호 <input type="hidden" id="mNum" value="${manager.MNum}"> </td>
			<td colspan="2"><span id="mnum">${manager.MNum}</span> </td>
		</tr>
		<tr>
			<td class="first">아이디</td><td colspan="2"><span id="mUsername">${manager.MUsername}</span></td>
		</tr>
		<tr>
			<td class="first">이름</td><td colspan="2"><span id="mIrum">${manager.MIrum}</span></td>
		</tr>
		<tr>
			<td class="first">이메일</td><td colspan="2"><span id="mEmail">${manager.MEmail}</span></td>
		</tr>
		<tr>
			<td class="first">계좌</td><td colspan="2"><span id="mAccount">${manager.MAccount}</span></td>
		</tr>
		<tr>
			<td class="first">전화번호</td><td colspan="2"><span id="mTel">${manager.MTel}</span></td>
		</tr>
	</table>
	<button type="button" class="btn btn-success" id="enabled">가입승인</button>
</div>
</body>
</html>