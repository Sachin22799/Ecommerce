<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN DASHBOARD</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Custom CSS -->

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h3>Categories</h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Category Name</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="categories" items="${requestScope.categoryList}">
							<tr>
								<td>${categories.categoryName}</td>
								<td><a
									href="${pageContext.request.contextPath}/admin/approveCategory?categoryId=${categories.id}">Approve</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<h1>Product List</h1>
	<table class="table">
		<thead>
			<tr>
				<th>SELLER ID</th>
				<th>Product Image</th>
				<th>Product Name</th>
				<th>Description</th>
				<th>Purchasing Price</th>
				<th>Selling Price</th>
				<th>Available Quantity</th>
				<th>Minimum Quantity</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${requestScope.productlist}">
				<tr>
					<td>${product.sellerId}</td>

					<td><img
						src="${pageContext.request.contextPath}/static/Images/${product.productImage}"
						class="img-thumbnail" width="100px"></td>

					<td>${product.productName}</td>
					<td>${product.productDesc}</td>
					<td>${product.purchasingPrice}</td>
					<td>${product.sellingPrice}</td>
					<td>${product.availableQty}</td>
					<td>${product.minQty}</td>
					<td><a
						href="${pageContext.request.contextPath}/admin/approveProduct?productId=${product.id}">Approve</a>
						<form method="post"
							action="${pageContext.request.contextPath}/admin/cancelProduct">
							<input type="hidden" name="productId" value="${product.id}">
							<input type="text" name="reason"
								placeholder="Reason for cancellation"> <input
								type="submit" value="Cancel">
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<h1>Seller List</h1>
	<table class="table">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Password</th>
				<th>Contact No</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="seller" items="${requestScope.sellerlist}">
				<tr>
					<td>${seller.firstName}</td>
					<td>${seller.lastName}</td>
					<td>${seller.email}</td>
					<td>${seller.password}</td>
					<td>${seller.contactNo}</td>
					<td><a
						href="${pageContext.request.contextPath}/admin/approveSeller?sellerId=${seller.id}">Approve</a>
						<form method="post"
							action="${pageContext.request.contextPath}/admin/cancelSeller">
							<input type="hidden" name="sellerId" value="${seller.id}">
							<input type="text" name="reason"
								placeholder="Reason for cancellation"> <input
								type="submit" value="Cancel">
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>

