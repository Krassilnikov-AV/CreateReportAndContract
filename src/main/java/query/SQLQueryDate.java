package query;


import connection.ConnectionApp;
import readDoc.ReadExcelData;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.*;

/**
 * Класс SQLQueryDate
 */
public class SQLQueryDate {

	// выбрать столбец для чтения данных (для проверки/тестировниая)
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
	String insertTimeStartSQL = "INSERT INTO schedule(timestart) VALUES(?)";
	String insertDateStartSQL = "INSERT INTO schedule(datestart) VALUES(?)";


	//	String insertSQL = "INSERT INTO schedule(program) VALUES(?)";

//	String insertSQL = "insert into group(groupid) values (?)";
//	String selectSQL = "SELECT datestart, timestart FROM schedule";
//	String selectSQL = "SELECT programm FROM raspisanie";

	private ConnectionApp con = new ConnectionApp();
	private ReadExcelData read = new ReadExcelData();

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
				LinkedList<Date> listDateStart = (LinkedList<Date>) read.getDate(dateStart);
				LinkedList<Date> listTimeStart = (LinkedList<Date>) read.getDate(timeStart);
				LinkedList<Date> listDateFinish = (LinkedList<Date>) read.getDate(dateEnd);
				LinkedList<Date> listTimeFinish = (LinkedList<Date>) read.getDate(timeEnd);
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

					Date df = listDateFinish.pop();
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
					long startInternal = System.currentTimeMillis();
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

	/*
	метод для удаления внесённых данных в таблицу
	*/
	public int deletedDataSQL() throws SQLException {
		try (Connection connection = con.getPostConnection()) {
			String deletedSQL = "DELETE FROM schedule";
			try (PreparedStatement stm = connection.prepareStatement(deletedSQL)) {
				return stm.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Данные БД успешно удалены!");
			System.out.println("-/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/--/*/-");
			System.out.println("Закрыли соединение с БД после удаления данных...");
		}
		return 0;
	}
}
