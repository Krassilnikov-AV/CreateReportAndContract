<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<%--<table><tbody></tbody></table>--%>
<%--<c:forEach items="${requestScope.shedules}" var="shedule">--%>

  <%--<p id="pro<c:out value="${shedule.pro}"></c:out>" code = "<c:out value="${shedule.code}"></c:out>" audit = "<c:out value="${shedule.audit}"></c:out>"  type = "<c:out value="${shedule.type}"></c:out>">--%>

  <%--</p>--%>


<%--</c:forEach>--%>
<%--</body>--%>
</html>