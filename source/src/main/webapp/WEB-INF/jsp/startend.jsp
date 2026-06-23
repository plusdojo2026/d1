<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<head>
<meta charset="UTF-8">
<header>
	<img alt="SaleS" src="${pageContext.request.contextPath}/img/SaleS.png">
	<div class="hamburger-menu">
		<input type="checkbox" id="menu-btn-check"> <label
			for="menu-btn-check" class="menu-btn"></label>
		<!--ここからメニュー-->
		<div class="menu-content">
			<ul>
				<li>
					<p>メニュー</p>
				</li>
				<li><a href="/d1/ReserveServlet">📅予約</a></li>
				<li><a href="/d1/GasolineServlet">🔥ガソリン</a></li>
				<li><a href="/d1/TodoServlet">✅TO DO</a></li>
				<li><a href="/d1/StartendServlet">開始/終了</a></li>
				<li><a href="/d1/LoginServlet">🔚ログアウト</a></li>
				<li><a href="/d1/ContactServlet">❓お問い合わせ</a></li>
			</ul>
		</div>
		<!--ここまでメニュー-->
	</div>
</header>



<title>Insert title here</title>

</head>
<body>

	<p id="realtime"></p>
	<br>

	<c:forEach var="syo" items="${reservelist}">
		<c:if test="${syo.status == '利用中'}">
			<p class="finish">終了予定時刻：
				${syo.fdate.toString().substring(5,16).replace('T',' ')}</p>
		</c:if>
	</c:forEach>
	<br>

	<c:forEach var="syo" items="${reservelist}">

  開始日時${syo.sdate.toString().replace('T', ' ')} ～ 終了日時${syo.fdate.toString().replace('T', ' ')}
  使用車${syo.carname}
  目的${syo.purpose}<br>
	</c:forEach>

	<c:if test="${hasReserve}">

		<form action="/d1/StartendServlet" method="post">
			<input type="hidden" name="action" value="start">

			<c:forEach var="reserve" items="${reservelist}" begin="0" end="0">
				<input type="hidden" name="reservenumber"
					value="${reserve.reservenumber}">
			</c:forEach>

			<button type="submit" ${canStart ? '' : 'disabled'}>開始</button>
		</form>

		<form action="/d1/StartendServlet" method="post">
			<input type="hidden" name="action" value="end">

			<c:forEach var="reserve" items="${reservelist}" begin="0" end="0">
				<input type="hidden" name="reservenumber"
					value="${reserve.reservenumber}">
			</c:forEach>

			<button type="submit" ${canEnd ? '' : 'disabled'}>終了</button>
		</form>


		<c:if test="${showQR}">
			<div id="qrArea">
				<h3>返却確認</h3>
				<img src="${pageContext.request.contextPath}/img/QR.png" alt="QRコード"
					width="300">
			</div>
		</c:if>
	</c:if>

	<c:if test="${!hasReserve}">
		<p style="color: red;">予約していません</p>
	</c:if>
	<script>
		function showQR() {
			document.getElementById("qrArea").style.display = "block";
		}
		function showClock() {
			let nowTime = new Date();
			let nowHour = nowTime.getHours();
			let nowMin = nowTime.getMinutes();
			let nowSec = nowTime.getSeconds();
			let msg = "現在時刻：" + nowHour + ":" + nowMin + ":" + nowSec;
			document.getElementById("realtime").innerHTML = msg;
		}
		setInterval('showClock()', 1000);

		showClock();
	</script>
</body>

</html>