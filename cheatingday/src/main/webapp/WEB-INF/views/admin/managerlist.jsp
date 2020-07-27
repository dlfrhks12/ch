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
</head>
<body>
<div>
		<span style="font-size: x-large; font-weight: bold; color: red;" >사업자 가입신청 목록</span>
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>아이디</th>
					<th>사업자이름</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>사업자 등록번호</th>
					<th>승인 여부</th>
				</tr>
			</thead>
			<tbody id="list">
				<c:forEach items="${list}" var="manager">
					<tr>
						<td>${manager.MUsername}</td>
						<td><a href="/cheatingday/admin/manager_read?mUsername=${manager.MUsername}" onclick="openWin()">${manager.MIrum}</a></td>
						<td>${manager.MEmail}</td>
						<td>${manager.MTel}</td>
						<td>${manager.MNum}</td>
						<td>${manager.MEnabledStr}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>