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
var socket = null;
</script>
</sec:authorize>
<sec:authorize access="isAnonymous()">
	<script>
		var isLogin = false;
		var loginId = undefined;
	</script>
</sec:authorize>
<script>
$(function() {
    $("#chat").on("keypress", function(e){
        var keyCode = e.keyCode;
        if(keyCode==13){
           socket.send($("#chat").val());
           $("#chat").val("");
        }
     });
   $("#connect").on("click", function(){
      socket = new WebSocket("ws://localhost:8081/cheatingday/board/chat");
      console.log(socket);
   socket.onmessage = function(e){
	   var f = document.getElementById("chat").contentDocument;
	   console.log("aaaaaaaaaaa")
	   console.log(f);
	   var msg = JSON.parse(e.data);  
	   console.log("ssssssssssssssss")
	   console.log(msg);
	  switch(msg.type){
	  case "id":
		  clientID = msg.id;
	  	  setUsername();
	  	  break;
	  case "username":
		  text = "<b>User<em>"+msg.name +"</em>signed in at </b><br>";
	  	  break;
	  }
	  if(text.length){
		  f.write(text);
		  document.getElementById("chat").contentWindow.scrollByPages(1);
	  }
	  
   }
     
         
   });
   $("#disconnect").on("click", function(){
      socket.close();
   })
function sendText(){
	var msg = {
		type : "message",
		text: document.getElementById("chat").value,
		id: clientID,
		date: Date.now()
	}
	socket.send(JSON.stringify(msg))
}
});
  
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
      </div>
      <input type="text" id="chat" placeholder="대화하기">
      <button id="connect" class = "btn btn-success">대화시작하기</button>
      <button id="disconnect" class = "btn btn-danger">대화 끝내기</button>
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