<%@page import="entities.User"%>
<%@page import="java.util.LinkedList"%>
<%@page import="data.DataProduct"%>
<%@page import="entities.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style/indexStyle.css" rel="Stylesheet">
<!-- find a way of fowarding to this same page,cleaning the attributes "search" and "category", but keeping the session attribute "user" (using redirect on the logo of the page would delete the session too)( this same thing must be done when searching product or clicking on a category) -->
<!-- maybe i can solve this using a new attribute on the request, "state" , that is null if i'm needing to see the clean index, "search" if i'm searching products or "category" if i clicked a category. before fowarding i change the value of the "state" attribute, and then, i use two if-else if-else (one in the <head> to load results, or categories, or the things on the clean index, and another in the <body> to show those products  -->
<title>Welcome!</title>
<% DataProduct dp= new DataProduct();
   LinkedList<Product> products;
   LinkedList<String> categories = dp.getCategories();
   if(request.getAttribute("categoryProducts")!=null){
	products = (LinkedList<Product>)request.getAttribute("categoryProducts");
   } else if(request.getAttribute("searchResults")!=null){
	products = (LinkedList<Product>)request.getAttribute("searchResults");
   }else if(request.getAttribute("filteredProducts")!=null){
   products = (LinkedList<Product>)request.getAttribute("filteredProducts");
   }else{
   	products = dp.getAll();
   }
    %>
  <% User user = (User)session.getAttribute("user"); %>
</head>
<body>
	<div class = "header">
		<a href="index.jsp">
		<img class="logo" alt="logo" src="ImageResources/logo-transparent.png">
		</a>
		<form action="Search" method="get">
			<div>
				<input id="searchField" name="searchField" type="text" placeholder="Search that item that you need now!">
				<button id="searchButton" type="submit">⌕</button>
			</div>
		</form>

		<!-- if session has user in null -->
		<% if(user == null){  %>
		<a href="LogInForm.jsp">
			<img id="userPhoto" alt="user" src="ImageResources/UserImage/Default/noUser-small.png">
		</a>
		<% }else { %>
		<form action="ViewProfile" method="post">
		<!-- 	<img id="userPhoto" alt="user" src="<%= user.getUserPic() %>" > -->
		<input type="image" id="userPhoto" src="<%= user.getUserPic() %>">
		</form>
		<% } %>
		<% if(user != null){  %>
		<form action="ManageCart" method="get">
  			<button type="submit"> 🛒 </button>
		</form>
<!-- <a class="shoppingCart" href="/ECommerce/ManageCart"> 🛒 </a>  <!-- this should be replaced with a form, and styled to look like an <a> tag -->
		<% } %>
	</div>

	<aside id="sidebar">
		<%for(String category: categories){ %>	<!-- first filter by category -->
			
			<form action="SearchCategory" method="get">  <!-- form with hidden field for the category -->
				<input name="category" type="hidden" type="hidden" value="<%= category %>">
				<button type="submit" class="sidebarCategory"> <%= category %> </button>
			</form> 
			<br>
		<% } %>
	</aside>
	<aside id="filterSidebar">
	<form action="FilterProduct" method="get">
	<b>Filter by price:</b> <br>
<!--<input name="minPrice" class="priceFilter" type="number" min=".01" max="1000000" step=".01" placeholder="Min." required> -->
	<input name="maxPrice" class="priceFilter" type="number" min=".01" max="1000000" step=".01" placeholder="Max." required>
	<button type="submit"> > </button>
	</form>
	</aside>

	<div class= "list">
		<h1>Products:</h1>
		<% if(products.size() != 0){
			for(int p = 0;p<products.size();p++){ %>
			<div class="item">	
				<form action="ViewProduct" method="get">
				<input name="id" type="hidden" value="<%= products.get(p).getId() %>">
				<input type="submit">
				</form>
				<%= 	products.get(p).getName() %>
				<br>
			<b>	<%= 	"$" + products.get(p).getPrice() %> </b>				
			</div>
			<div class="separator"></div>
			<%	}	%>
			<% }else { %>
			<h1> No products found with this characteristic </h1>
			<% } %>			
	</div>

</body>
</html>