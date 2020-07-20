<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
<style>
   #bg {width: 100%; height: 300px; }
   #row {position: absolute; top: 100px; left: 450px;}
   #search {position: absolute; top: -48px; left: 425px; width: 80px;}
   #map_wrap {position: absolute; left: -410px; top: -85px;}
   .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
   #centerAddr {display:block;margin-top:2px;font-weight: normal;}
   .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap; color:black;}
   	#topmenu{
		margin-left: -40px;
		margin-top: -16px;
		height: 40px;
		width: 105%;
	}
	#a{
		list-style: none;
		color: white;
		background-color: red;
		float: left;
		width: 9.8%;
		line-height: 40px;
		vertical-align: middle;
		text-align: center;
	}	
	#topmenu li a{
		text-decoration: none;
		color: white;
		display: block;
		font-size: 12px;
		font-weight: bold;
	}
	#topmenu li a:hover{
		color: red;
		background-color: #4d4d4d;
	}
</style>
<body>
    <!-- Masthead -->
    <header class="masthead text-white text-center" id="bg">
        <div class="overlay"></div>
        <div class="container">
            <div class="row" id="row">
                <div class="col-xl-9 mx-auto">
					<div id ="map_wrap">
   						 <div id="map" style="width:450px; height:350px; position:relative; overflow:hidden;"></div>
					</div>                       
                    <h1 class="mb-5">&nbsp;&nbsp;오늘 하루는 치팅데이&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</h1>
                </div>
                <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
                    <form style="900px;">
                        <div class="form-row">
                        <button type="button" class="fas fa-street-view fa-2x" id="but" ></button>
                            <div class="col-12 col-md-9 mb-2 mb-md-0" id="hAddr">
                                <input  class="form-control form-control-lg" placeholder="건물명, 도로명, 지번으로 검색하세요." id="centerAddr">
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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c86650f7cb8f6b997cf396f534dcc3&libraries=services"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c86650f7cb8f6b997cf396f534dcc3&libraries=LIBRARY"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c86650f7cb8f6b997cf396f534dcc3"></script>
<script>



//////////////////////////   카카오 맵 API    //////////////////////////////////
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 2 // 지도의 확대 레벨 
    }; 

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
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

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);      




    var infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

//지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
$(function() {
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
	    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
	        if (status === kakao.maps.services.Status.OK) {
	            var detailAddr = !!result[0].road_address ? '<div>도로명: ' + result[0].road_address.address_name + '</div>' : '';
	            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
	            
	            var content = '<div class="bAddr">' + detailAddr + '</div>';
	            
	            // 마커를 클릭한 위치에 표시합니다 
	            marker.setPosition(mouseEvent.latLng);
	            marker.setMap(map);
	
	            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
	            infowindow.setContent(content);
	            infowindow.open(map, marker);
	            
	            // 지도 좌표 클릭 시 지번 주소가 검색창에 들어감
	            $("#centerAddr").val(result[0].address.address_name);
	            
	            }
	        });
	    });
	});   
}
// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');

        for(var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }    
}




///////////////////////////////////////////////////////////////////////////////
$(function() {
	$("#map").hide();
	$("#but").on("click", function() {
		$("#map").toggle();
	})
})
</script>
<div id="topmenu">
		<ul>
			<li id="a"><a href="/cheatingday/store_list">전체목록</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=1">분식</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=2">중식</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=3">치킨</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=4">한식</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=5">족발/보쌈</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=6">피자/양식</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=7">일식/돈까스</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=8">카페/디저트</a></li>
			<li id="a"><a href="/cheatingday/store_list?foodNo=9">프렌차이즈</a></li>
		</ul>
	</div>
</body>
</html>