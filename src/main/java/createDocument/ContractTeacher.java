/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package createDocument;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Класс ContractTeacher
 */
public class ContractTeacher implements CreateDocument {

	public static void main(String[] args) throws SQLException, ParseException {
		long start = System.currentTimeMillis();
		ContractTeacher contr = new ContractTeacher();
		contr.createDoc();
		long finish = System.currentTimeMillis();
		System.out.println("Время выполнения: " + (finish - start) + "_ms");
	}

	@Override
	public void createDoc() throws SQLException, ParseException {

		try (OutputStream outputStream
				 = new FileOutputStream("D:\\REPOSITORIES-2\\ContractTeach.docx")) {
			XWPFDocument document = new XWPFDocument();
// настройка полей документа
			CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
			CTPageMar pageMar = sectPr.addNewPgMar();
			pageMar.setLeft(BigInteger.valueOf(1300L));
			pageMar.setRight(BigInteger.valueOf(1000L));
			pageMar.setTop(BigInteger.valueOf(1000L));
			pageMar.setBottom(BigInteger.valueOf(950L));

			XWPFRun title = methodRunTitle(document);
			title.setText("Договор возмездного оказания преподавательских услуг № ");

			/*
			в ниже представленном параграфе необходимо вставить дату договора, которая будет вставляться в браузере
			при помощи текстового поля
			для понимания процесса работы вставка пока будет продемонстрирована локально в методом  main
			 */
			String strDate = "25.12.2020";

			XWPFParagraph stadtdate = document.createParagraph();

			stadtdate.setAlignment(ParagraphAlignment.CENTER);     // выравнить по левому краю
//			stadtdate.setIndentationLeft(4);
			XWPFRun stadtdateRun = stadtdate.createRun();
			stadtdateRun.setFontFamily("Times New Roman");
			stadtdateRun.setFontSize(12);
			stadtdateRun.setText("Санкт-Петербург");
			stadtdateRun.addTab();
			stadtdateRun.addTab();
			stadtdateRun.setText(strDate);

			/*получение первого обзаца
			 * вставляемые данные с браузерного поля:
			 * 1. FIO - должность и Ф.И.О. заказчика
			 * 2. numContract - доверенность по которой осуществляется подпись заказчика*/
			XWPFRun runFirst = getIndentationRun(document);
			String FIO = "Хорошая фамилия";
			String numContract = "юр-323/20-д от 29.12.2020";
			runFirst.setText("Федеральное государственное автономное образовательное учреждение" +
				" высшего образования «Санкт-Петербургский политехнический университет Петра Великого»" +
				" (ФГАОУ ВО «СПбПУ), именуемое в дальнейшем «Заказчик», в лице " + FIO + ", действующей " +
				"на основании Доверенности №" + numContract + "с одной стороны и гражданина Российской Федерации:");

			XWPFTable tableFIO = document.createTable(2, 1);
// вставка в таблицу ФИО
// доработать таблицу, по аналогии следующие
			String fio = "Отличная фамилия";
			tableFIO.setWidth(100);
			XWPFTableRow tableRowOne = tableFIO.getRow(0);
			tableRowOne.getCell(0).setText(fio);
			XWPFTableRow xwpfRowTwo = tableFIO.getRow(1);

			xwpfRowTwo.getCell(0).setText("(Фамилия, Имя, Отчество)");

			XWPFParagraph firstPar2 = document.createParagraph();
			firstPar2.setAlignment(ParagraphAlignment.BOTH);     // выравнить по ширине
			firstPar2.setIndentationFirstLine(20);
			XWPFRun firstRun2 = firstPar2.createRun();
			firstRun2.setFontFamily("Times New Roman");
			firstRun2.setFontSize(12);
			firstRun2.setText(" именуемый в дальнейшем «Исполнитель», с другой стороны, " +
				"далее совместно именуемые «Стороны» для целей образовательного процесса заключили" +
				" настоящий Договор (далее - Договор) о нижеследующем:");
//			firstRun2.addBreak();

			XWPFRun subjectAgreement = methodRunTitle(document);
			subjectAgreement.setText("1. Предмет Договора");

// поля program и nameProgram вставляются пр помощи текстового поля с браузера
			/*
			 * 1. указат межстрочный интервал
			 * 2. наименование программы прописным, полужирным
			 *
			 */
			String program = "дополнительной общеобразовательной общеразвивающей программе:";
			XWPFRun subject1 = getItems(document);
			subject1.setText("1.1. Исполнитель обязуется по заданию Заказчика оказать" +
				" образовательные услуги по " + program);

/*
сделать ширину до размера 16,5
1. верхний текст: Times New Roman, 12, прописной, полужирный
2. нижний текст: Times New Roman, 9, прописной
3. посмотреть межстрочный интервал
*/
			XWPFTable tableProgram = document.createTable(2, 1);
			String nameProgram = "«наименование»";
			tableProgram.setWidth(1000);
			XWPFTableRow tablePrRow1 = tableProgram.getRow(0);
			tablePrRow1.getCell(0).setText(nameProgram);
			XWPFTableRow tablePrRow2 = tableProgram.getRow(1);
			tablePrRow2.getCell(0).setText("(указать учебную тему/темы)");

			/*
			 * продолжение предмета договора п.2
			 * 1.
			 */
			XWPFParagraph subject2 = document.createParagraph();
			String dataSt = null;  // дата начала договора
			String dataEnd = null; // дата окончания договора
//			String
			subject2.setAlignment(ParagraphAlignment.LEFT);     // выравнить по левому краю
			XWPFRun subject2Run = subject2.createRun();
			subject2Run.setFontFamily("Times New Roman");
			subject2Run.setFontSize(12);
			subject2Run.addBreak();
			subject2Run.setText("1.2. Исполнитель оказывает услуги с 00.00.2021" +
				dataSt + "по" + dataEnd + ". " +
				"Общий объем оказываемых услуг составляет 00 академических часов. Оплата услуг " +
				"Исполнителю производится в размере 00 (                 ) рублей в час. ");
			subject2Run.addBreak();

			XWPFRun TermPlace = methodRunTitle(document);
			TermPlace.setText("2. Срок и место оказания услуг");

			XWPFRun TermPlace1 = getItems(document);
			String dSt = "01.09.2021";
			String dEnd = "30.09.2021";
			TermPlace1.setText("2.1. Период оказания услуг: с " + dSt + " по " + dEnd);

			XWPFRun TermPlace2 = getItems(document);
			String adresPlace = "ул. Обручевых, дом 1";
			TermPlace2.setText("2.2. Место оказания услуг: г. Санкт- Петербург, " + adresPlace);

			/*цена договора*/
			XWPFRun ContractPrice = methodRunTitle(document);
			ContractPrice.setText("3. Цена договора и порядок расчётов");

			XWPFRun ContractPrice1 = getItems(document);
			String priceNum = "10000";
			String price = "Десять тысяч ";
			ContractPrice1.setText("3.1. Общая цена Договора составляет " + priceNum + "(" + price + ")" +
				"рублей 00 копеек, в том числе НДФЛ.");

			XWPFRun rightObligation = methodRunTitle(document);
			rightObligation.setText("4. Права и обязанности сторон");

			XWPFRun responsibility = methodRunTitle(document);
			responsibility.setText("5. Ответственность сторон");

			XWPFRun forceMajeure = methodRunTitle(document);
			forceMajeure.setText("6. Форс-мажор");

			XWPFRun basisValidityPeriod = methodRunTitle(document);
			basisValidityPeriod.setText("7. Срок действия, основания и порядок изменения и расторжения Договора");

			XWPFRun disputeResolution = methodRunTitle(document);
			disputeResolution.setText("8. Разрешение споров");

			XWPFRun finalProvisions = methodRunTitle(document);
			finalProvisions.setText("9. Заключительные положения");

			XWPFRun addressBankdetails = methodRunTitle(document);
			addressBankdetails.setText("10. Адреса и реквизиты сторон");


			/*создание документа*/
			document.write(outputStream);
			System.out.println("Файл успешно создан!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Файл к сожалению не создан");
		}
	}

	/*
	 * метод для получения оглавлений разделов договора
	 * */
	private XWPFRun methodRunTitle(XWPFDocument doc) {
		XWPFParagraph title = doc.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun paraTitRun = title.createRun();
		paraTitRun.setBold(true);
		paraTitRun.setFontFamily("Times New Roman");
		paraTitRun.setFontSize(12);
		return paraTitRun;
	}
	/*
	 * метод для описания обзаца с красной строки
	 */

	private XWPFRun getIndentationRun(XWPFDocument doc) {
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);     // выравнить по ширине
		paragraph.setIndentationLeft(20);
		paragraph.setIndentationHanging(-1050);         // отступ с левого края
		XWPFRun paragraphRun = paragraph.createRun();       //
		paragraphRun.setFontFamily("Times New Roman");     // шрифт название
		paragraphRun.setFontSize(12);                        // номер шрифта
		return paragraphRun;
	}

	private XWPFRun getItems(XWPFDocument doc) {
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);
		paragraph.setIndentationHanging(-571);
		XWPFRun paragraphRun = paragraph.createRun();
		paragraphRun.setFontFamily("Times New Roman");
		paragraphRun.setFontSize(12);
		return paragraphRun;
	}

}