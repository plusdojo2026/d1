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
                <p>目次</p>
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

 <select name="kind">
	<option value="1">プリウス</option>
	<option value="2">軽</option>
	<option value="3">アリウス</option>
	<option value="4">BMW</option>
</select>
</head>
<body>
<form method="POST" action="/d1/CarsServlet">
<img src="" alt="外装写真">

<h2>現在の状況</h2>
<p>利用状況</p>
<div class="card-view">
<div class="card-content">
    <div class="card-field">
        <label>外装</label>
        <value>${e.company}</value>
    </div>
    <div class="card-field">
        <label>匂い</label>
        <value>${e.name}</value>
    </div>
    <div class="card-field">
        <label>備品</label>
        <value>${e.phone}</value>
    </div>
    <div class="card-field">
        <label>ガソリン</label>
        <value>${e.email}</value>
    </div>
    <div class="card-field">
        <label>忘れ物</label>
        <value></value>
    </div>
</div>
</div>

<img src="" alt="todoフォーム写真" class="photo">

<div>
<p>利用者履歴</p>

</div>
</form>
</body>
</html>