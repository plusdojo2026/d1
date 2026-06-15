<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>車種別</title>
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
                    <a href="/meishi/HomeServlet">ホームページ</a>
                </li>                
                <li>
                    <a href="/meishi/LoginServlet">ログイン</a>
                </li>
                <li>
                    <a href="/meishi/SearchServlet">検索</a>
                </li>
                <li>
                    <a href="/meishi/RegistServlet">登録</a>
                </li>
                <li>
                    <a href="/meishi/itiranServlet">一覧</a>
                </li>                
            </ul>
        </div>
        <!--ここまでメニュー-->
    </div>

 <select name="kind" class="selectbox">
	<option value="1">プリウス</option>
	<option value="2">軽</option>
	<option value="3">アリウス</option>
	<option value="4">BMW</option>
</select>
</head>
<body>
<form method="POST" action="/d1/CarsServlet">
<img src="c:\Users\User\Documents\サンプル\site\images\home-hero.jpg" alt="外装写真" class="photo">

<h2>現在の状況</h2>
<div class="info-box">
    車内の状況と利用者履歴を閲覧することが出来ます。
</div>
<table class="table-view">
	<tr>
		<th>チェック項目</th>
		<th>状態</th>
	</tr>
	<tr>
		<td>外装</td>
		<td>変化なし</td>
	</tr>
	<tr>
		<td>匂い</td>
		<td>user2</td>
	</tr>
	<tr>
		<td>ガソリン</td>
		<td>user3</td>
	</tr>
    <tr>
		<td>忘れ物</td>
		<td>user3</td>
	</tr>
</table>

<img src="" alt="todoフォーム写真" class="photo">

<table class="table-view">
	<tr>
		<th>利用履歴</th>
		<th>利用者</th>
	</tr>
	<tr>
		<td>2026/06/15</td>
		<td>user1</td>
	</tr>
	<tr>
		<td>2026/06/10</td>
		<td>user2</td>
	</tr>
	<tr>
		<td>2026/06/05</td>
		<td>user3</td>
	</tr>
</table>
</form>
</body>
</html>