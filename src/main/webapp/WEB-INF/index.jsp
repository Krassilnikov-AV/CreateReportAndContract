<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<h1>Hello from Java Vision!</h1><br />

<h2>Все пользователи</h2><br />

<c:forEach var="userNameAge" items="${requestScope.users}">
    <ul>

        <li>Имя: <c:out value="${userNameAge.name}"/></li>

        <li>Возраст: <c:out value="${userNameAge.age}"/></li>
    </ul>
    <hr />

</c:forEach>

<h2>Создание нового пользователя</h2><br />

<form method="post" action="">

    <label><input type="text" name="name"></label>Имя<br>

    <label><input type="number" name="age"></label>Фамилия<br>
    <label><input type="number" name="age"></label>Email<br>
    <label><input type="number" name="age"></label>Пароль<br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>
