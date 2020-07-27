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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/cheatingday/css/read.css">
<sec:authorize access="isAuthenticated()">
<script>
		var isLogin = true;
		var loginId = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}"
	</script>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
	<script>
		var isLogin = false;
		var loginId = undefined;
	</script>
</sec:authorize>
<script>

	var review = undefined;
	function printReview(){
		var com = review.comments;
		console.log(review);
		$("#rTitle").val(review.rtitle);
		$("#rNo").text(review.rno);
		$("#write_time").text(review.rwriteTimeStr);
		$("#rStarPoint").text(review.rstarPoint);
		if(isLogin===true && review.uusername===loginId)
			ck = CKEDITOR.replace("rContent",{
				filebrowserUploadUrl:"http://localhost:8081/cheatingday/review/ckupload"
			})
		$("#rContent").html(review.rcontent).css("height","500px").css("overflow","scroll");
		$("#btn_area").hide();
		if(isLogin===true&& review.uusername===loginId){
			$("#rTitle").prop("disabled",false);
			$("#btn_area").show();
			$("#rStarPoint").prop("disabled",false);
		}else if(isLogin===true && review.uusername!==loginId){
			$("#rStarPoint").prop("disabled",false);
			$("#comment_textarea").prop("disabled",false);
			$("#commnet_write").prop("disabled",false);
		}
		if(com.length>=1)
			$("#comment_div").hide();
	}
		function printComment(reviewComment){
			console.log(reviewComment);
			var $comments = $("#comments");
			$comments.empty();
			$.each(reviewComment,function(i,comment){
				var $comment = $("<div>").appendTo($comments);
				var $upper_div = $("<div>").appendTo($comment);
				var $center_div = $("<div>").appendTo($comment);
				var $lower_div = $("<div>").appendTo($comment);
				$("<span></span><br>").text("사장님").appendTo($upper_div);
				$("<span>").text(comment.rcDateTime).appendTo($upper_div);
				$("<div>").html(comment.rcContent).attr("id","comment_rcContent").appendTo($center_div);
				
				if(comment.musername===loginId){
					var btn = $("<button>").attr("class","delete_comment").attr("data-rcno",comment.rcNo).attr("data-musername", comment.musername)
							.text("삭제").appendTo($center_div).css("float",right);
				}
				$("<hr>").appendTo($comment);
				
			})
		}
		
		$(function(){
			var rNo = location.search.substr(5);
			console.log(rNo);
			$.ajax({
				url:"/cheatingday/review/read",
				data:"rNo="+rNo,
				method:"post"
			}).done((result)=>{review=result; printReview();printComment(review.comments)})
			
		
		
		$("#comment_write").on("click",function(){
		if(isLogin===false)
			return;
		var params = {
				rNo: review.rno,
				rcContent : $("#comment_textarea").val(),
				_csrf :"${_csrf.token}"
		}
		console.log('************************');
		console.log(params);
		$.ajax({
			url:"/cheatingday/reviewComment/write",
			method:"post",
			data:params
		}).done((result)=>{printComment(result);$("#comment_textarea").val(""); $("#comment_div").hide();}).fail((result)=>{console.log(result)});
	})
	$("#comments").on("click",".delete_comment",function(){
		var params = {
			rcNo: $(this).data("rcno"),
			rNo: review.rno,
			_method:"delete",
			_csrf: "${_csrf.token}"
		}
		$.ajax({
			url:"/cheatingday/reviewComment/delete",
			method:"post",
			data: params
		}).done((result)=>{printComment(result); $("#comment_div").show();}).fail((result)=>{console.log(result)})
	})
	$("#update").on("click",function(){
		var params = {
			rNo:review.rno,
			rTitle: $("#rTitle").val(),
			rContent: CKEDITOR.instances['rContent'].getData(),
			_csrf:"${_csrf.token}",
			_method:"patch"
		}
		console.log(params);
		$.ajax({
			url:"/cheatingday/review/update",
			method:"post",
			data: params
		}).done((result)=>{location.reload()}).fail((result)=>{console.log(result)})
	})
	$("#singo").on("click",function(){
		var params = {
			rNo:review.rno,
			_csrf:"${_csrf.token}",
			_method:"patch"
		}
		console.log(params);
		$.ajax({
			url:"/cheatingday/review/singo",
			method:"post",
			data: params
		}).done((result)=>{alert("신고하셨습니다"); location.reload()}).fail((result)=>{console.log(result)})
	})
	$("#delete").on("click",function(){
		var params = {
			rNo: review.rno,
			_csrf:"${_csrf.token}",
			_method: "delete"
		}
		$.ajax({
			url:"/cheatingday/review/delete",
			method:"post",
			data:params
		}).done((result)=>{location.href = "/cheatingday/review/list"}).fail((result)=>console.log(result))
	})
	
		})
