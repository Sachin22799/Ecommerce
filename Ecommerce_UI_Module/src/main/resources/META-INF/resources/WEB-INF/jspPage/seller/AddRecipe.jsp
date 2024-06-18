<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Recipe Form</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Add Recipe</h2>

		<form:form name="sentMessage" method="POST"
			enctype="multipart/form-data" modelAttribute="recipe">
			<div class="form-group">
				<label for="recipeName">Recipe Name:</label> <input id="recipeName"
					name="recipeName" placeholder="Recipe Name"
					class="form-control input-md" required type="text"
					value="${requestScope.recipe.recipeName}">
			</div>
			<div class="form-group">
				<label for="introduction">introduction:</label>
				<textarea id="introduction" name="introduction"
					placeholder="introduction" class="form-control" required>${requestScope.recipe.introduction}</textarea>
			</div>
			<div class="form-group">
				<label for="recipeImage">Recipe Image:</label> <input type="file"
					class="form-control-file" id="recipeImage" name="recpImg" required>
			</div>

			<div class="form-group">
				<label for="requiredMaterial">Requird Material:</label>
				<textarea id="requiredMaterial" name="requiredMaterial"
					placeholder="required Material" class="form-control" required>${requestScope.recipe.requiredMaterial}</textarea>
			</div>

			<div class="form-group">
				<label for="recipeInstructions"> How to make Recipe :</label>
				<textarea id="recipeInstructions" name="recipeInstructions"
					placeholder="Recipe Description" class="form-control" required>${requestScope.recipe.recipeInstructions}</textarea>
			</div>
			<div class="form-group">
				<label for="note">Note</label>
				<textarea id="note" name="note" placeholder="Note"
					class="form-control" required>${requestScope.recipe.note}</textarea>
			</div>


			<div class="text-center">
				<button type="submit" class="btn btn-primary">Add Recipes</button>
			</div>


		</form:form>
	</div>
</body>
</html>
