/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package createDocument.example;

import java.text.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Класс ExampleClass
 */
public class ExampleClass {
	// пробный метод "регулярок"
	private void regexExample() {
		// элементарный пример поиска номера телефона
//		String inputString = "This is simple that contains phone number +380505055050 That's great.";
//		String pattern = "(\\d+)";
//
//		Pattern pat = Pattern.compile(pattern);
//		Matcher matcher = pat.matcher(inputString);
//
//		if(matcher.find()){
//			System.out.println("Phone number: " + matcher.group(0));
//		}else {
//			System.out.println("PHONE NUMBER NOT FOUND");
//		}

//		String str = "today is tuesday";
//		return ; // returns "false"
//		System.out.println(str.matches(".*?\\b.\\br*?"));  // false

//		String text = "Егор Алла Александр";
//		Pattern pattern = Pattern.compile("А.+а");
//		Matcher matcher = pattern.matcher(text);
//		while (matcher.find()) {
//			System.out.println(text.substring(matcher.start(), matcher.end()));     // Алла Алекса
//		}
//		String text = "Егор Алла Анна";
		String text = "Егор (Алла) Анна";
		Pattern pattern = Pattern.compile("\\s");
		String[] strings = pattern.split(text, 2);
		for (String s : strings) {
			System.out.println(s);
		}
		System.out.println("---------");
		String[] strings1 = pattern.split(text);
		for (String s : strings1) {

			System.out.println(s);

		}
	}


	// показательный метод для сравнения дат

	private void dateEquels() throws ParseException {
//		Date date = new Date();
		String str = "2013-08-23";
		String str1 = "2014-08-23";
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(str1);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		if (cal.get(Calendar.YEAR) == cal1.get(Calendar.YEAR)) {
			System.out.println("Years are equal");
		} else {
			System.out.println("Years not equal");
		}

		if (cal.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)) {
			System.out.println("Months are equal");
		} else {
			System.out.println("Months not equal");
		}
	}
/*
преобразование строки в дату
 */
	private void exDate() {
		String stringDate = "03/08/1995";
		String stringDate2 = "02-09-1995 23:37:50";
		String stringDate3 = "02-May-1995";
		String stringDate4 = "04 02, 1995";
		String stringDate5 = "Thu, May 02 1995";
		String stringDate6 = "Thu, May 02 1995 23:37:50";
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat date2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		DateFormat date3 = new SimpleDateFormat("dd-MMM-yyyy");
		DateFormat date4 = new SimpleDateFormat("MM dd, yyyy");
		DateFormat date5 = new SimpleDateFormat("E, MMM dd yyyy");
		DateFormat date6 = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
		try {
			Date nDate1 = date.parse(stringDate);
			System.out.println(stringDate + " : " + date.format(nDate1));
			Date nDate2 = date2.parse(stringDate2);
			System.out.println(stringDate2 + " : " + date2.format(nDate2));
			Date nDate3 = date3.parse(stringDate3);
			System.out.println(stringDate3 + " : " + date3.format(nDate3));
			Date nDate4 = date4.parse(stringDate4);
			System.out.println(stringDate4 + " : " + date4.format(nDate4));
			Date nDate5 = date5.parse(stringDate5);
			System.out.println(stringDate5 + " : " + date5.format(nDate5));
			Date nDate6 = date6.parse(stringDate6);
			System.out.println(stringDate6 + " : " + date6.format(nDate6));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
/*
преобразование даты в строку
 */

	public static void main(String[] args) {
		ExampleClass exClass = new ExampleClass();
		exClass.exDate();
	}
}