</script>
<style>
	#title_div{
		font-weight: bold;
		font-size: medium;
		margin-left: 100px;
	}
	#rTitle{
	 text-align: center;
	 font-size :large;
	 font-weight :bold;
	 border :3px solid gray;
	 display: inline-block;
	 margin:20px 400px 10px 30px;
	}
	#rStarPoint{
		font-size: large;
		font-weight:bolder;
		margin-right: 30px;
	}
		#lower_left{
		margin-right:20px 20px 10px 500px;
		padding-left: 24px;
		display: inline-block;
	}
	 #all{
   		width: 1000px;
   		height: 800px;
   		display: inline-block;
   		margin-left: 450px;
   		margin-top: 120px;
   }
   #write_time_div{
		margin-top: 15px;
		display: inline-block;
		margin-right:350px;
		
	}
	#content{
		width:900px;
		height:300px;
	}
	#btn_area{
		margin-left: 700px;
	}
	#update{
		margin-right: 30px;
	}
	#comment_textarea {
		width:780px;
		height:140px;
		margin-top:30px;
		margin-left: 98px;
		display: inline-block;
			
	}
	#comment_div{
		display:inline-block;
		width:1050px;
	}
	#comment_write{
		margin-right: 20px;
		display: inline-block;
		margin-bottom: 45px;
	}
	#comment_form{
		display:inline-block;
		margin-right: 30px;
		
	}
	#comments{
		font-size:medium;
		font-weight: bold;
		
	}
	#comment_writer{
		font-size: large;
		font-weight: bolder;
	}
	.delete_comment{
		border-radius: 15px;
		background-color: white;
		font-weight: bold;
		border-color: red;
		margin-left: 900px;
	}
</style>
</head>
<body>
	<div id = "all">
		<div id = "title_div">
			<div id = "upper">
				<input type = "text" id = "rTitle" disabled = "disabled">
				별점:<span id = "rStarPoint">
               </span>
			</div>
			
			<div id = "lower">
				<ul id = "lower_left">
					<div id = "rNo_div">
						글번호: <span id = "rNo"></span>
					</div>
					<div id = "write_time_div">
						작성시간:<span id = "write_time"></span>
					</div>
				</ul>
			</div>
			<div id = "content_div">
				<div class = "form-group">
					<div class = "form-control" id = "rContent"></div>
				</div>
				<div id = "btn_area">
					<button id = "update" class = "btn btn-outline-info">Update</button>
					<button id = "delete" class = "btn btn-outline-info">Delete</button>
				</div>
				<div>
				<sec:authorize access="hasRole('ROLE_MANAGER')">
					<button id = "singo" class = "btn btn-outline-info">신고하기</button>
				</sec:authorize>
				</div>
			</div>
		</div>
		<sec:authorize access="hasRole('ROLE_MANAGER')">
		<div id = "comment_div">
			<div class = "form-group" id = "comment_form">
				<textarea class = "form-control" rows = "5" id = "comment_textarea" placeholder = "댓글을 입력하세요" disabled="disabled"></textarea>
			</div>
			<button type = "button" class = "btn btn-outline-info" id = "comment_write">Write </button>
		</div>
		</sec:authorize>
		<hr>
		<div id = "comments"></div>
	</div>
</body>
</html>