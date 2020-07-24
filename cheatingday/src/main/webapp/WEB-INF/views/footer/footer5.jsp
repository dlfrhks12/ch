<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cheating Day Footer</title>
<script type="text/JavaScript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
<style>
#all {
	max-width:1020px; 
	margin: 0 auto; 
	padding: 0;
	border: 0.5px solid lightgray;
}
.tabs-menu {
	text-align: center;
	list-style: none;
	margin: 0;
	padding: 0;
	line-height: 30px;
	vertical-align: middle;
	text-align: center; 
	overflow: hidden;
	background-color: #EAEAEA;
}
.tabs-menu li {
	padding: 0;
	margin: 0 auto;
	float: left;
	width: 20%;
}

.tabs-menu li a {
	font-size: 14px;
	display:block;
	color: white;
	text-aling: center;
	padding: 14px 16px;
	text-decoration: none;
	color: #5D5D5D;
}

.tabs-menu li a:hover {
	text-decoration: none;
}
#menu {
	text-align: center;
}
#title {
	font-size: 17px;
	font-weight: 600;
}
#con {
	font-size: small;
	margin-left: 20px;
}
</style>
<script>
</script>
</head>
<body style="background-color: #FAFAFA">
<div id="all">
	<div id="tabs-container" style="margin: 0 auto;">
		<ul class="tabs-menu" style="overflow: hidden;">
			<li><a id="btn1" href="/cheatingday/footer_use">이용약관</a></li>
			<li><a id="btn2" href="/cheatingday/footer_imp">개인정보 처리방침</a></li>
			<li><a id="btn3" href="/cheatingday/footer_agr">개인정보 제3자 제공동의</a></li>
			<li><a id="btn4" href="/cheatingday/footer_loc">위치기반 서비스 이용약관</a></li>
			<li><a id="btn5" style="border: 1px solid red; color: red; background-color:#F6F6F6">전자금융거래 이용약관</a></li>
		</ul>
	</div>
	<div class="tab">
		<div id="tab-1" class="tab-content" style="margin:30px;">
			<br><h5 id="menu">전자금융거래 이용약관</h5><br>
			<p id="title">제 1조(목적)<p>
			<p id="con">이 약관은 유한책임회사 딜리버리히어로코리아(이하 '회사'라 합니다)가 제공하는 전자지급결제대행서비스를 이용자가 이용함에 있어 회사와 이용자 사이의 전자금융거래에 관한 기본적인 사항을 정함을 목적으로 합니다.</p><br>
			<p id="title">제2조 (용어의 정의)</p>
			<p id="con">① 이 약관에서 정하는 용어의 정의는 다음 각 호와 같습니다.<br>
			1. '전자금융거래'라 함은 회사가 전자적 장치를 통하여 전자지급결제대행서비스 (이하 '전자금융거래 서비스'라고 합니다)를 제공하고, 이용자가 회사의 종사자와 직접 대면하거나 의사소통을 하지 아니하고 자동화된 방식으로 이를 이용하는 거래를 말합니다.<br>
			2. '전자지급수단'이라 함은 전자자금이체, 직불전자지급수단, 선불전자지급수단, 전자화폐, 신용카드, 전자채권 그 밖에 전자적 방법에 따른 지급 수단을 말합니다.<br>
			3. '전자지급결제대행서비스'라 함은 전자적 방법으로 재화의 구입 또는 용역의 이용에 있어서 지급결제정보를 송신하거나 수신하는 것 또는 그 대가의 정산을 대행하거나 매개하는 서비스를 말합니다.<br>
			4. '이용자'라 함은 이 약관에 동의하고 '회사'가 제공하는 전자금융거래 서비스를 이용하는 자를 말합니다.<br>
			5. '접근매체'라 함은 전자금융거래에 있어서 거래지시를 하거나 '이용자' 및 거래내용의 진실성과 정확성을 확보하기 위하여 사용되는 수단 또는 정보로서 전자식 카드 및 이에 준하는 전자적 정보(신용카드번호를 포함한다), '전자서명법'상의 인증서, '회사'에 등록된 '이용자'번호, '이용자'의 생체정보, 이상의 수단이나 정보를 사용하는데 필요한 비밀번호 등 전자금융거래법 제2조 제10호에서 정하고 있는 것을 말합니다.<br>
			6. '거래지시'라 함은 '이용자'가 본 약관에 의하여 체결되는 전자금융거래계약에 따라 '회사'에 대하여 전자금융거래의 처리를 지시하는 것을 말합니다.<br>
			7. '오류'라 함은 '이용자'의 고의 또는 과실 없이 전자금융거래가 전자금융거래계약 또는 '이용자'의 거래지시에 따라 이행되지 아니한 경우를 말합니다.<br>
			8. '정보통신망'이라 함은 전기통신설비를 이용하거나 전기통신설비와 컴퓨터 및 컴퓨터의 이용기술을 활용하여 정보를 수집·가공·검색·송신 또는 수신하는 정보통신체제를 말합니다.<br>
			② 본 조 및 본 약관의 다른 조항에서 정의한 것을 제외하고는 전자금융거래법등 관계 법령에 따릅니다.</p><br>
			<p id="title">제3조 (약관의 명시 및 변경)</p>
			<p id="con">① 회사는 이용자가 전자금융거래 서비스를 이용하기 전에 이 약관을 게시하고 이용자가 이 약관의 중요한 내용을 확인할 수 있도록 합니다.<br>
			② 회사는 이용자의 요청이 있는 경우 전자문서의 전송방식에 의하여 본 약관의 사본을 이용자에게 교부합니다.<br>
			③ 회사가 약관을 변경하는 때에는 그 시행일 1개월 전에 변경되는 약관을 회사가 제공하는 전자금융거래 서비스 이용 초기화면 및 회사의 홈페이지에 게시함으로써 이용자에게 공지합니다.<br>
			④ 법령의 개정으로 인하여 긴급하게 약관을 변경한 때에는 변경된 약관을 전자금융거래 서비스 이용 초기화면 및 회사의 홈페이지에 최소 1월 이상 게시하고 이용자에게 통지합니다.<br>
			⑤ 회사가 제3항의 공지를 할 경우, “이용자가 변경 내용에 동의하지 않는 경우, 공지한 날로부터 1개월(공지기간)이내에 계약을 해지할 수 있으며, 계약해지의 의사표시를 하지 아니한 경우에는 변경에 동의한 것으로 본다”라는 취지의 내용을 고지하거나 통지합니다.</p><br>
			<p id="title">제4조 (전자지급결제대행서비스의 종류)</p>
			<p id="con">회사가 제공하는 전자지급결제대행서비스는 지급결제수단에 따라 다음과 같이 구별됩니다.<br>
			1. 신용카드결제대행서비스: 이용자가 결제대금의 지급을 위하여 제공한 지급결제수단이 신용카드인 경우로서, 회사가 전자결제시스템을 통하여 신용카드 지불정보를 송·수신하고 결제대금의 정산을 대행하거나 매개하는 서비스를 말합니다.<br>
			2. 계좌이체대행서비스: 이용자가 결제대금을 회사의 전자결제시스템을 통하여 금융기관에 등록한 자신의 계좌에서 출금하여 원하는 계좌로 이체할 수 있는 실시간 송금 서비스를 말합니다.<br>
			3. 가상계좌서비스: 이용자가 결제대금을 현금으로 결제하고자 경우 회사의 전자결제시스템을 통하여 자동으로 이용자만의 고유한 일회용 계좌의 발급을 통하여 결제대금의 지급이 이루어지는 서비스를 말합니다.<br>
			4. 기타: 회사가 제공하는 서비스로서 지급결제수단의 종류에 따라, 'ARS결제대행서비스', '상품권결제대행서비스'등이 있습니다.</p>
					</div>
	</div>
</div>
</body>
</html>