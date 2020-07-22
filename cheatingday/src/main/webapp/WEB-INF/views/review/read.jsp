<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<sec:authorize access="isAuthenticated()">
<script>
var isLogin = true;
var loginId = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}"
</script>
</sec:authorize>
<sec:authorize access = "isAnonymous()">
<script>	
		var isLogin = false;
		var loginId = undefined;
</script>
</sec:authorize>
<script>
${review}

	 var review = undefined;
	function printReview(){
		$("#title").val(review.rtitle);
		$("#writer").text(review.uusername);
		$("#rno").text(review.rno);
		$("#write_time").text(review.rwritetimestr);
		if(isLogin===true&& review.uusername===loginId)
			ck=CKEDITOR.replace("content",{
				filebroserUploadUrl:"http://localhost:8081/cheatingday/review/ckupload"
			})
		$("#content").html(review.rcontent).css("height","500px").css("overflow","scroll");
		$("#btn_area").hide();
		if(isLogin===true&& review.uusername===loginId){
			$("#title").prop("diabled",false);
			$("#btn_area").show();
			$("#comment_textarea").prop("disabled",false);
			$("#comment_write").prop("diabled",false);
		}else if(isLogin===true&& review.uusername!==loginId){
			$("#comment_textarea").prop("disabled",false);
			$("#comment_write").prop("diabled",false);
			
		}
	}
	function printComment(comments){
		var $comments = $("#comments");
		$comments.empty();
		$.each(comments,function(i,comment){
			var $comment = $("<div>").appendTo($comments);
			var $upper_div = $("<div>").appendTo($comment);
			var $center_div = $("<div>").appendTo($comment);
			var $lower_div = $("<div>").appendTo($comment);
			$("<span></span>").text(comment.writer).attr("id","comment_writer").appendTo($upper_div);
			$("<span>").text(comment.writeTime).attr("id","write_time").appendTo($lower_div);
			$("<div>").html(comment.content).attr("id","comment_content").appendTo($center_div);
			if(comment.writer===loginId){
				var btn = $("<button>").attr("class","delete_comment").attr("data-rcNo",comment.rcNo).attr("data-writer",comment.write)
					.text("삭제").appendTo($center_div).css("float",right);
			}
			$("<hr>").appendTo($comment);
		})
	}
	$(function(){
		var rno = location.search.substr(5);
		console.log(rno);
		$.ajax({
			url:"/cheatingday/review/read",
			data:"rNo="+rno,
			method:"post"
		}).done((result)=>{review = result; printReview();printComment(review.comments)})
		
		$("#comment_write").on("click",function(){
			console.log(review);
			if(isLogin===false)
			return;
			var params = {
				rNo : review.rno,
				rcContent:$("#comment_textarea").val(),
				_csrf:"${_csrf.token}"
			}
			$.ajax({
				url:"/cheatingday/reviewComment/write",
				method:"post",
				data: params
			}).done((result)=>{printComment(result)}).fail((result)=>{console.log(result)});
		})
	})
	$("#comments").on("click",".delete_comment",function(){
		var params = {
			rcno: $(this).data("rcno"),
			rno:review.rno,
			_method:"delete",
			_csrf:"${_csrf.token}"
		}
		$.ajax({
			url:"/cheatingday/reviewComment/delete",
			method:"post",
			data: params
		}).done((result)=>{printComment(result)}).fail((result)=>{console.log(result)});
	})
	$("#update").on("click",function(){
		var params = {
			rno: review.rno,
			title: $("#title").val(),
			content: CKEDITOR.instances['content'].getData(),
			_csrf:"${_csrf.token}",
			_method:"patch"
		}
		$.ajax({
			url:"/cheatingday/review/update",
			method:"post",
			data: params
		}).done((result)=>{location.reload()}).fail((result)=>{console.log(result)});
	})
	$("#delete").on("click",function(){
		var params = {
			rno : review.rno,
			_csrf:"${_csrf.token}",
			_method:"delete"
		}
		$.ajax({
			url:"/cheatingday/review/delete",
			method:"post",
			data:params
		}).done((result)=>{location.href = "/cheatingday/review/list"}).fail((result =>console.log(result)));
	}) 
</script>
</head>
<body>
	<div id = "all"> 
		<div id = "title_div">
			<div id = "upper">
				<input type = "text" id = "title" disabled="disabled">
				<span id = "writer"></span>
			</div>
			<div id = "lower">
				<ul id = "lower_left">
					<div id = "rno_div">
						글번호:<span id = "rno"></span>
					</div>
					<div id = "write_time_div">
						작성시간:<span id = "write_time"></span>
					</div>
				</ul>
			</div>
			<div id = "content_div">
				<div class = "form-group">
					<div class = "form-control" id = "content"></div>
				</div>
				<div id= "btn_area">
					<button id = "update" class = "btn btn-outline-info">Update</button>
					<button id = "delete" class = "btn btn-outline-info">Delete</button>
				</div>
			</div>
		</div>
		<div id = "comment_div">
			<div class = "form-group" id = "comment_form">
				<textarea class = "form-control" rows = "5" id = "comment_textarea" placeholder = "댓글을 입력하세요"></textarea>
			</div>
			<button type = "button" class = "btn btn-outline-info" id = "comment_write">댓글작성</button>
		</div>
		<hr>
		<div id = "comments"></div>
	</div>
</body>
</html>