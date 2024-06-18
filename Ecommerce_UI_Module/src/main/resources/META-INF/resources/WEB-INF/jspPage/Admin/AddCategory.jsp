<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<div class="container text-right">
		<form action="logout">
			<button type="submit" class="btn btn-danger">Logout</button>
		</form>
	</div>
<body>
	<div class="container">
		<h1>Add Category</h1>
		<form method="post">
			<div class="form-group">
				<label for="categoryName">Category Name:</label> <input type="text"
					class="form-control" name="categoryName" required>
			</div>
			<div class="form-group">
				<label for="categoryDesc">Category Description:</label> <input
					type="text" class="form-control" name="categoryDesc" required>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Add Category">
			</div>
		</form>
	</div>
	<div class="container center">
		<h2>Category List</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${categoryList}" var="category">
					<tr>
						<td>${category.categoryName}</td>
						<td>${category.categoryDesc}</td>
						<td>
							<form action="delete-category"
								onsubmit="return confirm('Are you sure you want to delete this category?');">
								<input type="hidden" name="categoryId" value="${category.id}">
								<button type="submit" class="btn btn-danger btn-sm">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		
	</div>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
