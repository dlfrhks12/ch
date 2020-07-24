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
<title>메뉴리스트</title>
<style>

section { margin-left: 450px; margin-right: 450px; font-size: 17px;}
#write {margin-left: 600px;}


</style>
</head>
<body>
<div style="padding: 40px 0;">
      <h3><i class="fas fa-utensils">&nbsp;메뉴 관리</i></h3>
      <br>
<div>
		<table class="table table-hover">
			<colgroup>
				<col width="60%">
				<col width="40%">
			</colgroup>
			<thead>
				<tr class="table-danger">
					<th>메뉴이름</th>
					<th>메뉴가격</th>
				</tr>
			</thead>
			<tbody id="list">
			 <c:forEach items="${menuList}" var="menu">
				<tr>
					<td><a href="/cheatingday/manager/menu_read?menuno=${menu.menuno}">${menu.menuname}</a></td>
					<td>${menu.menusal}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="form-group">
	<a href="/cheatingday/manager/menu_write">
		<button type="button" id="write"  class="btn btn-danger" style="font-size: 17px;">메뉴추가</button></a>
	</div>
	 </div>
</body>
</html>