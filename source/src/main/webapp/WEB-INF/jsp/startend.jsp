<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
input[type="datetime-local"]::-webkit-calendar-picker-indicator {
    display: none;
}

</style>

<title>Insert title here</title>

</head>
<body>

<p id="realtime"></p><br>
<c:forEach var="syo" items="${reservelist}">

  開始日時${syo.sdate.toString().replace('T', ' ')} ～ 終了日時${syo.fdate.toString().replace('T', ' ')}
  使用車${syo.carname}
  目的<input type="text" name="purpose" value="${syo.purpose}"><br>
  </c:forEach>
 
 <script>

 function showClock() {
     let nowTime = new Date();
     let nowHour = nowTime.getHours();
     let nowMin  = nowTime.getMinutes();
     let nowSec  = nowTime.getSeconds();
     let msg = "現在時刻：" + nowHour + ":" + nowMin + ":" + nowSec;
     document.getElementById("realtime").innerHTML = msg;
   }
   setInterval('showClock()',1000);

showClock();
</script>
</body>

</html>