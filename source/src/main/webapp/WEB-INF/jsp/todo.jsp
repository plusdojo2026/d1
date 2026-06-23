<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SaleS_todo</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/todo.css">

</head>
<body class="body">

<header class="header">
<img alt="SaleS" src="${pageContext.request.contextPath}/img/SaleS.png">
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
<form id = carfrom method="get" action="/d1/TodoServlet">
  <select class="ca" name="select"onchange="this.form.submit()">
    <c:forEach var="ca" items="${carlist}">
        <option value="${ca.carname}"${select == ca.carname ? 'selected' : ''}>${ca.carname}</option>
    </c:forEach>
	</select>
  </form>

<form id="todo" action="${pageContext.request.contextPath}/TodoServlet" method="post" 
				enctype="multipart/form-data"
				onsubmit="return validateForm()">
	<input type="hidden" name="select" value="${select}">
	<h3>Todoフォーム</h3>
	<table class="table-todo">
	<tr>
		<th>外装</th>
		<td><input type="file" name="outsidephoto" accept="image/*"><td>
	</tr>
		<tr>
		<th></th>
		<td><input type="text" name="outsidememo" placeholder="写真メモ"></td>
		</tr>
	<tr>
		<th>タバコの匂い</th>
		<td>あり<input type="radio" name="smell" value="1"></td>
		<td>なし<input type="radio" name="smell" value="0"></td>
	</tr>
	<tr class="item-list">
		<th><input type="checkbox" name="itemlist">備品</th>
				<td>
					<ul>
					<li>ティッシュ</li>
					<li>サングラス</li>
					<li>ゴミ袋</li>
					<li>消臭剤</li>
					</ul>
				</td>
				<td><input type="text" name="insideitemmemo" placeholder="備品メモ"></td>
	</tr>

	<tr>
	<th><input type="checkbox" name="lostitem" value="1">忘れ物確認</th>

		<td><input type="text" name="lostitemmemo" placeholder="忘れ物メモ"></td>
	</tr>
	<tr>
	<th>ガソリン残量入力</th>
	<td><input type="text" name="gasolineamount" placeholder="メモリ数を記入"></td>
	</tr>
	</table>
	<div class="button-area">
	  <input type="reset" name="reset" value="クリア" onclick="clearError()">
	  <input type="submit" name="submit" value="送信">
	 <p id="errorMsg"></p>
	</div>
</form>
</body>
<script>
	function validateForm() {

		const photo = document.forms[1].outsidephoto.value;
		const smell = document.forms[1].smell.value;
		const lostitem = document.forms[1].lostitem.checked;
		const gasoline = document.forms[1].gasolineamount.value;

		const error = document.getElementById("errorMsg");

		error.innerHTML = "";

		if (!photo) {
			error.innerHTML = "外装写真を選択してください";
			return false;
		}

		if (!smell) {
			error.innerHTML = "タバコの匂いを選択してください";
			return false;
		}

		if (!lostitem) {
			error.innerHTML = "忘れ物確認をチェックしてください";
			return false;
		}

		if (!gasoline) {
			error.innerHTML = "ガソリン残量を入力してください";
			return false;
		}

		return true;
	}
	function clearError() {
		document.getElementById("errorMsg").innerHTML = "";
	}
</script>
</html>