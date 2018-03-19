<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddProduct</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="css/stoleTableCss.css">
<div class="form-style-6">
    <H1>New product</H1>
    <h4>${message}</h4>
    <form method="post" action="/addproduct">
        <table>
            <tr>
                <td><label path="name">Name</label></td>
                <td><input type="text" name="name" path="name" required/></td>
            </tr>
            <tr>
                <td><label path="price">Price</label></td>
                <td><input type="text" name="price" required/></td>
            </tr>
            <tr>
                <td><label path="description">Description</label></td>
                <td><input type="text" name="description"/></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/product">Go back to Product</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
