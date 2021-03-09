<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Расписание занятий</title>
</head>
<body>
<p><a href="upload.jsp">
  <h>Назад</h>
</a></p>

</body>
<body>
<h2>Создание отчёта расписания занятий</h2>

<p>Должность: <input type="text" name="post" size="30"></p>
<p>Ф.И.О.: <input type="text" name="fioPost" size="57"></p>
<form action="servletSchedule" method="POST">
  <table width="400" border="0" cellpadding="3" cellspacing="0">
    <col width="100" valign="top">
    <col width="250" valign="top">
  </table>
  <tbody>
  <%--<tr>--%>
  <%--<td>--%>
  <form action="servletSchedule" method="POST">

    <td><p><input type="text" name="pathToSave" size="57"></p>
    </td>

    <td>
      <p>Выбрать период: <input type="month" name="calendar"></p>
    </td>

    <td><p><input type="hidden" name="operation" id="data_shedule" value="data_shedule" style="display:none"/>
      <input type="submit" value="Получить данные" name="data_shedule"></p></td>
  </form>
  <form type="hidden">
    <p>Результаты поиска: </p>
    <table>
      <tr>
        <th>group</th>
        <th>pro</th>
        <th>audit</th>
        <th>type</th>
        <th>tech</th>
      </tr>
      <c:forEach items="${requestScope.shedules}" var="data_shedule">

        <tr>
          <ol>
            <td><input type="checkbox" name="list" value="<c:out value="${shedule.id}"></c:out>"></td>
            <td>"<c:out value="${shedule.group}"></c:out>"</td>
            <td>"<c:out value="${shedule.pro}"></c:out>"</td>
            <td>"<c:out value="${shedule.audit}"></c:out>"</td>
            <td>"<c:out value="${shedule.type}"></c:out>"</td>
            <td>"<c:out value="${shedule.tech}"></c:out>"</td>
          </ol>
        </tr>
      </c:forEach>
    </table>

    <input type="button" value="Выделить все" onclick="check(this.form.list, 1)">
    <input type="button" value="Снять выделение" onclick="check(this.form.list, 0)">
    <tr>

      <input type="hidden" name="operation" id="delete_program" value="delete_program" style="display:none"/>
      <input type="submit" value="Удалить" name="delete_program"/>

    </tr>
  </form>
  </tbody>


  <form action="servletSchedule" method="POST">
    <input type="hidden" name="operation" id="create" value="create" style="display:none"/>
    <td><p><input type="submit" value="СОЗДАТЬ ОТЧЁТ" name="create"></p></td>
  </form>

  <script type="text/javascript">
    function check(field, flag) {
      if (flag == 1) {
        for (i = 0; i < field.length; i++) field[i].checked = true;
      } else {
        for (i = 0; i < field.length; i++) field[i].checked = false;
      }
    }
  </script>
</body>