<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Landing Page - Start Bootstrap Theme</title>
<!-- Bootstrap core CSS -->
<link href="mainhome/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template -->
<link href="mainhome/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
<link href="mainhome/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
<!-- Custom styles for this template -->
<link href="mainhome/css/landing-page.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
<link rel="stylesheet" type="text/css" href="path/to/font-awesome/css/font-awesome.min.css">
=======
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
>>>>>>> branch 'master' of https://github.com/tjddnjs5092/CheatingDay.git
</head>
<body>
	<div id="page">
		<div id="main">
			<nav>
				<jsp:include page="include/nav.jsp" />
			</nav>
			<header>
				<jsp:include page="${viewHeader}" />
			</header>
			<section>
				<jsp:include page="${viewName}" />
			</section>
		</div>
		<footer>
			<jsp:include page="include/footer.jsp" />
		</footer>
	</div>
</body>
</html>