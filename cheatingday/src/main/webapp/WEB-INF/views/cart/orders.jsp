<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
$(function() {
	var cartno = $("#orderno").val();
	console.log(cartno);
	$("#orderOpne_bnt").on("click", function() {	
		var params = {
			orderNo: cartno,
			_csrf: "${_csrf.token}"
		}
		console.log(cartno);
			$.ajax({ 
				url: "/cheatingday/cart/finalorder",
				method: "post",
				data: params
			}).done(()=>{
				var choice = confirm("결제가 완료 되었습니다.");
				if(choice==true)
					location.href = "/cheatingday/"
			}).fail((xhr)=>{
				alert(xhr.responseText); 
			})
	});
});

$("#deletes")
</script>
<style>
a:link { text-decoration: none;}
a:visited { color: black; text-decoration: none;}
</style>
<title>Insert title here</title>
</head>
<body>
<hr>
<div class="alldiv">
<div class="wrap1">
<div class ="wrap2">
	<c:set var="sum" value="0" />
	<c:forEach items="${orders}" var="cart" varStatus="status">
			<div style="width: 176px; margin-right: 25px;">
				<div>
					<span style="font-size: 0.75em;">메뉴이름: ${cart.cartName }</span><br>
					<span style="font-size: 0.75em;">수량: ${cart.cartCount}개</span><br>
					<span style="font-size: 0.75em;">가격: ${cart.cartJumunMoney }원</span>
					<input type="hidden" id="orderno" name="orderNo" value="${cart.orderNo }">
				</div>
			</div>
			<hr>
			<c:set var="sum" value="${sum + (cart.cartJumunMoney)}" />
		</c:forEach>
		</div>
		</div>
	
		<div class="listResult">
			<div class="sum">
				총 합계 :
				<fmt:formatNumber pattern="###,###,###" value="${sum}" />
				원
				<button type="button" class="btn btn-success" id="orderOpne_bnt" >결제하기</button>
				<a href="/cheatingday/"><button type="button" class="btn btn-success">취소</button></a>
			</div>
		</div>
		</div>
</body>
</html>