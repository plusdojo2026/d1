<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/contact.css">
<link rel="stylesheet" href="css/style.css">
<title>お問い合わせフォーム</title>
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
	
	<h2 class="contact">いつもご利用ありがとうございます。</h2>
	<form method="POST" action="/d1/ContactServlet" id="contact">
		<p>
		<label for="kind">お問い合わせの種類</label><br> 
        <select name="contactkind" id="kind">
			<option value="app">アプリについて</option>
			<option value="car">車について</option>
			<option value="item">備品について</option>
			<option value="error">不具合について</option>
		</select> 
        </p>
        <p>
        <label for="message">お問い合わせの内容</label><br>
        <textarea name="message" id="message"></textarea>
        </p>
        <p>
		<label for="username">氏名</label><br> 
        <input type="text" name="username"  id="username">
        </p>
        <p>
		<label for="email">メールアドレス</label><br> 
        <input type="text" name="mail" class="contact-text" id="email">
        </p>
		<div>緊急連絡先: 090-2222-3333</div>
		<p id="msg" class="error"></p><br>
		<div class="contact-btn">
		<input type="reset" name="reset" value="クリア"> 
        <input type="submit" name="submit" value="送信">
        </div>
	</form>
	
</body>
<script>
	"use strict"	
	document.getElementById('contact').onsubmit = function(event) {
		let username = document.getElementById('username').value;
		let email = document.getElementById('email').value;
		if (username === '' && email === '') {
		document.getElementById('msg').textContent = '氏名とメールアドレスを入力してください';
		event.preventDefault();
		}else if (username === '') {
		document.getElementById('msg').textContent = '氏名を入力してください';
		event.preventDefault();
		} else if (email === '') {
		document.getElementById('msg').textContent = 'メールアドレスを入力してください';
		event.preventDefault();
		} 
		let ans = window.confirm("送信してよろしいですか？");
		if (ans === false) {
			event.preventDefault();
		}
		};
</script>
</html>