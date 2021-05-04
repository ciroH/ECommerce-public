<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In!</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link href="style/indexStyle.css" rel="stylesheet">
<% String warning = (String)request.getAttribute("warning"); %>
</head>
<body>
	<form action="SignIn" method="post">
		<div id="userForm">
			<h1>Create an Account</h1>
			<br>
			<!--  for adding dynamism to the page, it would be nice to put the inputPic field into a separtate form, so that the image can first be uploaded and saved to the temp folder, and after that, forward to this same page but showing the image and having it embedded into the main form as a hidden field (the image would be clickable, this is, it would still be a part of the secondary form, so that the user can click it to replace the image for another one) -->
			<br>
			<input id="name" name="name" type="text" placeholder="Name" required="required">
			<br>
			<input id="inputEmail" name="inputEmail" type="email" placeholder="Email" required="required">	
			<br>
			<input id="inputPassword" name="inputPassword" type="password" placeholder="Password" required="required">
			<br>
			<input id="inputPasswordCheck" name="inputPasswordCheck" type="password" placeholder="Confirm Password" required="required">
			<br>
			<button type="submit">Sign In!</button>
		</div>
	</form>
	<% if(warning!=null){
		if(warning.equals("existing")){ %>
		<h1> That Email Address is already Registered!</h1>
	<% } else if(warning.equals("password")){  %>
		<h1> Verify Password! </h1>
	<% } 
	  } %>
	
</body>
</html>