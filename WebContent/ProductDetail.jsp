<%@page import="entities.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% Product product = (Product)request.getAttribute("product"); %>
<link href="style/indexStyle.css" rel="Stylesheet">
<link href="style/productStyle.css" rel="Stylesheet">
</head>
<body>
<div class = "header">
		<a href="index.jsp">
		<img class="logo" alt="logo" src="ImageResources/logo-transparent.png">
		</a>
</div>
<div id="productWrapper">
<div id="photoWrapper">

</div>

<div id="priceWrapper"> 
<% if (product.getOldPrice() != 0.0){ %>
	<%= "AR$"+product.getOldPrice() %>
<% } %>
<h1> <%= "AR$"+product.getPrice() %> </h1>
<% if(session.getAttribute("user")!= null){ %>
<form action="ManageCart" method="get">
	<input name="id" type="hidden" value=<%= product.getId() %> >
	<select name="quantity">
		<% for(int q=1;q<=product.getStock();q++){ %>
		<option value="<%= q %>" > <%= q %> </option>
		<% } %>
	</select>
	<input type="submit" value="Add to cart!"> 
</form>
<% } %>
</div>

<div id="descWrapper">
<%= product.getDescription() %>
</div>
</div>
</body>
</html>