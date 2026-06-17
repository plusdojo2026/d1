<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SaleS_todo</title>
</head>
<body>
<h1>Todoフォーム</h1>
<hr>
<form action="${pageContext.request.contextPath}/TodoServlet" method="post">
外装写真<input type="text" name="outsidephoto"><br>
ガソリン残量<input type="text" name="gasolineamount"><br>
外装メモ<input type="text" name="outsidememo"><br>
タバコ匂い<input type="checkbox" name="smell"><br>
備品メモ<input type="text" name="insideitemmemo"><br>
忘れ物確認<input type="checkbox" name="lostitem"><br>
忘れ物メモ<input type="text" name="lostitemmemo"><br>
<input type="text" name="todoid">
<input type="text" name="carid">
<input type="text" name="userid">
<p>
	<input type="submit" name="submit" value="送信">
	<input type="submit" name="submit" value="キャンセル">
</p>
</form>
</body>
</html>