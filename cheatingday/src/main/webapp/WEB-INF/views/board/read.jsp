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
	var board = undefined;
	function printBoard(){	
		$("#title").val(board.title);
		$("#writer").text(board.username);
		$("#bno").text(board.bno);
		$("#write_time").text(board.writeTimeStr);
		$("#good_cnt").text(board.goodCnt);
		$("#read_cnt").text(board.readCnt);
		$("#bad_cnt").text(board.badCnt);
		$("#boardcate").text(board.category);
		if(isLogin===true &&board.username===loginId)
			ck = CKEDITOR.replace("content",{
				filebrowserUploadUrl: "http://localhost:8081/cheatingday/board/ckupload"
			})
		$("#content").html(board.content).css("height","500px").css("overflow","scroll");
		$("btn_area").hide();
		//로그인 했고, 작성자가 이름이 같으면 제목, 댓글 보여주기
		if(isLogin===true && board.username===loginId){
			$("#title").prop("disabled",false);
			$("#btn_area").show();
			$("#boardcate").prop("disabled",false);
			$("#comment_textarea").prop("disabled",false);
			$("#comment_write").prop("disabled",false);
			//로그인 했고 이름이 다를때는 추천, 비추, 댓글 보여주기
		}else if(isLogin===true && board.writer!==loginId){
			$("#good_btn").prop("disabled",false);
			$("#bad_btn").prop("disabled",false);
			$("boardcate").prop("disabled",false);
			$("#comment_textarea").prop("disabled",false);
			$("#comment_write").prop("diabled",false);
		}
	}
	function printAttachment(attachments){
		var $ul = $("#attachment");
		$ul.empty();
		$.each(attachments,function(i,attachment){
			var $li = $("<li>").appendTo($ul);
			if(attachment.isImage==true)
				$("<i class = 'fa fa-file-image-o'></li>").appendTo($li);
			else
				$("<i class = 'fa fa-paperclip'></li>").appendTo($li);
			$("<a href = '/cheatingday/attachment/view?fno="+attachment.fno+"'>"+attachment.originalFileNAme+"</a>").appendTo($li);
			
			if(isLogin===true && board.username===loginId){
				var str = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				str+="<span class = 'delete_attachment' data-fno='";
				str+=attachment.fno;
				str+="'data-bno='";
				str+=attachment.bno;
				str+="'>X</span";
				console.log(str);
				$li.append(str);
				$(".delete_attachment").css("cursor","pointer").attr("title","삭제하기");
			}
		})
		
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
			$("<img>").attr("src",comment.profile).css("width","40px").appendTo($center_div);
			$("<span>").text(comment.writeTime).attr("id","write_time").appendTo($lower_div);
			$("<div>").html(comment.content).attr("id","comment_content").appendTo($center_div);
			
			if(comment.writer===loginId){
				var btn = $("<button>").attr("class","delete_comment").attr("data-cno",comment.cno).attr("data-writer", comment.writer)
						.text("삭제").appendTo($center_div).css("float",right);
			}
			$("<hr>").appendTo($comment);
			
		})
	}
