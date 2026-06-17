<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SaleS_todo</title>
</head>
<body style="text-align:center">
<img alt="SaleS" src="${pageContext.request.contextPath}/img/SaleS.png">
<form id = carfrom method="get" action="/d1/TodoServlet">
  <select class="ca" name="select"onchange="this.form.submit()">
    <c:forEach var="ca" items="${carlist}">
        <option value="${ca.carname}"${select == ca.carname ? 'selected' : ''}>${ca.carname}</option>
    </c:forEach>
	</select>
  </form>
<hr>

<form action="${pageContext.request.contextPath}/TodoServlet" method="post" enctype="multipart/form-data">
	<input type="hidden" name="select" value="${select}">
		外装写真<input type="file" name="outsidephoto" accept="image/*" capture="environment"><br>
	<input type="text" name="outsidememo" placeholder="写真メモ"><br>
	タバコの匂い
	<p>
		あり<input type="checkbox" name="smell">
		なし<input type="checkbox" name="smell2">
	</p>
	備品メモ<input type="text" name="insideitemmemo"><br>
	忘れ物確認<input type="checkbox" name="lostitem"><br>
	忘れ物メモ<input type="text" name="lostitemmemo"><br>
	ガソリン残量<input type="text" name="gasolineamount"><br>
	<p>
		<input type="submit" name="submit" value="キャンセル">
		<input type="submit" name="submit" value="送信">
	</p>
</form>
</body>
</html>