<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>開始終了</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/startend.css">
</head>
<body class="body">
<header class="header">
	<a href="${pageContext.request.contextPath}/HomeServlet"> <img
		alt="SaleS" src="${pageContext.request.contextPath}/img/SaleS.png">
	</a>
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
                    <a href="/d1/ReserveServlet">:date:予約</a>
                </li>                
                <li>
                    <a href="/d1/GasolineServlet">:fire:ガソリン</a>
                </li>
                <li>
                    <a href="/d1/TodoServlet">:white_check_mark:TO DO</a>
                </li>
                <li>
                    <a href="/d1/StartendServlet">開始/終了</a>
                </li>
                <li>
                    <a href="/d1/LoginServlet">:end:ログアウト</a>
                </li>
                <li>
                    <a href="/d1/ContactServlet">:question:お問い合わせ</a>
                </li>                 
            </ul>
        </div>
        <!--ここまでメニュー-->
    </div>
</header>
<p id="time">現在時刻</p>
<p id="realtime"></p><br>

<c:forEach var="top1" items="${top1}">
  <p id = "finish">終了日時${top1.fdate.toString().substring(5, 16).replace('T', ' ')}</p>

  
<br>
  </c:forEach><br>
<div class="info-a">
<c:forEach var="syo" items="${reservelist}">
<table class="table-info">
	<tr>
		<th>日程</th>
		<td>${syo.sdate.toString().replace('T', ' ')} ～ ${syo.fdate.toString().replace('T', ' ')}</td>
	</tr>
	<tr>
		<th>使用車</th>
		<td>${syo.carname}</td>
	</tr>
	<tr>
		<th>目的</th>
		<td>${syo.purpose}</td>
	</tr>

	</table>
  </c:forEach>
 </div>
  <div class="button-group">
	  <div class="btn-engine-start">
	  <form action="/d1/StartendServlet" method="post">
	  <input type="hidden" name="action" value="start">
	  	<c:forEach var="reserve" items="${starts}">
			<input type="text" name="reservenumber"
				value="${reserve.reservenumber}">
		</c:forEach>
	  <button type="submit" class="btn btn-engine-start-in" onclick="showText()" id = "start" ${hasReserve ? '' : 'disabled'}>START<br><span>ENGINE</span></button>
	  </form>
	  </div>
	   <div class="btn-engine-start">
	   	 <form action="/d1/StartendServlet" method="post">
	  		<input type="hidden" name="action" value="end">
	  
	  	<c:forEach var="re" items="${top1}">
			<input type="text" name="reservenumber"
				value="${re.reservenumber}">
		</c:forEach>
	  	<button type ="submit" ${hasTodo ? '' : 'disabled'} class="btn btn-engine-start-in" id = "ed">STOP<br><span>ENGINE</span></button>
	  	</form>
	  </div>
  </div>
  	  <c:if test="${empty reservelist}">
		<p style="color: red;">予約していません</p>
		</c:if>
		<c:if test="${empty starts}">
		<p style="color: red;">開始できません</p>
		</c:if>
 
 <script>
 function showText() {
     
     document.getElementById("finish").style.display = "block";
 }


 function showClock() {
     let nowTime = new Date();
     let nowHour = nowTime.getHours();
     let nowMin  = nowTime.getMinutes();
     let nowSec  = nowTime.getSeconds();
     let msg = nowHour + ":" + nowMin + ":" + nowSec;
     document.getElementById("realtime").innerHTML = msg;
   }
   setInterval('showClock()',1000);

showClock();
</script>
</body>

</html>