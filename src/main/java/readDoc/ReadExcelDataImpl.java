package readDoc;

import model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public class ReadExcelDataImpl implements ReadData {

	final static int code = 0;    // код (строка)
	final static int divID = 1;   // ID подразделения (число)
	final static int gpoupID = 2;   // ID группы  (число)
	final private int CODEGROUP = 3;   //+1 код группы  (число)
	final static int group = 4;   // название группы (строка)
	final private int DATeSTART = 5;   // +3 дата начала (дата)
	final private int TIMeSTART = 6;   // +4 время начала (время)
	final private int DATeEnd = 7;   // +5 дата завершения (дата)
	final private int TIMeEND = 8;   // +6 время завершения (время)
	final static int classID = 9;   // ID аудитории (число)
	final private int CLAsRUM = 10;   // +7 №аудитории или вариант (ОнЛайн) (число/строка)
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
	private List<String> columndataStr;

	public ReadExcelDataImpl(InputStream ios) {
		this.ios = ios;
	}

//	@Override
//	public List<SheduleTable> getSheduleTable() {
//		List<SheduleTable> shedulesTable = new ArrayList<>();
//		try {
//			XSSFWorkbook workbook = new XSSFWorkbook(ios);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//			Iterator<Row> rowIterator = sheet.iterator();
//			columndataStr = new LinkedList<>();
//
//			while (rowIterator.hasNext()) {
//				Row row = rowIterator.next();
//				Iterator<Cell> cellIterator = row.cellIterator();
//				String codeStr = null;
//				String groupStr = null;
//				String disciplineStr = null;
//				java.sql.Date dateStartDate = null;
//				java.sql.Date timeStartDate = null;
//				java.sql.Date dateFinish = null;
//				java.sql.Date timeFinish = null;
//				String audit = null;
//				String type = null;
//				String tech = null;
//				int periodInt = 0;
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					if (row.getRowNum() > 0) { //фильтрация заголовков столбцов
//						switch (cell.getColumnIndex()) {
//							case CODEGROUP:
//								codeStr = cell.getStringCellValue();
//								break;
//							case group:
//								groupStr = cell.getStringCellValue();
//							case discipline:
//								disciplineStr = cell.getStringCellValue();
//								break;
//							case DATeSTART:
//								Date date = cell.getDateCellValue();
//								dateStartDate = new java.sql.Date(date.getTime());
//								break;
//							case TIMeSTART:
//								Date TIMeSTART = cell.getDateCellValue();
//								timeStartDate = new java.sql.Date(TIMeSTART.getTime());
//								break;
//							case DATeEnd:
//								Date dateFinishD = cell.getDateCellValue();
//								dateFinish = new java.sql.Date(dateFinishD.getTime());
//								break;
//							case TIMeEND:
//								if (cell.getDateCellValue() != null) {
//									Date timeEndDate = cell.getDateCellValue();
//									timeFinish = new java.sql.Date(timeEndDate.getTime());
//								}
//								break;
//							case CLAsRUM:
//								audit = cell.getStringCellValue();
//								break;
//							case typeLearn:
//								type = cell.getStringCellValue();
//								break;
//							case teacher:
//								tech = cell.getStringCellValue();
//								break;
//							case period:
//								periodInt = (int) cell.getNumericCellValue();
//								break;
//						}
//					}
//				}
//				if (codeStr != null && groupStr!=null &&
//					disciplineStr != null &&
//					dateStartDate != null &&
//					timeStartDate != null &&
//					dateFinish != null &&
//					timeFinish != null &&
//					audit != null &&
//					type != null &&
//					tech != null) {
//					SheduleTable sheduleTable = new SheduleTable(
//						codeStr, groupStr,
//						disciplineStr,
//						dateStartDate,
//						timeStartDate,
//						dateFinish,
//						timeFinish,
//						audit,
//						type,
//						tech, periodInt
//					);
//					shedulesTable.add(sheduleTable);
//				}
//			}
//			for (SheduleTable sheduleTable : shedulesTable) {
//				System.out.println(sheduleTable.toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return shedulesTable;
//	}



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
							case CODEGROUP:
								codeStr = cell.getStringCellValue();
								break;
							case discipline:
								disciplineStr = cell.getStringCellValue();
								break;
							case DATeSTART:
								Date date = cell.getDateCellValue();
								dateStartDate = new java.sql.Date(date.getTime());
								break;
							case TIMeSTART:
								Date timeStart = cell.getDateCellValue();
								timeStartDate = new java.sql.Date(timeStart.getTime());
								break;
							case DATeEnd:
								Date dateFinishD = cell.getDateCellValue();
								dateFinish = new java.sql.Date(dateFinishD.getTime());
								break;
							case TIMeEND:
								if (cell.getDateCellValue() != null) {
									Date timeEndDate = cell.getDateCellValue();
									timeFinish = new java.sql.Date(timeEndDate.getTime());
								}
								break;
							case CLAsRUM:
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shedulesInsert;
	}
}