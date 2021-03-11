/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package createDocument;
/**
 * XWPFTable
 * Это класс в пакете org.apache.poi.xwpf.usermodel и используется для добавления табличных данных в текстовый документ.
 * * Методы класса
 * Sr.No.	Метод и описание
 * addNewCol () - Добавляет новый столбец для каждой строки в этой таблице.
 * <p>
 * addRow (строка XWPFTableRow, int pos) - Добавляет новую строку в таблицу в позиции поз.
 * <p>
 * createRow () -  Создает новый объект XWPFTableRow с количеством ячеек,
 * равным количеству столбцов, определенных в данный момент.
 * <p>
 * setWidth (int width) -  Устанавливает ширину столбца.
 * <p>
 * addNewCol () - Добавляет новый столбец для каждой строки в этой таблице.
 * <p>
 * addRow (строка XWPFTableRow, int pos) - Добавляет новую строку в таблицу в позиции поз.
 * <p>
 * createRow () - Создает новый объект XWPFTableRow с количеством ячеек,
 * равным количеству столбцов, определенных в данный момент.
 * <p>
 * setWidth (int width) - Устанавливает ширину столбца.
 * <p>
 * Остальные методы этого класса см. В полном документе API по адресу: Документация по POI API.
 * (https://poi.apache.org/apidocs/index.html?org/apache/poi/openxml4j/opc/internal/package-summary.html.)
 */

import model.*;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import query.SQLQueryDataImpl;

import java.io.*;
import java.math.BigInteger;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

/**
 * Класс WriteWordRaspisanie
 */
public class CreateScheduleReport implements CreateDocument {

	private XWPFTable tab;

	/**
	 * @param args the command line arguments
	 */
String pathFile;
// 1. прописать путь в котором будет лежать файл
// 2. вписать строу поиска
// 3. вписать дату поиска
@Override
	public void createDoc(Connection connection) throws SQLException, ParseException {
		LinkedList<String> listDateStart;
		String search = "Java"; // слово для поиска
		String dateMonth = "2020-06-01";
		SQLQueryDataImpl sqlQueryDataImpl = new SQLQueryDataImpl();
		try (OutputStream outputStream
				 = new FileOutputStream("D:\\REPOSITORIES-2\\WordTest.docx")) {
			// создаем  документа docx, к которому будем прикручивать наполнение (колонтитулы, текст)
			XWPFDocument document = new XWPFDocument();
//			CTSectPr ctSectPr = document.getDocument().getBody().addNewSectPr();

			CTDocument1 doc = document.getDocument();
			CTBody body = doc.getBody();
			if (!body.isSetSectPr()) {
				body.addNewSectPr();
			}
			CTSectPr ctSectPr = body.getSectPr();

			if (!ctSectPr.isSetPgSz()) {
				ctSectPr.addNewPgSz();
			}
			CTPageSz pageSize = ctSectPr.getPgSz();
			pageSize.setW(BigInteger.valueOf(15840));     // размеры формата альбоиного
			pageSize.setH(BigInteger.valueOf(12240));     //
			pageSize.setOrient(STPageOrientation.LANDSCAPE);
// получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
			XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, ctSectPr);

			// устанавливаем сформированный верхний колонтитул в модель документа Word
			CTP ctpHeaderFIO = createHeaderModel("__________Ипатов");
			XWPFParagraph headerParagraphFIO = new XWPFParagraph(ctpHeaderFIO, document);
			headerParagraphFIO.setAlignment(ParagraphAlignment.RIGHT);
			headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT,
				new XWPFParagraph[]{headerParagraphFIO});

			// создаем верхний колонтитул Word файла
			CTP ctpHeaderStatement = createHeaderModel("УТВЕРЖДАЮ");

			ctpHeaderStatement.addNewR().addNewT().setStringValue("_________О.С.Ипатов");
			XWPFParagraph headerParagraphStatement = new XWPFParagraph(ctpHeaderStatement, document);
			headerParagraphStatement.setAlignment(ParagraphAlignment.RIGHT);
			headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT,
				new XWPFParagraph[]{headerParagraphStatement});

			// создаем нижний колонтитул docx файла
			CTP ctpFooterModel = createFooterModel("Просто нижний колонтитул");
			// устанавливаем сформированый нижний
			// колонтитул в модель документа Word
			XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, document);
			headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, new XWPFParagraph[]{footerParagraph});

			// создаем обычный параграф, который будет расположен слева,
			// будет синим курсивом со шрифтом 11 размера
			XWPFParagraph bodyParagraph = document.createParagraph();
			bodyParagraph.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun paragraphConfig = bodyParagraph.createRun();
			paragraphConfig.setItalic(false); // выбрать шрифт (обычный/полужирный)
			paragraphConfig.setFontSize(11);  // размер шрифта
			// HEX цвет без решетки #
			paragraphConfig.setColor("000000");  // цвет текста
			paragraphConfig.setFontFamily("Times New Roman"); // задание текств
			paragraphConfig.setText("федеральное государственное автономное образовательное учреждение высшего " +
				"образования <<Санкт-Петербургский политехнический университет Петра Великого (ФГАОУ ВО<<СПбПУ>>)");
			paragraphConfig.addCarriageReturn();     // новый абзац
			paragraphConfig.setText("Институт дополнительного образования");
			paragraphConfig.addCarriageReturn();     // новый абзац
			paragraphConfig.setText("Высшая инженерная школа");
			paragraphConfig.addCarriageReturn();     // новый абзац
			paragraphConfig.addCarriageReturn();     // новый абзац
			XWPFRun paragraph = bodyParagraph.createRun();
			paragraph.setColor("000000");
			paragraph.setBold(true);
			paragraph.setText("РАСПИСАИЕ ЗАНЯТИЙ");
			paragraph.addCarriageReturn();     // новый абзац
			XWPFRun paragraphProg = bodyParagraph.createRun();
			paragraphProg.setColor("000000");

			listDateStart = (LinkedList<String>) sqlQueryDataImpl.searchToDateStart(connection, search, dateMonth);
