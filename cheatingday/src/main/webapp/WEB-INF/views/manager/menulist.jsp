<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="40%">
				<col width="10%">
				<col width="30%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>메뉴번호</th>
					<th>메뉴이름</th>
					<th>메뉴가격</th>
					<th>메뉴사진</th>
					<th>음식점고유번호</th>
				</tr>
			</thead>
			<tbody id="list">
			<c:forEach items="${page.list}" var="menu">
				<tr>
					<td>${menu.menuno}</td>
					<td><a href="/cheatingday/manager/menu_read?menuno=${menu.menuno}">${menu.menuname}</a></td>
					<td>${menu.menusal}</td>
					<td>${menu.menusajin}</td>
					<td>${menu.sNum}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="form-group">
	<a href="/cheatingday/menu_write">
		<button type="button" id="write" class="btn btn-info">글쓰기</button></a>
	</div>
</body>
</html>