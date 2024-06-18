
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/style.css"
	type="text/css">
</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-6">
						<div class="header__top__left">
							<ul>
								<li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
								<li>Free Shipping for all Order of $99</li>
							</ul>
						</div>
					</div>
					<div class="col-lg-6 col-md-6">
						<div class="header__top__right">
							<div class="header__top__right__social">
								<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
									class="fa fa-twitter"></i></a> <a href="#"><i
									class="fa fa-linkedin"></i></a> <a href="#"><i
									class="fa fa-pinterest-p"></i></a>
							</div>
							<div class="header__top__right__language">
								<img src="img/language.png" alt="">
								<div>English</div>
								<span class="arrow_carrot-down"></span>
								<ul>
									<li><a href="#">Spanis</a></li>
									<li><a href="#">English</a></li>
								</ul>
							</div>
							<c:choose>
								<c:when test="${email == null }">
									<div class="header__top__right__auth">
										<a href="${pageContext.request.contextPath}/user/login"><i
											class="fa fa-user"></i>Login</a>

									</div>
								</c:when>
								<c:when test="${email != null }">
									<div class="header__top__right__auth">
										<a href="${pageContext.request.contextPath}/user/logout"><i
											class="fa fa-user"></i>Logout</a>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="./index.html"><img
							src="${pageContext.request.contextPath}/static/img/logo.png"
							alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="./index.html">Home</a></li>
							<li><a href="./shop-grid.html">Shop</a></li>
							<li><a href="#">Pages</a>
								<ul class="header__menu__dropdown">
									<li><a href="./shop-details.html">Shop Details</a></li>
									<li><a href="./shoping-cart.html">Shoping Cart</a></li>
									<li><a href="./checkout.html">Check Out</a></li>
									<li><a href="./blog-details.html">Blog Details</a></li>
								</ul></li>
							<li><a href="#">Our reciprs</a>
								<ul class="header__menu__dropdown">
									<c:forEach var="recipe" items="${requestScope.recipes}">
										<li><a href="recipes">${recipe.recipeName}</a></li>
									</c:forEach>

								</ul></li>
							<li><a href="./contact.html">Contact</a></li>
						</ul>
					</nav>
				</div>



				<div class="col-lg-3">
					${email}
					<c:choose>
						<c:when test="${email != null }">
							<div class="header__cart">
								<ul>
									<li><a
										href="${pageContext.request.contextPath}/user/show-wishlist"><i
											class="fa fa-heart"></i> <span>${wishlistCount}</span></a></li>
									<li><a
										href="${pageContext.request.contextPath}/user/show-cart"><i
											class="fa fa-shopping-bag"></i> <span>${cartCount}</span></a></li>
								</ul>
								<div class="header__cart__price">
									Cart Total : Rs. <span>${cartTotal}</span>
								</div>

								<div class="header__menu">
									<ul>
										<li>
											<%-- <a href="${pageContext.request.contextPath}/user/MyAccount"> --%>
											<a href="#"><i class="fa fa-user"></i>My Account</a>
											<ul class="header__menu__dropdown">
												<li><a
													href="${pageContext.request.contextPath}/user/show-order">My
														Order</a></li>
												<li><a
													href="${pageContext.request.contextPath}/user/show-cart">My
														Cart</a></li>
												<li><a
													href="${pageContext.request.contextPath}/user/show-wishlist">My
														Wishlist</a></li>
												<li><a
													href="${pageContext.request.contextPath}/user/MyPayment">Payment
												</a></li>
												
											</ul>
										</li>
									</ul>

								</div>

							</div>

						</c:when>
					</c:choose>


				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<c:choose>
		<c:when
			test="${pageContext.request.requestURI == '/xpertit_ecommerce/WEB-INF/jspPage/index.jsp'}">
			<!-- Hero Section Begin -->
			<section class="hero">
				<div class="container">
					<div class="row">
						<div class="col-lg-3">
							<div class="hero__categories">
								<div class="hero__categories__all">
									<i class="fa fa-bars"></i> <span>All Categories</span>
								</div>
								<ul>
									<c:forEach var="categories"
										items="${requestScope.categoryList}">

										<li><a href="load-Product?id=${categories.id}">${categories.categoryName}</a></li>
									</c:forEach>

								</ul>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="hero__search">
								<div class="hero__search__form">

									<form:form method="post">

										<input type="text" placeholder="What do you need?"
											name="query">
										<button type="submit" class="site-btn">SEARCH</button>

									</form:form>


								</div>
								<div class="hero__search__phone">
									<div class="hero__search__phone__icon">
										<i class="fa fa-phone"></i>
									</div>
									<div class="hero__search__phone__text">
										<h5>+65 11.188.888</h5>
										<span>support 24/7 time</span>
									</div>
								</div>
							</div>
							<div class="hero__item set-bg"
								data-setbg="${pageContext.request.contextPath}/static/img/hero/banner.jpg">
								<div class="hero__text">
									<span>FRUIT FRESH</span>
									<h2>
										Vegetable <br />100% Organic
									</h2>
									<p>Free Pickup and Delivery Available</p>
									<a href="#" class="primary-btn">SHOP NOW</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</c:when>
	</c:choose>
	<!-- Hero Section End -->
</body>
</html>
