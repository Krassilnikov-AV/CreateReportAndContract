/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model.ChekListTeacher;

import java.util.List;

/**
 * Класс ChekListTeachers
 */
public class ChekListTeachers {

	private final List<ChekListTeacher> chekListTeachers;

	public ChekListTeachers(List<ChekListTeacher> chekListTeachers) {
		this.chekListTeachers = chekListTeachers;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<ChekListTeacher> getChekListTeachers() {
		return chekListTeachers;
	}
}