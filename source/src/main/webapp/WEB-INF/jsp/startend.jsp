<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
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
				<li><img alt="SaleS"
					src="${pageContext.request.contextPath}/img/reserve.jpg"><a
					href="/d1/ReserveServlet">予約</a>
				<li><img alt="SaleS"
					src="${pageContext.request.contextPath}/img/gasoline.png"><a
					href="/d1/GasolineServlet">ガソリン</a>
				<li><img alt="SaleS"
					src="${pageContext.request.contextPath}/img/todo.png"><a
					href="/d1/TodoServlet">Todo</a>
				<li><img alt="SaleS"
					src="${pageContext.request.contextPath}/img/startend.png"><a
					href="/d1/StartendServlet">開始/終了</a></li>
				<li><img alt="SaleS"
					src="${pageContext.request.contextPath}/img/logout.png"><a
					href="/d1/LoginServlet">ログアウト</a></li>
				<li><img alt="SaleS"
					src="${pageContext.request.contextPath}/img/contact.png"><a
					href="/d1/ContactServlet">お問い合わせ</a></li>
			</ul>
		</div>
		<!--ここまでメニュー-->
	</div>
</header>

<style>
/* 初期状態は文字列を非表示にする */
#finish {
	display: none;
}
</style>

<title>Insert title here</title>

</head>
<body>

	<p id="realtime"></p>
	<br>

	<c:forEach var="top1" items="${top1}">
		<p id="finish">終了日時：${top1.fdate.toString().substring(5, 16).replace('T', ' ')}</p>


		<br>
	</c:forEach>
	<br>

	<c:forEach var="syo" items="${reservelist}">

  開始日時${syo.sdate.toString().replace('T', ' ')} ～ 終了日時${syo.fdate.toString().replace('T', ' ')}
  使用車${syo.carname}
  目的${syo.purpose}<br>
	</c:forEach>

	<button onclick="showText()">開始</button>
	<button type="submit" ${hasTodo ? '' : 'disabled'}>終了</button>
	<script>
		function showText() {

			document.getElementById("finish").style.display = "block";
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