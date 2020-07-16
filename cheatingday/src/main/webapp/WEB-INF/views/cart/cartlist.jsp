<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div, span {
		display: inline-block;
	}
	span {
		width: 175px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function() {
	// 3. 장바구니에 추가
	$(".cart").on("click", function() {
		var params = {
			menuno: $(this).attr("data-menuno"),
			_csrf: "${_csrf.token}"
		}
		$.ajax({ 
			url: "/cheatingday/cart/add",
			method: "post",
			data: params,
		}).done(()=>{
			var choice = confirm("상품을 장바구니에 담았습니다. 장바구니로 이동하시겠습니까?");
			if(choice==true)
				location.href = "/cheatingday/cart/cartview"
		}).fail((xhr)=>{
			alert(xhr.responseText);
		})
	});
});
</script>
</head>
<body>
	<a href="/cheatingday/cart/cartview">장바구니로</a><br>
	<c:forEach items="${cartlist}" var="product" varStatus="status">
		<div style="width: 176px; margin-right: 25px;">
			<img src="${product.image}" width="175px">
			<div>
				<span>${product.MPrice }원</span>
				<span style="font-size: 0.75em;">1개당 ${product.MPrice/product.MCount }원</span>
				<span style="font-size: 0.75em;">${product.MName }</span>
			</div>
			<div>
				<button class="cart" data-menuno="${product.menuno}">장바구니 담기</button>
			</div>
		</div>
		<%-- <c:if test="${status.MCount ==5}">
			<hr>
		</c:if --%>
	</c:forEach>
</body>
</html>