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
  <%--<p>Выбрать период: <input type="month" name="calendar">--%>
  <%--<input type="submit" value="Выбрать"></p>--%>
  <%--<p><input type="text" name="pathToSave" size="57">--%>
  <%--<input type="submit" value="Найти" name="searh"></p>--%>
  <%--<p>Результаты поиска: </p><br><br>--%>
  <p>Дата создания договора: <input type="text" name="dateContract"> г.</p>
  <p>Заказчик:</p>
  <p>Должность: <input type="text" name="post" size="30"></p>
  <p>Ф.И.О.: <input type="text" name="fioPost" size="57"></p>
  <p>№ Доверенности:<input type="text" name="numberContract" size="25"> (пример: юр-323/20-д от 29.12.2020)</p>
  <p> Предмет договора:</p>
  <p>Наименование общеобразовательной общеразвивающей программы:
    <input type="text" name="nameProgram"></p>
    <p>Дата начала оказания услуг:
      <input type="text" name="dateStartContract"> </p>
    <p>Дата окончания оказания услуг:
      <input type="text" name="dateFinishContract"> </p>
    <p>Общий объем оказания услуг:</p>

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
<%--

4.5.	Общая стоимость оказания услуг: вручную (пример: 1000 (тысяча) рублей в час
5.	Место оказания услуг:вручную
6.	Общая цена договора:___считать умножением или вручную?
7.	Данные исполнителя:
7.1.	Ф.И.О.: вручную
7.2.	Дата рождения: вручную
7.3.	Место рождения: вручную
7.4.	Адрес регистрации: вручную
7.5.	Образование: вручную
7.6.	Данные диплома вуза: вручную
7.7.	Серия № от: вручную
7.8.	Паспорт серия №: вручную
7.9.	Выдан кем/когда: вручную
7.10.	ИНН
7.11.	Номер страхового свидетельства:
8.  Нсименование банка:
9.  БИК банка


--%>
</td>
</body>
</html>
