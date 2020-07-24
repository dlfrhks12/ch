<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/cheatingday/ckeditor/ckeditor.js"></script>
<script>
$(function() {
	   $("#1star").on("click",function(){
	      $("#star1").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star2").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#star3").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#star4").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#star5").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#rScore2").val(1);
	   })
	   $("#2star").on("click",function(){
	      $("#star1").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star2").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star3").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#star4").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#star5").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#rScore2").val(2);
	   })
	   $("#3star").on("click",function(){
	      $("#star1").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star2").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star3").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star4").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#star5").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#rScore2").val(3);
	   })
	   $("#4star").on("click",function(){
	      $("#star1").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star2").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star3").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star4").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star5").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg");
	      $("#rScore2").val(4);
	   })
	   $("#5star").on("click",function(){
	      $("#star1").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star2").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star3").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star4").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#star5").attr("src","https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-full.svg");
	      $("#rScore2").val(5);
	   })
	})
	$(function(){
		var ck = CKEDITOR.replace("rContent",{
			filebrowserUploadUrl:"http://localhost:8081/cheatingday/review/ckupload"
		});
		var storecode = "${orderNo}"
		console.log(storecode);
		$("#write").on("click",function(){
			var params = {
				rTitle : $("#rTitle").val(),
				orderNo : storecode,
				rContent : CKEDITOR.instances['rContent'].getData(),
				rStarPoint: $("#rScore2").val(),
				_csrf:"${_csrf.token}" 
			}
			console.log(params);
			$.ajax({
				url:"/cheatingday/review/write",
				method:"post",
				data:params
			}).done((result)=>{location.href ="/cheatingday/review/list"}).fail((result)=>{console.log(result)});
		})
	
	})
	
</script>
<style>

#writeForm{
		font-size: large;
		font-weight: bold;
		margin:0 auto;
		border: solid 1px gray;
		height:900px;
		border-radius: 35px;
		width:1200px;
	}
	#title_div{
		margin: 50px 80px 50px 200px;
		width:800px;
		border-color: navy;
		margin-bottom: 20px;
	}
	#rTitle{
		border: solid 2px gray;
	}
	#rStarPoint{
		display: inline-block;
		text-align: center;
		margin: 30px 500px 20px 200px;
	}
	#cke_rContent{
	 	width: 800px;
	 	margin: 0 auto;
	 	heigh: 800px;
	 	
	 }
	 #add{
	 	margin-left: 800px;
	 }
	 #write{
	 	margin-left: 850px;
	 }
</style>
</head>
<body>
	<form action = "/cheatingday/review/write" method = "post" id = "writeForm">
		<div class = "form-group" id = "title_div">
			<label for = "rTitle"></label>
			<input type = "text" class = "form-control" id = "rTitle" placeholder = "제목을 입력하세요" name = "rTitle">
                        <div id = "star">
                           <input type="hidden" name="rStarPoint" id="rScore2" value="">
                           <a id="1star"><img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg" id="star1"></a>
                           <a id="2star"><img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg" id="star2"></a>
                           <a id="3star"><img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg" id="star3"></a>
                           <a id="4star"><img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg" id="star4"></a>
                           <a id="5star"><img src="https://assets.cdn.soomgo.com/icons/icon-common-review-star-small-empty.svg" id="star5"></a>
                        </div>
		</div>
		<div class = "form-group">
			<textarea class = "form-control" id = "rContent" name = "rContent"></textarea>
		</div>
		<button type = "button" class = "btn btn-outline-danger" id = "write">WRITE</button>
	</form>
</body>
</html>