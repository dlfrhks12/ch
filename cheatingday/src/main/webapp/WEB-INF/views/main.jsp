<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <style>
	/* reset */
	* { 
		margin: 0; 
		padding: 0;
	}	
	ul, li { 
		list-style: none; 
	}
	
	/* layout */
	#page { 
		width: 1000px; 
		margin: 0 auto;
	}
	aside { 
		float: left; 
		width: 200px; 
	}
	section { 
		float: left; 
		width: 800px;
		min-height: 600px;
	}
	#main { 
		overflow: hidden; 
	}

/* web font */
@import 
url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
header, nav, aside, footer, section {
	font-family : "Nanum Gothic", sans-serif;
}

</style>
<title>Insert title here</title>
<link rel="stylesheet" href="/cheatingday/css/main.css">
</head>
<body>
	<div id="page">
		<header>
			<jsp:include page="include/header.jsp" />
		</header>
		<nav>
			<jsp:include page="include/nav.jsp" />
		</nav>
		<footer>
			<jsp:include page="include/footer.jsp" />
		</footer>
	</div>
</body>
</html>