/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package createDocument;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import query.SQLQueryDate;

import java.io.*;
import java.util.*;

/**
 * Класс WriteWordRaspisanie
 */
public class CreateScheduleReport implements CreateDocument {

	/**
	 * @param args the command line arguments
	 */

	@Override
	public void createDoc() {
		try (OutputStream outputStream
				 = new FileOutputStream("D:\\REPOSITORIES-2\\WordTest.docx")) {
			// создаем модель docx документа,
			// к которой будем прикручивать наполнение (колонтитулы, текст)
			XWPFDocument docxModel = new XWPFDocument();
			CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();
			// получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
			XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);

			// создаем верхний колонтитул Word файла
			CTP ctpHeaderModel = createHeaderModel("УТВЕРЖДАЮ");
			// устанавливаем сформированный верхний
			// колонтитул в модель документа Word
			XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
			headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT,
				new XWPFParagraph[]{headerParagraph});

			// создаем нижний колонтитул docx файла
			CTP ctpFooterModel = createFooterModel("Просто нижний колонтитул");
			// устанавливаем сформированый нижний
			// колонтитул в модель документа Word
			XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, docxModel);
			headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, new XWPFParagraph[]{footerParagraph});

			// создаем обычный параграф, который будет расположен слева,
			// будет синим курсивом со шрифтом 14 размера
			XWPFParagraph bodyParagraph = docxModel.createParagraph();
			bodyParagraph.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun paragraphConfig = bodyParagraph.createRun();
			paragraphConfig.setItalic(true);
			paragraphConfig.setFontSize(14);
			// HEX цвет без решетки #
			paragraphConfig.setColor("06357a");
			paragraphConfig.setText("дОБРО ПОЖАЛОВАТЬ!");

			List<String> progArr;

			SQLQueryDate sqlQueryDate = new SQLQueryDate();
			progArr = sqlQueryDate.search("Java");
//			progArr.addAll(progArr);

			int sizeList = progArr.size();
/**
 * XWPFTable
 * Это класс в пакете org.apache.poi.xwpf.usermodel и используется для добавления табличных данных в текстовый документ.
 * * Методы класса
 * Sr.No.	Метод и описание
 * addNewCol () - Добавляет новый столбец для каждой строки в этой таблице.
 *
 * addRow (строка XWPFTableRow, int pos) - Добавляет новую строку в таблицу в позиции поз.
 *
 * createRow () -  Создает новый объект XWPFTableRow с количеством ячеек,
 *                равным количеству столбцов, определенных в данный момент.
 *
 * setWidth (int width) -  Устанавливает ширину столбца.
 *
 * addNewCol () - Добавляет новый столбец для каждой строки в этой таблице.
 *
 * addRow (строка XWPFTableRow, int pos) - Добавляет новую строку в таблицу в позиции поз.
 *
 * createRow () - Создает новый объект XWPFTableRow с количеством ячеек,
 *  равным количеству столбцов, определенных в данный момент.
 *
 * setWidth (int width) - Устанавливает ширину столбца.
 *
 * Остальные методы этого класса см. В полном документе API по адресу: Документация по POI API.
 * (https://poi.apache.org/apidocs/index.html?org/apache/poi/openxml4j/opc/internal/package-summary.html.)
 *
 */
			XWPFTable table = docxModel.createTable(sizeList, 2);

//			if (progArr.isEmpty())

//			for (String str : progArr)
			for (int i = 0; i < sizeList; i++) {
				table.getRow(i).getCell(0).setText(progArr.get(i));
				if(table.getRow(i).getCell(1).getText().isEmpty()) {
					table.getRow(i).getCell(1).setText(progArr.get(i));
				}
			}
//			table.addNewCol();
//			for (String str : progArr) {
//				table.createRow().getCell(1).setText(str);
//			}
			// сохраняем шаблон docx документа в файл

			docxModel.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Файл успешно создан!");
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		CreateScheduleReport csr = new CreateScheduleReport();
		csr.createDoc();
		long finish = System.currentTimeMillis();
		System.out.println("Время выполнения: " + (finish - start) + "_ms");
	}


	private static CTP createFooterModel(String footerContent) {
		// создаем футер или нижний колонтитул
		CTP ctpFooterModel = CTP.Factory.newInstance();
		CTR ctrFooterModel = ctpFooterModel.addNewR();
		CTText cttFooter = ctrFooterModel.addNewT();

		cttFooter.setStringValue(footerContent);
		return ctpFooterModel;
	}

	private static CTP createHeaderModel(String headerContent) {
		// создаем хедер или верхний колонтитул
		CTP ctpHeaderModel = CTP.Factory.newInstance();
		CTR ctrHeaderModel = ctpHeaderModel.addNewR();
		CTText cttHeader = ctrHeaderModel.addNewT();

		cttHeader.setStringValue(headerContent);
		return ctpHeaderModel;
	}

}