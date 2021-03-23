<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <title>Договор ГПХ</title>

</head>
<body>
<p><a href="upload.jsp">
  <h>Назад</h>
</a></p>
</body>
<body>
<h2>Создание договора гражданско правового характера внештатных сотрудников</h2>
<%--<td>--%>
<h1>Загрузка данных</h1>
 <form action="servletUploadDataDB" method="post"   enctype="multipart/form-data">

  <input type="file" name="file" size="50"/>
<input type="hidden" name="ws_code" id="shopCode" value="shopCode" style="display:none"/>
<p><input type="submit" value="Сохранить данные"/></p>

</form>
<form action="servletContract" method="POST" accept-charset="UTF-8">
  <p>Дата создания договора: <input type="text" name="dateContract"> г.(пример: «05» сентября 2021 г.)</p>
  <p>Заказчик:</p>
  <p>Должность: <input type="text" name="post" size="30">(пример: директора или и.о. директора)</p>
  <p>Ф.И.О.: <input type="text" name="fioPost" size="57">(пример: Сидорова Петра Михайловича)</p>
  <p>№ Доверенности:<input type="text" name="numberContract" size="25"> (пример: юр-323/20-д от 29.12.2020)</p>
  <p>Ф.И.О. преподавателя:<input type="text" name="fioTeacher" size="25"> (пример: Петрова Бориса Николаевича)</p>
  <%--<p> Предмет договора:</p>--%>
  <%--<p>Наименование общеобразовательной общеразвивающей программы:--%>
  <%--<input type="text" name="nameProgram"></p>--%>
<%--</form>--%>
  <p><b>1. Предмет Договора</b></p>
  <%--<form action="servletContract" method="POST">--%>
    <p><input type="text" name="fio" size="57">
      <input type="submit" value="Найти" name="searh"></p>
  <%--</form>--%>
  <p>Результаты поиска: </p><br><br>

  <%--<table name="table">--%>
    <%--<tr>--%>
      <%--<th>id</th>--%>
      <%--<th>group</th>--%>
      <%--<th>pro</th>--%>
      <%--<th>dateStart</th>--%>
      <%--<th>timeStart</th>--%>
      <%--<th>dateFinish</th>--%>
      <%--<th>timeFinish</th>--%>
      <%--<th>audit</th>--%>
      <%--<th>type</th>--%>
      <%--<th>tech</th>--%>
    <%--</tr>--%>
    <%--<c:forEach items="${requestScope.shedules}" var="data_shedule">--%>

      <%--<tr>--%>
        <%--<ol>--%>
          <%--<td><input type="checkbox" name="data_shedule" value="<c:out value="${data_shedule.id}"></c:out>"></td>--%>
            <%--&lt;%&ndash;<td>"<c:out value="${data_shedule.id}"></c:out>"</td>&ndash;%&gt;--%>
          <%--<td>"<c:out value="${data_shedule.group}"></c:out>"</td>--%>
          <%--<td>"<c:out value="${data_shedule.pro}"></c:out>"</td>--%>
          <%--<td>"<c:out value="${data_shedule.dateStart}"></c:out>"</td>--%>
          <%--<td>"<c:out value="${data_shedule.timeStart}"></c:out>"</td>--%>
          <%--<td>"<c:out value="${data_shedule.dateFinish}"></c:out>"</td>--%>
          <%--<td>"<c:out value="${data_shedule.timeFinish}"></c:out>"</td>--%>
          <%--<td>"<c:out value="${data_shedule.audit}"></c:out>"</td>--%>
          <%--<td>"<c:out value="${data_shedule.type}"></c:out>"</td>--%>
          <%--<td>"<c:out value="${data_shedule.tech}"></c:out>"</td>--%>
        <%--</ol>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>

  <%--<input type="button" value="Выделить все" onclick="check(this.form.data_shedule, 1)">--%>
  <%--<input type="button" value="Снять выделение" onclick="check(this.form.data_shedule, 0)">--%>
  <%--<tr>--%>
  <%--</tr>--%>

<%--</form>--%>
<%--</tbody>--%>
<%--<script type="text/javascript">--%>
  <%--function check(field, flag) {--%>
    <%--if (flag == 1) {--%>
      <%--for (i = 0; i < field.length; i++) field[i].checked = true;--%>
    <%--} else {--%>
      <%--for (i = 0; i < field.length; i++) field[i].checked = false;--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>
<%--</form>--%>

  <p><b>2. Срок и место оказания услуг</b></p>
  <p>Период оказания услуг:
    <input type="text" name="dateStartContract" size="15">(пример: 01.09.2021)</p>
  <p>Дата окончания оказания услуг:
    <input type="text" name="dateFinishContract">(пример: 30.11.2021)</p>

  <p><b>Общий объем оказания услуг:</b></p>
  <p>Количество академических часов: <input type="text" name="academicHour" size="15"></p>
  <p>Оплата: <input type="text" name="payment" size="15"> рублей в час</p>
  <p>Место оказания услуг: <input type="text" name="workAddress" size="57"></p>
  <%--Раздел 3._цена договора и порядок расчёта--%>
  <p>Цена договора составляет:<input type="text" name="contractPrice" size="57"> (пример: 00000 (     тысяч)) </p>
  <%--раздел_7 Срок действия, основания и порядок изменения и расторжения Договора --%>
  <%--<p>Действие договора до: <input type="text" name="contractPeriod" size="57">--%>
    <%--пересмотреть на окончательную дату оказания услуг!!!!</p>--%>


  <p><b>10. Адреса и реквизиты сторон</b></p>
  <p>Данные исполнителя:</p>
  <p>Ф.И.О.: <input type="text" name="serviceСost" size="57">!!! заполнить из БД</p>
  <p>Дата рождения: <input type="text" name="dateBirth" size="25"></p>
  <p>Место рождения: <input type="text" name="placeBirth" size="57"></p>
  <p>Адрес регистрации: <input type="text" name="registrationAddress" size="57"></p>
  <p>Образование: <input type="text" name="education" size="32"></p>
  <p>Данные диплома вуза: </p>
  <p><input type="text" name="detailsDiploma" size="57"></p>
  <p>серия  № <input type="text" name="serialDiploma" size="25">
      от:<input type="text" name="dateDiploma" size="25"> </p>

  <p>паспорт:</p>
  <p>серия <input type="text" name="passportSerial" size="20">
    № <input type="text" name="passportNumber" size="20"></p>
  <p>Выдан кем/когда: <input type="text" name="issuedWhomWhen" size="57"></p>
  <p>ИНН <input type="text" name="numberINN" size="57"></p>
  <p>Номер страхового свидетельства: <input type="text" name="certificateInsurance" size="57">(пример: 40817810090700087671)</p>

  <p>Наименование банка: <input type="text" name="nameBank" size="57">(пример: Сбербанк)</p>
  <p>БИК банка: <input type="text" name="bikBank" size="57">(пример: 40817810090700087671)</p>
  <p>№ счета <input type="text" name="numberScore" size="57"> (пример: 40817810090700087671)</p>
  <p>№ карты  <input type="text" name="numberCard" size="57">(пример: МИР 2200330564638743963)</p>
  <p>Тел.  <input type="text" name="numberTel" size="57">(пример:  8921-942-0888)</p>
  <p><b>Заполнение акта №1</b></p>
  <td>
    <p> Оплата из средств л/с  <input type="text" name="" size="20">(пример:  124503003)</p>
</td>

      <input type="hidden" name="operation" id="create_contract" value="create_contract" style="display:none"/>
      <p><input type="submit" value="Создать договор" name="create_contract"/></p>
</form>
</body>
</html>