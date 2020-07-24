<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   	table tr img {
        border: 1px solid #CFCFCF;
        width: 225px;
        height: 200px;
    }

    table {
        height: 100px;
        width: 100px;
        margin: 0 auto;
        text-align: center;
    }
    table td {
    	padding: 5px;
    }
    
</style>
</head>
<body style="background-color: #FAFAFA">
    <br>
    <table>
        <tr>
            <td><a href="/cheatingday/store_list"><img src="mainhome/images/all.JPG" alt="전체보기" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=1"><img src="mainhome/images/bunsick.JPG" alt="분식" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=2"><img src="mainhome/images/china.JPG" alt="중식" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=3"><img src="mainhome/images/chicken.JPG" alt="치킨" /></a></td>
        </tr>
        

        <tr>
            <td><a href="/cheatingday/store_list?foodNo=4"><img src="mainhome/images/korea.JPG" alt="한식" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=5"><img src="mainhome/images/bossam.JPG" alt="족발/보쌈" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=6"><img src="mainhome/images/pizza.JPG" alt="피자/양식" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=7"><img src="mainhome/images/japan.JPG" alt="일식/돈까스" /></a></td>
        </tr>

        <tr>
            <td><a href="/cheatingday/store_list?foodNo=8"><img src="mainhome/images/cafe.JPG" alt="카페/디저트" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=9"><img src="mainhome/images/franchise.JPG" alt="프랜차이즈" /></a></td>
            <td><a href="/cheatingday/board/list"><img src="mainhome/images/freeboard.JPG" alt="자유게시판" /></a></td>
            <td><a href="/cheatingday/review/list"><img src="mainhome/images/reviewboard.JPG" alt="리뷰게시판" /></a></td>
        </tr>

    </table>
</body>
</html>