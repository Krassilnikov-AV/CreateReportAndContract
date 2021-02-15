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

import com.sun.istack.internal.NotNull;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import query.SQLQueryData;

import java.io.*;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

/**
 * Класс WriteWordRaspisanie
 */
public class CreateScheduleReport implements CreateDocument {

	/**
	 * @param args the command line arguments
	 */
	@NotNull
	@Override
	public void createDoc() throws SQLException, ParseException {

		LinkedList<String> listCodeGroup;
		LinkedList<String> listProgs;

		LinkedList<String> listDateStart;
		LinkedList<String> listTimeStart;
		LinkedList<String> listDateEnd;
		LinkedList<String> listTimeEnd;
		LinkedList<String> listAuditorium;
		LinkedList<String> listTeach;

		String search = "Java"; // слово для поиска
		String dateMonth = "2020-06-01";
		SQLQueryData sqlQueryData = new SQLQueryData();
		listProgs = sqlQueryData.searchToProgram(search, dateMonth);
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
//			String nameProg = null;
//			List<String> result=listProgs.stream()
//				.filter(listProg -> listProg.contains(search)).collect(Collectors.toList());
//			List<String> result = listProgs;
//			for (String res :
//				 result) {
//				nameProg="Классная программа";
//			}
//			String res = null;
			listCodeGroup = sqlQueryData.searchToCodegroup(search, dateMonth);
			listDateStart = (LinkedList<String>) sqlQueryData.searchToDateStart(search, dateMonth);
			listTimeStart = (LinkedList<String>) sqlQueryData.searchToTimeStart(search, dateMonth);
			listAuditorium = sqlQueryData.searchToAuditorium(search, dateMonth);
			listTeach = sqlQueryData.searchToTeacher(search, dateMonth);

			paragraphProg.setText("по программе профессиональной переподготовки ___________________");

			/*
			 * Доработать_название месяца, окончание на -ь вместо -я!*/
			String resMonth = listDateStart.getFirst();
			DateFormat date = new SimpleDateFormat("yyyy.MM.dd", Locale.US);
			Date date1 = date.parse(resMonth);

			String dateStr = new SimpleDateFormat("MMMM yyyy").format(date1);

//			XWPFRun paragraphMonth = bodyParagraph.createRun();
			paragraphProg.addCarriageReturn();     // новый абзац
			paragraphProg.setText(dateStr);
			paragraphProg.addCarriageReturn();     // новый абзац
			paragraphProg.setText("____________________________________________________________________________");


			int listTableSize = listProgs.size();  // определяет количество строкразмер вставляемых значений из
			// списка и заголовка таблицы
			int listSelectData = listDateStart.size();  // кол-во строк вставляемых в таблицу с БД

			XWPFTable table = document.createTable(listTableSize+2, 6);
			table.setWidth(100);
//			table.setCellMargins(0, 0,0,1 );
//			table.setRowBandSize(10);
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
			for (int i = 0; i < listSelectData; i++) {
				table.getRow(i+2).getCell(0).setText(listCodeGroup.get(i));
				if (table.getRow(i+2).getCell(1).getText().isEmpty()) {
					table.getRow(i+2).getCell(1).setText(listProgs.get(i));
				}
				if (table.getRow(i+2).getCell(2).getText().isEmpty()) {
					table.getRow(i+2).getCell(2).setText(listDateStart.get(i));
				}
				if (table.getRow(i+2).getCell(3).getText().isEmpty()) {
					table.getRow(i+2).getCell(3).setText(listTimeStart.get(i));
				}
				if (table.getRow(i+2).getCell(4).getText().isEmpty()) {
					table.getRow(i+2).getCell(4).setText(listAuditorium.get(i));
				}
				if (table.getRow(i+2).getCell(5).getText().isEmpty()) {
					table.getRow(i+2).getCell(5).setText(listTeach.get(i));
				}
			}
//			table.addNewCol();
//			for (String str : listProgs) {
//				table.createRow().getCell(1).setText(str);
//			}
			// сохраняем шаблон docx документа в файл

			document.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Файл успешно создан!");
	}

	public static void main(String[] args) throws SQLException, ParseException {
		long start = System.currentTimeMillis();
		CreateScheduleReport csr = new CreateScheduleReport();
		csr.createDoc();
		long finish = System.currentTimeMillis();
		System.out.println("Время выполнения: " + (finish - start) + "_ms");
	}
//

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
}