package query;


import connection.ConnectionApp;
import model.*;
import readDoc.ReadExcelData;

import java.io.IOException;
import java.sql.Date;
import java.sql.*;
import java.text.*;
import java.util.*;

import static java.util.Calendar.MONTH;

/**
 * Класс SQLQueryData
 */

//public class SQLQueryData implements SQLQuery {
public class SQLQueryData {
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

	/**
	 * Команда INSERT INTO <table_name> в SQL отвечает за добавление данных в таблицу:
	 * <p>
	 * INSERT INTO <table_name> (<col_name1>, <col_name2>, <col_name3>, …)
	 * VALUES (<value1>, <value2>, <value3>, …);
	 */
//	String insertTimeStartSQL = "INSERT INTO schedule(timestart) VALUES(?)";
//	String insertDateStartSQL = "INSERT INTO schedule(datestart) VALUES(?)";


	//	String insertSQL = "INSERT INTO schedule(program) VALUES(?)";

//	String insertSQL = "insert into group(groupid) values (?)";
//	String searchToProgram = "SELECT datestart, timestart FROM schedule";
//	String searchToProgram = "SELECT programm FROM raspisanie";

	ConnectionApp con = new ConnectionApp();
	ReadExcelData read = new ReadExcelData();

	/**
	 * метод извлечения данных из БД (преполавателя, даты и времени начала занятий)
	 * Данный метод позволит избежать внесения повторяющих значений в БД
	 */
	public void gettingLearnTime() throws IOException, SQLException {
		try (Connection conn = con.getPostConnection()) {
			String getLearnTime = "SELECT teacher, datestart, timestart FROM schedule";

		}
	}

	/*
главный метод порверки выполнения запросов
 */
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, ParseException {
		long start = System.currentTimeMillis();
		String search = "C";
		String dateMonth = "2020-03-01";
		SQLQueryData sql = new SQLQueryData();
//		sql.searchToProgram("Java", "2020-06-01");
		sql.addValueTableShedule(search, dateMonth);
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
		long finish = System.currentTimeMillis();
		System.out.println("Время выпонения: " + (finish - start) + " ms");
	}

