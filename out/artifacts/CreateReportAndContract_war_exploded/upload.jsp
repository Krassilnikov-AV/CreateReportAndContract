
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
  <title>TODO supply a title</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<h1>Отправка файла на сервер</h1>
<form action="Servlet" method="POST" enctype="multipart/form-data">

  <input type="text" name="path" value="" /></BR></BR>
  <input type="file" name="fileToUpload"  size="63" /></BR></BR>
  <input type="submit" value="Отправить" />

</form>
</body>
</html>