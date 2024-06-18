
 
 
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Category</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style>
        .center {
            text-align: center;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Add Category</h1>
    <form method="post">
        <div class="form-group">
            <label for="categoryName">Category Name:</label>
            <input type="text" class="form-control" name="categoryName" required>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Add Category">
        </div>
    </form>
</div>
<div class="container center">
    <h2>Category List</h2>
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${categoryList}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.categoryName}</td>
               <%--      <td>
                        <form action="${pageContext.request.contextPath}/categories/${category.id}" method="get">
                            <button type="submit" class="btn btn-primary">Edit</button>
                        </form>
                    </td> --%>
                    <%-- <td>
                        <form action="${pageContext.request.contextPath}/categories/${category.id}" method="post">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td> --%>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>