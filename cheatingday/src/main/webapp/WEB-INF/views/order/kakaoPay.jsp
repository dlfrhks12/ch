<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
    <script>
//     var cartview = "${cartview}";
    var cartEntity = JSON.parse('${cartview}');
   
   console.log(cartEntity);
   console.log(cartEntity[0].cartPrice);
   console.log(cartEntity[1].cartPrice);
   console.log(cartEntity[1].cartJumunMoney);
  </script>
</body>
</html>