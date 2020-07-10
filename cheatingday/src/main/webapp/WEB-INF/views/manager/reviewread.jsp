<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
리뷰번호 : ${reviewRead.RNo}
</div>
<div>
내용: ${reviewRead.RContent}
</div>
<div>
별점: ${reviewRead.RStarPoint}
</div>
<div>
작성시간: ${reviewRead.RWriteTimeStr}
</div>
<div>
주문번호: ${reviewRead.ONo}
</div>
<div>
제목: ${reviewRead.RTitle}
</div>
<div>
신고수: ${reviewRead.RReport}
</div>
<div>
아이디: ${reviewRead.UUsername}
</div>
</body>
</html>