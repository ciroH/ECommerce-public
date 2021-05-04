<%@page import="entities.Address"%>
<%@page import="entities.User"%>
<%@page import="entities.Transaction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket</title>
<% Transaction transaction = (Transaction)request.getAttribute("ticket"); %>
<% String userMail = ((User)session.getAttribute("user")).getMail(); %>
<% Address address = (Address)session.getAttribute("address"); %>

</head>
<body>
	<pre>
		<%= transaction.getDetail() %> <br>
		<%= "User: " + userMail %> <br>
		<%= "Address: " + address.getStreet() + " " + address.getStreetNumber() + ", " + address.getCity() + ", " + address.getState() + ", " + address.getCountry() %> <br>
		<%= "Postal/ZIP Code: " + address.getPostalCode() %> <br>
	</pre>
	<form action="index.jsp">
	<button type="submit"> Home </button>
	</form>
</body>
</html>