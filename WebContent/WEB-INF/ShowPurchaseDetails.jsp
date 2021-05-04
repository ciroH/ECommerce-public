<%@page import="entities.Transaction"%>
<%@page import="entities.Address"%>
<%@page import="entities.Card"%>
<%@page import="entities.User"%>
<%@page import="entities.Product"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Purchase Confirmation</title>
<%  HashMap<Product,Integer> cartToShow = new HashMap<>();
   	User user = (User)session.getAttribute("user");
	Card card = (Card)session.getAttribute("card");
	boolean showCard = false;
	boolean showAddress = false;
	Address address = (Address)session.getAttribute("address");
	Transaction transaction = (Transaction)session.getAttribute("transaction");
	float price;
	float totalPrice = 0;
   	cartToShow =(HashMap<Product,Integer>)request.getAttribute("cartToShow"); 
   %>
</head>
<body>

<div class="productWrapper">
<table>
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
</div>
<div class="addressWrapper ">
<% if(address != null) showAddress = true;%>
<form action="AddAddress" method="post">
	<input name="street" type="text" placeholder="Street" value="<% if(showAddress){%><%= address.getStreet() %> <% }%>">
	<input name="streetnumber" type="text" placeholder="Street Number" value="<% if(showAddress){%><%= address.getStreetNumber() %> <% }%>">
	<input name="city" type="text" placeholder="City" value="<% if(showAddress){%><%= address.getCity() %> <% }%>">
	<input name="state" type="text" placeholder="State" value="<% if(showAddress){%><%= address.getState() %> <% }%>">
	<input name="country" type="text" placeholder="Country" value="<% if(showAddress){%><%= address.getCountry() %> <% }%>">
	<input name="postalcode" type="text" placeholder="Postal Code" value="<% if(showAddress){%><%= address.getPostalCode() %> <% }%>">
	<button type="submit"> Load Address </button>
	
</form>
</div>
<div class="cardWrapper ">
<% if(card != null)showCard = true; %>
<form action="AddCard" method="post">
	<input name="number" type="text" placeholder="Card Number" value="<% if(showCard){ %><%= card.getNumber() %><% } %>">
	<input name="securitycode" type="number" placeholder="Security Number" value="<% if(showCard){ %><%= card.getSecurityCode() %><% } %>">
	<input name="date" type="date" placeholder="date in YYYY-MM-DD" value="<% if(showCard){ %><%= card.getDate().toString() %><% } %>"> <!--  replace "date" with "expdate" in every java class-->
	<button type="submit"> Load Card </button>

</form>
</div>
<div class="confirmationWrapper">
<% if(showCard && showAddress){%>
	<form action="FinishPurchase" method="post">
		<button type="submit"> Complete Purchase! </button>
	</form>
<% } %>
</div>

</body>
</html>