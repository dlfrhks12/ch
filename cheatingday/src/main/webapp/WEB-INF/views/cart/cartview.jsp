<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	table {
		width: 800px;
		border-collapse: collapse;
		border: 1px solid lightgray;
	}
	.first { width: 50px; }
	.second { width: 150px;  }
	.third { widht: 300px; font-size: 0.8em; }
	.fourth {width: 150px;}
	.fifth { width: 150px; }
	.button_area a, .button_area span { 
		font-size: 0.8em; text-align: center;
		height: 30px; line-height: 30px;
	}
	.price { padding-left: 15px; }
</style>
<script>
// 장바구니를 저장할 변수
var cartList = undefined;
// 전체 선택버튼을 위한 boolean 변수
var isChoice = false;

// 1-3. 장바구니에 담긴 하나의 상품을 출력
function printCart(CartEntity, dest) {
	// 테이블을 이용해 각 상품을 출력
	// 각 상품마다 1행 5열의  <tr>로 
	var $div = $("<div>").appendTo(dest);
	var $table = $("<table>").appendTo($div);
	var $tr = $("<tr>").appendTo($table);

	// 상품마다 5개의 <td>를 가짐. 순서대로 first, second, third, fourth, fifth 클래스 선택자 지정
	$("<td class='first'>").append($("<input>").attr("type","checkbox").attr("class","select").attr("data-menuno", CartEntity.menuno)).appendTo($tr);
	$("<td class='second'>").append($("<img>").attr("src", CartEntity.image).css("width", "135px")).appendTo($tr);
	$("<td class='third'>").text(CartEntity.cartName).appendTo($tr);

	// 네번째 td에는 <div> 2개를 붙일 것임. 따라서 var $td로 저장
	var $td = $("<td class='fourth'>").appendTo($tr);
	$("<div class='price'>").text(CartEntity.cartJumunMoney + "원").appendTo($td);
	var $div = $("<div class='button_area'>").appendTo($td);
	$("<a href='#'>+</a>").attr("class","inc").attr("data-menuno", CartEntity.menuno).appendTo($div);
	$("<span>").text(CartEntity.cartCount).appendTo($div);
	console.log(CartEntity.cartCount);
	$("<a href='#'>-</a>").attr("class","dec").attr("data-menuno", CartEntity.menuno).appendTo($div);
	// 5번째 td에는 <button> 2개를 붙일 것임. 따라서 var $td로 저장
	var $td = $("<td class='fifth'>").appendTo($tr);
	$("<button>").attr("class","buy").attr("data-menuno", CartEntity.menuno).text("구입").appendTo($td);
	$("<button>").attr("class","delete").attr("data-menuno", CartEntity.menuno).text("삭제").appendTo($td);
} 