	/**
	 * метод добавления данных  с применением executeBatch()
	 * для более быстрой вставки в БД
	 */
//	@Override
	public boolean insertExecuteBatchQuerySQL(Connection connection) throws IOException, SQLException {

		String insertStartSQL = "INSERT INTO schedule(program, codgroup, datestart, timestart, datefinish, " +
			"timefinish, auditorium, typelesson, teacher) " +
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stm = connection.prepareStatement(insertStartSQL)) {

			LinkedList<String> listProgram = (LinkedList<String>) read.getString(discipline);
			LinkedList<String> listCodgroup = (LinkedList<String>) read.getString(codeGroup);
			LinkedList<Date> listDateStart = read.getDate(dateStart);
			LinkedList<Date> listTimeStart = read.getDate(timeStart);
			LinkedList<Date> listDateFinish = read.getDate(dateEnd);
			LinkedList<Date> listTimeFinish = read.getDate(timeEnd);
			LinkedList<String> listAuditorium = (LinkedList<String>) read.getString(clasRum);
			LinkedList<String> listTypelesson = (LinkedList<String>) read.getString(typeLearn);
			LinkedList<String> listTeacher = (LinkedList<String>) read.getString(teacher);

			int size = listDateStart.size();

			long start = System.currentTimeMillis();
			for (int i = 0; i < size; i++) {

				String prog = listProgram.pop();
				stm.setString(1, prog);

				String cod = listCodgroup.pop();
				stm.setString(2, cod);

				Date ds = listDateStart.pop();
				stm.setTimestamp(3, new Timestamp(ds.getTime()));

				Date ts = listTimeStart.pop();
				stm.setTime(4, new Time(ts.getTime()));

				Date df = listDateFinish.pop();     //возвращает в LinkedList
				stm.setTimestamp(5, new Timestamp(df.getTime()));

				Date tf = listTimeFinish.pop();
				stm.setTime(6, new Time(tf.getTime()));

				String audit = listAuditorium.pop();
				stm.setString(7, audit);

				String typeLes = listTypelesson.pop();
				stm.setString(8, typeLes);

				String teach = listTeacher.pop();
				stm.setString(9, teach);

				stm.addBatch();
//					long startInternal = System.currentTimeMillis();
//					System.out.println("время вставки эелемента: " +
//						(System.currentTimeMillis() - startInternal) + " " + "ms");
			}
			long end = System.currentTimeMillis();
			System.out.println("Вставлено: " + size + " строк");
			System.out.println("суммарное время вставки: " + (end - start) + " ms");
			int[] operationResult = stm.executeBatch();
			if (operationResult.length > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Данные не занесены, ошибка при выполнении....!!!");
			e.printStackTrace();
		}
//		}
		return false;
	}

//	@Override
//	public void insertExecuteBatchQuerySQL() throws IOException, SQLException {
//
//	}

//	@Override
//	public LinkedList<String> searchToCodegroup(String search, String dateMonth) throws SQLException, ParseException {
//		ConnectionApp con = new ConnectionApp();
//		List<String> pro = new LinkedList<>();
//		List<String> code = new LinkedList<>();
//
//		try (Connection connection = con.getPostConnection()) {
//			String SQL = "SELECT * FROM schedule";
//			try (Statement statement =
//					 connection.createStatement(
//						 ResultSet.TYPE_SCROLL_INSENSITIVE,
//						 ResultSet.CONCUR_READ_ONLY)) {
//
//				ResultSet resultSet;
//				resultSet = statement.executeQuery(SQL);
//				String programs;
//				String dateSearh;
//				String codegroup;
//				int row;
//				Set<String> words = null;
//				while (resultSet.next()) {
//					programs = resultSet.getString(1);
//					dateSearh = String.valueOf(resultSet.getDate("datestart"));
//					codegroup = resultSet.getString(2);
//					words = new HashSet<String>(
//						Arrays.asList(programs.split(" "))
//					);
////					сравнение полученной даты с БД с полученным параметром даты
//					java.util.Date dateResult;
//					dateResult = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
//					java.util.Date date1;
//					date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateMonth);
//
//					Calendar cal = getInstance();
//					cal.setTime(dateResult);
//					Calendar cal1;
//					cal1 = getInstance();
//					cal1.setTime(date1);
//
//					boolean sameDay;
//					sameDay = cal.get(MONTH) == cal1.get(MONTH);
//// если данное слово содержится в столбце БД добавляем учителя в список
//					if (words.contains(search) && sameDay) {
//						row = resultSet.getRow();
//						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
//						((LinkedList<String>) code).add(codegroup);  // добвляет указанный элемент в конец этого списка
//						System.out.println("индекс: " +
//							row + ": " + " код программы: " +
//							((LinkedList<String>) code).pop());
//					}
//				}
//				assert words != null;
//				if (words.isEmpty()) {
//					System.out.println("Совпадений не найдено!");
//				} else {
//					System.out.println("Данные успешно выбраны!");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return (LinkedList<String>) code;
//	}


