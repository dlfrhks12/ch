<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style>
	#bg {
	width: 100%;
	height: 300px;
	}
	
	#row {
	position: absolute;
	top: 100px;
	left: 450px;
	}
	
	#search {
	position: absolute;
	top: -48px;
	left: 425px;
	width: 80px;
	}
</style>
</head>
<body>
    <!-- Masthead -->
    <header class="masthead text-white text-center" id="bg">
        <div class="overlay"></div>
        <div class="container">
            <div class="row" id="row">
                <div class="col-xl-9 mx-auto">
                    <h1 class="mb-5">치팅데이와 한 끼 하실래여?</h1>
                </div>
                <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
                    <form style="900px;">
                
                        <div class="form-row">
                        <button class="fas fa-street-view fa-2x" id="but"></button>
                            <div class="col-12 col-md-9 mb-2 mb-md-0">
                                <input type="email" class="form-control form-control-lg" placeholder="건물명, 도로명, 지번으로 검색하세요.">
                            </div>
                            <div class="col-12 col-md-3">
                                <button type="submit" class="btn btn-block btn-lg btn-danger" id="search">검색</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </header>
</body>
</html>