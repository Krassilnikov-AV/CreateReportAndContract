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

			XWPFParagraph title = document.createParagraph();
			title.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun paraTitRun = title.createRun();
			paraTitRun.setBold(true);
			paraTitRun.setFontFamily("Times New Roman");
			paraTitRun.setFontSize(12);
			paraTitRun.setText("Договор возмездного оказания преподавательских услуг № ");
			/*
			в ниже представленном параграфе необходимо вставить дату договора, которая будет вставляться в браузере
			при помощи текстового поля
			для понимания процесса работы вставка пока будет продемонстрирована локально в методом  main
			 */
			String strDate = "25.12.2020";

			XWPFParagraph stadtdate = document.createParagraph();
			stadtdate.setAlignment(ParagraphAlignment.LEFT);     // выравнить по левому краю
//			stadtdate.setIndentationLeft(4);
			XWPFRun stadtdateRun = stadtdate.createRun();
			stadtdateRun.setFontFamily("Times New Roman");
			stadtdateRun.setFontSize(12);
			stadtdateRun.setText("Санкт-Петербург");
			stadtdateRun.addTab();
			stadtdateRun.addTab();

			stadtdateRun.setText(strDate);

			XWPFParagraph firstParagraph = document.createParagraph();
			String FIO = "Хорошая фамилия";
			String numContract = "юр-323/20-д от 29.12.2020";
			firstParagraph.setAlignment(ParagraphAlignment.LEFT);     // выравнить по левому краю
			firstParagraph.setIndentationFirstLine(20);
			XWPFRun firstRun = firstParagraph.createRun();
			firstRun.setFontFamily("Times New Roman");
			firstRun.setFontSize(12);
//			firstRun.setStrike();
			// добавить абзац, отступ
			firstParagraph.setIndentationLeft(20);
			firstRun.setText("Федеральное государственное автономное образовательное учреждение" +
				" высшего образования «Санкт-Петербургский политехнический университет Петра Великого»" +
				" (ФГАОУ ВО «СПбПУ), именуемое в дальнейшем «Заказчик», в лице " + FIO + ", действующей " +
				"на основании Доверенности №" + numContract + "с одной стороны и гражданина Российской Федерации:");

			XWPFTable tableFIO = document.createTable(2, 1);
// вставка в таблицу ФИО
			String fio = "Отличная фамилия";
			tableFIO.setWidth(100);
//			tableFIO.setRowBandSize(2);
			XWPFTableRow tableRowOne = tableFIO.getRow(0);
			tableRowOne.getCell(0).setText(fio);
			XWPFTableRow xwpfRowTwo = tableFIO.getRow(1);

			xwpfRowTwo.getCell(0).setText("(Фамилия, Имя, Отчество)");
//			firstRun.setTextPosition(1);
			XWPFParagraph firstPar2 = document.createParagraph();
			firstPar2.setAlignment(ParagraphAlignment.LEFT);     // выравнить по левому краю
			firstPar2.setIndentationFirstLine(20);
			XWPFRun firstRun2 = firstParagraph.createRun();
			firstRun2.setFontFamily("Times New Roman");
			firstRun2.setFontSize(12);
//			firstRun.setStrike();
			// добавить абзац, отступ
//			firstParagraph.setIndentationLeft(20);

			firstRun2.setText(" именуемый в дальнейшем «Исполнитель», с другой стороны, " +
				"далее совместно именуемые «Стороны» для целей образовательного процесса заключили" +
				" настоящий Договор (далее - Договор) о нижеследующем:");
void XWPFParagraph parNew() {
				XWPFParagraph firstParagraph = document.createParagraph();
				String FIO = "Хорошая фамилия";
				String numContract = "юр-323/20-д от 29.12.2020";
				firstParagraph.setAlignment(ParagraphAlignment.LEFT);     // выравнить по левому краю
				firstParagraph.setIndentationFirstLine(20);
				XWPFRun firstRun = firstParagraph.createRun();
				firstRun.setFontFamily("Times New Roman");
				firstRun.setFontSize(12);
				return firstParagraph;
			}
			document.write(outputStream);
			System.out.println("Файл успешно создан!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Файл к сожалению не создан");
		}
	}
}