	/**
	 * метод для получения запрашиваемых данных по на правлению с таблицы
	 *
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

//	String searchToProgram = "Java";  // слово для поиска данных
//	@Override
//	public LinkedList<String> searchToProgram(String search, String dateMonth) throws SQLException, ParseException {
//
//		ConnectionApp con = new ConnectionApp();
//		List<String> listProgs = new LinkedList<>();
//		List<String> date = new LinkedList<>();
//
//		try (Connection connection = con.getPostConnection()) {
//			String SQL = "SELECT * FROM schedule";
//			try (Statement statement =
//					 connection.createStatement(
//						 ResultSet.TYPE_SCROLL_INSENSITIVE,
//						 ResultSet.CONCUR_READ_ONLY)) {
//
//				ResultSet resultSet = statement.executeQuery(SQL);
//				String programs;
//				String dateSearh;
//				int row = 0;
//				Set<String> words = null;
////				List<String> listSearh=null;
//				while (resultSet.next()) {
//					programs = resultSet.getString(1);
//					dateSearh = String.valueOf(resultSet.getDate("datestart"));
//					words = new HashSet<String>(
//						Arrays.<String>asList(String.valueOf(programs.contains(search)))
//					);
////					сравнение полученной даты с БД с полученным параметром даты
//					java.util.Date dateResult;
//					dateResult = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
//					java.util.Date date1;
//					date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateMonth);
//
//					Calendar cal = getInstance();
//					cal.setTime(dateResult);
//					Calendar cal1 = getInstance();
//					cal1.setTime(date1);
//
//					boolean sameDay;
//					sameDay = cal.get(MONTH) == cal1.get(MONTH);
//// если данное слово содержится в столбце БД добавляем учителя в список
//					if (words.contains(search) && sameDay) {
//						row = resultSet.getRow();
//						listProgs.add(programs);
////						System.out.println("индекс: " + row + ": " +
////							((LinkedList<String>) listProgs).pop());
//					}
//				}
//				assert words != null;
//				if (words.isEmpty()) {
//					System.out.println("Совпадений не найдено!");
//				} else {
//					System.out.println("Данные успешно выбраны!");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return (LinkedList<String>) listProgs;
//	}


//	@Override
//	public List<String> searchToDateStart(String search, String dateMonth) throws SQLException, ParseException {
//		ConnectionApp con = new ConnectionApp();
////		List<String> pro = new LinkedList<>();
//		List<String> dateStart = new LinkedList<>();
//
//		try (Connection connection = con.getPostConnection()) {
//			String SQL = "SELECT * FROM schedule";
//			try (Statement statement =
//					 connection.createStatement(
//						 ResultSet.TYPE_SCROLL_INSENSITIVE,
//						 ResultSet.CONCUR_READ_ONLY)) {
//
//				ResultSet resultSet = statement.executeQuery(SQL);
//				String programs;
//				String dateSearh;
//				Set<String> words = null;
//				int row;
//
//				while (resultSet.next()) {
//					programs = resultSet.getString(1);
//					dateSearh = String.valueOf(resultSet.getDate("datestart"));
////	!!! доработать поиск, чтоб учитывал слово в скобках
//
//					words = new HashSet<String>(
//						Arrays.<String>asList(String.valueOf(programs.contains(search)))
//					);
////					сравнение полученной даты с БД с полученным параметром даты
//					java.util.Date dateResult;
//					dateResult = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
//					java.util.Date date1;
//					date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateMonth);
//
//					Calendar cal = getInstance();
//					cal.setTime(dateResult);
//					Calendar cal1;
//					cal1 = getInstance();
//					cal1.setTime(date1);
//
//					boolean sameDay;
//					sameDay = cal.get(MONTH) == cal1.get(MONTH);
//// если данное слово содержится в столбце БД добавляем учителя в список
//					if (words.contains(search) && sameDay) {
//						row = resultSet.getRow();
//						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
//						java.util.Date dates = new SimpleDateFormat("yyyy-MM-dd").parse(dateSearh);  // парсит
//						SimpleDateFormat simplDate = new SimpleDateFormat("yyyy.MM.dd");
////						java.util.Date dateRefact = new java.util.Date();
//						String exp = simplDate.format(dates);
//						((LinkedList<String>) dateStart).add(exp);  // добвляет указанный
//						// элемент в конец этого списка
////						System.out.println("индекс: " +
////							row + ": " + " дата начала: " +
////							((LinkedList<String>) dateStart).pop());
//					}
//				}
//				if (!words.isEmpty()) {
//					System.out.println("Запрошенные данные по дате начала успешно выбраны!");
//				} else {
//					System.out.println("Совпадений не нашлось");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return dateStart;
//	}

//	@Override
//	public List<String> searchToTimeStart(String search, String dateMonth) throws SQLException, ParseException {
//		ConnectionApp con = new ConnectionApp();
//		List<String> pro = new LinkedList<>();
//		List<String> timeStart = new LinkedList<>();
//
//		try (Connection connection = con.getPostConnection()) {
//			String SQL = "SELECT * FROM schedule";
//			try (Statement statement =
//					 connection.createStatement(
//						 ResultSet.TYPE_SCROLL_INSENSITIVE,
//						 ResultSet.CONCUR_READ_ONLY)) {
//
//				ResultSet resultSet;
//				resultSet = statement.executeQuery(SQL);
//				String programs;
//				String timeSearh;
//				String dateSearh;
//				Set<String> words = null;
//				int row;
//
//				while (resultSet.next()) {
//					programs = resultSet.getString(1);
//					dateSearh = String.valueOf(resultSet.getDate("datestart"));
//					timeSearh = String.valueOf(resultSet.getTime("timestart"));
////	!!! доработать поиск, чтоб учитывал слово в скобках
//
//					words = new HashSet<>(
//						Arrays.<String>asList(String.valueOf(programs.contains(search)))
//					);
////					сравнение полученной даты с БД с полученным параметром даты
//					java.util.Date dateResult;
//					dateResult = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
//					java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateMonth);
//
//					Calendar cal = getInstance();
//					cal.setTime(dateResult);
//					Calendar cal1;
//					cal1 = getInstance();
//					cal1.setTime(date1);
//
//					boolean sameDay;
//					sameDay = cal.get(MONTH) == cal1.get(MONTH);
//// если данное слово содержится в столбце БД добавляем учителя в список
//					if (words.contains(search) && sameDay) {
//						row = resultSet.getRow();
//						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
//						java.util.Date timeS = new SimpleDateFormat("HH:mm").parse(timeSearh);  // парсит
//						SimpleDateFormat simplDate = new SimpleDateFormat("HH:mm");
////						java.util.Date dateRefact = new java.util.Date();
//						String exp = simplDate.format(timeS);
//						((LinkedList<String>) timeStart).add(exp);  // добвляет указанный
//						// элемент в конец этого списка
////						System.out.println("индекс: " +
////							row + ": " + " дата начала: " +
////							((LinkedList<String>) timeStart).pop());
//					}
//				}
//				if (!words.isEmpty()) {
//					System.out.println("Запрошенные данные по времени начала успешно выбраны!");
//				} else {
//					System.out.println("Совпадений не нашлось");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return timeStart;
//	}

//
//	@Override
//	public LinkedList<String> searchToAuditorium(String search, String dateMonth) throws SQLException, ParseException {
//		ConnectionApp con = new ConnectionApp();
//		List<String> pro = new LinkedList<>();
//		List<String> audit = new LinkedList<>();
//
//		try (Connection connection = con.getPostConnection()) {
//			String SQL = "SELECT * FROM schedule";
//			try (Statement statement =
//					 connection.createStatement(
//						 ResultSet.TYPE_SCROLL_INSENSITIVE,
//						 ResultSet.CONCUR_READ_ONLY)) {
//				ResultSet resultSet = statement.executeQuery(SQL);
//				String program;
//				String dateSearh;
//				String aud;
//				int row;
//				Set<String> words = null;
//				while (resultSet.next()) {
//					program = resultSet.getString(1);
//					dateSearh = String.valueOf(resultSet.getDate("datestart"));
//					aud = resultSet.getString(7);
////	!!! доработать поиск, чтоб учитывал слово в скобках
//					// поиск слова в получаемом списке
//					words = new HashSet<>(
//						Arrays.<String>asList(String.valueOf(program.contains(search)))
//					);
//					java.util.Date dateResult =
//						new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
//					java.util.Date date1 =
//						new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateMonth);
//
//					Calendar cal = getInstance();
//					cal.setTime(dateResult);
//					Calendar cal1 = getInstance();
//					cal1.setTime(date1);
//
//					boolean sameDay = cal.get(MONTH) == cal1.get(MONTH);
//// если данное слово содержится в столбце БД добавляем учителя в список
//					if (words.contains(search) && sameDay) {
//						row = resultSet.getRow();
//						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
//						((LinkedList<String>) audit).add(aud);  // добвляет указанный элемент в конец этого списка
////						System.out.println("индекс: " +
////							row + ": " + " аудитория: " +
////							((LinkedList<String>) audit).pop());
//					}
//				}
//				if (!words.isEmpty()) {
//					System.out.println("Запрошенные данные по аудиториям успешно выбраны!");
//				} else {
//					System.out.println("Совпадений не найдено!");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return (LinkedList<String>) audit;
//	}
//	@Override
//	public LinkedList<String> searchToTeacher(String search, String dateMonth) throws SQLException, ParseException {
//		ConnectionApp con = new ConnectionApp();
//		List<String> pro = new LinkedList<>();
//		List<String> tech = new LinkedList<>();
//
//		try (Connection connection = con.getPostConnection()) {
//			String SQL = "SELECT * FROM schedule";
//			try (Statement statement =
//					 connection.createStatement(
//						 ResultSet.TYPE_SCROLL_INSENSITIVE,
//						 ResultSet.CONCUR_READ_ONLY)) {
//
//				ResultSet resultSet = statement.executeQuery(SQL);
//				String program;
//				String dateSearh;
//				String teacher;
//				Set<String> words = null;
//				int row;
//				while (resultSet.next()) {
//					program = resultSet.getString(1);
//					dateSearh = String.valueOf(resultSet.getDate("datestart"));
//					teacher = resultSet.getString(9);
////	!!! доработать поиск, чтоб учитывал слово в скобках
//					// поиск слова в получаемом списке
//					words = new HashSet<>(
//						Arrays.asList(program.split(" "))
//					);
//					java.util.Date dateResult;
//					dateResult = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
//					java.util.Date date1 =
//						new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateMonth);
//
//					Calendar cal = getInstance();
//					cal.setTime(dateResult);
//					Calendar cal1 = getInstance();
//					cal1.setTime(date1);
//
//					boolean sameDay;
//					if (cal.get(MONTH) == cal1.get(MONTH)) sameDay = true;
//					else sameDay = false;
//// если данное слово содержится в столбце БД добавляем учителя в список
//					if (words.contains(search) && sameDay) {
//						row = resultSet.getRow();
//						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
//						((LinkedList<String>) tech).add(teacher);  // добвляет указанный элемент в конец этого списка
////						System.out.println("индекс: " +
////							row + ": " + " учитель: " +
////							((LinkedList<String>) tech).pop());
//					}
//				}
//				if (words != null) {
//					if (words.isEmpty()) {
//						System.out.println("нет совпадений");
//					} else {
//						System.out.println("Запрошенные данные по учителям успешно выбраны!");
//					}
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return (LinkedList<String>) tech;
//	}


	/**
	 * метод для поиска данных с выбором ключевого слова и даты
	 *
	 */

