package query;


import model.*;
import readDoc.ReadExcelDataImpl;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
/*
 * класс для создания запросов к таблицам в БД
 * **/

public class SQLQueryDataImpl implements SQLQuery {

	/*метод вставки данных в БД*/
	@Override
	public boolean insertExecuteBatchQuerySQL(Connection connection, InputStream fileStream) throws IOException,
		SQLException {
		ReadExcelDataImpl read = new ReadExcelDataImpl(fileStream);
/*попытка создания запроса для занесения в БД не повторяющихся данных*/
//		String insertUnikSQL = "INSERT INTO schedule(program, codgroup, datestart, timestart, datefinish, timefinish, " +
//			"auditorium, typelesson, teacher) " +
//			"SELECT * FROM(SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?) AS tmp WHERE NOT EXISTS(" +
//			"SELECT program, codgroup, datestart, timestart, datefinish, timefinish, auditorium, typelesson, teacher" +
//			"FROM schedule WHERE program =? AND codgroup =? AND datestart =? AND timestart =? AND datefinish=?" +
//			"AND timefinish=? AND auditorium =? AND typelesson =? AND teacher =?) LIMIT 1";

		String insertStartSQL = "INSERT INTO shedule(program, codgroup, datestart, timestart, datefinish, " +
			"timefinish, auditorium, typelesson, teacher, period) " +
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stm = connection.prepareStatement(insertStartSQL)) {
			List<SheduleInsert> shedulesInsert = read.getShedulesSearch();

			long start = System.currentTimeMillis();
			for (SheduleInsert sheduleInsert : shedulesInsert) {
				stm.setString(1, sheduleInsert.getPro());

				stm.setString(2, sheduleInsert.getGroup());

				stm.setTimestamp(3, new Timestamp(sheduleInsert.getDateStart().getTime()));

				stm.setTime(4, new Time(sheduleInsert.getTimeStart().getTime()));

				stm.setTimestamp(5, new Timestamp(sheduleInsert.getDateFinish().getTime()));

				stm.setTime(6, new Time(sheduleInsert.getTimeFinish().getTime()));

				stm.setString(7, sheduleInsert.getAudit());

				stm.setString(8, sheduleInsert.getType());

				stm.setString(9, sheduleInsert.getTech());
				stm.setInt(10, sheduleInsert.getPeriod());

				stm.addBatch();

			}

			long end = System.currentTimeMillis();
			System.out.println("Вставлено: " + shedulesInsert.size() + " строк");
			System.out.println("суммарное время вставки: " + (end - start) + " ms");
			int[] operationResult = stm.executeBatch();
			if (operationResult.length > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Данные не занесены, ошибка при выполнении....!!!");
			e.printStackTrace();
		}
		return false;
	}

	/*метод для сортировки программы обучения по выбранному месяцу*/
	@Override
	public ShedulesSearch addValueTableShedule(Connection connection, String search, String dateMonth) throws SQLException {
		List<SheduleSearch> shedules = new ArrayList();
		String id, program, codegroup, timeStart, dateEnd, timeEnd, auditorium, typelesson, teacher;
		String searchSql = "%" + search + "%";
		String dateMonthSql = "%" + dateMonth + "%";
		String SQL = "SELECT * FROM shedule WHERE  UPPER(program) LIKE UPPER(?) AND  text(datestart) like ?";
		try (PreparedStatement stm = connection.prepareStatement(SQL)) {
			stm.setString(1, searchSql);
			stm.setString(2, dateMonthSql);

			ResultSet resultSet = stm.executeQuery();
			String dateSearh;
			SheduleSearch shedule;
			while (resultSet.next()) {
				id = resultSet.getString("id_shedule");
				codegroup = resultSet.getString("codgroup");
				program = resultSet.getString("program");
				dateSearh = String.valueOf(resultSet.getDate("datestart"));
				timeStart = String.valueOf(resultSet.getTime("timestart"));
				dateEnd = String.valueOf(resultSet.getTime("datefinish"));
				timeEnd = String.valueOf(resultSet.getTime("timefinish"));
				auditorium = resultSet.getString("auditorium");
				typelesson = resultSet.getString("typelesson");
				teacher = resultSet.getString("teacher");
				shedule = new SheduleSearch(
					codegroup
					, program
					, dateSearh
					, timeStart
					, dateEnd
					, timeEnd
					, auditorium
					, typelesson
					, teacher
				);
				shedule.setId(id);
				shedules.add(shedule);
			}
		}
		return new ShedulesSearch(shedules);
	}

