<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--import spring supplied JSP tag lib --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%--import spring supplied JSP tag lib --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Category</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="padding-left: 30px; padding-right: 30px;">
		<div class="container bcontent">
			<h2>Product Details</h2>
			<hr />
			<div class="card" style="width: 500px;">
				<div class="row no-gutters">
					<div class="col-sm-5">
						<img class="card-img"
							src="${pageContext.request.contextPath}/static/Images/${requestScope.productDetail.productImage}"
							alt="${requestScope.productDetail.productName} Card">
					</div>
					<div class="col-sm-7">
						<div class="card-body">
							<h5 class="card-title">${requestScope.productDetail.productName}</h5>
							<p class="card-text">${requestScope.productDetail.productDesc}.</p>
							<p class="card-text">${requestScope.productDetail.sellingPrice}.</p>
							<p class="card-text">${requestScope.productDetail.productInfo}.</p>

							<%-- <p class="card-text">
								<c:choose>
									<c:when
										test="${requestScope.productDetail.status == 'In Stock'}">
										<span class="badge badge-success rounded-pill d-inline">${requestScope.productDetail.status}</span>
									</c:when>
									<c:when
										test="${requestScope.productDetail.status == 'Limited Stock'}">
										<span class="badge badge-primary rounded-pill d-inline">${requestScope.productDetail.status}</span>
									</c:when>
									<c:otherwise>
										<span class="badge badge-warning rounded-pill d-inline">${requestScope.productDetail.status}</span>
									</c:otherwise>
								</c:choose>
							</p> --%>
							<a href="${pageContext.request.contextPath}/home/"
								class="btn btn-primary">Go back</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr />
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>