	public ShedulesSearch addValueTableShedule(String search, String dateMonth) throws SQLException, IOException, ParseException {
		List<SheduleSearch> shedules = new ArrayList();

		String program, codegroup, timeStart, dateEnd, timeEnd, auditorium, typelesson, teacher;
		try (Connection connection = con.getPostConnection()) {
			String SQL = "SELECT * FROM schedule";
			try (Statement statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {
				ResultSet resultSet = statement.executeQuery(SQL);
				String dateSearh;
				int row;
				SheduleSearch shedule;
				while (resultSet.next()) {
					codegroup = resultSet.getString("codgroup");
					program = resultSet.getString("program");
					dateSearh = String.valueOf(resultSet.getDate("datestart"));
					timeStart = String.valueOf(resultSet.getTime("timestart"));
					dateEnd = String.valueOf(resultSet.getTime("datefinish"));
					timeEnd = String.valueOf(resultSet.getTime("timefinish"));
					auditorium = resultSet.getString("auditorium");
					typelesson = resultSet.getString("typelesson");
					teacher = resultSet.getString("teacher");
					/**/
					java.util.Date dateResult =
						new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
					java.util.Date date1 =
						new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateMonth);

					Calendar calendar =Calendar.getInstance();
					calendar.setTime(dateResult);
					Calendar calendar1 = Calendar.getInstance();
					calendar1.setTime(date1);

					boolean sameDay = calendar.get(MONTH) == calendar1.get(MONTH);
					if (program.contains(search) && sameDay) {
						row = resultSet.getRow();
						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
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
						shedules.add(shedule);
						System.out.println("индекс: " + row + ": " + shedule.toString());
//						((LinkedList<String>) tech).add(teacher);  // добвляет указанный элемент в конец этого списка
//						System.out.println( " учитель: " +
//							((LinkedList<String>) tech).pop());
					}
				}
				System.out.println("Запрошенные данные успешно выбраны!");
				return new ShedulesSearch(shedules);
			}
		}
	}

