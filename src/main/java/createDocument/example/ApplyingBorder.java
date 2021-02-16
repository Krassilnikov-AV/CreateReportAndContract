/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package createDocument.example;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;

/**
 * Класс ApplyingBorder
 */
public class ApplyingBorder {
	public static void main(String[] args)throws Exception {

		//Blank Document
		XWPFDocument document = new XWPFDocument();

		//Write the Document in file system

		FileOutputStream out = new FileOutputStream("D:\\REPOSITORIES-2\\BorderExample.docx");

		//create paragraph
		XWPFParagraph paragraph = document.createParagraph();

		//Set bottom border to paragraph
		paragraph.setBorderBottom(Borders.BASIC_WHITE_DASHES);

		//Set left border to paragraph
		paragraph.setBorderLeft(Borders.BASIC_WHITE_DASHES);

		//Set right border to paragraph
		paragraph.setBorderRight(Borders.BASIC_WHITE_DASHES);

		//Set top border to paragraph
		paragraph.setBorderTop(Borders.BASIC_WHITE_DASHES);

		XWPFRun run = paragraph.createRun();
		run.setText("At tutorialspoint.com, we strive hard to " +
			"provide quality tutorials for self-learning " +
			"purpose in the domains of Academics, Information " +
			"Technology, Management and Computer Programming " +
			"Languages.");

		document.write(out);
		out.close();
		System.out.println("applyingborder.docx written successully");
	}
}