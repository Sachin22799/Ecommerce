 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ECommerce - Forgot Password</title>
</head>
<body>

	${initialFlag} ${setPasswordFields}
	<div class="container-fluid py-5">
		<div class="container py-5">
			<div class="text-center mb-5">
				<h5 class="text-primary text-uppercase mb-3"
					style="letter-spacing: 5px;">Forgot Password</h5>

			</div>
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="contact-form bg-secondary rounded p-5">
						<div id="success">
							<h4 class="text-primary text-uppercase mb-3"
								style="letter-spacing: 5px;">${requestScope.response}</h4>
							<h4 class="text-primary text-uppercase mb-3"
								style="letter-spacing: 5px;">${errorMsg}</h4>
						</div>
						<c:choose>
							<c:when
								test="${initialFlag == 'true' && setPasswordFields == 'false'}">
								<h3 class="h3">Enter your email address or mobile</h3>
								<form:form role="form" id="contactForm" name="sentMessage"
									onsubmit="return validateEmailOrMobile()"
									 method="post">
									<div class="control-group">
										<input type="text" class="form-control border-0 p-4"
											name="email" id="email"
											placeholder="Enter Your Email or Mobile" required="required"
											data-validation-required-message="Please enter your email or mobile number" />
										<p class="help-block text-danger"></p>
									</div>
									<div class="text-center">
										<input class="btn btn-primary py-3 px-5" name="forgotpassword"
											type="submit" id="submit">
									</div>
								</form:form>
							</c:when>
							<c:when
								test="${initialFlag == 'false' && setPasswordFields == 'false'}">
								<form:form role="form" id="contactForm" name="sentMessage"
									 method="post">
									<div class="form-group">
										<c:forEach var="ques" items="${requestScope.sec_ques_list}">
											<label class="sr-only" for="exampleInputEmail2">${ques}</label>
							          ${ques}
							          <input type="text" class="form-control" name="secAns"
												placeholder="Enter Answer" required="required">
											<br>
										</c:forEach>
									</div>
									<input type="submit" value="Submit" name="securityques"
										class="btn btn-success">
								</form:form>
							</c:when>
							<c:when
								test="${initialFlag == 'false' && setPasswordFields == 'true'}">
								<form:form role="form" id="contactForm" name="sentMessage"
									onsubmit="return validateForm()" 
									method="post">
									<div class="form-group">
										<label class="sr-only" for="exampleInputEmail2">Enter
											password</label>
										<div class="input-group">
											<input type="password" class="form-control"
												name="newPassword" id="newPassword"
												placeholder="Enter new password" required="required"
												pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}"
												title="Password must contain at least one number, one uppercase letter, one lowercase letter, one special character, and be at least 8 characters long">
											<div class="input-group-append">
												<button type="button" class="btn btn-outline-secondary"
													onclick="togglePasswordVisibility()">Show</button>
											</div>
										</div>
										<br> Confirm password<br> <input type="password"
											class="form-control" name="cfmpassword" id="cfmpassword"
											placeholder="Confirm new password" required="required">
										<div id="passwordError" class="text-danger"></div>
									</div>
									<br>
									<input type="submit" value="Submit" name="resetpwd"
										class="btn btn-success">
								</form:form>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		function validateForm() {
			var password = document.getElementById("newPassword").value;
			var confirmPassword = document.getElementById("cfmpassword").value;

			if (password !== confirmPassword) {
				document.getElementById("passwordError").innerHTML = "Passwords do not match.";
				return false;
			} else {
				document.getElementById("passwordError").innerHTML = "";
			}

			var passwordPattern = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}/;

			if (!passwordPattern.test(password)) {
				document.getElementById("passwordError").innerHTML = "Password must contain at least one number, one uppercase letter, one lowercase letter, one special character, and be at least 8 characters long";
				return false;
			} else {
				document.getElementById("passwordError").innerHTML = "";
			}
		}

		function togglePasswordVisibility() {
			var passwordInput = document.getElementById("newPassword");
			var passwordVisibilityButton = document
					.querySelector("#newPassword + .input-group-append button");

			if (passwordInput.type === "password") {
				passwordInput.type = "text";
				passwordVisibilityButton.textContent = "Hide";
			} else {
				passwordInput.type = "password";
				passwordVisibilityButton.textContent = "Show";
			}
		}
		
		 function validateEmailOrMobile() {
		        var input = document.forms["sentMessage"]["email"].value.trim();
		        var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		        var mobileFormat = /^[0-9]{10}$/;

		        if (input == "") {
		            alert("Please enter your email or mobile number");
		            return false;
		        } else if (!input.match(emailFormat) && !input.match(mobileFormat)) {
		            alert("Please enter a valid email address or mobile number");
		            return false;
		        }
		    }
		
	</script>

</body>
</html> 