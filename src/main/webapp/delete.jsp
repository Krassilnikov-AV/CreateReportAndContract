<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
  <title>TODO supply a title</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<h1>Удалить все данные с базы данных</h1>
<form action="servletDelete" method="POST">
  <input type="hidden" name="operation" id="delete" value="delete" style="display:none"/>
  <input type="submit" value="Удалить" name="delete"/>
</form>
<form action="servletDelete" method="POST">
  <input type="hidden" name="operation" id="upload" value="insert" style="display:none"/>
  <input type="submit" value="Вставить" name="insert"/>
</form>
<form action="servletDelete" method="POST">
  <input type="hidden" name="operation" id="view" value="view" style="display:none"/>
  <input type="submit" value="Просмотр" name="view"/>
</form>
<%--<%= %>--%>
<table>
  <tr>
    <th>pro</th>
    <th>code</th>
    <th>audit</th>
    <th>type</th>
  </tr>
  <c:forEach items="${requestScope.shedules}" var="shedule">
    <tr>
      <td>"<c:out value="${shedule.pro}"></c:out>"</td>
      <td>"<c:out value="${shedule.code}"></c:out>"</td>
      <td>"<c:out value="${shedule.audit}"></c:out>"</td>
      <td>"<c:out value="${shedule.type}"></c:out>"</td>
    </tr>

  </c:forEach>
</table>
</body>
</html>