<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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


	<select name="carid">
		<option value="1" <c:if test="${carid == 1}">selected</c:if>>プリウス</option>
		<option value="2" <c:if test="${carid == 2}">selected</c:if>>アクア</option>
		<option value="3" <c:if test="${carid == 3}">selected</c:if>>フィット</option>
		<option value="4" <c:if test="${carid == 4}">selected</c:if>>ノート</option>
	</select>
</header>
</head>
<body>
	<form method="POST" action="/d1/CarsServlet">
		<img src="c:\Users\User\Documents\サンプル\site\images\home-hero.jpg"
			alt="外装写真" class="photo">

		<h2>現在の車の利用状況</h2>
		<p>${carCondition}</p>
		<div class="info-box">車内の状況と利用者履歴を閲覧することが出来ます。</div>
		<c:forEach var="todo" items="${todoList}">
			<tr>
				<td>外装 ${todo.outsidememo}<br></td>
				<td>匂い ${todo.smell ? '有' : '無'}<br></td>
				<td>備品 ${todo.insideitemmemo}<br></td>
				<td>ガソリン ${todo.gasolineamount}<br></td>
				<td>忘れ物 ${todo.lostitem ? '有' : '無'}<br></td>
				<td>${todo.lostitemmemo}<br></td>

			</tr>
		</c:forEach>

		<img src="" alt="todoフォーム写真" class="photo"><br>

		<div class="history">
			<h2>使用履歴</h2>

			<c:forEach var="reserve" items="${history}">
				<div class="history-row">
					<span> ○ ${reserve.sdate.toString().replace('T', ' ')} ～
						${reserve.fdate.toString().replace('T', ' ')} </span> <span class="user">
						${reserve.username} </span>
				</div>
			</c:forEach>
		</div>
	</form>
</body>
</html>