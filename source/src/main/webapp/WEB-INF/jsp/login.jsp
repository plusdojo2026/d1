<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>車管理アプリ</title>
</head>
<body>
	<h1>ログイン</h1>
	<p>※ID=user01, PW=pass01 でログインできます。
	<hr>
	<form id="login" method="POST" action="/d1/LoginServlet">
		<p>
			ユーザーID<br> <input type="text" name="userid">
		</p>
		<p>
			パスワード<br> <input type="password" name="password">
		</p>
		<input type="submit" name="submit" value="ログイン"> <input
			type="reset" name="reset" value="リセット"> <span id="error"
			class="error"></span>
		<p>ログイン情報を忘れてしまった場合はこちらをクリック</p>
	</form>
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
