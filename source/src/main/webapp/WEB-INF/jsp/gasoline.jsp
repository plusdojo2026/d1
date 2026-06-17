<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ガソリン価格比較</title>

<style>

body{
    text-align:center;
    font-family:sans-serif;
}

/* ロゴ */
.logo{
    width:200px;
    height:auto;
    margin-top:10px;
    margin-bottom:20px;
}

.error{
    color:red;
}

.card{
    display:inline-block;
    border:1px solid black;
    border-radius:10px;
    padding:10px;
    margin:5px;
    width:100px;
}

.result{
    background-color:#e8dd88;
    border-radius:15px;
    padding:15px;
    width:250px;
    margin:20px auto;
}

</style>

</head>
<body>

<!-- ロゴ表示 -->
<img src="images/SaleS.png"
     alt="ロゴ"
     class="logo">

<form action="GasolineServlet"
      method="post">

スタンド名

<br>

<input type="text"
       name="stationname">

<br><br>

価格

<br>

<input type="text"
       name="gasolineprice">

<br><br>

<input type="reset"
       value="クリア">

<input type="submit"
       value="比較">

</form>

<br>

<c:if test="${not empty error}">
    <div class="error">
        ${error}
    </div>
</c:if>

<div class="result">

<h3>結果</h3>

スタンド名：
${stationname}

<br><br>

<c:if test="${minGasoline != null}">
最安値：
${minGasoline.gasolineprice} 円
</c:if>

<br><br>

平均価格：
${avgPrice} 円

<br><br>

差額：
${diff} 円

<br><br>

結果：
${resultMessage}

</div>

<c:if test="${minGasoline != null}">

<h3>最安値スタンド</h3>

スタンド名：
${minGasoline.stationname}

<br>

価格：
${minGasoline.gasolineprice} 円

<br><br>

</c:if>

<h3>履歴一覧</h3>

<table border="1">

<tr>
    <th>スタンド名</th>
    <th>価格</th>
</tr>

<c:forEach var="g"
           items="${gasolineList}">

<tr>
    <td>${g.stationname}</td>
    <td>${g.gasolineprice}円</td>
</tr>

</c:forEach>

</table>