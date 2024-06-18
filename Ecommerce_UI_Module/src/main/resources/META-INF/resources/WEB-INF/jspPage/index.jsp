<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>Online Grocery and Vegetable Shop</title>
<meta charset="UTF-8">

</head>
<body>
	<%-- <jsp:include page="header.jsp" /> --%>
	<%@include file="header.jsp"%>
	<main>
		<h2>Welcome to our online grocery and vegetable shop!</h2>


		<!-- Categories Section Begin -->
		<section class="categories">
			<div class="container">
				<div class="row">
					<div class="categories__slider owl-carousel">


						<c:forEach var="prod" items="${requestScope.productList}">
							<div class="col-tg-3">
								<div class="categories__item set-bg"
									data-setbg="${pageContext.request.contextPath}/static/Images/${prod.productImage}">
									<h5>
										<a href="Product-info?id=${prod.id}">${prod.productName}</a>
									</h5>
									<h2>${prod.sellingPrice}</h2>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</section>
		<!-- Categories Section End -->

	</main>
	<%@include file="footer.jsp"%>
	<%-- <jsp:include page="footer.jsp" /> --%>
</body>
</html>