	/**
	 * метод для просмотра имеющихся данных в БД на странице браузера после загрузки данных
	 */
//	@Override
	public Shedules view(Connection connection) throws SQLException {
		List<Shedule> shedules = new ArrayList<>();
		String SQL = "SELECT * FROM schedule";
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SQL);
			String program, codegroup, auditorium, typelesson, teacher;
			while (resultSet.next()) {
				program = resultSet.getString("program");
				codegroup = resultSet.getString("codgroup");
				auditorium = resultSet.getString("auditorium");
				typelesson = resultSet.getString("typelesson");
				teacher = resultSet.getString("teacher");
				Shedule shedule = new model.Shedule(
					program
					, codegroup
					, auditorium
					, typelesson
					, teacher
				);
				shedules.add(shedule);
				System.out.println(shedule.toString());
			}
			System.out.println("Запрошенные данные успешно выбраны!");
			return new Shedules(shedules);
		}
	}


	/**
	 * метод для удаления данных с таблицы schedule (локально)
	 *
	 * @return
	 * @throws SQLException
	 */
//	@Override
//	public int deletedDataSQLloc() throws SQLException {
//		ConnectionApp con = new ConnectionApp();
//		try (Connection connection = con.getPostConnection()) {
//			String deletedSQL = "DELETE FROM schedule";
//			try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
//				stm.executeUpdate();
//				System.out.println("Данные БД успешно удалены!");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}

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
	public boolean deletedDataSQL(Connection connection) throws SQLException {
		String deletedSQL = "DELETE FROM schedule";
		try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
			int result = stm.executeUpdate();
			if (result != 0) {
				return true;
			}
		}
		return false;
	}
}
