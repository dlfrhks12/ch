<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function(){
	var $boardcate = $("#boardcate");
	$boardcate.empty();
	$.ajax(){
		url:,
		method:,
		data: 
	}
	
})
var socket = null;
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
     	 $("#chat_area").append(e.data + "<br>");
      }
   });
   $("#disconnect").on("click", function(){
      socket.close();
   })
});
</script>
<style>
   #chat_area { width:500px; height:300px; overflow:auto; border: 3px solid red; background: aqua;}
   #chat{width:500px; height:100px; overflow: auto; border:2px solid orange;}
   #chating {
       position: fixed; bottom: 20px; 
   }
   
</style>
</head>
<body>
<div class = "form-group" id = "chating">
      <h1>★실시간대화창★</h1>
      <button id="connect" class = "btn btn-success">대화시작하기</button>
      <div id="chat_area">
      </div>
      <input type="text" id="chat" placeholder="대화하기">
      <button id="disconnect" class = "btn btn-danger">대화 끝내기</button>
   </div>
	<div>
		<table class ="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="40%">
				<col width="10%">
				<col width="30%">
				<col width="10%">
			</colgroup>
			<thead>
				<th>글번호</th>
				<th>제목</th>
				<th>
				<select id = "boardcate">
						<c:forEach items = "${category}" var = "c">
							<option value = "${c.CATENO }">${c.CATEGORY}</option>
							
							
						</c:forEach>
				</select>
				</th>
				<th>아이디</th>
				<th>작성일자</th>
				<th>조회수</th>
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
				<td>${board.readCnt }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/cheatingday/board/list?pageno=${page.startPage-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active">
							<a href="/cheatingday/board/list?pageno=${i}">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/cheatingday/board/list?pageno=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/board/list?pageno=${page.endPage+1}">다음</a></li>
			</c:if>
		</ul>
	</div>	
		<div class="form-group">
		<a class="btn btn-info" href="/cheatingday/board/write">글쓰기</a>
	</div>
</body>
</html>