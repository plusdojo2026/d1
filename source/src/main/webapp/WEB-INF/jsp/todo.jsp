<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<form id=carfrom method="get" action="/d1/TodoServlet">
		<select class="ca" name="select" onchange="this.form.submit()">
			<c:forEach var="ca" items="${carlist}">
				<option value="${ca.carname}"
					${select == ca.carname ? 'selected' : ''}>${ca.carname}</option>
			</c:forEach>
		</select>
	</form>

	<form id="todo" action="${pageContext.request.contextPath}/TodoServlet"
		method="post" enctype="multipart/form-data"
		onsubmit="return validateForm()">
		<input type="hidden" name="select" value="${select}">
		<h3>Todoフォーム</h3>


		<div class="form-group">
			<label>外装写真</label> <input type="file" name="outsidephoto"
				accept="image/*">
		</div>

		<div class="form-group">
			<label>写真メモ</label> <input type="text" name="outsidememo"
				placeholder="写真メモ">
		</div>

		<div class="form-group">
			<div class="title">タバコの匂い</div>
			<div class="form-inline">
				<label><input type="radio" name="smell" value="1">
					あり</label> <label><input type="radio" name="smell" value="0">
					なし</label>
			</div>
		</div>

		<div class="form-group">
			<div class="title">
				<label> <input type="checkbox" name="equipmentcheck">
					備品
				</label>
			</div>
			<ul>
				<li>ティッシュ</li>
				<li>サングラス</li>
				<li>ゴミ袋</li>
				<li>消臭剤</li>
			</ul>
			<input type="text" name="insideitemmemo" placeholder="備品メモ">
		</div>

		<div class="form-group">
			<label> <input type="checkbox" name="lostitem" value="1">
				忘れ物確認
			</label> <input type="text" name="lostitemmemo" placeholder="忘れ物メモ">
		</div>

		<div class="form-group">
			<label>ガソリン残量</label> <input type="number" min="0" max="100"
				name="gasolineamount" placeholder="メモリ数を記入">
		</div>
		<p id="errorMsg"></p>
		<div class="button-area">
			<input type="submit" value="送信"> <input type="reset"
				value="クリア" onclick="clearError()">
		</div>


	</form>
</body>
<script>
	function validateForm() {

		const photo = document.forms[1].outsidephoto.value;
		const smell = document.forms[1].smell.value;
		const equipmentcheck = document.forms[1].equipmentcheck.checked;
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
		if (!equipmentcheck) {
			error.innerHTML = "備品確認をチェックしてください";
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