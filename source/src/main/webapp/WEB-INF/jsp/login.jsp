<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>車管理アプリ</title>
</head>
<body>
<h1>ログイン</h1>
<p>※ID=id, PW=password でログインできます。
<hr>
<form method="POST" action="/d1/LoginServlet">
<p>
	ユーザーID<br>
	<input type="text" name="userid">
</p>
<p>
	パスワード<br>
	<input type="password" name="password">
</p>
<input type="submit" name="login" value="ログイン">
<p>ログイン情報を忘れてしまった場合はこちらをクリック</p>
</form>
<script>
'use strict';
document.getElementById('form').onsubmit = function(event) {
    let id = document.getElementById('form').id.value;
    let pw = document.getElementById('form').pw.value;
    if (id === '' || pw === '') {
        document.getElementById('msg').textContent = 'ユーザーIDとパスワードを入力してください！';
        event.preventDefault();
    }
};
</script>
</body>
</html>
