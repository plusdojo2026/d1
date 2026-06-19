<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SaleS_todo</title>
<link rel="stylesheet" href="css/Sales.css">
<style>

</style>
</head>
<body style="text-align:center">
<div class="above">
<img alt="SaleS" src="${pageContext.request.contextPath}/img/SaleS.png" width="200" height="140">
<form id = carfrom method="get" action="/d1/TodoServlet">
  <select class="ca" name="select"onchange="this.form.submit()">
    <c:forEach var="ca" items="${carlist}">
        <option value="${ca.carname}"${select == ca.carname ? 'selected' : ''}>${ca.carname}</option>
    </c:forEach>
	</select>
  </form>
</div>
<hr>

<form id="todo" action="${pageContext.request.contextPath}/TodoServlet" method="post" enctype="multipart/form-data">
	<input type="hidden" name="select" value="${select}">
		外装写真<input type="file" name="outsidephoto" accept="image/*" capture="environment"><br>
	<input type="text" name="outsidememo" placeholder="写真メモ"><br>
	<p>タバコの匂い</P>
	<p>
		あり<input type="checkbox" name="smell">
		なし<input type="checkbox" name="smell2">
	</p>
	<ul style="display:inline-block; text-align:left;">
		<li class="white-box">備品</li>
		<li>ティッシュ</li>
		<li>サングラス</li>
		<li>ゴミ袋</li>
		<li>消臭剤</li>
	</ul>
	<p>備品メモ<input type="text" name="insideitemmemo"><br></p>
	<p>忘れ物確認<input type="checkbox" name="lostitem"><br></p>
	<p>忘れ物メモ<input type="text" name="lostitemmemo"><br></p>
	<p>ガソリン残量<input type="text" name="gasolineamount"><br></p>
	<p>
		<input type="reset"  name="reset" value="クリア">
		<input type="submit" name="submit" value="送信">
	</p>
</form>
</body>
<script>
'use strict';
let form = document.getElementById('todo');
let errorMessage = document.getElementById('error');

form.onsubmit = function(event) {
	if (form.smell.value === '' && form.gasolineamount.value === '') {
		errorMessage.textContent = '※タバコ有無とガソリン残量確認にチェックを入れてください';
		form.userid.style.backgroundColor = "#ffe6e6";
		form.password.style.backgroundColor = "#ffe6e6";
		event.preventDefault();
	} else if (form.smell.value === '') {
		errorMessage.textContent = '※タバコ有無を入力してください！';
		form.smell.style.backgroundColor = "#ffe6e6";
		event.preventDefault();
	} else if (form.gasolineamount.value === '') {
		errorMessage.textContent = '※ガソリン残量確認を入力してください';
		form.gasolineamount.style.backgroundColor = "#ffe6e6";
		event.preventDefault();
	}
};

form.onreset = function() {
	errorMessage.textContent = null;
	form.userid.style.backgroundColor = null;
	form.password.style.backgroundColor = null;
};
</script>
</html>