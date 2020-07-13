<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
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
   
   #map {
   position: absolute;
   left: -410px;
   top: -85px;
   }
</style>
<body>
    <!-- Masthead -->
    <header class="masthead text-white text-center" id="bg">
        <div class="overlay"></div>
        <div class="container">
            <div class="row" id="row">
                <div class="col-xl-9 mx-auto">
					<div id="map" style="width:450px; height:350px;"></div>                        
                    <h1 class="mb-5">&nbsp;&nbsp;오늘 하루는 치팅데이&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</h1>
                </div>
                <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
                    <form style="900px;">
                        <div class="form-row">
                        <button type="button" class="fas fa-street-view fa-2x" id="but" ></button>
                            <div class="col-12 col-md-9 mb-2 mb-md-0">
                                <input  class="form-control form-control-lg" placeholder="건물명, 도로명, 지번으로 검색하세요.">
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
<script type="text/JavaScript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c86650f7cb8f6b997cf396f534dcc3"></script>
<script>
var mapContainer = document.getElementById('map'); // 지도를 표시할 div
var coardXY = document.getElementById("coardXY");  // 검색 지도 경도위도 알아내기
var mapOption = { 
	center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	level: 10 // 지도의 확대 레벨 
}; 

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


//HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {

//GeoLocation을 이용해서 접속 위치를 얻어옵니다
navigator.geolocation.getCurrentPosition(function(position) {

var lat = position.coords.latitude, // 위도
    lon = position.coords.longitude; // 경도

var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
    message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다

// 마커와 인포윈도우를 표시합니다
displayMarker(locPosition, message);
    
});

} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
message = 'geolocation을 사용할수 없어요..'

displayMarker(locPosition, message);
}

//지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {

//마커를 생성합니다
var marker = new kakao.maps.Marker({  
map: map, 
position: locPosition
}); 

var iwContent = message, // 인포윈도우에 표시할 내용
iwRemoveable = true;

//인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
content : iwContent,
removable : iwRemoveable
});

//인포윈도우를 마커위에 표시합니다 
infowindow.open(map, marker);

//지도 중심좌표를 접속위치로 변경합니다
map.setCenter(locPosition);      
}    

$(function() {
	$("#map").hide();
	$("#but").on("click", function() {
		$("#map").toggle();
	})
})
</script>
</body>
</html>