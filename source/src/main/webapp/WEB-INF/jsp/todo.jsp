<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SaleS</title>
</head>
<body>
<h1>Todoフォーム</h1>
<hr>
<form method="POST" action="/d1/TodoServlet">
outsidephoto
outsidememo
smell
insideitemmemo
gasolineamount
lostitem
lostitemmemo
外装写真<input type="text" name="company"><br>
ガソリン残量<input type="text" name="department"><br>
外装メモ<input type="text" name="position"><br>
タバコ匂い<input type="text" name="name"><br>
備品メモ<input type="text" name="zipcode"><br>
忘れ物確認<input type="text" name="address"><br>
忘れ物メモ<input type="text" name="phone"><br>
<p>
	<input type="submit" name="regist" value="検索">
	<input type="submit" name="regist" value="検索">
</p>
</form>
</body>
</html>
