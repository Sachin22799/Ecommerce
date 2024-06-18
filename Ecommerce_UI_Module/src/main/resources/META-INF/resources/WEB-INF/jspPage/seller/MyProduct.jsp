

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Manage Products - Your Online Store</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/styles.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<header>
		<div class="container">
			<h1>Welcome to Your Online Store</h1>
		</div>
	</header>
	<main>
		<div class="container">
	
			<h2>Manage Products</h2>

			<!-- ... Existing code ... -->

			<c:if test="${not empty message}">
				<div class="alert alert-info" style="color: red">${message}</div>
			</c:if>

			<!-- ... Remaining code ... -->


			<table class="table table-striped">
				<tr>
					<th>Image</th>
					<th>Product Name</th>
					<th>Description</th>
					<th>Purchasing Price</th>
					<th>Selling Price</th>
					<th>Available Qty</th>
					<th>Minimum Qty</th>
					<th>Actions</th>
				</tr>
				
				
				<c:forEach var="product" items="${products}">
					<tr>
						<td><img
							src="${pageContext.request.contextPath}/static/Images/${product.productImage}"
							class="img-thumbnail" width="100px"></td>
						<td>${product.productName}</td>
						<td>${product.productDesc}</td>
						<td>${product.purchasingPrice}</td>
						<td>${product.sellingPrice}</td>
						<td>${product.availableQty}</td>
						<td>${product.minQty}</td>
						<td>
							<%-- <a href="${pageContext.request.contextPath}/seller/edit-product"class="btn btn-primary btn-sm">Edit</a>  --%>
							<a href="edit-product?id=${product.id}"
							class="btn btn-primary btn-sm">Add More Product or Edit
								Product</a> <a href="delete-product?id=${product.id}"
							class="btn btn-danger btn-sm"
							onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>

						</td>
					</tr>
				</c:forEach>

			</table>
		</div>
		<li><a href="logout">Logout</a></li>

	</main>
	<footer>
		<div class="container">
			<p>&copy; 2023 Your Online Store. All rights reserved.</p>
		</div>
	</footer>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
