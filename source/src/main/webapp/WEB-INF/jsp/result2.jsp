<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SaleS</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body id="top">
	<div class="wrapper">
		<a href="/webapp/MenuServlet"><p class="title">SaleS</p></a>

		<h1>
			<c:out value="${result.title}" />
		</h1>
		<hr>
		<p>
			<c:out value="${result.message}" />
		</p>
		<a href="${result.backTo}">ログイン画面へ戻る</a>
	</div>

</body>
</html>
