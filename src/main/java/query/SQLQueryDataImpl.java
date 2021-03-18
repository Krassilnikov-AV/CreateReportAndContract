package query;


import model.*;
import readDoc.ReadExcelDataImpl;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import static java.util.Calendar.MONTH;
/*
 * класс для создания запросов к таблицам в БД
 * **/

public class SQLQueryDataImpl implements SQLQuery {
	//public class SQLQueryDataImpl {
	final static int code = 0;    // код (строка)
	final static int divID = 1;   // ID подразделения (число)
	final static int gpoupID = 2;   // ID группы  (число)
	private int codeGroup = 3;   //+1 код группы  (число)
	final static int group = 4;   // название группы (строка)
	private int dateStart = 5;   // +3 дата начала (дата)
	private int timeStart = 6;   // +4 время начала (время)
	private int dateEnd = 7;   // +5 дата завершения (дата)
	private int timeEnd = 8;   // +6 время завершения (время)
	final static int classID = 9;   // ID аудитории (число)
	private int clasRum = 10;   // +7 №аудитории или вариант (ОнЛайн) (число/строка)
	private int typeLearn = 11;   // +8 тип занятия (строка)
	private int codeDirectionProgramm = 12;   // код-направление-программа (число-строка)
	final static int courseID = 13;   // +2.1 ID курса (число) -
	private int discipline = 14;   // +2 предмет/дисциплина/программа (число/строка)
	final static int period = 15;   // период (число)
	final static int teacherID = 16;   // ID преподавателя (число)
	private int teacher = 17;   // +9 преподаватель (строка)
	final static int periodDay = 18;   // период дней(число)
	final static int academHour = 19;   // академических часов (число)
	final static int academRecord = 20;   // академических записей (число)

	//	ConnectionApp connection = new ConnectionApp();

//	static String fileToRead = "D:\\REPOSITORIES-2\\Primer_raspisania.xlsx";
	/**
	 * метод извлечения данных из БД (преполавателя, даты и времени начала занятий)
	 * Данный метод позволит избежать внесения повторяющих значений в БД
	 */
//	public void gettingLearnTime() throws IOException, SQLException {
//		try (Connection conn = con.getPostConnection()) {
//			String getLearnTime = "SELECT teacher, datestart, timestart FROM schedule";
//
//		}
//	}

	/*
главный метод порверки выполнения запросов
 */
//	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, ParseException {
//		long start = System.currentTimeMillis();
//		String search = "C";
//		String dateMonth = "2020-03-01";
//	SQLQueryDataImpl sql = new SQLQueryDataImpl();

//		sql.insertDataContractTeacherSQL();
//		sql.searchToProgram("Java", "2020-06-01");
//		sql.addValueTableShedule(search, dateMonth);
//		sql.searchToCodegroup("Java", "2020-06-01");
//		sql.searchToDateStart(search, dateMonth);  // выбор по дате
//		sql.searchToAuditorium("Java", "2020-06-01");
	//		sql.searchToTeacher("Java", "2020-06-01");
//		sql.searchToTimeStart(search, dateMonth);
//		sql.regexExample();
//		sql.view();
//		sql.prog();
//		sql.deletedDataSQLloc();
//		sql.insertExecuteBatchQuerySQL();
//		long finish = System.currentTimeMillis();
//		System.out.println("Время выпонения: " + (finish - start) + " ms");
//	}
//	@Override
//	public boolean insertDataContractTeacherSQL() throws IOException, SQLException {
//		ConnectionApp connection = new ConnectionApp();
//		String insertTeacherData = "INSERT INTO contract_teacher(contract_id, name_teacher, title_program) " +
//			"VALUES(?, ?, ?)";
//		try (PreparedStatement stm = connection.prepareStatement(insertTeacherData)) {
//
//			LinkedList<String> listProgram = (LinkedList<String>) read.getString(discipline);
//
//			LinkedList<String> listTeacher = (LinkedList<String>) read.getString(teacher);
//
//			int size = listProgram.size();
//
//			long start = System.currentTimeMillis();
//			for (int i = 0; i < size; i++) {
//
//				String prog = listProgram.pop();
//				stm.setString(1, prog);
//
//				String cod = listTeacher.pop();
//				stm.setString(2, cod);
//
//				/*
//				 * вставить метод проверки
//				 * **/
//				stm.addBatch();
//			}
//			long end = System.currentTimeMillis();
//			System.out.println("Вставлено: " + size + " строк");
//			System.out.println("суммарное время вставки: " + (end - start) + " ms");
//			int[] operationResult = stm.executeBatch();
//			if (operationResult.length > 0) {
//				return true;
//			}
//		} catch (Exception e) {
//			System.out.println("Данные не занесены, ошибка при выполнении....!!!");
//			e.printStackTrace();
//		}
//		return false;
//	}

