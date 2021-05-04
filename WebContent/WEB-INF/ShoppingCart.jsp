<%@page import="logic.LogicProduct"%>
<%@page import="java.util.HashMap"%>
<%@page import="entities.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link href="style/indexStyle.css" rel="Stylesheet">
<%  LogicProduct logic = new LogicProduct();
	HashMap<Integer,Integer> shoppingCart = new HashMap<>();
	HashMap<Product,Integer> cartToShow =  new HashMap<>(); //This is a Hashmap with K == Product and V == quantity, i didn't kept this into the Session because the Attributes of a Product stored on the DB can change trought a session and the user would end up with two copies of the same Product on the shoppingCart attached to his session.
	float price;
	float totalPrice = 0;
	shoppingCart.putAll((HashMap<Integer,Integer>)request.getSession().getAttribute("shoppingCart"));
	for(HashMap.Entry<Integer,Integer> product : shoppingCart.entrySet()){
		cartToShow.put(logic.idSearch(product.getKey()) , product.getValue());
	} %>
</head>	<!--  talvez pueda directamente redirigir acá desde el index con un <a> tag, ya que el shoppingCart siempre va a estar cargado en la Session -->
<body>
<div class = "header">
		<a href="index.jsp">
		<img class="logo" alt="logo" src="ImageResources/logo-transparent.png">
		</a>
</div>
<div class="cartWrapper">
	<table class="cartTable">
	<thead>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Quantity</th>		
			<th>Price</th>
		</tr>		
	</thead>
	<tbody>
	<% for(HashMap.Entry<Product,Integer> product : cartToShow.entrySet()){  %>
		<% price = product.getValue() * product.getKey().getPrice(); %>
		<% totalPrice += price; %>
		<tr>
			<td>--image--</td>
			<td><%= product.getKey().getName() %></td>
			<td><%= product.getValue() %></td>
			<td><%= "AR$"+price %></td>
		</tr>
	<% } %>
	<tr> <td></td> <td></td> <td><b> Total: </b></td> <td><%= "AR$"+totalPrice %></td> </tr>
	</tbody>
	</table>
<form action="PurchaseDetails" method="post">
	<button type="submit"> Proceed! </button>
</form>
</div>

</body>
</html>