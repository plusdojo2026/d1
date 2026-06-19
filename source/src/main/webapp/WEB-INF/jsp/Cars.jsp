<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>車種別</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
</head>
<header>
<div class="hamburger-menu">
        <input type="checkbox" id="menu-btn-check">
        <label for="menu-btn-check" class="menu-btn"><span></span></label>
        <!--ここからメニュー-->
        <div class="menu-content">
            <ul>
                <li>
                    <p>メニュー</p>
                </li>
                <li>
                    <a href="/d1/ReserveServlet">📅予約</a>
                </li>                
                <li>
                    <a href="/d1/GasolineServlet">🔥ガソリン</a>
                </li>
                <li>
                    <a href="/d1/TodoServlet">✅TO DO</a>
                </li>
                <li>
                    <a href="/d1/StartendServlet">開始/終了</a>
                </li>
                <li>
                    <a href="/d1/LoginServlet">🔚ログアウト</a>
                </li>
                <li>
                    <a href="/d1/ContactServlet">❓お問い合わせ</a>
                </li>                 
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
<body class="body">
<form method="POST" action="/d1/CarsServlet">
<img src="c:\Users\User\Documents\サンプル\site\images\home-hero.jpg" alt="外装写真" class="photo">

<h2>現在の状況</h2>
<div class="info-box">
    車内の状況と利用者履歴を閲覧することが出来ます。
</div>
<c:forEach var="todo" items="${top1}">
<table class="table-view">
	<tr>
		<th>チェック項目</th>
		<th>状態</th>
	</tr>

	<tr>
		<td>外装</td>
		<td>${todo.outsidememo}</td>
	</tr>
	<tr>
		<td>匂い</td>
		<td>${todo.smell ? '有' : '無'}</td>
	</tr>
	<tr>
		<td>備品</td>
		<td>${todo.insideitemmemo}</td>
	</tr>
	<tr>
		<td>ガソリン</td>
		<td>${todo.gasolineamount}</td>
	</tr>
    <tr>
		<td>忘れ物</td>
		<td>${todo.lostitemmemo}</td>
	</tr>
</table>

<img alt="todoフォーム写真" src="${pageContext.request.contextPath}/img/${todo.outsidephoto}">
		</c:forEach>
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