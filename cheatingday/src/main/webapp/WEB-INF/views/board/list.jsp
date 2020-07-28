<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
	var wsocket;
	function send(){
		var msg = $("#message").val();
		wsocket.send(msg);
		$("#message").val("");
	}
	$(document).ready(function(){
		var username = "${username}"
		wsocket = new WebSocket("ws://localhost:8081/cheatingday/chat");
		
		$("#message").on("keypresss",function(evt){
			var keycode = evt.keyCode;
			if(keycode==13)
				send();
		});
		$("#connect").on("click",function(){
			wsocket.onmessage = function(e){
				$("#chat_area").append(e.data+"<br>");
			}
		})
		$("#disconnect").on("click",function(){
			wsocket.close();
		})
		if(username!=""){
			wsocket.onmessage = function(e){
				$("#chatMessageArea").append(e.data+"<br>");
			}
			wsocket.onclose = function(){
				console.log("연결이 종료되었습니다.");
			}
			wsocket.onopen = function(){
				console.log("연결되었습니다.");
			}
		}
		$("#send").on("click",function(){
			console.log($("#message").val());
			wsocket.send($("#message").val());
			$("#message").val("")
		})
		
	})
</script>
<style>
   #chat_area { width:300px; height:350px; overflow:auto; border: 3px solid gray; margin-bottom: 15px;}
   #chat{width:300px; height:150px; overflow: auto; border:2px solid gray; margin-bottom: 10px;}
   #chating {
       position: fixed; 
       margin-left:2px;
       margin-top: 100px;
       width: 300px;
   }
   #all{
   		width: 1200px;
   		display: inline-block;
   		margin-left: 450px;
   		margin-top: 120px;
   }
   #h1{
   	
   }
</style>
</head>
<body>
<div class = "form-group" id = "chating"> 
	<div >
      <h1>실시간 대화창</h1>
      <div id="chat_area">
      	<div id = "chatMessageArea"></div>
      </div>
      <input type="text" id="message" placeholder="대화하기"><button id = "send" class = "btn btn-outline-info" >메시지 보내기</button>
      <button id="connect" class = "btn btn-outline-success">대화시작하기</button>
      <button id="disconnect" class = "btn btn-outline-danger">대화 끝내기</button>
      
   </div>
   </div> 
  <div id = "all">
   <div>
      <table class ="table table-hover">
         <colgroup>
            <col width="10%">
            <col width="25%">
            <col width="10%">
            <col width="15%">
            <col width="15%">
            <col width="10%">
         </colgroup>
         <thead>
         	<tr>
            <th>글번호</th>
            <th>제목</th>
            <th class = "dropdown">
            	<a class = "dropdown-toggle" data-toggle = "dropdown" href = "#">카테고리<span class = "caret"></span></a>
           		<ul class = "dropdown-menu">
           			<li><a href = "/cheatingday/board/list?cateno=1">맛집 추천</a></li>
           			<li><a href = "/cheatingday/board/list?cateno=2">나만의 레시피</a></li>
           		</ul>
            </th>
            <th>아이디</th>
            <th>작성일자</th>
            <th>조회수</th>
         </tr>
         </thead>
         <tbody id = "list">
            <c:forEach items = "${page.freelist }" var = "board">
            <tr>
            <td>${board.bno}</td>
            <td><a href = "/cheatingday/board/read?bno=${board.bno }">${board.title }</a></td>
            <td>
               ${board.category}
            </td>
            <td>${board.username }</td>
            <td>${board.writeTimeStr }</td>
            <td>&nbsp;&nbsp;&nbsp;${board.readCnt }</td>
            </tr>
            </c:forEach>
         </tbody>
      </table>
   </div>
      <div style="text-align:center; display: inline-block; padding-left: 600px; ">
      <ul class="pagination" class="pagination pagination-lg">
         <c:if test="${page.prev==true}">
            <li class="page-item"><a href="/cheatingday/board/list?pageno=${page.startPage-1}">이전</a></li>
         </c:if>
         <c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
            <c:choose>
               <c:when test="${page.pageno eq i }">
                  <li  class="page-item">
                     <a class="page-link" href="/cheatingday/board/list?pageno=${i}">${i}</a>
                  </li>
               </c:when>
               <c:otherwise>
                  <li class="page-item"><a class="page-link"  href="/cheatingday/board/list?pageno=${i}">${i}</a></li>
               </c:otherwise>
            </c:choose>
            
         </c:forEach>
         <c:if test="${page.next==true}">
            <li  class="page-item"><a class="page-link" href="/cheatingday/board/list?pageno=${page.endPage+1}">다음</a></li>
         </c:if>
      </ul>
   </div>   
      <div class="form-group" style="padding-left: 1110px;">
      <a class="btn btn-outline-danger" href="/cheatingday/board/write">글쓰기</a>
   </div>
   </div>
</body>
</html>