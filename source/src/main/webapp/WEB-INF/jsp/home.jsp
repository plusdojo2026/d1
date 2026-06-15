<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

<head>

<meta charset="UTF-8">
<title>SaleS</title>

</head>
<body>
	<div class="notice">
		<h2>お知らせ</h2>
		${notice}
	</div>
	<a href="${pageContext.request.contextPath}/CarServlet?carid=1"> <img
		alt="car1" src="${pageContext.request.contextPath}/img/car1.jpg">
	</a>

	<a href="${pageContext.request.contextPath}/CarServlet?carid=2"> <img
		alt="car2" src="${pageContext.request.contextPath}/img/car2.jpg">
	</a>

	<a href="${pageContext.request.contextPath}/CarServlet?carid=3"> <img
		alt="car3" src="${pageContext.request.contextPath}/img/car3.jpg">
	</a>

	<a href="${pageContext.request.contextPath}/CarServlet?carid=4"> <img
		alt="car4" src="${pageContext.request.contextPath}/img/car4.jpg">
	</a>
</body>
</html>
