
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
			<div class="row">
				<div class="col-md-6">

					<h2>Add Product</h2>

					<form:form name="sentMessage" novalidate="novalidate"
						id="contactForm" method="POST" enctype="multipart/form-data"
						modelAttribute="product">
						
						<div class="form-group">
							<label for="categoryId">Category:</label> <select
								class="form-control" id="categoryId" name="categoryId" required>
								<option value="">Select a Category</option>
								<c:forEach items="${categoryList}" var="category">
									<option value="${category.id}">${category.categoryName}</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label for="productName">Product Name:</label> <input
								id="productName" name="productName" placeholder="Product Name"
								class="form-control input-md" required="" type="text"
								value="${requestScope.product.productName}">
						</div>

						<div class="form-group">
							<label for="productDesc">Product Description:</label>
							<textarea class="form-control" id="productDesc"
								name="productDesc">${requestScope.product.productDesc}</textarea>
						</div>

						<div class="form-group">
							<label for="productImage">Product Image:</label> <input
								type="file" class="form-control-file" id="productImage"
								name="prodImg" required>
						</div>

						<div class="form-group">
							<label for="purchasingPrice">Purchasing Price:</label> <input
								id="purchasingPrice" name="purchasingPrice"
								placeholder="Purchasing Price" class="form-control input-md"
								required="" type="text"
								value="${requestScope.product.purchasingPrice}">
						</div>

						<div class="form-group">
							<label for="sellingPrice">Selling Price:</label> <input
								id="sellingPrice" name="sellingPrice"
								placeholder="Selling Price" class="form-control input-md"
								required="" type="text"
								value="${requestScope.product.sellingPrice}">
						</div>

						<div class="form-group">
							<label for="availableQty">Available Quantity:</label> <input
								id="availableQty" name="availableQty"
								placeholder="Available Quantity" class="form-control input-md"
								required="" type="text"
								value="${requestScope.product.availableQty}">
						</div>

						<div class="form-group">
							<label for="minQty">Minimum Quantity:</label> <input id="minQty"
								name="minQty" placeholder="Minimum Quantity"
								class="form-control input-md" required type="text"
								value="${requestScope.product.minQty}">
						</div>

						<div class="form-group">
							<label for="productInfo">Product Info How to Use and
								Storage:</label>
							<textarea class="form-control" id="productInfo"
								name="productInfo">${requestScope.product.productInfo}</textarea>
						</div>

						<div class="text-center">
							<button type="submit" class="btn btn-primary">Add
								Product</button>
						</div>
					</form:form>
				</div>

				<div class="col-md-6">

					<h2>Manage Products</h2>
					<!-- ... Existing code ... -->

					<c:if test="${not empty message}">
						<div class="alert alert-info" style="color: red">${message}</div>
					</c:if>

					<!-- ... Remaining code ... -->

<!-- ... Existing code ... -->

<table class="table table-striped">
    <thead>
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
    </thead>
    <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td><img src="${pageContext.request.contextPath}/static/Images/${product.productImage}" class="img-thumbnail" width="100px"></td>
                <td>${product.productName}</td>
                <td>${product.productDesc}</td>
                <td>${product.purchasingPrice}</td>
                <td>${product.sellingPrice}</td>
                <td>${product.availableQty}</td>
                <td>${product.minQty}</td>
                <td>
                    <a href="edit-product?id=${product.id}" class="btn btn-primary btn-sm"> Edit</a>
                    <a href="delete-product?id=${product.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- ... Remaining code ... -->

				</div>
			</div>
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
