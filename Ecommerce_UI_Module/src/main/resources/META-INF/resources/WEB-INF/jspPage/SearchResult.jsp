<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
</head>
<body>
	<table>
		<tr>
			<!-- <th>product name</th>
            <th>product image</th> -->
		</tr>
		<c:forEach var="product" items="${productList}">
			<div class="col-tg-3">
				<div class="categories__item set-bg"
					data-setbg="${pageContext.request.contextPath}/static/Images/Products/${product.productImage}">
					<h2>
						<a href="Product-info?id=${product.id}">${product.productName}</a> <img
							src="${pageContext.request.contextPath}/static/Images/${product.productImage}"
							width="200" height="200"> <a
							href="buy-now?id=${product.id}" class="buy-now-button">Buy
							Now</a>
					</h2>
				</div>
			</div>
		</c:forEach>
	</table>
</body>
</html>
