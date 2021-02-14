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
			pageMar.setRight(BigInteger.valueOf(500L));
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
			stadtdate.setIndentationLeft(4);
			XWPFRun stadtdateRun = stadtdate.createRun();
			stadtdateRun.setFontFamily("Times New Roman");
			stadtdateRun.setFontSize(12);
			stadtdateRun.setText("Санкт-Петербург");
			stadtdateRun.addTab();
			stadtdateRun.addTab();
			stadtdateRun.addTab();
			stadtdateRun.addTab();
			stadtdateRun.addTab();
			stadtdateRun.addTab();
			stadtdateRun.addTab();
			stadtdateRun.addTab();
			stadtdateRun.setText(strDate);
			document.write(outputStream);
			System.out.println("Файл успешно создан!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Файл к сожалению не создан");
		}


	}
}