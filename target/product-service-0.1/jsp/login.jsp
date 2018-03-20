<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello my sweety</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="css/stoleTableCss.css">
<div class="form-style-6">
    <h1>Sign-in</h1>
    <form method="post" action="/login">
        <c:if test="${cookie.error.value != null}">
            <p>
                Invalid username or password.
            </p>
        </c:if>
        <c:if test="${cookie.logout.value != null}">
            <p>
                You have been logged out.
            </p>
        </c:if>
        <table>
            <tr>
                <td><label path="Username">Username</label></td>
                <td><input type="text" name="username" placeholder="Username" path="username" required/></td>
            </tr>
            <tr>
                <td><label path="Passwords">Password</label></td>
                <td><input type="password" name="password" placeholder="Passwords" path="passwords" required/></td>
            </tr>

            <tr>
                <td colspan="2">
                    <table width="100%" border="0">
                        <tr>
                            <td>
                                <input type="submit" value="login"/>
                            </td>
                            <td>
                                <a class="button" href="/signUp">Sign-up</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
