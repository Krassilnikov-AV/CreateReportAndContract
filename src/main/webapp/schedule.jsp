<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Расписание занятий</title>
</head>

<body>
<h2>Создание отчёта расписания занятий</h2>
<form name="inserting.jsp" action="" method="POST">
  <table width="400" border="0" cellpadding="3" cellspacing="0">
    <col width="100" valign="top">
    <col width="250" valign="top">
    <tbody>
    <tr>
      <%--<td>--%>
        <p><input type="text" name="pathToSave" size="57">
          <input type="submit" value="Найти" name="searh"></p>
      <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
      <%--<td>--%>
        <p>Поиск группы: <input type="text" name="last" value="" size="30">
          <input type="submit" value="Получить данные" name="data"></p>
      <%--</td>--%>
    </tr>
    <%--<tr>--%>
      <td>
        <p>Выбрать период: <input type="month" name="calendar">
          <input type="submit" value="Выбрать"></p>
        <p>Результаты поиска: </p>
      </td>
    <%--</tr>--%>

    <tr>
      <%--<p><input type="checkbox" name="a" value="1417"> 1417</p>--%>
      <td>
        <textarea name="listData" rows="15" cols="100">
      </textarea>
      </td>
    </tr>
    <tr>
      <td><p><input type="submit" value="Выбрать" name="select">
        <input type="submit" value="Удалить" name="delete">
      </p></td>
    </tr>
    <%--<tr>--%>
      <td><p><input type="submit" value="Создать_отчёт" name="create"></p></td>
    <%--</tr>--%>
</form>
</body>