	/**
	 * метод добавления данных  с применением executeBatch()
	 * для более быстрой вставки в БД
	 * ------------------------------------------------
	 * INSERT INTO table_listnames (name, address, tele)
	 * SELECT * FROM (SELECT 'Rupert', 'Somewhere', '022') AS tmp
	 * WHERE NOT EXISTS (
	 * SELECT name FROM table_listnames WHERE name = 'Rupert'
	 * ) LIMIT 1;
	 */
	@Override
	public boolean insertExecuteBatchQuerySQL(Connection connection, InputStream fileStream) throws IOException,
		SQLException {
		ReadExcelDataImpl read = new ReadExcelDataImpl(fileStream);

//		String insertUnikSQL = "INSERT INTO schedule(program, codgroup, datestart, timestart, datefinish, timefinish, " +
//			"auditorium, typelesson, teacher) " +
//			"SELECT * FROM(SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?) AS tmp WHERE NOT EXISTS(" +
//			"SELECT program, codgroup, datestart, timestart, datefinish, timefinish, auditorium, typelesson, teacher" +
//			"FROM schedule WHERE program =? AND codgroup =? AND datestart =? AND timestart =? AND datefinish=?" +
//			"AND timefinish=? AND auditorium =? AND typelesson =? AND teacher =?) LIMIT 1";

		String insertStartSQL = "INSERT INTO sheduleid(program, codgroup, datestart, timestart, datefinish, " +
			"timefinish, auditorium, typelesson, teacher, period) " +
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stm = connection.prepareStatement(insertStartSQL)) {
			List<SheduleInsert> shedulesInsert = read.getShedulesSearch();

			long start = System.currentTimeMillis();
			for (SheduleInsert sheduleInsert: shedulesInsert) {
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

	/**
	 * 1. необходимо создать условие по которому можно было бы задать запрос, выполняющий наличие данных в БД
	 * по преподавателю дате начал и времени в SQL. После заносил в список эти данные.
	 * 2. Перед занесением данных в БД проверяется наличие в списке совпадений
	 * 3. если нет совпадений, то заносятся в БД
	 */


	/**
	 * метод для получения запрашиваемых данных по на правлению с таблицы
	 *
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	/**
	 * метод для поиска данных с выбором ключевого слова программы обучения и даты
	 */
	@Override
	public List<String> searchToDateStart(Connection connection, String search, String dateMonth) throws SQLException,
		ParseException {
		List<String> dateStart = new LinkedList<>();

		String SQL = "SELECT * FROM sheduleid";
		try (Statement statement =
				 connection.createStatement(
					 ResultSet.TYPE_SCROLL_INSENSITIVE,
					 ResultSet.CONCUR_READ_ONLY)) {

			ResultSet resultSet = statement.executeQuery(SQL);
			String programs = null;
			String dateSearh;
			int row;

			while (resultSet.next()) {
				programs = resultSet.getString("program");
				dateSearh = String.valueOf(resultSet.getDate("datestart"));
				java.util.Date dateResult;
				dateResult = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
				java.util.Date date1;
				date1 = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH).parse(dateMonth);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dateResult);
				Calendar cal1;
				cal1 = Calendar.getInstance();
				cal1.setTime(date1);

				boolean sameDay = cal.get(MONTH) == cal1.get(MONTH);
				if (programs.contains(search) && sameDay) {
					row = resultSet.getRow();
					resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
					java.util.Date dates = new SimpleDateFormat("yyyy-MM-dd").parse(dateSearh);  // парсит
					SimpleDateFormat simplDate = new SimpleDateFormat("yyyy.MM.dd");
					String exp = simplDate.format(dates);
					((LinkedList<String>) dateStart).add(exp);  // добвляет указанный
				}
			}
			if (!programs.isEmpty()) {
				System.out.println("Запрошенные данные по дате начала успешно выбраны!");
			} else {
				System.out.println("Совпадений не нашлось");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dateStart;
	}
	/**
	 * метод для поиска данных с выбором ключевого слова программы обучения и даты
	 */
	@Override
	public ShedulesSearch addValueTableShedule(Connection connection, String search, String dateMonth) throws SQLException {
		List<SheduleSearch> shedules = new ArrayList();
		String id, program, codegroup, timeStart, dateEnd, timeEnd, auditorium, typelesson, teacher;
		String searchSql = "%" + search + "%";
		String dateMonthSql = "%" + dateMonth + "%";
		String SQL = "SELECT * FROM sheduleid WHERE  UPPER(program) LIKE UPPER(?) AND  text(datestart) like ?";
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

	/**
	 * добавить дату и время!!!
	 * метод для просмотра имеющихся данных в БД на странице браузера после загрузки данных
	 */

	@Override
	public ShedulesSearch view(Connection connection) throws SQLException {
		List<SheduleSearch> shedules = new ArrayList<>();
		String SQL = "SELECT * FROM sheduleid";
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

	/*
	- setConnectionBuilder - сеттер установления пул соединения
	- getConnection() - геттер получение pool соединения
	  метод для удаления внесённых данных в таблицу через сервлет
	*/
//	private ConnectionBuilder connectionBuilder;
//
//	public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
//		this.connectionBuilder = connectionBuilder;
//	}
//
//	private Connection getConnection() throws SQLException {
//		return connectionBuilder.getConnection();
//	}

	/*
	 * deletedDataSQL() - для локальной работы  */
//	public void deletedDataSQL() throws SQLException {
//		try (Connection connection = getConnection()) {
//			String deletedSQL = "DELETE FROM schedule";
//			try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
//				stm.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

	/*для удаления с бразера данных в таблице*/
	@Override
	public boolean deletedDataSQL(Connection connection) throws SQLException {
		String deletedSQL = "DELETE FROM sheduleid";
		try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
			int result = stm.executeUpdate();
			if (result != 0) {
				return true;
			}
		}
		return false;
	}
}
