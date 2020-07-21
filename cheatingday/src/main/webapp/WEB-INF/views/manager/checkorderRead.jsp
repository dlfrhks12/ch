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

<script>
	var oNo = "${oNo}"
$(function(){
	$("#ok").on("click",function(){
		
		var params={
				_method:"patch",
				oNo:oNo
		}
		
		console.log(params)
		
		$.ajax({
			data: params,
			url: "/cheatingday/manager/order_update",
			method: "post",
			success: function() {
				Swal.fire({
					icon: 'success',
					title: 'success',
					text: '주문이 승인되었습니다',
					})
				}
		})
	})

	$("#no").on("click",function(){
		
		var params={
				_method:"delete",
				oNo: oNo
		}
		console.log(params);
		
		$.ajax({
			data: params,
			url:"/cheatingday/manager/order_delete",
			method:"post",
			success: function(){
				Swal.fire({
					icon: 'error',
					title: 'delete',
					text: '주문을 거절하여 삭제되었습니다',
					})
			}
		})
		
	})
	
	
})

</script>
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
					<td id="order">${order.ONo}</td>
					<td>${order.menuno}</td>
					<td>${order.DMenuName}</td>
					<td>${order.DSal} 원</td>
					<td>${order.DMenuCnt} 개</td>
				</tr>
			</c:forEach> 
			</tbody>
		</table>
		<button type="button"  class="btn btn-danger" id="ok">주문승인</button>
		<button type="button"  class="btn btn-danger" id="no">주문거절</button>
	</div>
	</div>
</body>
</html>