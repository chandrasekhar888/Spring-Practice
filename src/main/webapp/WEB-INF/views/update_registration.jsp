<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(to right, #74ebd5, #ACB6E5);
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .container {
        background: #fff;
        padding: 30px 40px;
        border-radius: 15px;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        width: 400px;
        text-align: center;
    }

    h2 {
        color: #333;
        margin-bottom: 20px;
    }

    label {
        font-weight: bold;
        color: #555;
    }

    input[type="text"], input[type="email"] {
        width: 100%;
        padding: 10px;
        margin: 8px 0 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
        font-weight: bold;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }

    .banner {
        width: 100px;
        height: auto;
        margin-bottom: 20px;
    }
</style>
</head>
<body>

<div class="container">
    <img src="https://cdn-icons-png.flaticon.com/512/1055/1055646.png" alt="Student Update" class="banner">
    <h2>Update Student</h2>
<p>ID coming to form: ${fetchid.id}</p>

    <form action="/updateReg" method="post">
    <input type="hidden" name="id" value="${fetchid.id}" />
    <label for="name">Name:</label><br>
    <input type="text" name="name" value="${fetchid.name}" required><br>

    <label for="email">Email:</label><br>
    <input type="email" name="email" value="${fetchid.email}" required><br>

    <label for="course">Course:</label><br>
    <input type="text" name="course" value="${fetchid.course}" required><br>
    
    <label for="mobile">Mobile:</label><br>
    <input type="text" name="mobile" value="${fetchid.mobile}" required><br>

    <input type="submit" value="Update">
</form>

    <c:if test="${not empty msg}">
        <p style="color:green;">${msg}</p>
    </c:if>
</div>

</body>
</html>
	