//			listTimeStart = (LinkedList<String>) sqlQueryDataImpl.searchToTimeStart(search, dateMonth);
//			listAuditorium = sqlQueryDataImpl.searchToAuditorium(search, dateMonth);
//			listTeach = sqlQueryDataImpl.searchToTeacher(search, dateMonth);

			ShedulesSearch shedulesSearch = sqlQueryDataImpl.addValueTableShedule(connection, search, dateMonth);
			paragraphProg.setText("по программе профессиональной переподготовки ___________________");

			/*
			 * Доработать_название месяца, окончание на -ь вместо -я!
			 * необходимо получить первый элемент пропарсить и получить
			 * название месяца для заголовка расписания
			 * */
			String resMonth = listDateStart.getFirst();
//				String resultMonth = String.valueOf(shedulesSearch.getShedules().get(0));

			DateFormat date = new SimpleDateFormat("yyyy.MM.dd", Locale.US);
			Date date1 = date.parse(resMonth);

			String dateStr = new SimpleDateFormat("MMMM yyyy").format(date1);

//			XWPFRun paragraphMonth = bodyParagraph.createRun();
			paragraphProg.addCarriageReturn();     // новый абзац
			paragraphProg.setText(dateStr);
			paragraphProg.addCarriageReturn();     // новый абзац
			paragraphProg.setText("____________________________________________________________________________");

			int listTableSize = shedulesSearch.getShedules().size();  // определяет количество строкразмер
			// вставляемых значений из
			// списка и заголовка таблицы
//			int listSelectData = listDateStart.size();  // кол-во строк вставляемых в таблицу с БД

			XWPFTable table = document.createTable(listTableSize + 2, 6);
			getWidth(table, 13500);         // установка ширины таблицы
//			table.setWidth(100);

			/*
			 *метод для задания ширтны таблицы
			 */

			table.getRow(0).getCell(0).setText("Группа");
			table.getRow(0).getCell(1).setText("Образовательная программа");
			table.getRow(0).getCell(2).setText("Дата начала");
			table.getRow(0).getCell(3).setText("Время начала");
			table.getRow(0).getCell(4).setText("Аудитория");
			table.getRow(0).getCell(5).setText("Преподаватель");
			table.getRow(1).getCell(0).setText("1");
			table.getRow(1).getCell(1).setText("2");
			table.getRow(1).getCell(2).setText("3");
			table.getRow(1).getCell(3).setText("4");
			table.getRow(1).getCell(4).setText("5");
			table.getRow(1).getCell(5).setText("6");

			int countRows = 2;
			for (SheduleSearch sheduleSearch : shedulesSearch.getShedules()) {
				table.getRow(countRows).getCell(0).setText(sheduleSearch.getGroup());
				if (table.getRow(countRows).getCell(1).getText().isEmpty()) {
					table.getRow(countRows).getCell(1).setText(sheduleSearch.getPro());
				}
				if (table.getRow(countRows).getCell(2).getText().isEmpty()) {
					table.getRow(countRows).getCell(2).setText(sheduleSearch.getDateStart());
				}
				if (table.getRow(countRows).getCell(3).getText().isEmpty()) {
					table.getRow(countRows).getCell(3).setText(sheduleSearch.getTimeStart());
				}
				if (table.getRow(countRows).getCell(4).getText().isEmpty()) {
					table.getRow(countRows).getCell(4).setText(sheduleSearch.getAudit());
				}
				if (table.getRow(countRows).getCell(5).getText().isEmpty()) {
					table.getRow(countRows).getCell(5).setText(sheduleSearch.getTech());
				}

				countRows = countRows + 1;
			}

			// сохраняем шаблон docx документа в файл

			document.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Файл успешно создан!");
	}

	private CTP createFooterModel(String footerContent) {
		// создаем футер или нижний колонтитул
		CTP ctpFooterModel = CTP.Factory.newInstance();
		CTR ctrFooterModel = ctpFooterModel.addNewR();
		CTText cttFooter = ctrFooterModel.addNewT();

		cttFooter.setStringValue(footerContent);
		return ctpFooterModel;
	}

	//CTPageSz
	private static CTP createHeaderModel(String headerStatment) {
		// создаем хедер или верхний колонтитул
		CTP ctpHeaderModel = CTP.Factory.newInstance();      // определение параметров вставки текста в колонтитуле

		CTR ctrStatment = ctpHeaderModel.addNewR();      // создание строки
		CTText cttHeader = ctrStatment.addNewT();         // создание текстового поля
		cttHeader.setStringValue(headerStatment);            // получение строчных параметров
//		CTR ctrFIO = ctpHeaderModel.addNewR();
//		CTText cttFIO = ctrFIO.addNewT();
//		cttFIO.setStringValue(headFIO);
		return ctpHeaderModel;
	}

	/*
	 * метод получения ширины таблицы
	 * */
	private CTTblWidth getWidth(XWPFTable tab, int value) {
		CTTblWidth width = tab.getCTTbl().addNewTblPr().addNewTblW();
		width.setType(STTblWidth.DXA);
		width.setW(BigInteger.valueOf(value));
		return width;
	}
}
