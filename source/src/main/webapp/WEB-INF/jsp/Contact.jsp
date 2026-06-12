<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お問い合わせフォーム</title>
</head>
<body>

	<h2>いつもご利用ありがとうございます。</h2>
	<form id="contact" method="POST" action="/d1/ContactServlet">
		お問い合わせの種類<br> <select name="contactkind">
			<option value="app">アプリについて</option>
			<option value="car">車について</option>
			<option value="item">備品について</option>
			<option value="error">不具合について</option>
		</select> <br> お問い合わせの内容 <br> <input type="text" name="contact"><br>
		氏名<br> <input type="text" name="username"><br>
		メールアドレス<br> <input type="text" name="mail"><br>
		<div>緊急連絡先: 090-2222-3333</div>
		<input type="reset" name="reset" value="クリア"> <input
			type="submit" id="submit" name="submit" value="送信" disabled>
	</form>
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