//1-2. 장바구니 전체 출력함수 - printCart()를 호출해 각 장바구니를 출력
function printCartList() {
	// 장바구니 출력 영역을 선택한 다음 내용을 제거
	var $cartArea = $("#cart_area");
	$cartArea.empty();
	console.log(cartList);
	// 장바구니 목록이 비어있다면 empty_cart.jpg 출력하고 선택삭제, 주문하기 버튼 영역을 안보이게
	if(cartList.length==0) {
		$("<img>").attr("src","/cheatingday/img/empty_cart.jpg").appendTo($cartArea);
		$("#button_area").hide();
		return;
	} 
	// 장바구니에 담긴 각 상품에 대해 printCart()를 호출해 출력
	$.each(cartList, function(idx, CartEntity) {
		printCart(CartEntity, $cartArea);
	});
}
$(function() {
	// 1-1. 전체선택 체크박스의 기본 상태는 비활성화
	$("#check_all").prop("checked", false);
	$.ajax({
		url: "/cheatingday/cart/read",
		method: "get",
	}).done((result)=>{ 
		cartList = result;
		printCartList();
	})
	
	// 4. <a href='#' class='inc'>+</a> 를 클릭하면 상품 개수 증가
	$("#cart_area").on("click", ".inc", function(e) {
		// <a>태그는 클릭하면 이동한다. 기본동작 이동을 막는다
		e.preventDefault();
			var params = {
				_csrf: "${_csrf.token}",
				_method: "patch",
				menuno: $(this).attr("data-menuno"),
				isIncrease: "1"
			}
			console.log(params);
			return $.ajax({
				url:"/cheatingday/cart/change",
				data: params,
				method: "post"
			}).then((CartEntity)=>{
	            $(this).next().text(CartEntity.cartCount);
	            $(this).parent().prev().text(CartEntity.cartJumunMoney + "원");
	         }).fail(()=>{
	            alert("실패");
	         })
	});
	
	// 5. <a href='#' class='dec'>+</a> 를 클릭하면 상품 개수 감소
	$("#cart_area").on("click", ".dec", function(e) {
		e.preventDefault();

		// + 개수 - 이므로 클릭한 - 앞의 <span>에 개수가 출력되고 있다
		var count = parseInt($(this).prev().text());
		if(count<=1)
			return;
		console.log(count);
		var params = {
			_csrf: "${_csrf.token}}",
			_method: "patch",
			menuno: $(this).attr("data-menuno"),
			isIncrease: "0"
		}
		$.ajax({
			url:"/cheatingday/cart/change",
			data: params,
			method: "post",
		}).then((CartEntity)=>{
            $(this).parent().prev().text(CartEntity.cartJumunMoney + "원")
            $(this).prev().text(CartEntity.cartCount);
         }).fail(()=>{
            alert("실팽");
         })
	})
	
	// 삭제버튼을 클릭하면 해당 상품을 장바구니에서 삭제
	$("#cart_area").on("click", ".delete", function() {
		var params = {
			_csrf: "${_csrf.token}}",
			_method: "delete",
			menuno: $(this).attr("data-menuno")	
		}
		$.ajax({
			url:"/cheatingday/cart/delete",
			data: params,
			method: "post"
		}).done((result)=>{
			cartList = result;
			printCartList();
		})
	})
	
	
	
	// 주문 버튼을 클릭하면 해당 상품을 구입 후 이동
	$("#cart_area").on("click", ".buy", function() {
		var $form = $("<form>").attr("action","/cheatingday/order/buy").attr("method","post");
		$("<input>").attr("type","hidden").attr("name","menuno").val($(this).data("menuno")).appendTo($form);
		var countStr = $(this).parent().prev().children().find("span").text();
		var count = parseInt(countStr);
		$("<input>").attr("type","hidden").attr("name","cartCount").val(cartCount).appendTo($form);
		$("<input>").attr("type","hidden").attr("name","_csrf").val("${_csrf.token}").appendTo($form);
		$form.appendTo($("body")).submit();	
	});

	// 전체 상품 선택/선택해제 toggle
	$("#check_all").on("click", function() {
		isChoice = !isChoice;
		$(".select").prop("checked", isChoice);
	});

	// 선택한 상품삭제. pno의 배열을 만든다음 json 문자열로 변환 후 서버로 보낸다
	$("#delete_all").on("click", function() {
		var ar = [];
		$(".select").each(function(idx) {
			if($(this).prop("checked")) {
				ar.push($(this).data("menuno"));
			}
		});
		var params = {
			_csrf: "${_csrf.token}}",
			_method: "delete",
			pnos: JSON.stringify(ar)
		}
		$.ajax({
			   url:"/cheatingday/cart/delete_all",
			   data: params,
			   method: "post",
			  }).done((result)=>{
			   cartList = result;
			   printCartList();
			  })
	});

	// 선택한 상품 구매. !!!!!!!!!!! 상품번호와 개수로 구성된 자바스크립트 객체를 만들어 배열에 담는다
	$("#buy_all").on("click", function() {
		var ar = [];
		$(".select").each(function(idx) {
			if($(this).prop("checked")) {
				// 체크박스의 부모인 td로 이동
				// td 다음 다음 다음  td로 이동 : <td class='fourth'>
				// 네번째 td의 자식들 중 span 요소를 찾아 가진 문자열을 읽는다
				var countStr = $(this).parent().next().next().next().children().find("span").text();
				var count = parseInt(countStr);
				var obj = {
					menuno : $(this).data("menuno"),
					cartCount : cartCount 
				};
				ar.push(obj);
			}
		});
	
		var $form = $("<form>").attr("action","/cheatingday/order/kakaoPay").attr("method","post");
		$("<input>").attr("type","hidden").attr("name","json").val(JSON.stringify(ar)).appendTo($form);
		$("<input>").attr("type","hidden").attr("name","_csrf").val("${_csrf.token}").appendTo($form);
		$form.appendTo($("body")).submit();
	});
	
	
	
})
</script>
<style>
	a {  color: black; text-decoration: none;}
	a, span { width: 30px; height: 30px; border: 1px solid gray; display: inline-block;}
</style>
</head>
<body>
	<div id="cart_area">
	</div>
	<div id="button_area">
	</div>
	<input type="checkbox" id="check_all">전체 선택 
	<button id="delete_all">선택삭제</button>
	<span id=" totalprice"></span>
	<button type="button" id="buy_all">주문하기</button>
</body>
</html>