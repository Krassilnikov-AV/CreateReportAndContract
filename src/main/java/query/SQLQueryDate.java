package query;


import connection.*;
import readDoc.ReadExcelData;

import java.io.IOException;
import java.sql.Date;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

/**
 * Класс SQLQueryDate
 */
public class SQLQueryDate {

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

	/**
	 * метод добавления данных  с применением executeBatch()
	 * для более быстрой вставки в БД
	 */

	public void insertExecuteBatchQuerySQL() throws IOException, SQLException {
		try (Connection connection = con.getPostConnection()) {
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
				stm.executeBatch();
			} catch (Exception e) {
				System.out.println("Данные не занесены, ошибка при выполнении....!!!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * метод для получения запрашиваемых данных по на правлению с таблицы
	 *
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

//	String searchToProgram = "Java";  // слово для поиска данных
	public LinkedList<String> searchToProgram(String search) throws SQLException {

		ConnectionApp con = new ConnectionApp();
		List<String> pro = new LinkedList<>();
//		List<String> tech = new LinkedList<>();

		try (Connection connection = con.getPostConnection()) {
			String SQL = "SELECT * FROM schedule";
			try (Statement statement =
					 connection.createStatement(
						 ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY)) {

				ResultSet resultSet = statement.executeQuery(SQL);
				String program;
//				String teacher;
//				int row;
				while (resultSet.next()) {
					program = resultSet.getString(1);
//	!!! доработать поиск, чтоб учитывал слово в скобках
					// поиск слова в получаемом списке
					Set<String> words = new HashSet<>(
						Arrays.asList(program.split(" "))
					);
// если данное слово содержится в столбце БД добавляем в список
					if (words.contains(search)) {
//						row = resultSet.getRow();
						pro.add(program);
//						System.out.print("индекс: " + row + ": " +
//							((LinkedList<String>) pro).pop());
//						resultSet.absolute(row);
//						((LinkedList<String>) tech).addLast(teacher);
//						System.out.println("индекс: " +
//							row + ": " + " учитель: " +
//							((LinkedList<String>) tech).pop());
					}
				}
				System.out.println("Запрошенные данные программы успешно выбраны!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (LinkedList<String>) pro;
	}


	public LinkedList<String> searchToCodegroup(String search) throws SQLException {
		ConnectionApp con = new ConnectionApp();
//		List<String> pro = new LinkedList<>();
		List<String> code = new LinkedList<>();

		try (Connection connection = con.getPostConnection()) {
			String SQL = "SELECT * FROM schedule";
			try (Statement statement =
					 connection.createStatement(
						 ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY)) {

				ResultSet resultSet = statement.executeQuery(SQL);
				String program;
				String codegroup;
				int row;
				while (resultSet.next()) {
					program = resultSet.getString(1);
					codegroup = resultSet.getString(2);
//	!!! доработать поиск, чтоб учитывал слово в скобках
					// поиск слова в получаемом списке
					Set<String> words = new HashSet<>(
						Arrays.asList(program.split(" "))
					);
// если данное слово содержится в столбце БД добавляем учителя в список
					if (words.contains(search)) {
						row = resultSet.getRow();
						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
						((LinkedList<String>) code).addLast(codegroup);  // добвляет указанный элемент в конец этого списка
//						System.out.println("индекс: " +
//							row + ": " + " код программы: " +
//							((LinkedList<String>) code).pop());
					}
				}
				System.out.println("Запрошенные данные кода программы успешно выбраны!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (LinkedList<String>) code;
	}

	public LinkedList<String> searchToAuditorium(String search) throws SQLException {
		ConnectionApp con = new ConnectionApp();
		List<String> pro = new LinkedList<>();
		List<String> audit = new LinkedList<>();

		try (Connection connection = con.getPostConnection()) {
			String SQL = "SELECT * FROM schedule";
			try (Statement statement =
					 connection.createStatement(
						 ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY)) {

				ResultSet resultSet = statement.executeQuery(SQL);
				String program;
				String aud;
				int row;
				while (resultSet.next()) {
					program = resultSet.getString(1);
					aud = resultSet.getString(7);
//	!!! доработать поиск, чтоб учитывал слово в скобках
					// поиск слова в получаемом списке
					Set<String> words = new HashSet<>(
						Arrays.asList(program.split(" "))
					);
// если данное слово содержится в столбце БД добавляем учителя в список
					if (words.contains(search)) {
						row = resultSet.getRow();
						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
						((LinkedList<String>) audit).addLast(aud);  // добвляет указанный элемент в конец этого списка
//						System.out.println("индекс: " +
//							row + ": " + " аудитория: " +
//							((LinkedList<String>) audit).pop());
					}
				}
				System.out.println("Запрошенные данные по аудиториям успешно выбраны!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (LinkedList<String>) audit;
	}

	// пробный метод "регулярок"
	private void regexExample() {
		// элементарный пример поиска номера телефона
//		String inputString = "This is simple that contains phone number +380505055050 That's great.";
//		String pattern = "(\\d+)";
//
//		Pattern pat = Pattern.compile(pattern);
//		Matcher matcher = pat.matcher(inputString);
//
//		if(matcher.find()){
//			System.out.println("Phone number: " + matcher.group(0));
//		}else {
//			System.out.println("PHONE NUMBER NOT FOUND");
//		}

		String str = "today is tuesday";
//		return ; // returns "false"
//		System.out.println(str.matches(".*?\\b.\\br*?"));  // false

		String text = "Егор Алла Александр";
		Pattern pattern = Pattern.compile("А.+а");
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			System.out.println(text.substring(matcher.start(), matcher.end()));     // Алла Алекса
		}
	}

// показательный метод для сравнения дат
	private void dateEquels() throws ParseException {
//		Date date = new Date();
		String str="2013-08-23";
		String str1="2014-08-23";
		java.util.Date date=  new SimpleDateFormat("yyyy-MM-dd").parse(str);
		java.util.Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(str1);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		if(cal.get(Calendar.YEAR) == cal1.get(Calendar.YEAR)){
			System.out.println("Years are equal");
		}
		else{
			System.out.println("Years not equal");
		}

		if(cal.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)){
			System.out.println("Months are equal");
		}
		else{
			System.out.println("Months not equal");
		}
	}

	public List<String> searchToDateStart(String search) throws SQLException, ParseException {
		ConnectionApp con = new ConnectionApp();
		List<String> pro = new LinkedList<>();
		List<String> dateStart = new LinkedList<>();


		try (Connection connection = con.getPostConnection()) {
			String SQL = "SELECT * FROM schedule";
			try (Statement statement =
					 connection.createStatement(
						 ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY)) {

				ResultSet resultSet = statement.executeQuery(SQL);
				String program;
				String dateSearh;
				String date="2020-05-01";

				int row;
				while (resultSet.next()) {
					program = resultSet.getString(1);
					dateSearh = String.valueOf(resultSet.getDate("datestart"));
//	!!! доработать поиск, чтоб учитывал слово в скобках
					// поиск слова в получаемом списке
					Set<String> words = new HashSet<>(
						Arrays.asList(program.split(" "))
					);
//					сравнение полученной даты с БД с полученным параметром даты
					java.util.Date dateResult=
						new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateSearh);
					java.util.Date date1=
						new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);

					Calendar cal = Calendar.getInstance();
					cal.setTime(dateResult);
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(date1);
//					if(cal.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)){
//						System.out.println("Months are equal");
//					}
//					else{
//						System.out.println("Months not equal");
//					}

//					Calendar calendar1 = Calendar.getInstance();
//					Calendar calendar2 = Calendar.getInstance();
//					calendar1.setTime(dateSearh);
//					calendar2.setTime((java.util.Date) dateStart);
					boolean sameDay = cal.get(Calendar.MONTH) == cal1.get(Calendar.MONTH);
// если данное слово содержится в столбце БД добавляем учителя в список
					if (words.contains(search) && sameDay) {
						row = resultSet.getRow();
						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
						String pattern = "yyyy-MM-dd";
						java.util.Date dates = new SimpleDateFormat(pattern).parse(dateSearh);
						((LinkedList<String>) dateStart).addLast(String.valueOf(dates));  // добвляет указанный
						// элемент в
						// конец этого
						// списка
						System.out.println("индекс: " +
							row + ": " + " дата начала: " +
							((LinkedList<String>) dateStart).pop());
					}
				}
				System.out.println("Запрошенные данные по дате начала успешно выбраны!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dateStart;
	}

	public LinkedList<String> searchToTeacher(String search) throws SQLException {
		ConnectionApp con = new ConnectionApp();
		List<String> pro = new LinkedList<>();
		List<String> tech = new LinkedList<>();

		try (Connection connection = con.getPostConnection()) {
			String SQL = "SELECT * FROM schedule";
			try (Statement statement =
					 connection.createStatement(
						 ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY)) {

				ResultSet resultSet = statement.executeQuery(SQL);
				String program;
				String teacher;
				int row;
				while (resultSet.next()) {
					program = resultSet.getString(1);
					teacher = resultSet.getString(9);
//	!!! доработать поиск, чтоб учитывал слово в скобках
					// поиск слова в получаемом списке
					Set<String> words = new HashSet<>(
						Arrays.asList(program.split(" "))
					);
// если данное слово содержится в столбце БД добавляем учителя в список
					if (words.contains(search)) {
						row = resultSet.getRow();
						resultSet.absolute(row);    // перемещение курсора к заданному номеру строки
						((LinkedList<String>) tech).addLast(teacher);  // добвляет указанный элемент в конец этого списка
//						System.out.println("индекс: " +
//							row + ": " + " учитель: " +
//							((LinkedList<String>) tech).pop());
					}
				}
				System.out.println("Запрошенные данные по учителям успешно выбраны!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (LinkedList<String>) tech;
	}

	/*
	главный метод порверки выполнения запросов
	 */
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, ParseException {
		long start = System.currentTimeMillis();
		SQLQueryDate sql = new SQLQueryDate();
//		sql.searchToProgram("Java");
//		sql.searchToTeacher("Java");
//		sql.searchToCodegroup("Java");
//		sql.dateEquels();
//		sql.searchToDateStart("Java");  // выбор по дате
		sql.regexExample();
//		sql.view();
//		sql.prog();
//		sql.deletedDataSQLloc();
//		sql.insertExecuteBatchQuerySQL();
		long finish = System.currentTimeMillis();
		System.out.println("Время выпонения: " + (finish - start) + " ms");
	}

	/**
	 * метод для просмотра имеющихся данных в БД
	 */
	private void view() {
		ConnectionApp con = new ConnectionApp();

		LinkedList<String> pro = new LinkedList<>();
		LinkedList<String> code = new LinkedList<>();
		LinkedList<String> audit = new LinkedList<>();
		LinkedList<String> type = new LinkedList<>();
		LinkedList<String> tech = new LinkedList<>();

		try (Connection connection = con.getPostConnection()) {
			String SQL = "SELECT * FROM schedule";
			try (Statement statement = connection.createStatement()) {

				ResultSet resultSet = statement.executeQuery(SQL);
				String program, codegroup, auditorium, typelesson, teacher;
//				Date ;

				while (resultSet.next()) {
					program = resultSet.getString("program");
					codegroup = resultSet.getString("codgroup");
					auditorium = resultSet.getString("auditorium");
					typelesson = resultSet.getString("typelesson");
					teacher = resultSet.getString("teacher");
					pro.add(program);
					code.add(codegroup);
					audit.add(auditorium);
					type.add(typelesson);
					tech.add(teacher);
					System.out.println("№: " +
						pro.pop() + " || " +
						code.pop() + " || " +
						audit.pop() + " || " +
						type.pop() + " || " +
						tech.pop());
				}
				System.out.println("Запрошенные данные успешно выбраны!");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * метод для удаления данных с таблицы schedule (локально)
	 *
	 * @return
	 * @throws SQLException
	 */
	public int deletedDataSQLloc() throws SQLException {
		ConnectionApp con = new ConnectionApp();
		try (Connection connection = con.getPostConnection()) {
			String deletedSQL = "DELETE FROM schedule";
			try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
				stm.executeUpdate();
				System.out.println("Данные БД успешно удалены!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/*
	- setConnectionBuilder - сеттер установления пул соединения
	- getConnection() - геттер получение pool соединения
	  метод для удаления внесённых данных в таблицу через сервлет
	*/
	private ConnectionBuilder connectionBuilder;

	public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
		this.connectionBuilder = connectionBuilder;
	}

	private Connection getConnection() throws SQLException {
		return connectionBuilder.getConnection();
	}

	public void deletedDataSQL() throws SQLException {
		try (Connection connection = getConnection()) {
			String deletedSQL = "DELETE FROM schedule";
			try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
				stm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
