<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ECommerce - Forgot Password</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

</head>
<body>
	<div class="container-fluid py-5">
		<div class="container py-5">
			<div class="text-center mb-5">
				<h5 class="text-primary text-uppercase mb-3" style="letter-spacing: 5px;">Forgot Password</h5>
			</div>
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div>
						<div id="success">
							<h4 class="text-primary text-uppercase mb-3">${requestScope.response}</h4>
							<h4 class="text-primary text-uppercase mb-3">${errorMsg}</h4>
						</div>
						<c:choose>
							<c:when test="${initialFlag == 'true' && setPasswordFields == 'false'}">
								<h3 class="h3 primary-color">Enter your email address or mobile</h3>
								<form:form role="form" id="contactForm" name="sentMessage" method="post">
									<div class="form-group">
										<input type="text" class="form-control" name="email" id="email"
											placeholder="Enter Your Email or Mobile" required="required">
										<div class="invalid-feedback primary-color">Please enter your email or mobile number</div>
									</div>
									<div class="text-center">
										<input class="btn btn-primary py-3 px-5" name="forgotpassword"
											type="submit" id="submit">
									</div>
								</form:form>
							</c:when>
							<c:when test="${initialFlag == 'false' && setPasswordFields == 'false'}">
								<form:form role="form" id="contactForm" name="sentMessage" method="post">
									<div class="form-group">
										<c:forEach var="ques" items="${requestScope.sec_ques_list}">
											${ques}
											<input type="text" class="form-control" name="secAns"
												placeholder="Enter Answer" required="required">
											<div class="invalid-feedback primary-color">Please enter the answer</div>
											<br>
										</c:forEach>
									</div>
									<input type="submit" value="Submit" name="securityques" class="btn btn-success">
								</form:form>
							</c:when>
							<c:when test="${initialFlag == 'false' && setPasswordFields == 'true'}">
								<form:form role="form" id="contactForm" name="sentMessage" onsubmit="return validateForm()" method="post">
									<div class="form-group">
										<label class="sr-only" for="newPassword">Enter password</label>
										<div class="input-group">
											<input type="password" class="form-control" name="newPassword" id="newPassword"
												placeholder="Enter new password" required="required"
												pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}"
												title="Password must contain at least one number, one uppercase letter, one lowercase letter, one special character, and be at least 8 characters long">
											<div class="input-group-append">
												<button type="button" class="btn btn-outline-secondary"
													onclick="togglePasswordVisibility()">Show</button>
											</div>
										</div>
										<div class="invalid-feedback primary-color">Please enter a valid password</div>
									</div>
									<div class="form-group">
										<label class="sr-only" for="cfmpassword">Confirm password</label>
										<input type="password" class="form-control" name="cfmpassword"
											id="cfmpassword" placeholder="Confirm new password" required="required">
										<div class="invalid-feedback primary-color">Passwords do not match.</div>
									</div>
									<input type="submit" value="Submit" name="resetpwd" class="btn btn-success">
								</form:form>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script>
		function validateForm() {
			var password = document.getElementById("newPassword").value;
			var confirmPassword = document.getElementById("cfmpassword").value;
			var passwordPattern = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}/;
			var passwordInput = document.getElementById("newPassword");
			var confirmPasswordInput = document.getElementById("cfmpassword");

			if (password !== confirmPassword) {
				confirmPasswordInput.classList.add("is-invalid");
				return false;
			} else {
				confirmPasswordInput.classList.remove("is-invalid");
			}

			if (!passwordPattern.test(password)) {
				passwordInput.classList.add("is-invalid");
				return false;
			} else {
				passwordInput.classList.remove("is-invalid");
			}
		}

		function togglePasswordVisibility() {
			var passwordInput = document.getElementById("newPassword");
			var passwordVisibilityButton = document.querySelector("#newPassword + .input-group-append button");

			if (passwordInput.type === "password") {
				passwordInput.type = "text";
				passwordVisibilityButton.textContent = "Hide";
			} else {
				passwordInput.type = "password";
				passwordVisibilityButton.textContent = "Show";
			}
		}
	</script>
</body>
</html>
