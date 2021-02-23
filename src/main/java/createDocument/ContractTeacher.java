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
	private enum Border {LEFT, TOP, BOTTOM, RIGHT}

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

//			XWPFTable tableFIO = document.createTable(2, 1);
//			tableFIO.setColBandSize();
// вставка в таблицу ФИО
// доработать таблицу, по аналогии следующие
			String fio = "Отличная фамилия";
			/*
			---------------новый метод!!!!!!--------------
			*/

			XWPFTable tableName = document.createTable(2, 1);
			getWidth(tableName, 9150);                      //определение ширины таблицы
			XWPFTableRow rowOne = tableName.getRow(0);

// сделать метод для оптимизации получения данного типа таблиц
			XWPFParagraph paragraph = rowOne.getCell(0).addParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			paragraph.setSpacingAfter(0);
			setRun(paragraph.createRun(), "Times New Roman", 12,
				"2b5079", fio, false, true, false);
			rowOne.getCell(0).removeParagraph(0);
/*
вывод границы необходимой для видимости
вывод границы видимости в данном случае вводится индивидуально
*/
			setTableCellBorder(rowOne.getCell(0), Border.TOP, STBorder.NIL);
			setTableCellBorder(rowOne.getCell(0), Border.LEFT, STBorder.NIL);
			setTableCellBorder(rowOne.getCell(0), Border.RIGHT, STBorder.NIL);

			XWPFTableRow rowTwo = tableName.getRow(1);
			XWPFParagraph paragraph2 = rowTwo.getCell(0).addParagraph();
			paragraph2.setAlignment(ParagraphAlignment.CENTER);
			paragraph2.setSpacingAfter(0);
			setTableCellBorder(rowTwo.getCell(0), Border.BOTTOM, STBorder.NIL);
			setTableCellBorder(rowTwo.getCell(0), Border.RIGHT, STBorder.NIL);
			setTableCellBorder(rowTwo.getCell(0), Border.LEFT, STBorder.NIL);

			setRun(paragraph2.createRun(), "Times New Roman", 8,
				"000000", "Фамилия Имя Отчество", false, false, false);
			rowTwo.getCell(0).removeParagraph(0);

			XWPFParagraph firstPar2 = document.createParagraph();
			firstPar2.setAlignment(ParagraphAlignment.BOTH);     // выравнить по ширине
			XWPFRun firstRun2 = firstPar2.createRun();
			firstRun2.setFontFamily("Times New Roman");
			firstRun2.setFontSize(12);
			firstRun2.setText(" именуемый в дальнейшем «Исполнитель», с другой стороны, " +
				"далее совместно именуемые «Стороны» для целей образовательного процесса заключили" +
				" настоящий Договор (далее - Договор) о нижеследующем:");

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
получение таблицы для вставки наименования прпограммы
*/
			String nameProgram = "«наименование»";
			XWPFTable tableProgram = document.createTable(2, 1);
			getWidth(tableProgram, 9150);

			XWPFTableRow rowOneprog = tableProgram.getRow(0);
			XWPFParagraph paragraphProgramm1 = rowOneprog.getCell(0).addParagraph();

			getParagraphCenter(paragraphProgramm1);
			setRun(paragraphProgramm1.createRun(), "Times New Roman", 12,
				"2b5079", nameProgram, false, true, false);
			rowOneprog.getCell(0).removeParagraph(0);

			setTableCellBorder(rowOneprog.getCell(0), Border.TOP, STBorder.NIL);
			setTableCellBorder(rowOneprog.getCell(0), Border.LEFT, STBorder.NIL);
			setTableCellBorder(rowOneprog.getCell(0), Border.RIGHT, STBorder.NIL);

			XWPFTableRow rowTwoprog = tableProgram.getRow(1);

			XWPFParagraph paragraphProgramm2 = rowTwoprog.getCell(0).addParagraph();
			getParagraphCenter(paragraphProgramm2);

			setRun(paragraphProgramm2.createRun(), "Times New Roman", 8,
				"000000", "(указать учебную тему/темы)", false, true, false);
			rowTwoprog.getCell(0).removeParagraph(0);

			setTableCellBorder(rowTwoprog.getCell(0), Border.BOTTOM, STBorder.NIL);
			setTableCellBorder(rowTwoprog.getCell(0), Border.RIGHT, STBorder.NIL);
			setTableCellBorder(rowTwoprog.getCell(0), Border.LEFT, STBorder.NIL);
			/*
			 * продолжение предмета договора п.2
			 * 1.
			 */
			XWPFParagraph subject2 = document.createParagraph();
			String dataSt = null;  // дата начала договора
			String dataEnd = null; // дата окончания договора
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

			XWPFRun termPlace2 = getItems(document);
			String adresPlace = "ул. Обручевых, дом 1";
			termPlace2.setText("2.2. Место оказания услуг: г. Санкт- Петербург, " + adresPlace);
			termPlace2.addBreak();

			/*цена договора*/
			XWPFRun contractPrice = methodRunTitle(document);
			contractPrice.setText("3. Цена договора и порядок расчётов");

			XWPFRun ContractPrice1 = getItems(document);
			String priceNum = "10000";
			String price = "Десять тысяч";
			ContractPrice1.setText("3.1. Общая цена Договора составляет " + priceNum + "(" + price + ")" +
				" рублей 00 копеек, в том числе НДФЛ.");

			XWPFRun contractPrice2 = getItems(document);
			contractPrice2.setText("3.2. Оплата оказанных Исполнителем услуг производится Заказчиком поэтапно " +
				"за каждый месяц, после подписания Сторонами акта сдачи-приемки оказанных услуг, в котором отражаются" +
				" количество оказанных услуг в часах и сумма, подлежащая плате. ");
			XWPFRun contractPrice3 = getItems(document);
			contractPrice3.setText("Заказчик производит оплату услуг Исполнителя до 15 числа месяца, следующего после " +
				"оказания услуг, при условии предоставления Исполнителем акта сдачи-приемки до 25 числа месяца," +
				" предшествующего месяцу оплаты.");
			XWPFRun contractPrice4 = getItems(document);
			contractPrice4.setText("3.3. Заказчик, в соответствии со статьёй 226 Налогового Кодекса Российской Федерации " +
				"(далее – НК РФ), признаётся налоговым агентом. Заказчик исчисляет и удерживает налог на доходы физических" +
				" лиц (13%) из цены услуг при фактической оплате Исполнителю.");
			XWPFRun contractPrice5 = getItems(document);
			contractPrice5.setText("Удержанную сумму налога на доходы физических лиц Заказчик уплачивает" +
				" по месту своего учёта в налоговом органе.\n");
			XWPFRun contractPrice6 = getItems(document);
			contractPrice6.setText("3.4. Оплата по Договору производится в безналичном порядке путём перечисления " +
				"соответствующей денежной суммы на счёт Исполнителя, указанный в пункте 10 Договора.");
			XWPFRun contractPrice7 = getItems(document);
			contractPrice7.setText("3.5. Оплата по Договору производится за счёт средств от приносящей доход деятельности.");
			contractPrice7.addBreak();

			XWPFRun rightObligation = methodRunTitle(document);
			rightObligation.setText("4. Права и обязанности сторон");
			XWPFRun rightObligation41 = getItems(document);
			rightObligation41.setBold(true);
			rightObligation41.setText("4.1. Исполнитель обязан:");
			XWPFRun rightObligation411 = getItems(document);
			rightObligation411.setText("4.1.1. Качественно и в полном объёме оказывать услуги Заказчику," +
				" указанные в пункте 1.1 Договора.");
			XWPFRun rightObligation412 = getItems(document);
			rightObligation412.setText("4.1.2. Заблаговременно, не позднее чем за один рабочий день, " +
				"извещать Заказчика о невозможности оказать обусловленные Договором услуги.");
			XWPFRun rightObligation413 = getItems(document);
			rightObligation413.setText("4.1.3. Информировать Заказчика, по запросу последнего, о ходе исполнения условий Договора.");
			XWPFRun rightObligation42 = getItems(document);
			rightObligation42.setBold(true);
			rightObligation42.setText("4.2. Исполнитель вправе:");
			XWPFRun rightObligation421 = getItems(document);
			rightObligation421.setText("4.2.1. Исполнитель имеет право завершить оказание услуг досрочно.");
			XWPFRun rightObligation422 = getItems(document);
			rightObligation422.setText("4.2.2. Самостоятельно определять методы и средства оказания услуг.");
			XWPFRun rightObligation423 = getItems(document);
			rightObligation423.setText("4.2.3. Требовать оплаты от Заказчика за оказанные услуги в размере и сроки, " +
				"предусмотренные условиями Договора.");
			XWPFRun rightObligation43 = getItems(document);
			rightObligation43.setBold(true);
			rightObligation43.setText("4.3. Заказчик обязуется");
			XWPFRun rightObligation431 = getItems(document);
			rightObligation431.setText("4.3.1. Создать условия Исполнителю для оказания услуг, предусмотренных Договором. " +
				"Предоставить для проведения занятий аудиторию, соответствующую санитарным нормам и правилам.");
			XWPFRun rightObligation432 = getItems(document);
			rightObligation432.setText("4.3.2. Оплачивать услуги Исполнителя в размере, порядке и на условиях, " +
				"которые предусмотрены Договором.");
			XWPFRun rightObligation44 = getItems(document);
			rightObligation44.setBold(true);
			rightObligation44.setText("4.4. Заказчик вправе:");
			XWPFRun rightObligation441 = getItems(document);
			rightObligation441.setText("4.4.1. Проверять ход и качество оказания услуг в период действия Договора," +
				" не вмешиваясь в деятельность Исполнителя. В случае выявления Заказчиком нарушений в ходе оказания " +
				"услуг со стороны Исполнителя Сторонами составляется двусторонний акт с указанием недостатков и сроков" +
				" их устранения.");
			XWPFRun rightObligation442 = getItems(document);
			rightObligation442.setText("4.4.2. Отказаться по своей инициативе от исполнения Договора в любое время в период " +
				"действия Договора, уплатив Исполнителю часть установленного вознаграждения пропорционально части услуг," +
				" оказанных до получения Исполнителем уведомления об отказе Заказчика от исполнения Договора, на основании " +
				"двустороннего акта.\n");
			XWPFRun rightObligationEnd = getItems(document);
			rightObligationEnd.addBreak();

			XWPFRun responsibility = methodRunTitle(document);
			responsibility.addBreak();
			responsibility.setText("5. Ответственность сторон");
			XWPFRun responsibility51 = getItems(document);
			responsibility51.setText(" 5.1. Стороны несут ответственность за неисполнение либо за ненадлежащее " +
				"исполнение обязательств по Договору в соответствии с действующим законодательством Российской Федерации и " +
				"условиями Договора.\n");
			XWPFRun responsibility52 = getItems(document);
			responsibility52.setText(" 5.2. Договор может быть изменен или расторгнут по письменному соглашению Сторон, " +
				"в судебном порядке, а также в случае одностороннего отказа Стороны от исполнения Договора по основаниям " +
				"и в порядке, предусмотренном законодательством Российской Федерации и Договором.\n");
			XWPFRun responsibility53 = getItems(document);
			responsibility53.setText(" 5.3. В случае оказания услуг ненадлежащего качества Заказчик вправе взыскать с Исполнителя" +
				" неустойку в размере 1% от цены Договора. В случае просрочки исполнения обязательств по Договору " +
				"Заказчик вправе требовать оплаты неустойки (пени) в размере 0,1% от цены Договора за каждый день просрочки.\n");
			XWPFRun responsibility54 = getItems(document);
			responsibility54.setText(" 5.4. За нарушение сроков оплаты (раздел 3 Договора) Исполнитель вправе требовать с " +
				"Заказчика уплаты неустойки (пени) в размере одной трёхсотой ключевой ставки Банка России от невыплаченной в " +
				"срок суммы за каждый день просрочки, начиная со следующего дня после наступления установленного срока оплаты" +
				" по день фактической выплаты включительно, но не более суммы Договора.\n");

			XWPFRun forceMajeure = methodRunTitle(document);
			forceMajeure.setText("6. Форс-мажор");
			XWPFRun forceMajeure1 = getItems(document);
			forceMajeure1.setText("6.1. Стороны освобождаются от ответственности за частичное или полное неисполнение" +
				" своих обязательств по Договору, если оно явилось следствием обстоятельств непреодолимой силы, в том" +
				" числе: " +
				"пожара, наводнения, землетрясения, забастовки, военных действий, актов органов государственной власти, " +
				"и если эти обстоятельства возникли после заключения Договора и непосредственно повлияли на исполнение " +
				"Договора.\n");

			XWPFRun basisValidityPeriod = methodRunTitle(document);
			basisValidityPeriod.addBreak();
			basisValidityPeriod.setText("7. Срок действия, основания и порядок изменения и расторжения Договора");
			XWPFRun basisValidityPeriod71 = getItems(document);
			basisValidityPeriod71.setText("7.1. Договор вступает в силу со дня его подписания Сторонами и действует до 00.00.2021.");
			XWPFRun basisValidityPeriod72 = getItems(document);
			basisValidityPeriod72.setText("7.2. Любые изменения и дополнения к Договору действительны при условии, если они совершены" +
				" в письменной форме и подписаны уполномоченными представителями Сторон.\n");
			XWPFRun basisValidityPeriod73 = getItems(document);
			basisValidityPeriod73.setText("7.3. Расторжение Договора допускается по соглашению Сторон, по решению суда и по " +
				"иным основаниям, предусмотренным законодательством Российской Федерации.\n");
			XWPFRun basisValidityPeriod74 = getItems(document);
			basisValidityPeriod74.setText("7.4. Заказчик вправе отказаться от исполнения обязательств по Договору, письменно " +
				"уведомив об этом исполнителя не менее чем за 14 (четырнадцать) дней, при условии оплаты Исполнителю " +
				"фактически оказанных услуг,  в следующих случаях:\n");
			XWPFRun basisValidityPeriod741 = getItems(document);
			basisValidityPeriod741.setText("- если Исполнитель не приступает в установленный Договором срок\n" +
				"к исполнению Договора или оказывает Услугу таким образом, что окончание её к сроку, предусмотренному Договором, " +
				"становится явно невозможным, либо в ходе оказания Услуги стало очевидно, что она не будет оказана надлежащим " +
				"образом в срок,  установленный Договором;");
			XWPFRun basisValidityPeriod742 = getItems(document);
			basisValidityPeriod742.setText("- если Исполнитель оказывает услугу таким образом, что окончание её к сроку, " +
				"предусмотренному Договором, становится явно невозможным, либо в ходе оказания услуги стало очевидно, что " +
				"она не будет оказана надлежащим образом в срок, установленный Договором;");
			XWPFRun basisValidityPeriod743 = getItems(document);
			basisValidityPeriod743.setText("- если во время оказания услуги нарушены условия исполнения Договора, " +
				"и в назначенный Заказчиком для устранения нарушений разумный срок Исполнителем такие нарушения не " +
				"устранены, либо являются существенными и неустранимыми.\n");

			XWPFRun disputeResolution = methodRunTitle(document);
			disputeResolution.addBreak();
			disputeResolution.setText("8. Разрешение споров");
			XWPFRun disputeResolution81 = getItems(document);
			disputeResolution81.setText("8.1. Все споры и разногласия, возникающие между Сторонами по вопросам " +
				"исполнения обязательств по Договору, будут разрешаться путем переговоров.\n");
			XWPFRun disputeResolution82 = getItems(document);
			disputeResolution82.setText("8.2. В случае недостижения согласия, спор передается на рассмотрение в суд" +
				" в соответствии с действующим законодательством РФ.\n");

			XWPFRun finalProvisions = methodRunTitle(document);
			finalProvisions.addBreak();
			finalProvisions.setText("9. Заключительные положения");
			XWPFRun finalProvisions91 = getItems(document);
			finalProvisions91.setText("9.1. В случае изменения наименования, местонахождения, банковских реквизитов " +
				"и других данных каждая из Сторон обязана в течение 5 (пяти) дней в письменной форме сообщить другой Стороне" +
				" о произошедших изменениях.\n");
			XWPFRun finalProvisions92 = getItems(document);
			finalProvisions92.setText("9.2. Вся переписка между Сторонами ведётся по адресам, указанным в пункте 10 Договора.");
			XWPFRun finalProvisions921 = getItems(document);
			finalProvisions921.setText("Каждая из Сторон обязана немедленно известить другую Сторону об изменении своих " +
				"реквизитов. До получения такого извещения все письменные сообщения, направленные по прежним адресам, " +
				"считаются направленными надлежащим образом.\n");
			XWPFRun finalProvisions93 = getItems(document);
			finalProvisions93.setText("9.3. Во всём остальном, что не предусмотрено Договором, Стороны руководствуются " +
				"действующим законодательством Российской Федерации.\n");
			XWPFRun finalProvisions94 = getItems(document);
			finalProvisions94.setText("9.4. Договор составлен в 3 (трех) экземплярах, имеющих равную юридическую силу.");

			/*

			 */
			XWPFRun addressBankdetails = methodRunTitle(document);
			addressBankdetails.setText("10. Адреса и реквизиты сторон");
			createTableDetalsCustomerExecutor(document);
			XWPFParagraph xwpfParagraph = document.createParagraph();
			XWPFRun runEnd = xwpfParagraph.createRun();
			runEnd.addBreak(BreakType.PAGE);

			creteActReceptionDelivery(document);
			/*создание документа*/
			document.write(outputStream);
			System.out.println("Файл успешно создан!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Файл к сожалению не создан");
		}
	}

	/*метод для получения акта №1*/
	private void creteActReceptionDelivery(XWPFDocument document) {
		XWPFRun oneStr = getLeftParagraphRun(document);
		oneStr.setText("Федеральное государственное автономное образовательное учреждение высшего образования");
		XWPFRun twoStr = getCenterParagraphRun(document);
		twoStr.setText("«Санкт-Петербургский политехнический университет Петра Великого»");
		XWPFRun threeStr = getCenterParagraphRun(document);
		threeStr.setText("Высшая инженерная школа Институт дополнительного образования");
		XWPFRun fourStr = getCenterParagraphRun(document);
		fourStr.setFontSize(10);
		fourStr.setText("___________________________________________________________________________________________");
		XWPFRun fiveStr = getCenterParagraphRun(document);
		fiveStr.setFontSize(8);
		fiveStr.setText("                         (структурное подразделение)");
		XWPFRun sixStr = getCenterParagraphRun(document);
		sixStr.setFontSize(14);
		sixStr.setBold(true);
		sixStr.setText("АКТ № 1 от ");
		String dateAct = "сентября 2021";
		String dateDay = "";
		sixStr.setText(" «" + dateDay + "»  " + dateAct + " г. ");
		XWPFRun sevenStr = getLeftParagraphRun(document);
		sevenStr.setText("сдачи-приёмки оказанных услуг к договору от «___»                2021 г. № _______");
		XWPFRun eightStr = getLeftParagraphRun(document);
		eightStr.setItalic(true);
		eightStr.setBold(true);
		eightStr.setText("                            Оплата из средств л/с");
		String score = "124503003";
		XWPFRun eightOneStr = eightStr;
		eightOneStr.setText(score);
		eightOneStr.addBreak();
		XWPFRun runTen = getIndentationRun(document);
		String official = "и.о. директора Института дополнительного образования Курзановой Александры Сергеевны";
		String prikaz = "юр-323/20-д от 29.12.2020";
		runTen.setText("Федеральное государственное автономное образовательное учреждение высшего образования " +
			"«Санкт-Петербургский политехнический университет Петра Великого», именуемое в дальнейшем «Заказчик», в " +
			"лице " + official + " действующей на основании Доверенности    № " + prikaz + ", с одной стороны, и гражданина (ка) " +
			"Российской Федерации ФИО, именуемый (ая) в дальнейшем «Исполнитель», с другой стороны, составили настоящий Акт " +
			"о нижеследующем:  ");
		XWPFRun runEleven = geWidthParagraphRun(document);
		String dop = "дополнительной общеобразовательной общеразвивающей программе: «наименование»";
		runEleven.setText("Во исполнение Договора от «___»            2021 № _____________ Исполнитель оказал," +
			" а Заказчик принял оказанные образовательные услуги по " + dop);

		XWPFTable tableAct = document.createTable(3, 3);
		getWidth(tableAct, 9700);
		// выровнить по колонкам

		XWPFTableRow row0=tableAct.getRow(0);
		XWPFParagraph paragraph00=row0.getCell(0).addParagraph();
		row0.getCell(0).removeParagraph(0);
		getParagraphCenter(paragraph00);
		setRun(paragraph00.createRun(),"Times New Roman",
			10, "000000", "Дата", false, false, false);
		XWPFParagraph paragraph01=row0.getCell(1).addParagraph();
		row0.getCell(1).removeParagraph(0);
		getParagraphCenter(paragraph01);
		setRun(paragraph01.createRun(),"Times New Roman",
			10, "000000", "Группа", false, false, false);
		XWPFParagraph paragraph02=row0.getCell(2).addParagraph();
		row0.getCell(2).removeParagraph(0);
		getParagraphCenter(paragraph02);
		setRun(paragraph02.createRun(),"Times New Roman",
			10, "000000", "Количество часов", false, false, false);
		/* слияние ячеек таблиц*/
		// First Row
//		CTHMerge hMerge = CTHMerge.Factory.newInstance();
//		hMerge.setVal(STMerge.RESTART);
//		tableAct.getRow(1).getCell(0).getCTTc().getTcPr().setHMerge(hMerge);
//		// Secound Row cell will be merged/"deleted"
//		CTHMerge hMerge1 = CTHMerge.Factory.newInstance();
//		hMerge.setVal(STMerge.CONTINUE);
//		tableAct.getRow(1).getCell(1).getCTTc().getTcPr().setHMerge(hMerge1);

//		XWPFTableRow rowAgreement = tableAct.getRow(1);
//		XWPFParagraph parAgreement = rowAgreement.getCell(0).addParagraph();
//		rowAgreement.getCell(0).removeParagraph(0);
//		setRun(parAgreement.createRun(), "Times New Roman",
//			12, "000000", " ", true, false, false);

	}

	private void deleteParagraph(XWPFDocument document, XWPFParagraph par) {
		int pPos = document.getPosOfParagraph(par);
		document.removeBodyElement(pPos);
	}

	/*
	 * получение прозрачных границ таблицы с заполнением
	 * */
	private void createTableDetalsCustomerExecutor(XWPFDocument document) {
		XWPFTable tabBank = document.createTable(6, 2);
// доработать: равномерные колонки по ширине
		getWidth(tabBank, 9700);
		for (XWPFTableRow row : tabBank.getRows()) {
			row.getCell(0);
			setTableCellBorder(row.getCell(0), Border.TOP, STBorder.NIL);
			setTableCellBorder(row.getCell(0), Border.BOTTOM, STBorder.NIL);
			setTableCellBorder(row.getCell(0), Border.LEFT, STBorder.NIL);
			setTableCellBorder(row.getCell(0), Border.RIGHT, STBorder.NIL);
			setTableCellBorder(row.getCell(1), Border.TOP, STBorder.NIL);
			setTableCellBorder(row.getCell(1), Border.BOTTOM, STBorder.NIL);
			setTableCellBorder(row.getCell(1), Border.LEFT, STBorder.NIL);
			setTableCellBorder(row.getCell(1), Border.RIGHT, STBorder.NIL);
		}
		XWPFTableRow rowTitulСustomer = tabBank.getRow(0);
		XWPFParagraph paragraphTitulСustomer = rowTitulСustomer.getCell(0).addParagraph();
		rowTitulСustomer.getCell(0).removeParagraph(0);
		getParagraphCenter(paragraphTitulСustomer);
		setRun(paragraphTitulСustomer.createRun(), "Times New Roman",
			12, "000000", "ЗАКАЗЧИК", true, false, false);

		XWPFTableRow rowTitulExecutor = tabBank.getRow(0);
		XWPFParagraph paragraphTitulExecutor = rowTitulExecutor.getCell(1).addParagraph();
		rowTitulExecutor.getCell(1).removeParagraph(0);
		getParagraphCenter(paragraphTitulExecutor);
		setRun(paragraphTitulExecutor.createRun(), "Times New Roman",
			12, "000000", "ИСПОЛНИТЕЛЬ", true, false, false);

		XWPFTableRow rowBankDetal = tabBank.getRow(1);
		XWPFParagraph parBankDet = rowBankDetal.getCell(0).addParagraph();
		rowBankDetal.getCell(0).removeParagraph(0);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "ФГАОУ ВО «СПбПУ»", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "Юридический адрес:", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "195251, Санкт-Петербург,", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "Политехническая ул., 29", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "ИНН 7804040077, КПП 780401001", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "ОКАТО 40273562000, ОКПО: 02068574", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "р/счет 40503810990554000001", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "БИК 044030790", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "ПАО «БАНК «САНКТ-ПЕТЕРБУРГ»", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "к/счет 30101810900000000790", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", " ", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", " ", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", " ", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "Почтовый адрес: ", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "195251, С.-Петербург, ", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "ул. Политехническая, д.29 ", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "Тел.: (812) 552-66-12 ", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "Факс: (812) 552-60-80 ", false, false, true);
		setRun(parBankDet.createRun(), "Times New Roman",
			12, "000000", "E-mail: office.ido@spbstu.ru ", false, false, false);

		XWPFTableRow rowExecutorDetal = tabBank.getRow(1);
		XWPFParagraph parExecutorDet = rowExecutorDetal.getCell(1).addParagraph();
		rowExecutorDetal.getCell(1).removeParagraph(0);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Ф.И.О.", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Дата рождения", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Место рождения:", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Адрес регистрации:", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Образование: высшее", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Образование: высшее", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Данные диплома вуза:", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", " ", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "серия  №  от", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", " ", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Паспорт серия  №", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Выдан кем/когда", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "ИНН", false, true, true);
		setRun(parExecutorDet.createRun(), "Times New Roman",
			12, "2b5079", "Номер страхового свидетельства", false, true, false);

		XWPFTableRow rowExecutorBank = tabBank.getRow(2);
		XWPFParagraph parExecutorBank = rowExecutorBank.getCell(1).addParagraph();
		rowExecutorBank.getCell(1).removeParagraph(0);
		setRun(parExecutorBank.createRun(), "Times New Roman",
			12, "2b5079", "Наименование банка", false, true, true);
		setRun(parExecutorBank.createRun(), "Times New Roman",
			12, "2b5079", "БИК банка 044030790", false, true, true);
		setRun(parExecutorBank.createRun(), "Times New Roman",
			12, "2b5079", "№ счета 40817810090700087671", false, true, true);
		setRun(parExecutorBank.createRun(), "Times New Roman",
			12, "2b5079", "№ карты МИР 2200330564638743963", false, true, true);
		setRun(parExecutorBank.createRun(), "Times New Roman",
			12, "2b5079", "Тел.8921-942-0888", false, true, false);

		XWPFTableRow rowСustomer = tabBank.getRow(3);
		XWPFParagraph parСustomer = rowСustomer.getCell(0).addParagraph();
		setRun(parСustomer.createRun(), "Times New Roman",
			12, "000000", "Заказчик", true, false, true);
		setRun(parСustomer.createRun(), "Times New Roman",
			12, "2b5079", "И.о. Директора Института дополнительного", false, true, true);
		setRun(parСustomer.createRun(), "Times New Roman",
			12, "2b5079", "образования", false, true, true);
		setRun(parСustomer.createRun(), "Times New Roman",
			8, "2b5079", "", false, true, true);
		setRun(parСustomer.createRun(), "Times New Roman",
			12, "2b5079", "___________________/А.С. Курзанова/", true, false, false);

		XWPFTableRow rowExecutor = tabBank.getRow(3);
		XWPFParagraph parExecutor = rowExecutor.getCell(1).addParagraph();
		setRun(parExecutor.createRun(), "Times New Roman",
			12, "2b5079", "Исполнитель:", false, true, true);
		setRun(parExecutor.createRun(), "Times New Roman",
			12, "2b5079", "", false, true, true);
		setRun(parExecutor.createRun(), "Times New Roman",
			12, "2b5079", "", false, true, true);
		setRun(parExecutor.createRun(), "Times New Roman",
			12, "2b5079", "_________________/ФИО/", false, false, false);

		XWPFTableRow rowCustomerMP = tabBank.getRow(4);
		XWPFParagraph parExecutorMP = rowCustomerMP.getCell(0).addParagraph();
		rowCustomerMP.getCell(0).removeParagraph(0);
		setRun(parExecutorMP.createRun(), "Times New Roman",
			12, "000000", "                МП", false, false, true);
		/*слияние ячеек таблиц*/
		// First Row
		CTHMerge hMerge = CTHMerge.Factory.newInstance();
		hMerge.setVal(STMerge.RESTART);
		tabBank.getRow(5).getCell(0).getCTTc().getTcPr().setHMerge(hMerge);
		// Secound Row cell will be merged/"deleted"
		CTHMerge hMerge1 = CTHMerge.Factory.newInstance();
		hMerge.setVal(STMerge.CONTINUE);
		tabBank.getRow(5).getCell(1).getCTTc().getTcPr().setHMerge(hMerge1);

		XWPFTableRow rowAgreement = tabBank.getRow(5);
		XWPFParagraph parAgreement = rowAgreement.getCell(0).addParagraph();
		rowAgreement.getCell(0).removeParagraph(0);
		setRun(parAgreement.createRun(), "Times New Roman",
			12, "000000", "Согласовано", true, false, true);
		setRun(parAgreement.createRun(), "Times New Roman",
			12, "000000", "Директор ", false, true, false);
		setRun(parAgreement.createRun(), "Times New Roman",
			10, "000000", "ВИШ ИДО", false, false, true);
		setRun(parAgreement.createRun(), "Times New Roman",
			8, "000000", "                           (института)", false, false, true);
		setRunH(parAgreement.createRun(), "Times New Roman",
			12, "000000",
			"                                                          Кудаков А.В.   ",
			true, false, true, false);
		setRun(parAgreement.createRun(), "Times New Roman",
			12, "000000", "  /____________________/",
			false, false, true);
		setRun(parAgreement.createRun(), "Times New Roman",
			8, "000000",
			"                    			               (Фамилия, инициалы)               " +
				"(Подпись)", false, false, false);

	}
	/*
	 *
	 */

	private XWPFParagraph getParagraphCenter(XWPFParagraph paragraph) {
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		paragraph.setSpacingAfter(0);
		return paragraph;
	}

	/*
	 * метод для получения оглавлений разделов договора
	 * */
	private XWPFRun methodRunTitle(XWPFDocument doc) {
		XWPFParagraph title = doc.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		title.setSpacingAfter(0);          // интервал после последней строки
		XWPFRun paraTitRun = title.createRun();
		paraTitRun.setBold(true);
		paraTitRun.setFontFamily("Times New Roman");
		paraTitRun.setFontSize(12);
		return paraTitRun;
	}

	/*
	 * метод для описания обзаца с красной строки на 1,5
	 */

	private XWPFRun getIndentationRun(XWPFDocument doc) {
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);     // выравнить по ширине
		paragraph.setSpacingBetween(1.0);         // межстрочный интервал в абзаце
		paragraph.setIndentationHanging(-1050);         // отступ с левого края
		paragraph.setSpacingAfter(0);
		XWPFRun paragraphRun = paragraph.createRun();       //
		paragraphRun.setFontFamily("Times New Roman");     // шрифт название
		paragraphRun.setFontSize(12);                        // номер шрифта
		return paragraphRun;
	}

	/*метод для определения абзаца с красной строки документа
	 * выравнивания по ширине
	 * межстрочного интарвала 1.0*/
	private XWPFRun getItems(XWPFDocument doc) {
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);
		paragraph.setSpacingBetween(1.0);
		paragraph.setSpacingAfter(0);
		paragraph.setIndentationHanging(-571);
		XWPFRun run = getMethodRun(paragraph);
		return run;
	}

	/*метод определит абзац без красной строки
	 * выравнивание по левому краю*/
	private XWPFRun getLeftParagraphRun(XWPFDocument doc) {
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		paragraph.setSpacingBetween(1.0);
		paragraph.setSpacingAfter(0);
		XWPFRun leftRun = getMethodRun(paragraph);
		return leftRun;
	}

	/*метод определит абзац без красной строки
	 * выравнивание по ширине*/
	private XWPFRun geWidthParagraphRun(XWPFDocument doc) {
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);
		paragraph.setSpacingBetween(1.0);
		paragraph.setSpacingAfter(0);
		XWPFRun widthRun = getMethodRun(paragraph);
		return widthRun;
	}

	private XWPFRun getCenterParagraphRun(XWPFDocument doc) {
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		paragraph.setSpacingBetween(1.0);
		paragraph.setSpacingAfter(0);
		XWPFRun centerRun = getMethodRun(paragraph);
		return centerRun;
	}

	/*метод получения Run*/
	private XWPFRun getMethodRun(XWPFParagraph paragraph) {
		XWPFRun paragraphRun = paragraph.createRun();
		paragraphRun.setFontFamily("Times New Roman");
		paragraphRun.setFontSize(12);
		return paragraphRun;
	}

	/*
	 * метод получения определённых границ в таблице документа
	 * */
	void setTableCellBorder(XWPFTableCell cell, Border border, STBorder.Enum type) {
		CTTc tc = cell.getCTTc();
		CTTcPr tcPr = tc.getTcPr();
		if (tcPr == null) tcPr = tc.addNewTcPr();
		CTTcBorders tcBorders = tcPr.getTcBorders();
		if (tcBorders == null) tcBorders = tcPr.addNewTcBorders();
		if (border == Border.LEFT) {
			CTBorder left = tcBorders.getLeft();
			if (left == null) left = tcBorders.addNewLeft();
			left.setVal(type);
		} else if (border == Border.TOP) {
			CTBorder top = tcBorders.getTop();
			if (top == null) top = tcBorders.addNewTop();
			top.setVal(type);
		} else if (border == Border.BOTTOM) {
			CTBorder bottom = tcBorders.getBottom();
			if (bottom == null) bottom = tcBorders.addNewBottom();
			bottom.setVal(type);
		} else if (border == Border.RIGHT) {
			CTBorder right = tcBorders.getRight();
			if (right == null) right = tcBorders.addNewRight();
			right.setVal(type);
		}
	}

	/*
	 * метод для получения настроек текста в ячейке
	 * */
	private void setRun(XWPFRun run,
						String fontFamily,
						int fontSize, String colorRGB, String text, boolean bold,
						boolean italic, boolean addBreak) {
		run.setFontFamily(fontFamily);
		run.setFontSize(fontSize);
		run.setColor(colorRGB);
		run.setText(text);
		run.setBold(bold);
		run.setItalic(italic);
		if (addBreak) run.addBreak();
	}

	private void setRunH(XWPFRun run, String fontFamily, int fontSize, String colorRGB, String text, boolean bold,
						 boolean italic, boolean un, boolean addBreak) {
//		XWPFDocument doc = new XWPFDocument();
//		XWPFParagraph paragraph = doc.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		XWPFRun run1 = paragraph.createRun();
//		run=run1;
		run.setFontFamily(fontFamily);
		run.setFontSize(fontSize);
		run.setColor(colorRGB);
		run.setText(text);
		run.setBold(bold);
		run.setItalic(italic);
		if (un) run.setUnderline(UnderlinePatterns.WORDS);
		if (addBreak) run.addBreak();
	}

	/*
	 *метод для задания ширтны таблицы
	 */
	//	9150
	private CTTblWidth getWidth(XWPFTable tab, int value) {
		CTTblWidth width = tab.getCTTbl().addNewTblPr().addNewTblW();
		width.setType(STTblWidth.DXA);
		width.setW(BigInteger.valueOf(value));
		return width;
	}
}