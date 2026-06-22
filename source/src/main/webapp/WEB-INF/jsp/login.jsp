<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>車管理アプリ</title>
</head>
<body>
	<div class="background-opacity-container">
	<div id="login-box">
	<form id="login" method="POST" action="/d1/LoginServlet">
	<h1>ログイン</h1>
		<p>
			ユーザーID<br> <input type="text" name="userid">
		</p>
		<p>
			パスワード<br> <input type="password" name="password">
		</p>
		<span id="error" class="error"></span><br>
		<input type="submit" name="submit" value="ログイン"> 
		 
		<p>ログイン情報を忘れてしまった場合は<br>
		<a href = "/d1/ContactServlet">こちらをクリック</a></p>
	</form>
	</div>
	</div>
	<script>
		'use strict';
		let form = document.getElementById('login');
		let errorMessage = document.getElementById('error');

		form.onsubmit = function(event) {
			if (form.userid.value === '' && form.password.value === '') {
				errorMessage.textContent = '※IDとPWを入力してください！';
				form.userid.style.backgroundColor = "#ffe6e6";
				form.password.style.backgroundColor = "#ffe6e6";
				event.preventDefault();
			} else if (form.userid.value === '') {
				errorMessage.textContent = '※IDを入力してください！';
				form.userid.style.backgroundColor = "#ffe6e6";
				event.preventDefault();
			} else if (form.password.value === '') {
				errorMessage.textContent = '※PWを入力してください！';
				form.password.style.backgroundColor = "#ffe6e6";
				event.preventDefault();
			}
		};

		form.onreset = function() {
			errorMessage.textContent = null;
			form.userid.style.backgroundColor = null;
			form.password.style.backgroundColor = null;
		};
	</script>
</body>
</html>
