<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link href="style/indexStyle.css" rel="stylesheet">
<title>Log In!</title>
<% String warning = (String)request.getAttribute("warning"); %>
</head>

<body>
<div id="userForm">
	<h1>Please Login to continue</h1>
	<form action="LogIn" method="post">
		<input id="inputEmail" name="inputEmail" type="text" placeholder="Email" required="required">
		<br>
		<input id="inputPassword" name="inputPassword" type="password" placeholder="Password" required="required">
		<br>
		<button type="submit">Log In!</button>
	</form>
	<a href="SignInForm.jsp">Need an Account?</a>
</div>

	<% if(warning!=null){
		if(warning.equals("non-valid")){ %>
		<h1 id="warning">The user name or password is not valid!</h1>
	<% 	 } 
	    } %>
</body>

</html>