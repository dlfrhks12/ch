<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="/cheatingday/ckeditor/ckeditor.js"></script>
<!-- 로그인여부 확인 및 아이디꺼내오기 -->
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
<style>
	#lower_left li{
		display: inline-block;
		padding : 0 10px;
	}
	#lower_left li:nth-of-type(2n){  /* #lower_left 하위의 li 중 짝수번째인 것에 적용 */
		border-left: 1px solid gray;
		border-right: 1px solid gray;
	}
	#lower_right li {
		display: inline-block;
		padding : 0 5px;
		font-size: 0.9em;
	}
	#lower_left {
		float: left;
	}
	#lower_right {
		float: right;
	}
	#lower {
		overflow: hidden;
	}
	#title_div div {
		margin: 5px 0 5px;
	}

</style>
<script>
$(function() {
	//공지 불러오기
	var notice = ${notice};
	console.log(notice);
	$("#nTitle").val(notice.ntitle);
	$("#aUsername").val(notice.ausername);
	$("#aIrum").text(notice.airum);
	$("#nNo").text(notice.nno);
	$("#write_time").text(notice.nwriteTimeStr);
	$("#read_cnt").text(notice.nreadCnt);
	if(isLogin===true && notice.ausername===loginId)
		var ck = CKEDITOR.replace("content",{
			height:'500px',
			filebrowserUploadUrl:"http://localhost:8081/cheatingday/notice/ckupload"
		})
	$("#content").html(notice.content).css("height","600px").css("overflow","scroll");;
	// 초기화 - 버튼영역 감추기
	$("#btn_area").hide();
	if(isLogin===true && notice.ausername===loginId) {
		$("#nTitle").prop("disabled", false);
		$("#btn_area").show();
		$("#content").prop("readonly", false);
	} 
	//공지 삭제
	$("#delete").on("click", function(){
		var params = {
			nNo : notice.nno,
			_method: "delete",
			_csrf: "${_csrf.token}"
		}
		$.ajax({
			url: "/cheatingday/notice/delete",
			method: "post",
			data: params
		})
		.done((result)=>{ location.href=result })
		.fail((result)=>{console.log(result)});

	})
	//공지 변경
	$("#update").on("click", function(){
		var params = {
			nNo : notice.nno,
			nTitle : $("#nTitle").val(),
			content : CKEDITOR.instances['content'].getData(),
			_csrf: "${_csrf.token}",
			_method: "patch"
		}
		console.log(params);
		$.ajax({
			url: "/cheatingday/notice/update",
			method: "post",
			data: params
		})
		.done((result)=>{ location.reload();})
		.fail((result)=>{console.log(result)});
	})
});
</script>
</head>
<body>
<div id="wrap" style="width: 1000px; min-height: 800px; margin-left: 400px; margin-right: 400px;">
	<div>
		<div id="title_div">
			<div id="upper">
				<input type="text" id="nTitle" disabled="disabled" style="min-width: 600px; ">
				<input type="hidden" id="aUsername">
			</div>
			<div id="lower">
				<ul id="lower_left">
					<li><span style="text-align: " id="aIrum"></span></li>
					<li><span id="write_time"></span></li>
				</ul>
				<ul id="lower_right">
  						<li>
						<button type="button" class="btn btn-info" disabled="disabled">
							조회수 <span class="badge" id="read_cnt"></span>
						</button></li>        	
				</ul>
			</div>
		</div>
		<div id="content_div">
			<div class="form-group">
				<div class="form-control" id="content"></div>
			</div>
			<div id="btn_area">
				<button id="update" class="btn btn-danger">변경</button>
				<button id="delete" class="btn btn-danger">삭제</button>
			</div>
		</div>
		</div>
		<hr>
		<button class="btn btn-danger" onclick="location.href='/cheatingday/notice/list';">공지목록</button>
</div>
</body>
</html>