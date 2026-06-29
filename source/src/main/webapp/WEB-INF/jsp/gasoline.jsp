<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/gasoline.css">
<head>
<meta charset="UTF-8">
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
<title>ガソリン価格比較</title>
</head>
<body class="body">



	<form action="${pageContext.request.contextPath}/GasolineServlet"
		method="post" onsubmit="return validateForm()">
		ガソリンスタンド名 <br> <input type="text" name="stationname"> <br>

		ガソリン価格 <br> <input type="text" name="gasolineprice"> <br>
		<div class="button-group">
			<input type="reset" name="reset" value="クリア" onclick="clearError()">
			<input type="submit" value="比較">
		</div>

	</form>
	<div id="errorMsg" style="color: red;"></div>
	<br>

	<c:if test="${minGasoline != null}">
		<div class="result-box">
			<h3>最安値</h3>
			${minGasoline.stationname}<br>
			今日の最安値：${minGasoline.gasolineprice}円<br>
			<div class="avg-price">ガソリン平均価格：${avgPrice}円</div>
			<br>
			<c:set var="diff" value="${minGasoline.gasolineprice - avgPrice}" />

			<c:choose>
				<c:when test="${diff > 0}">
					<div class="diffprice plus">差額：${diff}円</div>
				</c:when>

				<c:when test="${diff < 0}">
					<div class="diffprice minus">差額：${diff}円</div>
				</c:when>

				<c:otherwise>
					<div class="diffprice">±0円</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>


	<h2>ガソリンスタンド一覧</h2>

	<div class="history-container">
		<c:forEach var="gasoline" items="${gasolineList}">
			<div class="history-card">
				<p>スタンド名：${gasoline.stationname}</p>
				<p>価格：${gasoline.gasolineprice}円</p>
				<p>差額：${gasoline.resultMessage}</p>
			</div>
		</c:forEach>
	</div>
</body>
<script>
	function validateForm() {
		const station = document.forms[0].stationname.value;
		const price = document.forms[0].gasolineprice.value;
		const errorMsg = document.getElementById("errorMsg");

		errorMsg.innerHTML = ""; // 毎回リセット

		// スタンド名チェック
		if (station.trim() === "") {
			errorMsg.innerHTML = "ガソリンスタンド名を入力してください";
			return false;
		}

		// 価格未入力
		if (price.trim() === "") {
			errorMsg.innerHTML = "価格を入力してください";
			return false;
		}

		// 数字チェック
		if (!/^[0-9]+$/.test(price)) {
			errorMsg.innerHTML = "価格は数字で入力してください";
			return false;
		}

		// 0以下チェック
		if (parseInt(price) <= 0) {
			errorMsg.innerHTML = "価格は1以上を入力してください";
			return false;
		}

		return true;

	}
	function clearError() {
		const errorMsg = document.getElementById("errorMsg");
		errorMsg.innerHTML = "";
	}
</script>
</html>