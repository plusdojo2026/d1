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
				<li><a href="/d1/StartendServlet">開始/終了</a></li>
				<li><a href="/d1/LoginServlet">🔪ログアウト</a></li>
				<li><a href="/d1/ContactServlet">❓お問い合わせ</a></li>
			</ul>
		</div>
		<!--ここまでメニュー-->
	</div>
</header>
<title>ガソリン価格比較</title>
</head>
<body>

	<form onsubmit="return comparePrice(event)">
		ガソリンスタンド名 <br> <input type="text" name="stationname" id="stationname"> <br>

		ガソリン価格 <br> <input type="text" name="gasolineprice" id="gasolineprice"> <br>
		<div class="button-group">
			<input type="reset" name="reset" value="クリア" onclick="clearError()">
			<input type="button" value="比較" onclick="comparePrice(event)">
		</div>

	</form>
	<div id="errorMsg" style="color: red;"></div>
	<br>

	<div id="resultBox" class="result-box" style="display: none;">
		<h3>最安値</h3>
		<p id="minStationName"></p>
		<p id="minPrice"></p>
		<div class="avg-price" id="avgPriceDisplay"></div>
		<br>
		<div id="diffPriceDisplay"></div>
	</div>

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
	async function comparePrice(event) {
		event.preventDefault();
		
		const station = document.getElementById("stationname").value;
		const price = document.getElementById("gasolineprice").value;
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

		try {
			// APIからガソリン平均価格を取得
			console.log("APIを呼び出しています...");
			const apiResponse = await fetch("/d1/GetGasolineApi");
			
			if (!apiResponse.ok) {
				throw new Error("API呼び出しに失敗しました");
			}
			
			const apiData = await apiResponse.json();
			console.log("APIレスポンス:", apiData);
			
			const avgPrice = apiData.averagePrice;
			console.log("平均価格:", avgPrice);

			// 差額を計算
			const diff = parseInt(price) - avgPrice;

			// 最安値エリアを表示
			const resultBox = document.getElementById("resultBox");
			document.getElementById("minStationName").textContent = "スタンド名：" + station;
			document.getElementById("minPrice").textContent = "今日の最安値：" + price + "円";
			document.getElementById("avgPriceDisplay").textContent = "ガソリン平均価格：" + avgPrice + "円";

			// 差額の表示
			const diffDisplay = document.getElementById("diffPriceDisplay");
			if (diff > 0) {
				diffDisplay.innerHTML = '<div class="diffprice plus">差額：+' + diff + '円</div>';
			} else if (diff < 0) {
				diffDisplay.innerHTML = '<div class="diffprice minus">差額：' + diff + '円</div>';
			} else {
				diffDisplay.innerHTML = '<div class="diffprice">差額：±0円</div>';
			}

			resultBox.style.display = "block";

			// サーバーにデータを送信（DB保存）
			const formData = new FormData();
			formData.append("stationname", station);
			formData.append("gasolineprice", price);

			const response = await fetch("/d1/GasolineServlet", {
				method: "POST",
				body: formData
			});

			if (response.ok) {
				console.log("データがサーバーに送信されました");
				// 1秒後にページをリロード
				setTimeout(() => {
					location.reload();
				}, 1000);
			}

		} catch (error) {
			console.error("エラー:", error);
			errorMsg.innerHTML = "エラーが発生しました：" + error.message;
		}

		return false;
	}

	function clearError() {
		const errorMsg = document.getElementById("errorMsg");
		errorMsg.innerHTML = "";
	}
</script>
</html>