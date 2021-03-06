<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Загрузка данных в БД</title>

</head>
<body>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Загрузка данных файла</title>
</head>
<br>
<h1>Загрузка файла</h1>
<form action="servletUpload" method="post"
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

<!--отдельное згрузка и удаление-->
<form action="servletUpload" method="post">
  <input type="hidden" name="operation" id="upload" value="upload" style="display:none"/>
  <input type="submit" value="Загрузите данные" name="insert"/></BR></br>
</form>
<form action="servletUpload" method="post">
  <input type="hidden" name="operation" id="delete" value="delete" style="display:none"/>
  <input type="submit" value="Удалить данные" name="delete"/></BR></br>
</form>

<input type="submit" value="СМОТРЕТЬ ДАННЫЕ" name="view"/>


<p><a href="schedule.jsp">
  <h>Отчёт расписания занятий</h>
</a></p>
<p><a href="gph.jsp">
  <h>Договор ГПХ внештатных сотрудников</h>
</a></p>
</body>
</html>
