/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package query;

import java.sql.SQLException;

/**
 * Класс Shedule
 */
public class Shedule {
	public String prog;

	private void methodChek() throws SQLException {
//		SQLQueryData sqlQueryData = new SQLQueryData();
//		LinkedList<String> contents = sqlQueryData.searchToProgram("Java");
//
//		int size = contents.size();
//		for (int i=0; i<size; i++) {
//			String el=contents.get(i);
//			System.out.println(el);
//		}
//		for (Object o : contents) {
////			contents.add((String) o);
//			System.out.println(o);
//
//		}

//		for (int i = 0; i < list.length; i++) {
//			contents.add(i, );
//		}

	}


	public static void main(String[] args) throws SQLException {
		long start = System.currentTimeMillis();
		Shedule sched = new Shedule();
		sched.methodChek();

		long finish = System.currentTimeMillis();
		System.out.println("Время выполнения: " + (finish - start) + "_ms");
	}

}