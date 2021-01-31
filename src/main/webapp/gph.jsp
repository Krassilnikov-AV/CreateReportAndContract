<%--
  Created by IntelliJ IDEA.
  User: Aleks
  Date: 27.01.2021
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Договор ГПХ</title>

</head>
<body>
<h2>Создание договора гражданско правового характера внештатных сотрудников</h2>
<td>
  <p>Выбрать период: <input type="month" name="calendar">
    <input type="submit" value="Выбрать"></p>
  <p><input type="text" name="pathToSave" size="57">
    <input type="submit" value="Найти" name="searh"></p>
  <p>Результаты поиска: </p><br><br>
  <p>Оплата: <input type="text" name="payment" size="5"> рублей в час</p>
  <p>Место оказания услуг: <input type="text" name="workAddress" size="57"></p>
  <p>Дата рождения: <input type="text" name="dateBirth" size="57"></p>
  <p>Адрес регистрации: <input type="text" name="registrationAddress" size="57"></p>
  <p>Образование: <input type="text" name="education" size="32"></p>
  <p>Данные диплома вуза: <input type="text" name="detailsDiploma" size="57"></p>
  <p>паспорт: <input type="text" name="passport" size="57"></p>
  <p>Персональный номер ИНН: <input type="text" name="numberINN" size="57"></p>
  <p>Номер страхового свидетельства: <input type="text" name="certificateInsurance" size="57"></p>
<td><p><input type="submit" value="Создать договор" name="create"></p></td>
</td>
</body>
</html>
