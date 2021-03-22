<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
  <title>Загрузка и просмотр данных</title>
</head>
<br>
<h1>Загрузка данных</h1>
<p action="servletUpload" method="post"
      enctype="multipart/form-data">
  <p>
    <input type="file" name="file" size="50"/>
  </p>
  <input type="hidden" name="ws_code" id="shopCode" value="shopCode" style="display:none"/>
  <%--<input type="text" name="token" id="123414" value="123414" style="display:none">--%>

  <p></p><input type="submit" value="Сохранить данные"/></p>

</form>

<form action="servletOperation" method="POST">
  <input type="hidden" name="operation" id="delete" value="delete" style="display:none"/>
  <input type="submit" value="Удалить" name="delete"/>
</form>

<h1>Выбор операции: </h1>

<form action="servletOperation" method="POST">
  <input type="hidden" name="operation" id="view" value="view" style="display:none"/>
  <input type="submit" value="Просмотр" name="view"/>
</form>

<table>

  <tr>
    <th>group</th>
    <th>pro</th>
    <th>dateStart</th>
    <th>timeStart</th>
    <th>dateFinish</th>
    <th>timeFinish</th>
    <th>audit</th>
    <th>type</th>
    <th>tech</th>
  </tr>
  <c:forEach items="${requestScope.shedules}" var="shedule">

    <tr>
      <td>"<c:out value="${shedule.group}"></c:out>"</td>
      <td>"<c:out value="${shedule.pro}"></c:out>"</td>
      <td>"<c:out value="${shedule.dateStart}"></c:out>"</td>
      <td>"<c:out value="${shedule.timeStart}"></c:out>"</td>
      <td>"<c:out value="${shedule.dateFinish}"></c:out>"</td>
      <td>"<c:out value="${shedule.timeFinish}"></c:out>"</td>
      <td>"<c:out value="${shedule.audit}"></c:out>"</td>
      <td>"<c:out value="${shedule.type}"></c:out>"</td>
      <td>"<c:out value="${shedule.tech}"></c:out>"</td>
    </tr>

  </c:forEach>
</table>

<body>
<p><a href="shedule.jsp">
  <h>Отчёт расписания занятий</h>
</a></p>
<p><a href="gph.jsp">
  <h>Договор ГПХ внештатных сотрудников</h>
</a></p>
</body>
</html>
