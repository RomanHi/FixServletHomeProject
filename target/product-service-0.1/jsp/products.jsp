<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="css/stoleTableCss.css">
<h4>${emptyData}</h4>
<div class="form-style-6">
    <table border="0">
        <caption>Таблица товаров</caption>
        <tr>
            <th>Наименование</th>
            <th>Цена за штуку</th>
            <th>Описание</th>
        </tr>
        <c:forEach items="${allProduct}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <a class="admin" href="/addproduct">Add product</a>
            </td>
        </tr>
    </table>

</div>
</body>
</html>
