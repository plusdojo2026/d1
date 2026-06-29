<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/style.css">
<title>SaleS</title>
<style>
#s {
	display: grid;
	grid-template-columns: repeat(2, 1fr); /* ← 常に2列 */
	gap: 10px;
	max-width: 100%;
	margin: 0 auto;
	margin-top: 20px;
}

#s a {
	text-decoration: none;
	display: block;
}

#s img {
	width: 100%;
	height: 150px;
	object-fit: cover;
	border-radius: 8px;
	transition: transform 0.3s ease;
}

header img {
	width: 120px;
	height: auto;
	display: block;
	margin: 0 auto;
}

.header {
	background-color: #C0C0C0;
	padding: 10px 20px;
	display: flex;
	align-items: center;
	justify-content: center;
	position: relative;
}
</style>
</head>
<body class="body">
	<header class="header">
		<a href="${pageContext.request.contextPath}/HomeServlet"> <img
			alt="SaleS" src="${pageContext.request.contextPath}/img/SaleS.png">
		</a>
		<div class="hamburger-menu">
			<input type="checkbox" id="menu-btn-check"> <label
				for="menu-btn-check" class="menu-btn"><span></span></label>
			<!--ここからメニュー-->
			<div class="menu-content">
				<ul>
					<li>
						<p>メニュー</p>
					</li>
					<li><a href="/d1/ReserveServlet">📅予約</a></li>
					<li><a href="/d1/GasolineServlet">🔥ガソリン</a></li>
					<li><a href="/d1/TodoServlet">✅TO DO</a></li>
					<li><a href="/d1/StartendServlet">▶️開始/終了</a></li>
					<li><a href="/d1/LoginServlet">🔚ログアウト</a></li>
					<li><a href="/d1/ContactServlet">❓お問い合わせ</a></li>
				</ul>
			</div>
			<!--ここまでメニュー-->
		</div>
	</header>
	<div class="notice">
		<h2>お知らせ</h2>
		${notice} 🚗

		<c:forEach var="r" items="${useList}">
			<div
				style="background-color: #fff3cd; border: 2px solid orange; padding: 10px; margin-bottom: 10px;">
				<b>${r.carname}</b> は利用中です<br>
				開始：${r.sdate.toString().replace("T"," ")}<br>
				終了：${r.fdate.toString().replace("T"," ")}
			</div>
		</c:forEach>
	</div>
	<!--    <h2>📅 今日の予約</h2>

	<c:forEach var="r" items="${todayList}">
		<div>
			${r.carname}<br> ${r.sdate} ～ ${r.fdate}<br>
			目的：${r.purpose}
		</div>
	</c:forEach>-->


	<div id="s">
		<c:forEach var="syo" items="${homeList}">

			<a
				href="${pageContext.request.contextPath}/CarServlet?select=${syo.carname}">
				<img alt="car${syo.carid}"
				src="${pageContext.request.contextPath}/img/${syo.carimage}">
			</a>
		</c:forEach>
	</div>

</body>
</html>
