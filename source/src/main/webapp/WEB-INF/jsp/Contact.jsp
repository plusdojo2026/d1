<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>お問い合わせフォーム</title>
</head>
<body class="body">
<header class="header">
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
</header>
	<div id="contact">
	<h2 class="contact">いつもご利用ありがとうございます。</h2>
	<form method="POST" action="/d1/ContactServlet">
		<p>
        お問い合わせの種類<br> 
        <select name="contactkind">
			<option value="app">アプリについて</option>
			<option value="car">車について</option>
			<option value="item">備品について</option>
			<option value="error">不具合について</option>
		</select> 
        </p>
        <p>
         お問い合わせの内容 <br>
        <textarea name="message"></textarea>
        </p>
        <p>
		氏名<br> 
        <input type="text" name="username">
        </p>
        <p>
		メールアドレス<br> 
        <input type="text" name="mail" class="contact-text">
        </p>
		<div>緊急連絡先: 090-2222-3333</div>
		<p class="contact-btn">
		<input type="reset" name="reset" value="クリア"> 
        <input type="submit" name="submit" value="送信">
        </p>
	</form>
	</div>
</body>
<script>
	"use strict"
	let form = document.getElementById('contact');
	form.onsubmit = function(event) {
		let ans = window.confirm("送信してよろしいですか？");
		if (ans === false) {
			event.preventDefault();
		}
	}
</script>
</html>