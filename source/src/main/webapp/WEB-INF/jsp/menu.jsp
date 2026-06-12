<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
/*
 * Servletから渡されたsortを受け取る
 * 今どの並び順か保持するため
 */
String sort = (String) request.getAttribute("sort");
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム | 名刺管理</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body id="top">
	<div class="wrapper">
		<!-- ヘッダー（ここから） -->

		<a href="/webapp/MenuServlet"><p class="title">名刺管理アプリ</p></a>


		<ul id="nav">
			<li><a href="/webapp/MenuServlet">ホーム</a></li>
			<li><a href="/webapp/SearchServlet">検索（更新／削除）</a></li>
			<li><a href="/webapp/RegistServlet">登録</a></li>
			<!--<li><a href="edit.html">編集</a></li>-->
			<li><a href="/webapp/MenuServlet#list">一覧表示</a></li>
			<li><a href="/webapp/LogoutServlet">ログアウト</a></li>
		</ul>

		<!-- ヘッダー（ここまで） -->
		<!-- メイン（ここから） -->
		<main>
			<!-- アプリ説明 -->
			<h2>名刺管理アプリの機能</h2>
			<ul>
				<li>検索ページで、名刺データの検索ができます。</li>
				<li>検索結果ページで、名刺データの更新と削除ができます。</li>
				<li>登録ページで、名刺データの登録ができます。</li>
			</ul>
			<!-- 画像表示 -->
			<img src="<%=request.getContextPath()%>/img/te.png" class="photo">

			<!-- 名刺一覧 -->
			<h2 id="list">名刺一覧</h2>
			<!-- 並び替えフォーム -->
			<form method="GET" action="/webapp/MenuServlet">
				<!-- onchange選択したら自動送信 -->
				<select name="sort" id="sort" onchange="this.form.submit()">
					<option value="" disabled selected>並び替え</option>
					<!-- 新しい順 -->
					<option value="new" <%="new".equals(sort) ? "selected" : ""%>>新しい順</option>
					<!-- 古い順 -->
					<option value="old" <%="old".equals(sort) ? "selected" : ""%>>古い順</option>
					<!--会社名順 
					<option value="company"
						<%="company".equals(sort) ? "selected" : ""%>>会社名順</option>-->
					<!-- お気に入りのみ -->
					<option value="favorite"
						<%="favorite".equals(sort) ? "selected" : ""%>>お気に入り</option>
				</select>
			</form>

			<!--ここに登録した名刺を表示-->
			<div class="scroll" id="list2">
				<!-- cardListを一件ずつ取り出す -->
				<c:forEach var="e" items="${cardList}">
					<!-- 名刺一件分 -->
					<div class="bc_data">
						<form method="POST" action="/webapp/UpdateDeleteServlet"
							class="search_result">
							<!-- hiddenで番号保持 -->
							<input type="hidden" name="number" value="${e.number}" readonly>
							番号：<input type="text" name="number1" value="${e.number}" readonly><br>
							会社名：<input type="text" name="company" value="${e.company}"
								readonly><br> 部署名：<input type="text"
								name="department" value="${e.department}" readonly><br>
							役職名：<input type="text" name="position" value="${e.position}"
								readonly><br> 氏名：<input type="text" name="name"
								value="${e.name}" readonly><br> 住所：<input
								type="text" name="address" value="${e.address}" readonly><br>〒<input
								type="text" name="zipcode" value="${e.zipcode}" readonly><br>
							☎<input type="text" name="phone" value="${e.phone}" readonly><br>
							✉<input type="text" name="email" value="${e.email}" id="email"
								readonly><br> 交換日：<input type="date"
								name="exchangedate" value="${e.exchangedate}" readonly><br>
							備考：<input type="text" name="remarks" value="${e.remarks}"
								readonly><br> お気に入り<input type="checkbox"
								name="favorite" value="true" disabled
								<c:if test="${e.favorite}">checked</c:if>><br>
						</form>
					</div>
				</c:forEach>

			</div>
			<!-- ページトップへ戻る -->
			<p>
				<a href="#top"><img src="img/yazirusiue.png" alt="ページトップに戻る"
					class="yazirusiue"> </a>
			</p>

		</main>
		<!-- メイン（ここまで） -->
		<!-- フッター（ここから） -->

		<footer id="footer">
			<p></p>
		</footer>
		<!-- フッター（ここまで） -->
	</div>

	<script>
		
	</script>
</body>
</html>