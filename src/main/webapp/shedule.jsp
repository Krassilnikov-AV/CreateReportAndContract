<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <%--<meta http-equiv="Content-Type" content="text/html">--%>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>Расписание занятий</title>
</head>
<body>
<p><a href="upload.jsp">
  <h>Назад</h>
</a></p>

<h2>Создание отчёта расписания занятий</h2>

<form action="servletSchedule" method="POST">
  <table width="400" border="0" cellpadding="3" cellspacing="0">
    <col width="100" valign="top">
    <col width="250" valign="top">
  </table>
  <tbody>
  <form action="servletSchedule" method="POST">

  </form>
  <form action="servletSchedule" method="POST">

    <td><p>Наименование программы обучения по ключевому слову:</p>
      <p><input type="text" name="wordName" size="57" accept-charset="UTF-8"></p>
    </td>

    <td>
      <p>Выбрать период: <input type="month" name="calendar"></p>
    </td>

    <td><p><input type="hidden" name="operation" id="data_shedule" value="data_shedule" style="display:none"/>
      <input type="submit" value="Получить данные" name="data_shedule"></p></td>
    </form>
  <form  action="servletSchedule" method="POST" accept-charset="UTF-8">
    <p>Результаты поиска: </p>
    <table name="table">
      <tr>
        <th>id</th>
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
      <c:forEach items="${requestScope.shedules}" var="data_shedule">

        <tr>
          <ol>
            <td><input type="checkbox" name="data_shedule" value="<c:out value="${data_shedule.id}"></c:out>"></td>
              <%--<td>"<c:out value="${data_shedule.id}"></c:out>"</td>--%>
            <td>"<c:out value="${data_shedule.group}"></c:out>"</td>
            <td>"<c:out value="${data_shedule.pro}"></c:out>"</td>
            <td>"<c:out value="${data_shedule.dateStart}"></c:out>"</td>
            <td>"<c:out value="${data_shedule.timeStart}"></c:out>"</td>
            <td>"<c:out value="${data_shedule.dateFinish}"></c:out>"</td>
            <td>"<c:out value="${data_shedule.timeFinish}"></c:out>"</td>
            <td>"<c:out value="${data_shedule.audit}"></c:out>"</td>
            <td>"<c:out value="${data_shedule.type}"></c:out>"</td>
            <td>"<c:out value="${data_shedule.tech}"></c:out>"</td>
          </ol>
        </tr>
      </c:forEach>
    </table>

    <input type="button" value="Выделить все" onclick="check(this.form.data_shedule, 1)">
    <input type="button" value="Снять выделение" onclick="check(this.form.data_shedule, 0)">
    <%--<tr>--%>
    <%--</tr>--%>
    <p>Должность: <input type="text" name="post" size="30"></p>
    <p>Ф.И.О.: <input type="text" name="fioPost" size="57"></p>
    <input type="hidden" name="operation" id="create" value="create" style="display:none"/>
    <td><p><input type="submit" value="СОЗДАТЬ ОТЧЁТ" name="create"></p></td>
  </form>
</tbody>
  <script type="text/javascript">
    function check(field, flag) {
      if (flag == 1) {
        for (i = 0; i < field.length; i++) field[i].checked = true;
      } else {
        for (i = 0; i < field.length; i++) field[i].checked = false;
      }
    }
  </script>
</form>
<script>

</script>
</body>
</html>