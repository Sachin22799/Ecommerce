<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<body background="./images/pattern2.jpg">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

	<div id="login-overlay" class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					Login to <b>online shoping</b>
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-6">
						<div class="well">

							<form class="login-form" action="login" method="post">
								<label for="email">Username</label> <input type="text"
									id="email" name="email" class="form-control"
									placeholder="Email address" required> <label
									for="password">password</label> <input type="password"
									id="password" name="password" class="form-control"
									placeholder="Enter Password" required>
								<button type="button" id="togglePassword">Show/Hide
									Password</button>
								<br> <br>

								<button class="btn btn-success btn-block" type="submit"
									value="login" name="login" id="login">Sign in</button>
							</form>
						</div>
					</div>
					<div class="col-xs-6">
						<p class="lead">
							Register now for <span class="text-success">FREE</span>
						</p>
						<ul class="list-unstyled" style="line-height: 2">
							<li><span class="fa fa-check text-success"></span> See all
								your orders</li>
							<li><span class="fa fa-check text-success"></span> Shipping
								is always free</li>
							<li><span class="fa fa-check text-success"></span> Save your
								favorites</li>
							<li><span class="fa fa-check text-success"></span> Fast
								checkout</li>
							<li><span class="fa fa-check text-success"></span> Get a
								gift <small>(only new customers)</small></li>
							<li><span class="fa fa-check text-success"></span>Holiday
								discounts up to 30% off</li>
						</ul>
						<p>
							<a href="Registration" class="btn btn-info btn-block">Yes
								please, register now!</a>
						<div class="forgot-password">
							<a href="forgot-pass">Forgot Password</a>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<script>
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
