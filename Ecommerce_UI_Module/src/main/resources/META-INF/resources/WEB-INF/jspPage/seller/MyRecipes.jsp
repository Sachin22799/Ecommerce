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
			<h1>Welcome to Your Store</h1>
		</div>
	</header>
	<main>
		<div class="container">
			<h2>How to Make Food Step by Step with Us</h2>

			<!-- Existing code -->

			<c:if test="${not empty message}">
				<div class="alert alert-info" style="color: red">${message}</div>
			</c:if>

			<!-- Remaining code -->
			<c:forEach var="recipe" items="${recipes}">
				<div class="recipe-container">
					<div class="recipe-image">
						<img
							src="${pageContext.request.contextPath}/static/Images/${recipe.recipeImage}"
							class="img-thumbnail" width="150px">
					</div>
					<div class="recipe-details">
						<h3>${recipe.recipeName}</h3>

						<p>
							<strong>introduction Material:</strong> ${recipe.introduction}
						</p>

						<p>
							<strong>Required Recipe Material:</strong>
							${recipe.requiredMaterial}
						</p>

						<p>
							<strong>How to make Recipe:</strong> ${recipe.recipeInstructions}
						</p>

						<p>
							<strong>note:</strong> ${recipe.note}
						</p>


					</div>
				</div>
			</c:forEach>
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
