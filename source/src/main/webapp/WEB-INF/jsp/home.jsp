<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style>
#s {
    display: flex;
    flex-wrap: wrap; /* 折り返し */
    gap: 20px;       /* 余白 */
}

#s a {
    width: calc(50% - 20px); /* 2列にする */
    box-sizing: border-box;
}

#s img {
    width: 100%;  /* 画像を枠いっぱいに */
    height: auto;
    display: block;
}


</style>
</head>
<body>
	<div class="notice">
		<h2>お知らせ</h2>
		${notice}
	</div>

	
<div id = "s">
	<c:forEach var="syo" items="${homeList}">

	  <a href="${pageContext.request.contextPath}/CarServlet?select=${carimage}"> <img
		alt="car${syo.carid}" src="${pageContext.request.contextPath}/img/${syo.carimage}">
	</a>
 	</c:forEach>
 	</div>
</body>
</html>
