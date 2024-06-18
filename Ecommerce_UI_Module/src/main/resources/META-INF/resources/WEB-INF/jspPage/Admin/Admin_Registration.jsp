<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.ecommerce.ui.utils.SecurityQuestion"%>

<!DOCTYPE html>
<html>
<head>
<title>Registration Form</title>
<%--  <%@include file="../header.jsp"%> --%>

</head>
<body>
	<h2>Registration Form for Admin</h2>
	<div class="container">
		<div class="row">

			<form class="form-horizontal"
				 action="Registration" method="post" 
				onsubmit="return validateEmail() && validatePassword()">
				
					<!-- Form Name -->

					<legend>Register Here</legend>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="firstName">First
							Name</label>
						<div class="col-md-4">
							<input id="firstName" name="firstName"
								placeholder="Insert your First Name"
								class="form-control input-md" required="required" type="text">
							<span class="help-block"> </span>
						</div>

						<label class="col-md-2 control-label" for="lastName">Last
							Name</label>
						<div class="col-md-4">
							<input id="lastName" name="lastName"
								placeholder="Insert your Last Name"
								class="form-control input-md" required="required" type="text">
							<span class="help-block"> </span>
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="email">Email</label>
						<div class="col-md-4">
							<input id="email" name="email" placeholder="Insert your Email"
								class="form-control input-md" required="required" type="email">
							<span class="help-block"> </span>

						</div>

						<label class="col-md-2 control-label" for="mobile">Mobile</label>
						<div class="col-md-4">
							<input id="mobile" name="contactNo"
								placeholder="Insert your Mobile" class="form-control input-md"
								required="required" type="number" onblur="validateMobile()">
							<span class="help-block">Please enter a 10-digit mobile
								number.</span>
						</div>

						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-2 control-label" for="password">Password</label>
							<div class="col-md-4">
								<input id="password" name="password"
									placeholder="Insert your Password"
									class="form-control input-md" required="required"
									type="password">
								<button type="button" id="togglePassword">Show/Hide
									Password</button>
								<br> <br> <span class="help-block"></span>
							</div>

							<label class="col-md-2 control-label" for="confirmPassword">Confirm
								Password</label>
							<div class="col-md-4">
								<input id="confirmPassword" name="confirmPassword"
									placeholder="Confirm your Password"
									class="form-control input-md" required="required"
									type="password"> <span class="help-block"> </span>
							</div>
						</div>
						<!-- Select input for security question -->
						<div class="form-group">
							<label class="col-md-2 control-label" for="securityQuestion">Security
								Question 1</label>
							<div class="col-md-4">
								<select id="securityQuestion1" name="securityQuestion1"
									class="form-control input-md" required>
									<option value="">--Select Security Question--</option>
									<c:forEach items="${SecurityQuestion}" var="ques">
										<option value="${ques}">${ques}</option>
									</c:forEach>
									<%-- <%
									for (SecurityQuestion question : SecurityQuestion.values()) {
									%>
									<option value="<%=question.name()%>"><%=question.getQuestion()%></option>
									<%
									}
									%> --%>
								</select> <span class="help-block"> </span>
							</div>


							<div class="form-group">
								<label class="col-md-2 control-label" for="securityAnswer1">Answer
									1</label>
								<div class="col-md-4">
									<input id="securityAnswer1" name="securityAnswer1"
										placeholder="Insert your answer" class="form-control input-md"
										required type="text"> <span class="help-block">
									</span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label" for="securityQuestion">Security
									Question 2</label>
								<div class="col-md-4">
									<select id="securityQuestion2" name="securityQuestion2"
										class="form-control input-md" required>
										<option value="">--Select Security Question--</option>
										<c:forEach items="${SecurityQuestion}" var="ques">
											<option value="${ques}">${ques}</option>
										</c:forEach>
										<%-- <%
										for (SecurityQuestion question : SecurityQuestion.values()) {
										%>
										<option value="<%=question.name()%>"><%=question.getQuestion()%></option>
										<%
										}
										%> --%>
									</select> <span class="help-block"> </span>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label" for="securityAnswer2">Answer
										2</label>
									<div class="col-md-4">
										<input id="securityAnswer2" name="securityAnswer2"
											placeholder="Insert your answer"
											class="form-control input-md" required type="text"> <span
											class="help-block"> </span>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label" for="securityQuestion">Security
										Question 3</label>
									<div class="col-md-4">
										<select id="securityQuestion" name="securityQuestion3"
											class="form-control input-md" required>
											<option value="">--Select Security Question--</option>
											<c:forEach items="${SecurityQuestion}" var="ques">
												<option value="${ques}">${ques}</option>
											</c:forEach>
											<%-- <%
											for (SecurityQuestion question : SecurityQuestion.values()) {
											%>
											<option value="<%=question.name()%>"><%=question.getQuestion()%></option>
											<%
											}
											%> --%>
										</select> <span class="help-block"> </span>
									</div>


									<div class="form-group">
										<label class="col-md-2 control-label" for="securityAnswer3">Answer
											3</label>
										<div class="col-md-4">
											<input id="securityAnswer3" name="securityAnswer3"
												placeholder="Insert your answer"
												class="form-control input-md" required type="text">
											<span class="help-block"> </span>
										</div>
									</div>

									<!-- Button -->
									<div class="form-group">
										<label class="col-md-2 control-label" for="submit"></label>
										<div class="col-md-4">
											<button id="submit" name="submit" class="btn btn-success">Submit</button>
											<button id="clear" name="clear" class="btn btn-danger">Clear</button>
										</div>
									</div>
				
			</form>
		</div>
	</div>
	<script>
		function validateEmail() {
			var email = document.getElementById("email").value;
			var pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
			if (!email.match(pattern)) {
				alert("Please enter a valid email address.");
				return false;
			}
			return true;
		}

		function validatePassword() {
			var password = document.getElementById("password").value;
			var confirmPassword = document.getElementById("confirmPassword").value;
			var pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,20}$/;

			if (!password.match(pattern)) {
				alert("Password must contain at least one digit, one lowercase letter, one uppercase letter, and must be 8-20 characters long.");
				return false;
			}
			if (password !== confirmPassword) {
				alert("Passwords do not match.");
				return false;
			}
			return true;
		}
		function validateMobile() {
			var mobile = document.getElementById("mobile").value;
			var pattern = /^[0-9]{10}$/;
			if (!mobile.match(pattern)) {
				alert("Please enter a 10-digit mobile number.");
				return false;
			}
			return true;
		}

		const togglePassword = document.querySelector('#togglePassword');
		const password = document.querySelector('#password');

		togglePassword.addEventListener('click', function(e) {
			const type = password.getAttribute('type') === 'password' ? 'text'
					: 'password';
			password.setAttribute('type', type);
			this.textContent = type === 'password' ? 'Show Password'
					: 'Hide Password';
		});
	</script>
</body>
</html>



