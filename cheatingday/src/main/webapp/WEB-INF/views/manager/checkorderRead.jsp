<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

section {margin-left: 400px; margin-right: 400px;}
#ok { margin-left: 700px; margin-top: 30px;}
#no{ margin-top: 30px;}
</style>

</head>
<body>
	<div>
	 <div style="padding: 40px 0;">
      <h3><i class="fas fa-shopping-cart">&nbsp;주문내역</i></h3>
      <br>
      
		<table class="table table-hover">
			<thead>
				<tr class="table-danger">
					<th>주문번호</th>
					<th>메뉴번호</th>
					<th>메뉴이름</th>
					<th>메뉴금액</th>
					<th>메뉴수량</th>
					
				</tr>
			</thead> 
			<tbody id="list">
			<c:forEach items="${orderRead}" var="order">
				<tr>
					<td id="order">${order.orderNo}</td>
					<td>${order.menuno} 번</td>
					<td>${order.CartName}</td>
					<td>${order.CartPrice} 원</td>
					<td>${order.CartCount} 개</td>
				</tr>
			</c:forEach> 
			</tbody>
		</table>
		
	</div>
	</div>
</body>
</html>