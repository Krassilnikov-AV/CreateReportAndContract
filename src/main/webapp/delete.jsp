<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
  <title>Загрузка данных</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<form action="ServletOperation" method="post"
      enctype="multipart/form-data">
  <p>
    укажите путь сохранения: <input type="text" name="path" value=""/>
  </p>
  <p>
    <input type="file" name="file" size="50"/>
  </p>
  <input type="hidden" name="ws_code" id="shopCode" value="shopCode" style="display:none"/>
  <input type="text" name="token" id="123414" value="123414" style="display:none">
  <!--<input type="hidden" name="ws_code" value="shopCode" style="display:none">-->
  <input type="submit" value="Загрузите файл"/>
</form>
<%--выбор операций с сервлета delete--%>
<h1>Выбор операции: </h1>
<form action="ServletOperation" method="POST">
  <input type="hidden" name="operation" id="upload" value="insert" style="display:none"/>
  <input type="submit" value="Вставить" name="insert"/>
</form>
<form action="ServletOperation" method="POST">
  <input type="hidden" name="operation" id="delete" value="delete" style="display:none"/>
  <input type="submit" value="Удалить" name="delete"/>
</form>
<form action="ServletOperation" method="POST">
  <input type="hidden" name="operation" id="view" value="view" style="display:none"/>
  <input type="submit" value="Просмотр" name="view"/>
</form>
<%--<%= %>--%>
<table>
  <tr>
    <th>group</th>
    <th>pro</th>
    <th>audit</th>
    <th>type</th>
    <th>tech</th>
  </tr>
  <c:forEach items="${requestScope.shedules}" var="shedule">
    <tr>
    <ol>
      <td><input type="checkbox" name="musik" value="juze" checked></td>
      <td>"<c:out value="${shedule.group}"></c:out>"</td>
      <td>"<c:out value="${shedule.pro}"></c:out>"</td>
      <td>"<c:out value="${shedule.audit}"></c:out>"</td>
      <td>"<c:out value="${shedule.type}"></c:out>"</td>
      <td>"<c:out value="${shedule.tech}"></c:out>"</td>
    </ol>
    </tr>
  </c:forEach>
</table>
</body>
</html>
<%--return " //код группы: " + group + " //программа: " + pro + " //аудитория: " + audit +
			" //тип занятия: " + type + " //преподаватель: " + tech;
--%>