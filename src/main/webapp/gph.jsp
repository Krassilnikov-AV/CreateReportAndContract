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
<%--<form action="ServletContract" method="POST">--%>
  <%--<p><input type="text" name="fio" size="57">--%>
    <%--<input type="submit" value="Найти" name="searh"></p>--%>

  <%--<p>Результаты поиска: </p><br><br>--%>

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
<form action="servletContract" method="POST" accept-charset="UTF-8">
  <p>Дата создания договора: <input type="text" name="dateContract"> г.</p>
  <p>Заказчик:</p>
  <p>Должность: <input type="text" name="post" size="30"></p>
  <p>Ф.И.О.: <input type="text" name="fioPost" size="57"></p> заполнять в родительном падеже (Ивана Ивановича
  Иванова)
  <p>№ Доверенности:<input type="text" name="numberContract" size="25"> (пример: юр-323/20-д от 29.12.2020)</p>
  <p>Ф.И.О. преподавателя:<input type="text" name="fioTeacher" size="25"> (пример: Бориса Борисовича Петрова)</p>
  <%--<p> Предмет договора:</p>--%>
  <%--<p>Наименование общеобразовательной общеразвивающей программы:--%>
    <%--<input type="text" name="nameProgram"></p>--%>
  <p>Дата начала оказания услуг:
    <input type="text" name="dateStartContract"></p>
  <p>Дата окончания оказания услуг:
    <input type="text" name="dateFinishContract"></p>
  <p>Общий объем оказания услуг:</p>
  <p>Оплата: <input type="text" name="payment" size="5"> рублей в час</p>
  <p>Место оказания услуг: <input type="text" name="workAddress" size="57"></p>
  <%--Раздел 3._цена договора и порядок расчёта--%>
  <p>Цена договора составляет:<input type="text" name="contractPrice" size="57"> </p>
  <%--раздел_7 Срок действия, основания и порядок изменения и расторжения Договора --%>
  <p>Действие договора до: пересмотреть на окончательную дату
    оказания услуг!!!!<input type="text" name="contractPeriod" size="57"></p>

  <%--раздел 10____ Адреса и реквизиты сторон--%>
  <p>Данные исполнителя:</p>
  <p>Ф.И.О.: <input type="text" name="serviceСost" size="57">!!! заполнить из БД</p>
  <p>Дата рождения: <input type="text" name="dateBirth" size="57"></p>
  <p>Место рождения: <input type="text" name="placeBirth" size="57"></p>
  <p>Адрес регистрации: <input type="text" name="registrationAddress" size="57"></p>
  <p>Образование: <input type="text" name="education" size="32"></p>
  <p>Данные диплома вуза: <input type="text" name="detailsDiploma" size="57"></p>
  <p>серия  № <input type="text" name="serialDiploma" size="25">
      от:<input type="text" name="dateDiploma" size="25"> </p>

  <p>паспорт: серия <input type="text" name="passportSerial" size="20">
    № <input type="text" name="passportNumber" size="20"></p>
  <p>Выдан кем/когда: <input type="text" name="issuedWhomWhen" size="57"></p>
  <p>ИНН <input type="text" name="numberINN" size="57"></p>
  <p>Номер страхового свидетельства: <input type="text" name="certificateInsurance" size="57"></p>

  <p>Общая стоимость оказания услуг: <input type="text" name="serviceСost" size="57"></p>
  <p>Место оказания услуг: <input type="text" name="PlaceServiceProvision" size="57"></p>
  <p>Общая цена договора: <input type="text" name="TotalContractPrice" size="57">
  </p> <%--:___считать умножением или вручную?--%>
  <p>Общая стоимость оказания услуг: <input type="text" name="TotalCostServices" size="57">
  </p> <%-- вручную (пример: 1000 (тысяча) рублей в час   --%>

  <p>ИНН: <input type="text" name="serviceСost" size="57"></p>
  <p>Номер страхового свидетельства:<input type="text" name="serviceСost" size="57"></p>
  <p>Наименование банка: <input type="text" name="serviceСost" size="57"></p>
  <p>БИК банка: <input type="text" name="serviceСost" size="57"></p>
  <td>
</td>

      <input type="hidden" name="operation" id="create_contract" value="create_contract" style="display:none"/>
      <p><input type="submit" value="Создать договор" name="create_contract"/></p>
</form>
</body>
</html>