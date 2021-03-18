package readDoc;

import model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

/**
 * Класс ExReadExcelData
 */
public class ReadExcelDataImpl implements ReadData {

	final static int code = 0;    // код (строка)
	final static int divID = 1;   // ID подразделения (число)
	final static int gpoupID = 2;   // ID группы  (число)
	final private int codeGroup = 3;   //+1 код группы  (число)
	final static int group = 4;   // название группы (строка)
	final private int dateStart = 5;   // +3 дата начала (дата)
	final private int timeStart = 6;   // +4 время начала (время)
	final private int dateEnd = 7;   // +5 дата завершения (дата)
	final private int timeEnd = 8;   // +6 время завершения (время)
	final static int classID = 9;   // ID аудитории (число)
	final private int clasRum = 10;   // +7 №аудитории или вариант (ОнЛайн) (число/строка)
	final private int typeLearn = 11;   // +8 тип занятия (строка)
	private int codeDirectionProgramm = 12;   // код-направление-программа (число-строка)
	final static int courseID = 13;   // +2.1 ID курса (число) -
	final private int discipline = 14;   // +2 предмет/дисциплина/программа (число/строка)
	final static int period = 15;   // период (число)
	final static int teacherID = 16;   // ID преподавателя (число)
	final private int teacher = 17;   // +9 преподаватель (строка)
	final static int periodDay = 18;   // период дней(число)
	final static int academHour = 19;   // академических часов (число)
	final static int academRecord = 20;   // академических записей (число)


	private final InputStream ios;


	private java.sql.Date columndataDateSql;
	private List<java.sql.Date> columnListDateSql = new LinkedList<>();
	private List<String> columndataStr;
	private List<Integer> columndataInt;

	/**
	 * метод для построения таблицы из прчитанных данных и просмотра данных
	 * Вопросы:
	 * 1. формат даты считывается как ссылка
	 */

	/**
	 * метод должен получить определённые номера колонок, вызвать метод, который обработает тип ячейки
	 * и вернуть считанные данные
	 */

	public ReadExcelDataImpl(InputStream ios) {
		this.ios = ios;
	}

	@Override
	public LinkedList<java.sql.Date> getDate(int columnIndex) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			columnListDateSql = new LinkedList<>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
						if (cell.getColumnIndex() == columnIndex) {// соответствие индекса столбца
							Date date = cell.getDateCellValue();
							columndataDateSql = new java.sql.Date(date.getTime());
							columnListDateSql.add(columndataDateSql);
						}
					}
				}
			}

		}
//			ios.close();
		/*			просмотр прочитанного			 */
//			Iterator it = columndataDate.iterator();
//			while (it.hasNext()) {
//				System.out.println(it.next());
//			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (LinkedList<java.sql.Date>) columnListDateSql;
	}

	@Override
	public List<String> getString(int columnIndex) throws IOException {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			columndataStr = new LinkedList<>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
						if (cell.getColumnIndex() == columnIndex) {// соответствие индекса столбца
							columndataStr.add(cell.getStringCellValue());
						}
					}
				}
			}

			/*			просмотр прочитанного			 */
			for (String s : columndataStr) {
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columndataStr;
	}

	@Override
	public List<Integer> getDataInteger(int columnIndex) {

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			columndataInt = new LinkedList<>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
						if (cell.getColumnIndex() == columnIndex) {// соответствие индекса столбца

							columndataInt.add((int) cell.getNumericCellValue());
							break;
						}
					}
				}
			}
			Iterator it = columndataInt.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columndataInt;
	}

	@Override
	public List<SheduleInsert> getShedulesSearch() {
		List<SheduleInsert> shedulesInsert = new ArrayList<>();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			columndataStr = new LinkedList<>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				String codeStr = null;
				String disciplineStr = null;
				java.sql.Date dateStartDate = null;
				java.sql.Date timeStartDate = null;
				java.sql.Date dateFinish = null;
				java.sql.Date timeFinish = null;
				String audit = null;
				String type = null;
				String tech = null;
				int periodInt = 0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
						switch (cell.getColumnIndex()) {
							case codeGroup:
								codeStr = cell.getStringCellValue();
								break;
							case discipline:
								disciplineStr = cell.getStringCellValue();
								break;
							case dateStart:
								Date date = cell.getDateCellValue();
								dateStartDate = new java.sql.Date(date.getTime());
								break;
							case timeStart:
								Date timeStart = cell.getDateCellValue();
								timeStartDate = new java.sql.Date(timeStart.getTime());
								break;
							case dateEnd:
								Date dateFinishD = cell.getDateCellValue();
								dateFinish = new java.sql.Date(dateFinishD.getTime());
								break;
							case timeEnd:
								if (cell.getDateCellValue() != null) {
									Date timeEndDate = cell.getDateCellValue();
									timeFinish = new java.sql.Date(timeEndDate.getTime());
								}
								break;
							case clasRum:
								audit = cell.getStringCellValue();
								break;
							case typeLearn:
								type = cell.getStringCellValue();
								break;
							case teacher:
								tech = cell.getStringCellValue();
								break;
							case period:
								periodInt = (int) cell.getNumericCellValue();
								break;

						}

					}
				}
				if (codeStr != null &&
					disciplineStr != null &&
					dateStartDate != null &&
					timeStartDate != null &&
					dateFinish != null &&
					timeFinish != null &&
					audit != null &&
					type != null &&
					tech != null) {
					SheduleInsert sheduleInsert = new SheduleInsert(
						codeStr,
						disciplineStr,
						dateStartDate,
						timeStartDate,
						dateFinish,
						timeFinish,
						audit,
						type,
						tech, periodInt
					);
					shedulesInsert.add(sheduleInsert);
				}
			}
			for (SheduleInsert sheduleInsert : shedulesInsert) {
				System.out.println(sheduleInsert.toString());
			}


			/*			просмотр прочитанного			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return shedulesInsert;

	}
}