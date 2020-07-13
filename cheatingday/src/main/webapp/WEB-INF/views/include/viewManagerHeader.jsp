<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function() {
	
	$("#menuManage").click(function() {
		var username = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}";
		$.ajax({
			url:"/cheatingday/manager/exists_snum",
			method:"get",
			data: 'mUsername='+username,
			success:function(result) {
				if(result==true) {
					location.href="/cheatingday/manager/menu_list?mUsername="+username;
				}else {
					Swal.fire("Info","등록된 매장이 없습니다","info");
				}
			}
		})
	})
});
</script>
</head>
<body>
<sec:authentication property="principal.username" var="username"/>
<div><a href="/cheatingday/manager/mypage?mUsername=${username}">
	내업소 관리 - 여러개가 좌르륵 떠야해 지금X</a>
</div>
<div id="menuManage"><a href="#">
	메뉴관리- 지금ㅇ</a>
</div>
<div>
	입점안내
</div>
<div>
	고객센터
</div>

<div><a href="/cheatingday/manager/information?mUsername=${username}">
	임시 내정보 관리 - 지금o</a>
</div>
<div><a href="/cheatingday/manager/store_list?mUsername=${username}">
	임시 내 매장 관리 - 지금o</a>
</div>
<div><a href="/cheatingday/manager/review_list?mUsername=${username}">
	임시 내 매장 리뷰관리 - 지금하는 중</a>
</div>

</body>
</html>