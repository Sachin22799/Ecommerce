<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Product - Your Online Store</title>
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
			<h2>Edit Product</h2>

			<form:form modelAttribute="product" method="POST"
				enctype="multipart/form-data">
				<table class="table">

					<form:input type="hidden" path="categoryId"
						value="${requestScope.product.categoryId}" class="form-control"></form:input>

					<tr>
						<th>Product Image</th>
						<td><img
							src="${pageContext.request.contextPath}/static/Images/${product.productImage}"
							class="img-thumbnail" width="200px"> <br> <br> <input
							type="file" name="prodImg"  id="productImage" accept=".jpg,.png,.jpeg"></td>
					</tr>
					<tr>
						<th>Product Name</th>
						<td><form:input path="productName" name="productName"
								value="${requestScope.product.productName}" class="form-control"></form:input></td>
					</tr>
					<tr>
						<th>Description</th>
						<td><form:textarea path="productDesc" name="productDesc"
								value="${requestScope.product.productDesc}" class="form-control"></form:textarea></td>
					</tr>
					<tr>
						<th>Purchasing Price</th>
						<td><form:input path="purchasingPrice" name="purchasingPrice"
								value="${requestScope.product.purchasingPrice}"
								class="form-control"></form:input></td>
					</tr>
					<tr>
						<th>Selling Price</th>
						<td><form:input path="sellingPrice" name="sellingPrice"
								value="${requestScope.product.sellingPrice}"
								class="form-control"></form:input></td>
					</tr>
					<tr>
						<th>Available Qty</th>
						<td><form:input path="availableQty" name="availableQty"
								value="${requestScope.product.availableQty}"
								class="form-control"></form:input></td>
					</tr>
					<tr>
						<th>Minimum Qty</th>
						<td><form:input path="minQty" name="minQty"
								value="${requestScope.product.minQty}" class="form-control"></form:input></td>
					</tr>

					<tr>
						<th>PRODUCT INFO HOW TO USE AND STORAGE</th>
						<td><form:input path="productInfo" name="productInfo"
								value="${requestScope.product.productInfo}" class="form-control"></form:input></td>
					</tr>
				</table>
				<br>
				<div class="text-center">
					<button type="submit" class="btn btn-primary">Update
						Product</button>
					<a
						href="${pageContext.request.contextPath}/seller/show-my-products"
						class="btn btn-danger">Cancel</a>
				</div>
			</form:form>
		</div>
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
