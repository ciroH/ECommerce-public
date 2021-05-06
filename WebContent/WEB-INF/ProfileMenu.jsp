<%@page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Menu</title>
<% String userPic = ((User)session.getAttribute("user")).getUserPic(); %>
</head>
<body>
<!-- 	<h1>
		Change Profile Picture:
	</h1>
	<img src="<%= "../"+userPic %>">
	<form action="UploadImage" method="post" enctype="multipart/form-data">
		<input name="inputPic" type="file" accept=".png">
		<button type="submit"> Update </button>
	</form>
-->
		<form action="LogOut" method="post">
		<button type="submit">Close Session</button>
		</form>
</body>
</html>