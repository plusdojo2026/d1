<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SaleS_todo</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/Sales.css">
<link rel="stylesheet" href="css/style.css">
<style>
#todo {
    width: 400px;
    margin: 0 auto;
    text-align: left;
}
.item-list{
    margin: 0;
    padding-left: 20px;
}
.white-box{
    list-style:none;
    position:relative;
}

.white-box::before{
    content:"";
    position:absolute;
    left:-18px;
    top:8px;
    width:10px;
    height:10px;
    border:1px solid #000;
    background:#fff;
}
.button-area {
	display: flex;
	gap: 12px;
	justify-content: flex-start; /* 左揃え */
	align-items: center;
	margin-top: 10px;
}
.button-area input {
	min-width: 140px;
	padding: 10px 24px;
	font-size: 14px;
	cursor: pointer;
	border: 1px solid #ccc;
	border-radius: 4px;
	background: #f5f5f5;
}
.button-area input:hover {
  background: #f0566e;
}
.above {
    text-align: center;
}
.above form {
    display: inline-block;
    text-align: left;
}
input[name="lostitem"] {
    margin-left: 8px;
}
input[name="lostitemmemo"] {
    margin-top: 0;
}
</style>
</head>
<body class="body">
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
<hr>

<form id="todo" action="${pageContext.request.contextPath}/TodoServlet" method="post" 
				enctype="multipart/form-data"
				onsubmit="return validateForm()">
	<input type="hidden" name="select" value="${select}">
		<h3>Todoフォーム</h3>
		外装
		<p><input type="file" name="outsidephoto" accept="image/*"><br></p>
	<input type="text" name="outsidememo" placeholder="写真メモ"><br>
	<p>タバコの匂い</P>
	<p>
		あり<input type="radio" name="smell" value="1">
		なし<input type="radio" name="smell" value="0">
	</p>
	<ul style="display:inline-block; text-align:left;" class="item-list">
		<li class="white-box">備品</li>
		<li>ティッシュ</li>
		<li>サングラス</li>
		<li>ゴミ袋</li>
		<li>消臭剤</li>
	</ul>
	<p><input type="text" name="insideitemmemo" placeholder="備品メモ"><br></p>
	<p>忘れ物確認<input type="checkbox" name="lostitem" value="1"><br></p>
	<p><input type="text" name="lostitemmemo" placeholder="忘れ物メモ"><br></p>
	<p>ガソリン残量入力<input type="text" name="gasolineamount" placeholder="メモリ数を記入"><br></p>
	<div class="button-area">
	  <input type="reset" name="reset" value="クリア" onclick="clearError()">
	  <input type="submit" name="submit" value="送信">
	 <p id="errorMsg" style="color: red;"></p>
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