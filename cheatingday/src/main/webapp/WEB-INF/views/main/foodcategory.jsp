<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #category {
        border: 1px solid #CFCFCF;
        width: 225px;
        height: 200px;
    }

    table {
        height: 100px;
        width: 100px;
        margin: auto;
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
            <td><a href="/cheatingday/store_list"><img id="category" src="mainhome/images/all.JPG" alt="전체보기" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=1"><img id="category" src="mainhome/images/bunsick.JPG" alt="분식" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=2"><img id="category" src="mainhome/images/china.JPG" alt="중식" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=3"><img id="category" src="mainhome/images/chicken.JPG" alt="치킨" /></a></td>
        </tr>
        

        <tr>
            <td><a href="/cheatingday/store_list?foodNo=4"><img id="category" src="mainhome/images/korea.JPG" alt="한식" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=5"><img id="category" src="mainhome/images/bossam.JPG" alt="족발/보쌈" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=6"><img id="category" src="mainhome/images/pizza.JPG" alt="피자/양식" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=7"><img id="category" src="mainhome/images/japan.JPG" alt="일식/돈까스" /></a></td>
        </tr>

        <tr>
            <td><a href="/cheatingday/store_list?foodNo=8"><img id="category" src="mainhome/images/cafe.JPG" alt="카페/디저트" /></a></td>
            <td><a href="/cheatingday/store_list?foodNo=9"><img id="category" src="mainhome/images/franchise.JPG" alt="프랜차이즈" /></a></td>
            <td><a href="/cheatingday/board/list"><img id="category" src="mainhome/images/6.png" alt="자유게시판" /></a></td>
            <td><a href="/cheatingday/"><img id="category" src="mainhome/images/6.png" alt="Product 1" /></a></td>
        </tr>

    </table>
</body>
</html>