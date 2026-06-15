<%@ page contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ガソリン価格比較</title>
</head>
<body>

<h1>ガソリン価格比較</h1>

<form action="${pageContext.request.contextPath}/GasolineServlet"
      method="post">

スタンド名
<input type="text"
       name="stationname">

<br><br>

価格
<input type="text"
       name="gasolineprice">

<br><br>

<input type="submit"
       value="比較">

</form>

<br>

<c:if test="${not empty error}">
    ${error}
</c:if>

<hr>

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

<hr>

<c:if test="${minGasoline != null}">

<h2>最安値スタンド</h2>

スタンド名：
${minGasoline.stationname}

<br><br>

価格：
${minGasoline.gasolineprice} 円

</c:if>

<hr>

<h2>履歴一覧</h2>

<c:forEach var="g"
           items="${gasolineList}">

スタンド名：
${g.stationname}

<br><br>

価格：
${g.gasolineprice} 円

<br><br>

登録日時：
${g.createddate}

<hr>

</c:forEach>

</body>
</html>