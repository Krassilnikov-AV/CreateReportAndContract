package readDoc;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import services.FilePathTransferOperation;

import java.io.*;
import java.util.*;

/**
 * Класс ExReadExcelData
 */
public class ReadExcelDataImpl implements ReadData {


//
//	String fileToRead = "fileToRead";
//	private LinkedList<String> columnStrData;

//	static int columnIndex = 15;
//	static int columnInt = 1;
private FilePathTransferOperation filePath=new FilePathTransferOperation();

	// основной метод класса для проверки считывания данных с таблицы
//	public static void main(String[] args) throws IOException {
//		ReadExcelDataImpl exr = new ReadExcelDataImpl();
////		exr.getString(0);
//		exr.getDataInteger(columnIndex);
////		exr.buildingTable();
////		exr.getDate(columnIndex);
////		exr.getTime(columnIndex);
//	}

	private java.sql.Date columndataDateSql;
	private List<java.sql.Date> columnListDateSql=new LinkedList<>();
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
	private String fileToRead=filePath.getFileToRead();
	@Override
	public LinkedList<java.sql.Date> getDate(int columnIndex) {
		try {
			File f = new File(fileToRead);
			try (FileInputStream ios = new FileInputStream(f)) {
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
	public List<String> getString(int columnIndex) {
		try {
			File f = new File(fileToRead);
			try (FileInputStream ios = new FileInputStream(f)) {
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
			File f = new File(fileToRead);
			try (FileInputStream ios = new FileInputStream(f)) {
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
}