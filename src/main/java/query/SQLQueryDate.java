package query;


import connection.*;
import readDoc.ReadExcelData;

import java.io.IOException;
import java.sql.Date;
import java.sql.*;
import java.util.*;

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
//	String search = "SELECT datestart, timestart FROM schedule";
//	String search = "SELECT programm FROM raspisanie";

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

//	String search = "Java";  // слово для поиска данных
	public void search(String search) throws SQLException {

		ConnectionApp con = new ConnectionApp();
		List<String> pro = new LinkedList<>();
		List<String> tech = new LinkedList<>();

		try (Connection connection = con.getPostConnection()) {
			String SQL = "SELECT * FROM schedule";
			try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
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
// если данное слово содержится в столбце БД добавляем в список
					if (words.contains(search)) {
						row = resultSet.getRow();
						pro.add(program);
						System.out.print("индекс: " + row + ": " + ((LinkedList<String>) pro).element());
						resultSet.absolute(row);
						((LinkedList<String>) tech).addLast(teacher);
						System.out.println("индекс: " + row + ": " + " учитель: " + ((LinkedList<String>) tech).pop());
					}
				}
				System.out.println("Запрошенные данные успешно выбраны!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private void searh() {
//
//	}

	/*
	главный метод порверки выполнения запросов
	 */
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		SQLQueryDate sql = new SQLQueryDate();
		sql.search("Java");
//		sql.prog();
//		sql.deletedDataSQLloc();
//		sql.insertExecuteBatchQuerySQL();
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
