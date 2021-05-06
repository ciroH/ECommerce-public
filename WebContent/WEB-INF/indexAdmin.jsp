<%@page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/style/AdminStyle.css" rel="Stylesheet">
<% User admin = (User)session.getAttribute("user"); %>
<title><%= "Welcome, "+ admin.getName() %></title>
</head>
<body>
	<div class="header">
<!--	<img class="logo" alt="logo" src="${pageContext.request.contextPath}/ImageResources/logo-transparent.png"> -->
	</div>
	<div class="menu">
		<form action="Menu" method="post">
			<input name="option" type="hidden" value="index">
			<a type="submit">Home Page</a>
		</form>
		<form action="Menu" method="post">
			<input name="option" type="hidden" value="products">
			<button type="submit"> Products </button>
		</form>
		<form action="Menu" method="post">
			<input name="option" type="hidden" value="">
			<a type="submit"></a>
		</form>
		<form action="Menu" method="post">
			<input name="option" type="hidden" value="">
			<a type="submit"></a>
		</form>
		<form action="Menu" method="post">
			<input name="option" type="hidden" value="">
			<a type="submit"></a>
		</form>
		<form action="LogOut" method="post">
		<button type="submit">Close Session</button>
		</form>
<!-- 	<br>
	-ver el index
	-productos (submenú para alta,baja,consulta,modificación)(o hacer simplemente una consulta y presentar las opciones de alta, baja, modificacion y filtrado dentro de la propia pagina de la tabla)
	-log de cambios a productos
	-transacciones recientes (o todas)(+ grafica de tiempo)
	-lista de users
 -->
	</div>
</body>
</html>