$(function(){
	//자바스크립트 객체로 바꿈.
	var bno = location.search.substr(5);
	$.ajax({
		url:"/cheatingday/board/read",
		data: "bno="+bno,
		method:"post"
	}).done((result)=>{board = result; printBoard();printAttachment(board.attachments);printComment(board.comments)})
	//1.댓글 달기
	$("#comment_write").on("click",function(){
		if(isLogin===false)
			return;
		var params = {
				bno : board.bno,
				content : $("#comment_textarea").val(),
				_csrf :"${_csrf.token}"
		}
		$.ajax({
			url:"/cheatingday/comment/write",
			method:"post",
			data:params
		}).done((result)=>{printComment(result)}).fail((result)=>{console.log(result)});
	})
	//2.댓글 삭제
	$("#comments").on("click",".delete_comment",function(){
		var params = {
			cno: $(this).data("cno"),
			bno: board.bno,
			_method:"delete",
			_csrf: "${_csrf.token}"
		}
		$.ajax({
			url:"/cheatingday/comment/delete",
			method:"post",
			data: params
		}).done((result)=>{printComment(result)}).fail((result)=>{console.log(result)})
	})
	//3.업데이트
	$("#update").on("click",function(){
		var params = {
			bno:board.bno,
			title: $("#title").val(),
			content: CKEDITOR.instances['content'].getData(),
			_csrf:"${_csrf.token}",
			_method:"patch"
		}
		$.ajax({
			url:"/cheatingday/board/update",
			method:"post",
			data: params
		}).done((result)=>{location.reload()}).fail((result)=>{console.log(result)})
	})
	//삭제
	$("#delete").on("click",function(){
		var params = {
			bno: board.bno,
			_csrf:"${_csrf.token}",
			_method: "delete"
		}
		$.ajax({
			url:"/cheatingday/board/delete",
			method:"post",
			data:params
		}).done((result)=>{location.href = "/cheatingday/board/list"}).fail((result)=>console.log(result))
	})
	//글 추천
	$("#lower_right").on("click","#good_btn",function(){
		if(isLogin===false)
			return;
		if(isLogin===true && board.username===loginId)
			return;
		var params = {
			_method:"patch",
			_csrf:"${_csrf.token}",
			bno: board.bno,
			isGood:'true'
		}
		$.ajax({
			url:"/cheatingday/board/good_or_bad",
			method:"post",
			data: params,
			success:function(result){
				console.log("result"+result);
				$("#good_cnt").text(result);
			}, error:function(){
				console.log("===========")
			}
		})
	})
	//글 비추
	$("#lower_right").on("click","#bad_btn",function(){
		if(isLogin===false)
			return;
		if(isLogin===true && board.username===loginId)
			return;
		var params = {
			_method:"patch",
			_csrf:"${_csrf.token}",
			bno: board.bno,
			isGood:'false'
		}
		$.ajax({
			url:"/cheatingday/board/good_or_bad",
			method:"post",
			data:params
		}).done((result)=>{$("#bad_cnt").text(result)}).fail((xhr)=>{console.log(xhr)});
	})
})
</script>
<style>
	#title_div{
		font-weight: bold;
		font-size: medium;
		margin-left: 100px;
	}
	#title{
	 text-align: center;
	 font-size :large;
	 font-weight :bold;
	 border :3px solid gray;
	 display: inline-block;
	 margin:20px 400px 10px 30px;
	}

	#boardcate{
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
	#lower_right{
		margin-top: 15px;
		display: inline-block;
		padding-left: 20px;
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
		margin-right: 20px;
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
		background-color: white;
		border-radius: 10px;
		border-color: red;
		margin-top: 30px;
		margin-left:900px;
		
	}
	#comments{
		width:1000px;
	}
</style>
</head>
<body>
	<div id = "all">
		<div id = "title_div">
			<div id = "upper">
			<input type="text" id="title" disabled="disabled">
			 <span id = "writer"></span>
				<span id = "boardcate"></span>
			</div>
			<div id = "lower">
				<ul id="lower_left">
				<div id = "bno_div">
					글번호:<span id="bno"></span>
					</div>
					<div id = "write_time_div">
					작성시간:<span id="write_time"></span>
					</div>
				</ul>
				<ul id="lower_right">
						<button type="button" class="btn btn-outline-primary" id="good_btn" disabled="disabled">
							추천 <span class="badge" id="good_cnt"></span>
						</button>
						<button type="button" class="btn btn-outline-success" disabled="disabled">
							조회 <span class="badge" id="read_cnt"></span>
						</button>   
  						<button type="button" class="btn btn-outline-danger" id="bad_btn" disabled="disabled">
  						비추 <span class="badge" id="bad_cnt"></span>
  						</button>       	
				</ul>
			</div>
			<div>
				<ul id="attachment">
				</ul>
			</div>
			<div id="content_div">
			<div class="form-group">
				<div class="form-control" id="content"></div>
			</div>
			<div id="btn_area">
				<button id="update" class="btn btn-outline-info">Update</button>
				<button id="delete" class="btn btn-outline-danger">Delete</button>
			</div>
		</div>
		</div>
		<div id = "comment_div">
			<div class="form-group" id = "comment_form">
				<textarea class="form-control" rows="5"	id="comment_textarea" placeholder="댓글을 입력하세요" disabled="disabled"></textarea>
			</div>
			<button type="button" class="btn btn-outline-info" 
				id="comment_write" >댓글 작성</button>
		</div>
		<hr>
		<div id="comments">
			
		</div>
	</div>

</body>
</html>