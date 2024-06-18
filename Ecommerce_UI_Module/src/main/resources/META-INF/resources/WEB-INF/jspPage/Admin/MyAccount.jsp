<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <!DOCTYPE html>
<html>
<head>
<title>My Account</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- Roboto font -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">
<!-- Bootstrap JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</head>
<body>

	<!-- Navbar -->
	<nav class="teal darken-3">
		<div class="container">
			<ul class="right hide-on-med-and-down">
				<li><a href="logout">Logout</a></li>
				<li><a href="add-category">Add Category</a></li>
				<li><a href="add-Product">Add Product</a></li>
				<li><a href="my-Product">My Products</a></li>
				<li><a href="dashbord">Request</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<h2>Welcome, ${response1}</h2>
		<p>Here is your account information:</p>
		<table class="striped">
			<tbody>
				<tr>
					<th>First Name:</th>
					<td>${response1}</td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td>${response2}</td>
				</tr>
				<tr>
					<th>Email:</th>
					<td>${email}</td>
				</tr>
				<tr>
					<th>Phone:</th>
					<td>${phone}</td>
				</tr>
				<tr>
					<th>Address:</th>
					<td>${address}</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
 