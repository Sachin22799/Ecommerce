<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
    <h1>Products</h1>
  

    <c:forEach var="product" items="${productList}">
        <div class="col-tg-3">
            <div class="categories__item set-bg"
                data-setbg="${pageContext.request.contextPath}/static/Images/Products/${product.productImage}">
                <h2>
                    <a href="Product-info?id=${product.id}">${product.productName}</a>
                    <img src="${pageContext.request.contextPath}/static/Images/${product.productImage}" width="200" height="200">
                </h2>
                <a href="buy-now?id=${product.id}" class="buy-now-button">Buy Now</a>
            </div>
        </div>
    </c:forEach>
</body>
</html>
