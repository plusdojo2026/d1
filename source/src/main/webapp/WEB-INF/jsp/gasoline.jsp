<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ガソリン価格比較</title>
</head>
<body>

	<h1>ガソリン価格比較</h1>

	<form action="${pageContext.request.contextPath}/GasolineServlet"
		method="post" onsubmit="return validateForm()">
		ガソリンスタンド名 <br> <input type="text" name="stationname"> <br>

		ガソリン価格 <br> <input type="text" name="gasolineprice"> <br>
		<input type="reset" name="reset" value="クリア" onclick="clearError()">
		<input type="submit" value="比較">

	</form>
	<div id="errorMsg" style="color: red;"></div>
	<br>

	<c:if test="${minGasoline != null}">
		<br>結果
		<br> ガソリンスタンド名： ${minGasoline.stationname}
		<br>今日の最安値は ${minGasoline.gasolineprice} 円
		<br> ガソリン平均価格： ${avgPrice} 円
		<br> 差額： ${minGasoline.gasolineprice - avgPrice}
	</c:if>


	<h2>履歴一覧</h2>

	<c:forEach var="gasoline" items="${gasolineList}">
	スタンド名：${gasoline.stationname}
	<br>
	価格：${gasoline.gasolineprice}
	<br>
	結果：${gasoline.resultMessage}
	<br>
		<br>
	</c:forEach>
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