	/*метод для просмотра занесённых данных*/
	@Override
	public ShedulesSearch view(Connection connection) throws SQLException {
		List<SheduleSearch> shedules = new ArrayList<>();
		String SQL = "SELECT * FROM shedule";
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SQL);
			String id, program, codegroup, dateStart, timeStart, dateFinish, timeFinish, auditorium, typelesson, teacher;
			SheduleSearch shedule = null;
			while (resultSet.next()) {
				id = resultSet.getString("id_shedule");
				codegroup = resultSet.getString("codgroup");
				program = resultSet.getString("program");
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

				dateStart = resultSet.getString("datestart");
//				dateStart = df.format(new java.util.Date());

				timeStart = resultSet.getString("timestart");

				dateFinish = resultSet.getString("datefinish");
//				dateFinish = df.format(new java.util.Date());
				timeFinish = resultSet.getString("timefinish");

				auditorium = resultSet.getString("auditorium");
				typelesson = resultSet.getString("typelesson");
				teacher = resultSet.getString("teacher");
				shedule = new model.SheduleSearch(
					codegroup
					, program
					, dateStart
					, timeStart
					, dateFinish
					, timeFinish
					, auditorium
					, typelesson
					, teacher
				);
				shedule.setId(id);
				shedules.add(shedule);
				System.out.println(shedule.toString());
			}
			System.out.println("Запрошенные данные успешно выбраны!");
			return new ShedulesSearch(shedules);
		}
	}

	/*метод для получения данных данных из БД и внесения в таблицу расписания*/
	@Override
	public ShedulesSearch getSheduleBy(Connection connection, String[] idList) throws SQLException {
		String sqlQuery = "SELECT * FROM shedule WHERE id_shedule IN (" + String.join(",", idList) + ")";

		try (PreparedStatement stm = connection.prepareStatement(sqlQuery)) {
			List<SheduleSearch> shedulesInsert = new ArrayList<>();

			ResultSet resultSet = stm.executeQuery();
			String id, program, codegroup, dateStart, timeStart, dateFinish, timeFinish, auditorium, typelesson, teacher;
			SheduleSearch shedule = null;
			while (resultSet.next()) {
				id = resultSet.getString("id_shedule");
				codegroup = resultSet.getString("codgroup");
				program = resultSet.getString("program");

				dateStart = resultSet.getString("datestart");

				timeStart = resultSet.getString("timestart");

				dateFinish = resultSet.getString("datefinish");
				timeFinish = resultSet.getString("timefinish");

				auditorium = resultSet.getString("auditorium");
				typelesson = resultSet.getString("typelesson");
				teacher = resultSet.getString("teacher");
				shedule = new model.SheduleSearch(
					codegroup
					, program
					, dateStart
					, timeStart
					, dateFinish
					, timeFinish
					, auditorium
					, typelesson
					, teacher
				);
				shedule.setId(id);
				shedulesInsert.add(shedule);
				System.out.println(shedule.toString());
			}
			System.out.println("Запрошенные данные успешно выбраны!");
			return new ShedulesSearch(shedulesInsert);
		}
	}


	/*для удаления с бразера данных в таблицах*/
	@Override
	public boolean deletedDataSQL(Connection connection) throws SQLException {
		String deletedSQL = "DELETE FROM shedule";
		try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
			int result = stm.executeUpdate();
			if (result != 0) {
				return true;
			}
		}
		return false;
	}
}
