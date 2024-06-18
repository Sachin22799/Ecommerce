<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Recipe Page</title>
<link rel="stylesheet" type="text/css" href="path_to_your_css_file.css">
</head>
<body>
	<header>
		<div class="container">
			<h1>Welcome to Our Recipes</h1>
		</div>
	</header>
	<main>
		<div class="container">
			<ul class="header__menu">
				<li><a href="#">Our recipes</a>
					<ul class="header__menu__dropdown">
						<c:forEach var="recipe" items="${requestScope.recipes}">
							<li><a href="">${recipe.recipeName}</a></li>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
	</main>
	<footer>
		<div class="container">
			<p>&copy; 2023 Your Company. All rights reserved.</p>
		</div>
	</footer>
</body>
</html>
