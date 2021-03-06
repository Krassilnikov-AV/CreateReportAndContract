package createDocument;

import model.*;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

public class CreateScheduleReport implements CreateDocument {

	@Override
	public XWPFDocument createDoc(ShedulesSearch createShedules, String fio) throws SQLException, ParseException {

//		SQLQueryDataImpl sqlQueryDataImpl = new SQLQueryDataImpl();

		// создаем  документа docx, к которому будем прикручивать наполнение (колонтитулы, текст)
		XWPFDocument document = new XWPFDocument();

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
		CTP ctpHeaderFIO = createHeaderModel("__________" + fio);
		XWPFParagraph headerParagraphFIO = new XWPFParagraph(ctpHeaderFIO, document);
		headerParagraphFIO.setAlignment(ParagraphAlignment.RIGHT);
//
		headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT,
			new XWPFParagraph[]{headerParagraphFIO});

		// создаем верхний колонтитул Word файла
//		CTP ctpHeaderStatement = createHeaderModel("Ипатов");
//
//		ctpHeaderStatement.addNewR().addNewT().setStringValue("_________ ");
//		XWPFParagraph headerParagraphStatement = new XWPFParagraph(ctpHeaderStatement, document);
//		headerParagraphStatement.setAlignment(ParagraphAlignment.RIGHT);
//		headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT,
//			new XWPFParagraph[]{headerParagraphStatement});
//		setFooter(document, fio);

	/*------------------создание нижнего колонтитула-----------------------*/
		CTP ctpFooterModel = createFooterModel("Просто нижний колонтитул");
		// устанавливаем сформированый нижний
		// колонтитул в модель документа Word
		XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, document);
		headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, new XWPFParagraph[]{footerParagraph});


		XWPFParagraph bodyParagraph = document.createParagraph();
		bodyParagraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun paragraphConfig = bodyParagraph.createRun();
		paragraphConfig.setItalic(false); // выбрать шрифт (обычный/полужирный)
		paragraphConfig.setFontSize(11);  // размер шрифта
		// HEX цвет без решетки #
		paragraphConfig.setColor("000000");  // цвет текста
		paragraphConfig.setFontFamily("Times New Roman"); // задание текств
		paragraphConfig.setText("федеральное государственное автономное образовательное учреждение высшего " +
			"образования «Санкт-Петербургский политехнический университет Петра Великого» (ФГАОУ ВО«СПбПУ»)");
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

		paragraphProg.setText("по программе профессиональной переподготовки ___________________");

		String resMonth = createShedules.getShedules().get(0).getDateStart();

		DateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

		Date date1 = date.parse(resMonth);

		String dateStr = new SimpleDateFormat("MMMM yyyy").format(date1);

//			XWPFRun paragraphMonth = bodyParagraph.createRun();
		paragraphProg.addCarriageReturn();     // новый абзац
		paragraphProg.setText(fio);
		paragraphProg.setText(dateStr);
		paragraphProg.addCarriageReturn();     // новый абзац
		paragraphProg.setText("____________________________________________________________________________");

		int listTableSize = createShedules.getShedules().size();  // определяет количество строкразмер
		// вставляемых значений из

		XWPFTable table = document.createTable(listTableSize + 2, 6);
		getWidth(table, 13500);

		/*		 *метод для задания ширтны таблицы	 */

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
		for (SheduleSearch sheduleSearch : createShedules.getShedules()) {
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

		System.out.println("Файл успешно создан!");

		return document;
	}

	private CTP createFooterModel(String footerContent) {
		// создаем футер или нижний колонтитул
		CTP ctpFooterModel = CTP.Factory.newInstance();
		CTR ctrFooterModel = ctpFooterModel.addNewR();
		CTText cttFooter = ctrFooterModel.addNewT();

		cttFooter.setStringValue(footerContent);
		return ctpFooterModel;
	}

	public static void setFooter(XWPFDocument document, String footerText) {
		CTP ctpFooter = CTP.Factory.newInstance();
		ctpFooter.addNewR().addNewT();

		XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
		XWPFRun footerRun = footerParagraph.createRun();
		footerRun.setFontSize(12);
		footerRun.setText(footerText);
		XWPFParagraph[] parsFooter = new XWPFParagraph[1];
		parsFooter[0] = footerParagraph;

		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
		policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
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
