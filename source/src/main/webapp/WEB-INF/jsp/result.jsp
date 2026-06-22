<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
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

<title>SaleS</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body id="top">
	<div class="wrapper">
		<a href="/d1/HomeServlet"><p class="title">SaleS</p></a>

		<h1>
			<c:out value="${result.title}" />
		</h1>
		<hr>
		<p>
			<c:out value="${result.message}" />
		</p>
		<a href="${result.backTo}">ホーム画面へ戻る</a>
	</div>

</body>
</html>
