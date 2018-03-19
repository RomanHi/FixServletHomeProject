<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign-up and be cool</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="css/stoleTableCss.css">
<div class="form-style-6">
    <H1>Sign-up</H1>
    <form method="post" action="/signUp">
        <c:if test="${cookie.userExist.value != null}">
            <p>
                a user with that user name already exists
            </p>
        </c:if>
        <table>
            <tr>
                <td><label path="username">Username</label></td>
                <td><input type="text" name="username" path="username" placeholder="Username" required/></td>
            </tr>
            <tr>
                <td><label path="Passwords">Password</label></td>
                <td><input type="password" name="password" placeholder="Passwords" required/></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
