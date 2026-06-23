<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>車種別</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/cars.css">
<link rel="stylesheet" href="css/style.css">
<title>車種別</title>
</head>
<body>
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
<body class="body">
<form id=carfrom method="get" action="/d1/CarServlet">
		<select class="ca" name="select" onchange="this.form.submit()">
			<c:forEach var="ca" items="${carlist}">
				<option value="${ca.carname}"
					${select == ca.carname ? 'selected' : ''}>${ca.carname}</option>
			</c:forEach>
		</select>
		<br><h2>${carCondition}</h2>
	</form>

	<form method="GET" action="/d1/CarsServlet">
		<img src="${pageContext.request.contextPath}/img/${carImage}"
			alt="home写真" class="photo">

<h2>現在の状況</h2>
<div class="info-box">
    車内の状況と利用者履歴を閲覧することが出来ます
</div>
<c:forEach var="todo" items="${top1}">
<table class="table-view">
	<tr>
		<th>チェック項目</th>
		<th>状態</th>
	</tr>

	<tr>
		<td>🚘外装</td>
		<td>${todo.outsidememo}</td>
	</tr>
	<tr>
		<td>🫧匂い</td>
		<td>${todo.smell ? '有' : '無'}</td>
	</tr>
	<tr>
		<td>🧰備品</td>
		<td>${todo.insideitemmemo}</td>
	</tr>
	<tr>
		<td>⛽ガソリン</td>
		<td>${todo.gasolineamount}</td>
	</tr>
    <tr>
		<td>📱忘れ物</td>
		<td>${todo.lostitemmemo}</td>
	</tr>
</table>
	<img alt="todoフォーム写真" src="${pageContext.request.contextPath}/img/${todo.outsidephoto}">
		</c:forEach>

<table class="table-view">
    <tr>
        <th>利用期間</th>
        <th>利用者</th>
    </tr>

    <c:forEach var="reserve" items="${history}">
        <tr>
            <td>
                ${reserve.sdate.toString().replace('T', ' ')}
                ～
                ${reserve.fdate.toString().replace('T', ' ')}
            </td>
            <td>${reserve.username}</td>
        </tr>
    </c:forEach>
</table>
	</form>
</body>
</html>