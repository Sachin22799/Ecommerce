<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
</head>
<body>
	<div style="text-align: center;">
		<div style="margin-top: 50px;">
			<b id="login-name">LOGIN Here</b>
			<div style="margin-top: 50px;">
				<form style="display: inline-block;" action="login" method="post">
					<label for="email">Username:</label>
					<input type="text" id="email" name="email" placeholder="Email address" required>
					<br><br>
					<label for="password">Password:</label>
					<input type="password" id="password" name="password" placeholder="Enter Password" required>
					<br><br>
					<input type="submit" value="Login">
					<input type="reset" value="Reset">
				</form>
			</div>
			<div style="margin-top: 20px;">
				<a href="forgot-pass">Forget Password</a>
				<a href="Registration" style="margin-left: 10px;">Register</a>
			</div>
		</div>
	</div>
</body>