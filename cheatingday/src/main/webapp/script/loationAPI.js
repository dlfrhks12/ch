
/////////////////////////////// 현재위치 주소 API   ////////////////////////////////////////////



$(function() {
	// Geolocation API에 액세스할 수 있는지를 확인
	if(navigator.geolocation) {
	// 위치 정보를 정기적으로 얻기
	navigator.geolocation.getCurrentPosition(function(pos) {
		$("#latitude").html(pos.coords.latitude);   //위도
		$("#longitude").html(pos.coords.longitude); // 경도
		});
	} else {			
		alert("이 브라우저에서는 위치추적이 지원되지 않습니다");
	}		
})

function getmap() {
	// geolocation을 지원하는지 확인
	if(!navigator.geolocation)
		throw "위치 정보가 지원되지 않습니다";
	
	//geolocation 요청
	navigator.geolocation.getCurrentPosition(setMapURL, showError);
	
	function setMapURL(pos) {
		// 전달된 인자 객체로부터 위치 정보를 가져옴
		var latitude = pos.coords.latitude;		// 위도
		var longitude = pos.coords.longitude;	// 경도
		var accuracy = pos.coords.accuracy;		// 미터
		
	}
	
	// geolocation 요청이 실패하면 호출되는 콜백 함수
	function showError(err) {
		var errors = [
			err.message,
			"사용자가 권한 거부",
			"위치를 찾을 수 없음",
		];
		alert("["+err.code+"]"+errors[err.code]);
	}
}