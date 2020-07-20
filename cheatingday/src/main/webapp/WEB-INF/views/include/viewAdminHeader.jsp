<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#topmenu{
		margin-left: -40px;
		height: 40px;
		width: 105%;
	}
	#a, #b, #c, #d{
		list-style: none;
		color: white;
		background-color: red;
		float: left;
		width:25%;
		line-height: 40px;
		vertical-align: middle;
		text-align: center;
	}
	#e{
		list-style: none;
		color: white;
		background-color: #2d2d2d;
		float: left;
		width:100%;
		line-height: 20px;
		vertical-align: middle;
		text-align: center;
	}
	
	#topmenu li a{
		text-decoration: none;
		color: white;
		display: block;
		font-size: 12px;
		font-weight: bold;
	}
	#topmenu li a:hover{
		color: red;
		background-color: #4d4d4d;
	}
</style>
</head>
<body>
	<div id="topmenu">
		<ul>
			<li id="a"><a href="/cheatingday/admin/report_list">신고리뷰목록</a></li>
			<li class="dropdown" id="d"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> 회원정보 관리 <span class="caret"></span></a>
				<ul class="dropdown-menu" id="e">
					<li><a href="/cheatingday/admin/user_list?job=user_list">일반 회원 목록</a></li>
					<li><a href="/cheatingday/admin/manager_list">사업자 목록</a></li>
				</ul>
			</li>
			<li id="b"><a href="/cheatingday/notice/list">공지사항</a></li>
			<li id="c"><a href="/cheatingday/center/list">Q&A</a></li>
		</ul>
	</div>
</body>
</html>