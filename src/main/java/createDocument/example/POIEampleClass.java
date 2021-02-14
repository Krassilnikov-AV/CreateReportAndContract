/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package createDocument.example;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;

/**
 * Класс POIEampleClass
 */
public class POIEampleClass {

	public static void main(String[] args) throws IOException, XmlException {
		// create footer components
		XWPFDocument document = new XWPFDocument();
		CTP footerCtp = CTP.Factory.newInstance();
		CTR footerCtr = footerCtp.addNewR();
		XWPFParagraph footerCopyrightParagraph = new XWPFParagraph(footerCtp, document);
		document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
		XWPFRun run = footerCopyrightParagraph.getRun(footerCtr);
		run.setText("My Website.com");
		run.addTab();
		run.setText("\u00A9" + " My Website - " + Calendar.getInstance().get(Calendar.YEAR));
		run.addTab();
		run.setText("Right Side Text");

		setTabStop(footerCtp, STTabJc.Enum.forString("right"), BigInteger.valueOf(9000));

		XWPFParagraph[] footerParagraphs = {footerCopyrightParagraph};
		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
		try {
			headerFooterPolicy.createFooter(STHdrFtr.DEFAULT, footerParagraphs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void setTabStop(CTP oCTP, STTabJc.Enum oSTTabJc, BigInteger oPos) {
		CTPPr oPPr = oCTP.getPPr();
		if (oPPr == null) {
			oPPr = oCTP.addNewPPr();
		}

		CTTabs oTabs = oPPr.getTabs();
		if (oTabs == null) {
			oTabs = oPPr.addNewTabs();
		}

		CTTabStop oTabStop = oTabs.addNewTab();
		oTabStop.setVal(oSTTabJc);
		oTabStop.setPos(oPos);
	}
}